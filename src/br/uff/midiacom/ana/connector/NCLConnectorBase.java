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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLConnectorBase<T extends NCLConnectorBase, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCausalConnector, Ei extends NCLImport>
        extends NCLConnectorBasePrototype<T, P, I, Ec, Ei> implements NCLIdentifiableElement<T, P> {


    public NCLConnectorBase() throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public boolean addCausalConnector(Ec connector) throws XMLException {
        if(super.addCausalConnector(connector)){
            impl.notifyInserted(NCLElementSets.CONNECTORS, connector);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean removeCausalConnector(Ec connector) throws XMLException {
        if(super.removeCausalConnector(connector)){
            impl.notifyRemoved(NCLElementSets.CONNECTORS, connector);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeCausalConnector(String id) throws XMLException {
        if(super.removeCausalConnector(id)){
            impl.notifyRemoved(NCLElementSets.CONNECTORS, id);
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
//            if(localName.equals("connectorBase")){
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
//            else if(localName.equals("causalConnector")){
//                C child = createCausalConnector();
//                child.startElement(uri, localName, qName, attributes);
//                addCausalConnector(child);
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
//        if(hasCausalConnector()){
//            for(C connector : connectors){
//                connector.endDocument();
//                addWarning(connector.getWarnings());
//                addError(connector.getErrors());
//            }
//        }
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
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected NCLImport createImportBase() throws XMLException {
        return new NCLImport(NCLImportType.BASE);
    }


    /**
     * Function to create the child element <i>causalConnector</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>causalConnector</i>.
     */
    protected NCLCausalConnector createCausalConnector(String id) throws XMLException {
        return new NCLCausalConnector(id);
    }
}
