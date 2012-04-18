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
package br.uff.midiacom.ana.datatype.ncl.descriptor;

import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;


/**
 * Class that represents a descriptor switch element. A descriptor switch element
 * represents a set of alternative descriptor to be used by a content node. The
 * descriptor to be used is chosen during presentation, according to a set of
 * rules.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the descriptor switch element. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>bindRule</i> - element relating a rule to a descriptor switch component
 *                        descriptor. The descriptor switch must have at least
 *                          one bindRule element.</li>
 *  <li><i>defaultDescriptor</i> - element representing the descriptor switch
 *                                 component descriptor to be used when no rule
 *                                 is true. This element is optional.</li>
 *  <li><i>descriptor</i> - element representing a descriptor. The descriptor
 *                          switch must have at least one descriptor element.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <El>
 * @param <Ed>
 * @param <Eb> 
 */
public abstract class NCLDescriptorSwitchPrototype<T extends NCLDescriptorSwitchPrototype,
                                                   P extends NCLElement,
                                                   I extends NCLElementImpl,
                                                   El extends NCLLayoutDescriptor,
                                                   Edd extends NCLDescriptor,
                                                   Ed extends DescriptorReference,
                                                   Eb extends NCLDescriptorBindRulePrototype>
        extends NCLIdentifiableElementPrototype<El, P, I>
        implements NCLLayoutDescriptor<El, P> {

    protected IdentifiableElementList<Edd, T> descriptors;
    protected ElementList<Eb, T> binds;
    protected Ed defaultDescriptor;
    
    protected ItemList<ReferenceType> references;


    /**
     * Descriptor switch element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorSwitchPrototype() throws XMLException {
        super();
        descriptors = new IdentifiableElementList<Edd, T>();
        binds = new ElementList<Eb, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Adds a descriptor to the descriptor switch. The descriptor switch must
     * have at least one descriptor element.
     * 
     * @param descriptor
     *          element representing a descriptor.
     * @return
     *          true if the element representing a descriptor was added.
     * @throws XMLException 
     *          if the element representing the descriptor is null.
     */
    public boolean addDescriptor(Edd descriptor) throws XMLException {
        if(descriptors.add(descriptor, (T) this)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor of the descriptor switch. The descriptor switch must
     * have at least one descriptor element.
     * 
     * @param descriptor
     *          element representing a descriptor.
     * @return
     *          true if the element representing a descriptor was removed.
     * @throws XMLException 
     *          if the element representing the descriptor is null.
     */
    public boolean removeDescriptor(Edd descriptor) throws XMLException {
        if(descriptors.remove(descriptor)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor of the descriptor switch. The descriptor switch must
     * have at least one descriptor element.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor.
     * @return
     *          true if the descriptor was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeDescriptor(String id) throws XMLException {
        if(descriptors.remove(id)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the descriptor switch has a specific element representing
     * a descriptor. The descriptor switch must have at least one descriptor
     * element.
     * 
     * @param descriptor
     *          element representing a descriptor.
     * @return
     *          true if the descriptor switch has the descriptor element.
     * @throws XMLException 
     *          if the element representing the descriptor is null.
     */
    public boolean hasDescriptor(Edd descriptor) throws XMLException {
        return descriptors.contains(descriptor);
    }


    /**
     * Verifies if the descriptor switch has a descriptor with a specific id.
     * The descriptor switch must have at least one descriptor element.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor.
     * @return
     *          true if the descriptor switch has the descriptor element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasDescriptor(String id) throws XMLException {
        return descriptors.get(id) != null;
    }


    /**
     * Verifies if the descriptor switch has at least one descriptor. The
     * descriptor switch must have at least one descriptor element.
     * 
     * @return 
     *          true if the descriptor switch has at least one descriptor.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Returns the list of descriptors that a descriptor switch have. The
     * descriptor switch must have at least one descriptor element.
     * 
     * @return 
     *          element list with all descriptors.
     */
    public IdentifiableElementList<Edd, T> getDescriptors() {
        return descriptors;
    }


    /**
     * Adds an element relating a rule to a descriptor switch component descriptor.
     * The descriptor switch can have none or several bindRule elements.
     * 
     * @param bind
     *          element relating a rule to a descriptor switch component descriptor.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean addBind(Eb bind) throws XMLException {
        if(binds.add(bind, (T) this)){
            impl.notifyInserted(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    /**
     * Removes an element relating a rule to a descriptor switch component
     * descriptor. The descriptor switch can have none or several bindRule elements.
     *
     * @param bind
     *          element relating a rule to a descriptor switch component descriptor.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean removeBind(Eb bind) throws XMLException {
        if(binds.remove(bind)){
            impl.notifyRemoved(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the descriptor switch element has a specific element relating
     * a rule to a descriptor switch component descriptor. The descriptor switch
     * can have none or several bindRule elements.
     *
     * @param bind
     *          element relating a rule to a descriptor switch component descriptor.
     * @return
     *          true if the descriptor switch element has the bind.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifies if the descriptor switch element has at least one element relating
     * a rule to a descriptor switch component descriptor. The descriptor switch
     * can have none or several bindRule elements.
     * 
     * @return 
     *          true if the descriptor switch has at least one bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Returns the list of binds that a descriptor switch element have. The
     * descriptor switch can have none or several bindRule elements.
     * 
     * @return 
     *          element list with all binds.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }


    /**
     * Sets the element representing the descriptor switch component descriptor
     * to be used when no rule is true. This element is optional. Set the default
     * descriptor to <i>null</i> to erase a default descriptor already defined.
     * 
     * @param defaultDescriptor 
     *          element representing a reference to a descriptor switch component
     *          descriptor or <i>null</i> to erase a default descriptor already
     *          defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor
     *          switch component descriptor.
     */
    public void setDefaultDescriptor(Ed defaultDescriptor) throws XMLException {
        if(this.defaultDescriptor != null){
            impl.notifyRemoved(NCLElementSets.DEFAULTDESCRIPTOR, (El) this.defaultDescriptor.getTarget());
            this.defaultDescriptor.clean();
        }
        
        this.defaultDescriptor = defaultDescriptor;
        
        if(this.defaultDescriptor != null){
            this.defaultDescriptor.setOwner((T) this);
            this.defaultDescriptor.setOwnerAtt(NCLElementAttributes.DEFAULTDESCRIPTOR);
            impl.notifyInserted(NCLElementSets.DEFAULTDESCRIPTOR, (El) this.defaultDescriptor.getTarget());
        }
    }


    /**
     * Returns the element representing the descriptor switch component descriptor
     * to be used when no rule is true or <i>null</i> if the attribute is not
     * defined.
     * 
     * @return 
     *          element representing a reference to a descriptor switch component
     *          descriptor or <i>null</i> if the attribute is not defined.
     */
    public Ed getDefaultDescriptor() {
        return defaultDescriptor;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
