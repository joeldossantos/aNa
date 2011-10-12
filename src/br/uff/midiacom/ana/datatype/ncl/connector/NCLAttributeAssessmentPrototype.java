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

import br.uff.midiacom.ana.datatype.auxiliar.IntegerParamType;
import br.uff.midiacom.ana.datatype.auxiliar.KeyParamType;
import br.uff.midiacom.ana.datatype.enums.NCLAttributeType;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;


public class NCLAttributeAssessmentPrototype<T extends NCLAttributeAssessmentPrototype, P extends NCLElement, I extends XMLElementImpl, Er extends NCLRolePrototype, Ep extends NCLConnectorParamPrototype>
        extends XMLElementPrototype<T, P, I> implements NCLElement<T, P> {

    protected Er role;
    protected NCLEventType eventType;
    protected KeyParamType<Ep> key;
    protected NCLAttributeType attributeType;
    protected IntegerParamType<Ep> offset;
    

    /**
     * Construtor do elemento <i>attributeStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLAttributeAssessmentPrototype() {}


    /**
     * Determina o papel do atributo da assertiva.
     * 
     * @param role
     *          String representando o papel definido pela assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o role for uma String vazia.
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
     * Retorna o papel atribuido ao atributo da assertiva.
     * 
     * @return
     *          elemento representando o papel.
     */
    public Er getRole() {
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
    public void setKey(KeyParamType<Ep> key) {
        this.key = key;
    }
    
    
    /**
     * Retorna a tecla testada pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando a tecla.
     */
    public KeyParamType<Ep> getKey() {
        return key;
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
    public void setOffset(IntegerParamType<Ep> offset) throws IllegalArgumentException {
        this.offset = offset;
    }
    
    
    /**
     * Retorna o offset de teste.
     * 
     * @return
     *          inteiro representando o valor do offset a ser utilizado no teste.
     */
    public IntegerParamType<Ep> getOffset() {
        return offset;
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
            content += " key='" + getKey().parse() + "'";
        if(getAttributeType() != null)
            content += " attributeType='" + getAttributeType().toString() + "'";        
        if(getOffset() != null)
            content += " offset='" + getOffset().parse() + "'";
        content += "/>\n";

        return content;
    }

    
    public boolean compare(T other) {
        boolean comp = true;

        String this_att, other_att;

        // Compara pelo role
        if(getRole() == null) this_att = ""; else this_att = getRole().getName();
        if(other.getRole() == null) other_att = ""; else other_att = other.getRole().getName();
        comp &= this_att.equals(other_att);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_att = ""; else this_att = getEventType().toString();
        if(other.getEventType() == null) other_att = ""; else other_att = other.getEventType().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo tipo do atributo
        if(getAttributeType() == null) this_att = ""; else this_att = getAttributeType().toString();
        if(other.getAttributeType() == null) other_att = ""; else other_att = other.getAttributeType().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo offset
        if(getOffset() == null) this_att = ""; else this_att = getOffset().parse();
        if(other.getOffset() == null) other_att = ""; else other_att = other.getOffset().parse();
        comp &= this_att.equals(other_att);

        // Compara pela tecla
        if(getKey() == null) this_att = ""; else this_att = getKey().parse();
        if(other.getKey() == null) other_att = ""; else other_att = other.getKey().parse();
        comp &= this_att.equals(other_att);


        return comp;
    }
}
