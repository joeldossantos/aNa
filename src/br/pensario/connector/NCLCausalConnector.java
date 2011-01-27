package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conector de um documento NCL.<br>
 *
 *@see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public class NCLCausalConnector<C extends NCLCausalConnector, Co extends NCLCondition, Ac extends NCLAction, P extends NCLConnectorParam>
        extends NCLIdentifiableElement implements Comparable<C> {

    private Co condition;
    private Ac action;
    private Set<P> conn_params = new TreeSet<P>();


    /**
     * Construtor do elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do conector.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          caso o identificador seja inválido.
     */    
    public NCLCausalConnector(String id) throws NCLInvalidIdentifierException {
        this.setId(id);
    }


    /**
     * Construtor do elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCausalConnector(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
    
    
    /**
     * Atribui uma condição ao conector causal.
     * 
     * @param condition
     *          elemento representando uma condição do conector.
     */    
    public void setCondition(Co condition) {
        //Retira o parentesco do condition atual
        if(this.condition != null)
            this.condition.setParent(null);

        this.condition = condition;
        //Se condition existe, atribui este como seu parente
        if(this.condition != null)
            this.condition.setParent(this);
    }
    
    
    /**
     * Retorna a condição atribuida ao conector causal.
     * 
     * @return
     *          elemento representando uma condição do conector.
     */    
    public Co getCondition() {
        return condition;
    }


    /**
     * Atribui uma ação ao conector causal.
     * 
     * @param action
     *          elemento representando uma ação do conector.
     */    
    public void setAction(Ac action) {
        //Retira o parentesco do action atual
        if(this.action != null)
            this.action.setParent(null);

        this.action = action;
        //Se action existe, atribui este como seu parente
        if(this.action != null)
            this.action.setParent(this);
    }
    
    
    /**
     * Retorna a ação atribuida ao conector causal.
     *
     * @return
     *          elemento representando uma ação do conector.
     */
    public Ac getAction() {
        return action;
    }

    
    /**
     * Adiciona um parâmetro ao conector causal NCL.     
     * 
     * @param param
     *          parâmetro a ser adicionado ao conector.
     * @return
     *          verdadeiro se o parâmetro for adicionado.
     *
     * @see TreeSet#add
     */    
    public boolean addConnectorParam(P param) throws NCLInvalidIdentifierException {
        if(conn_params.add(param)){
            //Se param existe, atribui este como seu parente
            if(param != null)
                param.setParent(this);

            return true;
        }
        return false;
    }

    
    /**
     * Remove um parâmetro do conector causal.
     * 
     * @param name
     *          nome do parâmetro a ser removido do conector.
     * @return
     *          verdadeiro se o parâmetro for removido.
     */    
    public boolean removeConnectorParam(String name) {
        for(P connp : conn_params){
            if(connp.getName().equals(name))
                return removeConnectorParam(connp);
        }

        return false;
    }


    /**
     * Remove um parâmetro do conector causal.
     *
     * @param param
     *          parâmetro a ser removido do conector.
     * @return
     *          verdadeiro se o parâmetro for removido.
     */
    public boolean removeConnectorParam(P param) {
        if(conn_params.remove(param)){
            //Se param existe, retira o seu parentesco
            if(param != null)
                param.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o conector possui um parâmetro.
     *
     * @param name
     *          nome do parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasConnectorParam(String name) {
        for(P connp : conn_params){
            if(connp.getName().equals(name))
                return true;
        }

        return false;
    }


    /**
     * Verifica se o conector possui pelo menos um parâmetro.
     *
     * @return
     *          verdadeiro se o conector possuir pelo menos um parâmetro.
     */
    public boolean hasConnectorParam() {
        return !conn_params.isEmpty();
    }


    /**
     * Retorna os parâmetros do conector.
     *
     * @return
     *          objeto Iterable contendo os parâmetros do conector.
     */
    public Iterable<P> getConnectorParams() {
        return conn_params;
    }

    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<causalConnector";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasConnectorParam()){
            for(P connp : conn_params)
                content += connp.parse(ident + 1);
        }
        if(getCondition() != null)
            content += getCondition().parse(ident + 1);
        if(getAction() != null)
            content += getAction().parse(ident + 1);

        content += space + "</causalConnector>\n";

        return content;
    }

    
    public int compareTo(C other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }
        if(getCondition() == null){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir um elemento simpleCondition ou compoundCondition.");
            valid = false;
        }
        if(getAction() == null){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir um elemento simpleAction ou compoundAction.");
            valid = false;
        }

        if(getCondition() != null){
            valid &= getCondition().validate();
            addWarning(getCondition().getErrors());
            addError(getCondition().getErrors());
        }
        if(getAction() != null){
            valid &= getAction().validate();
            addWarning(getAction().getErrors());
            addError(getAction().getErrors());
        }

        if(hasConnectorParam()){
            for(P connp : conn_params){
                valid &= connp.validate();
                addWarning(connp.getErrors());
                addError(connp.getErrors());
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("causalConnector")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("connectorParam")){
                P child = createConnectorParam();
                child.startElement(uri, localName, qName, attributes);
                addConnectorParam(child);
            }
            else if(localName.equals("simpleCondition")){
                setCondition(createSimpleCondition());
                getCondition().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("compoundCondition")){
                setCondition(createCompoundCondition());
                getCondition().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("simpleAction")){
                setAction(createSimpleAction());
                getAction().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("compoundAction")){
                setAction(createCompoundAction());
                getAction().startElement(uri, localName, qName, attributes);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasConnectorParam()){
            for(P param : conn_params){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
        if(getCondition() != null){
            getCondition().endDocument();
            addWarning(getCondition().getWarnings());
            addError(getCondition().getErrors());
        }
        if(getAction() != null){
            getAction().endDocument();
            addWarning(getAction().getWarnings());
            addError(getAction().getErrors());
        }
    }


    /**
     * Função de criação do elemento filho <i>connectorParam</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>connectorParam</i>.
     */
    protected P createConnectorParam() {
        return (P) new NCLConnectorParam(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>simpleCondition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleCondition</i>.
     */
    protected Co createSimpleCondition() {
        return (Co) new NCLSimpleCondition(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundCondition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundCondition</i>.
     */
    protected Co createCompoundCondition() {
        return (Co) new NCLCompoundCondition(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>simpleAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleAction</i>.
     */
    protected Ac createSimpleAction() {
        return (Ac) new NCLSimpleAction(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundAction</i>.
     */
    protected Ac createCompoundAction() {
        return (Ac) new NCLCompoundAction(getReader(), this);
    }
}
