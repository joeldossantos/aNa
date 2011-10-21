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
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.auxiliar.IntegerParamType;
import br.uff.midiacom.ana.datatype.auxiliar.KeyParamType;
import br.uff.midiacom.ana.datatype.enums.NCLAttributeType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLAttributeAssessmentPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLAttributeAssessment<T extends NCLAttributeAssessment, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRole, Ep extends NCLConnectorParam>
        extends NCLAttributeAssessmentPrototype<T, P, I, Er, Ep> implements NCLElement<T, P>{


    public NCLAttributeAssessment() throws XMLException {
        super();
    }

    public NCLAttributeAssessment(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setRole(Er role) {
        Er aux = this.role;
        super.setRole(role);
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
    }
    
        
    @Override
    public void setEventType(NCLEventType eventType) {
        NCLEventType aux = this.eventType;
        super.setEventType(eventType);
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, eventType);
    }
    
        
    @Override
    public void setKey(KeyParamType key) {
        KeyParamType aux = this.key;
        super.setKey(key);
        impl.notifyAltered(NCLElementAttributes.KEY, aux, key);
    }


    @Override
    public void setAttributeType(NCLAttributeType attributeType) {
        NCLAttributeType aux = this.attributeType;
        super.setAttributeType(attributeType);
        impl.notifyAltered(NCLElementAttributes.ATTRIBUTETYPE, aux, attributeType);
    }
    
        
    @Override
    public void setOffset(IntegerParamType offset) {
        IntegerParamType aux = this.offset;
        super.setOffset(offset);
        impl.notifyAltered(NCLElementAttributes.OFFSET, aux, offset);
    }
    
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("role")){
//                    R child = createRole(attributes.getValue(i));
//                    setRole(child);
//                }
//                else if(attributes.getLocalName(i).equals("key")){
//                    String value = attributes.getValue(i);
//                    if(value.contains("$")){
//                        value = value.substring(1);
//                        setKey((P) new NCLConnectorParam(value));//cast retirado na correcao das referencias
//                    }
//                    else{
//                        for(NCLKey k : NCLKey.values()){
//                            if(k.toString().equals(value))
//                                setKey(k);
//                        }
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("offset")){
//                    String value = attributes.getValue(i);
//                    if(value.contains("$")){
//                        value = value.substring(1);
//                        setOffset((P) new NCLConnectorParam(value));//cast retirado na correcao das referencias
//                    }
//                    else
//                        setOffset(new Integer(value));
//                }
//                else if(attributes.getLocalName(i).equals("eventType")){
//                    for(NCLEventType e : NCLEventType.values()){
//                        if(e.toString().equals(attributes.getValue(i)))
//                            setEventType(e);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("attributeType")){
//                    for(NCLAttributeType t : NCLAttributeType.values()){
//                        if(t.toString().equals(attributes.getValue(i)))
//                            setAttributeType(t);
//                    }
//                }
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(getParent() == null)
//            return;
//
//        if(getParamKey() != null)
//            setKey(parameterReference(getParamKey().getId()));
//        if(getParamOffset() != null)
//            setOffset(parameterReference(getParamOffset().getId()));
//    }


//    private P parameterReference(String id) {
//        NCLElementImpl connector = getParent();
//
//        while(!(connector instanceof NCLCausalConnector)){
//            connector = connector.getParent();
//            if(connector == null){
//                addWarning("Could not find a parent connector");
//                return null;
//            }
//        }
//
//        Set<P> params = ((NCLCausalConnector) connector).getConnectorParams();
//        for(P param : params){
//            if(param.getId().equals(id))
//                return param;
//        }
//
//        addWarning("Could not find connectorParam in connector with id: " + id);
//        return null;
//    }


    public void load(Element element) throws XMLException, NCLParsingException {
        String att_name, att_var;

        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRole(createRole(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the eventType (required)
        att_name = NCLElementAttributes.EVENTTYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setEventType(NCLEventType.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the key (optional)
        att_name = NCLElementAttributes.KEY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setKey(new KeyParamType(att_var));

        // set the attributeType (optional)
        att_name = NCLElementAttributes.ATTRIBUTETYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setAttributeType(NCLAttributeType.getEnumType(att_var));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create a connector <i>role</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a connector <i>role</i>.
     */
    protected Er createRole(String name) throws XMLException {
        return (Er) new NCLRole(name);
    }
}
