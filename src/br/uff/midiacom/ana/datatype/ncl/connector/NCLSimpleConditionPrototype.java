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

import br.uff.midiacom.ana.datatype.aux.parameterized.DoubleParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.KeyParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventTransition;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;


public abstract class NCLSimpleConditionPrototype<T extends NCLSimpleConditionPrototype,
                                                  P extends NCLElement,
                                                  I extends NCLElementImpl,
                                                  Ec extends NCLCondition,
                                                  Er extends NCLRolePrototype,
                                                  Ep extends NCLConnectorParamPrototype,
                                                  R extends ConParamReference>
        extends NCLElementPrototype<Ec, P, I>
        implements NCLCondition<Ec, P, Ep, R> {

    protected KeyParamType<Ep, Ec, R> key;
    protected Integer min;
    protected MaxType max;
    protected NCLConditionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventTransition transition;
    protected Er role;
    protected DoubleParamType<Ep, Ec, R> delay;
    

    /**
     * Construtor do elemento <i>simpleCondition</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSimpleConditionPrototype() throws XMLException {
        super();
    }


    /**
     * Determina o número mínimo de binds que devem usar essa condição.
     * 
     * @param min
     *          inteiro positivo representando o número mínimo.
     */
    public void setMin(Integer min) {
        if(min != null && min < 0)
            throw new IllegalArgumentException("Invalid min");

        Integer aux = this.min;
        this.min = min;
        impl.notifyAltered(NCLElementAttributes.MIN, aux, min);
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
        MaxType aux = this.max;
        this.max = max;
        impl.notifyAltered(NCLElementAttributes.MAX, aux, max);
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
        NCLConditionOperator aux = this.qualifier;
        this.qualifier = qualifier;
        impl.notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
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

        Er aux = this.role;
        this.role = role;
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
        
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
    public void setKey(KeyParamType<Ep, Ec, R> key) throws XMLException {
        KeyParamType aux = this.key;
        
        this.key = key;
        this.key.setOwner((Ec) this, NCLElementAttributes.KEY);
        
        impl.notifyAltered(NCLElementAttributes.KEY, aux, key);
        if(aux != null)
            aux.removeOwner();
    }


    /**
     * Retorna a tecla da condição.
     *
     * @return
     *          elemento representando a tecla da condição.
     */
    public KeyParamType<Ep, Ec, R> getKey() {
        return key;
    }


    /**
     * Determina o tipo do evento da condição.
     *
     * @param eventType
     *          elemento representando o tipo do evento da condição.
     */
    public void setEventType(NCLEventType eventType) {
        NCLEventType aux = this.eventType;
        this.eventType = eventType;
        impl.notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
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
        NCLEventTransition aux = this.transition;
        this.transition = transition;
        impl.notifyAltered(NCLElementAttributes.TRANSITION, aux, transition);
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


    @Override
    public void setDelay(DoubleParamType<Ep, Ec, R> delay) throws XMLException {
        DoubleParamType aux = this.delay;
        
        this.delay = delay;
        this.delay.setOwner((Ec) this, NCLElementAttributes.DELAY);
        
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
        if(aux != null)
            aux.removeOwner();
    }


    @Override
    public DoubleParamType<Ep, Ec, R> getDelay() {
        return delay;
    }

    
    @Override
    public boolean compare(Ec other) {
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
