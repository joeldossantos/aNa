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

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


/**
 * Class that represents a base of descriptors.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of descriptors. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importBase</i> - element that imports a descriptor base defined in another
 *                          NCL document. The base can have none or several import
 *                          elements.</li>
 *  <li><i>descriptor</i> - element representing a descriptor inside the base. The
 *                          base can have none or several descriptor elements.</li>
 *  <li><i>descriptorSwitch</i> - element representing a descriptor switch inside
 *                                the base. The base can have none or several
 *                                descriptor switch elements.</li>
 * </ul>
 * 
 * Note that the base of descriptors must have at least one child element, which
 * can be a import, a descriptor or a descriptor switch.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <El>
 * @param <Ei> 
 */
public abstract class NCLDescriptorBasePrototype<T extends NCLDescriptorBasePrototype,
                                                 P extends NCLElement,
                                                 I extends NCLElementImpl,
                                                 El extends NCLLayoutDescriptor,
                                                 Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected IdentifiableElementList<El, T> descriptors;
    protected ElementList<Ei, T> imports;


    /**
     * Base of descriptors constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorBasePrototype() throws XMLException {
        super();
        descriptors = new IdentifiableElementList<El, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Adds a descriptor or a descriptor switch to the base of descriptors. The
     * base of descriptors can have none or several descriptor or descriptor
     * switch elements.
     * 
     * @param descriptor
     *          element representing a descriptor or a descriptor switch.
     * @return
     *          true if the element representing a descriptor or descriptor
     *          switch was added.
     * @throws XMLException 
     *          if the element representing the descriptor or descriptor switch
     *          is null.
     */
    public boolean addDescriptor(El descriptor) throws XMLException {
        if(descriptors.add(descriptor, (T) this)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor or a descriptor switch of the base of descriptors.
     * The base of descriptors can have none or several descriptor or descriptor
     * switch elements.
     * 
     * @param descriptor
     *          element representing a descriptor or a descriptor switch.
     * @return
     *          true if the element representing a descriptor or descriptor
     *          switch was removed.
     * @throws XMLException 
     *          if the element representing the descriptor or descriptor switch
     *          is null.
     */
    public boolean removeDescriptor(El descriptor) throws XMLException {
        if(descriptors.remove(descriptor)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor or a descriptor switch of the base of descriptors.
     * The base of descriptors can have none or several descriptor or descriptor
     * switch elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor or a descriptor switch.
     * @return
     *          true if the descriptor or descriptor switch was removed.
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
     * Verifies if the base of descriptors has a specific element representing
     * a descriptor or descriptor switch. The base of descriptors can have none
     * or several descriptor or descriptor switch elements.
     * 
     * @param descriptor
     *          element representing a descriptor or descriptor switch.
     * @return
     *          true if the base of descriptors has the descriptor or descriptor
     *          switch element.
     * @throws XMLException 
     *          if the element representing the descriptor or descriptor switch
     *          is null.
     */
    public boolean hasDescriptor(El descriptor) throws XMLException {
        return descriptors.contains(descriptor);
    }


    /**
     * Verifies if the base of descriptors has a descriptor or descriptor switch
     * with a specific id. The base of descriptors can have none or several
     * descriptor or descriptor switch elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor or descriptor switch.
     * @return
     *          true if the base of descriptors has the descriptor or descriptor
     *          switch element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasDescriptor(String id) throws XMLException {
        return descriptors.get(id) != null;
    }


    /**
     * Verifies if the base of descriptors has at least one descriptor or
     * descriptor switch. The base of descriptors can have none or several
     * descriptor or descriptor switch elements.
     * 
     * @return 
     *          true if the base of descriptors has at least one descriptor or
     *          descriptor switch.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Returns the list of descriptors and descriptor switches that a base of
     * descriptors have. The base of descriptors can have none or several
     * descriptor or descriptor switch elements.
     * 
     * @return 
     *          element list with all descriptors and descriptor switches.
     */
    public IdentifiableElementList<El, T> getDescriptors() {
        return descriptors;
    }


    /**
     * Adds an element that imports a base of descriptors defined in another NCL
     * document to the base of descriptors. The base can have none or several
     * import elements.
     * 
     * @param importBase
     *          element that imports a base of descriptors defined in another NCL
     *          document.
     * @return
     *          true if the import element was added.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(imports.add(importBase, (T) this)){
            impl.notifyInserted(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Removes an element that imports a base of descriptors defined in another
     * NCL document of the base of descriptors. The base can have none or several
     * import elements.
     * 
     * @param importBase
     *          element that imports a base of descriptors defined in another NCL
     *          document.
     * @return
     *          true if the import element was removed.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(imports.remove(importBase)){
            impl.notifyRemoved(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of descriptors has a specific element that imports a
     * base of descriptors defined in another NCL document. The base can have
     * none or several import elements.
     * 
     * @param importBase
     *          element that imports a base of descriptors defined in another NCL
     *          document.
     * @return
     *          true if the base of descriptors has the import element.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifies if the base of descriptors has at least one element that imports
     * a base of descriptors defined in another NCL document. The base can have
     * none or several import elements.
     * 
     * @return 
     *          true if the base of descriptors has at least import element.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Returns the list of elements that imports a base of descriptors defined in
     * another NCL document. The base can have none or several import elements.
     * 
     * @return 
     *          element list with all import elements.
     */
    public ElementList<Ei, T> getImportBases() {
        return imports;
    }
}
