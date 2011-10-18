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
package br.uff.midiacom.ana.datatype.ncl.descriptor.param;

import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;


public class NCLDescriptorParamImpl<T extends NCLIdentifiableElement, P extends NCLElement, Ed extends NCLDescriptorParam, Ep extends NCLDescriptorParamPrototype, V>
        extends NCLElementImpl<T, P> {

    protected NCLAttributes name;
    private Ep owner;
    protected V value;


    /**
     * Construtor do elemento <i>descriptorParam</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLDescriptorParamImpl(Ep owner) {
        this.owner = owner;
    }


    /**
     * Atribui um nome ao parâmetro. Segue os nomes padronizados de atributos do descritor.
     *
     * @param name
     *          Elemento representando o nome do parâmetro.
     */
    public void setName(NCLAttributes name) {
        this.name = name;
    }


    /**
     * Retorna o nome do parâmetro.
     *
     * @return
     *          elemento representando o nome do parâmetro.
     */
    public NCLAttributes getName() {
        return name;
    }


    /**
     * Atribui um valor ao parâmetro.
     *
     * @param value
     *          valor do parâmetro.
     * @throws IllegalArgumentException
     *          se o valor não estiver de acordo com o esperado.
     */
    public void setValue(V value) {
        this.value = value;
    }


    /**
     * Retorna o valor do parâmetro.
     *
     * @return
     *          valor do parâmetro.
     */
    public V getValue() {
        return value;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<descriptorParam";
        if(getName() != null)
            content += " name='" + getName().toString() + "'";
        if((getValue() != null) || (owner instanceof NCLColorDescriptorParamPrototype && ((NCLColorDescriptorParamPrototype) owner).getIsTransparent() != null))
            content += " value='" + owner.getParamValue() + "'";
        content += "/>\n";

        return content;
    }


    public boolean compare(Ed other) {
        return getName().equals(other.getName());
    }
}
