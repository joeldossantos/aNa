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
import br.uff.midiacom.ana.datatype.aux.parameterized.DoubleParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.KeyParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventTransition;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


/**
 * Class that represents a connector simple condition.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>role</i> - condition identification. This attribute is required.</li>
 *  <li><i>delay</i> - delay waited by the condition. This attribute is optional.</li>
 *  <li><i>eventType</i> - type of event. This attribute is optional.</li>
 *  <li><i>key</i> - key pressed. This attribute is optional.</li>
 *  <li><i>transition</i> - transition occurred. This attribute is optional.</li>
 *  <li><i>min</i> - minimum cardinality of the condition. This attribute is optional.</li>
 *  <li><i>max</i> - maximum cardinality of the condition. This attribute is optional.</li>
 *  <li><i>qualifier</i> - relates the condition instances when its cardinality is
 *                         greater than 1. This attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec>
 * @param <Er>
 * @param <Ep>
 * @param <R> 
 */
public class NCLSimpleCondition<T extends NCLSimpleCondition,
                                P extends NCLElement,
                                I extends NCLElementImpl,
                                Ec extends NCLCondition,
                                Er extends NCLRole,
                                Ep extends NCLConnectorParam,
                                R extends ConParamReference>
        extends NCLElementPrototype<Ec, P, I>
        implements NCLCondition<Ec, P, Ep, Er, R> {

    protected KeyParamType<Ep, Ec, R> key;
    protected Integer min;
    protected MaxType max;
    protected NCLConditionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventTransition transition;
    protected Er role;
    protected DoubleParamType<Ep, Ec, R> delay;
    

    /**
     * Simple condition element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSimpleCondition() throws XMLException {
        super();
    }


    /**
     * Sets the connector interface point, used to identify this simple condition
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
     * condition or <i>null</i> if the attribute is not defined.
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
     * Sets the minimum cardinality of the simple condition. This attribute is
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
    public void setMin(Integer min) {
        if(min != null && min < 0)
            throw new IllegalArgumentException("Invalid min");

        Integer aux = this.min;
        this.min = min;
        impl.notifyAltered(NCLElementAttributes.MIN, aux, min);
    }


    /**
     * Returns the minimum cardinality of the simple condition or <i>null</i> if
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
     * Sets the maximum cardinality of the simple condition. This attribute is
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
     * Returns the maximum cardinality of the simple condition or <i>null</i> if
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
     * Sets the relation among the condition instances when its cardinality is
     * greater than 1. This attribute is optional. Set the qualifier to
     * <i>null</i> to erase a qualifier already defined.
     * 
     * <br/>
     * 
     * The qualifier attribute relates the simple condition instances. Its
     * possible values are "and" and "or" and are defined in the enumeration
     * <i>NCLConditionOperator</i>.
     * 
     * <br/>
     * 
     * Using an "and" qualifier means that all instances of the simple condition
     * must be satisfied at the same time. Using an "or" qualifier means that at
     * least one instance of the simple condition must be satisfied.
     * 
     * <br/>
     * 
     * The default qualifier value is <i>or</i>.
     *
     * @param qualifier
     *          element representing the simple condition qualifier from the
     *          enumeration <i>NCLConditionOperator</i> or <i>null</i> to erase
     *          a qualifier already defined.
     */
    public void setQualifier(NCLConditionOperator qualifier) {
        NCLConditionOperator aux = this.qualifier;
        this.qualifier = qualifier;
        impl.notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
    }


    /**
     * Returns the relation among the condition instances when its cardinality is
     * greater than 1 or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The qualifier attribute relates the simple condition instances. Its
     * possible values are "and" and "or" and are defined in the enumeration
     * <i>NCLConditionOperator</i>.
     * 
     * <br/>
     * 
     * Using an "and" qualifier means that all instances of the simple condition
     * must be satisfied at the same time. Using an "or" qualifier means that at
     * least one instance of the simple condition must be satisfied.
     * 
     * <br/>
     * 
     * The default qualifier value is <i>or</i>.
     *
     * @return
     *          element representing the simple condition qualifier from the
     *          enumeration <i>NCLConditionOperator</i> or <i>null</i> if the
     *          attribute is not defined.
     */
    public NCLConditionOperator getQualifier() {
        return qualifier;
    }


    /**
     * Sets the key pressed by the user, in case a selection event is used. This
     * attribute is optional. Set the key to <i>null</i> to erase a key already
     * defined.
     * 
     * <br/>
     * 
     * The key can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this simple condition is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple condition it.
     * 
     * @param key
     *          element representing the key pressed or <i>null</i> to erase a
     *          key already defined.
     * @throws XMLException
     *          if an error occur while creating the key value.
     */
    public void setKey(KeyParamType<Ep, Ec, R> key) throws XMLException {
        KeyParamType aux = this.key;
        
        this.key = key;
        if(this.key != null)
            this.key.setOwner((Ec) this, NCLElementAttributes.KEY);
        
        impl.notifyAltered(NCLElementAttributes.KEY, aux, key);
        if(aux != null)
            aux.removeOwner();
    }


    /**
     * Returns the key pressed by the user, in case a selection event is used or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The key can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this simple condition is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this simple condition it.
     * 
     * @return
     *          element representing the key pressed or <i>null</i> if the
     *          attribute is not defined.
     */
    public KeyParamType<Ep, Ec, R> getKey() {
        return key;
    }


    /**
     * Sets the type of the event tested by the simple condition. This attribute
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
     * Returns the type of the event tested by the simple condition or <i>null</i>
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
     * Sets the transition to be tested by the condition in the event state
     * machine. This attribute is optional. Set the transition to <i>null</i>
     * to erase a transition already defined.
     * 
     * <br/>
     * 
     * The transition must be defined when the simple condition defined the
     * attribute event type. The possible transition values are defined in the
     * enumeration <i>NCLEventTransition</i>.
     *
     * @param transition
     *          value representing the transition from the enumeration
     *          <i>NCLEventTransition</i> or <i>null</i> to erase a transition
     *          already defined.
     */
    public void setTransition(NCLEventTransition transition) {
        NCLEventTransition aux = this.transition;
        this.transition = transition;
        impl.notifyAltered(NCLElementAttributes.TRANSITION, aux, transition);
    }


    /**
     * Returns the transition to be tested by the condition in the event state
     * machine or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The transition must be defined when the simple condition defined the
     * attribute event type. The possible transition values are defined in the
     * enumeration <i>NCLEventTransition</i>.
     *
     * @return
     *          value representing the transition from the enumeration
     *          <i>NCLEventTransition</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLEventTransition getTransition() {
        return transition;
    }


    @Override
    public void setDelay(DoubleParamType<Ep, Ec, R> delay) throws XMLException {
        DoubleParamType aux = this.delay;
        
        this.delay = delay;
        this.delay.setOwner((Ec) this, NCLElementAttributes.DELAY);
        
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
        if(aux != null)
            aux.removeOwner();
    }


    @Override
    public DoubleParamType<Ep, Ec, R> getDelay() {
        return delay;
    }

    
    @Override
    public boolean compare(Ec other) {
        boolean comp = true;

        String this_cond, other_cond;
        NCLSimpleCondition other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleCondition))
            return false;

         other_simp = (NCLSimpleCondition) other;

        // Compara pelo role
        if(getRole() == null) this_cond = ""; else this_cond = getRole().getName();
        if(other_simp.getRole() == null) other_cond = ""; else other_cond = other_simp.getRole().getName();
        comp &= this_cond.equals(other_cond);

        // Compara pelo número mínimo
        
        int this_min, other_min;
        if(getMin() == null) this_min = 0; else this_min = getMin();
        if(other_simp.getMin() == null) other_min = 0; else other_min = other_simp.getMin();
        comp &= this_min == other_min;

        // Compara pelo número máximo
        if(getMax() == null) this_cond = ""; else this_cond = getMax().parse();
        if(other_simp.getMax() == null) other_cond = ""; else other_cond = other_simp.getMax().parse();
        comp &= this_cond.equals(other_cond);

        // Compara pelo delay
        if(getDelay() == null) this_cond = ""; else this_cond = getDelay().parse();
        if(other_simp.getDelay() == null) other_cond = ""; else other_cond = other_simp.getDelay().parse();
        comp &= this_cond.equals(other_cond);

        // Compara pelo qualifier
        if(getQualifier() == null) this_cond = ""; else this_cond = getQualifier().toString();
        if(other_simp.getQualifier() == null) other_cond = ""; else other_cond = other_simp.getQualifier().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pela tecla
        if(getKey() == null) this_cond = ""; else this_cond = getKey().parse();
        if(other_simp.getKey() == null) other_cond = ""; else other_cond = other_simp.getKey().parse();
        comp &= this_cond.equals(other_cond);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_cond = ""; else this_cond = getEventType().toString();
        if(other_simp.getEventType() == null) other_cond = ""; else other_cond = other_simp.getEventType().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pela transicao do evento
        if(getTransition() == null) this_cond = ""; else this_cond = getTransition().toString();
        if(other_simp.getTransition() == null) other_cond = ""; else other_cond = other_simp.getTransition().toString();
        comp &= this_cond.equals(other_cond);


        return comp;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleCondition";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadRole(element);
            loadMin(element);
            loadMax(element);
            loadQualifier(element);
            loadKey(element);
            loadEventType(element);
            loadTransition(element);
            loadDelay(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("SimpleCondition:\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseRole();
        content += parseDelay();
        content += parseEventType();
        content += parseKey();
        content += parseTransition();
        content += parseMin();
        content += parseMax();
        content += parseQualifier();
        
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
    
    
    protected String parseKey() {
        KeyParamType aux = getKey();
        if(aux != null)
            return " key='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadKey(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the key (optional)
        att_name = NCLElementAttributes.KEY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setKey(new KeyParamType(att_var));
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
        NCLConditionOperator aux = getQualifier();
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
            setQualifier(NCLConditionOperator.getEnumType(att_var));
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
    
    
    protected String parseTransition() {
        NCLEventTransition aux = getTransition();
        if(aux != null)
            return " transition='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadTransition(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the transition (optional)
        att_name = NCLElementAttributes.TRANSITION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setTransition(NCLEventTransition.getEnumType(att_var));
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
