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
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLProperty<T extends NCLProperty, P extends NCLElement, I extends NCLElementImpl, Ei extends NCLInterface>
        extends NCLPropertyPrototype<T, P, I, Ei> implements NCLInterface<Ei, P> {

    
    public NCLProperty(String name) throws XMLException {
        super(name);
    }


    public NCLProperty(NCLSystemVariable name) throws XMLException {
        super(name);
    }


    public NCLProperty() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setName(String name) throws XMLException {
        String aux = this.getName();
        super.setName(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }    


    @Override
    public void setName(NCLSystemVariable name) throws XMLException {
        String aux = this.getName();
        super.setName(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }


    @Override
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        // set the name (required)
        att_name = NCLElementAttributes.NAME.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setName(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the value (optional)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(att_var);
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
