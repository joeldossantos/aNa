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

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.datatype.aux.reference.ExternalReferenceType;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a base of rules. Those rules can be simple or composite
 * rules.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of rules. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importBase</i> - element that imports a rule base defined in another
 *                          NCL document. The base can have none or several import
 *                          elements.</li>
 *  <li><i>rule</i> - element representing a simple rule inside the base. The base
 *                    can have none or several rule elements.</li>
 *  <li><i>compositeRule</i> - element representing a composite rule inside the
 *                             base. The base can have none or several composite
 *                             rule elements.</li>
 * </ul>
 * 
 * Note that the base of rules must have at least one child element, which can
 * be a import, a simple or a composite rule.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Et>
 * @param <Ei> 
 */
public class NCLRuleBase<T extends NCLRuleBase,
                         P extends NCLElement,
                         I extends NCLElementImpl,
                         Et extends NCLTestRule,
                         Ei extends NCLImportBase>
        extends NCLBase<T, P, I, Ei> {

    protected IdentifiableElementList<Et, T> rules;


    /**
     * Base of rules constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLRuleBase() throws XMLException {
        super();
        rules = new IdentifiableElementList<Et, T>();
    }


    /**
     * Adds a rule to the base of rules. The rule can be a simple rule or a
     * composite rule. The base of rules can have none or several rules.
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
        if(rules.add(rule, (T) this)){
            impl.notifyInserted(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    /**
     * Removes a rule of the base of rules. The rule can be a simple rule or a
     * composite rule. The base of rules can have none or several rules.
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
        if(rules.remove(rule)){
            impl.notifyRemoved(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    /**
     * Removes a rule of the base of rules. The rule can be a simple rule or a
     * composite rule. The base of rules can have none or several rules.
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
     * Verifies if the base of rules has a specific element representing
     * a rule. The rule can be a simple rule or a composite rule. The base of
     * rules can have none or several rules.
     * 
     * @param rule
     *          element representing a rule. This rule can be a simple rule or a
     *          composite rule.
     * @return
     *          true if the base of rules has the rule element.
     * @throws XMLException 
     *          if the element representing the rule is null.
     */
    public boolean hasRule(Et rule) throws XMLException {
        return rules.contains(rule);
    }


    /**
     * Verifies if the base of rules has a rule with a specific id. The rule can
     * be a simple rule or a composite rule. The base of rules can have none or
     * several rules.
     * 
     * @param id
     *          string representing the id of the element representing a rule.
     *          This rule can be a simple rule or a composite rule.
     * @return
     *          true if the base of rules has the rule element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasRule(String id) throws XMLException {
        return rules.get(id) != null;
    }


    /**
     * Verifies if the base of rules has at least one rule. The rule can
     * be a simple rule or a composite rule. The base of rules can have none or
     * several rules.
     * 
     * @return 
     *          true if the base of rules has at least rule.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Returns the list of rules that a base of rules have. The rule can
     * be a simple rule or a composite rule. The base of rules can have none or
     * several rules.
     * 
     * @return 
     *          element list with all rules.
     */
    public IdentifiableElementList<Et, T> getRules() {
        return rules;
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

        content = space + "<ruleBase";
        content += parseAttributes();

        if(hasImportBase() || hasRule()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</ruleBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        try{
            loadId(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("RuleBase:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadImportBases(el);
                    loadRules(el);
                    loadCompositeRules(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("RuleBase > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportBases(ident);
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
        
        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
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
    
    
    /**
     * Searches for a rule inside a ruleBase and its descendants.
     * The rule can be a rule or a compositeRule.
     * 
     * @param id
     *          id of the rule to be found.
     * @return 
     *          rule or null if no rule was found.
     */
    public Object findRule(String id) throws XMLException {
        Object result;
        
        if(!id.contains("#")){
            for(Et rule : rules){
                result = rule.findRule(id);
                if(result != null)
                    return result;
            }
        }
        else{
            int index = id.indexOf("#");
            String alias = id.substring(0, index);
            id = id.substring(index + 1);
            
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = findRuleReference(d, id);
                    if(ref instanceof NCLTestRule)
                        return createExternalRef(imp, (Et) ref);
                    else
                        return createExternalRef(imp, (Et) ((ExternalReferenceType) ref).getTarget());
                }
            }
            
            NCLImportedDocumentBase ib = (NCLImportedDocumentBase) ((NCLHead) getParent()).getImportedDocumentBase();
            for(Ei imp : (ElementList<Ei, NCLImportedDocumentBase>) ib.getImportNCLs()){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = findRuleReference(d, id);
                    if(ref instanceof NCLTestRule)
                        return createExternalRef(imp, (Et) ref);
                    else
                        return createExternalRef(imp, (Et) ((ExternalReferenceType) ref).getTarget());
                }
            }
        }
        
        
        return null;
    }
    
    
    protected Object findRuleReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLRuleBase base = (NCLRuleBase) head.getRuleBase();
        if(base == null)
            throw new NCLParsingException("Could not find document ruleBase element");

        Object result = base.findRule(id);

        if(result == null)
            throw new NCLParsingException("Could not find rule in ruleBase with id: " + id);
        
        return result;
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
     * Function to create a reference to a rule.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a rule.
     */
    protected ExternalReferenceType createExternalRef(Ei imp, Et ref) throws XMLException {
        return new ExternalReferenceType(imp, ref);
    }
}
