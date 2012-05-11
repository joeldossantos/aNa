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
package br.uff.midiacom.ana.descriptor.param;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.ncl.descriptor.param.NCLIntegerDescriptorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


/**
 * Class that represents a descriptor parameter whose value is a integer value.
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
public class NCLIntegerDescriptorParam<T extends NCLIntegerDescriptorParam,
                                       P extends NCLElement,
                                       I extends NCLElementImpl>
        extends NCLIntegerDescriptorParamPrototype<T, P, I>
        implements NCLDescriptorParam<T, P, Integer> {


    /**
     * Descriptor parameter constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLIntegerDescriptorParam() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<descriptorParam";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }

    
    public void load(Element element) throws NCLParsingException {
        try{
            loadName(element);
            loadValue(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("DescriptorParam:\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseName();
        content += parseValue();
        
        return content;
    }
    
    
    protected String parseName() {
        NCLAttributes aux = getName();
        if(aux != null)
            return " name='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadName(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the name (required)
        att_name = NCLElementAttributes.NAME.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setName(NCLAttributes.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseValue() {
        String aux = getParamValue();
        if(aux != null)
            return " value='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadValue(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the value (required)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            try{
                setValue(new Integer(att_var));
            }catch(Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
}
