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
package br.uff.midiacom.ana.datatype.ncl.interfaces;

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;


/**
 * Class that represents a switch port element. A switch port maps alternative
 * interface points of the nodes inside a switch element.
 * 
 * <br/>
 * 
 * When a node or a node interface point is mapped by a port, every action over
 * the port is reflected to the node or node interface point mapped by it. If the
 * port interface attribute is not defined, it is assumed that the port maps
 * the whole node.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the switch port element. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>mapping</i> - element that maps a switch inner node interface point.
 *                       The switch port must have at least one mapping element.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Em>
 * @param <Ei> 
 */
public abstract class NCLSwitchPortPrototype<T extends NCLSwitchPortPrototype,
                                             P extends NCLElement,
                                             I extends NCLElementImpl,
                                             Em extends NCLMappingPrototype,
                                             Ei extends NCLInterface>
        extends NCLIdentifiableElementPrototype<Ei, P, I>
        implements NCLInterface<Ei, P> {

    protected ElementList<Em, T> mappings;
    
    protected ItemList<ReferenceType> references;


    /**
     * Switch port element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSwitchPortPrototype() throws XMLException {
        super();
        mappings = new ElementList<Em, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Adds an element that maps a switch inner node interface point to the
     * switch port. The switch port must have at least one mapping element.
     * 
     * @param mapping
     *          element that maps a switch inner node interface point.
     * @return
     *          true if the mapping was added.
     * @throws XMLException 
     *          if the element representing the mapping is null.
     */
    public boolean addMapping(Em mapping) throws XMLException {
        if(mappings.add(mapping, (T) this)){
            impl.notifyInserted(NCLElementSets.MAPPINGS, mapping);
            return true;
        }
        return false;
    }


    /**
     * Removes an element that maps a switch inner node interface point of the
     * switch port. The switch port must have at least one mapping element.
     * 
     * @param mapping
     *          element that maps a switch inner node interface point.
     * @return
     *          true if the mapping was removed.
     * @throws XMLException 
     *          if the element representing the mapping is null.
     */
    public boolean removeMapping(Em mapping) throws XMLException {
        if(mappings.remove(mapping)){
            impl.notifyRemoved(NCLElementSets.MAPPINGS, mapping);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the switch port has a specific element that maps a switch
     * inner node interface point of the switch port. The switch port must have
     * at least one mapping element.
     * 
     * @param mapping
     *          element that maps a switch inner node interface point.
     * @return
     *          true if the switch port has the mapping element.
     * @throws XMLException 
     *          if the element representing the mapping is null.
     */
    public boolean hasMapping(Em mapping) throws XMLException {
        return mappings.contains(mapping);
    }


    /**
     * Verifies if the switch port has at least one element that maps a switch
     * inner node interface point of the switch port. The switch port must have
     * at least one mapping element.
     * 
     * @return 
     *          true if the switch port has at least one mapping.
     */
    public boolean hasMapping() {
        return !mappings.isEmpty();
    }


    /**
     * Returns the list of mapping that a switch port have. The switch port must have
     * at least one mapping element.
     * 
     * @return 
     *          element list with all mappings.
     */
    public ElementList<Em, T> getMappings() {
        return mappings;
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
