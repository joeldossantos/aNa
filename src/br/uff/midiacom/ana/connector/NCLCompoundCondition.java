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
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a connector compound condition.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>operator</i> - compound condition operator. This attribute is required.</li>
 *  <li><i>delay</i> - delay waited by the condition. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>simpleCondition</i> - element representing a connector condition. The
 *                               compound condition can have none or several
 *                               condition elements.</li>
 *  <li><i>compundCondition</i> - element representing a connector condition. The
 *                                compound condition can have none or several
 *                                condition elements.</li>
 *  <li><i>assessmentStatement</i> - element representing a connector statement. The
 *                                   compound condition can have none or several
 *                                   statement elements.</li>
 *  <li><i>compundStatement</i> - element representing a connector statement. The
 *                                compound condition can have none or several
 *                                statement elements.</li>
 * </ul>
 * 
 * Note that the compound condition must have at least two conditions or statements.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec>
 * @param <Es>
 * @param <Ep>
 * @param <R> 
 */
public class NCLCompoundCondition<T extends NCLElement,
                                  Ec extends NCLCondition,
                                  Es extends NCLStatement,
                                  Ep extends NCLConnectorParam,
                                  Er extends NCLRoleElement>
        extends ParamElement<T>
        implements NCLCondition<T, Ep, Er> {
    
    protected NCLConditionOperator operator;
    protected Object delay;
    protected ElementList<Ec> conditions;
    protected ElementList<Es> statements;
    

    /**
     * Compound condition element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompoundCondition() {
        super();
        conditions = new ElementList<Ec>();
        statements = new ElementList<Es>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ec aux : conditions) {
            ((NCLElementPrototype) aux).setDoc(doc);
        }
        for (Es aux : statements) {
            ((NCLElementPrototype) aux).setDoc(doc);
        }
    }


    /**
     * Sets the compound condition operator. This attribute is required and can
     * not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound condition children elements.
     * Its possible values are "and" and "or" and are defined in the enumeration
     * <i>NCLConditionOperator</i>.
     * 
     * <br/>
     * 
     * Using an "and" operator means that all conditions of the compound condition
     * must be satisfied at the same time. Using an "or" operator means at least
     * one condition of the compound condition must be satisfied.
     *
     * @param operator
     *          element representing the compound condition operator from the
     *          enumeration <i>NCLConditionOperator</i>.
     * @throws XMLException 
     *          if the element representing the operator is null.
     */
    public void setOperator(NCLConditionOperator operator) throws XMLException {
        if(operator == null)
            throw new XMLException("Null operator.");
        
        NCLConditionOperator aux = this.operator;
        this.operator = operator;
        notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
    
    /**
     * Returns the compound condition operator or <i>null</i> if the attribute is
     * not defined.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound condition children elements.
     * Its possible values are "and" and "or" and are defined in the enumeration
     * <i>NCLConditionOperator</i>.
     * 
     * <br/>
     * 
     * Using an "and" operator means that all conditions of the compound condition
     * must be satisfied at the same time. Using an "or" operator means at least
     * one condition of the compound condition must be satisfied.
     * 
     * @return 
     *          element representing the compound condition operator from the
     *          enumeration <i>NCLConditionOperator</i> or <i>null</i> if the
     *          attribute is not defined.
     */
    public NCLConditionOperator getOperator() {
        return operator;
    }


    /**
     * Adds a condition to the compound condition. The compound condition must
     * have at least two condition or statement elements.
     * 
     * @param condition
     *          element representing a condition.
     * @return
     *          true if the element representing a condition was added.
     * @throws XMLException 
     *          if the element representing the condition is null.
     */
    public boolean addCondition(Ec condition) throws XMLException {
        if(conditions.add(condition)){
            notifyInserted((T) condition);
            condition.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes a condition of the compound condition. The compound condition must
     * have at least two condition or statement elements.
     * 
     * @param condition
     *          element representing a condition.
     * @return
     *          true if the element representing a condition was removed.
     * @throws XMLException 
     *          if the element representing the condition is null.
     */
    public boolean removeCondition(Ec condition) throws XMLException {
        if(condition instanceof ReferredElement && !((ReferredElement) condition).getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(conditions.remove(condition)){
            notifyRemoved((T) condition);
            return true;
        }
        return false;
    }

    
    /**
     * Verifies if the compound condition has a specific element representing a
     * condition. The compound condition must have at least two condition or
     * statement elements.
     * 
     * @param condition
     *          element representing a condition.
     * @return
     *          true if the compound condition has the condition element.
     * @throws XMLException 
     *          if the element representing the condition is null.
     */
    public boolean hasCondition(Ec condition) throws XMLException {
        return conditions.contains(condition);
    }

    
    /**
     * Verifies if the compound condition has at least one condition. The compound
     * condition must have at least two condition or statement elements.
     * 
     * @return 
     *          true if the compound condition has at least one condition.
     */
    public boolean hasCondition() {
        return !conditions.isEmpty();
    }


    /**
     * Returns the list of conditions that a compound condition have. The compound
     * condition must have at least two condition or statement elements.
     * 
     * @return 
     *          element list with all conditions.
     */
    public ElementList<Ec> getConditions() {
        return conditions;
    }


    /**
     * Adds a statement to the compound condition. The compound condition must
     * have at least two condition or statement elements.
     * 
     * @param statement
     *          element representing a statement.
     * @return
     *          true if the element representing a statement was added.
     * @throws XMLException 
     *          if the element representing the statement is null.
     */
    public boolean addStatement(Es statement) throws XMLException {
        if(statements.add(statement)){
            notifyInserted((T) statement);
            statement.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes a statement of the compound condition. The compound condition must
     * have at least two condition or statement elements.
     * 
     * @param statement
     *          element representing a statement.
     * @return
     *          true if the element representing a statement was removed.
     * @throws XMLException 
     *          if the element representing the statement is null.
     */
    public boolean removeStatement(Es statement) throws XMLException {
        if(statements.remove(statement)){
            notifyRemoved((T) statement);
            statement.setParent(null);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the compound condition has a specific element representing a
     * statement. The compound condition must have at least two condition or
     * statement elements.
     * 
     * @param statement
     *          element representing a statement.
     * @return
     *          true if the compound condition has the statement element.
     * @throws XMLException 
     *          if the element representing the statement is null.
     */
    public boolean hasStatement(Es statement) throws XMLException {
        return statements.contains(statement);
    }

    
    /**
     * Verifies if the compound condition has at least one statement. The compound
     * condition must have at least two condition or statement elements.
     * 
     * @return 
     *          true if the compound condition has at least one statement.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }


    /**
     * Returns the list of statements that a compound condition have. The compound
     * condition must have at least two condition or statement elements.
     * 
     * @return 
     *          element list with all statements.
     */
    public ElementList<Es> getStatements() {
        return statements;
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
        
        notifyAltered(NCLElementAttributes.DELAY, aux, delay);
    }


    @Override
    public Object getDelay() {
        return delay;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLCompoundCondition))
            return false;
        
        boolean comp = true;

        String this_cond, other_cond;
        NCLCompoundCondition other_comp = (NCLCompoundCondition) other;
        
        // Compara pelo operador
        if(getOperator() == null) this_cond = ""; else this_cond = getOperator().toString();
        if(other_comp.getOperator() == null) other_cond = ""; else other_cond = other_comp.getOperator().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pelo delay
        if(getDelay() == null) this_cond = ""; else this_cond = getDelay().toString();
        if(other_comp.getDelay() == null) other_cond = ""; else other_cond = other_comp.getDelay().toString();
        comp &= this_cond.equals(other_cond);

        ElementList otherlist = ((NCLCompoundCondition) other).getConditions();
        comp &= conditions.size() == otherlist.size();
        for (Ec aux : conditions) {
            try {
                comp &= otherlist.contains(aux);
            } catch (XMLException ex) {}
            if(!comp)
                break;
        }

        otherlist = ((NCLCompoundCondition) other).getStatements();
        comp &= statements.size() == otherlist.size();
        for (Es aux : statements) {
            try {
                comp &= otherlist.contains(aux);
            } catch (XMLException ex) {}
            if(!comp)
                break;
        }

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

        content = space + "<compoundCondition";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</compoundCondition>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadOperator(element);
            loadDelay(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("CompoundCondition:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadSimpleConditions(el);
                    loadCompoundConditions(el);
                    loadAssessmentStatements(el);
                    loadCompoundStatements(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("CompoundCondition > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseOperator();
        content += parseDelay();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseConditions(ident);
        content += parseStatements(ident);
        
        return content;
    }
    
    
    protected String parseOperator() {
        NCLConditionOperator aux = getOperator();
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
            setOperator(NCLConditionOperator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
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


    protected String parseConditions(int ident) {
        if(!hasCondition())
            return "";
        
        String content = "";
        for(Ec aux : conditions)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadSimpleConditions(Element element) throws XMLException {
        //create the simpleCondition
        if(element.getTagName().equals(NCLElementAttributes.SIMPLECONDITION.toString())){
            Ec inst = createSimpleCondition();
            addCondition(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundConditions(Element element) throws XMLException {
        // create the compoundCondition
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDCONDITION.toString())){
            Ec inst = createCompoundCondition();
            addCondition(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseStatements(int ident) {
        if(!hasStatement())
            return "";
        
        String content = "";
        for(Es aux : statements)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadAssessmentStatements(Element element) throws XMLException {
        //create the assessmentStatement
        if(element.getTagName().equals(NCLElementAttributes.ASSESSMENTSTATEMENT.toString())){
            Es inst = createAssessmentStatement();
            addStatement(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundStatements(Element element) throws XMLException {
        // create the compoundStatement
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDSTATEMENT.toString())){
            Es inst = createCompoundStatement();
            addStatement(inst);
            inst.load(element);
        }
    }
    
    
    @Override
    public Er findRole(String name) {
        Er result;
        
        for(Ec condition : conditions){
            result = (Er) condition.findRole(name);
            if(result != null)
                return result;
        }
        
        for(Es statement : statements){
            result = (Er) statement.findRole(name);
            if(result != null)
                return result;
        }
        
        return null;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(delay != null && delay instanceof NCLConnectorParam)
            ((Ep)delay).removeReference(this);
        
        operator = null;
        delay = null;
        
        for(Ec c : conditions)
            c.clean();
        
        for(Es s : statements)
            s.clean();
    }
    

    /**
     * Function to create the child element <i>simpleCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleCondition</i>.
     */
    protected Ec createSimpleCondition() throws XMLException {
        return (Ec) new NCLSimpleCondition();
    }


    /**
     * Function to create the child element <i>compoundCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundCondition</i>.
     */
    protected Ec createCompoundCondition() throws XMLException {
        return (Ec) new NCLCompoundCondition();
    }


    /**
     * Function to create the child element <i>assessmentStatement</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>assessmentStatement</i>.
     */
    protected Es createAssessmentStatement() throws XMLException {
        return (Es) new NCLAssessmentStatement();
    }


    /**
     * Function to create the child element <i>compoundStatement</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundStatement</i>.
     */
    protected Es createCompoundStatement() throws XMLException {
        return (Es) new NCLCompoundStatement();
    }
}
