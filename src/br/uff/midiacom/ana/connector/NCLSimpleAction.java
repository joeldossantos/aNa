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
        extends NCLSimpleActionPrototype<T, P, I, Ea, Er, Ep> implements NCLAction<Ea, P, Ep, Er> {


    public NCLSimpleAction() throws XMLException {
        super();
    }
    
    
    public NCLSimpleAction(Element element) throws XMLException {
        super();
        load(element);
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
        impl.notifyAltered(NCLElementAttributes.MAX, aux, max);
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


    public void load(Element element) throws XMLException, NCLParsingException {
        String att_name, att_var;

        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRole(createRole(att_name));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the value (optional)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(new StringParamType<Ep>(att_var, this));

        // set the min (optional)
        att_name = NCLElementAttributes.MIN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            try{
                setMin(new Integer(att_var));
            }catch (Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        }

        // set the max (optional)
        att_name = NCLElementAttributes.MAX.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setMax(new MaxType(att_var));

        // set the qualifier (optional)
        att_name = NCLElementAttributes.QUALIFIER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setQualifier(NCLActionOperator.getEnumType(att_var));

        // set the eventType (optional)
        att_name = NCLElementAttributes.EVENTTYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setEventType(NCLEventType.getEnumType(att_var));

        // set the actionType (optional)
        att_name = NCLElementAttributes.ACTIONTYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setActionType(NCLEventAction.getEnumType(att_var));

        // set the repeatDelay (optional)
        att_name = NCLElementAttributes.REPEATDELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRepeatDelay(new DoubleParamType<Ep>(att_var, this));

        // set the repeat (optional)
        att_name = NCLElementAttributes.REPEAT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRepeat(new IntegerParamType<Ep>(att_var, this));

        // set the duration (optional)
        att_name = NCLElementAttributes.DURATION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDuration(new DoubleParamType<Ep>(att_var, this));

        // set the by (optional)
        att_name = NCLElementAttributes.BY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBy(new ByParamType<Ep>(att_var, this));

        // set the delay (optional)
        att_name = NCLElementAttributes.DELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDelay(new DoubleParamType<Ep>(att_var, this));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
    
    
    public Er findRole(String name) {
        if(role.getName().equals(name))
            return role;
        else
            return null;
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
