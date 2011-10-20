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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBodyPrototype;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLBody<T extends NCLBody, P extends NCLElement, I extends NCLElementImpl, Ept extends NCLPort, Epp extends NCLProperty, En extends NCLNode, El extends NCLLink, Em extends NCLMeta, Emt extends NCLMetadata>
        extends NCLBodyPrototype<T, P, I, Ept, Epp, En, El, Em, Emt> implements NCLIdentifiableElement<T, P> {

    
    public NCLBody() throws XMLException {
        super();
    }


    public NCLBody(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
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


    public void load(Element element) throws XMLException {
        String att_name, att_var;
        NodeList nl;

        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);

        // create the child nodes (except ports and links)
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                // create the property
                if(el.getTagName().equals(NCLElementAttributes.PROPERTY.toString()))
                    addProperty(createProperty(el));
                // create the meta
                if(el.getTagName().equals(NCLElementAttributes.META.toString()))
                    addMeta(createMeta(el));
                // create the metadata
                if(el.getTagName().equals(NCLElementAttributes.METADATA.toString()))
                    addMetadata(createMetadata(el));
                // create the media
                if(el.getTagName().equals(NCLElementAttributes.MEDIA.toString()))
                    addNode(createMedia(el));
                // create the context
                if(el.getTagName().equals(NCLElementAttributes.CONTEXT.toString()))
                    addNode(createContext(el));
                // create the switch
                if(el.getTagName().equals(NCLElementAttributes.SWITCH.toString()))
                    addNode(createSwitch(el));
            }
        }

        // create the child nodes (ports and links)
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                //create the port
                if(el.getTagName().equals(NCLElementAttributes.PORT.toString()))
                    addPort(createPort(el));
                // create the link
                if(el.getTagName().equals(NCLElementAttributes.LINK.toString()))
                    addLink(createLink(el));
            }
        }
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
    protected Em createMeta(Element element) throws XMLException {
        return (Em) new NCLMeta(element);
    }


    /**
     * Function to create the child element <i>metadata</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>metadata</i>.
     */
    protected Emt createMetadata(Element element) throws XMLException {
        return (Emt) new NCLMetadata(element);
    }


    /**
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected Ept createPort(Element element) throws XMLException {
        return (Ept) new NCLPort(element);
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Epp createProperty(Element element) throws XMLException {
        return (Epp) new NCLProperty(element);
    }


    /**
     * Function to create the child element <i>media</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>media</i>.
     */
    protected En createMedia(Element element) throws XMLException {
        return (En) new NCLMedia(element);
    }


    /**
     * Function to create the child element <i>context</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>context</i>.
     */
    protected En createContext(Element element) throws XMLException {
        return (En) new NCLContext(element);
    }


    /**
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected En createSwitch(Element element) throws XMLException {
        return (En) new NCLSwitch(element);
    }


    /**
     * Function to create the child element <i>link</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>link</i>.
     */
    protected El createLink(Element element) throws XMLException {
        return (El) new NCLLink(element);
    }
}
