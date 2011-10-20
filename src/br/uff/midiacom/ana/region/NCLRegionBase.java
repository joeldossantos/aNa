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
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLRegionBase<T extends NCLRegionBase, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegion, Ei extends NCLImport>
        extends NCLRegionBasePrototype<T, P, I, Er, Ei> implements NCLIdentifiableElement<T, P> {


    public NCLRegionBase() throws XMLException {
        super();
    }


    public NCLRegionBase(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setDevice(String device) throws XMLException {
        StringType aux = this.device;
        super.setDevice(device);
        impl.notifyAltered(NCLElementAttributes.DEVICE, aux, device);
    }


    @Override
    public void setParentRegion(Er region) {
        super.setParentRegion(region);
    }


    @Override
    public boolean addRegion(Er region) throws XMLException {
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
    public boolean removeRegion(Er region) throws XMLException {
        if(super.removeRegion(region)){
            impl.notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    @Override
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(super.addImportBase(importBase)){
            impl.notifyInserted(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(super.removeImportBase(importBase)){
            impl.notifyRemoved(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("regionBase")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("device"))
//                        setDevice(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("region"))
//                        setParentRegion((R) new NCLRegion(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("importBase")){
//                I child = createImportBase();
//                child.startElement(uri, localName, qName, attributes);
//                addImportBase(child);
//            }
//            else if(localName.equals("region")){
//                R child = createRegion();
//                child.startElement(uri, localName, qName, attributes);
//                addRegion(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }

   
//    private R findRegion(Set<R> regions) {
//        for(R reg : regions){
//            if(reg.hasRegion()){
//                NCLRegion r = findRegion(reg.getRegions());
//                if(r != null)
//                    return (R) r;
//            }
//            else{
//                if(reg.getId().equals(getParentRegion().getId()))
//                    return (R) reg;
//            }
//        }
//
//        addWarning("Could not find region in regionBase with id: " + getParentRegion().getId());
//        return null;
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;
        NodeList nl;

        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);

        // set the device (optional)
        att_name = NCLElementAttributes.DEVICE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDevice(att_var);

        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setRegion(); //@todo: tem que usar o método de busca pelo id da regiao

        // create the child nodes
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                //create the imports
                if(el.getTagName().equals(NCLElementAttributes.IMPORTBASE.toString()))
                    addImportBase(createImportBase(el));
                // create the regions
                if(el.getTagName().equals(NCLElementAttributes.REGION.toString()))
                    addRegion(createRegion(el));
            }
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected Ei createImportBase(Element element) throws XMLException {
        return (Ei) new NCLImport(NCLImportType.BASE, element);
    }


    /**
     * Function to create the child element <i>region</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>region</i>.
     */
    protected Er createRegion(Element element) throws XMLException {
        return (Er) new NCLRegion(element);
    }
}
