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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.basic.SampleType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLAreaPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.array.ArrayType;
import org.w3c.dom.Element;


public class NCLArea<T extends NCLArea,
                     P extends NCLElement,
                     I extends NCLElementImpl,
                     Ei extends NCLInterface>
        extends NCLAreaPrototype<T, P, I, Ei>
        implements NCLInterface<Ei, P> {
    
    
    public NCLArea() throws XMLException {
        super();
    }


    public NCLArea(String id) throws XMLException {
        super();
        setId(id);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
                
        // <area> element and attributes declaration
        content = space + "<area";
        content += parseAttributes();
        content += "/>\n";
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
            loadCoords(element);
            loadBegin(element);
            loadEnd(element);
            loadText(element);
            loadPosition(element);
            loadFirst(element);
            loadLast(element);
            loadLabel(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Area" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseCoords();
        content += parseBegin();
        content += parseEnd();
        content += parseText();
        content += parsePosition();
        content += parseFirst();
        content += parseLast();
        content += parseLabel();
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseCoords() {
        ArrayType aux = getCoords();
        if(aux != null)
            return " coords='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadCoords(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the coords (optional)
        att_name = NCLElementAttributes.COORDS.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setCoords(new ArrayType(att_var));
    }
    
    
    protected String parseBegin() {
        TimeType aux = getBegin();
        if(aux != null)
            return " begin='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadBegin(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the begin (optional)
        att_name = NCLElementAttributes.BEGIN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBegin(new TimeType(att_var));
    }
    
    
    protected String parseEnd() {
        TimeType aux = getEnd();
        if(aux != null)
            return " end='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadEnd(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the end (optional)
        att_name = NCLElementAttributes.END.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setEnd(new TimeType(att_var));
    }
    
    
    protected String parseText() {
        String aux = getText();
        if(aux != null)
            return " text='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadText(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the text (optional)
        att_name = NCLElementAttributes.TEXT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setText(att_var);
    }
    
    
    protected String parsePosition() {
        Integer aux = getPosition();
        if(aux != null)
            return " position='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadPosition(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the position (optional)
        att_name = NCLElementAttributes.POSITION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            try{
                setPosition(new Integer(att_var));
            }catch(Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        }
    }
    
    
    protected String parseFirst() {
        SampleType aux = getFirst();
        if(aux != null)
            return " first='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadFirst(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the first (optional)
        att_name = NCLElementAttributes.FIRST.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFirst(new SampleType(att_var));
    }
    
    
    protected String parseLast() {
        SampleType aux = getLast();
        if(aux != null)
            return " last='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadLast(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the last (optional)
        att_name = NCLElementAttributes.LAST.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setLast(new SampleType(att_var));
    }
    
    
    protected String parseLabel() {
        String aux = getLabel();
        if(aux != null)
            return " label='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadLabel(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the label (optional)
        att_name = NCLElementAttributes.LABEL.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setLabel(att_var);
    }
}
