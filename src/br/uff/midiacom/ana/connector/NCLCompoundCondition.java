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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma condição composta de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCompoundCondition<C extends NCLCondition, S extends NCLStatement, P extends NCLConnectorParam> extends NCLElement implements NCLCondition<C, P> {
    
    private NCLConditionOperator operator;
    
    private List<C> conditions = new ArrayList<C>();
    private List<S> statements = new ArrayList<S>();
    private Integer delay;
    private P parDelay;

    private boolean insideCondition;
    

    /**
     * Construtor do elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundCondition() {}


    /**
     * Construtor do elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCompoundCondition(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideCondition = false;
    }


    /**
     * Determina o operador da condição composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLConditionOperator operator) {
        notifyAltered(NCLElementAttributes.OPERATOR, this.operator, operator);
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
    public boolean addCondition(C condition) {
        if(condition != null && conditions.add(condition)){
            //atribui este como parente da condicao
            condition.setParent(this);

            notifyInserted(NCLElementSets.CONDITIONS, condition);
            return true;
        }
        return false;
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
    public boolean removeCondition(C condition) {
        if(conditions.remove(condition)){
            //Se condition existe, retira o seu parentesco
            if(condition != null)
                condition.setParent(null);

            notifyRemoved(NCLElementSets.CONDITIONS, condition);
            return true;
        }
        return false;
    }

    
    /**
     * Verifica se a condição composta possui uma condição.
     * 
     * @param condition
     *          elemento representando a condição a ser verificada
     * @return
     *          verdadeiro se a condição existe.
     */
    public boolean hasCondition(C condition) {
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
    public List<C> getConditions() {
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
    public boolean addStatement(S statement) {
        if(statement != null && statements.add(statement)){
            //atribui este como parente do statement
            statement.setParent(this);

            notifyInserted(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
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
    public boolean removeStatement(S statement) {
        if(statements.remove(statement)){
            //Se statement existe, retira o seu parentesco
            if(statement != null)
                statement.setParent(null);

            notifyRemoved(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a condição composta possui uma assertiva.
     *
     * @param statement
     *          elemento representando a assertiva a ser verificada.
     * @return
     *          verdadeiro se a assertiva existe.
     */
    public boolean hasStatement(S statement) {
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
    public List<S> getStatements() {
        return statements;
    }


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if(delay != null && delay < 0)
            throw new IllegalArgumentException("Invalid delay");

        notifyAltered(NCLElementAttributes.DELAY, this.delay, delay);
        this.delay = delay;
        this.parDelay= null;
    }


    public void setDelay(P delay) {
        notifyAltered(NCLElementAttributes.DELAY, this.delay, delay);
        this.parDelay = delay;
        this.delay = null;
    }


    public Integer getDelay() {
        return delay;
    }


    public P getParamDelay() {
        return parDelay;
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
        if(getOperator() != null)
            content += " operator='" + getOperator().toString() + "'";
        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";
        if(getParamDelay() != null)
            content += " delay='$" + getParamDelay().getId() + "'";
        content += ">\n";

        if(hasCondition()){
            for(C condition : conditions)
                content += condition.parse(ident + 1);
        }
        if(hasStatement()){
            for(S statement : statements)
                content += statement.parse(ident + 1);
        }

        content += space + "</compoundCondition>\n";

        return content;
    }
    
    
    public int compareTo(C other) {
        int comp = 0;

        String this_cond, other_cond;
        NCLCompoundCondition other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundCondition))
            return 1;

        other_comp = (NCLCompoundCondition) other;
        
        // Compara pelo operador
        if(comp == 0){
            if(getOperator() == null) this_cond = ""; else this_cond = getOperator().toString();
            if(other_comp.getOperator() == null) other_cond = ""; else other_cond = other_comp.getOperator().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pelo delay
        if(comp == 0){
            int this_del, other_del;
            if(getDelay() == null) this_del = 0; else this_del = getDelay();
            if(other_comp.getDelay() == null) other_del = 0; else other_del = other_comp.getDelay();
            comp = this_del - other_del;
        }

        // Compara pelo delay (parametro)
        if(comp == 0){
            if(getParamDelay() == null && other_comp.getParamDelay() == null)
                comp = 0;
            else if(getParamDelay() != null && other_comp.getParamDelay() != null)
                comp = getParamDelay().compareTo(other_comp.getParamDelay());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamDelay() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara o número de condicoes
        if(comp == 0)
            comp = conditions.size() - ((List) other_comp.getConditions()).size();

        // Compara as condicoes
        if(comp == 0){
            Iterator it = other_comp.getConditions().iterator();
            for(NCLCondition c : conditions){
                NCLCondition other_c = (NCLCondition) it.next();
                comp = c.compareTo(other_c);
                if(comp != 0)
                    break;
            }
        }

        // Compara o número de statements
        if(comp == 0)
            comp = statements.size() - ((List) other_comp.getStatements()).size();

        // Compara as statements
        if(comp == 0){
            Iterator it = other_comp.getStatements().iterator();
            for(NCLStatement st : statements){
                NCLStatement other_st = (NCLStatement) it.next();
                comp = st.compareTo(other_st);
                if(comp != 0)
                    break;
            }
        }


        return comp;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("compoundCondition") && !insideCondition){
                cleanWarnings();
                cleanErrors();
                insideCondition = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("operator")){
                        for(NCLConditionOperator o : NCLConditionOperator.values()){
                            if(o.toString().equals(attributes.getValue(i)))
                                setOperator(o);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("delay")){
                        String var = attributes.getValue(i);
                        if(var.contains("$")){
                            var = var.substring(1);
                            setDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                        }
                        else{
                            var = var.substring(0, var.length() - 1);
                            setDelay(new Integer(var));
                        }
                    }
                }
            }
            else if(localName.equals("simpleCondition")){
                C child = createSimpleCondition();
                child.startElement(uri, localName, qName, attributes);
                addCondition(child);
            }
            else if(localName.equals("compoundCondition") && insideCondition){
                C child = createCompoundCondition();
                child.startElement(uri, localName, qName, attributes);
                addCondition(child);
            }
            else if(localName.equals("assessmentStatement")){
                S child = createAssessmentStatement();
                child.startElement(uri, localName, qName, attributes);
                addStatement(child);
            }
            else if(localName.equals("compoundStatement")){
                S child = createCompoundStatement();
                child.startElement(uri, localName, qName, attributes);
                addStatement(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getParamDelay() != null)
                setDelay(parameterReference(getParamDelay().getId()));
        }

        if(hasCondition()){
            for(C condition : conditions){
                condition.endDocument();
                addWarning(condition.getWarnings());
                addError(condition.getErrors());
            }
        }
        if(hasStatement()){
            for(S statement : statements){
                statement.endDocument();
                addWarning(statement.getWarnings());
                addError(statement.getErrors());
            }
        }
    }


    private P parameterReference(String id) {
        NCLElement connector = getParent();

        while(!(connector instanceof NCLCausalConnector)){
            connector = connector.getParent();
            if(connector == null){
                addWarning("Could not find a parent connector");
                return null;
            }
        }

        Set<P> params = ((NCLCausalConnector) connector).getConnectorParams();
        for(P param : params){
            if(param.getId().equals(id))
                return param;
        }

        addWarning("Could not find connectorParam in connector with id: " + id);
        return null;
    }


    /**
     * Função de criação do elemento filho <i>simpleCondition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleCondition</i>.
     */
    protected C createSimpleCondition() {
        return (C) new NCLSimpleCondition(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundCondition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundCondition</i>.
     */
    protected C createCompoundCondition() {
        return (C) new NCLCompoundCondition(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>assessmentStatement</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>assessmentStatement</i>.
     */
    protected S createAssessmentStatement() {
        return (S) new NCLAssessmentStatement(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundStatement</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundStatement</i>.
     */
    protected S createCompoundStatement() {
        return (S) new NCLCompoundStatement(getReader(), this);
    }
}
