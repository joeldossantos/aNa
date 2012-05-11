/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.reference.NodeReference;
import br.uff.midiacom.ana.datatype.aux.reference.PostReferenceElement;
import br.uff.midiacom.ana.datatype.aux.reference.SwitchReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.node.NCLSwitchPrototype;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLSwitch<T extends NCLSwitch,
                       P extends NCLElement,
                       I extends NCLElementImpl,
                       En extends NCLNode,
                       Ei extends NCLInterface,
                       Ep extends NCLSwitchPort,
                       Eb extends NCLSwitchBindRule,
                       Rn extends SwitchReference,
                       Ri extends NodeReference>
        extends NCLSwitchPrototype<T, P, I, En, Ep, Eb, Rn, Ri>
        implements NCLNode<En, P, Ei>, PostReferenceElement {


    public NCLSwitch() throws XMLException {
        super();
    }


    public NCLSwitch(String id) throws XMLException {
        super();
        setId(id);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }

    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<switch";
        content += parseAttributes();

        if(hasPort() || hasBind() || hasNode()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</switch>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadId(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes (except ports and binds)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadMedia(el);
                    loadContext(el);
                    loadSwitch(el);
                }
            }

            // create the child nodes (ports, binds and defaultComponent)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadPorts(el);
                    loadBinds(el);
                    loadDefaultComponent(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + " > " + ex.getMessage());
        }

        try{
            loadRefer(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseRefer();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parsePorts(ident);
        content += parseBinds(ident);
        content += parseDefaultComponent(ident);
        content += parseNodes(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseRefer() {
        Rn aux = getRefer();
        if(aux != null)
            return " refer='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRefer(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the refer (optional)
        att_name = NCLElementAttributes.REFER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            En ref = (En) new NCLSwitch(att_var);
            setRefer(createSwitchRef(ref));
            NCLReferenceManager.getInstance().waitReference(this);
        }
    }
    
    
    protected String parsePorts(int ident) {
        if(!hasPort())
            return "";
        
        String content = "";
        for(Ep aux : ports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadPorts(Element element) throws XMLException {
        //create the switchPort
        if(element.getTagName().equals(NCLElementAttributes.SWITCHPORT.toString())){
            Ep inst = createSwitchPort();
            addPort(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseBinds(int ident) {
        if(!hasBind())
            return "";
        
        String content = "";
        for(Eb aux : binds)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadBinds(Element element) throws XMLException {
        // create the bindRule
        if(element.getTagName().equals(NCLElementAttributes.BINDRULE.toString())){
            Eb inst = createBindRule();
            addBind(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseDefaultComponent(int ident) {
        Ri aux = getDefaultComponent();
        if(aux == null)
            return "";
        
        String space = "";
        if(ident < 0)
            ident = 0;
        
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        return space + "<defaultComponent component='" + aux.parse() + "'/>\n";
    }
    
    
    protected void loadDefaultComponent(Element element) throws XMLException {
        String att_name, att_var;
        
        // create the defaultComponent
        if(element.getTagName().equals(NCLElementAttributes.DEFAULTCOMPONENT.toString())){
            att_name = NCLElementAttributes.COMPONENT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDefaultComponent(createNodeRef(nodes.get(att_var)));
        }
    }
    
    
    protected String parseNodes(int ident) {
        if(!hasNode())
            return "";
        
        String content = "";
        for(En aux : nodes)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMedia(Element element) throws XMLException {
        // create the media
        if(element.getTagName().equals(NCLElementAttributes.MEDIA.toString())){
            En inst = createMedia();
            addNode(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadContext(Element element) throws XMLException {
        // create the context
        if(element.getTagName().equals(NCLElementAttributes.CONTEXT.toString())){
            En inst = createContext();
            addNode(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadSwitch(Element element) throws XMLException {
        // create the switch
        if(element.getTagName().equals(NCLElementAttributes.SWITCH.toString())){
            En inst = createSwitch();
            addNode(inst);
            inst.load(element);
        }
    }
    
    
    public Ei findInterface(String id) throws XMLException {
        Ei result;
        
        // search as a switchPort
        result = (Ei) ports.get(id);
        if(result != null)
            return result;
        
        // search in inner nodes
        for(En node : nodes){
            result = (Ei) node.findInterface(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public En findNode(String id) throws XMLException {
        En result;
        
        if(getId().equals(id))
            return (En) this;
        
        for(En node : nodes){
            result = (En) node.findNode(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // set the refer (optional)
            if((aux = ((En) getRefer().getTarget()).getId()) != null){
                En ref = (En) ((NCLBody) impl.getDoc().getBody()).findNode(aux);
                setRefer(createSwitchRef(ref));
            }
        }
        catch(XMLException ex){
            aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
    }


    /**
     * Function to create the child element <i>bindRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindRule</i>.
     */
    protected Eb createBindRule() throws XMLException {
        return (Eb) new NCLSwitchBindRule();
    }


    /**
     * Function to create the child element <i>switchPort</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switchPort</i>.
     */
    protected Ep createSwitchPort() throws XMLException {
        return (Ep) new NCLSwitchPort();
    }


    /**
     * Function to create the child element <i>media</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>media</i>.
     */
    protected En createMedia() throws XMLException {
        return (En) new NCLMedia();
    }


    /**
     * Function to create the child element <i>context</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>context</i>.
     */
    protected En createContext() throws XMLException {
        return (En) new NCLContext();
    }


    /**
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected En createSwitch() throws XMLException {
        return (En) new NCLSwitch();
    }


    /**
     * Function to create a reference to a switch.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a switch.
     */
    protected Rn createSwitchRef(En ref) throws XMLException {
        return (Rn) new SwitchReference(ref, NCLElementAttributes.ID);
    }


    /**
     * Function to create a reference to a node.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a node.
     */
    protected Ri createNodeRef(En ref) throws XMLException {
        return (Ri) new NodeReference(ref, NCLElementAttributes.ID);
    }
}
