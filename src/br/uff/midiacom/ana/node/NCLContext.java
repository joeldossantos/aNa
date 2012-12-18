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
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLCompositeNodeElement;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.util.exception.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents the context node element.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the context element. This attribute is required.</li>
 *  <li><i>refer</i> - reference to a context or body elements. This attribute
 *                     is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>port</i> - element representing a context interface point. The context
 *                    can have none or several port elements.</li>
 *  <li><i>property</i> - element representing a property. The context can have
 *                        none or several property elements.</li>
 *  <li><i>media</i> - element representing a media object. The context can have
 *                     none or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The context can have
 *                       none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      context can have none or several switch elements.</li>
 *  <li><i>link</i> - element representing a link among medias or compositions.
 *                    The context can have none or several link elements.</li>
 *  <li><i>meta</i> - elements defining meta data. The context can have none or
 *                    several meta elements.</li>
 *  <li><i>metadata</i> - elements defining a RDF tree. The context can have none
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
 * @param <Rn> 
 */
public class NCLContext<T extends NCLElement,
                        Ept extends NCLPort,
                        Epp extends NCLProperty,
                        En extends NCLNode,
                        Ei extends NCLInterface,
                        El extends NCLLink,
                        Em extends NCLMeta,
                        Emt extends NCLMetadata,
                        R extends ExternalReferenceType>
        extends NCLCompositeNodeElement<T, Ept, Epp, En, El, Em, Emt>
        implements NCLNode<T, En, Ei>, PostReferenceElement {

    protected Object refer;
    private String refer_id;
    
    
    /**
     * Context element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLContext() {
        super();
    }
    
    
    public NCLContext(String id) throws XMLException {
        super();
        setId(id);
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
    }


    /**
     * Sets the reference to a context or body elements. This attribute is
     * optional. Set the reference to <i>null</i> to erase a reference already
     * defined.
     * 
     * <br/>
     * 
     * The referred element must be a context element that represents the same
     * context defined in the body of the NCL document where this context is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child nodes.
     * It can not refer to another node.
     * 
     * <br/>
     * 
     * When a context element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attribute and
     * child nodes defined by the context that makes the reference are ignored,
     * except the id attribute.
     * 
     * <br/>
     * 
     * The referred element and the one that makes the reference must be
     * considered the same, in relation to its data structure. That means that
     * a node can be represented by more than one NCL element. Since nodes inside
     * a composite node defines a set of nodes, a node can not be represented by
     * more than one node inside a composition. That means that the id attribute
     * of an NCL element representing a node is not only a unique identifier for
     * the element, but also the only identifier corresponding to a node inside
     * a composition.
     * 
     * @param refer
     *          element representing a context or body, a reference to a context
     *          or body elements or <i>null</i> to erase a reference already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the context
     *          or body elements.
     */
    public void setRefer(Object refer) throws XMLException {
        Object aux = this.refer;
        
        if(refer instanceof NCLCompositeNodeElement)
            ((En) refer).addReference(this);
        else if(refer instanceof ExternalReferenceType){
            ((R) refer).getTarget().addReference(this);
            ((R) refer).getAlias().addReference(this);
        }
        
        this.refer = refer;
        notifyAltered(NCLElementAttributes.REFER, aux, refer);
        
        if(aux != null){
            if(aux instanceof NCLCompositeNodeElement)
                ((En) aux).removeReference(this);
            else{
                ((R) aux).getTarget().removeReference(this);
                ((R) aux).getAlias().removeReference(this);
            }
        }
    }


    /**
     * Return the reference to a context or body elements or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The referred element must be a context element that represents the same
     * context defined in the body of the NCL document where this context is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child nodes.
     * It can not refer to another node.
     * 
     * <br/>
     * 
     * When a context element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attribute and
     * child nodes defined by the context that makes the reference are ignored,
     * except the id attribute.
     * 
     * <br/>
     * 
     * The referred element and the one that makes the reference must be
     * considered the same, in relation to its data structure. That means that
     * a node can be represented by more than one NCL element. Since nodes inside
     * a composite node defines a set of nodes, a node can not be represented by
     * more than one node inside a composition. That means that the id attribute
     * of an NCL element representing a node is not only a unique identifier for
     * the element, but also the only identifier corresponding to a node inside
     * a composition.
     * 
     * @return 
     *          element representing a context or body, a reference to a context
     *          or body elements or <i>null</i> if the attribute is not defined.
     */
    public Object getRefer() {
        return refer;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLContext))
            return false;
        
        boolean result = true;
        
        result &= super.compareContent((NCLCompositeNodeElement) other);
        
        Object aux = getRefer();
        Object oaux = ((NCLContext) other).getRefer();
        if(aux != null && oaux != null){
            if(aux instanceof NCLContext && oaux instanceof NCLContext)
                result &= ((En) aux).compare((En) oaux);
            else if(aux instanceof ExternalReferenceType && oaux instanceof ExternalReferenceType)
                result &= ((R) aux).equals((R) oaux);
            else
                result = false;
        }
        
        return result;
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


    @Override
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
            
            throw new NCLParsingException("Context" + aux + ":\n" + ex.getMessage());
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
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Context" + aux + " > " + ex.getMessage());
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
            
            throw new NCLParsingException("Context" + aux + ":\n" + ex.getMessage());
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
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseRefer() {
        Object aux = getRefer();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLCompositeNodeElement)
            return " refer='" + ((En) aux).getId() + "'";
        else
            return " refer='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadRefer(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the refer (optional)
        att_name = NCLElementAttributes.REFER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
//            T ref = (T) new NCLContext(att_var);
//            setRefer(createContextRef((En) ref));
            refer_id = att_var;
            ((NCLDoc) getDoc()).waitReference(this);
        }
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
    
    
    @Override
    public Ei findInterface(String id) throws XMLException {
        Ei result;
        
        // if reuses another context search in it
        Object aux;
        if((aux = getRefer()) != null){
            if(aux instanceof NCLCompositeNodeElement)
                return (Ei) ((En) aux).findInterface(id);
            else
                return (Ei) ((En) ((R) aux).getTarget()).findInterface(id);
        }
        
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
    
    
    @Override
    public En findNode(String id) throws XMLException {
        En result;
        
        if(getId().equals(id))
            return (En) this;
        
        // if reuses another context search in it
        Object aux;
        if((aux = getRefer()) != null){
            if(aux instanceof NCLCompositeNodeElement)
                return (En) ((En) aux).findNode(id);
            else
                return (En) ((En) ((R) aux).getTarget()).findNode(id);
        }
        
        for(En node : nodes){
            result = (En) node.findNode(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    @Override
    @Deprecated
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // set the refer (optional)
            if( getRefer() != null && (aux = ((NCLCompositeNodeElement) getRefer()).getId()) != null){
                En ref = (En) ((NCLBody) ((NCLDoc) getDoc()).getBody()).findNode(aux);
                setRefer(ref);
            }
            if(refer_id != null){
                En ref = (En) ((NCLBody) ((NCLDoc) getDoc()).getBody()).findNode(refer_id);
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

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(refer != null){
            if(refer instanceof NCLContext)
                ((NCLContext)refer).removeReference(this);
            else if(refer instanceof NCLBody)
                ((NCLBody)refer).removeReference(this);
            else{
                ((R) refer).getTarget().removeReference(this);
                ((R) refer).getAlias().removeReference(this);
            }
        }
        
        refer = null;
        refer_id = null;
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
