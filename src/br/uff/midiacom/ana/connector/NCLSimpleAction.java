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
import br.uff.midiacom.ana.datatype.auxiliar.ByParamType;
import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.auxiliar.IntegerParamType;
import br.uff.midiacom.ana.datatype.auxiliar.StringParamType;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventAction;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLSimpleActionPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


public class NCLSimpleAction<T extends NCLSimpleAction, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLAction, Er extends NCLRole, Ep extends NCLConnectorParam>
        extends NCLSimpleActionPrototype<T, P, I, Ea, Er, Ep> implements NCLAction<Ea, P, Ep> {


    public NCLSimpleAction() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setValue(StringParamType value) {
        StringParamType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
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
        impl.notifyAltered(NCLElementAttributes.MIN, aux, max);
    }


    @Override
    public void setQualifier(NCLActionOperator qualifier) {
        NCLActionOperator aux = this.qualifier;
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
    public void setEventType(NCLEventType eventType) {
        NCLEventType aux = this.eventType;
        super.setEventType(eventType);
        impl.notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
    }


    @Override
    public void setActionType(NCLEventAction actionType) {
        NCLEventAction aux = this.actionType;
        super.setActionType(actionType);
        impl.notifyAltered(NCLElementAttributes.ACTIONTYPE, aux, actionType);
    }


    @Override
    public void setRepeat(IntegerParamType repeat) {
        IntegerParamType aux = this.repeat;
        super.setRepeat(repeat);
        impl.notifyAltered(NCLElementAttributes.REPEAT, aux, repeat);
    }


    @Override
    public void setRepeatDelay(DoubleParamType repeatDelay) {
        DoubleParamType aux = this.repeatDelay;
        super.setRepeatDelay(repeatDelay);
        impl.notifyAltered(NCLElementAttributes.REPEATDELAY, aux, repeatDelay);
    }


    @Override
    public void setDuration(DoubleParamType duration) {
        DoubleParamType aux = this.duration;
        super.setDuration(duration);
        impl.notifyAltered(NCLElementAttributes.DURATION, aux, duration);
    }


    @Override
    public void setBy(ByParamType by) {
        ByParamType aux = this.by;
        super.setBy(by);
        impl.notifyAltered(NCLElementAttributes.BY, aux, by);
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
//                else if(attributes.getLocalName(i).equals("value")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setValue((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else
//                        setValue(var);
//                }
//                else if(attributes.getLocalName(i).equals("delay")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else{
//                        var = var.substring(0, var.length() - 1);
//                        setDelay(new Integer(var));
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
//                    for(NCLActionOperator q : NCLActionOperator.values()){
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
//                else if(attributes.getLocalName(i).equals("actionType")){
//                    for(NCLEventAction t : NCLEventAction.values()){
//                        if(t.toString().equals(attributes.getValue(i)))
//                            setActionType(t);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("repeat")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setRepeat((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else
//                        setRepeat(new Integer(var));
//                }
//                else if(attributes.getLocalName(i).equals("repeatDelay")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setRepeatDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else{
//                        var = var.substring(0, var.length() - 1);
//                        setRepeatDelay(new Integer(var));
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("duration")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setDuration((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else{
//                        var = var.substring(0, var.length() - 1);
//                        setDuration(new Integer(var));
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("by")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setBy((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else
//                        setBy(new Integer(var));
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
//        if(getParamValue() != null)
//            setValue(parameterReference(getParamValue().getId()));
//        if(getParamRepeat() != null)
//            setRepeat(parameterReference(getParamRepeat().getId()));
//        if(getParamRepeatDelay() != null)
//            setRepeatDelay(parameterReference(getParamRepeatDelay().getId()));
//        if(getParamDuration() != null)
//            setDuration(parameterReference(getParamDuration().getId()));
//        if(getParamBy() != null)
//            setBy(parameterReference(getParamBy().getId()));
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


    public void load(Element element) {
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
