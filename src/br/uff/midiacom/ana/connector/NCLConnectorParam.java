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
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um parâmetro de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLConnectorParam<P extends NCLConnectorParam> extends NCLIdentifiableElement implements Comparable<P> {
    
    private String type;
    
    
    /**
     * Construtor do elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     *         java.lang.IllegalArgumentException
     *          se a String do tipo for vazia.
     */
    public NCLConnectorParam(String name) throws NCLInvalidIdentifierException {
        setName(name);
    }


    /**
     * Construtor do elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLConnectorParam(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um nome ao parâmetro
     *
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     */
    public void setName(String name) throws NCLInvalidIdentifierException {
        notifyAltered(NCLElementAttributes.NAME, getId(), name);
        setId(name);
    }
    
    
    /**
     * Retorna o nome do parâmetro
     * 
     * @return
     *          String contendo o nome atribuido ao parâmetro.
     */
    public String getName() {
        return getId();
    }
    
    
    /**
     * Atribui um tipo ao parâmetro
     *
     * @param type
     *      String contendo o tipo do parâmetro.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setType(String type) {
        if(type != null && "".equals(type.trim()))
            throw new IllegalArgumentException("Empty type String");

        notifyAltered(NCLElementAttributes.TYPE, this.type, type);
        this.type = type;
    }


    /**
     * Retorna o tipo do parâmetro
     * 
     * @return
     *      String contendo o tipo do parâmetro.
     */
    public String getType() {
        return type;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<connectorParam";
        if(getName() != null)
            content += " name='" + getName() + "'";
        if(getType() != null)
            content += " type='" + getType() + "'";        
        content += "/>\n";

        return content;
    }

    
    public int compareTo(P other) {
        return getName().compareTo(other.getName());
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("name"))
                    setName(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("type"))
                    setType(attributes.getValue(i));
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }
}
