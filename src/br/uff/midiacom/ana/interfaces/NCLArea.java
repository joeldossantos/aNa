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
import br.uff.midiacom.ana.datatype.auxiliar.SampleType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLAreaPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.array.ArrayType;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLArea<T extends NCLArea, P extends NCLElement, I extends NCLElementImpl, Ei extends NCLInterface>
        extends NCLAreaPrototype<T, P, I, Ei> implements NCLInterface<Ei, P> {
    
    
    public NCLArea(String id) throws XMLException {
        super(id);
    }


    public NCLArea() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setCoords(ArrayType coords) {
        ArrayType aux = this.coords;
        super.setCoords(coords);
        impl.notifyAltered(NCLElementAttributes.COORDS, aux, coords);
    }
    
    
    @Override
    public void setBegin(TimeType begin) {
        TimeType aux = this.begin;
        super.setBegin(begin);
        impl.notifyAltered(NCLElementAttributes.BEGIN, aux, begin);
    }
    
        
    @Override
    public void setEnd(TimeType end) {
        TimeType aux = this.end;
        super.setEnd(end);
        impl.notifyAltered(NCLElementAttributes.END, aux, end);
    }
    
        
    @Override
    public void setText(String text) throws XMLException {
        StringType aux = this.text;
        super.setText(text);
        impl.notifyAltered(NCLElementAttributes.TEXT, aux, text);
    }


    @Override
    public void setPosition(Integer position) throws XMLException {
        Integer aux = this.position;
        super.setPosition(position);
        impl.notifyAltered(NCLElementAttributes.POSITION, aux, position);
    }
    
        
    @Override
    public void setFirst(SampleType first) {
        SampleType aux = this.first;
        super.setFirst(first);
        impl.notifyAltered(NCLElementAttributes.FIRST, aux, first);
    }
    
        
    @Override
    public void setLast(SampleType last) {
        SampleType aux = this.last;
        super.setLast(last);
        impl.notifyAltered(NCLElementAttributes.LAST, aux, last);
    }
    
        
    @Override
    public void setLabel(String label) throws XMLException {
        StringType aux = this.label;
        super.setLabel(label);
        impl.notifyAltered(NCLElementAttributes.LABEL, aux, label);
    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the coords (optional)
        att_name = NCLElementAttributes.COORDS.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setCoords(new ArrayType(att_var));

        // set the begin (optional)
        att_name = NCLElementAttributes.BEGIN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBegin(new TimeType(att_var));

        // set the end (optional)
        att_name = NCLElementAttributes.END.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setEnd(new TimeType(att_var));

        // set the text (optional)
        att_name = NCLElementAttributes.TEXT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setText(att_var);

        // set the position (optional)
        att_name = NCLElementAttributes.POSITION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            try{
                setPosition(new Integer(att_var));
            }catch(Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        }

        // set the first (optional)
        att_name = NCLElementAttributes.FIRST.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFirst(new SampleType(att_var));

        // set the last (optional)
        att_name = NCLElementAttributes.LAST.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setLast(new SampleType(att_var));

        // set the label (optional)
        att_name = NCLElementAttributes.LABEL.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setLabel(att_var);
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
