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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.aux.parameterized.IntegerParamType;
import br.uff.midiacom.ana.datatype.aux.parameterized.KeyParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLAttributeType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;


/**
 * Class that represents a attribute assessment element. This element  represents
 * the attribute whose value will be compared.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>role</i> - connector interface point, used to identify this attribute
 *                    assessment. This attribute is required.</li>
 *  <li><i>eventType</i> - type of the event used in the attribute assessment.
 *                         This attribute is required.</li>
 *  <li><i>key</i> - key pressed by the user. This attribute is optional.</li>
 *  <li><i>attributeType</i> - type of the attribute. This attribute is optional.</li>
 *  <li><i>offset</i> - value added to the attribute value. This attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Er>
 * @param <Ep>
 * @param <R> 
 */
public abstract class NCLAttributeAssessmentPrototype<T extends NCLAttributeAssessmentPrototype,
                                                      P extends NCLElement,
                                                      I extends NCLElementImpl,
                                                      Er extends NCLRolePrototype,
                                                      Ep extends NCLConnectorParamPrototype,
                                                      R extends ConParamReference>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected Er role;
    protected NCLEventType eventType;
    protected KeyParamType<Ep, T, R> key;
    protected NCLAttributeType attributeType;
    protected IntegerParamType<Ep, T, R> offset;
    

    /**
     * Attribute assessment element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLAttributeAssessmentPrototype() throws XMLException {
        super();
    }


    /**
     * Sets the connector interface point, used to identify this attribute
     * assessment. This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * @param role
     *          element representing the role name.
     * @throws XMLException
     *          if the role is null.
     */
    public void setRole(Er role) throws XMLException {
        if(role == null)
            throw new XMLException("Null role.");
        
        //Removes the parent of the actual role
        if(this.role != null)
            this.role.setParent(null);

        Er aux = this.role;
        this.role = role;
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
        
        //Set this as the parent of the new role
        this.role.setParent(this);
    }
    
    
    /**
     * Returns the connector interface point, used to identify this attribute
     * assessment or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The role must be unique inside the connector.
     * 
     * @return
     *          element representing the role name or <i>null</i> if the
     *          attribute is not defined.
     */
    public Er getRole() {
        return role;
    }
    
    
    /**
     * Sets the type of the event used in the attribute assessment. This attribute
     * is required and can not be set to <i>null</i>. The possible event type
     * values are defined in the enumeration <i>NCLEventType</i>.
     * 
     * @param eventType
     *          value representing the type of event from the enumeration
     *          <i>NCLEventType</i>.
     * @throws XMLException 
     *          if the type is null.
     */
    public void setEventType(NCLEventType eventType) throws XMLException {
        if(eventType == null)
            throw new XMLException("Null event type.");
        
        NCLEventType aux = this.eventType;
        this.eventType = eventType;
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, eventType);
    }
    
    
    /**
     * Returns the type of the event used in the attribute assessment or
     * <i>null</i> if the attribute is not defined. The possible event type
     * values are defined in the enumeration <i>NCLEventType</i>.
     * 
     * @return
     *          value representing the type of event from the enumeration
     *          <i>NCLEventType</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLEventType getEventType() {
        return eventType;
    }
    
    
    /**
     * Sets the key pressed by the user, in case a selection event is used. This
     * attribute is optional. Set the key to <i>null</i> to erase a key already
     * defined.
     * 
     * <br/>
     * 
     * The key can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this attribute assessment is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this attribute assessment it.
     * 
     * @param key
     *          element representing the key pressed or <i>null</i> to erase a
     *          key already defined.
     * @throws XMLException
     *          if an error occur while creating the key value.
     */
    public void setKey(KeyParamType<Ep, T, R> key) throws XMLException {
        KeyParamType aux = this.key;
        
        this.key = key;
        if(this.key != null)
            this.key.setOwner((T) this, NCLElementAttributes.KEY);
        
        impl.notifyAltered(NCLElementAttributes.KEY, aux, key);
        if(aux != null)
            aux.removeOwner();
    }
    
    
    /**
     * Returns the key pressed by the user, in case a selection event is used or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The key can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this attribute assessment is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this attribute assessment it.
     * 
     * @return
     *          element representing the key pressed or <i>null</i> if the
     *          attribute is not defined.
     */
    public KeyParamType<Ep, T, R> getKey() {
        return key;
    }
    
    
    /**
     * Sets the type of the attribute. This attribute is optional. Set it to
     * <i>null</i> to erase an attribute already defined. The possible attribute
     * type values are defined in the enumeration <i>NCLAttributeType</i>.
     * 
     * @param attributeType
     *          value representing the type of attribute from the enumeration
     *          <i>NCLAttributeType</i> or <i>null</i> to erase an attribute
     *          already defined.
     */
    public void setAttributeType(NCLAttributeType attributeType) {
        NCLAttributeType aux = this.attributeType;
        this.attributeType = attributeType;
        impl.notifyAltered(NCLElementAttributes.ATTRIBUTETYPE, aux, attributeType);
    }
    
    
    /**
     * Returns the type of the attribute or <i>null</i> if it is not defined.
     * The possible attribute type values are defined in the enumeration
     * <i>NCLAttributeType</i>.
     * 
     * @return
     *          value representing the type of attribute from the enumeration
     *          <i>NCLAttributeType</i> or <i>null</i> if it is not defined.
     */
    public NCLAttributeType getAttributeType() {
        return attributeType;
    }
    
    
    /**
     * Sets the value added to the attribute value. This attribute is optional.
     * Set the offset to <i>null</i> to erase an offset already defined.
     * 
     * <br/>
     * 
     * The offset can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this attribute assessment is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this attribute assessment it.
     * 
     * @param offset
     *          element representing the offset or <i>null</i> to erase an
     *          offset already defined.
     * @throws XMLException
     *          if an error occur while creating the offset value.
     */
    public void setOffset(IntegerParamType<Ep, T, R> offset) throws XMLException {
        IntegerParamType aux = this.offset;
        
        this.offset = offset;
        if(this.offset != null)
            this.offset.setOwner((T) this, NCLElementAttributes.OFFSET);
        
        impl.notifyAltered(NCLElementAttributes.OFFSET, aux, offset);
        if(aux != null)
            aux.removeOwner();
    }
    
    
    /**
     * Returns the value added to the attribute value or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The offset can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this attribute assessment is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this attribute assessment it.
     * 
     * @return
     *          element representing the offset or <i>null</i> if the attribute
     *          is not defined.
     */
    public IntegerParamType<Ep, T, R> getOffset() {
        return offset;
    }

    
    public boolean compare(T other) {
        boolean comp = true;

        String this_att, other_att;

        // Compara pelo role
        if(getRole() == null) this_att = ""; else this_att = getRole().getName();
        if(other.getRole() == null) other_att = ""; else other_att = other.getRole().getName();
        comp &= this_att.equals(other_att);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_att = ""; else this_att = getEventType().toString();
        if(other.getEventType() == null) other_att = ""; else other_att = other.getEventType().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo tipo do atributo
        if(getAttributeType() == null) this_att = ""; else this_att = getAttributeType().toString();
        if(other.getAttributeType() == null) other_att = ""; else other_att = other.getAttributeType().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo offset
        if(getOffset() == null) this_att = ""; else this_att = getOffset().parse();
        if(other.getOffset() == null) other_att = ""; else other_att = other.getOffset().parse();
        comp &= this_att.equals(other_att);

        // Compara pela tecla
        if(getKey() == null) this_att = ""; else this_att = getKey().parse();
        if(other.getKey() == null) other_att = ""; else other_att = other.getKey().parse();
        comp &= this_att.equals(other_att);


        return comp;
    }
}
