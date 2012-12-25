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
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLActionOperator;
import br.uff.midiacom.ana.util.enums.NCLDefaultActionRole;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLEventAction;
import br.uff.midiacom.ana.util.enums.NCLEventType;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.util.ArrayList;
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
public class NCLSimpleAction<T extends NCLElement,
                             Ep extends NCLConnectorParam,
                             Er extends NCLRoleElement,
                             Eb extends NCLBind>
        extends ParamElement<T>
        implements NCLAction<T, Ep, Er>, NCLRoleElement<Eb> {

    protected Object value;
    protected Integer min;
    protected Object max;
    protected NCLActionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventAction actionType;
    protected Object repeat;
    protected Object repeatDelay;
    protected Object duration;
    protected Object by;
    protected Object role;
    protected Object delay;
    
    protected ArrayList<Eb> references;


    /**
     * Simple action element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSimpleAction() {
        super();
        references = new ArrayList<Eb>();
    }


    /**
     * Sets the connector interface point, used to identify this simple action
     * This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * <br/>
     * 
     * The role may be set as a string value or a value from the
     * <i>NCLDefaultActionRole</i> enumeration.
     * 
     * @param role
     *          string or a value from the enumeration <i>NCLDefaultActionRole</i>
     *          representing the role name.
     * @throws XMLException
     *          if the role is null or of the wrong type.
     */
    public void setRole(Object role) throws XMLException {
        if(role == null)
            throw new XMLException("Null role.");
        
        Object aux = this.role;
        
        if(role instanceof String){
            String name = (String) role;
            if("".equals(name.trim()))
                throw new XMLException("Empty role String");
            
            for(NCLDefaultActionRole drole : NCLDefaultActionRole.values()){
                if(name.equals(drole.toString())){
                    this.role = drole;
                    notifyAltered(NCLElementAttributes.ROLE, aux, drole);
                    return;
                }
            }
            
            this.role = role;
            notifyAltered(NCLElementAttributes.ROLE, aux, role);
        }
        else if(role instanceof NCLDefaultActionRole){
            this.role = role;
            notifyAltered(NCLElementAttributes.ROLE, aux, role);
        }
        else{
            throw new XMLException("Wrong role type.");
        }
    }


    /**
     * Returns the connector interface point, used to identify this simple
     * action or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * <br/>
     * 
     * The role may be set as a string value or a value from the
     * <i>NCLDefaultActionRole</i> enumeration.
     * 
     * @return
     *          string or a value from the enumeration <i>NCLDefaultActionRole</i>
     *          representing the role name or <i>null</i> if the attribute is
     *          not defined.
     */
    @Override
    public Object getRole() {
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
     *          string or connector parameter representing the value for the
     *          attribution or <i>null</i> to erase a value already defined.
     * @throws XMLException
     *          if the value is of the wrong type.
     */
    public void setValue(Object value) throws XMLException {
        Object aux = this.value;
        
        if(value == null){
            this.value = value;
            notifyAltered(NCLElementAttributes.VALUE, aux, value);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(value instanceof String){
            String v = (String) value;
            if("".equals(v.trim()))
                throw new XMLException("Empty value String");
            
            if(!v.contains("$"))
                this.value = v;
            else{
                this.value = findConnectorParam(v.substring(1));
                ((Ep) this.value).addReference(this);
            }
        }
        else if(value instanceof NCLConnectorParam){
            this.value = value;
            ((Ep) this.value).addReference(this);
        }
        else
            throw new XMLException("Wrong value type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.VALUE, aux, value);
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
     *          string or connector parameter representing the value for the
     *          attribution or <i>null</i> if the attribute is not defined.
     */
    public Object getValue() {
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
        notifyAltered(NCLElementAttributes.MIN, aux, min);
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
     *          integer representing the maximum cardinality value, the string
     *          "unbounded" or <i>null</i> to erase a maximum already defined.
     */
    public void setMax(Object max) throws XMLException {
        Object aux = this.max;
        
        if(max == null){
            this.max = max;
            notifyAltered(NCLElementAttributes.MAX, aux, max);
            return;
        }
        
        // test if is an integer
        if(max instanceof Integer){
            if(((Integer) max) < 0)
                throw new XMLException("Negative value");

            this.max = max;
        }
        // test if is an string
        else if(max instanceof String){
            String value = (String) max;
            if("".equals(value.trim()))
                throw new XMLException("Empty value String");

            if(!value.equals("unbounded"))
                this.max = new Integer(value);
            else
                this.max = max;
        }
        // type not valid
        else
            throw new XMLException("Wrong max type.");

        // notify the modification
        notifyAltered(NCLElementAttributes.MAX, aux, max);
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
     *          integer representing the maximum cardinality value, the string
     *          "unbounded" or <i>null</i> if the attribute is not defined.
     */
    public Object getMax() {
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
    public void setQualifier(NCLActionOperator qualifier) throws XMLException {
        NCLActionOperator aux = this.qualifier;
        this.qualifier = qualifier;
        notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
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
    public void setEventType(NCLEventType eventType) throws XMLException {
        NCLEventType aux = this.eventType;
        this.eventType = eventType;
        notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
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
    public void setActionType(NCLEventAction actionType) throws XMLException {
        NCLEventAction aux = this.actionType;
        this.actionType = actionType;
        notifyAltered(NCLElementAttributes.ACTIONTYPE, aux, actionType);
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
     *          string, integer or connector parameter representing the number
     *          of repetitions of the action or <i>null</i> to erase a repeat
     *          already defined.
     * @throws XMLException 
     *          if the repeat value is of a wrong type.
     */
    public void setRepeat(Object repeat) throws XMLException {
        Object aux = this.repeat;
        
        if(repeat == null){
            this.repeat = repeat;
            notifyAltered(NCLElementAttributes.REPEAT, aux, repeat);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(repeat instanceof String){
            String value = (String) repeat;
            if("".equals(value.trim()))
                throw new XMLException("Empty key String");
            
            if(!value.contains("$"))
                this.repeat = new Integer(value);
            else{
                this.repeat = findConnectorParam(value.substring(1));
                ((Ep) this.repeat).addReference(this);
            }
        }
        else if(repeat instanceof Integer)
            this.repeat = repeat;
        else if(repeat instanceof NCLConnectorParam){
            this.repeat = repeat;
            ((Ep) this.repeat).addReference(this);
        }
        else
            throw new XMLException("Wrong repeat type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.REPEAT, aux, repeat);
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
     *          string, integer or connector parameter representing the number
     *          of repetitions of the action or <i>null</i> if the attribute is
     *          not defined.
     */
    public Object getRepeat() {
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
     *          double, string or connector parameter representing the delay
     *          between repetitions of the action or <i>null</i> to erase a delay
     *          already defined.
     * @throws XMLException 
     *          if the delay type is wrong.
     */
    public void setRepeatDelay(Object repeatDelay) throws XMLException {
        Object aux = this.repeatDelay;
        
        if(repeatDelay == null){
            this.repeatDelay = repeatDelay;
            notifyAltered(NCLElementAttributes.REPEATDELAY, aux, repeatDelay);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(repeatDelay instanceof String){
            String value = (String) repeatDelay;
            if("".equals(value.trim()))
                throw new XMLException("Empty delay String");
            
            if(!value.contains("$"))
                this.repeatDelay = new Double(value);
            else{
                this.repeatDelay = findConnectorParam(value.substring(1));
                ((Ep) this.repeatDelay).addReference(this);
            }
        }
        else if(repeatDelay instanceof Double)
            this.repeatDelay = repeatDelay;
        else if(repeatDelay instanceof NCLConnectorParam){
            this.repeatDelay = repeatDelay;
            ((Ep) this.repeatDelay).addReference(this);
        }
        else
            throw new XMLException("Wrong delay type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.REPEATDELAY, aux, repeatDelay);
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
     *          double, string or connector parameter representing the delay
     *          between repetitions of the action or <i>null</i> if the attribute
     *          is not defined.
     */
    public Object getRepeatDelay() {
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
     *          double, string or connector parameter representing the duration
     *          of an attribution or <i>null</i> to erase a delay already defined.
     * @throws XMLException 
     *          if the duration type is wrong.
     */
    public void setDuration(Object duration) throws XMLException {
        Object aux = this.duration;
        
        if(duration == null){
            this.duration = duration;
            notifyAltered(NCLElementAttributes.DURATION, aux, duration);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(duration instanceof String){
            String value = (String) duration;
            if("".equals(value.trim()))
                throw new XMLException("Empty delay String");
            
            if(!value.contains("$")){
                // Take out the s for the seconds
                if(value.endsWith("s"))
                    value = value.substring(0, value.length() - 1);
                this.duration = new Double(value);
            }
            else{
                this.duration = findConnectorParam(value.substring(1));
                ((Ep) this.duration).addReference(this);
            }
        }
        else if(duration instanceof Double)
            this.duration = duration;
        else if(duration instanceof NCLConnectorParam){
            this.duration = duration;
            ((Ep) this.duration).addReference(this);
        }
        else
            throw new XMLException("Wrong delay type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.DURATION, aux, duration);
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
     *          double, string or connector parameter representing the duration
     *          of an attribution or <i>null</i> if the attribute is not defined.
     */
    public Object getDuration() {
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
     *          integer, string or connector parameter representing the increment
     *          of an attribution or <i>null</i> to erase an increment already
     *          defined.
     * @throws XMLException 
     *          if the increment type is wrong.
     */
    public void setBy(Object by) throws XMLException {
        Object aux = this.by;
        
        if(by == null){
            this.by = by;
            notifyAltered(NCLElementAttributes.BY, aux, by);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(by instanceof String){
            String value = (String) by;
            if("".equals(value.trim()))
                throw new XMLException("Empty delay String");
            
            if(!value.contains("$")){
                if(!value.equals("indefinite"))
                    this.by = new Integer(value);
                else
                    this.by = by;
            }
            else{
                this.by = findConnectorParam(value.substring(1));
                ((Ep) this.by).addReference(this);
            }
        }
        else if(by instanceof Integer)
            this.by = by;
        else if(by instanceof NCLConnectorParam){
            this.by = by;
            ((Ep) this.by).addReference(this);
        }
        else
            throw new XMLException("Wrong delay type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.BY, aux, by);
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
     *          integer, string or connector parameter representing the increment
     *          of an attribution or <i>null</i> if the attribute is not defined.
     */
    public Object getBy() {
        return by;
    }


    @Override
    public void setDelay(Object delay) throws XMLException {
        Object aux = this.delay;
        
        if(delay == null){
            this.delay = delay;
            notifyAltered(NCLElementAttributes.DELAY, aux, delay);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(delay instanceof String){
            String value = (String) delay;
            if("".equals(value.trim()))
                throw new XMLException("Empty delay String");
            
            if(!value.contains("$")){
                if(value.contains("s"))
                    value = value.substring(0, value.length() - 1);
                this.delay = new Double(value);
            }
            else{
                this.delay = findConnectorParam(value.substring(1));
                ((Ep) this.delay).addReference(this);
            }
        }
        else if(delay instanceof Double)
            this.delay = delay;
        else if(delay instanceof NCLConnectorParam){
            this.delay = delay;
            ((Ep) this.delay).addReference(this);
        }
        else
            throw new XMLException("Wrong delay type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.DELAY, aux, delay);
    }


    @Override
    public Object getDelay() {
        return delay;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLSimpleAction))
            return false;
        
        boolean comp = true;

        String this_act, other_act;
        int this_ac, other_ac;
        NCLSimpleAction other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleAction))
            return false;

         other_simp = (NCLSimpleAction) other;

        // Compara pelo role
        if(getRole() == null) this_act = ""; else this_act = getRole().toString();
        if(other_simp.getRole() == null) other_act = ""; else other_act = other_simp.getRole().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo número mínimo
        if(getMin() == null) this_ac = 0; else this_ac = getMin();
        if(other_simp.getMin() == null) other_ac = 0; else other_ac = other_simp.getMin();
        comp &= (this_ac == other_ac);

        // Compara pelo número máximo
        if(getMax() == null) this_act = ""; else this_act = getMax().toString();
        if(other_simp.getMax() == null) other_act = ""; else other_act = other_simp.getMax().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo delay
        if(getDelay() == null) this_act = ""; else this_act = getDelay().toString();
        if(other_simp.getDelay() == null) other_act = ""; else other_act = other_simp.getDelay().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo qualifier
        if(getQualifier() == null) this_act = ""; else this_act = getQualifier().toString();
        if(other_simp.getQualifier() == null) other_act = ""; else other_act = other_simp.getQualifier().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo value
        if(getValue() == null) this_act = ""; else this_act = getValue().toString();
        if(other_simp.getValue() == null) other_act = ""; else other_act = other_simp.getValue().toString();
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
        if(getRepeat() == null) this_act = ""; else this_act = getRepeat().toString();
        if(other_simp.getRepeat() == null) other_act = ""; else other_act = other_simp.getRepeat().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo repeatDelay
        if(getRepeatDelay() == null) this_act = ""; else this_act = getRepeatDelay().toString();
        if(other_simp.getRepeatDelay() == null) other_act = ""; else other_act = other_simp.getRepeatDelay().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo duration
        if(getDuration() == null) this_act = ""; else this_act = getDuration().toString();
        if(other_simp.getDuration() == null) other_act = ""; else other_act = other_simp.getDuration().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo by
        if(getBy() == null) this_act = ""; else this_act = getBy().toString();
        if(other_simp.getBy() == null) other_act = ""; else other_act = other_simp.getBy().toString();
        comp &= this_act.equals(other_act);

        return comp;
    }
    
    
    @Override
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


    @Override
    public void load(Element element) throws NCLParsingException {
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
        Object aux = getRole();
        if(aux != null)
            return " role='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadRole(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRole(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseValue() {
        Object aux = getValue();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " value='$" + ((Ep) aux).getName() + "'";
        else
            return " value='" + aux.toString() + "'";
    }
    
    
    protected void loadValue(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the value (optional)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(att_var);
    }
    
    
    protected String parseDelay() {
        Object aux = getDelay();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " delay='$" + ((Ep) aux).getName() + "'";
        else
            return " delay='" + aux.toString() + "s'";
    }
    
    
    protected void loadDelay(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the delay (optional)
        att_name = NCLElementAttributes.DELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDelay(att_var);
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
        Object aux = getMax();
        if(aux != null)
            return " max='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadMax(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the max (optional)
        att_name = NCLElementAttributes.MAX.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setMax(att_var);
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
        Object aux = getRepeat();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " repeat='$" + ((Ep) aux).getName() + "'";
        else
            return " repeat='" + aux.toString() + "'";
    }
    
    
    protected void loadRepeat(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the repeat (optional)
        att_name = NCLElementAttributes.REPEAT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRepeat(att_var);
    }
    
    
    protected String parseRepeatDelay() {
        Object aux = getRepeatDelay();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " repeatDelay='$" + ((Ep) aux).getName() + "'";
        else
            return " repeatDelay='" + aux.toString() + "s'";
    }
    
    
    protected void loadRepeatDelay(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the repeatDelay (optional)
        att_name = NCLElementAttributes.REPEATDELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRepeatDelay(att_var);
    }
    
    
    protected String parseDuration() {
        Object aux = getDuration();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " duration='$" + ((Ep) aux).getName() + "'";
        else
            return " duration='" + aux.toString() + "s'";
    }
    
    
    protected void loadDuration(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the duration (optional)
        att_name = NCLElementAttributes.DURATION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDuration(att_var);
    }
    
    
    protected String parseBy() {
        Object aux = getBy();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " by='$" + ((Ep) aux).getName() + "'";
        else
            return " by='" + aux.toString() + "'";
    }
    
    
    protected void loadBy(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the by (optional)
        att_name = NCLElementAttributes.BY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBy(att_var);
    }
    
    
    @Override
    public Er findRole(String name) {
        if(role.toString().equals(name))
            return (Er) this;
        else
            return null;
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(Eb reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(Eb reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(value != null && value instanceof NCLConnectorParam)
            ((Ep)value).removeReference(this);
        
        if(repeat != null && repeat instanceof NCLConnectorParam)
            ((Ep)repeat).removeReference(this);
        
        if(repeatDelay != null && repeatDelay instanceof NCLConnectorParam)
            ((Ep)repeatDelay).removeReference(this);
        
        if(duration != null && duration instanceof NCLConnectorParam)
            ((Ep)duration).removeReference(this);
        
        if(by != null && by instanceof NCLConnectorParam)
            ((Ep)by).removeReference(this);
        
        if(delay != null && delay instanceof NCLConnectorParam)
            ((Ep)delay).removeReference(this);
        
        value = null;
        min = null;
        max = null;
        qualifier = null;
        eventType = null;
        actionType = null;
        repeat = null;
        repeatDelay = null;
        duration = null;
        by = null;
        role = null;
        delay = null;
    }
}
