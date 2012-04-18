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
package br.uff.midiacom.ana.datatype.ncl.descriptor.param;

import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;


/**
 * Class that represents a descriptor parameter. This element is used to
 * parameterize the presentation of the node associated to a descriptor. The
 * descriptorParam may redefine the value of an attribute defined by a region
 * element or define new attributes for the node presentation.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the descriptor parameter. This attribute is required.</li>
 *  <li><i>value</i> - value of the descriptor parameter. This attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <V> 
 */
public abstract class NCLDescriptorParamPrototype<T extends NCLDescriptorParam,
                                                  P extends NCLElement,
                                                  I extends NCLElementImpl,
                                                  V>
        extends NCLElementPrototype<T, P, I>
        implements NCLDescriptorParam<T, P, V> {

    protected NCLAttributes name;
    protected V value;


    /**
     * Descriptor parameter constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorParamPrototype() throws XMLException {
        super();
    }


    @Override
    public void setName(NCLAttributes name) throws XMLException {
        NCLAttributes aux = this.name;
        this.name = name;
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }


    @Override
    public NCLAttributes getName() {
        return name;
    }


    @Override
    public void setValue(V value) throws XMLException {
        V aux = this.value;
        this.value = value;
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }


    @Override
    public V getValue() {
        return value;
    }


    @Override
    public boolean compare(T other) {
        return getName().equals(other.getName());
    }


    /**
     * Receives the parameter value as a string and converts this value to the
     * type used by the parameter. The value can not be <i>null</i>.
     * 
     * <br/>
     * 
     * This method must be extended by classes that implement descriptor
     * parameters.
     * 
     * @param value 
     *          string representing the parameter value.
     * @throws XMLException 
     *          if the value is null.
     */
    protected abstract void setParamValue(String value) throws XMLException;


    /**
     * Returns the parameter value as a string or <i>null</i> if the attribute
     * is not defined.
     * 
     * <br/>
     * 
     * This method must be extended by classes that implement descriptor
     * parameters.
     *
     * @return
     *          string representing the parameter value or <i>null</i> if the
     *          attribute is not defined.
     */
    protected abstract String getParamValue();
}
