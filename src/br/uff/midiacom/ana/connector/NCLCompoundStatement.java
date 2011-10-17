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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCompoundStatementPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.ArrayList;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma assertiva composta de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCompoundStatement<T extends NCLCompoundStatement, P extends NCLElement, I extends NCLElementImpl, Es extends NCLStatement>
        extends NCLCompoundStatementPrototype<T, P, I, Es> implements NCLStatement<Es, P> {


    /**
     * Construtor do elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundStatement() {}

    
    /**
     * Determina o operador da assertiva composta.
     * 
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    @Override
    public void setOperator(NCLOperator operator) {
        NCLOperator aux = this.operator;
        super.setOperator(operator);
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
        
    /**
     * Determina se a assertiva composta está negada.
     * 
     * @param isNegated
     *          booleano que define se a assertiva está negada.
     */
    @Override
    public void setIsNegated(Boolean isNegated) {
        Boolean aux = this.isNegated;
        super.setIsNegated(isNegated);
        impl.notifyAltered(NCLElementAttributes.ISNEGATED, aux, isNegated);
    }
    
        
    /**
     * Adiciona uma assertiva a assertiva composta.
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
     * Remove uma assertiva da assertiva composta.
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
    

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        if(localName.equals("compoundStatement") && !insideStatement){
//            cleanWarnings();
//            cleanErrors();
//            insideStatement = true;
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("operator")){
//                    for(NCLOperator o : NCLOperator.values()){
//                        if(o.toString().equals(attributes.getValue(i)))
//                            setOperator(o);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("isNegated"))
//                    setIsNegated(new Boolean(attributes.getValue(i)));
//            }
//        }
//        else if(localName.equals("assessmentStatement")){
//            S child = createAssessmentStatement();
//            child.startElement(uri, localName, qName, attributes);
//            addStatement(child);
//        }
//        else if(localName.equals("compoundStatement") && insideStatement){
//            S child = createCompoundStatement();
//            child.startElement(uri, localName, qName, attributes);
//            addStatement(child);
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(hasStatement()){
//            for(S statement : statements){
//                statement.endDocument();
//                addWarning(statement.getWarnings());
//                addError(statement.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


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
