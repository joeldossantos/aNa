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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLAttributes;
import br.uff.midiacom.ana.util.enums.NCLColor;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLFit;
import br.uff.midiacom.ana.util.enums.NCLFontVariant;
import br.uff.midiacom.ana.util.enums.NCLFontWeight;
import br.uff.midiacom.ana.util.enums.NCLPlayerLife;
import br.uff.midiacom.ana.util.enums.NCLScroll;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import java.util.ArrayList;
import org.w3c.dom.Element;


/**
 * Class that represents a descriptor parameter. This element is used to
 * parameterize the presentation of the node associated to a descriptor. The
 * descriptorParam may redefine the value of an attribute defined by a region
 * element or define new attributes for the node presentation.
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
 * @param <V> 
 */
public class NCLDescriptorParam<T extends NCLElement, V>
        extends NCLElementPrototype<T>
        implements NCLElement<T> {

    protected NCLAttributes name;
    protected Object value;
    protected boolean percentSign;


    /**
     * Descriptor parameter constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorParam() {
        super();
        percentSign = false;
    }


    /**
     * Sets the name of the descriptor parameter. The element name represents
     * an attribute of descriptor related node presentation. This attribute is
     * required and can not be set to <i>null</i>. The possible parameter names
     * to be used are defined in the enumeration <i>NCLAttributes</i>.
     * 
     * @param name 
     *          parameter name from the enumeration <i>NCLAttributes</i>.
     * @throws XMLException 
     *          if the value representing the name is null.
     */
    public void setName(NCLAttributes name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        if(!matchNameAndType(name, value))
            throw new XMLException("Parameter name and value type does not match.");
        
        NCLAttributes aux = this.name;
        this.name = name;
        notifyAltered(NCLElementAttributes.NAME, aux, name);
    }


    /**
     * Returns the name of the descriptor parameter or <i>null</i> if the
     * attribute is not defined. The element name represents an attribute of
     * descriptor related node presentation. The possible parameter names
     * to be used are defined in the enumeration <i>NCLAttributes</i>.
     * 
     * @return
     *          parameter name from the enumeration <i>NCLAttributes</i> or
     *          <i>null</i> if the attribute is not defined.
     */
    public NCLAttributes getName() {
        return name;
    }


    /**
     * Sets the value of the descriptor parameter. This attribute is required
     * and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * Each parameter have its possible value types. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @param value 
     *          value of the descriptor parameter. The type of the value will
     *          depends on the parameter name.
     * @throws XMLException 
     *          if the value is null.
     */
    public void setValue(Object value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
        
        Object newvalue = null;
        if(value instanceof String)
            newvalue = convertValue((String) value);
        if(newvalue == null)
            newvalue = value;
        
        if(!matchNameAndType(name, newvalue))
            throw new XMLException("Parameter name and value type does not match.");
        
        Object aux = this.value;
        
        if(percentSign){
            if(!(newvalue instanceof Double))
                throw new XMLException("A relative value must have type Double.");
            
            Double var = (Double) newvalue;
            if(var < 0 || var > 100)
                throw new XMLException("The relative value of the paramenter must be between 0 and 100");
        }
        else if(newvalue instanceof Double){
            Double var = (Double) newvalue;
            if(!name.equals(NCLAttributes.FONT_SIZE) && (var < 0 || var > 1))
                throw new XMLException("The value of the paramenter must be between 0 and 1");
        }
            
        this.value = newvalue;
        
        notifyAltered(NCLElementAttributes.VALUE, aux, newvalue);
    }


    /**
     * Returns the value of the descriptor parameter or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * Each parameter have its possible value types. See the reference
     * (ABNT NBR 15606-2) to more information.
     *
     * @return
     *          value of the descriptor parameter or <i>null</i> if the attribute
     *          is not defined. The type of the value will depends on the
     *          parameter name
     */
    public Object getValue() {
        return value;
    }
    
    
    /**
     * Determines if the value of the descriptor parameter has a percent sign.
     * If it does, than the value type must be Double.
     * 
     * @param percentSign 
     *          boolean determining if the value has a percent sign.
     */
    public void setPercentSign(boolean percentSign) {
        this.percentSign = percentSign;
    }
    
    
    /**
     * Verifies if the value of the descriptor parameter has a percent sign.
     * If it does, than the value type must be Double.
     * 
     * @return
     *          boolean determining if the value has a percent sign.
     */
    public boolean getPercentSign() {
        return percentSign;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLDescriptorParam))
            return false;
        
        boolean result = true;
        Object aux;
        
        if((aux = getName()) != null)
            result &= aux.equals(((NCLDescriptorParam) other).getName());
        if((aux = getValue()) != null)
            result &= aux.equals(((NCLDescriptorParam) other).getValue());
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


        // param element and attributes declaration
        content = space + "<descriptorParam";
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
        Object aux = getValue();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer[]){
            String value = "";
            for (Integer v : (Integer[]) aux) {
                value += v.toString() + ",";
            }
            value = value.substring(0,value.length()-1);
            return " value='" + value + "'";
        }
        
        if(aux instanceof Double[]){
            String value = "";
            for (Double v : (Double[]) aux) {
                value += v.toString() + "%,";
            }
            value = value.substring(0,value.length()-1);
            return " value='" + value + "'";
        }
        
        if(percentSign)
            return " value='" + aux.toString() + "%'";
        else
            return " value='" + aux.toString() + "'";
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
    
    
    protected boolean matchNameAndType(NCLAttributes name, Object value){
        if(name == null || value == null)
            return true;
        
        if(value instanceof Integer[])
            return name.equals(NCLAttributes.BOUNDS) || name.equals(NCLAttributes.SIZE) ||
                    name.equals(NCLAttributes.LOCATION);
        
        if(value instanceof Double[])
            return name.equals(NCLAttributes.BOUNDS) || name.equals(NCLAttributes.SIZE) ||
                    name.equals(NCLAttributes.LOCATION);
        
        if(value instanceof Boolean)
            return name.equals(NCLAttributes.REUSE_PLAYER) || name.equals(NCLAttributes.VISIBLE);
        
        if(value instanceof Integer)
            return name.equals(NCLAttributes.ZINDEX) || name.equals(NCLAttributes.TOP) ||
                   name.equals(NCLAttributes.LEFT) || name.equals(NCLAttributes.BOTTOM) ||
                   name.equals(NCLAttributes.RIGHT) || name.equals(NCLAttributes.WIDTH) ||
                   name.equals(NCLAttributes.HEIGHT) || name.equals(NCLAttributes.FONT_SIZE) ||
                   name.equals(NCLAttributes.TRANSPARENCY) || name.equals(NCLAttributes.SOUND_LEVEL) ||
                   name.equals(NCLAttributes.BALANCE_LEVEL) || name.equals(NCLAttributes.TREBLE_LEVEL) ||
                   name.equals(NCLAttributes.BASS_LEVEL);
        
        if(value instanceof Double)
            return name.equals(NCLAttributes.FONT_SIZE) || name.equals(NCLAttributes.TRANSPARENCY) ||
                   name.equals(NCLAttributes.SOUND_LEVEL) || name.equals(NCLAttributes.BALANCE_LEVEL) ||
                   name.equals(NCLAttributes.TREBLE_LEVEL) || name.equals(NCLAttributes.BASS_LEVEL) ||
                   name.equals(NCLAttributes.TOP) || name.equals(NCLAttributes.LEFT) ||
                   name.equals(NCLAttributes.BOTTOM) || name.equals(NCLAttributes.RIGHT) ||
                   name.equals(NCLAttributes.WIDTH) || name.equals(NCLAttributes.HEIGHT);
        
        if(value instanceof NCLColor)
            return name.equals(NCLAttributes.FONT_COLOR) || name.equals(NCLAttributes.BACKGROUND);
        
        if(value instanceof NCLFit)
            return name.equals(NCLAttributes.FIT);
        
        if(value instanceof NCLFontVariant)
            return name.equals(NCLAttributes.FONT_VARIANT);
        
        if(value instanceof NCLFontWeight)
            return name.equals(NCLAttributes.FONT_WEIGHT);
        
        if(value instanceof NCLPlayerLife)
            return name.equals(NCLAttributes.PLAYER_LIFE);
        
        if(value instanceof NCLScroll)
            return name.equals(NCLAttributes.SCROLL);
        
        if(value.equals("transparent"))
            return name.equals(NCLAttributes.BACKGROUND);
        
        if(value instanceof String)
            return name.equals(NCLAttributes.STYLE) || name.equals(NCLAttributes.FONT_FAMILY);
        
        return false;
    }
    
    
    protected Object convertValue(String value) throws XMLException {
        if(value != null && "".equals(value.trim()))
            throw new XMLException("Empty value String");
        
        Object aux;
        
        if(value.equals("true"))
            return true;
        
        if(value.equals("false"))
            return false;
        
        if(value.equals("transparent"))
            return value;
        
        if(value.contains(",")){
            if(value.contains("%")){
                ArrayList<Double> array = new ArrayList<Double>();
                int i,e;
                
                e = value.indexOf(",");
                while(e >= 0){
                    array.add(new Double(value.substring(0, e-1)));
                    value = value.substring(e+1, value.length());
                    e = value.indexOf(",");
                }
                array.add(new Double(value.substring(0, value.length()-1)));
                return array.toArray(new Double[1]);
            }
            else{
                ArrayList<Integer> array = new ArrayList<Integer>();
                int e;
                
                e = value.indexOf(",");
                do{
                    array.add(new Integer(value.substring(0, e)));
                    value = value.substring(e+1, value.length());
                    e = value.indexOf(",");
                }while(e >= 0);
                array.add(new Integer(value));
                return array.toArray(new Integer[1]);
            }
        }
        
        int index = value.indexOf("%");
        if(index > 0){
            try{
                aux = new Double(value.substring(0, index));
                percentSign = true;
                return aux;
            }catch(Exception e){
                return null;
            }
        }
        
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
    public void clean() throws XMLException {
        setParent(null);
        
        name = null;
        value = null;
    }
}
