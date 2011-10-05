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

import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLCompositeRulePrototype<T extends NCLTestRule, P extends NCLElement> extends NCLIdentifiableElementPrototype<T, P> implements NCLTestRule<T, P>, NCLIdentifiableElement<T, P> {

    protected NCLOperator operator;
    protected IdentifiableElementList<T, T> rules;


    /**
     * Construtor do elemento <i>compositeRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLCompositeRulePrototype(String id) throws XMLException {
        setId(id);
        rules = new IdentifiableElementList<T, T>();
    }


    /**
     * Atribui um operador a regra composta.
     *
     * @param operator
     *          elemento representando o operador da regra composta.
     */
    public void setOperator(NCLOperator operator) {
        this.operator = operator;
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
        return rules.add(rule, (T) this);
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
        return rules.remove(rule);
    }


    public boolean removeRule(String id) throws XMLException {
        return rules.remove(id);
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


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<compositeRule";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getOperator() != null)
            content += " operator='" + getOperator() + "'";
        content += ">\n";

        if(hasRule()){
            for(T rule : rules)
                content += rule.parse(ident + 1);
        }

        content += "</compositeRule>\n";

        return content;
    }
}
