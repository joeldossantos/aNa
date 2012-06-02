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
package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


/**
 * Class that represents a link or bind parameter element. This element is used
 * to define a value to a parameter defined in a connector.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - reference to the name of the parameter defined by a
 *                    connector. This attribute is required.</li>
 *  <li><i>value</i> - value of the parameter defined by a connector. This
 *                    attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec> 
 */
public abstract class NCLParam<T extends NCLParam,
                               P extends NCLElement,
                               I extends NCLElementImpl,
                               Ec extends NCLConnectorParam>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected Ec name;
    protected Object value;
    
    
    /**
     * Parameter element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLParam() throws XMLException {
        super();
    }
    
    
    /**
     * Sets the reference to the name of the parameter defined by the connector.
     * This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The connector parameter referred must be a parameter defined by the
     * connector used by the link where this parameter element is defined.
     * 
     * @param connectorParam
     *          element representing a reference to a connector parameter name.
     * @throws XMLException 
     *          if the connector parameter is null or any error occur while
     *          creating the reference to the connector parameter.
     */
    public void setName(Ec connectorParam) throws XMLException {
        if(connectorParam == null)
            throw new XMLException("Null connector parameter.");
        
        Ec aux = this.name;
        
        this.name = connectorParam;
        this.name.addReference(this);
        
        impl.notifyAltered(NCLElementAttributes.NAME, aux, connectorParam);
        if(aux != null)
            aux.removeReference(this);
    }
    
    
    /**
     * Returns the reference to the name of the parameter defined by the
     * connector or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The connector parameter referred must be a parameter defined by the
     * connector used by the link where this parameter element is defined.
     * 
     * @return 
     *          element representing a reference to a connector parameter name
     *          or <i>null</i> if the attribute is not defined.
     */
    public Ec getName() {
        return name;
    }
    
    
    /**
     * Sets the value of the parameter defined by a connector. This attribute is
     * required and can not be set to <i>null</i>.
     * 
     * @param value
     *          string representing the value to be set to the connector parameter.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public void setValue(Object value)  throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
        
        Object aux = this.value;
        
        if(value instanceof String){
            
        }
        
        this.value = value;
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
    /**
     * Returns the value of the parameter defined by a connector or <i>null</i>
     * if the attribute is not defined.
     * 
     * @return
     *          string representing the value to be set to the connector parameter
     *          or <i>null</i> if the attribute is not defined.
     */
    public String getValue() {
        if(value != null)
            return value.toString();
        else
            return null;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        return getName().equals(other.getName());
    }


    @Override
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // param element and attributes declaration
        content = space + "<" + getType();
        content += parseAttributes();
        content += "/>\n";
        
        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadName(element);
            loadValue(element);
        }
        catch(XMLException ex){
            String aux = null;
            if(name != null)
               aux = name.getName();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException(getType() + aux +":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseName();
        content += parseValue();
        
        return content;
    }
    
    
    protected String parseName() {
        Ec aux = getName();
        if(aux != null)
            return " name='" + aux.getName() + "'";
        else
            return "";
    }
    
    
    protected abstract void loadName(Element element) throws XMLException;
    
    
    protected String parseValue() {
        String aux = getValue();
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
            setValue(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected abstract String getType();
}
