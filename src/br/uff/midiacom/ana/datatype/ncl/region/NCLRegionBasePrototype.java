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
package br.uff.midiacom.ana.datatype.ncl.region;

import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents a base of regions.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of regions. This attribute is optional.</li>
 *  <li><i>device</i> - class of devices that use the base of regions. This
 *                      attribute is optional.</li>
 *  <li><i>region</i> - region to be used as parent of the regions defined in the
 *                      base of regions. This attribute is optional. See the
 *                      reference (ABNT NBR 15606-2) to more information.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importBase</i> - element that imports a region base defined in another
 *                          NCL document. The base can have none or several import
 *                          elements.</li>
 *  <li><i>region</i> - element representing a region inside the base. The base
 *                      can have none or several region elements.</li>
 * </ul>
 * 
 * Note that the base of regions must have at least one child element, which can
 * be a import or a region.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Er>
 * @param <Ei>
 * @param <Rr> 
 */
public abstract class NCLRegionBasePrototype<T extends NCLRegionBasePrototype,
                                             P extends NCLElement,
                                             I extends NCLElementImpl,
                                             Er extends NCLRegionPrototype,
                                             Ei extends NCLImportPrototype,
                                             Rr extends RegionReference>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected StringType device;
    protected Rr parent_region;
    protected IdentifiableElementList<Er, T> regions;
    protected ElementList<Ei, T> imports;


    /**
     * Base of regions constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLRegionBasePrototype() throws XMLException {
        super();
        regions = new IdentifiableElementList<Er, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Sets the class of devices that use the base of regions. This attribute is
     * optional. Set the device to <i>null</i> to erase a device already defined.
     * 
     * @param device
     *          string representing the class of devices that use the base of
     *          regions or <i>null</i> to erase the device already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setDevice(String device) throws XMLException {
        StringType aux = this.device;
        this.device = new StringType(device);
        impl.notifyAltered(NCLElementAttributes.DEVICE, aux, device);
    }


    /**
     * Returns the class of devices that use the base of regions or <i>null</i>
     * if the attribute is not defined.
     * 
     * @return 
     *          string representing the class of devices that use the base of
     *          regions or <i>null</i> if the attribute is not defined.
     */
    public String getDevice() {
        if(device != null)
            return device.getValue();
        else
            return null;
    }    


    /**
     * Sets the region to be used as parent of the regions defined in the base
     * of regions. This attribute is optional. Set the region to <i>null</i> to
     * erase a region already defined. This attribute must refer to a region
     * defined in a base of regions of an active class. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @param region
     *          element representing a reference to a region or <i>null</i> to
     *          erase a region already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the region.
     */
    public void setParentRegion(Rr region) throws XMLException {
        Rr aux = this.parent_region;
        
        this.parent_region = region;
        if(this.parent_region != null){
            this.parent_region.setOwner((T) this);
            this.parent_region.setOwnerAtt(NCLElementAttributes.REGION);
        }
        
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the region to be used as parent of the regions defined in the base
     * of regions or <i>null</i> if the attribute is not defined. This attribute
     * must refer to a region defined in a base of regions of an active class.
     * See the reference (ABNT NBR 15606-2) to more information.
     * 
     * @return 
     *          element representing a reference to a region or <i>null</i> if
     *          the attribute is not defined.
     */
    public Rr getParentRegion() {
        return parent_region;
    }


    /**
     * Adds a region to the base of regions. The base of regions can have none
     * or several regions.
     * 
     * @param region
     *          element representing a region.
     * @return
     *          true if the region was added.
     * @throws XMLException 
     *          if the element representing the region is null.
     */
    public boolean addRegion(Er region) throws XMLException {
        if(regions.add(region, (T) this)){
            impl.notifyInserted(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Removes a region of the base of regions. The base of regions can have
     * none or several regions.
     * 
     * @param region
     *          element representing a region.
     * @return
     *          true if the region was removed.
     * @throws XMLException 
     *          if the element representing the region is null.
     */
    public boolean removeRegion(Er region) throws XMLException {
        if(regions.remove(region)){
            impl.notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Removes a region of the base of regions. The base of regions can have
     * none or several regions.
     * 
     * @param id
     *          string representing the id of the element representing a region.
     * @return
     *          true if the region was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeRegion(String id) throws XMLException {
        if(regions.remove(id)){
            impl.notifyRemoved(NCLElementSets.REGIONS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of regions has a specific element representing
     * a region. The base of regions can have none or several regions.
     * 
     * @param region
     *          element representing a region.
     * @return
     *          true if the base of regions has the region element.
     * @throws XMLException 
     *          if the element representing the region is null.
     */
    public boolean hasRegion(Er region) throws XMLException {
        return regions.contains(region);        
    }


    /**
     * Verifies if the base of regions has a region with a specific id. The 
     * base of regions can have none or several regions.
     * 
     * @param id
     *          string representing the id of the element representing a region.
     * @return
     *          true if the base of regions has the region element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasRegion(String id) throws XMLException {
        return regions.get(id) != null;
    }


    /**
     * Verifies if the base of regions has at least one region. The base of
     * regions can have none or several regions.
     * 
     * @return 
     *          true if the base of regions has at least one region.
     */
    public boolean hasRegion() {
        return !regions.isEmpty();
    }


    /**
     * Returns the list of regions that a base of regions have. The base of
     * regions can have none or several regions.
     * 
     * @return 
     *          element list with all regions.
     */
    public IdentifiableElementList<Er, T> getRegions() {
        return regions;
    }


    /**
     * Adds an element that imports a base of regions defined in another NCL
     * document to the base of regions. The base can have none or several import
     * elements.
     * 
     * @param importBase
     *          element that imports a base of regions defined in another NCL
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
     * Removes an element that imports a base of regions defined in another NCL
     * document of the base of regions. The base can have none or several import
     * elements.
     * 
     * @param importBase
     *          element that imports a base of regions defined in another NCL
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
     * Verifies if the base of regions has a specific element that imports a base
     * of regions defined in another NCL document. The base can have none or
     * several import elements.
     * 
     * @param importBase
     *          element that imports a base of regions defined in another NCL
     *          document.
     * @return
     *          true if the base of regions has the import element.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifies if the base of regions has at least one element that imports a base
     * of regions defined in another NCL document. The base can have none or
     * several import elements.
     * 
     * @return 
     *          true if the base of regions has at least import element.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Returns the list of elements that imports a base of regions defined in
     * another NCL document. The base can have none or several import elements.
     * 
     * @return 
     *          element list with all import elements.
     */
    public ElementList<Ei, T> getImportBases() {
        return imports;
    }
}
