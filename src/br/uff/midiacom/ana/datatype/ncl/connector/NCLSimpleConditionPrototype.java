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
import br.uff.midiacom.ana.datatype.auxiliar.KeyParamType;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLEventTransition;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.datatype.number.MaxType;


public class NCLSimpleConditionPrototype<T extends NCLSimpleConditionPrototype, P extends NCLElement, I extends XMLElementImpl, Er extends NCLRolePrototype, Ep extends NCLConnectorParamPrototype>
        extends XMLElementPrototype<T, P, I> implements NCLCondition<T, P, Ep> {

    protected KeyParamType<Ep> key;
    protected Integer min;
    protected MaxType max;
    protected NCLConditionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventTransition transition;
    protected Er role;
    protected DoubleParamType<Ep> delay;
    

    /**
     * Construtor do elemento <i>simpleCondition</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSimpleConditionPrototype() {}


    /**
     * Determina o número mínimo de binds que devem usar essa condição.
     * 
     * @param min
     *          inteiro positivo representando o número mínimo.
     */
    public void setMin(Integer min) {
        if(min != null && min < 0)
            throw new IllegalArgumentException("Invalid min");

        this.min = min;
    }


    /**
     * Retorna o número mínimo de binds que devem usar essa condição.
     *
     * @return
     *          inteiro positivo representando o número mínimo.
     */    
    public Integer getMin() {
        return min;
    }
    

    /**
     * Determina o número máximo de binds que devem usar essa condição.
     *
     * @param max
     *          inteiro positivo representando o número máximo ou um inteiro negativo
     *          caso o número máximo seja a String "umbouded".
     */
    public void setMax(MaxType max) {
        this.max = max;
    }


    /**
     * Retorna o número máximo de binds que devem usar essa condição.
     *
     * @return
     *          inteiro positivo representando o número máximo ou -1
     *          caso o número máximo seja a String "umbouded".
     */
    public MaxType getMax() {
        return max;
    }


    /**
     * Determina como serão avaliados o conjunto de binds que usam essa condição.
     *
     * @param qualifier
     *          operador lógico que representa como os binds serão avaliados.
     */
    public void setQualifier(NCLConditionOperator qualifier) {
        this.qualifier = qualifier;
    }


    /**
     * Retorna como serão avaliados o conjunto de binds que usam essa condição.
     *
     * @return
     *          operador lógico que representa como os binds serão avaliados.
     */
    public NCLConditionOperator getQualifier() {
        return qualifier;
    }


    /**
     * Determina o nome do papel de condição seguindo um dos nomes padrões.
     *
     * @param role
     *          elemento representando o nome do papel.
     */
    public void setRole(Er role) {
        //Retira o parentesco do role atual
        if(this.role != null)
            this.role.setParent(null);

        this.role = role;
        //Se role existe, atribui este como seu parente
        if(this.role != null)
            this.role.setParent(this);
    }


    /**
     * Retorna o papel utilizado na condição.
     *
     * @return
     *          elemento representando o papel.
     */
    public Er getRole() {
        return role;
    }


    /**
     * Determina a tecla da condição.
     *
     * @param key
     *          elemento representando a tecla da condição.
     */
    public void setKey(KeyParamType<Ep> key) {
        this.key = key;
    }


    /**
     * Retorna a tecla da condição.
     *
     * @return
     *          elemento representando a tecla da condição.
     */
    public KeyParamType<Ep> getKey() {
        return key;
    }


    /**
     * Determina o tipo do evento da condição.
     *
     * @param eventType
     *          elemento representando o tipo do evento da condição.
     */
    public void setEventType(NCLEventType eventType) {
        this.eventType = eventType;
    }


    /**
     * Retorna o tipo do evento da condição.
     *
     * @return
     *          elemento representando o tipo do evento da condição.
     */
    public NCLEventType getEventType() {
        return eventType;
    }


    /**
     * Determina a transição do evento da condição.
     *
     * @param transition
     *          elemento representando a transição do evento da condição.
     */
    public void setTransition(NCLEventTransition transition) {
        this.transition = transition;
    }


    /**
     * Retorna a transição do evento da condição.
     *
     * @return
     *          elemento representando a transição do evento da condição.
     */
    public NCLEventTransition getTransition() {
        return transition;
    }


    public void setDelay(DoubleParamType<Ep> delay) {
        this.delay = delay;
    }


    public DoubleParamType<Ep> getDelay() {
        return delay;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleCondition";
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        if(getKey() != null)
            content += " key='" + getKey().parse() + "'";
        if(getDelay() != null){
            content += " delay='" + getDelay().parse();
            if(getDelay().getValue() != null)
                content += "s'";
            else
                content += "'";
        }
        if(getMin() != null)
            content += " min='" + getMin() + "'";        
        if(getMax() != null){
            content += " max='" + getMax().parse() + "'";
        }
        if(getQualifier() != null)
            content += " qualifier='" + getQualifier().toString() + "'";
        if(getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";
        if(getTransition() != null)
            content += " transition='" + getTransition().toString() + "'";
        content += "/>\n";

        return content;
    }

    
    public boolean compare(T other) {
        boolean comp = true;

        String this_cond, other_cond;
        NCLSimpleConditionPrototype other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleConditionPrototype))
            return false;

         other_simp = (NCLSimpleConditionPrototype) other;

        // Compara pelo role
        if(getRole() == null) this_cond = ""; else this_cond = getRole().getName();
        if(other_simp.getRole() == null) other_cond = ""; else other_cond = other_simp.getRole().getName();
        comp &= this_cond.equals(other_cond);

        // Compara pelo número mínimo
        
        int this_min, other_min;
        if(getMin() == null) this_min = 0; else this_min = getMin();
        if(other_simp.getMin() == null) other_min = 0; else other_min = other_simp.getMin();
        comp &= this_min == other_min;

        // Compara pelo número máximo
        if(getMax() == null) this_cond = ""; else this_cond = getMax().parse();
        if(other_simp.getMax() == null) other_cond = ""; else other_cond = other_simp.getMax().parse();
        comp &= this_cond.equals(other_cond);

        // Compara pelo delay
        if(getDelay() == null) this_cond = ""; else this_cond = getDelay().parse();
        if(other_simp.getDelay() == null) other_cond = ""; else other_cond = other_simp.getDelay().parse();
        comp &= this_cond.equals(other_cond);

        // Compara pelo qualifier
        if(getQualifier() == null) this_cond = ""; else this_cond = getQualifier().toString();
        if(other_simp.getQualifier() == null) other_cond = ""; else other_cond = other_simp.getQualifier().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pela tecla
        if(getKey() == null) this_cond = ""; else this_cond = getKey().parse();
        if(other_simp.getKey() == null) other_cond = ""; else other_cond = other_simp.getKey().parse();
        comp &= this_cond.equals(other_cond);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_cond = ""; else this_cond = getEventType().toString();
        if(other_simp.getEventType() == null) other_cond = ""; else other_cond = other_simp.getEventType().toString();
        comp &= this_cond.equals(other_cond);

        // Compara pela transicao do evento
        if(getTransition() == null) this_cond = ""; else this_cond = getTransition().toString();
        if(other_simp.getTransition() == null) other_cond = ""; else other_cond = other_simp.getTransition().toString();
        comp &= this_cond.equals(other_cond);


        return comp;
    }
}
