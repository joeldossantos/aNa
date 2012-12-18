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
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLBase;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.enums.NCLDevice;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
public class NCLRegionBase<T extends NCLElement,
                           Er extends NCLRegion,
                           Ei extends NCLImportBase,
                           R extends ExternalReferenceType>
        extends NCLBase<T, Ei> {

    protected NCLDevice device;
    protected Object parent_region;
    protected ElementList<Er> regions;


    /**
     * Base of regions constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLRegionBase() {
        super();
        regions = new ElementList<Er>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Er aux : regions) {
            aux.setDoc(doc);
        }
    }


    /**
     * Sets the class of devices that use the base of regions. This attribute is
     * optional. Set the device to <i>null</i> to erase a device already defined.
     * 
     * <br/>
     * 
     * The possible device values are defined in the enumeration <i>NCLDevice</i>.
     * 
     * @param device
     *          element from enumeration <i>NCLDevice</i> representing the class
     *          of devices that use the base of regions or <i>null</i> to erase
     *          the device already defined.
     * @throws XMLException 
     *          if an error occur while notifying the modification.
     */
    public void setDevice(NCLDevice device) throws XMLException {
        NCLDevice aux = this.device;
        this.device = device;
        notifyAltered(NCLElementAttributes.DEVICE, aux, device);
    }


    /**
     * Returns the class of devices that use the base of regions or <i>null</i>
     * if the attribute is not defined.
     * 
     * @return 
     *          element from enumeration <i>NCLDevice</i> representing the class
     *          of devices that use the base of regions or <i>null</i> if the
     *          attribute is not defined.
     */
    public NCLDevice getDevice() {
        return device;
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
    public void setParentRegion(Object region) throws XMLException {
        Object aux = this.parent_region;
        
        if(region instanceof NCLRegion)
            ((Er) region).addReference(this);
        else if(region instanceof ExternalReferenceType){
            ((R) region).getTarget().addReference(this);
            ((R) region).getAlias().addReference(this);
        }
        
        this.parent_region = region;
        notifyAltered(NCLElementAttributes.REGION, aux, region);
        
        if(aux != null){
            if(aux instanceof NCLRegion)
                ((Er) aux).removeReference(this);
            else{
                ((R) aux).getTarget().removeReference(this);
                ((R) aux).getAlias().removeReference(this);
            }
        }
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
    public Object getParentRegion() {
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
        if(regions.add(region)){
            notifyInserted((T) region);
            region.setParent(this);
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
        if(!region.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(regions.remove(region)){
            notifyRemoved((T) region);
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
        Er aux = regions.get(id);
        return removeRegion(aux);
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
    public ElementList<Er> getRegions() {
        return regions;
    }


    /**
     * Returns the region with a specific id. The base of regions can have none
     * or several regions.
     * 
     * @param id
     *          string representing the id of the element representing a region.
     * @return 
     *          element representing a region.
     */
    public Er getRegion(String id) throws XMLException {
        return regions.get(id);
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLRegionBase))
            return false;
        
        boolean result = true;
        ElementList<Er> otherreg = ((NCLRegionBase) other).getRegions();
        
        result &= super.compareImports((NCLBase) other);
        
        result &= regions.size() == otherreg.size();
        for (Er reg : regions) {
            try {
                result &= otherreg.contains(reg);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        if(device != null)
            result &= getDevice().compare(((NCLRegionBase) other).getDevice());
        
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


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;
        
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
            
            // create the importBases
            nl = element.getElementsByTagName(NCLElementAttributes.IMPORTBASE.toString());
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    loadImportBases(el);
                }
            }
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
        NCLDevice aux = getDevice();
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
            setDevice(new NCLDevice(att_var));
    }
    
    
    protected String parseParentRegion() {
        Object aux = getParentRegion();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLRegion)
            return " region='" + ((Er) aux).getId() + "'";
        else
            return " region='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadParentRegion(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            String[] reg = adjustReference(att_var);
            setParentRegion(findRegion(reg[0], reg[1]));
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
     * @param alias
     *          alias of the importBase the imports the region.
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public Object findRegion(String alias, String id) throws XMLException {
        Object result;
        
        if(alias == null){
            for(Er region : regions){
                result = region.findRegion(id);
                if(result != null)
                    return result;
            }   
        }
        else{
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = d.getHead().findRegion(imp.getBaseId(), null, id);
                    if(ref instanceof NCLRegion)
                        return createExternalRef(imp, (Er) ref);
                    else
                        return createExternalRef(imp, (Er) ((R) ref).getTarget());
                }
            }
        }
        
        return null;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(parent_region != null)
            ((NCLRegion)parent_region).removeReference(this);
        
        device = null;
        parent_region = null;
        
        for(Er r : regions)
            r.clean();
        
//        protected NCLDevice device;
//        protected Object parent_region;
//        protected ElementList<Er> regions;
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
    protected R createExternalRef(Ei imp, Er ref) throws XMLException {
        return (R) new ExternalReferenceType(imp, ref);
    }
}
