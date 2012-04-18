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
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.xml.XMLException;


/**
 * Class that represents a descriptor parameter whose value is a percent value.
 * 
 * <br/>
 * 
 * This element is used to parameterize the presentation of the node associated
 * to a descriptor. The descriptorParam may redefine the value of an attribute
 * defined by a region element or define new attributes for the node presentation.
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
 */
public abstract class NCLPercentDescriptorParamPrototype<T extends NCLPercentDescriptorParamPrototype,
                                                         P extends NCLElement,
                                                         I extends NCLElementImpl>
        extends NCLDescriptorParamPrototype<T, P, I, Double> {

    private boolean relative;


    /**
     * Descriptor parameter constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLPercentDescriptorParamPrototype() throws XMLException {
        super();
        relative = false;
    }


    @Override
    public void setName(NCLAttributes name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        if(!name.equals(NCLAttributes.TRANSPARENCY) && !name.equals(NCLAttributes.SOUND_LEVEL)
                && !name.equals(NCLAttributes.BALANCE_LEVEL) && !name.equals(NCLAttributes.TREBLE_LEVEL)
                && !name.equals(NCLAttributes.BASS_LEVEL))
            throw new XMLException("This parameter type can not be used with this name.");

        super.setName(name);
    }


    @Override
    public void setValue(Double value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
        if(!relative && (value < 0 || value > 1))
            throw new XMLException("The value of the paramenter must be between 0 and 1");
        if(relative && (value < 0 || value > 100))
            throw new XMLException("The relative value of the paramenter must be between 0 and 100");

        super.setValue(value);
    }


    @Override
    protected void setParamValue(String value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
        
        int index = value.indexOf("%");
        if(index > 0){
            value = value.substring(0, index);
            setRelative(true);
        }

        setValue(new Double(value));
    }


    @Override
    protected String getParamValue() {
        if(relative)
            return (getValue().toString())+"%";
        else
            return getValue().toString();
    }


    /**
     * Determines if the value is a percent value.
     * 
     * @param relative 
     *          true if the value is a percent value.
     */
    public void setRelative(boolean relative) {
        this.relative = relative;
    }


    /**
     * Verifies if the value is a percent value.
     * 
     * @return 
     *          true if the value is a percent value.
     */
    public boolean getRelative() {
        return relative;
    }
}
