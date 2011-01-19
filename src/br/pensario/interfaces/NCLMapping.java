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
        boolean valid = true;

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
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("component"))
                    setComponent((N) new NCLContext(attributes.getValue(i)));//FIXME: fazer a referência ao nó correto
                else if(attributes.getLocalName(i).equals("interface"))
                    setInterface((I) new NCLPort(attributes.getValue(i)));//FIXME: fazer a referência a porta correta
            }
        }
        catch(NCLInvalidIdentifierException ex){

        }
    }
}
