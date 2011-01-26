package br.pensario.interfaces;

import br.pensario.NCLBody;
import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.node.NCLContext;
import br.pensario.node.NCLMedia;
import br.pensario.node.NCLNode;
import br.pensario.node.NCLSwitch;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>port</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma porta de um contexto.<br>
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
public class NCLPort<N extends NCLNode, I extends NCLInterface> extends NCLIdentifiableElement implements NCLInterface<I> {

    private N component;
    private I interfac;


    /**
     * Construtor do elemento <i>port</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da porta.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador for inválido.
     */
    public NCLPort(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>port</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLPort(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um nó a porta.
     * 
     * @param component
     *          elemento representando o nó.
     */
    public void setComponent(N component) {
        this.component = component;
    }
    
    
    /**
     * Retorna o nó atribuido a porta.
     * 
     * @return
     *          elemento representando o nó.
     */
    public N getComponent() {
        return component;
    }
    
    
    /**
     * Determina a interface de nó atributa a porta.
     * 
     * @param interfac
     *          elemento representando a interface do nó.
     */
    public void setInterface(I interfac) {
        this.interfac = interfac;
    }
    
    
    /**
     * Retorna a interface de nó atributa a porta.
     * 
     * @return
     *          elemento representando a interface do nó.
     */
    public I getInterface() {
        return interfac;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <port> element and attributes declaration
        content = space + "<port";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        content += "/>\n";
        
        return content;
    }
    
    
    public int compareTo(I other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        valid &= (getComponent() != null);

        //testa se o no contem a interface
        if(valid && getInterface() != null){
            if(getComponent() instanceof NCLMedia){
                if(getInterface() instanceof NCLArea)
                    valid &= ((NCLMedia) getComponent()).hasArea((NCLArea) getInterface());
                else if(getInterface() instanceof NCLProperty)
                    valid &= ((NCLMedia) getComponent()).hasProperty((NCLProperty) getInterface());
                else
                    valid = false;
            }
            else if(getComponent() instanceof NCLContext){
                if(getInterface() instanceof NCLPort)
                    valid &= ((NCLContext) getComponent()).hasPort((NCLPort) getInterface());
                else if(getInterface() instanceof NCLProperty)
                    valid &= ((NCLContext) getComponent()).hasProperty((NCLProperty) getInterface());
                else
                    valid = false;
            }
            else if(getComponent() instanceof NCLSwitch){
                if(getInterface() instanceof NCLSwitchPort)
                    valid &= ((NCLSwitch) getComponent()).hasPort((NCLSwitchPort) getInterface());
                else
                    valid = false;
            }
            else
                valid = false;
        }
        //TODO: testar a composicionalidade
        //TODO: testar se a interface é a mesma porta ou uma porta irma dessa porta

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("component"))
                    setComponent((N) new NCLContext(attributes.getValue(i)));//TODO: precisa retirar cast?
                else if(attributes.getLocalName(i).equals("interface"))
                    setInterface((I) new NCLPort(attributes.getValue(i)));//TODO: precisa retirar cast?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() == null)
            return;

        if(getComponent() != null)
            componentReference();

        if(getComponent() != null && getInterface() != null)
            interfaceReference();
    }


    private void componentReference() {
        //Search for a component node in its parent
        Iterable<N> nodes;
        
        if(getParent() instanceof NCLBody)
            nodes = ((NCLBody) getParent()).getNodes();
        else
            nodes = ((NCLContext) getParent()).getNodes();

        for(N node : nodes){
            if(node.getId().equals(getComponent().getId())){
                setComponent(node);
                return;
            }
        }

        addWarning("Could not find node with id: " + getComponent().getId());
    }


    private void interfaceReference() {
        //Search for the interface inside the node
        Iterable<I> ifaces;

        if(getComponent() instanceof NCLMedia){
            ifaces = ((NCLMedia) getComponent()).getAreas();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((NCLMedia) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof NCLContext){
            ifaces = ((NCLContext) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((NCLContext) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof NCLSwitch){
            ifaces = ((NCLSwitch) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }

        addWarning("Could not find interface with id: " + getInterface().getId());
    }
}
