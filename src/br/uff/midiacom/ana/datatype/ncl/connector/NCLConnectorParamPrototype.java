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

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents a connector parameter element. This element is used
 * to define a parameterized value to be used in a connector.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the parameter. This attribute is required.</li>
 *  <li><i>type</i> - type of the parameter. This attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public abstract class NCLConnectorParamPrototype<T extends NCLConnectorParamPrototype,
                                                 P extends NCLElement,
                                                 I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement<ReferenceType> {
    
    protected StringType type;
    
    protected ItemList<ReferenceType> references;
    
    
    /**
     * Parameter element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLConnectorParamPrototype() throws XMLException {
        super();
        references = new ItemList<ReferenceType>();
    }
    
    
    @Deprecated
    @Override
    public void setId(String id) throws XMLException {
        super.setId(id);
    }
    
    
    @Deprecated
    @Override
    public String getId() {
        return super.getId();
    }
    
    
    /**
     * Sets the name of the parameter. This attribute is required and can not be
     * set to <i>null</i>.
     * 
     * <br/>
     * 
     * The name of the parameter must be unique inside the connector.
     * 
     * @param name
     *          string representing the name of the parameter.
     * @throws XMLException 
     *          if the name is null or empty.
     */
    public void setName(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        String aux = this.getName();
        super.setId(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }
    
    
    /**
     * Returns the name of the parameter or <i>null</i> if the attribute is not
     * defined.
     * 
     * <br/>
     * 
     * The name of the parameter must be unique inside the connector.
     * 
     * @return
     *          string representing the name of the parameter or <i>null</i> if
     *          the attribute is not defined.
     */
    public String getName() {
        return super.getId();
    }
    
    
    /**
     * Sets the type of the parameter. This attribute is optional. Set the type
     * to <i>null</i> to erase a type already defined.
     * 
     * @param type
     *          string representing the type of the parameter or <i>null</i> to
     *          erase a type already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setType(String type) throws XMLException {
        StringType aux = this.type;
        this.type = new StringType(type);
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }


    /**
     * Returns the type of the parameter or <i>null</i> if the attribute is not
     * defined.
     * 
     * @return
     *          string representing the type of the parameter or <i>null</i> if
     *          the attribute is not defined.
     */
    public String getType() {
        if(type == null)
            return null;
        else
            return type.getValue();
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
