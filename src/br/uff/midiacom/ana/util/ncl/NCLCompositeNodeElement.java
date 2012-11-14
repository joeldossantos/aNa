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
package br.uff.midiacom.ana.util.ncl;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import java.util.ArrayList;


/**
 * Class that represents an NCL composite node.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the composite node element. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>port</i> - element representing a composite node interface point. The
 *                    composite node can have none or several port elements.</li>
 *  <li><i>property</i> - element representing a property. The composite node can
 *                        have none or several property elements.</li>
 *  <li><i>media</i> - element representing a media object. The composite node
 *                     can have none or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The composite node
 *                       can have none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      composite node can have none or several switch elements.</li>
 *  <li><i>link</i> - element representing a link among medias or compositions.
 *                    The composite node can have none or several link elements.</li>
 *  <li><i>meta</i> - elements defining meta data. The composite node can have
 *                    none or several meta elements.</li>
 *  <li><i>metadata</i> - elements defining a RDF tree. The composite node can
 *                        have none or several metadata elements.</li>
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
public abstract class NCLCompositeNodeElement<T extends NCLElement,
                                              Ept extends NCLPort,
                                              Epp extends NCLProperty,
                                              En extends NCLNode,
                                              El extends NCLLink,
                                              Em extends NCLMeta,
                                              Emt extends NCLMetadata>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLElement<T>, ReferredElement<T> {

    protected ElementList<Ept> ports;
    protected ElementList<Epp> properties;
    protected ElementList<En> nodes;
    protected ElementList<El> links;
    protected ElementList<Em> metas;
    protected ElementList<Emt> metadatas;
    
    protected ArrayList<T> references;


    /**
     * Composite node constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompositeNodeElement() {
        super();
        ports = new ElementList<Ept>();
        properties = new ElementList<Epp>();
        nodes = new ElementList<En>();
        links = new ElementList<El>();
        metas = new ElementList<Em>();
        metadatas = new ElementList<Emt>();
        references = new ArrayList<T>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ept aux : ports) {
            aux.setDoc(doc);
        }
        for (Epp aux : properties) {
            aux.setDoc(doc);
        }
        for (En aux : nodes) {
            aux.setDoc(doc);
        }
        for (El aux : links) {
            aux.setDoc(doc);
        }
        for (Em aux : metas) {
            aux.setDoc(doc);
        }
        for (Emt aux : metadatas) {
            aux.setDoc(doc);
        }
    }


    /**
     * Adds an element representing a composite node interface point to the
     * composite node. The composite node can have none or several port elements.
     *
     * @param port
     *          element representing a composite node interface point.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean addPort(Ept port) throws XMLException {
        if(ports.add(port)){
            notifyInserted((T) port);
            port.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a composite node interface point of the
     * composite node. The composite node can have none or several port elements.
     *
     * @param port
     *          element representing a composite node interface point.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean removePort(Ept port) throws XMLException {
        if(!port.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(ports.remove(port)){
            notifyRemoved((T) port);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a composite node interface point of the
     * composite node. The composite node can have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          composite node interface point.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removePort(String id) throws XMLException {
        Ept aux = ports.get(id);
        return removePort(aux);
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a composite node interface point of the composite node. The composite node
     * can have none or several port elements.
     * 
     * @param port
     *          element representing a composite node interface point.
     * @return
     *          true if the composite node element has the port element.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean hasPort(Ept port) throws XMLException {
        return ports.contains(port);
    }


    /**
     * Verifies if the composite node element has a interface point with a
     * specific id. The composite node can have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          composite node interface point.
     * @return
     *          true if the composite node element has the port element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasPort(String id) throws XMLException {
        return ports.get(id) != null;
    }


    /**
     * Verifies if the composite node element has at least one interface point.
     * The composite node can have none or several port elements.
     * 
     * @return 
     *          true if the composite node has at least one interface point.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Returns the list of interface points that a composite node element have.
     * The composite node can have none or several port elements.
     * 
     * @return 
     *          element list with all interface points.
     */
    public ElementList<Ept> getPorts() {
        return ports;
    }


    /**
     * Returns the interface point with a specific id. The composite node can
     * have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          composite node interface point.
     * @return 
     *          element representing a composite node interface point.
     */
    public Ept getPort(String id) throws XMLException {
        return ports.get(id);
    }


    /**
     * Adds an element representing a property to the composite node. The
     * composite node can have none or several property elements.
     *
     * @param property
     *          element representing a property.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean addProperty(Epp property) throws XMLException {
        if(properties.add(property)){
            notifyInserted((T) property);
            property.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a property of the composite node. The
     * composite node can have none or several property elements.
     *
     * @param property
     *          element representing a property.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean removeProperty(Epp property) throws XMLException {
        if(!property.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(properties.remove(property)){
            notifyRemoved((T) property);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a property of the composite node. The
     * composite node can have none or several property elements.
     *
     * @param name
     *          string representing the name of the property.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeProperty(String name) throws XMLException {
        Epp aux = properties.get(name);
        return removeProperty(aux);
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a property. The composite node can have none or several property elements.
     *
     * @param property
     *          element representing a property.
     * @return
     *          true if the composite node element has the property.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean hasProperty(Epp property) throws XMLException {
        return properties.contains(property);
    }


    /**
     * Verifies if the composite node element has a property with a specific
     * name. The composite node can have none or several property elements.
     * 
     * @param name
     *          string representing the name of the property.
     * @return
     *          true if the composite node element has the property.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasProperty(String name) throws XMLException {
        return properties.get(name) != null;
    }


    /**
     * Verifies if the composite node element has at least one property. The
     * composite node can have none or several property elements.
     * 
     * @return 
     *          true if the composite node has at least one property.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Returns the list of properties that a composite node element have. The
     * composite node can have none or several property elements.
     * 
     * @return 
     *          element list with all properties.
     */
    public ElementList<Epp> getProperties() {
        return properties;
    }


    /**
     * Returns the property with a specific name. The composite node can have
     * none or several property elements.
     * 
     * @param name
     *          string representing the name of the property.
     * @return 
     *          element representing a property.
     */
    public Epp getProperty(String name) throws XMLException {
        return properties.get(name);
    }


    /**
     * Adds an element representing a node to the composite node element. The
     * node can be a media, a context or a switch element. The composite node
     * can have none or several node elements.
     * 
     * @param node
     *          element representing a node.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean addNode(En node) throws XMLException {
        if(nodes.add(node)){
            notifyInserted((T) node);
            node.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a node of the composite node element. The
     * node can be a media, a context or a switch element. The composite node
     * can have none or several node elements.
     * 
     * @param node
     *          element representing a node.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean removeNode(En node) throws XMLException {
        if(!node.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(nodes.remove(node)){
            notifyRemoved((T) node);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a node of the composite node element. The
     * node can be a media, a context or a switch element. The composite node
     * can have none or several node elements.
     *
     * @param id
     *          string representing the id of the node.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeNode(String id) throws XMLException {
        En aux = nodes.get(id);
        return removeNode(aux);
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a node. The node can be a media, a context or a switch element. The
     * composite node can have none or several node elements.
     *
     * @param node
     *          element representing a node.
     * @return
     *          true if the composite node element has the node.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean hasNode(En node) throws XMLException {
        return nodes.contains(node);
    }


    /**
     * Verifies if the composite node element has a node with a specific
     * id. The node can be a media, a context or a switch element. The composite
     * node can have none or several node elements.
     * 
     * @param id
     *          string representing the id of the node.
     * @return
     *          true if the composite node element has the node.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasNode(String id) throws XMLException {
        return nodes.get(id) != null;
    }


    /**
     * Verifies if the composite node element has at least one node. The node 
     * can be a media, a context or a switch element. The composite node can
     * have none or several node elements.
     * 
     * @return 
     *          true if the composite node has at least one node.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Returns the list of nodes that a composite node element have. The node
     * can be a media, a context or a switch element. The composite node can 
     * have none or several node elements.
     * 
     * @return 
     *          element list with all nodes.
     */
    public ElementList<En> getNodes() {
        return nodes;
    }


    /**
     * Returns the node with a specific id. The node can be a media, a context
     * or a switch element. The composite node can have none or several node
     * elements.
     * 
     * @param id
     *          string representing the id of the node.
     * @return 
     *          element representing a node.
     */
    public En getNode(String id) throws XMLException {
        return nodes.get(id);
    }


    /**
     * Adds an element representing a link among nodes to the composite node.
     * The composite node can have none or several link elements.
     *
     * @param link
     *          element representing a link among nodes.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the link is null.
     */
    public boolean addLink(El link) throws XMLException {
        if(links.add(link)){
            notifyInserted((T) link);
            link.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a link among nodes of the composite node.
     * The composite node can have none or several link elements.
     *
     * @param link
     *          element representing a link among nodes.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the link is null.
     */
    public boolean removeLink(El link) throws XMLException {
        if(links.remove(link)){
            notifyRemoved((T) link);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a link among nodes of the composite node.
     * The composite node can have none or several link elements. Note that it
     * is possible that a link element does not have an id.
     *
     * @param id
     *          string representing the id of the link.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeLink(String id) throws XMLException {
        El aux = links.get(id);
        return removeLink(aux);
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a link among nodes. The composite node can have none or several link
     * elements.
     *
     * @param link
     *          element representing a link among nodes.
     * @return
     *          true if the composite node element has the link.
     * @throws XMLException 
     *          if the element representing the link is null.
     */
    public boolean hasLink(El link) throws XMLException {
        return links.contains(link);
    }


    /**
     * Verifies if the composite node element has a link among nodes with a
     * specific id. The composite node can have none or several link elements.
     * Note that it is possible that a link element does not have an id.
     * 
     * @param id
     *          string representing the id of the link.
     * @return
     *          true if the composite node element has the link.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasLink(String id) throws XMLException {
        return links.get(id) != null;
    }


    /**
     * Verifies if the composite node element has at least one link among nodes.
     * The composite node can have none or several link elements.
     * 
     * @return 
     *          true if the composite node has at least one link.
     */
    public boolean hasLink() {
        return !links.isEmpty();
    }


    /**
     * Returns the list of links among nodes that a composite node element have.
     * The composite node can have none or several link elements.
     * 
     * @return 
     *          element list with all links.
     */
    public ElementList<El> getLinks() {
        return links;
    }


    /**
     * Returns the link with a specific id. The composite node can have none or
     * several link elements.
     * 
     * @param id
     *          string representing the id of the link.
     * @return 
     *          element representing a link.
     */
    public El getLink(String id) throws XMLException {
        return links.get(id);
    }


    /**
     * Adds an element defining meta data to the composite node element. The
     * composite node can have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the meta element was added.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean addMeta(Em meta) throws XMLException {
        if(metas.add(meta)){
            notifyInserted((T) meta);
            meta.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element defining meta data of the composite node element. The
     * composite node can have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the meta element was removed.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean removeMeta(Em meta) throws XMLException {
        if(metas.remove(meta)){
            notifyRemoved((T) meta);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific meta element. The
     * composite node can have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the composite node element has the meta element.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean hasMeta(Em meta) throws XMLException {
        return metas.contains(meta);
    }


    /**
     * Verifies if the composite node element has at least one meta element. The
     * composite node can have none or several meta elements.
     * 
     * @return 
     *          true if the composite node has at least one meta element.
     */
    public boolean hasMeta() {
        return !metas.isEmpty();
    }


    /**
     * Returns the list of meta elements that a composite node element have. The
     * composite node can have none or several meta elements.
     * 
     * @return 
     *          element list with all meta elements.
     */
    public ElementList<Em> getMetas() {
        return metas;
    }


    /**
     * Adds an element defining an RDF tree to the composite node element. The
     * composite node can have none or several metadata elements.
     * 
     * @param metadata
     *          element defining an RDF tree.
     * @return
     *          true if the metadata element was added.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean addMetadata(Emt metadata) throws XMLException {
        if(metadatas.add(metadata)){
            notifyInserted((T) metadata);
            metadata.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element defining an RDF tree of the composite node element. The
     * composite node can have none or several metadata elements.
     * 
     * @param metadata
     *          element defining an RDF tree.
     * @return
     *          true if the metadata element was removed.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean removeMetadata(Emt metadata) throws XMLException {
        if(metadatas.remove(metadata)){
            notifyRemoved((T) metadata);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific metadata element.
     * The composite node can have none or several metadata elements.
     * 
     * @param meta
     *          element defining an RDF tree.
     * @return
     *          true if the composite node element has the metadata element.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean hasMetadata(Emt metadata) throws XMLException {
        return metadatas.contains(metadata);
    }


    /**
     * Verifies if the composite node element has at least one metadata element.
     * The composite node can have none or several metadata elements.
     * 
     * @return 
     *          true if the composite node has at least one metadata element.
     */
    public boolean hasMetadata() {
        return !metadatas.isEmpty();
    }


    /**
     * Returns the list of metadata elements that a composite node element have.
     * The composite node can have none or several metadata elements.
     * 
     * @return 
     *          element list with all metadata elements.
     */
    public ElementList<Emt> getMetadatas() {
        return metadatas;
    }
    
    
    public boolean compareContent(NCLCompositeNodeElement other) {
        boolean result = true;
        
        String saux = getId();
        if(saux != null)
            result &= saux.equals(other.getId());
        
        
        ElementList<Ept> otherpor = other.getPorts();
        result &= ports.size() == otherpor.size();
        for (Ept aux : ports) {
            try {
                result &= otherpor.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Epp> otherpro = other.getProperties();
        result &= properties.size() == otherpro.size();
        for (Epp aux : properties) {
            try {
                result &= otherpro.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<En> othernod = other.getNodes();
        result &= nodes.size() == othernod.size();
        for (En aux : nodes) {
            try {
                result &= othernod.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<El> otherlin = other.getLinks();
        result &= links.size() == otherlin.size();
        for (El aux : links) {
            try {
                result &= otherlin.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Em> othermet = other.getMetas();
        result &= metas.size() == othermet.size();
        for (Em aux : metas) {
            try {
                result &= othermet.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Emt> othermtd = other.getMetadatas();
        result &= metadatas.size() == othermtd.size();
        for (Emt aux : metadatas) {
            try {
                result &= othermtd.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        return result;
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(T reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(T reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
}
