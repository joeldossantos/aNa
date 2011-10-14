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
import br.uff.midiacom.ana.datatype.ncl.node.NCLNode;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;


public class NCLMappingPrototype<T extends NCLMappingPrototype, P extends NCLElement, I extends XMLElementImpl, En extends NCLNode, Ei extends NCLInterface>
        extends XMLElementPrototype<T, P, I> implements NCLElement<T, P> {

    protected En component;
    protected Ei interfac;


    /**
     * Construtor do elemento <i>mapping</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLMappingPrototype() {}


    /**
     * Determina o componente mapeado pelo mapeamento.
     *
     * @param component
     *          elemento representando o componente mapeado.
     */
    public void setComponent(En component) {
        this.component = component;
    }


    /**
     * Retorna o componente mapeado pelo mapeamento.
     *
     * @return
     *          elemento representando o componente mapeado.
     */
    public En getComponent() {
        return component;
    }


    /**
     * Determina a interface mapeada pelo mapeamento.
     *
     * @param interfac
     *          elemento representando a interface mapeada.
     */
    public void setInterface(Ei interfac) {
        this.interfac = interfac;
    }


    /**
     * Retorna a interface mapeada pelo mapeamento.
     *
     * @return
     *          elemento representando a interface mapeada.
     */
    public Ei getInterface() {
        return interfac;
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
        content = space + "<mapping";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        content += "/>\n";

        return content;
    }

    public boolean compare(T other) {
        boolean comp = true;

        // Compara pelo componente
        comp &= getComponent().compare(other.getComponent());

        // Compara pela interface
        comp &= getInterface().compare(other.getInterface());

        return comp;
    }
}
