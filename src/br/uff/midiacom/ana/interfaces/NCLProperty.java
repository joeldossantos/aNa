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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma propriedade de um nó.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLProperty<T extends NCLProperty, P extends NCLElement, I extends NCLElementImpl, Ei extends NCLInterface>
        extends NCLPropertyPrototype<T, P, I, Ei> implements NCLInterface<Ei, P> {

    
    /**
     * Construtor do elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public NCLProperty(String name) throws XMLException {
        super(name);
        impl = (I) new NCLElementImpl(this);
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
    public NCLProperty(NCLSystemVariable name) throws XMLException {
        super(name);
        impl = (I) new NCLElementImpl(this);
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
    @Override
    public void setName(String name) throws XMLException {
        StringType aux = this.name;
        super.setName(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }    


    /**
     * Determina o nome de uma propriedade seguindo os valore padrão especificados na norma.
     * 
     * @param name
     *          NCLSystemVariable contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    @Override
    public void setName(NCLSystemVariable name) throws XMLException {
        StringType aux = this.name;
        super.setName(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }


    /**
     * Atribui um valor a propriedade.
     * 
     * @param value
     *          String representando o valor a ser atribuido.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    @Override
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("name"))
//                    setName(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("value"))
//                    setValue(attributes.getValue(i));
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
