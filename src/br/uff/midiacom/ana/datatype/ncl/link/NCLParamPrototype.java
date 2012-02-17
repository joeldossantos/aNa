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
package br.uff.midiacom.ana.datatype.ncl.link;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public abstract class NCLParamPrototype<T extends NCLParamPrototype,
                                        P extends NCLElement,
                                        I extends NCLElementImpl,
                                        Ec extends NCLConnectorParamPrototype>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P>{

    protected Ec name;
    protected StringType value;
    protected NCLParamInstance paramType;
    
    
    /**
     * Construtor do parâmetro interno a um elemento <i>link</i> ou <i>bind</i>.
     * 
     * @param paramType
     *          define se o parâmetro é de um elemento <i>link</i> ou <i>bind</i>.
     *
     * @throws java.lang.NullPointerException
     *          se o tipo for nulo.
     */
    public NCLParamPrototype(NCLParamInstance paramType) throws XMLException {
        super();
        if(paramType == null)
            throw new XMLException("Null type");

        this.paramType = paramType;
    }
    
    
    /**
     * Attribui um <i>connectorParam</i> ao parâmetro.
     * 
     * @param connectorParam
     *          elemento representando o parâmetro do conector ao qual este parâmetro se refere.
     */
    public void setName(Ec connectorParam) {
        Ec aux = this.name;
        this.name = connectorParam;
        impl.notifyAltered(NCLElementAttributes.NAME, aux, connectorParam);
    }
    
    
    /**
     * Retorna o <i>connectorParam</i> do parâmetro.
     * 
     * @return NCLConnectorParam representando o nome do parâmetro.
     */
    public Ec getName() {
        return name;
    }
    
    
    /**
     * Determina o valor do atributo value do parâmetro.
     * 
     * @param value
     *          String contendo o valor a ser atribuído ao parâmetro.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value)  throws XMLException {
        StringType aux = this.value;
        this.value = new StringType(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
    /**
     * Retorna o valor do atributo value do parâmetro.
     * 
     * @return
     *          String contendo o valor atribuído ao parâmetro.
     */
    public String getValue() {
        if(value != null)
            return value.getValue();
        else
            return null;
    }


    public NCLParamInstance getType() {
        return paramType;
    }
    
    
    @Override
    public boolean compare(T other) {
        return getName().equals(other.getName());
    }
}
