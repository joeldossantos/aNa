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
 *        This product includes the Api for NCL Authoring - aNa, developed
 *        by MídiaCom Lab (www.midiacom.uff.br) and its contributors.
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
import br.uff.midiacom.ana.NCLValues.NCLAttributeType;
import br.uff.midiacom.ana.NCLValues.NCLEventType;
import br.uff.midiacom.ana.NCLValues.NCLKey;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>attributeStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um atributo da assertiva de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLAttributeAssessment<A extends NCLAttributeAssessment, R extends NCLRole, P extends NCLConnectorParam> extends NCLElement implements Comparable<A>{

    private R role;
    private NCLEventType eventType;
    private NCLKey key;
    private NCLAttributeType attributeType;
    private Integer offset;

    private P parKey;
    private P parOffset;
    

    /**
     * Construtor do elemento <i>attributeStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLAttributeAssessment() {}


    /**
     * Construtor do elemento <i>attributeStatement</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLAttributeAssessment(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Determina o papel do atributo da assertiva.
     * 
     * @param role
     *          String representando o papel definido pela assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o role for uma String vazia.
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
     * Retorna o papel atribuido ao atributo da assertiva.
     * 
     * @return
     *          elemento representando o papel.
     */
    public R getRole() {
        return role;
    }
    
    
    /**
     * Determina o tipo do evento testado pelo atributo da assertiva.
     * 
     * @param eventType
     *          tipo do evento.
     */
    public void setEventType(NCLEventType eventType) {
        this.eventType = eventType;
    }
    
    
    /**
     * Retorna o tipo do evento testado pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando o tipo do evento.
     */
    public NCLEventType getEventType() {
        return eventType;
    }
    
    
    /**
     * Determina a tecla testada pelo atributo da assertiva.
     * 
     * @param key
     *          elemento representando a tecla.
     */
    public void setKey(NCLKey key) {
        this.key = key;
        this.parKey = null;
    }


    /**
     * Determina a tecla testada pelo atributo da assertiva.
     *
     * @param key
     *          parâmetro representando a tecla.
     */
    public void setKey(P key) {
        this.parKey = key;
        this.key = null;
    }
    
    
    /**
     * Retorna a tecla testada pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando a tecla.
     */
    public NCLKey getKey() {
        return key;
    }


    /**
     * Retorna a tecla testada pelo atributo da assertiva.
     *
     * @return
     *          parâmetro representando a tecla.
     */
    public P getParamKey() {
        return parKey;
    }
    
    
    /**
     * Determina o tipo do atributo testado pelo atributo da assertiva.
     * 
     * @param attributeType
     *          elemento representando o tipo do atributo.
     */
    public void setAttributeType(NCLAttributeType attributeType) {
        this.attributeType = attributeType;
    }
    
    
    /**
     * Retorna o tipo do atributo testado pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando o tipo do atributo.
     */
    public NCLAttributeType getAttributeType() {
        return attributeType;
    }
    
    
    /**
     * Determina o offset de teste.
     * 
     * @param offset
     *          inteiro representando o valor do offset a ser utilizado no teste.
     * @throws java.lang.IllegalArgumentException
     *          se o offset for inválido.
     */
    public void setOffset(Integer offset) throws IllegalArgumentException {
        if(offset != null && offset < 0)
            throw new IllegalArgumentException("illegal offset");
        
        this.offset = offset;
        this.parOffset = null;
    }


    /**
     * Determina o offset de teste.
     *
     * @param offset
     *          parâmetro representando o valor do offset a ser utilizado no teste.
     */
    public void setOffset(P offset) {
        this.parOffset = offset;
        this.offset = null;
    }
    
    
    /**
     * Retorna o offset de teste.
     * 
     * @return
     *          inteiro representando o valor do offset a ser utilizado no teste.
     */
    public Integer getOffset() {
        return offset;
    }


    /**
     * Retorna o offset de teste.
     *
     * @return
     *          parâmetro representando o valor do offset a ser utilizado no teste.
     */
    public P getParamOffset() {
        return parOffset;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident< 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<attributeAssessment";
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        if(getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";
        if(getKey() != null)
            content += " key='" + getKey().toString() + "'";
        if(getParamKey() != null)
            content += " key='$" + getParamKey().getId() + "'";
        if(getAttributeType() != null)
            content += " attributeType='" + getAttributeType().toString() + "'";        
        if(getOffset() != null)
            content += " offset='" + getOffset() + "'";
        if(getParamOffset() != null)
            content += " offset='$" + getParamOffset().getId() + "'";
        content += "/>\n";

        return content;
    }

    
    public int compareTo(A other) {//@todo: fazer o compareTo simétrico
        int comp = 0;

        String this_att, other_att;

        // Compara pelo role
        if(getRole() == null) this_att = ""; else this_att = getRole().getName();
        if(other.getRole() == null) other_att = ""; else other_att = other.getRole().getName();
        comp = this_att.compareTo(other_att);

        // Compara pelo tipo do evento
        if(comp == 0){
            if(getEventType() == null) this_att = ""; else this_att = getEventType().toString();
            if(other.getEventType() == null) other_att = ""; else other_att = other.getEventType().toString();
            comp = this_att.compareTo(other_att);
        }

        // Compara pelo tipo do atributo
        if(comp == 0){
            if(getAttributeType() == null) this_att = ""; else this_att = getAttributeType().toString();
            if(other.getAttributeType() == null) other_att = ""; else other_att = other.getAttributeType().toString();
            comp = this_att.compareTo(other_att);
        }

        // Compara pelo offset
        if(comp == 0){
            int this_off, other_off;
            if(getOffset() == null) this_off = 0; else this_off = getOffset();
            if(other.getOffset() == null) other_off = 0; else other_off = other.getOffset();
            comp = this_off - other_off;
        }

        // Compara pelo offset (parametro)
        if(comp == 0){
            if(getParamOffset() == null && other.getParamOffset() == null)
                comp = 0;
            else if(getParamOffset() != null && other.getParamOffset() != null)
                comp = getParamOffset().compareTo(other.getParamOffset());
            else
                comp = 1;
        }

        // Compara pela tecla
        if(comp == 0){
            if(getKey() == null) this_att = ""; else this_att = getKey().toString();
            if(other.getKey() == null) other_att = ""; else other_att = other.getKey().toString();
            comp = this_att.compareTo(other_att);
        }

        // Compara pela tecla (parametro)
        if(comp == 0){
            if(getParamKey() == null && other.getParamKey() == null)
                comp = 0;
            else if(getParamKey() != null && other.getParamKey() != null)
                comp = getParamKey().compareTo(other.getParamKey());
            else
                comp = 1;
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getRole() == null){
            addError("Elemento não possui atributo obrigatório role.");
            valid = false;
        }
        if(getEventType() == null){
            addError("Elemento não possui atributo obrigatório eventType.");
            valid = false;
        }
        if(getEventType() != null){
            switch(getEventType()){
                case SELECTION:
                    if(getKey() == null && getParamKey() == null){
                        addWarning("O atributo key deve ser especificado.");
                        valid = false;
                    }
                    if( !(getAttributeType() == NCLAttributeType.OCCURRENCES || getAttributeType() == NCLAttributeType.STATE) ){
                        addWarning("O atributo attributeType deve ser igual a occurrences ou state.");
                        valid = false;
                    }
                    break;
                case PRESENTATION:
                    if(getKey() != null || getParamKey() != null){
                        addWarning("O atributo key não deve ser especificado.");
                        valid = false;
                    }
                    if( !(getAttributeType() == NCLAttributeType.OCCURRENCES || getAttributeType() == NCLAttributeType.REPETITIONS || getAttributeType() == NCLAttributeType.STATE) ){
                        addWarning("O atributo attributeType deve ser igual a occurrences, repetitions ou state.");
                        valid = false;
                    }
                    break;
                case ATTRIBUTION:
                    if(getKey() != null || getParamKey() != null){
                        addWarning("O atributo key não deve ser especificado.");
                        valid = false;
                    }
            }
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
                else if(attributes.getLocalName(i).equals("offset")){
                    String value = attributes.getValue(i);
                    if(value.contains("$")){
                        value = value.substring(1);
                        setOffset((P) new NCLConnectorParam(value));//cast retirado na correcao das referencias
                    }
                    else
                        setOffset(new Integer(value));
                }
                else if(attributes.getLocalName(i).equals("eventType")){
                    for(NCLEventType e : NCLEventType.values()){
                        if(e.toString().equals(attributes.getValue(i)))
                            setEventType(e);
                    }
                }
                else if(attributes.getLocalName(i).equals("attributeType")){
                    for(NCLAttributeType t : NCLAttributeType.values()){
                        if(t.toString().equals(attributes.getValue(i)))
                            setAttributeType(t);
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

        if(getParamKey() != null)
            setKey(parameterReference(getParamKey().getId()));
        if(getParamOffset() != null)
            setOffset(parameterReference(getParamOffset().getId()));
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

        Iterable<P> params = ((NCLCausalConnector) connector).getConnectorParams();
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
