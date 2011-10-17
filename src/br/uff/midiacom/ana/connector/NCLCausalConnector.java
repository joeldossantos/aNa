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
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCausalConnectorPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLCausalConnector<T extends NCLCausalConnector, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCondition, Ea extends NCLAction, Ep extends NCLConnectorParam>
        extends NCLCausalConnectorPrototype<T, P, I, Ec, Ea, Ep> implements NCLIdentifiableElement<T, P> {


    public NCLCausalConnector(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }

    
    @Override
    public void setCondition(Ec condition) {
        Ec aux = this.condition;
        super.setCondition(condition);
        impl.notifyAltered(NCLElementAttributes.CONDITION, aux, condition);
    }
    
    
    @Override
    public void setAction(Ea action) {
        Ea aux = this.action;
        super.setAction(action);
        impl.notifyAltered(NCLElementAttributes.ACTION, aux, action);
    }
    
        
    @Override
    public boolean addConnectorParam(Ep param) throws XMLException {
        if(super.addConnectorParam(param)){
            impl.notifyInserted(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
        }
        return false;
    }

    
    @Override
    public boolean removeConnectorParam(String name) throws XMLException {
        if(super.removeConnectorParam(name)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, name);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeConnectorParam(Ep param) throws XMLException {
        if(super.removeConnectorParam(param)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("causalConnector")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("connectorParam")){
//                P child = createConnectorParam();
//                child.startElement(uri, localName, qName, attributes);
//                addConnectorParam(child);
//            }
//            else if(localName.equals("simpleCondition")){
//                setCondition(createSimpleCondition());
//                getCondition().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("compoundCondition")){
//                setCondition(createCompoundCondition());
//                getCondition().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("simpleAction")){
//                setAction(createSimpleAction());
//                getAction().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("compoundAction")){
//                setAction(createCompoundAction());
//                getAction().startElement(uri, localName, qName, attributes);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(hasConnectorParam()){
//            for(P param : conn_params){
//                param.endDocument();
//                addWarning(param.getWarnings());
//                addError(param.getErrors());
//            }
//        }
//        if(getCondition() != null){
//            getCondition().endDocument();
//            addWarning(getCondition().getWarnings());
//            addError(getCondition().getErrors());
//        }
//        if(getAction() != null){
//            getAction().endDocument();
//            addWarning(getAction().getWarnings());
//            addError(getAction().getErrors());
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
     * Function to create the child element <i>connectorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connectorParam</i>.
     */
    protected NCLConnectorParam createConnectorParam(String name) throws XMLException {
        return new NCLConnectorParam(name);
    }


    /**
     * Function to create the child element <i>simpleCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleCondition</i>.
     */
    protected NCLSimpleCondition createSimpleCondition() throws XMLException {
        return new NCLSimpleCondition();
    }


    /**
     * Function to create the child element <i>compoundCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundCondition</i>.
     */
    protected NCLCompoundCondition createCompoundCondition() throws XMLException {
        return new NCLCompoundCondition();
    }


    /**
     * Function to create the child element <i>simpleAction</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleAction</i>.
     */
    protected NCLSimpleAction createSimpleAction() throws XMLException {
        return new NCLSimpleAction();
    }


    /**
     * Function to create the child element <i>compoundAction</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundAction</i>.
     */
    protected NCLCompoundAction createCompoundAction() throws XMLException {
        return new NCLCompoundAction();
    }
}
