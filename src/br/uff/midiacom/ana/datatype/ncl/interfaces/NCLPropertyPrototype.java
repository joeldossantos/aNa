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

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class NCLPropertyPrototype<T extends NCLPropertyPrototype, P extends NCLElement, I extends NCLElementImpl, Ei extends NCLInterface>
        extends NCLIdentifiableElementPrototype<Ei, P, I> implements NCLInterface<Ei, P> {

    protected StringType value;
    
    
    /**
     * Construtor do elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public NCLPropertyPrototype(String name) throws XMLException {
        super();
        setName(name);
    }    


    /**
     * Construtor do elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
     * Segue os nomes da variáveis de sistema de NCL.
     * 
     * @param name
     *          NCLSystemVariable contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public NCLPropertyPrototype(NCLSystemVariable name) throws XMLException {
        setName(name);
    }
    
    
    /**
     * Determina o nome da propriedade sem seguir os valores padrão especificados na norma.
     * O nome, entretando pode estar na forma shared.xxx
     * 
     * @param name
     *          String contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public void setName(String name) throws XMLException {
        setId(name);
    }    


    /**
     * Determina o nome de uma propriedade seguindo os valore padrão especificados na norma.
     * 
     * @param name
     *          NCLSystemVariable contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public void setName(NCLSystemVariable name) throws XMLException {
        if(name == null)
            throw new XMLException("Invalid name");
        setId(name.toString());
    }
    
    
    /**
     * Retorna o nome da propriedade.
     * 
     * @return
     *          String contendo o nome da propriedade.
     */
    public String getName() {
        return getId();
    }
    
    
    /**
     * Atribui um valor a propriedade.
     * 
     * @param value
     *          String representando o valor a ser atribuido.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws XMLException {
        this.value = new StringType(value);
    }
    
    
    /**
     * Retorna o valor atributo a propriedade.
     * 
     * @return
     *          String representando o valor atribuido.
     */
    public String getValue() {
        return value.getValue();
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        // <property> element and attributes declaration
        content = space + "<property";
        if(getName() != null)
            content += " name='" + getName() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";
        
        
        return content;
    }
}
