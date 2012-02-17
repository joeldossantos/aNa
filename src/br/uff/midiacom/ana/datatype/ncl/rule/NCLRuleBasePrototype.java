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

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public abstract class NCLRuleBasePrototype<T extends NCLRuleBasePrototype,
                                           P extends NCLElement,
                                           I extends NCLElementImpl,
                                           Et extends NCLTestRule,
                                           Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected IdentifiableElementList<Et, T> rules;
    protected ElementList<Ei, T> imports;


    /**
     * Construtor do elemento <i>ruleBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLRuleBasePrototype() throws XMLException {
        super();
        rules = new IdentifiableElementList<Et, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Adiciona uma regra a base de regras.
     *
     * @param rule
     *          elemento representando a regra a ser adicionada.
     * @return
     *          verdadeiro se a regra foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRule(Et rule) throws XMLException {
        if(rules.add(rule, (T) this)){
            impl.notifyInserted(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    /**
     * Remove uma regra da base de regras.
     *
     * @param rule
     *          elemento representando a regra a ser removida.
     * @return
     *          verdadeiro se a regra foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRule(Et rule) throws XMLException {
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
     * Verifica se a base de regras possui uma regra.
     *
     * @param rule
     *          elemento representando a regra a ser verificada.
     * @return
     *          verdadeiro se a regra existir.
     */
    public boolean hasRule(Et rule) throws XMLException {
        return rules.contains(rule);
    }


    public boolean hasRule(String id) throws XMLException {
        return rules.get(id) != null;
    }


    /**
     * Verifica se a base de regras possui alguma regra.
     *
     * @return
     *          verdadeiro se a base de regras possui alguma regra.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Retorna as regras da base de regras.
     *
     * @return
     *          lista contendo as regras da base de regras.
     */
    public IdentifiableElementList<Et, T> getRules() {
        return rules;
    }


    /**
     * Adiciona um importador de base à base de regras.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(imports.add(importBase, (T) this)){
            impl.notifyInserted(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Remove um importador de base da base de regras.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(imports.remove(importBase)){
            impl.notifyRemoved(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a base de regras contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de regras possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de regras possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de regras.
     *
     * @return
     *          lista contendo os importadores de base da base de regras.
     */
    public ElementList<Ei, T> getImportBases() {
        return imports;
    }
}
