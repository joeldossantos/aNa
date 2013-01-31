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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLCompositeNodeElement;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents the NCL document body element. The body is a special
 * type of context node, representing the most external document context.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the document body element. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>port</i> - element representing a body interface point. The body can
 *                    have none or several port elements.</li>
 *  <li><i>property</i> - element representing a property. The body can have none
 *                        or several property elements.</li>
 *  <li><i>media</i> - element representing a media object. The body can have none
 *                     or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The body can have
 *                       none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      body can have none or several switch elements.</li>
 *  <li><i>link</i> - element representing a link among medias or compositions.
 *                    The body can have none or several link elements.</li>
 *  <li><i>meta</i> - elements defining meta data. The body can have none or several
 *                    meta elements.</li>
 *  <li><i>metadata</i> - elements defining a RDF tree. The body can have none
 *                        or several metadata elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ept>
 * @param <Epp>
 * @param <En>
 * @param <El>
 * @param <Em>
 * @param <Emt> 
 */
public class NCLBody<T extends NCLElement,
                     Ept extends NCLPort,
                     Epp extends NCLProperty,
                     En extends NCLNode,
                     Ei extends NCLInterface,
                     El extends NCLLink,
                     Em extends NCLMeta,
                     Emt extends NCLMetadata>
        extends NCLCompositeNodeElement<T, Ept, Epp, En, El, Em, Emt>
        implements NCLNode<T, En, Ei> {

    
    /**
     * Body element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLBody() {
        super();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        if(doc == null)
            doc = getParent(); // doc is the parent of body
        
        super.setDoc(doc);
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLBody))
            return false;
        
        return super.compareContent((NCLCompositeNodeElement) other);
    }
    
    
    @Override
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";        
        
        // <body> element and attributes declaration
        content = space + "<body";
        content += parseAttributes();
        content += ">\n";
        
        // <body> element content
        content += parseElements(ident + 1);
        
        // <body> element end declaration
        content += space + "</body>\n";
        
        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        try{
            // set the id (optional)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
        }
        catch(XMLException ex){
            throw new NCLParsingException("Body:\n" + ex.getMessage());
        }

        try{
            // create the child nodes (except ports and links)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadProperties(el);
                    loadMetas(el);
                    loadMetadatas(el);
                    loadMedia(el);
                    loadContext(el);
                    loadSwitch(el);
                }
            }

            // create the child nodes (ports and links)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadPorts(el);
                    loadLinks(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("Body > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
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
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
    }
    
    
    protected String parseMetas(int ident) {
        if(!hasMeta())
            return "";
        
        String content = "";
        for(Em aux : metas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMetas(Element element) throws XMLException {
        // create the meta
        if(element.getTagName().equals(NCLElementAttributes.META.toString())){
            Em inst = createMeta();
            addMeta(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseMetadatas(int ident) {
        if(!hasMetadata())
            return "";
        
        String content = "";
        for(Emt aux : metadatas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMetadatas(Element element) throws XMLException {
        // create the metadata
        if(element.getTagName().equals(NCLElementAttributes.METADATA.toString())){
            Emt inst = createMetadata();
            addMetadata(inst);
            inst.load(element);
        }
    }
    
    
    protected String parsePorts(int ident) {
        if(!hasPort())
            return "";
        
        String content = "";
        for(Ept aux : ports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadPorts(Element element) throws XMLException {
        //create the port
        if(element.getTagName().equals(NCLElementAttributes.PORT.toString())){
            Ept inst = createPort();
            addPort(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseProperties(int ident) {
        if(!hasProperty())
            return "";
        
        String content = "";
        for(Epp aux : properties)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadProperties(Element element) throws XMLException {
        // create the property
        if(element.getTagName().equals(NCLElementAttributes.PROPERTY.toString())){
            Epp inst = createProperty();
            addProperty(inst);
            inst.load(element);
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
    
    
    protected String parseLinks(int ident) {
        if(!hasLink())
            return "";
        
        String content = "";
        for(El aux : links)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadLinks(Element element) throws XMLException {
        // create the link
        if(element.getTagName().equals(NCLElementAttributes.LINK.toString())){
            El inst = createLink();
            addLink(inst);
            inst.load(element);
        }
    }

    
    /**
     * Searches for a interface inside the body and its descendants. The interface
     * could be: area, property, port, switchPort.
     * 
     * @param id
     *          id of the interface to be found.
     * @return 
     *          interface or null if no interface was found.
     */
    @Override
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
    
    
    /**
     * Searches for an node inside the body and its descendants. The node will be
     * searched inside contexts and switches.
     * 
     * @param id
     *          id of the node to be found.
     * @return 
     *          node or null if no node was found.
     */
    @Override
    public En findNode(String id) throws XMLException {
        En result;
        
        if(getId() != null && getId().equals(id))
            return (En) this;
        
        for(En node : nodes){
            result = (En) node.findNode(id);
            if(result != null)
                return result;
        }

        NCLDoc doc = (NCLDoc) getParent();
        if(doc == null)
            throw new NCLParsingException("Could not find document root element");
        
        NCLHead head = (NCLHead) doc.getHead();
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        
        NCLImportedDocumentBase ib = (NCLImportedDocumentBase) head.getImportedDocumentBase();
        if(ib == null)
            return null;
        
        for(NCLImport imp : (ElementList<NCLImport>) ib.getImportNCLs()){
            NCLDoc d = (NCLDoc) imp.getImportedDoc();
            result = (En) findNodeReference(d, id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public NCLNode findNodeReference(NCLDoc doc, String id) throws XMLException {
        NCLBody body = (NCLBody) doc.getBody();
        
        if(body == null)
            throw new NCLParsingException("Could not find document body element");

        NCLNode result = body.findNode(id);

        if(result == null)
            throw new NCLParsingException("Could not find node in ruleBase with id: " + id);
        
        return result;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        for(Ept p : ports)
            p.clean();
        
        for(Epp p : properties)
            p.clean();
        
        for(En n : nodes)
            n.clean();
        
        for(El l : links)
            l.clean();
        
        for(Em m : metas)
            m.clean();
        
        for(Emt m : metadatas)
            m.clean();
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
