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
import br.uff.midiacom.ana.util.SrcType;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLInstanceType;
import br.uff.midiacom.ana.util.enums.NCLMediaType;
import br.uff.midiacom.ana.util.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a media node element. A media node represents a media
 * object (text, audio, video, image, etc) inside an NCL document.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the media element. This attribute is required.</li>
 *  <li><i>src</i> - location of the media element content. This attribute is
 *                   optional.</li>
 *  <li><i>refer</i> - reference to a media element. This attribute is optional.</li>
 *  <li><i>instance</i> - type of reuse the media element does. This attribute
 *                        is optional.</li>
 *  <li><i>type</i> - type of a media element. This attribute is optional.</li>
 *  <li><i>descriptor</i> - descriptor that describes the media presentation. This
 *                          attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>area</i> - element representing a anchor of the media. An anchor
 *                    represents a subpart of the node content. The media can
 *                    have none or several area elements.</li>
 *  <li><i>property</i> - element representing a property. The media can have
 *                        none or several property elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ea>
 * @param <Ep>
 * @param <Ed>
 * @param <En>
 * @param <Rn> 
 */
public class NCLMedia<T extends NCLElement,
                      Ea extends NCLArea,
                      Ep extends NCLProperty,
                      El extends NCLLayoutDescriptor,
                      En extends NCLNode,
                      Ei extends NCLInterface,
                      R extends ExternalReferenceType>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLNode<T, En, Ei>, PostReferenceElement {

    protected SrcType src;
    protected NCLMimeType type;
    protected Object descriptor;
    protected Object refer;
    protected NCLInstanceType instance;
    protected ElementList<Ea> areas;
    protected ElementList<Ep> properties;
    
    protected ArrayList<T> references;
    
    
    /**
     * Media element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLMedia() {
        super();
        areas = new ElementList<Ea>();
        properties = new ElementList<Ep>();
        references = new ArrayList<T>();
    }
    
    
    public NCLMedia(String id) throws XMLException {
        super();
        areas = new ElementList<Ea>();
        properties = new ElementList<Ep>();
        references = new ArrayList<T>();
        setId(id);
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ea aux : areas) {
            aux.setDoc(doc);
        }
        for (Ep aux : properties) {
            aux.setDoc(doc);
        }
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
    }
    
    
    /**
     * Sets the location of the media element content. This attribute is
     * optional. Set the src to <i>null</i> to erase a src already defined.
     * 
     * <br/>
     * 
     * The src value can be of the following type:
     * <ul>
     *  <li><i>file:///file_path/#fragment_identifier</i> - for local files.</li>
     *  <li><i>http://server_identifier/file_path/#fragment_identifier</i> - for
     *              the remote files got by the interactive channel using the
     *              http protocol.</li>
     *  <li><i>https://server_identifier/file_path/#fragment_identifier</i> - for
     *              the remote files got by the interactive channel using the
     *              https protocol.</li>
     *  <li><i>rstp://server_identifier/file_path/#fragment_identifier</i> - for
     *              streams got by the interactive channel using the rstp
     *              protocol.</li>
     * <li><i>rtp://server_identifier/file_path/#fragment_identifier</i> - for
     *              streams got by the interactive channel using the rtp
     *              protocol.</li>
     *  <li><i>ncl-mirror://media_element_identifier</i> - for a content
     *              identical to the one being presented by another media
     *              element.</li>
     *  <li><i>sbtvd-ts://program_number.component_tag</i> - for a stream inside
     *              the received transport stream.</li>
     * </ul>
     * 
     * <br/>
     * 
     * The local URI can be relative. The src can also define a time when the
     * media represents a clock.
     * 
     * @see #setType(br.uff.midiacom.ana.datatype.enums.NCLMimeType) 
     * 
     * @param src 
     *          element representing the location of the media content or
     *          <i>null</i> to erase the location already defined.
     */
    public void setSrc(SrcType src) throws XMLException {
        SrcType aux = this.src;
        this.src = src;
        notifyAltered(NCLElementAttributes.SRC, aux, src);
    }
    
    
    /**
     * Returns the location of the media element content or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The src value can be of the following type:
     * <ul>
     *  <li><i>file:///file_path/#fragment_identifier</i> - for local files.</li>
     *  <li><i>http://server_identifier/file_path/#fragment_identifier</i> - for
     *              the remote files got by the interactive channel using the
     *              http protocol.</li>
     *  <li><i>https://server_identifier/file_path/#fragment_identifier</i> - for
     *              the remote files got by the interactive channel using the
     *              https protocol.</li>
     *  <li><i>rstp://server_identifier/file_path/#fragment_identifier</i> - for
     *              streams got by the interactive channel using the rstp
     *              protocol.</li>
     * <li><i>rtp://server_identifier/file_path/#fragment_identifier</i> - for
     *              streams got by the interactive channel using the rtp
     *              protocol.</li>
     *  <li><i>ncl-mirror://media_element_identifier</i> - for a content
     *              identical to the one being presented by another media
     *              element.</li>
     *  <li><i>sbtvd-ts://program_number.component_tag</i> - for a stream inside
     *              the received transport stream.</li>
     * </ul>
     * 
     * <br/>
     * 
     * The local URI can be relative. The src can also define a time when the
     * media represents a clock.
     * 
     * @see #setType(br.uff.midiacom.ana.datatype.enums.NCLMimeType) 
     * 
     * @return 
     *          element representing the location of the media content or
     *          <i>null</i> if the attribute is not defined.
     */
    public SrcType getSrc() {
        return src;
    }
    
    
    /**
     * Sets the type of the media element. This attribute is optional. Set the
     * type to <i>null</i> to erase a type already defined. The possible types
     * are defined in the enumeration <i>NCLMimeType</i>.
     * 
     * <br/>
     * 
     * The type <i>application/x-ginga-settings</i> is applied to a special media
     * element, whose properties are global variables defined by the author or
     * system variables  that will be used in the document. An NCL document can
     * have only one media element with that type.
     * 
     * <br/>
     * 
     * The type <i>application/x-ginga-time</i> is applied to a special media
     * element, whose content is a duration (UTC - Universal Time Coordinated).
     * This type of media defines a clock. This media src is defined with the
     * following syntax: year:month:day:hour:minute:second:fraction.
     * 
     * @param type 
     *          type of the media element from the enumeration <i>NCLMimeType</i>
     *          or <i>null</i> to erase a type already defined.
     */
    public void setType(NCLMimeType type) throws XMLException {
        NCLMimeType aux = this.type;
        this.type = type;
        notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }
    
    
    /**
     * Returns the type of the media element or <i>null</i> if the attribute is
     * not defined. The possible types are defined in the enumeration
     * <i>NCLMimeType</i>.
     * 
     * <br/>
     * 
     * The type <i>application/x-ginga-settings</i> is applied to a special media
     * element, whose properties are global variables defined by the author or
     * system variables  that will be used in the document. An NCL document can
     * have only one media element with that type.
     * 
     * <br/>
     * 
     * The type <i>application/x-ginga-time</i> is applied to a special media
     * element, whose content is a duration (UTC - Universal Time Coordinated).
     * This type of media defines a clock. This media src is defined with the
     * following syntax: year:month:day:hour:minute:second:fraction.
     * 
     * @return 
     *          type of the media element from the enumeration <i>NCLMimeType</i>
     *          or <i>null</i> if the attribute is not defined.
     */
    public NCLMimeType getType() {
        return type;
    }
    
    
    /**
     * Sets the descriptor that describes the media presentation. This attribute
     * is optional. Set the descriptor to <i>null</i> to erase a descriptor 
     * already defined.
     * 
     * <br/>
     * 
     * The descriptor referred can be defined in the document base of descriptors
     * or in a base defined in an external document, imported by the base of
     * descriptors or by the base of imported documents. When the descriptor is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @param descriptor
     *          element representing a descriptor, a reference to a descriptor
     *          element or <i>null</i> to erase a descriptor already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setDescriptor(Object descriptor) throws XMLException {
        Object aux = this.descriptor;
        
        if(descriptor instanceof NCLLayoutDescriptor)
            ((El) descriptor).addReference(this);
        else if(descriptor instanceof ExternalReferenceType){
            ((R) descriptor).getTarget().addReference(this);
            ((R) descriptor).getAlias().addReference(this);
        }
        
        this.descriptor = descriptor;
        notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, descriptor);
        
        if(aux != null){
            if(aux instanceof NCLLayoutDescriptor)
                ((El) aux).removeReference(this);
            else{
                ((R) aux).getTarget().removeReference(this);
                ((R) aux).getAlias().removeReference(this);
            }
        }
    }
    
    
    /**
     * Returns the descriptor that describes the media presentation or <i>null</i>
     * is the attribute is not defined.
     * 
     * <br/>
     * 
     * The descriptor referred can be defined in the document base of descriptors
     * or in a base defined in an external document, imported by the base of
     * descriptors or by the base of imported documents. When the descriptor is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @return 
     *          element representing a descriptor, a reference to a descriptor
     *          element or <i>null</i> if the attribute is not defined.
     */
    public Object getDescriptor() {
        return descriptor;
    }


    /**
     * Sets the reference to a media element. This attribute is optional. Set the
     * reference to <i>null</i> to erase a reference already defined.
     * 
     * <br/>
     * 
     * The referred element must be a media element that represents the same
     * media defined in the body of the NCL document where this media is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child
     * elements (area and property). It can not refer to another node.
     * 
     * <br/>
     * 
     * When a media element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attributes
     * defined by the media are ignored, except the id and instance attributes.
     * The media can add new area and property elements to the referred media
     * by defining then as children.
     * 
     * <br/>
     * 
     * If an area or property defined in the media has the same id or name,
     * respectively, of an area or property defined in the referred media, the
     * area or property is ignored.
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
     * @see #setInstance(br.uff.midiacom.ana.datatype.enums.NCLInstanceType) 
     * 
     * 
     * @param refer
     *          element representing a media, a reference to a media element or
     *          <i>null</i> to erase a reference already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the media
     *          element.
     */
    public void setRefer(Object refer) throws XMLException {
        Object aux = this.refer;
        
        if(refer instanceof NCLMedia)
            ((En) refer).addReference(this);
        else if(refer instanceof ExternalReferenceType){
            ((R) refer).getTarget().addReference(this);
            ((R) refer).getAlias().addReference(this);
        }
        
        this.refer = refer;
        notifyAltered(NCLElementAttributes.REFER, aux, refer);
        
        if(aux != null){
            if(aux instanceof NCLMedia)
                ((En) aux).removeReference(this);
            else{
                ((R) aux).getTarget().removeReference(this);
                ((R) aux).getAlias().removeReference(this);
            }
        }
    }


    /**
     * Returns the reference to a media element or <i>null</i> if the attribute
     * is not defined.
     * 
     * <br/>
     * 
     * The referred element must be a media element that represents the same
     * media defined in the body of the NCL document where this media is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child
     * elements (area and property). It can not refer to another node.
     * 
     * <br/>
     * 
     * When a media element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attributes
     * defined by the media are ignored, except the id and instance attributes.
     * The media can add new area and property elements to the referred media
     * by defining then as children.
     * 
     * <br/>
     * 
     * If an area or property defined in the media has the same id or name,
     * respectively, of an area or property defined in the referred media, the
     * area or property is ignored.
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
     * @see #setInstance(br.uff.midiacom.ana.datatype.enums.NCLInstanceType) 
     * 
     * @return 
     *          element representing a media, a reference to a media element or
     *          <i>null</i> if the attribute is not defined.
     */
    public Object getRefer() {
        return refer;
    }


    /**
     * Sets the type of reuse the media element does. This attribute is optional.
     * Set its value to <i>null</i> to remove a value already defined. The
     * possible types of reuse are defined in the enumeration <i>NCLInstanceType</i>.
     * 
     * <br/>
     * 
     * If the type of reuse is <i>instSame</i> the media element and the referred
     * media element are represented as one single instance. If a start a media
     * is started all the other medias that represent the same instance are also
     * started at the same time.
     * 
     * <br/>
     * 
     * If the type of reuse is <i>gradSame</i> the media element and the referred
     * media element are represented as one single instance. The medias
     * presentation, however, starts as the media nodes are started by links
     * defined in the document. That means that a media element that reuse another
     * media element, when started, will begin its presentation at the same point
     * the referred media presentation is.
     * 
     * <br/>
     * 
     * If the type of reuse is <i>new</i> the media element represents a new
     * instance of the media element and its presentation is not related to
     * the media if refers.
     * 
     * <br/>
     * 
     * The default value of the instance attribute is <i>new</i>.
     * 
     * @param instance 
     *          type of reuse from the enumeration <i>NCLMimeType</i> or <i>null</i>
     *          to erase a type already defined.
     */
    public void setInstance(NCLInstanceType instance) throws XMLException {
        NCLInstanceType aux = this.instance;
        this.instance = instance;
        notifyAltered(NCLElementAttributes.INSTANCE, aux, instance);
    }


    /**
     * Returns the type of reuse the media element does or <i>null</i> if the
     * attribute is not defined. The possible types of reuse are defined in the
     * enumeration <i>NCLInstanceType</i>.
     * 
     * <br/>
     * 
     * If the type of reuse is <i>instSame</i> the media element and the referred
     * media element are represented as one single instance. If a start a media
     * is started all the other medias that represent the same instance are also
     * started at the same time.
     * 
     * <br/>
     * 
     * If the type of reuse is <i>gradSame</i> the media element and the referred
     * media element are represented as one single instance. The medias
     * presentation, however, starts as the media nodes are started by links
     * defined in the document. That means that a media element that reuse another
     * media element, when started, will begin its presentation at the same point
     * the referred media presentation is.
     * 
     * <br/>
     * 
     * If the type of reuse is <i>new</i> the media element represents a new
     * instance of the media element and its presentation is not related to
     * the media if refers.
     * 
     * <br/>
     * 
     * The default value of the instance attribute is <i>new</i>.
     * 
     * @return 
     *          type of reuse from the enumeration <i>NCLMimeType</i> or <i>null</i>
     *          if the attribute is not defined.
     */
    public NCLInstanceType getInstance() {
        return instance;
    }
    
    
    /**
     * Adds an anchor to the media. An anchor represents a subpart of the node
     * content. The media can have none or several area elements.
     * 
     * @param area
     *          element representing an anchor.
     * @return
     *          true if the area was added.
     * @throws XMLException 
     *          if the element representing the area is null.
     */
    public boolean addArea(Ea area) throws XMLException {
        if(areas.add(area)){
            notifyInserted((T) area);
            area.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an anchor of the media. An anchor represents a subpart of the node
     * content. The media can have none or several area elements.
     * 
     * @param area
     *          element representing an anchor.
     * @return
     *          true if the area was removed.
     * @throws XMLException 
     *          if the element representing the area is null.
     */
    public boolean removeArea(Ea area) throws XMLException {
        if(!area.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(areas.remove(area)){
            notifyRemoved((T) area);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes an anchor of the media. An anchor represents a subpart of the node
     * content. The media can have none or several area elements.
     * 
     * @param id
     *          string representing the id of the element representing an anchor.
     * @return
     *          true if the area was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeArea(String id) throws XMLException {
        Ea aux = areas.get(id);
        return removeArea(aux);
    }


    /**
     * Verifies if the media has a specific element representing an anchor.
     * An anchor represents a subpart of the node content. The media can have
     * none or several area elements.
     * 
     * @param area
     *          element representing an anchor.
     * @return
     *          true if the media has the area element.
     * @throws XMLException 
     *          if the element representing the are is null.
     */
    public boolean hasArea(Ea area) throws XMLException {
        return areas.contains(area);
    }
    
    
    /**
     * Verifies if the media has an anchor with a specific id. An anchor
     * represents a subpart of the node content. The media can have none or
     * several area elements.
     * 
     * @param id
     *          string representing the id of the element representing an anchor.
     * @return
     *          true if the media has the area element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasArea(String id) throws XMLException {
        return areas.get(id) != null;
    }
    
    
    /**
     * Verifies if the media has at least one anchor. An anchor represents a
     * subpart of the node content. The media can have none or several area
     * elements.
     * 
     * @return 
     *          true if the media has at least one area.
     */
    public boolean hasArea() {
        return !areas.isEmpty();
    }
    
    
    /**
     * Returns the list of anchors that a media have. An anchor represents a
     * subpart of the node content. The media can have none or several area
     * elements.
     * 
     * @return 
     *          element list with all anchors.
     */
    public ElementList<Ea> getAreas() {
        return areas;
    }


    /**
     * Returns the anchor with a specific id. An anchor represents a
     * subpart of the node content. The media can have none or several area
     * elements.
     * 
     * @param id
     *          string representing the id of the element representing an anchor.
     * @return 
     *          element representing a composite node interface point.
     */
    public Ea getArea(String id) throws XMLException {
        return areas.get(id);
    }
    
    
    /**
     * Adds a property to the media. A property represents a node attribute. The
     * media can have none or several property elements.
     * 
     * @param property
     *          element representing a property.
     * @return
     *          true if the property was added.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean addProperty(Ep property) throws XMLException {
        if(properties.add(property)){
            notifyInserted((T) property);
            property.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes a property of the media. A property represents a node attribute.
     * The media can have none or several property elements.
     * 
     * @param property
     *          element representing a property.
     * @return
     *          true if the property was removed.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean removeProperty(Ep property) throws XMLException {
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
     * Removes a property of the media. A property represents a node attribute.
     * The media can have none or several property elements.
     * 
     * @param id
     *          string representing the id of the element representing a property.
     * @return
     *          true if the property was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeProperty(String name) throws XMLException {
        Ep aux = properties.get(name);
        return removeProperty(aux);
    }


    /**
     * Verifies if the media has a specific element representing a property.
     * A property represents a node attribute. The media can have none or several
     * property elements.
     * 
     * @param property
     *          element representing a property.
     * @return
     *          true if the media has the property element.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean hasProperty(Ep property) throws XMLException {
        return properties.contains(property);
    }


    /**
     * Verifies if the media has a property with a specific id. A property
     * represents a node attribute. The media can have none or several property
     * elements.
     * 
     * @param id
     *          string representing the id of the element representing a property.
     * @return
     *          true if the media has the property element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasProperty(String name) throws XMLException {
        return properties.get(name) != null;
    }


    /**
     * Verifies if the media has at least one property. A property represents a
     * node attribute. The media can have none or several property elements.
     * 
     * @return 
     *          true if the media has at least one property.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Returns the list of properties that a media have. A property represents a
     * node attribute. The media can have none or several property elements.
     * 
     * @return 
     *          element list with all properties.
     */
    public ElementList<Ep> getProperties() {
        return properties;
    }


    /**
     * Returns the property with a specific name. A property represents a
     * node attribute. The media can have none or several property elements.
     * 
     * @param name
     *          string representing the name of the property.
     * @return 
     *          element representing a property.
     */
    public Ep getProperty(String name) throws XMLException {
        return properties.get(name);
    }


    /**
     * Returns the media type according to its content as defined in the
     * attribute src.
     * 
     * @see #setSrc(br.uff.midiacom.ana.datatype.aux.basic.SrcType) 
     *
     * @return
     *          media type from the enumeration <i>NCLMediaType</i>.
     */
    public NCLMediaType getMediaType() {
        if(getType() != null)
            return NCLMediaType.getEnumType(getType());

        if(getSrc() != null){
            try{
                return NCLMediaType.getEnumType(getSrc().getExtension());
            }
            catch(NCLParsingException e){}
        }

        return NCLMediaType.OTHER;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLMedia))
            return false;
        
        boolean result = true;
        
        Object aux;
        if((aux = getId()) != null)
            result &= aux.equals(((NCLMedia) other).getId());
        if((aux = getSrc()) != null)
            result &= aux.equals(((NCLMedia) other).getSrc());
        if((aux = getType()) != null)
            result &= aux.equals(((NCLMedia) other).getType());
        if((aux = getInstance()) != null)
            result &= aux.equals(((NCLMedia) other).getInstance());
        
        Object ref = getDescriptor();
        Object oref = ((NCLMedia) other).getDescriptor();
        if(ref != null && oref != null){
            if(ref instanceof NCLLayoutDescriptor && oref instanceof NCLLayoutDescriptor)
                result &= ((El) ref).compare((El) oref);
            else if(ref instanceof ExternalReferenceType && oref instanceof ExternalReferenceType)
                result &= ((R) ref).equals((R) oref);
            else
                result = false;
        }
        
        ref = getRefer();
        oref = ((NCLMedia) other).getRefer();
        if(ref != null && oref != null){
            if(ref instanceof NCLMedia && oref instanceof NCLMedia)
                result &= ((En) ref).compare((En) oref);
            else if(ref instanceof ExternalReferenceType && oref instanceof ExternalReferenceType)
                result &= ((R) ref).equals((R) oref);
            else
                result = false;
        }
        
        ElementList<Ea> otherare = ((NCLMedia) other).getAreas();
        result &= areas.size() == otherare.size();
        for (Ea are : areas) {
            try {
                result &= otherare.contains(are);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Ep> otherpro = ((NCLMedia) other).getProperties();
        result &= properties.size() == otherpro.size();
        for (Ep pro : properties) {
            try {
                result &= otherpro.contains(pro);
            } catch (XMLException ex) {}
            if(!result)
                break;
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
        
        
        // <media> element and attributes declaration
        content = space + "<media";
        content += parseAttributes();
        
        // Test if the media has content
        if(hasArea() || hasProperty()){
            content += ">\n";
            
            content += parseElements(ident + 1);
            
            content += space + "</media>\n";
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
            loadSrc(element);
            loadType(element);
            loadDescriptor(element);
            loadInstance(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Media" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadAreas(el);
                    loadProperties(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Media" + aux + " > " + ex.getMessage());
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
            
            throw new NCLParsingException("Media" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseSrc();
        content += parseRefer();
        content += parseInstance();
        content += parseType();
        content += parseDescriptor();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseAreas(ident);
        content += parseProperties(ident);
        
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
    
    
    protected String parseSrc() {
        SrcType aux = getSrc();
        if(aux != null)
            return " src='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadSrc(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the src (optional)
        att_name = NCLElementAttributes.SRC.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setSrc(new SrcType(att_var));
    }
    
    
    protected String parseType() {
        NCLMimeType aux = getType();
        if(aux != null)
            return " type='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadType(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the type (optional)
        att_name = NCLElementAttributes.TYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setType(NCLMimeType.getEnumType(att_var));
    }
    
    
    protected String parseDescriptor() {
        Object aux = getDescriptor();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLLayoutDescriptor)
            return " descriptor='" + ((El) aux).getId() + "'";
        else
            return " descriptor='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadDescriptor(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the descriptor (optional)
        att_name = NCLElementAttributes.DESCRIPTOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] des = adjustReference(att_var);
            setDescriptor(d.getHead().findDescriptor(des[0], des[1]));
        }
    }
    
    
    protected String parseRefer() {
        Object aux = getRefer();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLMedia)
            return " refer='" + ((En) aux).getId() + "'";
        else
            return " refer='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadRefer(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the refer (optional)
        att_name = NCLElementAttributes.REFER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            En ref = (En) new NCLMedia(att_var);
            setRefer(ref);
            ((NCLDoc) getDoc()).waitReference(this);
        }
    }
    
    
    protected String parseInstance() {
        NCLInstanceType aux = getInstance();
        if(aux != null)
            return " instance='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadInstance(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the instance (optional)
        att_name = NCLElementAttributes.INSTANCE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setInstance(NCLInstanceType.getEnumType(att_var));
    }
    
    
    protected String parseAreas(int ident) {
        if(!hasArea())
            return "";
        
        String content = "";
        for(Ea aux : areas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadAreas(Element element) throws XMLException {
        //create the areas
        if(element.getTagName().equals(NCLElementAttributes.AREA.toString())){
            Ea inst = createArea();
            addArea(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseProperties(int ident) {
        if(!hasProperty())
            return "";
        
        String content = "";
        for(Ep aux : properties)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadProperties(Element element) throws XMLException {
        // create the properties
        if(element.getTagName().equals(NCLElementAttributes.PROPERTY.toString())){
            Ep inst = createProperty();
            addProperty(inst);
            inst.load(element);
        }
    }
    
    
    @Override
    public Ei findInterface(String id) throws XMLException {
        Ei result;
        
        // first search in the reused media
        Object aux;
        if((aux = getRefer()) != null){
            if(aux instanceof NCLMedia)
                result = (Ei) ((En) aux).findInterface(id);
            else
                result = (Ei) ((En) ((ExternalReferenceType) aux).getTarget()).findInterface(id);
            
            if(result != null)
                return result;
        }
        
        // search as a property
        result = (Ei) properties.get(id);
        if(result != null)
            return result;
        
        // search as an area
        result = (Ei) areas.get(id);
        if(result != null)
            return result;
        
        return null;
    }
    
    
    @Override
    public En findNode(String id) {
        if(getId().equals(id))
            return (En) this;
        else
            return null;
    }
    
    
    @Override
    @Deprecated
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // fix the refer
            if(getRefer() != null && (aux = ((En) getRefer()).getId()) != null){
                En ref = (En) ((NCLBody) ((NCLDoc) getDoc()).getBody()).findNode(aux);
                setRefer(ref);
            }
        }
        catch(XMLException ex){
            aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Media" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
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


    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(descriptor != null){
            if(descriptor instanceof NCLLayoutDescriptor)
                ((El)descriptor).removeReference(this);
            else if(descriptor instanceof ExternalReferenceType){
                ((R) descriptor).getTarget().removeReference(this);
                ((R) descriptor).getAlias().removeReference(this);
            }
        }
        
        if(refer != null){
            if(refer instanceof NCLMedia)
                ((NCLMedia)refer).removeReference(this);
            else if(refer instanceof ExternalReferenceType){
                ((R) refer).getTarget().removeReference(this);
                ((R) refer).getAlias().removeReference(this);
            }
        }
        
        src = null;
        type = null;
        descriptor = null;
        refer = null;
        instance = null;
        
        for(Ea a : areas)
            a.clean();
        
        for(Ep p : properties)
            p.clean();
    }
    
    
    /**
     * Function to create the child element <i>area</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>area</i>.
     */
    protected Ea createArea() throws XMLException {
        return (Ea) new NCLArea();
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Ep createProperty() throws XMLException {
        return (Ep) new NCLProperty();
    }
}
