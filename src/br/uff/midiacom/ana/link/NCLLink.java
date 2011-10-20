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
package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.datatype.ncl.link.NCLLinkPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLLink<T extends NCLLink, P extends NCLElement, I extends NCLElementImpl, Ep extends NCLParam, Eb extends NCLBind, Ec extends NCLCausalConnector>
        extends NCLLinkPrototype<T, P, I, Ep, Eb, Ec> implements NCLIdentifiableElement<T, P>{


    public NCLLink() throws XMLException {
        super();
    }


    public NCLLink(Element elem) throws XMLException {
        super();
        load(elem);
    }

    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setXconnector(Ec xconnector) {
        Ec aux = this.xconnector;
        super.setXconnector(xconnector);
        impl.notifyAltered(NCLElementAttributes.XCONNECTOR, aux, xconnector);
    }
    
    
    @Override
    public boolean addLinkParam(Ep param) throws XMLException {
        if(super.addLinkParam(param)){
            impl.notifyInserted(NCLElementSets.LINKPARAMS, param);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean removeLinkParam(Ep param) throws XMLException {
        if(super.removeLinkParam(param)){
            impl.notifyRemoved(NCLElementSets.LINKPARAMS, param);
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
    
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("link")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("xconnector"))
//                        setXconnector((C) new NCLCausalConnector(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("linkParam")){
//                P child = createLinkParam();
//                child.startElement(uri, localName, qName, attributes);
//                addLinkParam(child);
//            }
//            else if(localName.equals("bind")){
//                B child = createBind();
//                child.startElement(uri, localName, qName, attributes);
//                addBind(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getXconnector() != null)
//                connectorReference();
//        }
//
//        if(hasLinkParam()){
//            for(P param : linkParams){
//                param.endDocument();
//                addWarning(param.getWarnings());
//                addError(param.getErrors());
//            }
//        }
//        if(hasBind()){
//            for(B bind : binds){
//                bind.endDocument();
//                addWarning(bind.getWarnings());
//                addError(bind.getErrors());
//            }
//        }
//    }


//    private void connectorReference() {
//        //Search for the connector inside the base
//        Set<C> connectors = getConnectors();
//        if(connectors == null){
//            addWarning("Could not find connector in connectorBase with id: " + getXconnector().getId());
//            return;
//        }
//
//        for(C connector : connectors){
//            if(connector.getId().equals(getXconnector().getId())){
//                setXconnector(connector);
//                return;
//            }
//        }
//
//        addWarning("Could not find connector in connectorBase with id: " + getXconnector().getId());
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var, ch_name;
        int length;

        att_name = NCLElementAttributes.XCONNECTOR.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setXconnector(); // metodo de busca pelo id do connector

        ch_name = NCLElementSets.LINKPARAMS.toString();
        NodeList nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addLinkParam((Ep) new NCLParam((Element) nl.item(i), NCLParamInstance.LINKPARAM));

        ch_name = NCLElementSets.BINDS.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addBind((Eb) new NCLBind((Element) nl.item(i)));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>linkParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>linkParam</i>.
     */
    protected NCLParam createLinkParam() throws XMLException {
        return new NCLParam(NCLParamInstance.LINKPARAM);
    }


    /**
     * Function to create the child element <i>bind</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bind</i>.
     */
    protected NCLBind createBind() throws XMLException {
        return new NCLBind();
    }
}
