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
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.NCLDefaultConditionRole;
import br.uff.midiacom.ana.datatype.NCLEventTransition;
import br.uff.midiacom.ana.datatype.NCLEventType;
import br.uff.midiacom.ana.datatype.NCLKey;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>simpleCondition</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma condição simples de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLSimpleCondition<C extends NCLCondition, R extends NCLRole, P extends NCLConnectorParam> extends NCLElement implements NCLCondition<C, P> {

    private NCLKey key;
    private Integer min;
    private Integer max;
    private NCLConditionOperator qualifier;
    private NCLEventType eventType;
    private NCLEventTransition transition;
    private R role;
    private Integer delay;

    private P parKey;
    private P parDelay;
    

    /**
     * Construtor do elemento <i>simpleCondition</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLSimpleCondition() {}


    /**
     * Construtor do elemento <i>simpleCondition</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLSimpleCondition(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
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
    public void setMax(Integer max) {
        if(max != null && max < 0)
            this.max = -1;
        
        this.max = max;
    }


    /**
     * Retorna o número máximo de binds que devem usar essa condição.
     *
     * @return
     *          inteiro positivo representando o número máximo ou -1
     *          caso o número máximo seja a String "umbouded".
     */
    public Integer getMax() {
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
    public void setRole(R role) {
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
    public R getRole() {
        return role;
    }


    /**
     * Determina a tecla da condição.
     *
     * @param key
     *          elemento representando a tecla da condição.
     */
    public void setKey(NCLKey key) {
        this.key = key;
        this.parKey = null;
    }


    /**
     * Determina a tecla da condição.
     *
     * @param key
     *          parâmetro representando a tecla da condição.
     */
    public void setKey(P key) {
        this.parKey = key;
        this.key = null;
    }


    /**
     * Retorna a tecla da condição.
     *
     * @return
     *          elemento representando a tecla da condição.
     */
    public NCLKey getKey() {
        return key;
    }


    /**
     * Retorna a tecla da condição.
     *
     * @return
     *          parâmetro representando a tecla da condição.
     */
    public P getParamKey() {
        return parKey;
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


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if(delay != null && delay < 0)
            throw new IllegalArgumentException("Invalid delay");

        this.delay = delay;
        this.parDelay= null;
    }


    public void setDelay(P delay) {
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
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleCondition";
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        if(getKey() != null)
            content += " key='" + getKey().toString() + "'";
        if(getParamKey() != null)
            content += " key='$" + getParamKey().getId() + "'";
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
        if(getTransition() != null)
            content += " transition='" + getTransition().toString() + "'";
        content += "/>\n";

        return content;
    }

    
    public int compareTo(C other) {
        int comp = 0;

        String this_cond, other_cond;
        NCLSimpleCondition other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleCondition))
            return 1;

         other_simp = (NCLSimpleCondition) other;

        // Compara pelo role
        if(getRole() == null) this_cond = ""; else this_cond = getRole().getName();
        if(other_simp.getRole() == null) other_cond = ""; else other_cond = other_simp.getRole().getName();
        comp = this_cond.compareTo(other_cond);

        // Compara pelo número mínimo
        if(comp == 0){
            int this_min, other_min;
            if(getMin() == null) this_min = 0; else this_min = getMin();
            if(other_simp.getMin() == null) other_min = 0; else other_min = other_simp.getMin();
            comp = this_min - other_min;
        }

        // Compara pelo número máximo
        if(comp == 0){
            int this_max, other_max;
            if(getMax() == null) this_max = 0; else this_max = getMax();
            if(other_simp.getMax() == null) other_max = 0; else other_max = other_simp.getMax();
            comp = this_max - other_max;
        }

        // Compara pelo delay
        if(comp == 0){
            int this_del, other_del;
            if(getDelay() == null) this_del = 0; else this_del = getDelay();
            if(other_simp.getDelay() == null) other_del = 0; else other_del = other_simp.getDelay();
            comp = this_del - other_del;
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
            if(getQualifier() == null) this_cond = ""; else this_cond = getQualifier().toString();
            if(other_simp.getQualifier() == null) other_cond = ""; else other_cond = other_simp.getQualifier().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pela tecla
        if(comp == 0){
            if(getKey() == null) this_cond = ""; else this_cond = getKey().toString();
            if(other_simp.getKey() == null) other_cond = ""; else other_cond = other_simp.getKey().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pela tecla (parametro)
        if(comp == 0){
            if(getParamKey() == null && other_simp.getParamKey() == null)
                comp = 0;
            else if(getParamKey() != null && other_simp.getParamKey() != null)
                comp = getParamKey().compareTo(other_simp.getParamKey());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamKey() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara pelo tipo do evento
        if(comp == 0){
            if(getEventType() == null) this_cond = ""; else this_cond = getEventType().toString();
            if(other_simp.getEventType() == null) other_cond = ""; else other_cond = other_simp.getEventType().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pela transicao do evento
        if(comp == 0){
            if(getTransition() == null) this_cond = ""; else this_cond = getTransition().toString();
            if(other_simp.getTransition() == null) other_cond = ""; else other_cond = other_simp.getTransition().toString();
            comp = this_cond.compareTo(other_cond);
        }


        return comp;
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getRole() == null){
            addError("Elemento não possui atributo obrigatório role.");
            valid = false;
        }

        if(getRole() != null && getRole().getConditionName() != null){
            if(getRole().getConditionName().equals(NCLDefaultConditionRole.ONSELECTION)){
                if(getKey() == null && getParamKey() == null){
                    addError("O atributo key deve ser especificado.");
                    valid = false;
                }
            }
            else{
                if(getKey() != null || getParamKey() != null){
                    addWarning("O atributo key não deve ser especificado.");
                    valid = false;
                }
            }
        }
        else{
            if(getEventType() == null || getTransition() == null){
                addError("Os atributos eventType e transition devem ser especificados.");
                valid = false;
            }
            else if(getEventType().equals(NCLEventType.SELECTION)){
                if(getKey() == null && getParamKey() == null){
                    addError("O atributo key deve ser especificado.");
                    valid = false;
                }
            }
            else{
                if(getKey() != null || getParamKey() != null){
                    addWarning("O atributo key não deve ser especificado.");
                    valid = false;
                }
            }

        }

        if(getMax() != null && getMax() == 1 && getQualifier() != null){
            addWarning("O atributo qualifier não deve ser especificado");
            valid = false;
        }
        else if(getMax() != null && getMax() != 1 && getQualifier() == null){
            addWarning("O atributo qualifier deve ser especificado");
            valid = false;
        }

        return valid;
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
                else if(attributes.getLocalName(i).equals("key")){
                    String value = attributes.getValue(i);
                    if(value.contains("$")){
                        value = value.substring(1);
                        setKey((P) new NCLConnectorParam(value));//cast retirado na correcao das referencias
                    }
                    else{
                        for(NCLKey k : NCLKey.values()){
                            if(k.toString().equals(value))
                                setKey(k);
                        }
                    }
                }
                else if(attributes.getLocalName(i).equals("delay")){
                    String value = attributes.getValue(i);
                    if(value.contains("$")){
                        value = value.substring(1);
                        setDelay((P) new NCLConnectorParam(value));//cast retirado na correcao das referencias
                    }
                    else{
                        value = value.substring(0, value.length() - 1);
                        setDelay(new Integer(value));
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
                    for(NCLConditionOperator q : NCLConditionOperator.values()){
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
                else if(attributes.getLocalName(i).equals("transition")){
                    for(NCLEventTransition t : NCLEventTransition.values()){
                        if(t.toString().equals(attributes.getValue(i)))
                            setTransition(t);
                    }
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
        if(getParamKey() != null)
            setKey(parameterReference(getParamKey().getId()));
    }


    private P parameterReference(String id) {
        NCLElement connector = getParent();

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


    protected R createRole(String name) {
        return (R) new NCLRole(name);
    }
}
