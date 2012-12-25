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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLAttributeType;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLEventType;
import br.uff.midiacom.ana.util.enums.NCLKey;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.util.ArrayList;
import org.w3c.dom.Element;


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
public class NCLAttributeAssessment<T extends NCLElement,
                                    Ep extends NCLConnectorParam,
                                    Eb extends NCLBind,
                                    Er extends NCLRoleElement>
        extends ParamElement<T>
        implements NCLElement<T>, NCLRoleElement<Eb> {

    protected String role;
    protected NCLEventType eventType;
    protected Object key;
    protected NCLAttributeType attributeType;
    protected Object offset;
    
    protected ArrayList<Eb> references;
    

    /**
     * Attribute assessment element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLAttributeAssessment() {
        super();
        references = new ArrayList<Eb>();
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
     *          string representing the role name.
     * @throws XMLException
     *          if the role is null or empty.
     */
    public void setRole(String role) throws XMLException {
        if(role == null)
            throw new XMLException("Null role.");
        if("".equals(role.trim()))
            throw new XMLException("Empty role String");
        
        String aux = this.role;
        this.role = role;
        notifyAltered(NCLElementAttributes.ROLE, aux, role);
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
     *          string representing the role name or <i>null</i> if the
     *          attribute is not defined.
     */
    @Override
    public String getRole() {
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
        notifyAltered(NCLElementAttributes.ROLE, aux, eventType);
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
     *          element from the enumeration <i>NCLKey</i>, string or connector
     *          parameter representing the key pressed or <i>null</i> to erase a
     *          key already defined.
     * @throws XMLException
     *          if an error occur while creating the key value.
     */
    public void setKey(Object key) throws XMLException {
        Object aux = this.key;
        
        if(key == null){
            this.key = key;
            notifyAltered(NCLElementAttributes.KEY, aux, key);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(key instanceof String){
            String value = (String) key;
            if("".equals(value.trim()))
                throw new XMLException("Empty key String");
            
            if(!value.contains("$"))
                this.key = NCLKey.getEnumType(value);
            else{
                this.key = findConnectorParam(value.substring(1));
                ((Ep) this.key).addReference(this);
            }
        }
        else if(key instanceof NCLKey)
            this.key = key;
        else if(key instanceof NCLConnectorParam){
            this.key = key;
            ((Ep) this.key).addReference(this);
        }
        else
            throw new XMLException("Wrong key type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.KEY, aux, key);
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
     *          element from the enumeration <i>NCLKey</i>, string or connector
     *          parameter representing the key pressed or <i>null</i> if the
     *          attribute is not defined.
     */
    public Object getKey() {
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
    public void setAttributeType(NCLAttributeType attributeType) throws XMLException {
        NCLAttributeType aux = this.attributeType;
        this.attributeType = attributeType;
        notifyAltered(NCLElementAttributes.ATTRIBUTETYPE, aux, attributeType);
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
     *          string, integer or connector parameter representing the offset
     *          or <i>null</i> to erase an offset already defined.
     * @throws XMLException
     *          if an error occur while creating the offset value.
     */
    public void setOffset(Object offset) throws XMLException {
        Object aux = this.offset;
        
        if(offset == null){
            this.offset = offset;
            notifyAltered(NCLElementAttributes.OFFSET, aux, offset);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(offset instanceof String){
            String value = (String) offset;
            if("".equals(value.trim()))
                throw new XMLException("Empty key String");
            
            if(!value.contains("$"))
                this.offset = new Integer(value);
            else{
                this.offset = findConnectorParam(value.substring(1));
                ((Ep) this.offset).addReference(this);
            }
        }
        else if(offset instanceof Integer)
            this.offset = offset;
        else if (offset instanceof NCLConnectorParam){
            this.offset = offset;
            ((Ep) this.offset).addReference(this);
        }
        else
            throw new XMLException("Wrong repeat type.");
        
        if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.OFFSET, aux, offset);
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
     *          string, integer or connector parameter representing the offset
     *          or <i>null</i> if the attribute is not defined.
     */
    public Object getOffset() {
        return offset;
    }

    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLAttributeAssessment))
            return false;
        
        boolean comp = true;

        String this_att, other_att;
        NCLAttributeAssessment otheratt = (NCLAttributeAssessment) other;

        // Compara pelo role
        if(getRole() == null) this_att = ""; else this_att = getRole().toString();
        if(otheratt.getRole() == null) other_att = ""; else other_att = otheratt.getRole().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo tipo do evento
        if(getEventType() == null) this_att = ""; else this_att = getEventType().toString();
        if(otheratt.getEventType() == null) other_att = ""; else other_att = otheratt.getEventType().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo tipo do atributo
        if(getAttributeType() == null) this_att = ""; else this_att = getAttributeType().toString();
        if(otheratt.getAttributeType() == null) other_att = ""; else other_att = otheratt.getAttributeType().toString();
        comp &= this_att.equals(other_att);

        // Compara pelo offset
        if(getOffset() == null) this_att = ""; else this_att = getOffset().toString();
        if(otheratt.getOffset() == null) other_att = ""; else other_att = otheratt.getOffset().toString();
        comp &= this_att.equals(other_att);

        // Compara pela tecla
        if(getKey() == null) this_att = ""; else this_att = getKey().toString();
        if(otheratt.getKey() == null) other_att = ""; else other_att = otheratt.getKey().toString();
        comp &= this_att.equals(other_att);


        return comp;
    }
    
    
    @Override
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


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadRole(element);
            loadEventType(element);
            loadKey(element);
            loadAttributeType(element);
            loadOffset(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("AttributeAssessment:\n" + ex.getMessage());
        }
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
        String aux = getRole();
        if(aux != null)
            return " role='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadRole(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRole(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseEventType() {
        NCLEventType aux = getEventType();
        if(aux != null)
            return " eventType='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadEventType(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the eventType (required)
        att_name = NCLElementAttributes.EVENTTYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setEventType(NCLEventType.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseKey() {
        Object aux = getKey();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " key='$" + ((Ep) aux).getName() + "'";
        else
            return " key='" + aux.toString() + "'";
    }
    
    
    protected void loadKey(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the key (optional)
        att_name = NCLElementAttributes.KEY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setKey(att_var);
    }
    
    
    protected String parseAttributeType() {
        NCLAttributeType aux = getAttributeType();
        if(aux != null)
            return " attributeType='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadAttributeType(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the attributeType (optional)
        att_name = NCLElementAttributes.ATTRIBUTETYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setAttributeType(NCLAttributeType.getEnumType(att_var));
    }
    
    
    protected String parseOffset() {
        Object aux = getOffset();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " offset='$" + ((Ep) aux).getName() + "'";
        else
            return " offset='" + aux.toString() + "'";
    }
    
    
    protected void loadOffset(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the offset (optional)
        att_name = NCLElementAttributes.OFFSET.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setOffset(att_var);
    }
    
    
    public Er findRole(String name) {
        if(role.toString().equals(name))
            return (Er) this;
        else
            return null;
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(Eb reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(Eb reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(key != null && key instanceof NCLConnectorParam)
            ((Ep)key).removeReference(this);
        
        if(offset != null && offset instanceof NCLConnectorParam)
            ((Ep)offset).removeReference(this);
        
        role = null;
        eventType = null;
        key = null;
        attributeType = null;
        offset = null;
    }
}
