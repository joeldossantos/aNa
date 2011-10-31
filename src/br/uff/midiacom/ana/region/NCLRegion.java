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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.RelativeType;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLRegion<T extends NCLRegion, P extends NCLElement, I extends NCLElementImpl>
        extends NCLRegionPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {


    public NCLRegion(String id) throws XMLException {
        super(id);
    }


    public NCLRegion() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setTitle(String title) throws XMLException {
        StringType aux = this.title;
        super.setTitle(title);
        impl.notifyAltered(NCLElementAttributes.TITLE, aux, title);
    }


    @Override
    public void setLeft(RelativeType left) {
        RelativeType aux = this.left;
        super.setLeft(left);
        impl.notifyAltered(NCLElementAttributes.LEFT, aux, left);
    }


    @Override
    public void setRight(RelativeType right) {
        RelativeType aux = this.right;
        super.setRight(right);
        impl.notifyAltered(NCLElementAttributes.RIGHT, aux, right);
    }


    @Override
    public void setTop(RelativeType top) {
        RelativeType aux = this.top;
        super.setTop(top);
        impl.notifyAltered(NCLElementAttributes.TOP, aux, top);
    }


    @Override
    public void setBottom(RelativeType bottom) {
        RelativeType aux = this.bottom;
        super.setBottom(bottom);
        impl.notifyAltered(NCLElementAttributes.BOTTOM, aux, bottom);
    }


    @Override
    public void setHeight(RelativeType height) {
        RelativeType aux = this.height;
        super.setHeight(height);
        impl.notifyAltered(NCLElementAttributes.HEIGHT, aux, height);
    }


    @Override
    public void setWidth(RelativeType width) {
        RelativeType aux = this.width;
        super.setWidth(width);
        impl.notifyAltered(NCLElementAttributes.WIDTH, aux, width);
    }


    @Override
    public void setzIndex(Integer zIndex) {
        Integer aux = this.zIndex;
        super.setzIndex(zIndex);
        impl.notifyAltered(NCLElementAttributes.ZINDEX, aux, zIndex);
    }

    
    @Override
    public boolean addRegion(T region) throws XMLException {
        if(super.addRegion(region)){
            impl.notifyInserted(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRegion(String id) throws XMLException {
        if(super.removeRegion(id)){
            impl.notifyRemoved(NCLElementSets.REGIONS, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRegion(T region) throws XMLException {
        if(super.removeRegion(region)){
            impl.notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var, ch_name;
        NodeList nl;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the title (optional)
            att_name = NCLElementAttributes.TITLE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setTitle(att_var);

            // set the left (optional)
            att_name = NCLElementAttributes.LEFT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setLeft(new RelativeType(att_var));

            // set the right (optional)
            att_name = NCLElementAttributes.RIGHT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setRight(new RelativeType(att_var));

            // set the top (optional)
            att_name = NCLElementAttributes.TOP.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setTop(new RelativeType(att_var));

            // set the bottom (optional)
            att_name = NCLElementAttributes.BOTTOM.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setBottom(new RelativeType(att_var));

            // set the height (optional)
            att_name = NCLElementAttributes.HEIGHT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setHeight(new RelativeType(att_var));

            // set the width (optional)
            att_name = NCLElementAttributes.WIDTH.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setWidth(new RelativeType(att_var));

            // set the zIndex (optional)
            att_name = NCLElementAttributes.ZINDEX.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setzIndex(new Integer(att_var));
                }catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Region" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            ch_name = NCLElementAttributes.REGION.toString();
            nl = element.getElementsByTagName(ch_name);
            for(int i=0; i < nl.getLength(); i++){
                Element el = (Element) nl.item(i);
                if(!el.getParentNode().equals(element))
                    continue;

                T inst = createRegion();
                addRegion(inst);
                inst.load(el);
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Region" + aux + " > " + ex.getMessage());
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
    
    
    /**
     * Searches for a region inside a region and its descendants.
     * 
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public T findRegion(String id) throws XMLException {
        T result;
        
        if(getId().equals(id))
            return (T) this;
        
        for(T region : regions){
            result = (T) region.findRegion(id);
            if(result != null)
                return result;
        }
        
        return null;
    }


    /**
     * Function to create the child element <i>region</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>region</i>.
     */
    protected T createRegion() throws XMLException {
        return (T) new NCLRegion();
    }
}
