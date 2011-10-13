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
package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLCompositeRulePrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;
import org.w3c.dom.Element;


/**
 * Esta classe define uma regra de teste composta da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCompositeRule<T extends NCLTestRule, P extends NCLElement, I extends NCLElementImpl>
        extends NCLCompositeRulePrototype<T, P, I> implements NCLTestRule<T, P> {


    /**
     * Construtor do elemento <i>compositeRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLCompositeRule(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Atribui um operador a regra composta.
     *
     * @param operator
     *          elemento representando o operador da regra composta.
     */
    @Override
    public void setOperator(NCLOperator operator) {
        NCLOperator aux = this.operator;
        super.setOperator(operator);
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }


    /**
     * Adiciona uma regra a regra composta.
     *
     * @param rule
     *          elemento representando a regra a ser adicionada.
     * @return
     *          verdadeiro se a regra foi adicionada.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addRule(T rule) throws XMLException {
        if(super.addRule(rule)){
            impl.notifyInserted(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    /**
     * Remove uma regra da regra composta.
     *
     * @param rule
     *          elemento representando a regra a ser removida.
     * @return
     *          verdadeiro se a regra foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeRule(T rule) throws XMLException {
        if(super.removeRule(rule)){
            impl.notifyRemoved(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("compositeRule") && !insideRule){
//                cleanWarnings();
//                cleanErrors();
//                insideRule = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("operator")){
//                        for(NCLOperator op : NCLOperator.values()){
//                        if(op.toString().equals(attributes.getValue(i)))
//                            setOperator(op);
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("compositeRule") && insideRule){
//                // compositeRule e um elemento interno
//                T child = createCompositeRule();
//                child.startElement(uri, localName, qName, attributes);
//                addRule(child);
//            }
//            else if(localName.equals("rule")){
//                T child = createRule();
//                child.startElement(uri, localName, qName, attributes);
//                addRule(child);
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
//        if(hasRule()){
//            for(T rule : rules){
//                rule.endDocument();
//                addWarning(rule.getWarnings());
//                addError(rule.getErrors());
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


    /**
     * Função de criação do elemento filho <i>compositeRule</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compositeRule</i>.
     */
    protected T createCompositeRule() {
//        return (T) new NCLCompositeRule(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>rule</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>rule</i>.
     */
    protected T createRule() {
//        return (T) new NCLRule(getReader(), this);
    }
}
