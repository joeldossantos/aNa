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
import br.uff.midiacom.ana.util.enums.NCLConditionOperator;
import br.uff.midiacom.ana.util.enums.NCLDefaultConditionRole;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLEventTransition;
import br.uff.midiacom.ana.util.enums.NCLEventType;
import br.uff.midiacom.ana.util.enums.NCLKey;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.util.ArrayList;
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
public class NCLSimpleCondition<T extends NCLElement,
                                Ep extends NCLConnectorParam,
                                Er extends NCLRoleElement,
                                Eb extends NCLBind>
        extends ParamElement<T>
        implements NCLCondition<T, Ep, Er>, NCLRoleElement<Eb> {

    protected Object key;
    protected Integer min;
    protected Object max;
    protected NCLConditionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventTransition transition;
    protected Object role;
    protected Object delay;
    
    protected ArrayList<Eb> references;
    

    /**
     * Simple condition element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSimpleCondition() {
        super();
        references = new ArrayList<Eb>();
    }


    /**
     * Sets the connector interface point, used to identify this simple condition
     * This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * <br/>
     * 
     * The role may be set as a string value or a value from the
     * <i>NCLDefaultConditionRole</i> enumeration.
     * 
     * @param role
     *          string or a value from the enumeration <i>NCLDefaultConditionRole</i>
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
            
            for(NCLDefaultConditionRole drole : NCLDefaultConditionRole.values()){
                if(name.equals(drole.toString())){
                    this.role = drole;
                    notifyAltered(NCLElementAttributes.ROLE, aux, drole);
                    return;
                }
            }
            
            this.role = role;
            notifyAltered(NCLElementAttributes.ROLE, aux, role);
        }
        else if(role instanceof NCLDefaultConditionRole){
            this.role = role;
            notifyAltered(NCLElementAttributes.ROLE, aux, role);
        }
        else{
            throw new XMLException("Wrong role type.");
        }
    }


    /**
     * Returns the connector interface point, used to identify this simple
     * condition or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * <br/>
     * 
     * The role may be set as a string value or a value from the
     * <i>NCLDefaultConditionRole</i> enumeration.
     * 
     * @return
     *          string or a value from the enumeration <i>NCLDefaultConditionRole</i>
     *          representing the role name or <i>null</i> if the attribute is
     *          not defined.
     */
    @Override
    public Object getRole() {
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
    public void setMin(Integer min) throws XMLException {
        if(min != null && min < 0)
            throw new XMLException("Invalid min");

        Integer aux = this.min;
        this.min = min;
        notifyAltered(NCLElementAttributes.MIN, aux, min);
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
     * Returns the maximum cardinality of the simple condition or <i>null</i> if
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
    public void setQualifier(NCLConditionOperator qualifier) throws XMLException {
        NCLConditionOperator aux = this.qualifier;
        this.qualifier = qualifier;
        notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
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
     *          element from the enumeration <i>NCLKey</i>, string or connector
     *          parameter representing the key pressed or <i>null</i> to erase a
     *          key already defined.
     * @throws XMLException
     *          if an error occur while creating the key value.
     */
    public void setKey(Object key) throws XMLException {
        Object aux = this.key;
        
        if(key == null){
            this.key = key;
            notifyAltered(NCLElementAttributes.KEY, aux, key);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(key instanceof String){
            String value = (String) key;
            if("".equals(value.trim()))
                throw new XMLException("Empty key String");
            
            if(!value.contains("$"))
                this.key = NCLKey.getEnumType(value);
            else{
                this.key = findConnectorParam(value.substring(1));
                ((Ep) this.key).addReference(this);
            }
        }
        else if(key instanceof NCLKey)
            this.key = key;
        else if(key instanceof NCLConnectorParam){
            this.key = key;
            ((Ep) this.key).addReference(this);
        }
        else
            throw new XMLException("Wrong key type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.KEY, aux, key);
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
     *          element from the enumeration <i>NCLKey</i>, string or connector
     *          parameter representing the key pressed or <i>null</i> if the
     *          attribute is not defined.
     */
    public Object getKey() {
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
    public void setEventType(NCLEventType eventType) throws XMLException {
        NCLEventType aux = this.eventType;
        this.eventType = eventType;
        notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
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
    public void setTransition(NCLEventTransition transition) throws XMLException {
        NCLEventTransition aux = this.transition;
        this.transition = transition;
        notifyAltered(NCLElementAttributes.TRANSITION, aux, transition);
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
        if(other == null || !(other instanceof NCLSimpleCondition))
            return false;
        
        boolean comp = true;

        String this_cond, other_cond;
        NCLSimpleCondition other_simp = (NCLSimpleCondition) other;

        // Compara pelo role
        if(getRole() == null) this_cond = ""; else this_cond = getRole().toString();
        if(other_simp.getRole() == null) other_cond = ""; else other_cond = other_simp.getRole().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pelo número mínimo
        int this_min, other_min;
        if(getMin() == null) this_min = 0; else this_min = getMin();
        if(other_simp.getMin() == null) other_min = 0; else other_min = other_simp.getMin();
        comp &= this_min == other_min;

        // Compara pelo número máximo
        if(getMax() == null) this_cond = ""; else this_cond = getMax().toString();
        if(other_simp.getMax() == null) other_cond = ""; else other_cond = other_simp.getMax().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pelo delay
        if(getDelay() == null) this_cond = ""; else this_cond = getDelay().toString();
        if(other_simp.getDelay() == null) other_cond = ""; else other_cond = other_simp.getDelay().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pelo qualifier
        if(getQualifier() == null) this_cond = ""; else this_cond = getQualifier().toString();
        if(other_simp.getQualifier() == null) other_cond = ""; else other_cond = other_simp.getQualifier().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pela tecla
        if(getKey() == null) this_cond = ""; else this_cond = getKey().toString();
        if(other_simp.getKey() == null) other_cond = ""; else other_cond = other_simp.getKey().toString();
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


    @Override
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


    @Override
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
    
    
    protected String parseKey() {
        Object aux = getKey();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " key='$" + ((Ep) aux).getName() + "'";
        else
            return " key='" + aux.toString() + "'";
    }
    
    
    protected void loadKey(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the key (optional)
        att_name = NCLElementAttributes.KEY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setKey(att_var);
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
        
        if(key != null && key instanceof NCLConnectorParam)
            ((Ep)key).removeReference(this);
        
        if(delay != null && delay instanceof NCLConnectorParam)
            ((Ep)delay).removeReference(this);
        
        key = null;
        min = null;
        max = null;
        qualifier = null;
        eventType = null;
        transition = null;
        role = null;
        delay = null;
    }
}
