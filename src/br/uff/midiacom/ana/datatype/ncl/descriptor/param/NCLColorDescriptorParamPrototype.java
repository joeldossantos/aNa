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
package br.uff.midiacom.ana.datatype.ncl.descriptor.param;

import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.xml.XMLException;


public abstract class NCLColorDescriptorParamPrototype<T extends NCLColorDescriptorParamPrototype,
                                                       P extends NCLElement,
                                                       I extends NCLElementImpl>
        extends NCLDescriptorParamPrototype<T, P, I, NCLColor> {


    private Boolean isTransparent;


    public NCLColorDescriptorParamPrototype() throws XMLException {
        super();
    }


    @Override
    public void setName(NCLAttributes name) throws IllegalArgumentException {
        if(!name.equals(NCLAttributes.BACKGROUND) && !name.equals(NCLAttributes.FONT_COLOR))
            throw new IllegalArgumentException("This parameter type can not be used with this name.");

        super.setName(name);
    }


    @Override
    protected void setParamValue(String value) {
        if(value.equals("transparent")){
            setIsTransparent(true);
            setValue(null);
            return;
        }

        for(NCLColor color : NCLColor.values()){
            if(value.equals(color.toString())){
                setValue(color);
                this.isTransparent = false;
                break;
            }
        }
    }


    @Override
    protected String getParamValue() {
        if((getIsTransparent() != null) && (getIsTransparent()))
            return "transparent";
        
        return getValue().toString();
    }


    public void setIsTransparent(Boolean isTransparent) throws IllegalArgumentException {
        if(!getName().equals(NCLAttributes.BACKGROUND))
            throw new IllegalArgumentException("This value can not be used with this parameter.");

        this.isTransparent = isTransparent;
    }


    public Boolean getIsTransparent() {
        return isTransparent;
    }
}
