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
package br.uff.midiacom.ana.datatype.ncl.interfaces;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class NCLSwitchPortType<T extends NCLSwitchPortType, P extends NCLElement, Em extends NCLMappingPrototype> extends NCLIdentifiableElementPrototype<T, P> implements NCLInterface<T, P> {

    protected ElementList<Em, T> mappings = new ElementList<Em, T>();


    /**
     * Construtor do elemento <i>switchPort</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da porta de switch.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador for inválido.
     */
    public NCLSwitchPortType(String id) throws XMLException {
        setId(id);
    }


    /**
     * Adiciona um mapeamento a porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser adicionado.
     * @return
     *          Verdadeiro se o mapeamento foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMapping(Em mapping) throws XMLException {
        return mappings.add(mapping, (T) this);
    }


    /**
     * Remove um mapeamento da porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser removido.
     * @return
     *          Verdadeiro se o mapeamento foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMapping(Em mapping) throws XMLException {
        return mappings.remove(mapping);
    }


    /**
     * Verifica se a porta de switch possui um mapeamento.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser verificado.
     * @return
     *          verdadeiro se o mapeamento existir.
     */
    public boolean hasMapping(Em mapping) throws XMLException {
        return mappings.contains(mapping);
    }


    /**
     * Verifica se a porta de switch possui algum mapeamento.
     *
     * @return
     *          verdadeiro se a porta de switch possuir algum mapeamento.
     */
    public boolean hasMapping() {
        return !mappings.isEmpty();
    }


    /**
     * Retorna os mapeamentos da porta de switch.
     *
     * @return
     *          lista contendo os mapeamentos da porta de switch.
     */
    public ElementList<Em, T> getMappings() {
        return mappings;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // <port> element and attributes declaration
        content = space + "<switchPort";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasMapping()){
            for(Em mapping : mappings)
                content += mapping.parse(ident + 1);
        }

        content += "</switchPort>\n";

        return content;
    }
}
