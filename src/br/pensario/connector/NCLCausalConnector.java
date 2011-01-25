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
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
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
        this.condition = condition;
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
        this.action = action;
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
     *          nome do parâmetro a ser adicionado ao conector.
     * @return
     *          verdadeiro se o parâmetro for adicionado.
     *
     * @see TreeSet#add
     */    
    public boolean addConnectorParam(P param) throws NCLInvalidIdentifierException {
        return conn_params.add(param);
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
                return conn_params.remove(connp);
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
        boolean valid = true;

        valid &= (getId() != null);
        valid &= (getCondition() != null);
        valid &= (getAction() != null);

        if(getCondition() != null)
            valid &= getCondition().validate();
        if(getAction() != null)
            valid &= getAction().validate();

        if(hasConnectorParam()){
            for(P connp : conn_params)
                valid &= connp.validate();
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
                NCLConnectorParam p = new NCLConnectorParam(getReader(), this);
                p.startElement(uri, localName, qName, attributes);
                addConnectorParam((P) p); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("simpleCondition")){
                NCLSimpleCondition c = new NCLSimpleCondition(getReader(), this);
                setCondition((Co) c); //TODO: retirar o cast. Como melhorar isso?
                c.startElement(uri, localName, qName, attributes);
                addWarning(c.getWarnings());
                addError(c.getErrors());
            }
            else if(localName.equals("compoundCondition")){
                NCLCompoundCondition c = new NCLCompoundCondition(getReader(), this);
                setCondition((Co) c); //TODO: retirar o cast. Como melhorar isso?
                c.startElement(uri, localName, qName, attributes);
                addWarning(c.getWarnings());
                addError(c.getErrors());
            }
            else if(localName.equals("simpleAction")){
                NCLSimpleAction a = new NCLSimpleAction(getReader(), this);
                setAction((Ac) a); //TODO: retirar o cast. Como melhorar isso?
                a.startElement(uri, localName, qName, attributes);
                addWarning(a.getWarnings());
                addError(a.getErrors());
            }
            else if(localName.equals("compoundAction")){
                NCLCompoundAction a = new NCLCompoundAction(getReader(), this);
                setAction((Ac) a); //TODO: retirar o cast. Como melhorar isso?
                a.startElement(uri, localName, qName, attributes);
                addWarning(a.getWarnings());
                addError(a.getErrors());
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
}
