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
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


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
public abstract class NCLCompoundConditionPrototype<T extends NCLCompoundConditionPrototype,
                                                    P extends NCLElement,
                                                    I extends NCLElementImpl,
                                                    Ec extends NCLCondition,
                                                    Es extends NCLStatement,
                                                    Ep extends NCLConnectorParamPrototype,
                                                    R extends ConParamReference>
        extends NCLElementPrototype<Ec, P, I>
        implements NCLCondition<Ec, P, Ep, R> {
    
    protected NCLConditionOperator operator;
    protected DoubleParamType<Ep, Ec, R> delay;
    protected ElementList<Ec, T> conditions;
    protected ElementList<Es, T> statements;
    

    /**
     * Compound condition element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompoundConditionPrototype() throws XMLException {
        super();
        conditions = new ElementList<Ec, T>();
        statements = new ElementList<Es, T>();
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
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
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
        if(conditions.add(condition, (T) this)){
            impl.notifyInserted(NCLElementSets.CONDITIONS, condition);
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
        if(conditions.remove(condition)){
            impl.notifyRemoved(NCLElementSets.CONDITIONS, condition);
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
    public ElementList<Ec, T> getConditions() {
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
        if(statements.add(statement, (T) this)){
            impl.notifyInserted(NCLElementSets.STATEMENTS, statement);
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
            impl.notifyRemoved(NCLElementSets.STATEMENTS, statement);
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
    public ElementList<Es, T> getStatements() {
        return statements;
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
        NCLCompoundConditionPrototype other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundConditionPrototype))
            return false;

        other_comp = (NCLCompoundConditionPrototype) other;
        
        // Compara pelo operador
        if(getOperator() == null) this_cond = ""; else this_cond = getOperator().toString();
        if(other_comp.getOperator() == null) other_cond = ""; else other_cond = other_comp.getOperator().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pelo delay
        if(getDelay() == null) this_cond = ""; else this_cond = getDelay().parse();
        if(other_comp.getDelay() == null) other_cond = ""; else other_cond = other_comp.getDelay().parse();
        comp &= this_cond.equals(other_cond);

        // Compara o número de condicoes
        comp &= conditions.size() == other_comp.getConditions().size();

        // Compara as condicoes
        Iterator it = other_comp.getConditions().iterator();
        for(NCLCondition c : conditions){
            if(!it.hasNext())
                continue;
            NCLCondition other_c = (NCLCondition) it.next();
            comp &= c.compare(other_c);
            if(comp)
                break;
        }

        // Compara o número de statements
        comp &= statements.size() == other_comp.getStatements().size();

        // Compara as statements
        it = other_comp.getStatements().iterator();
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
