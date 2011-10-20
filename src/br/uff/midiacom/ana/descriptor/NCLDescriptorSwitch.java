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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorSwitchPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLDescriptorSwitch<T extends NCLDescriptorSwitch, P extends NCLElement, I extends NCLElementImpl, El extends NCLLayoutDescriptor, Eb extends NCLDescriptorBindRule>
        extends NCLDescriptorSwitchPrototype<T, P, I, El, Eb> implements NCLLayoutDescriptor<El, P> {


    public NCLDescriptorSwitch(String id) throws XMLException {
        super(id);
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
    public boolean addBind(Eb bind) throws XMLException {
        if(super.addBind(bind)){
            impl.notifyInserted(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeBind(Eb bind) throws XMLException {
        if(super.removeBind(bind)){
            impl.notifyRemoved(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    @Override
    public void setDefaultDescriptor(El defaultDescriptor) {
        El aux = this.defaultDescriptor;
        super.setDefaultDescriptor(defaultDescriptor);
        impl.notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, defaultDescriptor);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("descriptorSwitch")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("bindRule")){
//                B child = createBindRule();
//                child.startElement(uri, localName, qName, attributes);
//                addBind(child);
//            }
//            else if(localName.equals("descriptor")){
//                D child = createDescriptor();
//                child.startElement(uri, localName, qName, attributes);
//                addDescriptor(child);
//            }
//            else if(localName.equals("defaultDescriptor")){
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("descriptor"))
//                        setDefaultDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endElement(String uri, String localName, String qName) {
//        if(localName.equals("descriptorSwitch"))
//            super.endElement(uri, localName, qName);
//    }


//    @Override
//    public void endDocument() {
//        if(getDefaultDescriptor() != null)
//            defaultDescriptorReference();
//
//        if(hasBind()){
//            for(B bind : binds){
//                bind.endDocument();
//                addWarning(bind.getWarnings());
//                addError(bind.getErrors());
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


//    private void defaultDescriptorReference() {
//        //Search for a component node in its parent
//        for(D descriptor : descriptors){
//            if(descriptor.getId().equals(getDefaultDescriptor().getId())){
//                setDefaultDescriptor(descriptor);
//                return;
//            }
//        }
//
//        addWarning("Could not find descriptor in descriptorSwitch with id: " + getDefaultDescriptor().getId());
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>bindRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindRule</i>.
     */
    protected NCLDescriptorBindRule createBindRule() throws XMLException {
        return new NCLDescriptorBindRule();
    }


    /**
     * Function to create the child element <i>descriptor</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptor</i>.
     */
    protected NCLDescriptor createDescriptor(String id) throws XMLException {
        return new NCLDescriptor(id);
    }
}
