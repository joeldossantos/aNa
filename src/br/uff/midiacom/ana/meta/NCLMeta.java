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
package br.uff.midiacom.ana.meta;

import br.uff.midiacom.ana.NCLElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um metadado para o documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLMeta<M extends NCLMeta> extends NCLElement implements Comparable<M> {

    private String name;
    private String mcontent;


    /**
     * Construtor do elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLMeta() {}


    /**
     * Construtor do elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLMeta(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um nome ao metadado.
     *
     * @param name
     *          String representando o nome do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name != null && "".equals(name.trim()))
            throw new IllegalArgumentException("Empty String");

        this.name = name;
    }


    /**
     * Retorna o nome do metadado.
     *
     * @return
     *          String representando o nome do metadado.
     */
    public String getName() {
        return name;
    }


    /**
     * Atribui um conteúdo ao metadado.
     *
     * @param content
     *          String representando o conteúdo do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setContent(String content) throws IllegalArgumentException {
        if (content != null && "".equals(content.trim()))
            throw new IllegalArgumentException("Empty String");

        this.mcontent = content;
    }


    /**
     * Retorna o conteúdo do metadado.
     * 
     * @return
     *          String representando o conteúdo do metadado.
     */
    public String getContent() {
        return mcontent;
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


    public int compareTo(M other) {
        return getName().compareTo(other.getName());
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        cleanWarnings();
        cleanErrors();
        for(int i = 0; i < attributes.getLength(); i++){
            if(attributes.getLocalName(i).equals("name"))
                setName(attributes.getValue(i));
            else if(attributes.getLocalName(i).equals("content"))
                setContent(attributes.getValue(i));
        }
    }
}
