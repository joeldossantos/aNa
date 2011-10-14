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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCompoundConditionPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.ArrayList;


/**
 * Esta classe define o elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma condição composta de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCompoundCondition<T extends NCLCompoundCondition, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCondition, Es extends NCLStatement, Ep extends NCLConnectorParam>
        extends NCLCompoundConditionPrototype<T, P, I, Ec, Es, Ep> implements NCLCondition<Ec, P, Ep> {
    

    /**
     * Construtor do elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundCondition() {}


    /**
     * Determina o operador da condição composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    @Override
    public void setOperator(NCLConditionOperator operator) {
        NCLConditionOperator aux = this.operator;
        super.setOperator(operator);
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
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
    @Override
    public boolean addCondition(Ec condition) throws XMLException {
        if(super.addCondition(condition)){
            impl.notifyInserted(NCLElementSets.CONDITIONS, condition);
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
    @Override
    public boolean removeCondition(Ec condition) throws XMLException {
        if(super.removeCondition(condition)){
            impl.notifyRemoved(NCLElementSets.CONDITIONS, condition);
            return true;
        }
        return false;
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
    @Override
    public boolean addStatement(Es statement) throws XMLException {
        if(super.addStatement(statement)){
            impl.notifyInserted(NCLElementSets.STATEMENTS, statement);
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
    @Override
    public boolean removeStatement(Es statement) throws XMLException {
        if(super.removeStatement(statement)){
            impl.notifyRemoved(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
    }


    @Override
    public void setDelay(DoubleParamType delay) {
        DoubleParamType aux = this.delay;
        super.setDelay(delay);
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("compoundCondition") && !insideCondition){
//                cleanWarnings();
//                cleanErrors();
//                insideCondition = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("operator")){
//                        for(NCLConditionOperator o : NCLConditionOperator.values()){
//                            if(o.toString().equals(attributes.getValue(i)))
//                                setOperator(o);
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("delay")){
//                        String var = attributes.getValue(i);
//                        if(var.contains("$")){
//                            var = var.substring(1);
//                            setDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                        }
//                        else{
//                            var = var.substring(0, var.length() - 1);
//                            setDelay(new Integer(var));
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("simpleCondition")){
//                C child = createSimpleCondition();
//                child.startElement(uri, localName, qName, attributes);
//                addCondition(child);
//            }
//            else if(localName.equals("compoundCondition") && insideCondition){
//                C child = createCompoundCondition();
//                child.startElement(uri, localName, qName, attributes);
//                addCondition(child);
//            }
//            else if(localName.equals("assessmentStatement")){
//                S child = createAssessmentStatement();
//                child.startElement(uri, localName, qName, attributes);
//                addStatement(child);
//            }
//            else if(localName.equals("compoundStatement")){
//                S child = createCompoundStatement();
//                child.startElement(uri, localName, qName, attributes);
//                addStatement(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getParamDelay() != null)
//                setDelay(parameterReference(getParamDelay().getId()));
//        }
//
//        if(hasCondition()){
//            for(C condition : conditions){
//                condition.endDocument();
//                addWarning(condition.getWarnings());
//                addError(condition.getErrors());
//            }
//        }
//        if(hasStatement()){
//            for(S statement : statements){
//                statement.endDocument();
//                addWarning(statement.getWarnings());
//                addError(statement.getErrors());
//            }
//        }
//    }
//
//
//    private P parameterReference(String id) {
//        NCLElementImpl connector = getParent();
//
//        while(!(connector instanceof NCLCausalConnector)){
//            connector = connector.getParent();
//            if(connector == null){
//                addWarning("Could not find a parent connector");
//                return null;
//            }
//        }
//
//        Set<P> params = ((NCLCausalConnector) connector).getConnectorParams();
//        for(P param : params){
//            if(param.getId().equals(id))
//                return param;
//        }
//
//        addWarning("Could not find connectorParam in connector with id: " + id);
//        return null;
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>simpleCondition</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>simpleCondition</i>.
//     */
//    protected C createSimpleCondition() {
//        return (C) new NCLSimpleCondition(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>compoundCondition</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>compoundCondition</i>.
//     */
//    protected C createCompoundCondition() {
//        return (C) new NCLCompoundCondition(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>assessmentStatement</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>assessmentStatement</i>.
//     */
//    protected S createAssessmentStatement() {
//        return (S) new NCLAssessmentStatement(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>compoundStatement</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>compoundStatement</i>.
//     */
//    protected S createCompoundStatement() {
//        return (S) new NCLCompoundStatement(getReader(), this);
//    }
}
