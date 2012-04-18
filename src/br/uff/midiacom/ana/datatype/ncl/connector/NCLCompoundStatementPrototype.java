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

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


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
public abstract class NCLCompoundStatementPrototype<T extends NCLCompoundStatementPrototype,
                                                    P extends NCLElement,
                                                    I extends NCLElementImpl,
                                                    Es extends NCLStatement>
        extends NCLElementPrototype<Es, P, I>
        implements NCLStatement<Es, P> {

    protected NCLOperator operator;
    protected Boolean isNegated;
    protected ElementList<Es, T> statements;


    /**
     * Compound statement element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompoundStatementPrototype() throws XMLException {
        super();
        statements = new ElementList<Es, T>();
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
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
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
    public void setIsNegated(Boolean isNegated) {
        Boolean aux = this.isNegated;
        this.isNegated = isNegated;
        impl.notifyAltered(NCLElementAttributes.ISNEGATED, aux, isNegated);
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
        if(statements.add(statement, (T) this)){
            impl.notifyInserted(NCLElementSets.STATEMENTS, statement);
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
            impl.notifyRemoved(NCLElementSets.STATEMENTS, statement);
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
    public ElementList<Es, T> getStatements() {
        return statements;
    }
    
    
    @Override
    public boolean compare(Es other) {
        boolean comp = true;

        String this_stat, other_stat;
        NCLCompoundStatementPrototype other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundStatementPrototype))
            return false;

        other_comp = (NCLCompoundStatementPrototype) other;
        
        // Compara pelo operador
        if(getOperator() == null) this_stat = ""; else this_stat = getOperator().toString();
        if(other_comp.getOperator() == null) other_stat = ""; else other_stat = other_comp.getOperator().toString();
        comp &= this_stat.equals(other_stat);

        // Compara pelo isNegated
        if(getIsNegated() == null) this_stat = ""; else this_stat = getIsNegated().toString();
        if(other_comp.getIsNegated() == null) other_stat = ""; else other_stat = other_comp.getIsNegated().toString();
        comp &= this_stat.equals(other_stat);

        // Compara o número de statements
        comp &= statements.size() == other_comp.getStatements().size();

        // Compara as statements
        Iterator it = other_comp.getStatements().iterator();
        for(NCLStatement st : statements){
            if(!it.hasNext())
                continue;
            NCLStatement other_st = (NCLStatement) it.next();
            comp &= st.compare(other_st);
            if(comp)
                break;
        }


        return comp;
    }
}
