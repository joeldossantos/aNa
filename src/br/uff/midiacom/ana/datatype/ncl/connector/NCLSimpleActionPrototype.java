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
import br.uff.midiacom.ana.datatype.enums.NCLEventAction;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.datatype.number.MaxType;


public class NCLSimpleActionPrototype<T extends NCLSimpleActionPrototype, P extends NCLElement, I extends XMLElementImpl, Ea extends NCLAction, Er extends NCLRolePrototype, Ep extends NCLConnectorParamPrototype>
        extends XMLElementPrototype<Ea, P, I> implements NCLAction<Ea, P, Ep> {

    protected StringParamType<Ep> value;
    protected Integer min;
    protected MaxType max;
    protected NCLActionOperator qualifier;
    protected NCLEventType eventType;
    protected NCLEventAction actionType;
    protected IntegerParamType<Ep> repeat;
    protected DoubleParamType<Ep> repeatDelay;
    protected DoubleParamType<Ep> duration;
    protected ByParamType<Ep> by;
    protected Er role;
    protected DoubleParamType<Ep> delay;


    /**
     * Construtor do elemento <i>simpleAction</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSimpleActionPrototype() {}


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          String representando o valor de atribuição.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(StringParamType<Ep> value) {
        this.value = value;
    }
    
        
    /**
     * Retorna o valor de atribuição da ação.
     *
     * @return
     *          String representando o valor de atribuição.
     */
    public StringParamType<Ep> getValue() {
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

        this.min = min;
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
        this.max = max;
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
        this.qualifier = qualifier;
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

        this.role = role;
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
        this.eventType = eventType;
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
        this.actionType = actionType;
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
    public void setRepeat(IntegerParamType<Ep> repeat) {
        this.repeat = repeat;
    }


    /**
     * Retorna o número de repetições da ação.
     *
     * @return
     *          inteiro representando o número de repetições.
     */
    public IntegerParamType<Ep> getRepeat() {
        return repeat;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          inteiro representando o delay entre repetições.
     */
    public void setRepeatDelay(DoubleParamType<Ep> repeatDelay) {
        this.repeatDelay = repeatDelay;
    }


    /**
     * Retorna o delay entre repetições da ação.
     *
     * @return
     *          inteiro representando o delay entre repetições.
     */
    public DoubleParamType<Ep> getRepeatDelay() {
        return repeatDelay;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          inteiro representando a duração da atribuição.
     */
    public void setDuration(DoubleParamType<Ep> duration) {
        this.duration = duration;
    }


    /**
     * Retorna a duração da ação de atribuição.
     *
     * @return
     *          inteiro representando a duração da atribuição.
     */
    public DoubleParamType<Ep> getDuration() {
        return duration;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          inteiro positivo representando o passo da atribuição ou negativo
     *          caso o passo seja definido como a String "indefinite".
     */
    public void setBy(ByParamType<Ep> by) {
        this.by = by;
    }


    /**
     * Retorna o passo da ação de atribuição.
     *
     * @return
     *          inteiro representando o passo da atribuição. Retorna -1 se o
     *          o passo for "indefinite".
     */
    public ByParamType<Ep> getBy() {
        return by;
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
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleAction";
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        if(getValue() != null)
            content += " value='" + getValue().parse() + "'";
        if(getDelay() != null){
            content += " delay='" + getDelay().parse();
            if(getDelay().getValue() != null)
                content += "s'";
            else
                content += "'";
        }
        if(getMin() != null)
            content += " min='" + getMin() + "'";
        if(getMax() != null)
            content += " max='" + getMax().parse() + "'";
        if(getQualifier() != null)
            content += " qualifier='" + getQualifier().toString() + "'";
        if(getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";
        if(getActionType() != null)
            content += " actionType='" + getActionType().toString() + "'";
        if(getRepeat() != null)
            content += " repeat='" + getRepeat().parse() + "'";
        if(getRepeatDelay() != null){
            content += " repeatDelay='" + getRepeatDelay().parse();
            if(getRepeatDelay().getValue() != null)
                content += "s'";
            else
                content += "'";
        }
        if(getDuration() != null){
            content += " duration='" + getDuration().parse();
            if(getDuration().getValue() != null)
                content += "s'";
            else
                content += "'";
        }
        if(getBy() != null)
            content += " by='" + getBy().parse() + "'";
        content += "/>\n";

        return content;
    }


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
