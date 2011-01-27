package br.pensario.interfaces;

import br.pensario.NCLElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.node.NCLContext;
import br.pensario.node.NCLMedia;
import br.pensario.node.NCLNode;
import br.pensario.node.NCLSwitch;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define um mapeamento da porta do switch da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLMapping<M extends NCLMapping, N extends NCLNode, I extends NCLInterface> extends NCLElement implements Comparable<M> {

    private N component;
    private I interfac;


    /**
     * Construtor do elemento <i>mapping</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLMapping() {}


    /**
     * Construtor do elemento <i>mapping</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLMapping(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Determina o componente mapeado pelo mapeamento.
     *
     * @param component
     *          elemento representando o componente mapeado.
     */
    public void setComponent(N component) {
        this.component = component;
    }


    /**
     * Retorna o componente mapeado pelo mapeamento.
     *
     * @return
     *          elemento representando o componente mapeado.
     */
    public N getComponent() {
        return component;
    }


    /**
     * Determina a interface mapeada pelo mapeamento.
     *
     * @param interfac
     *          elemento representando a interface mapeada.
     */
    public void setInterface(I interfac) {
        this.interfac = interfac;
    }


    /**
     * Retorna a interface mapeada pelo mapeamento.
     *
     * @return
     *          elemento representando a interface mapeada.
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


        // param element and attributes declaration
        content = space + "<mapping";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        content += "/>\n";

        return content;
    }

    public int compareTo(M other) {
        int comp = 0;

        // Compara pelo componente
        comp = getComponent().compareTo(other.getComponent());

        // Compara pela interface
        if(comp == 0){
            comp = getInterface().compareTo(other.getInterface());
        }

        return comp;
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getComponent() == null){
            addError("Elemento não possui atributo obrigatório component.");
        }

        if(getComponent() != null && getParent().getParent() != null){
            if(getComponent().compareTo(getParent().getParent()) == 0){
                addError("Atributo component deve referênciar elemento interno ao switch.");
                valid = false;
            }

            if(!((NCLSwitch) getParent().getParent()).hasNode(getComponent())){
                addError("Atributo component deve referênciar elemento interno ao switch.");
                valid = false;
            }
        }

        if(getInterface() != null && getComponent() != null && getParent().getParent() != null){
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
                if(attributes.getLocalName(i).equals("component"))//TODO: precisa retirar cast?
                    setComponent((N) new NCLContext(attributes.getValue(i)));
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
        if(getParent().getParent() == null){
            addWarning("Could not find a parent switch");
            return;
        }

        Iterable<N> nodes = ((NCLSwitch) getParent().getParent()).getNodes();

        for(N node : nodes){
            if(node.getId().equals(getComponent().getId())){
                setComponent(node);
                return;
            }
        }

        addWarning("Could not find node in switch with id: " + getComponent().getId());
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
