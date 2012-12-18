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
package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLOperator;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
public class NCLCompositeRule<T extends NCLElement,
                              Eb extends NCLBindRule,
                              Et extends NCLTestRule>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLTestRule<T, Eb, Et> {

    protected NCLOperator operator;
    protected ElementList<Et> rules;
    
    protected ArrayList<Eb> references;


    /**
     * Composite rule constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompositeRule() {
        super();
        rules = new ElementList<Et>();
        references = new ArrayList<Eb>();
    }
    
    
    public NCLCompositeRule(String id) throws XMLException {
        super();
        rules = new ElementList<Et>();
        references = new ArrayList<Eb>();
        setId(id);
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Et aux : rules) {
            ((NCLElementPrototype) aux).setDoc(doc);
        }
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
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
        notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
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
    public boolean addRule(Et rule) throws XMLException {
        if(rules.add(rule)){
            notifyInserted((T) rule);
            rule.setParent(this);
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
    public boolean removeRule(Et rule) throws XMLException {
        if(!rule.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(rules.remove(rule)){
            notifyRemoved((T) rule);
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
        Et aux = rules.get(id);
        return removeRule(aux);
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
    public boolean hasRule(Et rule) throws XMLException {
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
    public ElementList<Et> getRules() {
        return rules;
    }


    /**
     * Returns the rule with a specific id. The rule can be a simple rule or a
     * composite rule. The composite rule can have none or several rules.
     * 
     * @param id
     *          string representing the id of the element representing a rule.
     *          This rule can be a simple rule or a composite rule.
     * @return 
     *          element representing a rule or a composite rule.
     */
    public Et getRule(String id) throws XMLException {
        return rules.get(id);
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLCompositeRule))
            return false;
        
        boolean result = true;
        
        Object aux;
        if((aux = getId()) != null)
            result &= aux.equals(((NCLCompositeRule) other).getId());
        if((aux = getOperator()) != null)
            result &= aux.equals(((NCLCompositeRule) other).getOperator());
        
        ElementList<Et> otherpar = ((NCLCompositeRule) other).getRules();
        result &= rules.size() == otherpar.size();
        for (Et rul : rules) {
            try {
                result &= otherpar.contains(rul);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        return result;
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


        // param element and attributes declaration
        content = space + "<compositeRule";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += "</compositeRule>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadId(element);
            loadOperator(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("CompositeRule" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadRules(el);
                    loadCompositeRules(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("CompositeRule" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseOperator();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseRules(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseOperator() {
        NCLOperator aux = getOperator();
        if(aux != null)
            return " operator='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadOperator(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the operator (required)
        att_name = NCLElementAttributes.OPERATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setOperator(NCLOperator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseRules(int ident) {
        if(!hasRule())
            return "";
        
        String content = "";
        for(Et aux : rules)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadRules(Element element) throws XMLException {
        //create the rules
        if(element.getTagName().equals(NCLElementAttributes.RULE.toString())){
            Et inst = createRule(); 
            addRule(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompositeRules(Element element) throws XMLException {
        // create the compositeRules
        if(element.getTagName().equals(NCLElementAttributes.COMPOSITERULE.toString())){
            Et inst = createCompositeRule();
            addRule(inst);
            inst.load(element);
        }
    }
    
    
    @Override
    public Et findRule(String id) throws XMLException {
        Et result;
        
        if(getId().equals(id))
            return (Et) this;
        
        for(Et rule : rules){
            result = (Et) rule.findRule(id);
            if(result != null)
                return result;
        }
        
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
        
        operator = null;
        
        for(Et r : rules)
            r.clean();
    }
    

    /**
     * Function to create the child element <i>compositeRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compositeRule</i>.
     */
    protected Et createCompositeRule() throws XMLException {
        return (Et) new NCLCompositeRule();
    }


    /**
     * Function to create the child element <i>rule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>rule</i>.
     */
    protected Et createRule() throws XMLException {
        return (Et) new NCLRule();
    }
}
