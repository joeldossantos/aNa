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
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLOperator;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a connector compound statement.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>operator</i> - compound statement operator. This attribute is required.</li>
 *  <li><i>isNegateed</i> - indicates if the statement is negated. This attribute
 *                          is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>assessmentStatement</i> - element representing a connector statement. The
 *                                   compound statement can have none or several
 *                                   statement elements.</li>
 *  <li><i>compundStatement</i> - element representing a connector statement. The
 *                                compound statement can have none or several
 *                                statement elements.</li>
 * </ul>
 * 
 * Note that the compound statement must have at least two statements.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Es> 
 */
public class NCLCompoundStatement<T extends NCLElement,
                                  Es extends NCLStatement,
                                  Er extends NCLRoleElement>
        extends NCLElementPrototype<T>
        implements NCLStatement<T, Er> {

    protected NCLOperator operator;
    protected Boolean isNegated;
    protected ElementList<Es> statements;


    /**
     * Compound statement element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompoundStatement() {
        super();
        statements = new ElementList<Es>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Es aux : statements) {
            ((NCLElementPrototype) aux).setDoc(doc);
        }
    }
    
    
    /**
     * Sets the compound statement operator. This attribute is required and can
     * not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound statement children elements.
     * Its possible values are "and" and "or" and are defined in the enumeration
     * <i>NCLOperator</i>.
     * 
     * <br/>
     * 
     * Using an "and" operator means that all statements of the compound statement
     * must be satisfied at the same time. Using an "or" operator means at least
     * one statement of the compound statement must be satisfied.
     *
     * @param operator
     *          element representing the compound statement operator from the
     *          enumeration <i>NCLOperator</i>.
     * @throws XMLException 
     *          if the element representing the operator is null.
     */
    public void setOperator(NCLOperator operator) throws XMLException {
        if(operator == null)
            throw new XMLException("Null operator.");
        
        NCLOperator aux = this.operator;
        this.operator = operator;
        notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
    
    /**
     * Returns the compound statement operator or <i>null</i> if the attribute is
     * not defined.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound statement children elements.
     * Its possible values are "and" and "or" and are defined in the enumeration
     * <i>NCLConditionOperator</i>.
     * 
     * <br/>
     * 
     * Using an "and" operator means that all statements of the compound statement
     * must be satisfied at the same time. Using an "or" operator means at least
     * one statement of the compound statement must be satisfied.
     * 
     * @return 
     *          element representing the compound statement operator from the
     *          enumeration <i>NCLOperator</i> or <i>null</i> if the attribute
     *          is not defined.
     */
    public NCLOperator getOperator() {
        return operator;
    }
    
    
    /**
     * Sets the attribute that indicates if the statement is negated. This
     * attribute is optional. Set the isNegated to <i>null</i> to erase an
     * inNegated already defined.
     * 
     * <br/>
     * 
     * If this attribute is true, that means that the compound statement "formula"
     * is negated.
     * 
     * @param isNegated
     *          boolean indicating if the compound statement is negated or
     *          <i>null</i> to erase an isNegated already defined.
     */
    public void setIsNegated(Boolean isNegated) throws XMLException {
        Boolean aux = this.isNegated;
        this.isNegated = isNegated;
        notifyAltered(NCLElementAttributes.ISNEGATED, aux, isNegated);
    }
    
    
    /**
     * Sets the attribute that indicates if the statement is negated or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * If this attribute is true, that means that the compound statement "formula"
     * is negated.
     * 
     * @return
     *          boolean indicating if the compound statement is negated or
     *          <i>null</i> if the attribute is not defined.
     */
    public Boolean getIsNegated() {
        return isNegated;
    }
    
    
    /**
     * Adds a statement to the compound statement. The compound statement must
     * have at least two statement elements.
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
     * Removes a statement of the compound statement. The compound statement must
     * have at least two statement elements.
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
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the compound statement has a specific element representing a
     * statement. The compound statement must have at least two statement elements.
     * 
     * @param statement
     *          element representing a statement.
     * @return
     *          true if the compound statement has the statement element.
     * @throws XMLException 
     *          if the element representing the statement is null.
     */
    public boolean hasStatement(Es statement) throws XMLException {
        return statements.contains(statement);
    }
    
    
    /**
     * Verifies if the compound statement has at least one statement. The compound
     * statement must have at least two statement elements.
     * 
     * @return 
     *          true if the compound statement has at least one statement.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }
    
    
    /**
     * Returns the list of statements that a compound statement have. The compound
     * statement must have at least two statement elements.
     * 
     * @return 
     *          element list with all statements.
     */
    public ElementList<Es> getStatements() {
        return statements;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLCompoundStatement))
            return false;
        
        boolean comp = true;

        String this_stat, other_stat;
        NCLCompoundStatement other_comp = (NCLCompoundStatement) other;
        
        // Compara pelo operador
        if(getOperator() == null) this_stat = ""; else this_stat = getOperator().toString();
        if(other_comp.getOperator() == null) other_stat = ""; else other_stat = other_comp.getOperator().toString();
        comp &= this_stat.equals(other_stat);

        // Compara pelo isNegated
        if(getIsNegated() == null) this_stat = ""; else this_stat = getIsNegated().toString();
        if(other_comp.getIsNegated() == null) other_stat = ""; else other_stat = other_comp.getIsNegated().toString();
        comp &= this_stat.equals(other_stat);

        ElementList<Es> otherlist = ((NCLCompoundStatement) other).getStatements();
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

        content = space + "<compoundStatement";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</compoundStatement>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadOperator(element);
            loadIsNegated(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("CompoundStatement:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadAssessmentStatements(el);
                    loadCompoundStatements(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("CompoundStatement > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseOperator();
        content += parseIsNegated();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseStatements(ident);
        
        return content;
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
    
    
    protected String parseIsNegated() {
        Boolean aux = getIsNegated();
        if(aux != null)
            return " isNegated='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadIsNegated(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the isNegated (optional)
        att_name = NCLElementAttributes.ISNEGATED.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setIsNegated(Boolean.valueOf(att_var));
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
        
        operator = null;
        
        for(Es s : statements)
            s.clean();
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
