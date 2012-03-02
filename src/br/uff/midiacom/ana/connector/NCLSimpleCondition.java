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
import br.uff.midiacom.ana.datatype.aux.parameterized.DoubleParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.KeyParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventTransition;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLSimpleConditionPrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


public class NCLSimpleCondition<T extends NCLSimpleCondition,
                                P extends NCLElement,
                                I extends NCLElementImpl,
                                Ec extends NCLCondition,
                                Er extends NCLRole,
                                Ep extends NCLConnectorParam,
                                Ip extends NCLImport,
                                R extends ReferenceType<Ec, Ep, Ip>>
        extends NCLSimpleConditionPrototype<T, P, I, Ec, Er, Ep, Ip, R>
        implements NCLCondition<Ec, P, Ep, Er, Ip, R> {


    public NCLSimpleCondition() throws XMLException {
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
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleCondition";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseRole();
        content += parseKey();
        content += parseDelay();
        content += parseMin();
        content += parseMax();
        content += parseQualifier();
        content += parseEventType();
        content += parseTransition();
        
        return content;
    }
    
    
    protected String parseRole() {
        Er aux = getRole();
        if(aux != null)
            return " role='" + aux.getName() + "'";
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
    
    
    protected String parseDelay() {
        DoubleParamType aux = getDelay();
        if(aux == null)
            return "";
        
        String content = " delay='" + aux.parse();
        if(aux.getValue() != null)
            content += "s'";
        else
            content += "'";
        
        return content;
    }
    
    
    protected String parseMin() {
        Integer aux = getMin();
        if(aux != null)
            return " min='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseMax() {
        MaxType aux = getMax();
        if(aux != null)
            return " max='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseQualifier() {
        NCLConditionOperator aux = getQualifier();
        if(aux != null)
            return " qualifier='" + aux.toString() + "'";
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
    
    
    protected String parseTransition() {
        NCLEventTransition aux = getTransition();
        if(aux != null)
            return " transition='" + aux.toString() + "'";
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

            // set the min (optional)
            att_name = NCLElementAttributes.MIN.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setMin(new Integer(att_var));
                }catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the max (optional)
            att_name = NCLElementAttributes.MAX.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setMax(new MaxType(att_var));

            // set the qualifier (optional)
            att_name = NCLElementAttributes.QUALIFIER.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setQualifier(NCLConditionOperator.getEnumType(att_var));

            // set the key (optional)
            att_name = NCLElementAttributes.KEY.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setKey(new KeyParamType(att_var));

            // set the eventType (optional)
            att_name = NCLElementAttributes.EVENTTYPE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setEventType(NCLEventType.getEnumType(att_var));

            // set the transition (optional)
            att_name = NCLElementAttributes.TRANSITION.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setTransition(NCLEventTransition.getEnumType(att_var));

            // set the delay (optional)
            att_name = NCLElementAttributes.DELAY.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDelay(new DoubleParamType(att_var));
        }
        catch(XMLException ex){
            throw new NCLParsingException("SimpleCondition:\n" + ex.getMessage());
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
