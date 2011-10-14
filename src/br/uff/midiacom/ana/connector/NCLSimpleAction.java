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
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLDefaultActionRole;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventAction;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLSimpleActionPrototype;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>simpleAction</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma ação simples de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLSimpleAction<T extends NCLSimpleAction, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLAction, Er extends NCLRole, Ep extends NCLConnectorParam>
        extends NCLSimpleActionPrototype<T, P, I, Ea, Er, Ep> implements NCLAction<Ea, P, Ep> {

    private String value;
    private Integer min;
    private Integer max;
    private NCLActionOperator qualifier;
    private NCLEventType eventType;
    private NCLEventAction actionType;
    private Integer repeat;
    private Integer repeatDelay;
    private Integer duration;
    private Integer by;
    private R role;
    private Integer delay;

    private P parValue;
    private P parRepeat;
    private P parRepeatDelay;
    private P parDuration;
    private P parBy;
    private P parDelay;


    /**
     * Construtor do elemento <i>simpleAction</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSimpleAction() {}


    /**
     * Construtor do elemento <i>simpleAction</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLSimpleAction(XMLReader reader, NCLElementImpl parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          String representando o valor de atribuição.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value) {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        notifyAltered(NCLElementAttributes.VALUE, this.value, value);
        this.value = value;
        this.parValue = null;
    }


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          Parâmetro representando o valor de atribuição.
     */
    public void setValue(P value) {
        notifyAltered(NCLElementAttributes.VALUE, this.value, value);
        this.parValue = value;
        this.value = null;
    }
    
        
    /**
     * Retorna o valor de atribuição da ação.
     *
     * @return
     *          String representando o valor de atribuição.
     */
    public String getValue() {
        return value;
    }


    /**
     * Retorna o valor de atribuição da ação.
     *
     * @return
     *          Parâmetro representando o valor de atribuição.
     */
    public P getParamValue() {
        return parValue;
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

        notifyAltered(NCLElementAttributes.MIN, this.min, min);
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
    public void setMax(Integer max) {
        if(max != null && max < 0){
            notifyAltered(NCLElementAttributes.MAX, this.max, max);
            this.max = -1;
        }else{
            notifyAltered(NCLElementAttributes.MAX, this.max, max);
            this.max = max;
        }
    }


    /**
     * Retorna o número máximo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro positivo representando o número máximo ou -1
     *          caso o número máximo seja a String "umbouded".
     */
    public Integer getMax() {
        return max;
    }


    /**
     * Determina como serão disparados o conjunto de binds que usam essa ação.
     *
     * @param qualifier
     *          operador que representa como os binds serão disparados.
     */
    public void setQualifier(NCLActionOperator qualifier) {
        notifyAltered(NCLElementAttributes.QUALIFIER, this.qualifier, qualifier);
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
    public void setRole(R role) {
        //Retira o parentesco do role atual
        if(this.role != null)
            this.role.setParent(null);

        notifyAltered(NCLElementAttributes.ROLE, this.role, role);
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
    public R getRole() {
        return role;
    }


    /**
     * Determina o tipo do evento da ação.
     *
     * @param eventType
     *          elemento representando o tipo do evento da ação.
     */
    public void setEventType(NCLEventType eventType) {
        notifyAltered(NCLElementAttributes.EVENTTYPE, this.eventType, eventType);
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
        notifyAltered(NCLElementAttributes.ACTIONTYPE, this.actionType, actionType);
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
    public void setRepeat(Integer repeat) {
        notifyAltered(NCLElementAttributes.REPEAT, this.repeat, repeat);
        this.repeat = repeat;
        this.parRepeat = null;
    }


    /**
     * Determina o número de repetições da ação.
     *
     * @param repeat
     *          Parâmetro representando o número de repetições.
     */
    public void setRepeat(P repeat) {
        notifyAltered(NCLElementAttributes.REPEAT, this.repeat, repeat);
        this.parRepeat = repeat;
        this.repeat = null;
    }


    /**
     * Retorna o número de repetições da ação.
     *
     * @return
     *          inteiro representando o número de repetições.
     */
    public Integer getRepeat() {
        return repeat;
    }


    /**
     * Retorna o número de repetições da ação.
     *
     * @return
     *          parametro representando o número de repetições.
     */
    public P getParamRepeat() {
        return parRepeat;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          inteiro representando o delay entre repetições.
     */
    public void setRepeatDelay(Integer repeatDelay) {
        notifyAltered(NCLElementAttributes.REPEATDELAY, this.repeatDelay, repeatDelay);
        this.repeatDelay = repeatDelay;
        this.parRepeatDelay = null;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          parâmetro representando o delay entre repetições.
     */
    public void setRepeatDelay(P repeatDelay) {
        notifyAltered(NCLElementAttributes.REPEATDELAY, this.repeatDelay, repeatDelay);
        this.parRepeatDelay = repeatDelay;
        this.repeatDelay = null;
    }


    /**
     * Retorna o delay entre repetições da ação.
     *
     * @return
     *          inteiro representando o delay entre repetições.
     */
    public Integer getRepeatDelay() {
        return repeatDelay;
    }


    /**
     * Retorna o delay entre repetições da ação.
     *
     * @return
     *          parâmetro representando o delay entre repetições.
     */
    public P getParamRepeatDelay() {
        return parRepeatDelay;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          inteiro representando a duração da atribuição.
     */
    public void setDuration(Integer duration) {
        notifyAltered(NCLElementAttributes.DURATION, this.duration, duration);
        this.duration = duration;
        this.parDuration = null;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          parâmetro representando a duração da atribuição.
     */
    public void setDuration(P duration) {
        notifyAltered(NCLElementAttributes.DURATION, this.duration, duration);
        this.parDuration = duration;
        this.duration = null;
    }


    /**
     * Retorna a duração da ação de atribuição.
     *
     * @return
     *          inteiro representando a duração da atribuição.
     */
    public Integer getDuration() {
        return duration;
    }


    /**
     * Retorna a duração da ação de atribuição.
     *
     * @return
     *          parâmetro representando a duração da atribuição.
     */
    public P getParamDuration() {
        return parDuration;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          inteiro positivo representando o passo da atribuição ou negativo
     *          caso o passo seja definido como a String "indefinite".
     */
    public void setBy(Integer by) {
        if(by != null && by < 0){
            notifyAltered(NCLElementAttributes.BY, this.by, by);
            this.by = -1;
        }else{
            notifyAltered(NCLElementAttributes.BY, this.by, by);
            this.by = by;
        }
        
        this.parBy = null;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          parâmetro representando o passo da atribuição.
     */
    public void setBy(P by) {
        notifyAltered(NCLElementAttributes.BY, this.by, by);
        this.parBy = by;
        this.by = null;
    }


    /**
     * Retorna o passo da ação de atribuição.
     *
     * @return
     *          inteiro representando o passo da atribuição. Retorna -1 se o
     *          o passo for "indefinite".
     */
    public Integer getBy() {
        return by;
    }


    /**
     * Retorna o passo da ação de atribuição.
     *
     * @return
     *          parâmetro representando o passo da atribuição.
     */
    public P getParamBy() {
        return parBy;
    }


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if(delay != null && delay < 0)
            throw new IllegalArgumentException("Invalid delay");

        notifyAltered(NCLElementAttributes.DELAY, this.delay, delay);
        this.delay = delay;
        this.parDelay= null;
    }


    public void setDelay(P delay) {
        notifyAltered(NCLElementAttributes.DELAY, this.delay, delay);
        this.parDelay = delay;
        this.delay = null;
    }


    public Integer getDelay() {
        return delay;
    }


    public P getParamDelay() {
        return parDelay;
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
            content += " value='" + getValue() + "'";
        if(getParamValue() != null)
            content += " value='$" + getParamValue().getId() + "'";
        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";
        if(getParamDelay() != null)
            content += " delay='$" + getParamDelay().getId() + "'";
        if(getMin() != null)
            content += " min='" + getMin() + "'";
        if(getMax() != null){
            if(getMax() < 0)
                content += " max='unbounded'";
            else
                content += " max='" + getMax() + "'";
        }
        if(getQualifier() != null)
            content += " qualifier='" + getQualifier().toString() + "'";
        if(getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";
        if(getActionType() != null)
            content += " actionType='" + getActionType().toString() + "'";
        if(getRepeat() != null)
            content += " repeat='" + getRepeat() + "'";
        if(getParamRepeat() != null)
            content += " repeat='$" + getParamRepeat().getId() + "'";
        if(getRepeatDelay() != null)
            content += " repeatDelay='" + getRepeatDelay() + "s'";
        if(getParamRepeatDelay() != null)
            content += " repeatDelay='$" + getParamRepeatDelay().getId() + "'";
        if(getDuration() != null)
            content += " duration='" + getDuration() + "s'";
        if(getParamDuration() != null)
            content += " duration='$" + getParamDuration().getId() + "'";
        if(getBy() != null){
            if(getBy() < 0)
                content += " by='indefinite'";
            else
                content += " by='" + getBy() + "'";
        }
        if(getParamBy() != null)
            content += " by='$" + getParamBy().getId() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(A other) {
        int comp = 0;

        String this_act, other_act;
        int this_ac, other_ac;
        NCLSimpleAction other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleAction))
            return 1;

         other_simp = (NCLSimpleAction) other;

        // Compara pelo role
        if(getRole() == null) this_act = ""; else this_act = getRole().getName();
        if(other_simp.getRole() == null) other_act = ""; else other_act = other_simp.getRole().getName();
        comp = this_act.compareTo(other_act);

        // Compara pelo número mínimo
        if(comp == 0){
            if(getMin() == null) this_ac = 0; else this_ac = getMin();
            if(other_simp.getMin() == null) other_ac = 0; else other_ac = other_simp.getMin();
            comp = this_ac - other_ac;
        }

        // Compara pelo número máximo
        if(comp == 0){
            if(getMax() == null) this_ac = 0; else this_ac = getMax();
            if(other_simp.getMax() == null) other_ac = 0; else other_ac = other_simp.getMax();
            comp = this_ac - other_ac;
        }

        // Compara pelo delay
        if(comp == 0){
            if(getDelay() == null) this_ac = 0; else this_ac = getDelay();
            if(other_simp.getDelay() == null) other_ac = 0; else other_ac = other_simp.getDelay();
            comp = this_ac - other_ac;
        }

        // Compara pelo delay (parametro)
        if(comp == 0){
            if(getParamDelay() == null && other_simp.getParamDelay() == null)
                comp = 0;
            else if(getParamDelay() != null && other_simp.getParamDelay() != null)
                comp = getParamDelay().compareTo(other_simp.getParamDelay());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamDelay() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara pelo qualifier
        if(comp == 0){
            if(getQualifier() == null) this_act = ""; else this_act = getQualifier().toString();
            if(other_simp.getQualifier() == null) other_act = ""; else other_act = other_simp.getQualifier().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo value
        if(comp == 0){
            if(getValue() == null) this_act = ""; else this_act = getValue();
            if(other_simp.getValue() == null) other_act = ""; else other_act = other_simp.getValue();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo value (parametro)
        if(comp == 0){
            if(getParamValue() == null && other_simp.getParamValue() == null)
                comp = 0;
            else if(getParamValue() != null && other_simp.getParamValue() != null)
                comp = getParamValue().compareTo(other_simp.getParamValue());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamValue() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara pelo tipo do evento
        if(comp == 0){
            if(getEventType() == null) this_act = ""; else this_act = getEventType().toString();
            if(other_simp.getEventType() == null) other_act = ""; else other_act = other_simp.getEventType().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pela acao do evento
        if(comp == 0){
            if(getActionType() == null) this_act = ""; else this_act = getActionType().toString();
            if(other_simp.getActionType() == null) other_act = ""; else other_act = other_simp.getActionType().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo repeat
        if(comp == 0){
            if(getRepeat() == null) this_ac = 0; else this_ac = getRepeat();
            if(other_simp.getRepeat() == null) other_ac = 0; else other_ac = other_simp.getRepeat();
            comp = this_ac - other_ac;
        }

        // Compara pelo repeat (parametro)
        if(comp == 0){
            if(getParamRepeat() == null && other_simp.getParamRepeat() == null)
                comp = 0;
            else if(getParamRepeat() != null && other_simp.getParamRepeat() != null)
                comp = getParamRepeat().compareTo(other_simp.getParamRepeat());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamRepeat() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara pelo repeatDelay
        if(comp == 0){
            if(getRepeatDelay() == null) this_ac = 0; else this_ac = getRepeatDelay();
            if(other_simp.getRepeatDelay() == null) other_ac = 0; else other_ac = other_simp.getRepeatDelay();
            comp = this_ac - other_ac;
        }

        // Compara pelo repeatDelay (parametro)
        if(comp == 0){
            if(getParamRepeatDelay() == null && other_simp.getParamRepeatDelay() == null)
                comp = 0;
            else if(getParamRepeatDelay() != null && other_simp.getParamRepeatDelay() != null)
                comp = getParamRepeatDelay().compareTo(other_simp.getParamRepeatDelay());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamRepeatDelay() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara pelo duration
        if(comp == 0){
            if(getDuration() == null) this_ac = 0; else this_ac = getDuration();
            if(other_simp.getDuration() == null) other_ac = 0; else other_ac = other_simp.getDuration();
            comp = this_ac - other_ac;
        }

        // Compara pelo duration (parametro)
        if(comp == 0){
            if(getParamDuration() == null && other_simp.getParamDuration() == null)
                comp = 0;
            else if(getParamDuration() != null && other_simp.getParamDuration() != null)
                comp = getParamDuration().compareTo(other_simp.getParamDuration());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamDuration() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara pelo by
        if(comp == 0){
            if(getBy() == null) this_ac = 0; else this_ac = getBy();
            if(other_simp.getBy() == null) other_ac = 0; else other_ac = other_simp.getBy();
            comp = this_ac - other_ac;
        }

        // Compara pelo by (parametro)
        if(comp == 0){
            if(getParamBy() == null && other_simp.getParamBy() == null)
                comp = 0;
            else if(getParamBy() != null && other_simp.getParamBy() != null)
                comp = getParamBy().compareTo(other_simp.getParamBy());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamBy() == null)
                comp = -1;
            else
                comp = 1;
        }


        return comp;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("role")){
                    R child = createRole(attributes.getValue(i));
                    setRole(child);
                }
                else if(attributes.getLocalName(i).equals("value")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setValue((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                    }
                    else
                        setValue(var);
                }
                else if(attributes.getLocalName(i).equals("delay")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                    }
                    else{
                        var = var.substring(0, var.length() - 1);
                        setDelay(new Integer(var));
                    }
                }
                else if(attributes.getLocalName(i).equals("min"))
                    setMin(new Integer(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("max")){
                    if(attributes.getValue(i).equals("unbounded"))
                        setMax(-1);
                    else
                        setMax(new Integer(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("qualifier")){
                    for(NCLActionOperator q : NCLActionOperator.values()){
                        if(q.toString().equals(attributes.getValue(i)))
                            setQualifier(q);
                    }
                }
                else if(attributes.getLocalName(i).equals("eventType")){
                    for(NCLEventType e : NCLEventType.values()){
                        if(e.toString().equals(attributes.getValue(i)))
                            setEventType(e);
                    }
                }
                else if(attributes.getLocalName(i).equals("actionType")){
                    for(NCLEventAction t : NCLEventAction.values()){
                        if(t.toString().equals(attributes.getValue(i)))
                            setActionType(t);
                    }
                }
                else if(attributes.getLocalName(i).equals("repeat")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setRepeat((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                    }
                    else
                        setRepeat(new Integer(var));
                }
                else if(attributes.getLocalName(i).equals("repeatDelay")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setRepeatDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                    }
                    else{
                        var = var.substring(0, var.length() - 1);
                        setRepeatDelay(new Integer(var));
                    }
                }
                else if(attributes.getLocalName(i).equals("duration")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setDuration((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                    }
                    else{
                        var = var.substring(0, var.length() - 1);
                        setDuration(new Integer(var));
                    }
                }
                else if(attributes.getLocalName(i).equals("by")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setBy((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                    }
                    else
                        setBy(new Integer(var));
                }
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() == null)
            return;

        if(getParamDelay() != null)
            setDelay(parameterReference(getParamDelay().getId()));
        if(getParamValue() != null)
            setValue(parameterReference(getParamValue().getId()));
        if(getParamRepeat() != null)
            setRepeat(parameterReference(getParamRepeat().getId()));
        if(getParamRepeatDelay() != null)
            setRepeatDelay(parameterReference(getParamRepeatDelay().getId()));
        if(getParamDuration() != null)
            setDuration(parameterReference(getParamDuration().getId()));
        if(getParamBy() != null)
            setBy(parameterReference(getParamBy().getId()));
    }


    private P parameterReference(String id) {
        NCLElementImpl connector = getParent();

        while(!(connector instanceof NCLCausalConnector)){
            connector = connector.getParent();
            if(connector == null){
                addWarning("Could not find a parent connector");
                return null;
            }
        }

        Set<P> params = ((NCLCausalConnector) connector).getConnectorParams();
        for(P param : params){
            if(param.getId().equals(id))
                return param;
        }

        addWarning("Could not find connectorParam in connector with id: " + id);
        return null;
    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    protected R createRole(String name) {
        return (R) new NCLRole(name);
    }
}
