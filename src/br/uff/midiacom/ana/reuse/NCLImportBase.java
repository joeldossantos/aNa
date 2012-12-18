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
package br.uff.midiacom.ana.reuse;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.util.exception.XMLException;
import org.w3c.dom.Element;


/**
 * Class that represents an element used to import a base defined in an external
 * document (importBase).
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>alias</i> - name used to refer to the document referred in the attribute
 *                     documentURI. This attribute is required.</li>
 *  <li><i>documentURI</i> - URI of the document to be imported (if it is an
 *                           importNCL element) or the document that will have
 *                           its base imported (if it is an importBase element).
 *                           This attribute is required.</li>
 *  <li><i>region</i> - region to become the parent of the regions imported by a
 *                      region base. This attribute is optional.</li>
 * </ul>
 * 
 * The alias attribute has to be unique in a document. An importing is transitive,
 * that is, if base A imports base B and it imports base C, then base A imports
 * base C. However, the alias defined to base C inside base B is not considered
 * by base A.
 * 
 * <br/>
 * 
 * When importing a base of descriptors, if the document that defines the base
 * of descriptors also defines a base of regions and a base of rules, both are
 * also imported.
 * 
 * <br/>
 * 
 * When importing a base of regions, the import element can define a region
 * attribute. Every regions defined in the base of regions imported are considered
 * as children of that region.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Er>
 * @param <Ed> 
 */
public class NCLImportBase<T extends NCLElement,
                           Er extends NCLRegion,
                           Ed extends NCLDoc>
        extends NCLImport<T, Ed>
        implements NCLElement<T> {

    protected Er region;
    protected String baseId;


    /**
     * Import base element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLImportBase() {
        super();
    }


    /**
     * Set the region that will have the imported regions as children. This
     * attribute is optional an is only used by an importBase element that
     * imports a base of regions. The region must refer to a region defined in
     * the base of regions parent of the importBase element. Set the region to
     * <i>null</i> to erase the region already defined.
     * 
     * @param region
     *          element representing the referred region or <i>null</i> to erase
     *          a region already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the region.
     */
    public void setRegion(Er region) throws XMLException {
        Er aux = this.region;
        // Set the new region
        this.region = region;
        // If the region is not null, set the reference owner
        if(this.region != null)
            this.region.addReference(this);
        
        notifyAltered(NCLElementAttributes.REGION, aux, region);
        if(aux != null)
            aux.removeReference(this);
    }


    /**
     * Returns the region that will have the imported regions as children or
     * <i>null</i> if no region is defined. This attribute is only used by an
     * importBase element that imports a base of regions. The region must refer
     * to a region defined in the base of regions parent of the importBase element.
     * 
     * @return
     *          element representing the referred region or <i>null</i> to erase
     *          a region already defined.
     */
    public Er getRegion() {
        return region;
    }
    
    
    /**
     * Sets the id of the base in the document to be imported. The baseId is
     * optional, set the baseId to <i>null</i> to erase a baseId already
     * defined.
     * 
     * @param baseId
     *          string representing the id of the base to be imported or <i>null</i>
     *          to erase a baseId already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setBaseId(String baseId) throws XMLException {
        if(baseId != null && "".equals(baseId.trim()))
            throw new XMLException("Empty baseId String.");
        
        this.baseId = baseId;
    }
    
    
    /**
     * Returns the id of the base in the document to be imported or <i>null</i>
     * if the attribute is not defined.
     * 
     * @return
     *          string representing the id of the base to be imported or <i>null</i>
     *          if the attribute is not defined.
     */
    public String getBaseId() {
        return baseId;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLImportBase))
            return false;
        
        boolean result = true;
        Er aux;
        
        result &= super.compare(other);
        
        if((aux = getRegion()) != null)
            result &= aux.compare((T) ((NCLImportBase) other).getRegion());
        return result;
    }
    
    
    @Override
    protected String parseRegion() {
        Er aux = getRegion();
        if(aux != null)
            return " region='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    @Override
    protected void loadRegion(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            Ed d = (Ed) getDoc();
            String[] reg = adjustReference(att_var);
            setRegion((Er) d.getHead().findRegion(null, reg[0], reg[1]));
        }
    }
    
    
    @Override
    protected String parseBaseId() {
        String aux = getBaseId();
        if(aux != null)
            return " baseId='" + aux + "'";
        else
            return "";
    }
    
    
    @Override
    protected void loadBaseId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.BASEID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setBaseId(att_var);
        }
    }
    
    
    @Override
    protected String getType() {
        return "importBase";
    }

    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(region != null)
            region.removeReference(this);
        
        region = null;
        baseId = null;
    }
}
