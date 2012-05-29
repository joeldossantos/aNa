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
package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


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
public class NCLRegionBase<T extends NCLRegionBase,
                           P extends NCLElement,
                           I extends NCLElementImpl,
                           Er extends NCLRegion,
                           Ei extends NCLImport,
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
    public NCLRegionBase() throws XMLException {
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


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<regionBase";
        content += parseAttributes();
        
        if(hasRegion() || hasImportBase()) {
            content += ">\n";

            content += parseElements(ident + 1);
            
            content += space + "</regionBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
            loadDevice(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("RegionBase" + aux + ":\n" + ex.getMessage());
        }
        
        try{
            loadRegions(element);
            loadImportBases(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("RegionBase" + aux + " > " + ex.getMessage());
        }

        try{
            loadParentRegion(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("RegionBase" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseDevice();
        content += parseParentRegion();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportBases(ident);
        content += parseRegions(ident);
        
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
    
    
    protected String parseDevice() {
        String aux = getDevice();
        if(aux != null)
            return " device='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadDevice(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the device (optional)
        att_name = NCLElementAttributes.DEVICE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDevice(att_var);
    }
    
    
    protected String parseParentRegion() {
        Rr aux = getParentRegion();
        if(aux != null)
            return " region='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadParentRegion(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setParentRegion((Rr) findRegion(att_var));
    }
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadImportBases(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the import child nodes
        ch_name = NCLElementAttributes.IMPORTBASE.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ei inst = createImportBase();
            addImportBase(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseRegions(int ident) {
        if(!hasRegion())
            return "";
        
        String content = "";
        for(Er aux : regions)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadRegions(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the region child nodes
        ch_name = NCLElementAttributes.REGION.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            if(!el.getParentNode().equals(element))
                continue;

            Er inst = createRegion();
            addRegion(inst);
            inst.load(el);
        }
    }
    
    
    /**
     * Searches for a region inside a regionBase and its descendants.
     * 
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public RegionReference findRegion(String id) throws XMLException {
        Er result;
        
        if(!id.contains("#")){
            for(Er region : regions){
                result = (Er) region.findRegion(id);
                if(result != null)
                    return new RegionReference(result, NCLElementAttributes.ID);
            }   
        }
        else{
            int index = id.indexOf("#");
            String alias = id.substring(0, index);
            id = id.substring(index + 1);
            
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    RegionReference ref = findRegionReference(d, id);
                    return new RegionReference(imp, (Er) ref.getTarget(), (NCLElementAttributes) ref.getTargetAtt());
                }
            }
            
            NCLImportedDocumentBase ib = (NCLImportedDocumentBase) ((NCLHead) getParent()).getImportedDocumentBase();
            for(Ei imp : (ElementList<Ei, NCLImportedDocumentBase>) ib.getImportNCLs()){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    RegionReference ref = findRegionReference(d, id);
                    return new RegionReference(imp, (Er) ref.getTarget(), (NCLElementAttributes) ref.getTargetAtt());
                }
            }
        }
        
        
        return null;
    }
    
    
    protected RegionReference findRegionReference(NCLDoc doc, String id) throws XMLException {
        RegionReference result = null;
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");

        if(!head.hasRegionBase())
            throw new NCLParsingException("Could not find regionBase element");
        
        IdentifiableElementList<NCLRegionBase, NCLHead> list = head.getRegionBases();

        for(NCLRegionBase base : list){
            result = base.findRegion(id);
            if(result != null)
                break;
        }

        if(result == null)
            throw new NCLParsingException("Could not find region in regionBase with id: " + id);
        
        return result;
    }


    /**
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected Ei createImportBase() throws XMLException {
        return (Ei) new NCLImport(NCLImportType.BASE);
    }


    /**
     * Function to create the child element <i>region</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>region</i>.
     */
    protected Er createRegion() throws XMLException {
        return (Er) new NCLRegion();
    }


    /**
     * Function to create a reference to a region.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a region.
     */
    protected Rr createRegionRef(Er ref) throws XMLException {
        return (Rr) new RegionReference(ref, NCLElementAttributes.ID);
    }
}
