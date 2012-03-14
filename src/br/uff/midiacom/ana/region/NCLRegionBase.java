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
package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLRegionBase<T extends NCLRegionBase,
                           P extends NCLElement,
                           I extends NCLElementImpl,
                           Er extends NCLRegion,
                           Ei extends NCLImport,
                           Rr extends RegionReference>
        extends NCLRegionBasePrototype<T, P, I, Er, Ei, Rr>
        implements NCLIdentifiableElement<T, P> {


    public NCLRegionBase() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
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
    
    
    protected String parseDevice() {
        String aux = getDevice();
        if(aux != null)
            return " device='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseParentRegion() {
        Rr aux = getParentRegion();
        if(aux != null)
            return " region='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseRegions(int ident) {
        if(!hasRegion())
            return "";
        
        String content = "";
        for(Er aux : regions)
            content += aux.parse(ident);
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var, ch_name;
        NodeList nl;

        try{
            // set the id (optional)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);

            // set the device (optional)
            att_name = NCLElementAttributes.DEVICE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDevice(att_var);
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
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("RegionBase" + aux + " > " + ex.getMessage());
        }

        try{
            // set the region (optional)
            att_name = NCLElementAttributes.REGION.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setParentRegion(createRegionRef(findRegion(att_var)));
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
    
    
    /**
     * Searches for a region inside a regionBase and its descendants.
     * 
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public Er findRegion(String id) throws XMLException {
        Er result;
        
        for(Er region : regions){
            result = (Er) region.findRegion(id);
            if(result != null)
                return result;
        }
        
        return null;
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
