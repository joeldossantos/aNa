/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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

import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class NCLCompoundConditionPrototype<T extends NCLCompoundConditionPrototype, P extends NCLElement, I extends XMLElementImpl, Ec extends NCLCondition, Es extends NCLStatement, Ep extends NCLConnectorParamPrototype>
        extends XMLElementPrototype<Ec, P, I> implements NCLCondition<Ec, P, Ep> {
    
    protected NCLConditionOperator operator;
    protected DoubleParamType<Ep, Ec> delay;
    protected ElementList<Ec, T> conditions;
    protected ElementList<Es, T> statements;
    

    /**
     * Construtor do elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundConditionPrototype() throws XMLException {
        super();
        conditions = new ElementList<Ec, T>();
        statements = new ElementList<Es, T>();
    }


    /**
     * Determina o operador da condição composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLConditionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a condição composta.
     *
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLConditionOperator getOperator() {
        return operator;
    }


    /**
     * Adiciona uma condição a condição composta.
     * 
     * @param condition
     *          elemento representando a condição a ser adicionada
     * @return
     *          verdadeiro se a condição foi adicionada.
     *
     * @see ArrayList#add
     */
    public boolean addCondition(Ec condition) throws XMLException {
        return conditions.add(condition, (T) this);
    }


    /**
     * Remove uma condição a condição composta.
     *
     * @param condition
     *          elemento representando a condição a ser removida
     * @return
     *          verdadeiro se a condição foi removida.
     *
     * @see ArrayList#remove
     */
    public boolean removeCondition(Ec condition) throws XMLException {
        return conditions.remove(condition);
    }

    
    /**
     * Verifica se a condição composta possui uma condição.
     * 
     * @param condition
     *          elemento representando a condição a ser verificada
     * @return
     *          verdadeiro se a condição existe.
     */
    public boolean hasCondition(Ec condition) throws XMLException {
        return conditions.contains(condition);
    }

    
    /**
     * Verifica se a condição composta possui alguma condição.
     *
     * @return
     *          verdadeiro se a condição composta possui alguma condição.
     */
    public boolean hasCondition() {
        return !conditions.isEmpty();
    }


    /**
     * Retorna as condições da condição composta.
     *
     * @return
     *          list contendo as condições da condição composta.
     */
    public ElementList<Ec, T> getConditions() {
        return conditions;
    }


    /**
     * Adiciona uma assertiva a condição composta.
     * 
     * @param statement
     *          elemento representando a assertiva a ser adicionada.
     * @return
     *          verdadeiro se a assertiva foi adicionada.
     *
     * @see ArrayList#add
     */
    public boolean addStatement(Es statement) throws XMLException {
        return statements.add(statement, (T) this);
    }


    /**
     * Remove uma assertiva da condição composta.
     *
     * @param statement
     *          elemento representando a assertiva a ser removida.
     * @return
     *          verdadeiro se a assertiva foi removida.
     *
     * @see ArrayList#remove
     */
    public boolean removeStatement(Es statement) throws XMLException {
        return statements.remove(statement);
    }


    /**
     * Verifica se a condição composta possui uma assertiva.
     *
     * @param statement
     *          elemento representando a assertiva a ser verificada.
     * @return
     *          verdadeiro se a assertiva existe.
     */
    public boolean hasStatement(Es statement) throws XMLException {
        return statements.contains(statement);
    }

    
    /**
     * Verifica se a condição composta possui pelo menos uma assertiva.
     *
     * @return
     *          verdadeiro se a condição composta possuir pelo menos uma assertiva.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }


    /**
     * Retorna as assertivas da condição composta.
     *
     * @return
     *          lista contendo as assertivas da condição composta.
     */
    public ElementList<Es, T> getStatements() {
        return statements;
    }


    public void setDelay(DoubleParamType<Ep, Ec> delay) {
        this.delay = delay;
    }


    public DoubleParamType<Ep, Ec> getDelay() {
        return delay;
    }


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


    protected String parseConditions(int ident) {
        if(!hasCondition())
            return "";
        
        String content = "";
        for(Ec aux : conditions)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseStatements(int ident) {
        if(!hasStatement())
            return "";
        
        String content = "";
        for(Es aux : statements)
            content += aux.parse(ident);
        
        return content;
    }
    
    
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
