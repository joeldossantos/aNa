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
package br.uff.midiacom.ana.datatype.ncl.rule;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;


/**
 * Class that represents a composite rule.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the composite rule element. This attribute is required.</li>
 *  <li><i>operator</i> - boolean operator relating the composite rule children
 *                        elements. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>rule</i> - element representing a simple rule inside the composite
 *                    rule. The composite rule can have none or several rule
 *                    elements.</li>
 *  <li><i>compositeRule</i> - element representing a composite rule inside the
 *                             composite rule. The composite rule can have none
 *                             or several composite rule elements.</li>
 * </ul>
 * 
 * Note that the composite rule must have at least one child element, which can
 * be a simple or a composite rule.
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public abstract class NCLCompositeRulePrototype<T extends NCLTestRule,
                                                P extends NCLElement,
                                                I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLTestRule<T, P> {

    protected NCLOperator operator;
    protected IdentifiableElementList<T, T> rules;
    
    protected ItemList<ReferenceType> references;


    /**
     * Composite rule constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompositeRulePrototype() throws XMLException {
        super();
        rules = new IdentifiableElementList<T, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Set the composite rule boolean operator relating the composite rule
     * children elements. This attribute is required and can not be set to
     * <i>null</i>. The possible operators to be used are defined in the
     * enumeration <i>NCLOperator</i>.
     *
     * @param operator
     *          composite rule operator from the enumeration <i>NCLOperator</i>.
     * @throws XMLException 
     *          if the value representing the operator is null.
     */
    public void setOperator(NCLOperator operator) throws XMLException {
        if(operator == null)
            throw new XMLException("Null operator.");
        
        NCLOperator aux = this.operator;
        this.operator = operator;
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }


    /**
     * Returns the composite rule boolean operator relating the composite rule
     * children elements or <i>null</i> if the attribute is not defined. The
     * possible operators to be used are defined in the enumeration
     * <i>NCLOperator</i>.
     * 
     * @return
     *          composite rule operator from the enumeration <i>NCLOperator</i>
     *          or <i>null</i> if the operator is not defined.
     */
    public NCLOperator getOperator() {
        return operator;
    }


    /**
     * Adds a rule to the composite rule. The rule can be a simple rule or a
     * composite rule. The composite rule must have at least one rule.
     * 
     * @param rule
     *          element representing a rule. This rule can be a simple rule or a
     *          composite rule.
     * @return
     *          true if the rule was added.
     * @throws XMLException 
     *          if the element representing the rule is null.
     */
    public boolean addRule(T rule) throws XMLException {
        if(rules.add(rule, (T) this)){
            impl.notifyInserted(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    /**
     * Removes a rule of the composite rule. The rule can be a simple rule or a
     * composite rule. The composite rule must have at least one rule.
     * 
     * @param rule
     *          element representing a rule. This rule can be a simple rule or a
     *          composite rule.
     * @return
     *          true if the rule was removed.
     * @throws XMLException 
     *          if the element representing the rule is null.
     */
    public boolean removeRule(T rule) throws XMLException {
        if(rules.remove(rule)){
            impl.notifyRemoved(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    /**
     * Removes a rule of the composite rule. The rule can be a simple rule or a
     * composite rule. The composite rule must have at least one rule.
     * 
     * @param id
     *          string representing the id of the element representing a rule.
     *          This rule can be a simple rule or a composite rule.
     * @return
     *          true if the rule was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeRule(String id) throws XMLException {
        if(rules.remove(id)){
            impl.notifyRemoved(NCLElementSets.RULES, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite rule has a specific element representing
     * a rule. The rule can be a simple rule or a composite rule. The composite
     * rule must have at least one rule.
     * 
     * @param rule
     *          element representing a rule. This rule can be a simple rule or a
     *          composite rule.
     * @return
     *          true if the composite rule has the rule element.
     * @throws XMLException 
     *          if the element representing the rule is null.
     */
    public boolean hasRule(T rule) throws XMLException {
        return rules.contains(rule);
    }


    /**
     * Verifies if the composite rule has a rule with a specific id. The rule can
     * be a simple rule or a composite rule. The composite rule must have at
     * least one rule.
     * 
     * @param id
     *          string representing the id of the element representing a rule.
     *          This rule can be a simple rule or a composite rule.
     * @return
     *          true if the composite rule has the rule element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasRule(String id) throws XMLException {
        return rules.get(id) != null;
    }


    /**
     * Verifies if the composite rule has at least one rule. The rule can
     * be a simple rule or a composite rule. The composite rule must have at
     * least one rule.
     * 
     * @return 
     *          true if the composite rule has at least rule.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Returns the list of rules that a composite rule have. The rule can
     * be a simple rule or a composite rule. The composite rule must have at
     * least one rule.
     * 
     * @return 
     *          element list with all rules.
     */
    public IdentifiableElementList<T, T> getRules() {
        return rules;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
