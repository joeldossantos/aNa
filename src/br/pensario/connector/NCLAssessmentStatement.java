package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLValues.NCLComparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma assertiva de um conector de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.1
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLAssessmentStatement<S extends NCLStatement, A extends NCLAttributeAssessment, V extends NCLValueAssessment>
        extends NCLElement implements NCLStatement<S> {

    private NCLComparator comparator;
    
    private V valueAssessment;
    private Set<A> attributeAssessments = new TreeSet<A>();
    

    /**
     * Construtor do elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLAssessmentStatement() {}


    /**
     * Construtor do elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLAssessmentStatement(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Determina o comparador da assertiva.
     * 
     * @param comparator
     *          comparador utilizado pela assertiva.
     */
    public void setComparator(NCLComparator comparator) {
        this.comparator = comparator;
    }
    
    
    /**
     * Retorna o comparador da assertiva.
     * 
     * @return
     *          comparador utilizado pela assertiva.
     */
    public NCLComparator getComparator() {
        return comparator;
    }
    
    
    /**
     * Determina um valor de comparação a assertiva.
     * 
     * @param value
     *          String representando o valor de comparação a ser utilizado.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor for uma String vazia.
     *
     * @see NCLValueAssessment
     */
    public void setValueAssessment(V value) {
        this.valueAssessment = value;
    }
    
    
    /**
     * Retorna o valor de comparação a assertiva.
     * 
     * @return
     *          elemento representando o valor de comparação utilizado.
     */
    public V getValueAssessment() {
        return valueAssessment;
    }
    
    
    /**
     * Adiciona um atributo de comparação a assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser adicionado.
     * @return
     *          verdadeiro se o atributo foi adicionado.
     * @throws java.lang.Exception
     *          se o número máximo de atributos for ultrapassado.
     *
     * @see TreeSet#add
     */
    public boolean addAttributeAssessment(A attribute) throws Exception {
        if(attributeAssessments.size() == 2)
            throw new Exception("can't have more than two attributes");
        
        return attributeAssessments.add(attribute);
    }
    
    
    /**
     * Remove um atributo de comparação da assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser removido.
     * @return
     *          verdadeiro se o atributo for removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeAttributeAssessment(A attribute) {
        return attributeAssessments.remove(attribute);
    }
    
    
    /**
     * Verifica se a assertiva possui um atributo.
     * 
     * @param attribute
     *          elemento representando o atributo a ser verificado.
     * @return
     *          verdadeiro se o atributo estiver presente.
     */
    public boolean hasAttributeAssessment(A attribute) {
        return attributeAssessments.contains(attribute);
    }
    
    
    /**
     * Verifica se a assertiva possui pelo menos um atributo.
     * 
     * @return
     *          verdadeiro se a assertiva possuir pelo menos um atributo.
     */
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }


    /**
     * Retorna os atributos da assertiva.
     *
     * @return
     *          objeto Iterable contendo os atributos da assertiva.
     */
    public Iterable<A> getAttributeAssessments() {
        return attributeAssessments;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<assessmentStatement";
        if(getComparator() != null)
            content += " comparator='" + getComparator().toString() + "'";
        content += ">\n";

        if(hasAttributeAssessment()){
            for(A attribute : attributeAssessments)
                content += attribute.parse(ident + 1);
        }

        if(getValueAssessment() != null)
            content += getValueAssessment().parse(ident + 1);
        
        content += space + "</assessmentStatement>\n";

        return content;
    }
    
    
    public int compareTo(S other) {
        int comp = 0;

        String this_stat, other_stat;
        NCLAssessmentStatement other_asses;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLAssessmentStatement))
            return -1;

        other_asses = (NCLAssessmentStatement) other;
        
        // Compara pelo comparador
        if(comp == 0){
            if(getComparator() == null) this_stat = ""; else this_stat = getComparator().toString();
            if(other_asses.getComparator() == null) other_stat = ""; else other_stat = other_asses.getComparator().toString();
            comp = this_stat.compareTo(other_stat);
        }

        // Compara o número de attributeAssessment
        if(comp == 0)
            comp = attributeAssessments.size() - ((Set) other_asses.getAttributeAssessments()).size();

        // Compara os attributeAssessment
        if(comp == 0){
            Iterator it = other_asses.getAttributeAssessments().iterator();
            for(NCLAttributeAssessment att : attributeAssessments){
                NCLAttributeAssessment other_att = (NCLAttributeAssessment) it.next();
                comp = att.compareTo(other_att);
                if(comp != 0)
                    break;
            }
        }

        // Compara os valueAssessment
        if(comp == 0){
            if(getValueAssessment() == null && other_asses.getValueAssessment() == null)
                comp = 0;
            else if(getValueAssessment() != null && other_asses.getValueAssessment() != null)
                comp = getValueAssessment().compareTo(other_asses.getValueAssessment());
            else
                comp = 1;
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getComparator() != null);
        valid &= (attributeAssessments.size() == 2 || (attributeAssessments.size() == 1 && getValueAssessment() != null));

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("assessmentStatement")){
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("comparator")){
                        for(NCLComparator c : NCLComparator.values()){
                            if(c.toString().equals(attributes.getValue(i)))
                                setComparator(c);
                        }
                    }
                }
            }
            else if(localName.equals("attributeAssessment")){
                NCLAttributeAssessment a = new NCLAttributeAssessment(getReader(), this);
                a.startElement(uri, localName, qName, attributes);
                addAttributeAssessment((A) a); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("valueAssessment")){
                NCLValueAssessment v = new NCLValueAssessment(getReader(), this);
                v.startElement(uri, localName, qName, attributes);
                setValueAssessment((V) v); //TODO: retirar o cast. Como melhorar isso?
            }
        }
        catch(Exception ex){
            //TODO: fazer o que?
        }
    }
}
