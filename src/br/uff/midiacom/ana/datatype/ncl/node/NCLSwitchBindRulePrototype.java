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
package br.uff.midiacom.ana.datatype.ncl.node;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLTestRule;
import br.uff.midiacom.xml.XMLElementPrototype;


public class NCLSwitchBindRulePrototype<T extends NCLSwitchBindRulePrototype, P extends NCLElement, En extends NCLNode, Er extends NCLTestRule> extends XMLElementPrototype<T, P> implements NCLElement<T, P> {

    protected En constituent;
    protected Er rule;


    /**
     * Construtor do elemento <i>bindRule</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSwitchBindRulePrototype() {}

    
    /**
     * Atribui um constituent ao bind.
     *
     * @param constituent
     *          elemento representando o nó mapeado pelo bind.
     */
    public void setConstituent(En constituent) {
        this.constituent = constituent;
    }


    /**
     * Retorna o constituent do bind.
     *
     * @return
     *          elemento representando o nó mapeado pelo bind.
     */
    public En getConstituent() {
        return constituent;
    }


    /**
     * Atribui uma regra de avaliação ao bind.
     *
     * @param rule
     *          elemento representando a regra de avaliação do bind.
     */
    public void setRule(Er rule) {
        this.rule = rule;
    }


    /**
     * Retorna a regra de avaliação do bind.
     *
     * @return
     *          elemento representando a regra de avaliação do bind.
     */
    public Er getRule() {
        return rule;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<bindRule";
        if(getRule() != null)
            content += " rule='" + getRule().getId() + "'";
        if(getConstituent() != null)
            content += " constituent='" + getConstituent().getId() + "'";
        content += "/>\n";


        return content;
    }


    public boolean compare(T other) {
        boolean comp = true;
        
        String this_sb, other_sb;

        // Compara pela regra
        if(getRule() == null) this_sb = ""; else this_sb = getRule().getId();
        if(other.getRule() == null) other_sb = ""; else other_sb = other.getRule().getId();
        comp &= this_sb.equals(other_sb);

        // Compara pelo constituent
        if(getConstituent() == null) this_sb = ""; else this_sb = getConstituent().getId();
        if(other.getConstituent() == null) other_sb = ""; else other_sb = other.getConstituent().getId();
        comp &= this_sb.equals(other_sb);

        return comp;
    }
}
