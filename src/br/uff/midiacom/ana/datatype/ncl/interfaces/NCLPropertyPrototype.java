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
package br.uff.midiacom.ana.datatype.ncl.interfaces;

import br.uff.midiacom.ana.datatype.aux.reference.VariableReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLAttribute;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.string.StringType;


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
public abstract class NCLPropertyPrototype<T extends NCLPropertyPrototype,
                                           P extends NCLElement,
                                           I extends NCLElementImpl,
                                           Ei extends NCLInterface,
                                           Ep extends VariableReference,
                                           Ea extends NCLAttribute>
        extends NCLElementPrototype<Ei, P, I>
        implements NCLInterface<Ei, P> {

    protected Ea attName;
    protected Ep varName;
    protected StringType value;
    
    protected ItemList<ReferenceType> references;
    
    
    /**
     * Property element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLPropertyPrototype() throws XMLException {
        super();
        references = new ItemList<ReferenceType>();
    }
    
    
    public String getId() {
        return getName();
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
     *          element representing the name of the property element as a node
     *          attribute.
     * @throws XMLException 
     *          if the name is null.
     */
    public void setName(Ea name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        Ea aux = this.attName;
        this.attName = name;
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
        
        //Erase the name as a variable
        varName.clean();
        varName = null;
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
     *          element that makes reference a variable defined in the document.
     * @throws XMLException 
     *          if the variable is null or any error occur while creating the
     *          reference to the variable.
     */
    public void setName(Ep name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name");
        
        Ep aux = this.varName;
        
        this.varName = name;
        this.varName.setOwner((T) this);
        this.varName.setOwnerAtt(NCLElementAttributes.NAME);
        
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
        if(aux != null)
            aux.clean();
        
        //Erase the name as a attribute
        attName = null;
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
     *          string representing the name of property element or <i>null</i>
     *          if the attribute is not defined.
     */
    public String getName() {
        if(varName != null)
            return varName.parse();
        if(attName != null)
            return attName.toString();
        else
            return null;
    }
    
    
    /**
     * Return the name of the property element as a node attribute or group of
     * attributes.
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
     *          element representing the name of the property element as a node
     *          attribute or <i>null</i> if the property name is not a node
     *          attribute or group of attributes or is not defined.
     */
    public Ea getAttributeName() {
        return attName;
    }
    
    
    /**
     * Return the name of the property element as a global variable.
     * 
     * <br/>
     * 
     * A property element may represent an attribute or group of attributes of
     * a node element. The possible values of the node attribute or group of
     * attributes are defined in the enumeration <i>NCLNodeAttributes</i>.
     * 
     * <br/>
     * 
     * If the property element is a child of a media element with type
     * <i>settings</i>, than it indicates the use of a global variable in the
     * list defined by the document.
     * 
     * @return
     *          element that makes reference a variable defined in the document
     *          or <i>null</i> if the property name is not a global variable or
     *          is not defined.
     */
    public Ep getVariableName() {
        return varName;
    }
    
    
    /**
     * Sets the initial value of the property. This attribute is optional. Set
     * the value to <i>null</i> to erase a value already defined.
     * 
     * @param value
     *          string representing the initial value of the property element.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        
        if(value != null)
            this.value = new StringType(value);
        else
            this.value= null;
        
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
    /**
     * Return the initial value of the property or <i>null</i> if the attribute
     * is not defined.
     * 
     * @return
     *          string representing the initial value of the property or
     *          <i>null</i> if the attribute is not defined.
     */
    public String getValue() {
        if(value != null)
            return value.getValue();
        else
            return null;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
