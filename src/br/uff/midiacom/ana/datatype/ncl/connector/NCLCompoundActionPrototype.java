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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.aux.parameterized.DoubleParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


/**
 * Class that represents a connector compound action.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>operator</i> - compound action operator. This attribute is required.</li>
 *  <li><i>delay</i> - delay waited by the action. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>simpleAction</i> - element representing a connector action. The
 *                            compound action can have none or several action
 *                            elements.</li>
 *  <li><i>compundAction</i> - element representing a connector action. The
 *                             compound action can have none or several action
 *                             elements.</li>
 * </ul>
 * 
 * Note that the compound action must have at least two actions.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ea>
 * @param <Ep>
 * @param <R> 
 */
public abstract class NCLCompoundActionPrototype<T extends NCLCompoundActionPrototype,
                                                 P extends NCLElement,
                                                 I extends NCLElementImpl,
                                                 Ea extends NCLAction,
                                                 Ep extends NCLConnectorParamPrototype,
                                                 R extends ConParamReference>
        extends NCLElementPrototype<Ea, P, I>
        implements NCLAction<Ea, P, Ep, R> {

    protected NCLActionOperator operator;
    protected DoubleParamType<Ep, Ea, R> delay;
    protected ElementList<Ea, T> actions;


    /**
     * Compound action element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompoundActionPrototype() throws XMLException {
        super();
        actions = new ElementList<Ea, T>();
    }
    
    
    /**
     * Sets the compound action operator. This attribute is required and can not
     * be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound action children elements. Its
     * possible values are "par" and "seq" and are defined in the enumeration
     * <i>NCLActionOperator</i>.
     * 
     * <br/>
     * 
     * Using a "par" operator means that the actions of the compound action will
     * be executed in any order. Using a "seq" operator means that the actions of
     * the compound action will be executed in a predetermined order.
     *
     * @param operator
     *          element representing the compound action operator from the
     *          enumeration <i>NCLActionOperator</i>.
     * @throws XMLException 
     *          if the element representing the operator is null.
     */
    public void setOperator(NCLActionOperator operator) throws XMLException {
        if(operator == null)
            throw new XMLException("Null operator.");
        
        NCLActionOperator aux = this.operator;
        this.operator = operator;
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
    
    /**
     * Returns the compound action operator or <i>null</i> if the attribute is
     * not defined.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound action children elements. Its
     * possible values are "par" and "seq" and are defined in the enumeration
     * <i>NCLActionOperator</i>.
     * 
     * <br/>
     * 
     * Using a "par" operator means that the actions of the compound action will
     * be executed in any order. Using a "seq" operator means that the actions of
     * the compound action will be executed in a predetermined order.
     * 
     * @return 
     *          element representing the compound action operator from the
     *          enumeration <i>NCLActionOperator</i> or <i>null</i> if the
     *          attribute is not defined.
     */
    public NCLActionOperator getOperator() {
        return operator;
    }

    
    /**
     * Adds an action to the compound action. The compound action must have at
     * least two action elements.
     * 
     * @param action
     *          element representing an action.
     * @return
     *          true if the element representing an action was added.
     * @throws XMLException 
     *          if the element representing the action is null.
     */
    public boolean addAction(Ea action) throws XMLException {
        if(actions.add(action, (T) this)){
            impl.notifyInserted(NCLElementSets.ACTIONS, action);
            return true;
        }
        return false;
    }


    /**
     * Removes an action of the compound action. The compound action must have at
     * least two action elements.
     * 
     * @param action
     *          element representing an action.
     * @return
     *          true if the element representing an action was removed.
     * @throws XMLException 
     *          if the element representing the action is null.
     */
    public boolean removeAction(Ea action) throws XMLException {
        if(actions.remove(action)){
            impl.notifyRemoved(NCLElementSets.ACTIONS, action);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the compound action has a specific element representing an
     * action. The compound action must have at least two action elements.
     * 
     * @param action
     *          element representing an action.
     * @return
     *          true if the compound action has the action element.
     * @throws XMLException 
     *          if the element representing the action is null.
     */
    public boolean hasAction(Ea action) throws XMLException {
        return actions.contains(action);
    }


    /**
     * Verifies if the compound action has at least one action. The compound
     * action must have at least two action elements.
     * 
     * @return 
     *          true if the compound action has at least one action.
     */
    public boolean hasAction() {
        return !actions.isEmpty();
    }


    /**
     * Returns the list of actions that a compound action have. The compound
     * action must have at least two action elements.
     * 
     * @return 
     *          element list with all actions.
     */
    public ElementList<Ea, T> getActions() {
        return actions;
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
        NCLCompoundActionPrototype other_comp;

        // Verify if actions are of the same type
        if(!(other instanceof NCLCompoundActionPrototype))
            return false;

        other_comp = (NCLCompoundActionPrototype) other;
        
        // Compare the operator
        if(getOperator() == null) this_act = ""; else this_act = getOperator().toString();
        if(other_comp.getOperator() == null) other_act = ""; else other_act = other_comp.getOperator().toString();
        comp = this_act.equals(other_act);

        // Compare the delay
        if(getDelay() == null) this_act = ""; else this_act = getDelay().parse();
        if(other_comp.getDelay() == null) other_act = ""; else other_act = other_comp.getDelay().parse();
        comp &= this_act.equals(other_act);

        // Compare the number of actions
        comp &= actions.size() == other_comp.getActions().size();

        // Compare the actions
        Iterator it = other_comp.getActions().iterator();
        for(NCLAction a : actions){
            if(!it.hasNext())
                continue;
            NCLAction other_a = (NCLAction) it.next();
            comp &= a.compare(other_a);
            if(comp)
                break;
        }

        
        return comp;
    }
}
