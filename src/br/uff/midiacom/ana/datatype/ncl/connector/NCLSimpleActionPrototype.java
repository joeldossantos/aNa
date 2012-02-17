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

import br.uff.midiacom.ana.datatype.auxiliar.ByParamType;
import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.auxiliar.IntegerParamType;
import br.uff.midiacom.ana.datatype.auxiliar.StringParamType;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventAction;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;


public abstract class NCLSimpleActionPrototype<T extends NCLSimpleActionPrototype,
                                               P extends NCLElement,
                                               I extends NCLElementImpl,
                                               Ea extends NCLAction,
                                               Er extends NCLRolePrototype,
                                               Ep extends NCLConnectorParamPrototype>
        extends NCLElementPrototype<Ea, P, I>
        implements NCLAction<Ea, P, Ep> {

    protected StringParamType<Ep, T> value;
    protected Integer min;
    protected MaxType max;
    protected NCLActionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventAction actionType;
    protected IntegerParamType<Ep, T> repeat;
    protected DoubleParamType<Ep, T> repeatDelay;
    protected DoubleParamType<Ep, T> duration;
    protected ByParamType<Ep, T> by;
    protected Er role;
    protected DoubleParamType<Ep, Ea> delay;


    /**
     * Construtor do elemento <i>simpleAction</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSimpleActionPrototype() throws XMLException {
        super();
    }


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          String representando o valor de atribuição.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(StringParamType<Ep, T> value) {
        StringParamType aux = this.value;
        this.value = value;
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
        
    /**
     * Retorna o valor de atribuição da ação.
     *
     * @return
     *          String representando o valor de atribuição.
     */
    public StringParamType<Ep, T> getValue() {
        return value;
    }
    
    
    /**
     * Determina o número mínimo de binds que devem usar essa ação.
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
     * Retorna o número mínimo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro positivo representando o número mínimo.
     */
    public Integer getMin() {
        return min;
    }


    /**
     * Determina o número máximo de binds que devem usar essa ação.
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
     * Retorna o número máximo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro positivo representando o número máximo ou -1
     *          caso o número máximo seja a String "umbouded".
     */
    public MaxType getMax() {
        return max;
    }


    /**
     * Determina como serão disparados o conjunto de binds que usam essa ação.
     *
     * @param qualifier
     *          operador que representa como os binds serão disparados.
     */
    public void setQualifier(NCLActionOperator qualifier) {
        NCLActionOperator aux = this.qualifier;
        this.qualifier = qualifier;
        impl.notifyAltered(NCLElementAttributes.QUALIFIER, aux, qualifier);
    }


    /**
     * Retorna como serão disparados o conjunto de binds que usam essa ação.
     *
     * @return
     *          operador que representa como os binds serão disparados.
     */
    public NCLActionOperator getQualifier() {
        return qualifier;
    }


    /**
     * Determina o nome do papel de ação seguindo um dos nomes padrões.
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
     * Retorna o papel utilizado na ação.
     *
     * @return
     *          elemento representando o papel.
     */
    public Er getRole() {
        return role;
    }


    /**
     * Determina o tipo do evento da ação.
     *
     * @param eventType
     *          elemento representando o tipo do evento da ação.
     */
    public void setEventType(NCLEventType eventType) {
        NCLEventType aux = this.eventType;
        this.eventType = eventType;
        impl.notifyAltered(NCLElementAttributes.EVENTTYPE, aux, eventType);
    }


    /**
     * Retorna o tipo do evento da ação.
     *
     * @return
     *          elemento representando o tipo do evento da ação.
     */
    public NCLEventType getEventType() {
        return eventType;
    }


    /**
     * Determina a ação do evento.
     *
     * @param actionType
     *          elemento representando a ação do evento.
     */
    public void setActionType(NCLEventAction actionType) {
        NCLEventAction aux = this.actionType;
        this.actionType = actionType;
        impl.notifyAltered(NCLElementAttributes.ACTIONTYPE, aux, actionType);
    }


    /**
     * Retorna o tipo do evento da ação.
     *
     * @return
     *          elemento representando o tipo do evento da ação.
     */
    public NCLEventAction getActionType() {
        return actionType;
    }


    /**
     * Determina o número de repetições da ação.
     *
     * @param repeat
     *          inteiro representando o número de repetições.
     */
    public void setRepeat(IntegerParamType<Ep, T> repeat) {
        IntegerParamType aux = this.repeat;
        this.repeat = repeat;
        impl.notifyAltered(NCLElementAttributes.REPEAT, aux, repeat);
    }


    /**
     * Retorna o número de repetições da ação.
     *
     * @return
     *          inteiro representando o número de repetições.
     */
    public IntegerParamType<Ep, T> getRepeat() {
        return repeat;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          inteiro representando o delay entre repetições.
     */
    public void setRepeatDelay(DoubleParamType<Ep, T> repeatDelay) {
        DoubleParamType aux = this.repeatDelay;
        this.repeatDelay = repeatDelay;
        impl.notifyAltered(NCLElementAttributes.REPEATDELAY, aux, repeatDelay);
    }


    /**
     * Retorna o delay entre repetições da ação.
     *
     * @return
     *          inteiro representando o delay entre repetições.
     */
    public DoubleParamType<Ep, T> getRepeatDelay() {
        return repeatDelay;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          inteiro representando a duração da atribuição.
     */
    public void setDuration(DoubleParamType<Ep, T> duration) {
        DoubleParamType aux = this.duration;
        this.duration = duration;
        impl.notifyAltered(NCLElementAttributes.DURATION, aux, duration);
    }


    /**
     * Retorna a duração da ação de atribuição.
     *
     * @return
     *          inteiro representando a duração da atribuição.
     */
    public DoubleParamType<Ep, T> getDuration() {
        return duration;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          inteiro positivo representando o passo da atribuição ou negativo
     *          caso o passo seja definido como a String "indefinite".
     */
    public void setBy(ByParamType<Ep, T> by) {
        ByParamType aux = this.by;
        this.by = by;
        impl.notifyAltered(NCLElementAttributes.BY, aux, by);
    }


    /**
     * Retorna o passo da ação de atribuição.
     *
     * @return
     *          inteiro representando o passo da atribuição. Retorna -1 se o
     *          o passo for "indefinite".
     */
    public ByParamType<Ep, T> getBy() {
        return by;
    }


    @Override
    public void setDelay(DoubleParamType<Ep, Ea> delay) {
        DoubleParamType aux = this.delay;
        this.delay = delay;
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
    }


    @Override
    public DoubleParamType<Ep, Ea> getDelay() {
        return delay;
    }


    @Override
    public boolean compare(Ea other) {
        boolean comp = true;

        String this_act, other_act;
        int this_ac, other_ac;
        NCLSimpleActionPrototype other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleActionPrototype))
            return false;

         other_simp = (NCLSimpleActionPrototype) other;

        // Compara pelo role
        if(getRole() == null) this_act = ""; else this_act = getRole().getName();
        if(other_simp.getRole() == null) other_act = ""; else other_act = other_simp.getRole().getName();
        comp &= this_act.equals(other_act);

        // Compara pelo número mínimo
        if(getMin() == null) this_ac = 0; else this_ac = getMin();
        if(other_simp.getMin() == null) other_ac = 0; else other_ac = other_simp.getMin();
        comp &= (this_ac == other_ac);

        // Compara pelo número máximo
        if(getMax() == null) this_act = ""; else this_act = getMax().parse();
        if(other_simp.getMax() == null) other_act = ""; else other_act = other_simp.getMax().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo delay
        if(getDelay() == null) this_act = ""; else this_act = getDelay().parse();
        if(other_simp.getDelay() == null) other_act = ""; else other_act = other_simp.getDelay().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo qualifier
        if(getQualifier() == null) this_act = ""; else this_act = getQualifier().toString();
        if(other_simp.getQualifier() == null) other_act = ""; else other_act = other_simp.getQualifier().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo value
        if(getValue() == null) this_act = ""; else this_act = getValue().parse();
        if(other_simp.getValue() == null) other_act = ""; else other_act = other_simp.getValue().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_act = ""; else this_act = getEventType().toString();
        if(other_simp.getEventType() == null) other_act = ""; else other_act = other_simp.getEventType().toString();
        comp &= this_act.equals(other_act);

        // Compara pela acao do evento
        if(getActionType() == null) this_act = ""; else this_act = getActionType().toString();
        if(other_simp.getActionType() == null) other_act = ""; else other_act = other_simp.getActionType().toString();
        comp &= this_act.equals(other_act);

        // Compara pelo repeat
        if(getRepeat() == null) this_act = ""; else this_act = getRepeat().parse();
        if(other_simp.getRepeat() == null) other_act = ""; else other_act = other_simp.getRepeat().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo repeatDelay
        if(getRepeatDelay() == null) this_act = ""; else this_act = getRepeatDelay().parse();
        if(other_simp.getRepeatDelay() == null) other_act = ""; else other_act = other_simp.getRepeatDelay().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo duration
        if(getDuration() == null) this_act = ""; else this_act = getDuration().parse();
        if(other_simp.getDuration() == null) other_act = ""; else other_act = other_simp.getDuration().parse();
        comp &= this_act.equals(other_act);

        // Compara pelo by
        if(getBy() == null) this_act = ""; else this_act = getBy().parse();
        if(other_simp.getBy() == null) other_act = ""; else other_act = other_simp.getBy().parse();
        comp &= this_act.equals(other_act);

        return comp;
    }
}
