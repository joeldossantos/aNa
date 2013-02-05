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

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLColor;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLFit;
import br.uff.midiacom.ana.util.enums.NCLFontVariant;
import br.uff.midiacom.ana.util.enums.NCLFontWeight;
import br.uff.midiacom.ana.util.enums.NCLNodeAttributes;
import br.uff.midiacom.ana.util.enums.NCLPlayerLife;
import br.uff.midiacom.ana.util.enums.NCLScroll;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLNamedElementPrototype;
import java.util.ArrayList;
import org.w3c.dom.Element;


/**
 * Class that represents a property element. A property represents a node
 * attribute or group of attributes. A property can also represent a global
 * variable.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the property. This attribute is required.</li>
 *  <li><i>value</i> - initial value of the property. This attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ei> 
 */
public class NCLProperty<T extends NCLElement,
                         Ev extends NCLVariable>
        extends NCLNamedElementPrototype<T, Object>
        implements NCLInterface<T> {

    protected Object value;
    
    protected ArrayList<T> references;
    
    
    /**
     * Property element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLProperty() {
        super();
        references = new ArrayList<T>();
    }
    
    
    public NCLProperty(Object name) throws XMLException {
        super();
        references = new ArrayList<T>();
        setName(name);
    }
    
    
    /**
     * Sets the name of the property element. This attribute is required and can
     * not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * A property element may represent an attribute or group of attributes of
     * a node element.
     * 
     * <br/>
     * 
     * If the property element is a child of a media element with type
     * <i>settings</i>, than it indicates the use of a global variable in the
     * list defined by the document.
     * 
     * @param name
     *          string, element from the enumeration <i>NCLNodeAttributes</i> or
     *          global variable representing the name of the property element.
     * @throws XMLException 
     *          if the name is null.
     */
    @Override
    public void setName(Object name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        Object aux;
        
        if(name instanceof String){
            String var = (String) name;
            
            if((aux = NCLNodeAttributes.getEnumType(var)) != null)
                name = aux;
            else if((getDoc() != null) && (aux = ((NCLDoc) getDoc()).getGlobalVariable((String) name)) != null){
                name = aux;
                ((Ev) name).addReference(this);
            }
            
            aux = this.name;
            this.name = name;
        }
        else if(name instanceof NCLNodeAttributes){
            aux = this.name;
            this.name = name;
        }
        else if(name instanceof NCLVariable){
            aux = this.name;
            this.name = name;
            ((Ev) name).addReference(this);
        }
        else
            throw new XMLException("Wrong name type.");
        
        
        notifyAltered(NCLElementAttributes.NAME, aux, name);
        //Erase the name as a variable
        if(aux != null && aux instanceof NCLVariable)
            ((Ev) name).removeReference(this);
    }
    
    
    /**
     * Return the name of the property element or <i>null</i> if the attribute
     * is not defined.
     * 
     * <br/>
     * 
     * A property element may represent an attribute or group of attributes of
     * a node element.
     * 
     * <br/>
     * 
     * If the property element is a child of a media element with type
     * <i>settings</i>, than it indicates the use of a global variable in the
     * list defined by the document.
     * 
     * @return
     *          string, element from the enumeration <i>NCLNodeAttributes</i> or
     *          global variable representing the name of the property element or
     *          <i>null</i> if the attribute is not defined.
     */
    @Override
    public Object getName() {
        return super.getName();
    }
    
    
    /**
     * Sets the initial value of the property. This attribute is optional. Set
     * the value to <i>null</i> to erase a value already defined.
     * 
     * @param value
     *          initial value of the property element.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setValue(Object value) throws XMLException {
        Object aux = this.value;
        
        if(value instanceof String)
            value = convertValue((String) value);
        
        this.value = value;
        notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
    /**
     * Return the initial value of the property or <i>null</i> if the attribute
     * is not defined.
     * 
     * @return
     *          string representing the initial value of the property or
     *          <i>null</i> if the attribute is not defined.
     */
    public Object getValue() {
        return value;
    }
    
    
    protected Object convertValue(String value) throws XMLException {
        if(value != null && "".equals(value.trim()))
            throw new XMLException("Empty value String");
        
        Object aux;
        
        if(value.equals("true"))
            return true;
        
        if(value.equals("false"))
            return false;
        
        try{
            return new Integer(value);
        }catch(Exception e){}

        try{
            return new Double(value);
        }catch(Exception e){}
        
        if((aux = NCLColor.getEnumType(value)) != null)
            return aux;
        
        if((aux = NCLFit.getEnumType(value)) != null)
            return aux;
        
        if((aux = NCLFontVariant.getEnumType(value)) != null)
            return aux;
        
        if((aux = NCLFontWeight.getEnumType(value)) != null)
            return aux;
        
        if((aux = NCLPlayerLife.getEnumType(value)) != null)
            return aux;
        
        if((aux = NCLScroll.getEnumType(value)) != null)
            return aux;
        
        return value;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLProperty))
            return false;
        
        boolean result = true;
        Object aux;
        
        if((aux = getName()) != null)
            result &= aux.equals(((NCLProperty) other).getName());
        if((aux = getValue()) != null)
            result &= aux.equals(((NCLProperty) other).getValue());

        return result;
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
        
        // <property> element and attributes declaration
        content = space + "<property";
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
            String aux = getName().toString();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Property" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseName();
        content += parseValue();
        
        return content;
    }
    
    
    protected String parseName() {
        Object aux = getName();
        if(aux != null){
            if(name instanceof NCLVariable)
                return " name='" + ((NCLVariable) aux).parse(0) + "'";
            else
                return " name='" + aux.toString() + "'";
        }
        else
            return "";
    }
    
    
    protected void loadName(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the name (required)
        att_name = NCLElementAttributes.NAME.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setName(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseValue() {
        Object aux = getValue();
        if(aux != null)
            return " value='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadValue(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the name (required)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(att_var);
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(T reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(T reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        value = null;
    }
}
