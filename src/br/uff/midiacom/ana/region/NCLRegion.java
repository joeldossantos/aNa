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


    public NCLRegion(Element elem) throws XMLException {
        super(elem.getAttribute(NCLElementAttributes.ID.toString()));
        load(elem);
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


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(!insideRegion){
//                cleanWarnings();
//                cleanErrors();
//                insideRegion = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("left")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setLeft(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("right")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setRight(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("top")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setTop(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("bottom")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setBottom(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("height")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setHeight(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("width")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setWidth(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("zIndex"))
//                        setzIndex(new Integer(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("title"))
//                        setTitle(attributes.getValue(i));
//                }
//            }
//            else{
//                // Region e um elemento interno
//                R child = createRegion();
//                child.startElement(uri, localName, qName, attributes);
//                addRegion(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(hasRegion()){
//            for(R region : regions){
//                region.endDocument();
//                addWarning(region.getWarnings());
//                addError(region.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException, NCLParsingException {
        String att_name, att_var, ch_name;
        int length;

        att_name = NCLElementAttributes.TITLE.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setTitle(att_var);

        att_name = NCLElementAttributes.LEFT.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setLeft(new RelativeType(att_var));

        att_name = NCLElementAttributes.RIGHT.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setRight(new RelativeType(att_var));

        att_name = NCLElementAttributes.TOP.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setTop(new RelativeType(att_var));

        att_name = NCLElementAttributes.BOTTOM.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setBottom(new RelativeType(att_var));

        att_name = NCLElementAttributes.HEIGHT.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setHeight(new RelativeType(att_var));

        att_name = NCLElementAttributes.WIDTH.toString();
        if((att_var = element.getAttribute(att_name)) != null)
            setWidth(new RelativeType(att_var));

        att_name = NCLElementAttributes.ZINDEX.toString();
        if((att_var = element.getAttribute(att_name)) != null){
            try{
                setzIndex(new Integer(att_var));
            }catch (Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value.");
            }
        }


        ch_name = NCLElementAttributes.REGION.toString();
        NodeList nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addRegion((T) new NCLRegion((Element) nl.item(i)));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>region</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>region</i>.
     */
    protected NCLRegion createRegion(String id) throws XMLException {
        return new NCLRegion(id);
    }
}
