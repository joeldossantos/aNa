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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLDescriptorBase<T extends NCLDescriptorBase, P extends NCLElement, I extends NCLElementImpl, El extends NCLLayoutDescriptor, Ei extends NCLImport>
        extends NCLDescriptorBasePrototype<T, P, I, El, Ei> implements NCLIdentifiableElement<T, P> {


    public NCLDescriptorBase() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public boolean addDescriptor(El descriptor) throws XMLException {
        if(super.addDescriptor(descriptor)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeDescriptor(String id) throws XMLException {
        if(super.removeDescriptor(id)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, id);
            return true;
        }
        return false;
    }
    

    @Override
    public boolean removeDescriptor(El descriptor) throws XMLException {
        if(super.removeDescriptor(descriptor)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, descriptor);
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
//            if(localName.equals("descriptorBase")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("importBase")){
//                I child = createImportBase();
//                child.startElement(uri, localName, qName, attributes);
//                addImportBase(child);
//            }
//            else if(localName.equals("descriptor")){
//                D child = createDescriptor();
//                child.startElement(uri, localName, qName, attributes);
//                addDescriptor(child);
//            }
//            else if(localName.equals("descriptorSwitch")){
//                D child = createDescriptorSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addDescriptor(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(hasImportBase()){
//            for(I imp : imports){
//                imp.endDocument();
//                addWarning(imp.getWarnings());
//                addError(imp.getErrors());
//            }
//        }
//        if(hasDescriptor()){
//            for(D descriptor : descriptors){
//                descriptor.endDocument();
//                addWarning(descriptor.getWarnings());
//                addError(descriptor.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException {
        NodeList nl;

        // create the child nodes
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                //create the descriptors
                if(el.getTagName().equals(NCLElementSets.DESCRIPTORS.toString()))
                    addDescriptor(createDescriptor(el));

                // create the importBases
                if(el.getTagName().equals(NCLElementSets.IMPORTS.toString()))
                    addImportBase(createImportBase(el));
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
     * Function to create the child element <i>descriptor</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptor</i>.
     */
    protected El createDescriptor(Element element) throws XMLException {
        return (El) new NCLDescriptor(element);
    }


    /**
     * Function to create the child element <i>descriptorSwitch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorSwitch</i>.
     */
    protected El createDescriptorSwitch(String id) throws XMLException {
        return (El) new NCLDescriptorSwitch(id);
    }
}
