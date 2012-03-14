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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.parameterized.IntegerParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.KeyParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLAttributeType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLAttributeAssessmentPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLAttributeAssessment<T extends NCLAttributeAssessment,
                                    P extends NCLElement,
                                    I extends NCLElementImpl,
                                    Er extends NCLRole,
                                    Ep extends NCLConnectorParam,
                                    R extends ConParamReference>
        extends NCLAttributeAssessmentPrototype<T, P, I, Er, Ep, R>
        implements NCLElement<T, P>{


    public NCLAttributeAssessment() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident< 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<attributeAssessment";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseRole();
        content += parseEventType();
        content += parseKey();
        content += parseAttributeType();
        content += parseOffset();
        
        return content;
    }
    
    
    protected String parseRole() {
        Er aux = getRole();
        if(aux != null)
            return " role='" + aux.getName() + "'";
        else
            return "";
    }
    
    
    protected String parseEventType() {
        NCLEventType aux = getEventType();
        if(aux != null)
            return " eventType='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseKey() {
        KeyParamType aux = getKey();
        if(aux != null)
            return " key='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseAttributeType() {
        NCLAttributeType aux = getAttributeType();
        if(aux != null)
            return " attributeType='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseOffset() {
        IntegerParamType aux = getOffset();
        if(aux != null)
            return " offset='" + aux.parse() + "'";
        else
            return "";
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;

        try{
            // set the role (required)
            att_name = NCLElementAttributes.ROLE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setRole(createRole(att_var));
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the eventType (required)
            att_name = NCLElementAttributes.EVENTTYPE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setEventType(NCLEventType.getEnumType(att_var));
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the key (optional)
            att_name = NCLElementAttributes.KEY.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setKey(new KeyParamType(att_var));

            // set the attributeType (optional)
            att_name = NCLElementAttributes.ATTRIBUTETYPE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setAttributeType(NCLAttributeType.getEnumType(att_var));

            // set the offset (optional)
            att_name = NCLElementAttributes.OFFSET.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setOffset(new IntegerParamType(att_var));
        }
        catch(XMLException ex){
            throw new NCLParsingException("AttributeAssessment:\n" + ex.getMessage());
        }
    }
    
    
    public Er findRole(String name) {
        if(role.getName().equals(name))
            return role;
        else
            return null;
    }


    /**
     * Function to create a connector <i>role</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a connector <i>role</i>.
     */
    protected Er createRole(String name) throws XMLException {
        return (Er) new NCLRole(name);
    }
}
