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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.auxiliar.PostReferenceElement;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.node.NCLContextPrototype;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLContext<T extends NCLContext,
                        P extends NCLElement,
                        I extends NCLElementImpl,
                        Ept extends NCLPort,
                        Epp extends NCLProperty,
                        En extends NCLNode,
                        Ei extends NCLInterface,
                        El extends NCLLink,
                        Em extends NCLMeta,
                        Emt extends NCLMetadata>
        extends NCLContextPrototype<T, P, I, Ept, Epp, En, El, Em, Emt>
        implements NCLNode<En, P, Ei>, PostReferenceElement {
    
    
    public NCLContext(String id) throws XMLException {
        super(id);
    }


    public NCLContext() throws XMLException {
        super();
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
        
        
        // <context> element and attributes declaration
        content = space + "<context";
        content += parseAttributes();
        
        // Test if the media has content
        if(hasMeta() || hasMetadata() || hasPort() || hasProperty() || hasNode() || hasLink()){
            content += ">\n";

            content += parseElements(ident + 1);
            
            // <context> element end declaration
            content += space + "</context>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseRefer();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseMetas(ident);
        content += parseMetadatas(ident);
        content += parsePorts(ident);
        content += parseProperties(ident);
        content += parseNodes(ident);
        content += parseLinks(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseRefer() {
        T aux = getRefer();
        if(aux != null)
            return " refer='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseMetas(int ident) {
        if(!hasMeta())
            return "";
        
        String content = "";
        for(Em aux : metas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseMetadatas(int ident) {
        if(!hasMetadata())
            return "";
        
        String content = "";
        for(Emt aux : metadatas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parsePorts(int ident) {
        if(!hasPort())
            return "";
        
        String content = "";
        for(Ept aux : ports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseProperties(int ident) {
        if(!hasProperty())
            return "";
        
        String content = "";
        for(Epp aux : properties)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseNodes(int ident) {
        if(!hasNode())
            return "";
        
        String content = "";
        for(En aux : nodes)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseLinks(int ident) {
        if(!hasLink())
            return "";
        
        String content = "";
        for(El aux : links)
            content += aux.parse(ident);
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Context" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes (except ports and links)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    // create the property
                    if(el.getTagName().equals(NCLElementAttributes.PROPERTY.toString())){
                        Epp inst = createProperty();
                        addProperty(inst);
                        inst.load(el);
                    }
                    // create the meta
                    if(el.getTagName().equals(NCLElementAttributes.META.toString())){
                        Em inst = createMeta();
                        addMeta(inst);
                        inst.load(el);
                    }
                    // create the metadata
                    if(el.getTagName().equals(NCLElementAttributes.METADATA.toString())){
                        Emt inst = createMetadata();
                        addMetadata(inst);
                        inst.load(el);
                    }
                    // create the media
                    if(el.getTagName().equals(NCLElementAttributes.MEDIA.toString())){
                        En inst = createMedia();
                        addNode(inst);
                        inst.load(el);
                    }
                    // create the context
                    if(el.getTagName().equals(NCLElementAttributes.CONTEXT.toString())){
                        En inst = createContext();
                        addNode(inst);
                        inst.load(el);
                    }
                    // create the switch
                    if(el.getTagName().equals(NCLElementAttributes.SWITCH.toString())){
                        En inst = createSwitch();
                        addNode(inst);
                        inst.load(el);
                    }
                }
            }

            // create the child nodes (ports and links)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    //create the port
                    if(el.getTagName().equals(NCLElementAttributes.PORT.toString())){
                        Ept inst = createPort();
                        addPort(inst);
                        inst.load(el);
                    }
                    // create the link
                    if(el.getTagName().equals(NCLElementAttributes.LINK.toString())){
                        El inst = createLink();
                        addLink(inst);
                        inst.load(el);
                    }
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Context" + aux + " > " + ex.getMessage());
        }

        try{
            // set the refer (optional)
            att_name = NCLElementAttributes.REFER.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                T ref = (T) new NCLContext(att_var);
                setRefer(ref);
                NCLReferenceManager.getInstance().waitReference(this);
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Context" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    public Ei findInterface(String id) throws XMLException {
        Ei result;
        
        // search as a property
        result = (Ei) properties.get(id);
        if(result != null)
            return result;
        
        // search as a port
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
            if((aux = getRefer().getId()) != null){
                T ref = (T) NCLReferenceManager.getInstance().findNodeReference(impl.getDoc(), aux);
                setRefer(ref);
            }
        }
        catch(XMLException ex){
            aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Context" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
    }


    /**
     * Function to create the child element <i>meta</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>meta</i>.
     */
    protected Em createMeta() throws XMLException {
        return (Em) new NCLMeta();
    }


    /**
     * Function to create the child element <i>metadata</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>metadata</i>.
     */
    protected Emt createMetadata() throws XMLException {
        return (Emt) new NCLMetadata();
    }


    /**
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected Ept createPort() throws XMLException {
        return (Ept) new NCLPort();
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Epp createProperty() throws XMLException {
        return (Epp) new NCLProperty();
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
     * Function to create the child element <i>link</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>link</i>.
     */
    protected El createLink() throws XMLException {
        return (El) new NCLLink();
    }
}
