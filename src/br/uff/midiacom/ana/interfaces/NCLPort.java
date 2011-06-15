/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>port</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma porta de um contexto.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
        Set<N> nodes;
        
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
        Set<I> ifaces;

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
