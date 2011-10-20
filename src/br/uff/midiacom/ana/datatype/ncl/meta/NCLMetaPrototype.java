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
package br.uff.midiacom.ana.datatype.ncl.meta;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class NCLMetaPrototype<T extends NCLMetaPrototype, P extends NCLElement, I extends XMLElementImpl>
        extends XMLElementPrototype<T, P, I> implements NCLElement<T, P> {

    protected StringType name;
    protected StringType mcontent;


    /**
     * Construtor do elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLMetaPrototype() throws XMLException {
        super();
    }


    /**
     * Atribui um nome ao metadado.
     *
     * @param name
     *          String representando o nome do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setName(String name) throws XMLException {
        this.name = new StringType(name);
    }


    /**
     * Retorna o nome do metadado.
     *
     * @return
     *          String representando o nome do metadado.
     */
    public String getName() {
        if(name != null)
            return name.getValue();
        else
            return null;
    }


    /**
     * Atribui um conteúdo ao metadado.
     *
     * @param content
     *          String representando o conteúdo do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setContent(String content) throws XMLException {
        this.mcontent = new StringType(content);
    }


    /**
     * Retorna o conteúdo do metadado.
     * 
     * @return
     *          String representando o conteúdo do metadado.
     */
    public String getContent() {
        if(mcontent != null)
            return mcontent.getValue();
        else
            return null;
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
        content = space + "<meta";
        if(getName() != null)
            content += " name='" + getName() + "'";
        if(getContent() != null)
            content += " content='" + getContent() + "'";
        content += "/>\n";

        return content;
    }


    public boolean compare(T other) {
        return getName().equals(other.getName());
    }
}
