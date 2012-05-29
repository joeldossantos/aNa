/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.parameterized.ByParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.DoubleParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.IntegerParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.StringParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventAction;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


/**
 * Class that represents a connector simple action.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>role</i> - action identification. This attribute is required.</li>
 *  <li><i>delay</i> - delay waited by the action. This attribute is optional.</li>
 *  <li><i>eventType</i> - type of event. This attribute is optional.</li>
 *  <li><i>actionType</i> - transition triggered. This attribute is optional.</li>
 *  <li><i>value</i> - value to be set if the action is an attribution. This
 *                     attribute is optional.</li>
 *  <li><i>min</i> - minimum cardinality of the action. This attribute is optional.</li>
 *  <li><i>max</i> - maximum cardinality of the action. This attribute is optional.</li>
 *  <li><i>qualifier</i> - relates the action instances when its cardinality is
 *                         greater than 1. This attribute is optional.</li>
 *  <li><i>repeat</i> - number of times the action will be repeated. This attribute
 *                      is optional.</li>
 *  <li><i>repeatDelay</i> - delay between repetitions. This attribute is optional.</li>
 *  <li><i>duration</i> - duration of an attribution. This attribute is optional.</li>
 *  <li><i>by</i> - increment of the attributed value during the interval. This
 *                  attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ea>
 * @param <Er>
 * @param <Ep>
 * @param <R> 
 */
public class NCLSimpleAction<T extends NCLSimpleAction,
                             P extends NCLElement,
                             I extends NCLElementImpl,
                             Ea extends NCLAction,
                             Er extends NCLRole,
                             Ep extends NCLConnectorParam,
                             R extends ConParamReference>
        extends NCLElementPrototype<Ea, P, I>
        implements NCLAction<Ea, P, Ep, Er, R> {

    protected StringParamType<Ep, Ea, R> value;
    protected Integer min;
    protected MaxType max;
    protected NCLActionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventAction actionType;
    protected IntegerParamType<Ep, Ea, R> repeat;
    protected DoubleParamType<Ep, Ea, R> repeatDelay;
    protected DoubleParamType<Ep, Ea, R> duration;
    protected ByParamType<Ep, Ea, R> by;
    protected Er role;
    protected DoubleParamType<Ep, Ea, R> delay;


    /**
     * Simple action element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSimpleAction() throws XMLException {
        super();
    }


    /**
     * Sets the connector interface point, used to identify this simple action
     * This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * @param role
     *          element representing the role name.
     * @throws XMLException
     *          if the role is null.
     */
    public void setRole(Er role) throws XMLException {
        if(role == null)
            throw new XMLException("Null role.");
        
        //Removes the parent of the actual role
        if(this.role != null)
            this.role.setParent(null);

        Er aux = this.role;
        this.role = role;
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
        
        //Set this as the parent of the new role
        this.role.setParent(this);
    }


    /**
     * Returns the connector interface point, used to identify this simple
     * action or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * @return
     *          element representing the role name or <i>null</i> if the
     *          attribute is not defined.
     */
    public Er getRole() {
        return role;
    }


    /**
     * Sets the value to be set if the action is an attribution. This attribute
     * is optional. Set the value to <i>null</i> to erase a value already defined.
     * 
     * <br/>
     * 
     * The value can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action it.
     *
     * @param value
     *          element representing the value for the attribution or <i>null</i>
     *          to erase a value already defined.
     * @throws XMLException
     *          if an error occur while creating the value.
     */
    public void setValue(StringParamType<Ep, Ea, R> value) throws XMLException {
        StringParamType aux = this.value;
        
        this.value = value;
        this.value.setOwner((Ea) this, NCLElementAttributes.VALUE);
        
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
        if(aux != null)
            aux.removeOwner();
    }
    
        
    /**
     * Returns the value to be set if the action is an attribution or <i>null</i>
     * if the attribute is not defined.
     * 
     * <br/>
     * 
     * The value can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action it.
     *
     * @return
     *          element representing the value for the attribution or <i>null</i>
     *          if the attribute is not defined.
     */
    public StringParamType<Ep, Ea, R> getValue() {
        return value;
    }
    
    
    /**
     * Sets the minimum cardinality of the simple action. This attribute is
     * optional. Set the minimum to <i>null</i> to erase a minimum already
     * defined.
     * 
     * <br/>
     * 
     * The default minimum value is <i>1</i>.
     *
     * @param min
     *          positive integer representing the minimum cardinality value or
     *          <i>null</i> to erase a minimum already defined.
     * @throws XMLException 
     *          if the value is negative.
     */
    public void setMin(Integer min) throws XMLException {
        if(min != null && min < 0)
            throw new XMLException("Invalid min");

        Integer aux = this.min;
        this.min = min;
        impl.notifyAltered(NCLElementAttributes.MIN, aux, min);
    }


    /**
     * Returns the minimum cardinality of the simple action or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * The default minimum value is <i>1</i>.
     *
     * @return
     *          positive integer representing the minimum cardinality value or
     *          <i>null</i> if the attribute is not defined.
     */
    public Integer getMin() {
        return min;
    }


    /**
     * Sets the maximum cardinality of the simple action. This attribute is
     * optional. Set the maximum to <i>null</i> to erase a maximum already
     * defined.
     * 
     * <br/>
     * 
     * The default maximum value is <i>1</i>.
     *
     * @param max
     *          element representing the maximum cardinality value or <i>null</i>
     *          to erase a minimum already defined.
     */
    public void setMax(MaxType max) {
        MaxType aux = this.max;
        this.max = max;
        impl.notifyAltered(NCLElementAttributes.MAX, aux, max);
    }


    /**
     * Returns the maximum cardinality of the simple action or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * The default maximum value is <i>1</i>.
     *
     * @return
     *          element representing the maximum cardinality value or <i>null</i>
     *          if the attribute is not defined.
     */
    public MaxType getMax() {
        return max;
    }


    /**
     * Sets the relation among the action instances when its cardinality is
     * greater than 1. This attribute is optional. Set the qualifier to
     * <i>null</i> to erase a qualifier already defined.
     * 
     * <br/>
     * 
     * The qualifier attribute relates the simple action instances. Its
     * possible values are "par" and "seq" and are defined in the enumeration
     * <i>NCLActionOperator</i>.
     * 
     * <br/>
     * 
     * Using a "par" qualifier means that the instances of the simple action will
     * be executed in parallel. Using a "seq" qualifier means that the instances
     * of the simple action will be executed in sequence.
     * 
     * <br/>
     * 
     * The default qualifier value is <i>par</i>.
     *
     * @param qualifier
     *          element representing the simple action qualifier from the
     *          enumeration <i>NCLActionOperator</i> or <i>null</i> to erase
     *          a qualifier already defined.
     */
    public void setQualifier(NCLActionOperator qualifier) {
        NCLActionOperator aux = this.qualifier;
        this.qualifier = qualifier;
        impl.notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
    }


    /**
     * Returns the relation among the action instances when its cardinality is
     * greater than 1 or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The qualifier attribute relates the simple action instances. Its
     * possible values are "par" and "seq" and are defined in the enumeration
     * <i>NCLActionOperator</i>.
     * 
     * <br/>
     * 
     * Using a "par" qualifier means that the instances of the simple action will
     * be executed in parallel. Using a "seq" qualifier means that the instances
     * of the simple action will be executed in sequence.
     * 
     * <br/>
     * 
     * The default qualifier value is <i>par</i>.
     *
     * @return
     *          element representing the simple action qualifier from the
     *          enumeration <i>NCLActionOperator</i> or <i>null</i> if the
     *          attribute is not defined.
     */
    public NCLActionOperator getQualifier() {
        return qualifier;
    }


    /**
     * Sets the type of the event used by the simple action. This attribute
     * is optional. Set the event type to <i>null</i> to erase an event type
     * already defined. The possible event type values are defined in the
     * enumeration <i>NCLEventType</i>.
     * 
     * @param eventType
     *          value representing the type of event from the enumeration
     *          <i>NCLEventType</i> or <i>null</i> to erase an event type
     *          already defined.
     */
    public void setEventType(NCLEventType eventType) {
        NCLEventType aux = this.eventType;
        this.eventType = eventType;
        impl.notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
    }


    /**
     * Returns the type of the event used by the simple action or <i>null</i>
     * if the attribute is not defined. The possible event type values are
     * defined in the enumeration <i>NCLEventType</i>.
     * 
     * @return
     *          value representing the type of event from the enumeration
     *          <i>NCLEventType</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLEventType getEventType() {
        return eventType;
    }


    /**
     * Sets the transition to be triggered by the action in the event state
     * machine. This attribute is optional. Set the action type to <i>null</i>
     * to erase an action type already defined.
     * 
     * <br/>
     * 
     * The action type must be defined when the simple action defined the
     * attribute event type. The possible action type values are defined in the
     * enumeration <i>NCLEventAction</i>.
     *
     * @param actionType
     *          value representing the type of action from the enumeration
     *          <i>NCLEventAction</i> or <i>null</i> to erase an action type
     *          already defined.
     */
    public void setActionType(NCLEventAction actionType) {
        NCLEventAction aux = this.actionType;
        this.actionType = actionType;
        impl.notifyAltered(NCLElementAttributes.ACTIONTYPE, aux, actionType);
    }


    /**
     * Returns the transition to be triggered by the action in the event state
     * machine or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The action type must be defined when the simple action defined the
     * attribute event type. The possible action type values are defined in the
     * enumeration <i>NCLEventAction</i>.
     *
     * @return
     *          value representing the type of action from the enumeration
     *          <i>NCLEventType</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLEventAction getActionType() {
        return actionType;
    }


    /**
     * Sets the number of times the action will be repeated. This attribute is
     * optional. Set the repeat to <i>null</i> to erase a repeat already defined.
     * 
     * <br/>
     * 
     * The repeat can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @param repeat
     *          element representing the number of repetitions of the action or
     *          <i>null</i> to erase a repeat already defined.
     * @throws XMLException 
     *          if an error occur while creating the repeat value.
     */
    public void setRepeat(IntegerParamType<Ep, Ea, R> repeat) throws XMLException {
        IntegerParamType aux = this.repeat;
        
        this.repeat = repeat;
        this.repeat.setOwner((Ea) this, NCLElementAttributes.REPEAT);
        
        impl.notifyAltered(NCLElementAttributes.REPEAT, aux, repeat);
        if(aux != null)
            aux.removeOwner();
    }


    /**
     * Returns the number of times the action will be repeated or <i>null</i>
     * if the attribute is not defined.
     * 
     * <br/>
     * 
     * The repeat can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @return
     *          element representing the number of repetitions of the action or
     *          <i>null</i> if the attribute is not defined.
     */
    public IntegerParamType<Ep, Ea, R> getRepeat() {
        return repeat;
    }


    /**
     * Sets the delay between action repetitions. This attribute is optional.
     * Set the repeat to <i>null</i> to erase a repeat already defined.
     * 
     * <br/>
     * 
     * The delay can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @param repeatDelay
     *          element representing the delay between repetitions of the action
     *          or <i>null</i> to erase a delay already defined.
     * @throws XMLException 
     *          if an error occur while creating the delay value.
     */
    public void setRepeatDelay(DoubleParamType<Ep, Ea, R> repeatDelay) throws XMLException {
        DoubleParamType aux = this.repeatDelay;
        
        this.repeatDelay = repeatDelay;
        this.repeatDelay.setOwner((Ea) this, NCLElementAttributes.REPEATDELAY);
        
        impl.notifyAltered(NCLElementAttributes.REPEATDELAY, aux, repeatDelay);
        if(aux != null)
            aux.removeOwner();
    }


    /**
     * Returns the delay between action repetitions or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The delay can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @return
     *          element representing the delay between repetitions of the action
     *          or <i>null</i> if the attribute is not defined.
     */
    public DoubleParamType<Ep, Ea, R> getRepeatDelay() {
        return repeatDelay;
    }


    /**
     * Sets the duration of an attribution. This attribute is optional. Set the
     * duration to <i>null</i> to erase a duration already defined.
     * 
     * <br/>
     * 
     * The duration can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @param duration
     *          element representing the duration of an attribution or <i>null</i>
     *          to erase a delay already defined.
     * @throws XMLException 
     *          if an error occur while creating the duration value.
     */
    public void setDuration(DoubleParamType<Ep, Ea, R> duration) throws XMLException {
        DoubleParamType aux = this.duration;
        
        this.duration = duration;
        this.duration.setOwner((Ea) this, NCLElementAttributes.DURATION);
        
        impl.notifyAltered(NCLElementAttributes.DURATION, aux, duration);
        if(aux != null)
            aux.removeOwner();
    }


    /**
     * Returns the duration of an attribution or <i>null</i> if the attribute is
     * not defined.
     * 
     * <br/>
     * 
     * The duration can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @return
     *          element representing the duration of an attribution or <i>null</i>
     *          if the attribute is not defined.
     */
    public DoubleParamType<Ep, Ea, R> getDuration() {
        return duration;
    }


    /**
     * Sets the increment of the attributed value during the interval. This
     * attribute is optional. Set the duration to <i>null</i> to erase a duration
     * already defined.
     * 
     * <br/>
     * 
     * The increment can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @param by
     *          element representing the increment of an attribution or <i>null</i>
     *          to erase an increment already defined.
     * @throws XMLException 
     *          if an error occur while creating the increment value.
     */
    public void setBy(ByParamType<Ep, Ea, R> by) throws XMLException {
        ByParamType aux = this.by;
        
        this.by = by;
        this.by.setOwner((Ea) this, NCLElementAttributes.BY);
        
        impl.notifyAltered(NCLElementAttributes.BY, aux, by);
        if(aux != null)
            aux.removeOwner();
    }


    /**
     * Returns the increment of the attributed value during the interval or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The increment can be set as a parameter, in which case its value is defined
     * by the link that uses the connector where this action is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple action is.
     *
     * @return
     *          element representing the increment of an attribution or <i>null</i>
     *          if the attribute is not defined.
     */
    public ByParamType<Ep, Ea, R> getBy() {
        return by;
    }


    @Override
    public void setDelay(DoubleParamType<Ep, Ea, R> delay) throws XMLException {
        DoubleParamType aux = this.delay;
        
        this.delay = delay;
        this.delay.setOwner((Ea) this, NCLElementAttributes.DELAY);
        
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
        if(aux != null)
            aux.removeOwner();
    }


    @Override
    public DoubleParamType<Ep, Ea, R> getDelay() {
        return delay;
    }


    @Override
    public boolean compare(Ea other) {
        boolean comp = true;

        String this_act, other_act;
        int this_ac, other_ac;
        NCLSimpleAction other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleAction))
            return false;

         other_simp = (NCLSimpleAction) other;

        // Compara pelo role
        if(getRole() == null) this_act = ""; else this_act = getRole().getName();
        if(other_simp.getRole() == null) other_act = ""; else other_act = other_simp.getRole().getName();
        comp &= this_act.equals(other_act);

        // Compara pelo número mínimo
        if(getMin() == null) this_ac = 0; else this_ac = getMin();
        if(other_simp.getMin() == null) other_ac = 0; else other_ac = other_simp.getMin();
        comp &= (this_ac == other_ac);

        // Compara pelo número máximo
        if(getMax() == null) this_act = ""; else this_act = getMax().parse();
        if(other_simp.getMax() == null) other_act = ""; else other_act = other_simp.getMax().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo delay
        if(getDelay() == null) this_act = ""; else this_act = getDelay().parse();
        if(other_simp.getDelay() == null) other_act = ""; else other_act = other_simp.getDelay().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo qualifier
        if(getQualifier() == null) this_act = ""; else this_act = getQualifier().toString();
        if(other_simp.getQualifier() == null) other_act = ""; else other_act = other_simp.getQualifier().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo value
        if(getValue() == null) this_act = ""; else this_act = getValue().parse();
        if(other_simp.getValue() == null) other_act = ""; else other_act = other_simp.getValue().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_act = ""; else this_act = getEventType().toString();
        if(other_simp.getEventType() == null) other_act = ""; else other_act = other_simp.getEventType().toString();
        comp &= this_act.equals(other_act);

        // Compara pela acao do evento
        if(getActionType() == null) this_act = ""; else this_act = getActionType().toString();
        if(other_simp.getActionType() == null) other_act = ""; else other_act = other_simp.getActionType().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo repeat
        if(getRepeat() == null) this_act = ""; else this_act = getRepeat().parse();
        if(other_simp.getRepeat() == null) other_act = ""; else other_act = other_simp.getRepeat().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo repeatDelay
        if(getRepeatDelay() == null) this_act = ""; else this_act = getRepeatDelay().parse();
        if(other_simp.getRepeatDelay() == null) other_act = ""; else other_act = other_simp.getRepeatDelay().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo duration
        if(getDuration() == null) this_act = ""; else this_act = getDuration().parse();
        if(other_simp.getDuration() == null) other_act = ""; else other_act = other_simp.getDuration().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo by
        if(getBy() == null) this_act = ""; else this_act = getBy().parse();
        if(other_simp.getBy() == null) other_act = ""; else other_act = other_simp.getBy().parse();
        comp &= this_act.equals(other_act);

        return comp;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleAction";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;

        try{
            loadRole(element);
            loadValue(element);
            loadMin(element);
            loadMax(element);
            loadQualifier(element);
            loadEventType(element);
            loadActionType(element);
            loadRepeat(element);
            loadRepeatDelay(element);
            loadDuration(element);
            loadBy(element);
            loadDelay(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("SimpleAction:\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseRole();
        content += parseDelay();
        content += parseEventType();
        content += parseActionType();
        content += parseValue();
        content += parseMin();
        content += parseMax();
        content += parseQualifier();
        content += parseRepeat();
        content += parseRepeatDelay();
        content += parseDuration();
        content += parseBy();
        
        return content;
    }
    
    
    protected String parseRole() {
        Er aux = getRole();
        if(aux != null)
            return " role='" + aux.getName() + "'";
        else
            return "";
    }
    
    
    protected void loadRole(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRole(createRole(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseValue() {
        StringParamType aux = getValue();
        if(aux != null)
            return " value='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadValue(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the value (optional)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(new StringParamType(att_var));
    }
    
    
    protected String parseDelay() {
        DoubleParamType aux = getDelay();
        if(aux == null)
            return "";
        
        String content = " delay='" + aux.parse();
        if(aux.getValue() != null)
            content += "s'";
        else
            content += "'";
        
        return content;
    }
    
    
    protected void loadDelay(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the delay (optional)
        att_name = NCLElementAttributes.DELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDelay(new DoubleParamType(att_var));
    }
    
    
    protected String parseMin() {
        Integer aux = getMin();
        if(aux != null)
            return " min='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadMin(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the min (optional)
        att_name = NCLElementAttributes.MIN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            try{
                setMin(new Integer(att_var));
            }catch (Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        }
    }
    
    
    protected String parseMax() {
        MaxType aux = getMax();
        if(aux != null)
            return " max='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadMax(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the max (optional)
        att_name = NCLElementAttributes.MAX.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setMax(new MaxType(att_var));
    }
    
    
    protected String parseQualifier() {
        NCLActionOperator aux = getQualifier();
        if(aux != null)
            return " qualifier='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadQualifier(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the qualifier (optional)
        att_name = NCLElementAttributes.QUALIFIER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setQualifier(NCLActionOperator.getEnumType(att_var));
    }
    
    
    protected String parseEventType() {
        NCLEventType aux = getEventType();
        if(aux != null)
            return " eventType='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadEventType(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the eventType (optional)
        att_name = NCLElementAttributes.EVENTTYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setEventType(NCLEventType.getEnumType(att_var));
    }
    
    
    protected String parseActionType() {
        NCLEventAction aux = getActionType();
        if(aux != null)
            return " actionType='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadActionType(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the actionType (optional)
        att_name = NCLElementAttributes.ACTIONTYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setActionType(NCLEventAction.getEnumType(att_var));
    }
    
    
    protected String parseRepeat() {
        IntegerParamType aux = getRepeat();
        if(aux != null)
            return " repeat='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRepeat(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the repeat (optional)
        att_name = NCLElementAttributes.REPEAT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRepeat(new IntegerParamType(att_var));
    }
    
    
    protected String parseRepeatDelay() {
        DoubleParamType aux = getRepeatDelay();
        if(aux == null)
            return "";
        
        String content = " repeatDelay='" + aux.parse();
        if(aux.getValue() != null)
            content += "s'";
        else
            content += "'";
        
        return content;
    }
    
    
    protected void loadRepeatDelay(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the repeatDelay (optional)
        att_name = NCLElementAttributes.REPEATDELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRepeatDelay(new DoubleParamType(att_var));
    }
    
    
    protected String parseDuration() {
        DoubleParamType aux = getDuration();
        if(aux == null)
            return "";
        
        String content = " duration='" + aux.parse();
        if(aux.getValue() != null)
            content += "s'";
        else
            content += "'";
        
        return content;
    }
    
    
    protected void loadDuration(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the duration (optional)
        att_name = NCLElementAttributes.DURATION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDuration(new DoubleParamType(att_var));
    }
    
    
    protected String parseBy() {
        ByParamType aux = getBy();
        if(aux != null)
            return " by='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadBy(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the by (optional)
        att_name = NCLElementAttributes.BY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBy(new ByParamType(att_var));
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
