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
package br.uff.midiacom.ana.descriptor.param;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.datatype.NCLAttributes;
import br.uff.midiacom.ana.datatype.NCLFontVariant;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>descriptorParam</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um parametro de descritor do tipo scroll
 * em um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLFontVariantDescriptorParam<P extends NCLDescriptorParam> extends NCLDefaultDescriptorParam<P, NCLFontVariant> {


    public NCLFontVariantDescriptorParam() {
        super();
    }


    public NCLFontVariantDescriptorParam(XMLReader reader, NCLElement parent) {
        super(reader, parent);
    }


    @Override
    public void setName(NCLAttributes name) {
        super.setName(NCLAttributes.FONT_VARIANT);
    }


    @Override
    protected void setParamValue(String value) throws IllegalArgumentException {
        for(NCLFontVariant font : NCLFontVariant.values()){
            if(value.equals(font.toString()))
                setValue(font);
        }

        setValue(null);
    }


    @Override
    protected String getParamValue() {
        return getValue().toString();
    }
}
