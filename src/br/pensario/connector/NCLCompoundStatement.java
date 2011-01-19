package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLValues.NCLOperator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma assertiva composta de um conector de um documento NCL.<br>
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
public class NCLCompoundStatement<S extends NCLStatement> extends NCLElement implements NCLStatement<S> {

    private NCLOperator operator;
    private Boolean isNegated;
    
    private Set<S> statements = new TreeSet<S>();

    private boolean insideStatement;


    /**
     * Construtor do elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundStatement() {}


    /**
     * Construtor do elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCompoundStatement(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideStatement = false;
    }
    
    
    /**
     * Determina o operador da assertiva composta.
     * 
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a assertiva composta.
     * 
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLOperator getOperator() {
        return operator;
    }
    
    
    /**
     * Determina se a assertiva composta está negada.
     * 
     * @param isNegated
     *          booleano que define se a assertiva está negada.
     */
    public void setIsNegated(Boolean isNegated) {
        this.isNegated = isNegated;
    }
    
    
    /**
     * Retorna se a assertiva composta está negada.
     * 
     * @return
     *          booleano que define se a assertiva está negada.
     */
    public Boolean getIsNegated() {
        return isNegated;
    }
    
    
    /**
     * Adiciona uma assertiva a assertiva composta.
     * 
     * @param statement
     *          elemento representando a assertiva a ser adicionada.
     * @return
     *          verdadeiro se a assertiva foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addStatement(S statement) {
        return statements.add(statement);
    }
    
    
    /**
     * Remove uma assertiva da assertiva composta.
     *
     * @param statement
     *          elemento representando a assertiva a ser removida.
     * @return
     *          verdadeiro se a assertiva foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeStatement(S statement) {
        return statements.remove(statement);
    }
    
    
    /**
     * Verifica se a assertiva composta possui uma assertiva.
     * 
     * @param statement
     *          elemento representando a assertiva a ser verificada.
     * @return
     *          verdadeiro se a assertiva existe.
     */
    public boolean hasStatement(S statement) {
        return statements.contains(statement);
    }
    
    
    /**
     * Verifica se a assertiva composta possui pelo menos uma assertiva.
     * 
     * @return
     *          verdadeiro se a assertiva composta possuir pelo menos uma assertiva.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }
    
    
    /**
     * Retorna as assertivas da assertiva composta.
     *
     * @return
     *          objeto Iterable contendo as assertivas da assertiva composta.
     */
    public Iterable<S> getStatements() {
        return statements;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundStatement";
        if(getOperator() != null)
            content += " operator='" + getOperator().toString() + "'";
        if(getIsNegated() != null)
            content += " isNegated='" + getIsNegated().toString() + "'";
        content += ">\n";

        if(hasStatement()){
            for(S statement : statements)
                content += statement.parse(ident + 1);
        }

        content += space + "</compoundStatement>\n";

        return content;
    }
    
    
    public int compareTo(S other) {
        int comp = 0;

        String this_stat, other_stat;
        NCLCompoundStatement other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundStatement))
            return 1;

        other_comp = (NCLCompoundStatement) other;
        
        // Compara pelo operador
        if(comp == 0){
            if(getOperator() == null) this_stat = ""; else this_stat = getOperator().toString();
            if(other_comp.getOperator() == null) other_stat = ""; else other_stat = other_comp.getOperator().toString();
            comp = this_stat.compareTo(other_stat);
        }

        // Compara pelo isNegated
        if(comp == 0){
            if(getIsNegated() == null) this_stat = ""; else this_stat = getIsNegated().toString();
            if(other_comp.getIsNegated() == null) other_stat = ""; else other_stat = other_comp.getIsNegated().toString();
            comp = this_stat.compareTo(other_stat);
        }

        // Compara o número de statements
        if(comp == 0)
            comp = statements.size() - ((Set) other_comp.getStatements()).size();

        // Compara as statements
        if(comp == 0){
            Iterator it = other_comp.getStatements().iterator();
            for(NCLStatement st : statements){
                NCLStatement other_st = (NCLStatement) it.next();
                comp = st.compareTo(other_st);
                if(comp != 0)
                    break;
            }
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getOperator() != null);
        valid &= hasStatement();

        if(hasStatement()){
            for(S statement : statements)
                valid &= statement.validate();
        }

        return valid;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(localName.equals("compoundStatement") && !insideStatement){
            insideStatement = true;
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("operator")){
                    for(NCLOperator o : NCLOperator.values()){
                        if(o.toString().equals(attributes.getValue(i)))
                            setOperator(o);
                    }
                }
                else if(attributes.getLocalName(i).equals("isNegated"))
                    setIsNegated(new Boolean(attributes.getValue(i)));
            }
        }
        else if(localName.equals("assessmentStatement")){
            NCLAssessmentStatement s = new NCLAssessmentStatement(getReader(), this);
            s.startElement(uri, localName, qName, attributes);
            addStatement((S) s); //TODO: retirar o cast. Como melhorar isso?
        }
        else if(localName.equals("compoundStatement") && insideStatement){
            NCLCompoundStatement s = new NCLCompoundStatement(getReader(), this);
            s.startElement(uri, localName, qName, attributes);
            addStatement((S) s); //TODO: retirar o cast. Como melhorar isso?
        }
    }

/*TODO: retirar isso
    @Override
    public void endElement(String uri, String localName, String qName) {
        getReader().setContentHandler(getParent());
        insideCondition = false;
    }*/
}
