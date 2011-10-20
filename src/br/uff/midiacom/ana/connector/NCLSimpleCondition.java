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
import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.auxiliar.KeyParamType;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventTransition;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLSimpleConditionPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


public class NCLSimpleCondition<T extends NCLSimpleCondition, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCondition, Er extends NCLRole, Ep extends NCLConnectorParam>
        extends NCLSimpleConditionPrototype<T, P, I, Ec, Er, Ep> implements NCLCondition<Ec, P, Ep> {


    public NCLSimpleCondition() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setMin(Integer min) {
        Integer aux = this.min;
        super.setMin(min);
        impl.notifyAltered(NCLElementAttributes.MIN, aux, min);
    }


    @Override
    public void setMax(MaxType max) {
        MaxType aux = this.max;
        super.setMax(max);
        impl.notifyAltered(NCLElementAttributes.MAX, aux, max);
    }


    @Override
    public void setQualifier(NCLConditionOperator qualifier) {
        NCLConditionOperator aux = this.qualifier;
        super.setQualifier(qualifier);
        impl.notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
    }


    @Override
    public void setRole(Er role) {
        Er aux = this.role;
        super.setRole(role);
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
    }


    @Override
    public void setKey(KeyParamType key) {
        KeyParamType aux = this.key;
        super.setKey(key);
        impl.notifyAltered(NCLElementAttributes.KEY, aux, key);
    }


    @Override
    public void setEventType(NCLEventType eventType) {
        NCLEventType aux = this.eventType;
        super.setEventType(eventType);
        impl.notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
    }


    @Override
    public void setTransition(NCLEventTransition transition) {
        NCLEventTransition aux = this.transition;
        super.setTransition(transition);
        impl.notifyAltered(NCLElementAttributes.TRANSITION, aux, transition);
    }


    @Override
    public void setDelay(DoubleParamType delay) {
        DoubleParamType aux = this.delay;
        super.setDelay(delay);
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
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
//                else if(attributes.getLocalName(i).equals("delay")){
//                    String value = attributes.getValue(i);
//                    if(value.contains("$")){
//                        value = value.substring(1);
//                        setDelay((P) new NCLConnectorParam(value));//cast retirado na correcao das referencias
//                    }
//                    else{
//                        value = value.substring(0, value.length() - 1);
//                        setDelay(new Integer(value));
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("min"))
//                    setMin(new Integer(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("max")){
//                    if(attributes.getValue(i).equals("unbounded"))
//                        setMax(-1);
//                    else
//                        setMax(new Integer(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("qualifier")){
//                    for(NCLConditionOperator q : NCLConditionOperator.values()){
//                        if(q.toString().equals(attributes.getValue(i)))
//                            setQualifier(q);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("eventType")){
//                    for(NCLEventType e : NCLEventType.values()){
//                        if(e.toString().equals(attributes.getValue(i)))
//                            setEventType(e);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("transition")){
//                    for(NCLEventTransition t : NCLEventTransition.values()){
//                        if(t.toString().equals(attributes.getValue(i)))
//                            setTransition(t);
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
//        if(getParamDelay() != null)
//            setDelay(parameterReference(getParamDelay().getId()));
//        if(getParamKey() != null)
//            setKey(parameterReference(getParamKey().getId()));
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
     * Function to create a connector <i>role</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a connector <i>role</i>.
     */
    protected NCLRole createRole(String name) throws XMLException {
        return new NCLRole(name);
    }
}
