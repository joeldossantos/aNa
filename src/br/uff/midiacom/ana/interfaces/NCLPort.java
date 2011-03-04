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
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
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
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }
        if(getComponent() == null){
            addError("Elemento não possui atributo obrigatório component.");
            valid = false;
        }

        if(getComponent() != null && getParent() != null){
            if(getParent() instanceof NCLNode && getComponent() instanceof NCLNode && getComponent().compareTo(getParent()) == 0){
                addError("Atributo component deve referênciar elemento interno ao contexto.");
                valid = false;
            }

            if(getParent() instanceof NCLContext && !((NCLContext) getParent()).hasNode(getComponent())){
                addError("Atributo component deve referênciar elemento interno ao contexto.");
                valid = false;
            }
            else if(getParent() instanceof NCLBody && !((NCLBody) getParent()).hasNode(getComponent())){
                addError("Atributo component deve referênciar elemento interno ao corpo do documento.");
                valid = false;
            }
        }

        if(getInterface() != null && getComponent() != null){
            if(getComponent() instanceof NCLMedia){
                if(getInterface() instanceof NCLArea && !((NCLMedia) getComponent()).hasArea((NCLArea) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(getInterface() instanceof NCLProperty && !((NCLMedia) getComponent()).hasProperty((NCLProperty) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(!(getInterface() instanceof NCLProperty) && !(getInterface() instanceof NCLArea)){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
            }
            else if(getComponent() instanceof NCLContext){
                if(getInterface() instanceof NCLPort && !((NCLContext) getComponent()).hasPort((NCLPort) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(getInterface() instanceof NCLProperty && !((NCLContext) getComponent()).hasProperty((NCLProperty) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(!(getInterface() instanceof NCLProperty) && !(getInterface() instanceof NCLPort)){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
            }
            else if(getComponent() instanceof NCLSwitch){
                if(getInterface() instanceof NCLSwitchPort && !((NCLSwitch) getComponent()).hasPort((NCLSwitchPort) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(!(getInterface() instanceof NCLProperty)){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
            }
            else
                valid = false;
        }

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
                    setComponent((N) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
                else if(attributes.getLocalName(i).equals("interface"))
                    setInterface((I) new NCLPort(attributes.getValue(i)));//cast retirado na correcao das referencias
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
