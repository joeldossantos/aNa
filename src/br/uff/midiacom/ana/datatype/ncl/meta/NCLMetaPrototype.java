/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents the meta element. This element is used to describe an
 * NCL document.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the metadata property. This attribute is required.</li>
 *  <li><i>content</i> - value of the metadata property. This attribute is
 *                     required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public abstract class NCLMetaPrototype<T extends NCLMetaPrototype,
                                       P extends NCLElement,
                                       I extends NCLElementImpl>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected StringType name;
    protected StringType mcontent;


    /**
     * Meta element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLMetaPrototype() throws XMLException {
        super();
    }


    /**
     * Sets the name of the metadata property. This attribute is required and
     * can not be set to <i>null</i>.
     * 
     * @param name
     *          string representing the name of the metadata property.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public void setName(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        StringType aux = this.name;
        this.name = new StringType(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }


    /**
     * Returns the name of the metadata property or <i>null</i> if the attribute is
     * not defined.
     *
     * @return
     *          string representing the name of the metadata property or null if
     *          the attribute is not defined.
     */
    public String getName() {
        if(name != null)
            return name.getValue();
        else
            return null;
    }


    /**
     * Sets the value of the metadata property. This attribute is required and
     * can not be set to <i>null</i>.
     * 
     * @param content
     *          string representing the name of the metadata property.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public void setContent(String content) throws XMLException {
        if(content == null)
            throw new XMLException("Null content.");
        
        StringType aux = this.mcontent;
        this.mcontent = new StringType(content);
        impl.notifyAltered(NCLElementAttributes.CONTENT, aux, content);
    }


    /**
     * Returns the value of the metadata property or <i>null</i> if the attribute
     * is not defined.
     *
     * @return
     *          string representing the value of the metadata property or null
     *          if the attribute is not defined.
     */
    public String getContent() {
        if(mcontent != null)
            return mcontent.getValue();
        else
            return null;
    }


    @Override
    public boolean compare(T other) {
        return getName().equals(other.getName());
    }
}
