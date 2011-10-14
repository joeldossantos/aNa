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
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLAssessmentStatementPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLAttributeAssessmentPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLValueAssessmentPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma assertiva de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLAssessmentStatement<T extends NCLAssessmentStatement, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLAttributeAssessment, Ev extends NCLValueAssessment>
        extends NCLAssessmentStatementPrototype<T, P, I, Ea, Ev> implements NCLStatement<T, P> {


    /**
     * Construtor do elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLAssessmentStatement() {}


    /**
     * Determina o comparador da assertiva.
     * 
     * @param comparator
     *          comparador utilizado pela assertiva.
     */
    @Override
    public void setComparator(NCLComparator comparator) {
        NCLComparator aux = this.comparator;
        super.setComparator(comparator);
        impl.notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }
    
    
    /**
     * Determina um valor de comparação a assertiva.
     * 
     * @param value
     *          String representando o valor de comparação a ser utilizado.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor for uma String vazia.
     *
     * @see NCLValueAssessment
     */
    @Override
    public void setValueAssessment(Ev value) {
        Ev aux = this.valueAssessment;
        super.setValueAssessment(value);
        impl.notifyAltered(NCLElementAttributes.VALUEASSESSMENT, aux, value);
    }
    
        
    /**
     * Adiciona um atributo de comparação a assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser adicionado.
     * @return
     *          verdadeiro se o atributo foi adicionado.
     * @throws java.lang.Exception
     *          se o número máximo de atributos for ultrapassado.
     *
     * @see ArrayList#add
     */
    @Override
    public boolean addAttributeAssessment(Ea attribute) throws Exception {
        if(super.addAttributeAssessment(attribute)){
            impl.notifyInserted(NCLElementSets.ATTRIBUTEASSESSMENTS, attribute);
            return true;
        }
        return false;
    }
    
    
    /**
     * Remove um atributo de comparação da assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser removido.
     * @return
     *          verdadeiro se o atributo for removido.
     *
     * @see ArrayList#remove
     */
    @Override
    public boolean removeAttributeAssessment(Ea attribute) throws XMLException {
        if(super.removeAttributeAssessment(attribute)){
            impl.notifyRemoved(NCLElementSets.ATTRIBUTEASSESSMENTS, attribute);
            return true;
        }
        return false;
    }
    
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("assessmentStatement")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("comparator")){
//                        for(NCLComparator c : NCLComparator.values()){
//                            if(c.toString().equals(attributes.getValue(i)))
//                                setComparator(c);
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("attributeAssessment")){
//                A child = createAttributeAssessment();
//                child.startElement(uri, localName, qName, attributes);
//                addAttributeAssessment(child);
//            }
//            else if(localName.equals("valueAssessment")){
//                V child = createValueAssessment();
//                child.startElement(uri, localName, qName, attributes);
//                setValueAssessment(child);
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(hasAttributeAssessment()){
//            for(A attribute : attributeAssessments){
//                attribute.endDocument();
//                addWarning(attribute.getWarnings());
//                addError(attribute.getErrors());
//            }
//        }
//        if(getValueAssessment() != null){
//            getValueAssessment().endDocument();
//            addWarning(getValueAssessment().getWarnings());
//            addError(getValueAssessment().getErrors());
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
//     * Função de criação do elemento filho <i>attributeAssessment</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>attributeAssessment</i>.
//     */
//    protected A createAttributeAssessment() {
//        return (A) new NCLAttributeAssessment(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>valueAssessment</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>valueAssessment</i>.
//     */
//    protected V createValueAssessment() {
//        return (V) new NCLValueAssessment(getReader(), this);
//    }
}
