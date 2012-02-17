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
package br.uff.midiacom.ana.datatype.ncl.rule;

import br.uff.midiacom.ana.datatype.auxiliar.ReferenceType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import java.util.TreeSet;


public abstract class NCLCompositeRulePrototype<T extends NCLTestRule,
                                                P extends NCLElement,
                                                I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLTestRule<T, P> {

    protected NCLOperator operator;
    protected IdentifiableElementList<T, T> rules;
    
    protected TreeSet<ReferenceType> references;


    /**
     * Construtor do elemento <i>compositeRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLCompositeRulePrototype(String id) throws XMLException {
        super();
        setId(id);
        rules = new IdentifiableElementList<T, T>();
        references = new TreeSet<ReferenceType>();
    }


    public NCLCompositeRulePrototype() throws XMLException {
        super();
        rules = new IdentifiableElementList<T, T>();
        references = new TreeSet<ReferenceType>();
    }


    /**
     * Atribui um operador a regra composta.
     *
     * @param operator
     *          elemento representando o operador da regra composta.
     */
    public void setOperator(NCLOperator operator) {
        NCLOperator aux = this.operator;
        this.operator = operator;
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }


    /**
     * Retorna o operador da regra composta.
     *
     * @return
     *          elemento representando o operador da regra composta.
     */
    public NCLOperator getOperator() {
        return operator;
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
    public boolean addRule(T rule) throws XMLException {
        if(rules.add(rule, (T) this)){
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
    public boolean removeRule(T rule) throws XMLException {
        if(rules.remove(rule)){
            impl.notifyRemoved(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    public boolean removeRule(String id) throws XMLException {
        if(rules.remove(id)){
            impl.notifyRemoved(NCLElementSets.RULES, id);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a regra composta possui uma regra.
     *
     * @param rule
     *          elemento representando a regra a ser verificada.
     * @return
     *          verdadeiro se a regra existir.
     */
    public boolean hasRule(T rule) throws XMLException {
        return rules.contains(rule);
    }


    public boolean hasRule(String id) throws XMLException {
        return rules.get(id) != null;
    }


    /**
     * Verifica se a regra composta possui alguma regra.
     *
     * @return
     *          verdadeiro se a regra composta possui alguma regra.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Retorna as regras da regra composta.
     *
     * @return
     *          lista contendo as regras da regra composta.
     */
    public IdentifiableElementList<T, T> getRules() {
        return rules;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) {
        return references.remove(reference);
    }
    
    
    @Override
    public TreeSet<ReferenceType> getReferences() {
        return references;
    }
}
