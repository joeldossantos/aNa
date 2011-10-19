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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.node.NCLContextPrototype;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.link.NCLParam;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLContext<T extends NCLContext, P extends NCLElement, I extends NCLElementImpl, Ept extends NCLPort, Epp extends NCLProperty, En extends NCLNode, El extends NCLLink, Em extends NCLMeta, Emt extends NCLMetadata>
        extends NCLContextPrototype<T, P, I, Ept, Epp, En, El, Em, Emt> implements NCLNode<En, P> {
    
    
    public NCLContext(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public void setRefer(T refer) {
        T aux = this.refer;
        super.setRefer(refer);
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    @Override
    public boolean addPort(Ept port) throws XMLException {
        if(super.addPort(port)){
            impl.notifyInserted(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    @Override
    public boolean removePort(String id) throws XMLException {
        if(super.removePort(id)){
            impl.notifyRemoved(NCLElementSets.PORTS, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean removePort(Ept port) throws XMLException {
        if(super.removePort(port)){
            impl.notifyRemoved(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    @Override
    public boolean addProperty(Epp property) throws XMLException {
        if(super.addProperty(property)){
            impl.notifyInserted(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeProperty(String name) throws XMLException {
        if(super.removeProperty(name)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, name);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeProperty(Epp property) throws XMLException {
        if(super.removeProperty(property)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    @Override
    public boolean addNode(En node) throws XMLException {
        if(super.addNode(node)){
            impl.notifyInserted(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeNode(String id) throws XMLException {
        if(super.removeNode(id)){
            impl.notifyRemoved(NCLElementSets.NODES, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeNode(En node) throws XMLException {
        if(super.removeNode(node)){
            impl.notifyRemoved(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    @Override
    public boolean addLink(El link) throws XMLException {
        if(super.addLink(link)){
            impl.notifyInserted(NCLElementSets.LINKS, link);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeLink(El link) throws XMLException {
        if(super.removeLink(link)){
            impl.notifyRemoved(NCLElementSets.LINKS, link);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeLink(String id) throws XMLException {
        if(super.removeLink(id)){
            impl.notifyRemoved(NCLElementSets.LINKS, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean addMeta(Em meta) throws XMLException {
        if(super.addMeta(meta)){
            impl.notifyInserted(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeMeta(Em meta) throws XMLException {
        if(super.removeMeta(meta)){
            impl.notifyRemoved(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    @Override
    public boolean addMetadata(Emt metadata) throws XMLException {
        if(super.addMetadata(metadata)){
            impl.notifyInserted(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeMetadata(Emt metadata) throws XMLException {
        if(super.removeMetadata(metadata)){
            impl.notifyRemoved(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("context") && !insideContext){
//                cleanWarnings();
//                cleanErrors();
//                insideContext = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((C) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("meta")){
//                M child = createMeta();
//                child.startElement(uri, localName, qName, attributes);
//                addMeta(child);
//            }
//            else if(localName.equals("metadata")){
//                MT child = createMetadata();
//                child.startElement(uri, localName, qName, attributes);
//                addMetadata(child);
//            }
//            else if(localName.equals("port")){
//                Pt child = createPort();
//                child.startElement(uri, localName, qName, attributes);
//                addPort(child);
//            }
//            else if(localName.equals("property")){
//                Pp child = createProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addProperty(child);
//            }
//            else if(localName.equals("media")){
//                N child = createMedia();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("context") && insideContext){
//                N child = createContext();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("switch")){
//                N child = createSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("link")){
//                L child = createLink();
//                child.startElement(uri, localName, qName, attributes);
//                addLink(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getRefer() != null)
//                contextReference();
//        }
//
//        if(hasMeta()){
//            for(M meta : metas){
//                meta.endDocument();
//                addWarning(meta.getWarnings());
//                addError(meta.getErrors());
//            }
//        }
//        if(hasMetadata()){
//            for(MT metadata : metadatas){
//                metadata.endDocument();
//                addWarning(metadata.getWarnings());
//                addError(metadata.getErrors());
//            }
//        }
//        if(hasPort()){
//            for(Pt port : ports){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//        if(hasProperty()){
//            for(Pp property : properties){
//                property.endDocument();
//                addWarning(property.getWarnings());
//                addError(property.getErrors());
//            }
//        }
//        if(hasNode()){
//            for(N node : nodes){
//                node.endDocument();
//                addWarning(node.getWarnings());
//                addError(node.getErrors());
//            }
//        }
//        if(hasLink()){
//            for(L link : links){
//                link.endDocument();
//                addWarning(link.getWarnings());
//                addError(link.getErrors());
//            }
//        }
//    }


//    private void contextReference() {
//        //Search for the interface inside the node
//        NCLElementImpl body = getParent();
//
//        while(!(body instanceof NCLBody)){
//            body = body.getParent();
//            if(body == null){
//                addWarning("Could not find a body");
//                return;
//            }
//        }
//
//        setRefer(findContext(((NCLBody) body).getNodes()));
//    }


//    private C findContext(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof NCLContext){
//                if(n.getId().equals(getRefer().getId()))
//                    return (C) n;
//                else if( ((NCLContext) n).hasNode()){
//                    Set<N> cnodes = ((NCLContext) n).getNodes();
//                    C c = findContext(cnodes);
//                    if(c != null)
//                        return (C) c;
//                }
//            }
//            else if(n instanceof NCLSwitch){
//                if( ((NCLSwitch) n).hasNode()){
//                    Set<N> snodes = ((NCLSwitch) n).getNodes();
//                    C c = findContext(snodes);
//                    if(c != null)
//                        return (C) c;
//                }
//            }
//        }
//
//        addWarning("Could not find media with id: " + getRefer().getId());
//        return null;
//    }


    public void load(Element element) throws XMLException {
        String att_name, ch_name;
        int length;

        att_name = NCLElementAttributes.REFER.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setRefer(); // usar metodo de busca por id para contextos


        ch_name = NCLElementSets.PORTS.toString();
        NodeList nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addPort((Ept) new NCLPort((Element) nl.item(i)));

        ch_name = NCLElementSets.PROPERTIES.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addProperty((Epp) new NCLProperty((Element) nl.item(i)));

        ch_name = NCLElementSets.NODES.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addNode((En) new NCLNode((Element) nl.item(i))); // tem que fazer este método na interface

        ch_name = NCLElementSets.LINKS.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addLink((El) new NCLLink((Element) nl.item(i)));

        ch_name = NCLElementSets.METAS.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addMeta((Em) new NCLMeta((Element) nl.item(i)));

        ch_name = NCLElementSets.METADATAS.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addMetadata((Emt) new NCLMetadata((Element) nl.item(i)));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>meta</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>meta</i>.
     */
    protected NCLMeta createMeta() throws XMLException {
        return new NCLMeta();
    }


    /**
     * Function to create the child element <i>metadata</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>metadata</i>.
     */
    protected NCLMetadata createMetadata() throws XMLException {
        return new NCLMetadata();
    }


    /**
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected NCLPort createPort(String id) throws XMLException {
        return new NCLPort(id);
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected NCLProperty createProperty(String name) throws XMLException {
        return new NCLProperty(name);
    }


    /**
     * Function to create the child element <i>media</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>media</i>.
     */
    protected NCLMedia createMedia(String id) throws XMLException {
        return new NCLMedia(id);
    }


    /**
     * Function to create the child element <i>context</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>context</i>.
     */
    protected NCLContext createContext(String id) throws XMLException {
        return new NCLContext(id);
    }


    /**
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected NCLSwitch createSwitch(String id) throws XMLException {
        return new NCLSwitch(id);
    }


    /**
     * Function to create the child element <i>link</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>link</i>.
     */
    protected NCLLink createLink() throws XMLException {
        return new NCLLink();
    }
}
