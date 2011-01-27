package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLValues.NCLAttributeType;
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
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
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
        //Retira o parentesco do valueAssessment atual
        if(this.valueAssessment != null)
            this.valueAssessment.setParent(null);

        this.valueAssessment = value;
        //Set valueAssessment existe, atribui este como seu parente
        if(this.valueAssessment != null)
            this.valueAssessment.setParent(this);
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
        
        if(attributeAssessments.add(attribute)){
            //Se attribute existe, atribui este como seu parente
            if(attribute != null)
                attribute.setParent(this);

            return true;
        }
        return false;
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
        if(attributeAssessments.remove(attribute)){
            //Se attribute existe, retira o seu parentesco
            if(attribute != null)
                attribute.setParent(null);

            return true;
        }
        return false;
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
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getComparator() == null){
            addError("Elemento não possui atributo obrigatório comparator.");
            valid = false;
        }
           
        
        if( !(attributeAssessments.size() == 2 || (attributeAssessments.size() == 1 && getValueAssessment() != null)) ){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir dois attributeAssessment ou um attributeAssessment e um valueAssessment.");
            valid = false;
        }

        //@todo: comparar os tipos de attributeAssessment e valueAssessment ou dos dois attributeAssessment

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("assessmentStatement")){
                cleanWarnings();
                cleanErrors();
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
                A child = createAttributeAssessment();
                child.startElement(uri, localName, qName, attributes);
                addAttributeAssessment(child);
            }
            else if(localName.equals("valueAssessment")){
                V child = createValueAssessment();
                child.startElement(uri, localName, qName, attributes);
                setValueAssessment(child);
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasAttributeAssessment()){
            for(A attribute : attributeAssessments){
                attribute.endDocument();
                addWarning(attribute.getWarnings());
                addError(attribute.getErrors());
            }
        }
        if(getValueAssessment() != null){
            getValueAssessment().endDocument();
            addWarning(getValueAssessment().getWarnings());
            addError(getValueAssessment().getErrors());
        }
    }


    /**
     * Função de criação do elemento filho <i>attributeAssessment</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>attributeAssessment</i>.
     */
    protected A createAttributeAssessment() {
        return (A) new NCLAttributeAssessment(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>valueAssessment</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>valueAssessment</i>.
     */
    protected V createValueAssessment() {
        return (V) new NCLValueAssessment(getReader(), this);
    }
}
