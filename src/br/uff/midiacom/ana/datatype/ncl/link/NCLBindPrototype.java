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
package br.uff.midiacom.ana.datatype.ncl.link;

import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.InterfaceReference;
import br.uff.midiacom.ana.datatype.aux.reference.NodeReference;
import br.uff.midiacom.ana.datatype.aux.reference.RoleReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLRolePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


/**
 * Class that represents a bind element. A bind associates a role defined in a
 * connector to a node.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>role</i> - role defined in a connector. This attribute is required.</li>
 *  <li><i>component</i> - node to be associated to the role. This attribute is
 *                         required.</li>
 *  <li><i>interface</i> - node interface point to be associated to the role. This
 *                         attribute is optional.</li>
 *  <li><i>descriptor</i> - descriptor to be used by the node. This attribute is
 *                          optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>bindParam</i> - element that defines a value to a parameter defined
 *                         in the connector. The bind can have none or several
 *                         parameter elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Er>
 * @param <En>
 * @param <Ei>
 * @param <Ed>
 * @param <Ep> 
 */
public abstract class NCLBindPrototype<T extends NCLBindPrototype,
                                       P extends NCLElement,
                                       I extends NCLElementImpl,
                                       Er extends RoleReference,
                                       En extends NodeReference,
                                       Ei extends InterfaceReference,
                                       Ed extends DescriptorReference,
                                       Ep extends NCLParamPrototype>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P>{

    protected Er role;
    protected En component;
    protected Ei interfac;
    protected Ed descriptor;
    protected ElementList<Ep, T> bindParams;
    

    /**
     * Bind element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLBindPrototype() throws XMLException {
        super();
        bindParams = new ElementList<Ep, T>();
    }


    /**
     * Sets the role defined in a connector used by the bind. This attribute is
     * required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The role referred must be a role defined by the connector used by the
     * link parent of the bind element.
     * 
     * @param role
     *          element representing a reference to a role element.
     * @throws XMLException 
     *          if the role is null or any error occur while creating the
     *          reference to the role.
     */
    public void setRole(Er role) throws XMLException {
        if(role == null)
            throw new XMLException("Null role.");
        
        Er aux = this.role;
        
        this.role = role;
        this.role.setOwner((T) this);
        this.role.setOwnerAtt(NCLElementAttributes.ROLE);
        
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
        if(aux != null)
            aux.clean();
    }
    
    
    /**
     * Returns the role defined in a connector used by the bind or <i>null</i>
     * if the attribute is not defined.
     * 
     * <br/>
     * 
     * The role referred must be a role defined by the connector used by the
     * link parent of the bind element.
     * 
     * @return 
     *          element representing a reference to a role element or <i>null</i>
     *          if the attribute is not defined.
     */
    public Er getRole() {
        return role;
    }
    
    
    /**
     * Sets the node to be associated to the role. This attribute is required
     * and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The node referred must be a node defined in the composition parent of the
     * link parent of the bind element.
     * 
     * @param component
     *          element representing a reference to a node element.
     * @throws XMLException 
     *          if the node is null or any error occur while creating the
     *          reference to the node.
     */
    public void setComponent(En component) throws XMLException {
        if(component == null)
            throw new XMLException("Null component.");
        
        En aux = this.component;
        
        this.component = component;
        this.component.setOwner((T) this);
        this.component.setOwnerAtt(NCLElementAttributes.COMPONENT);
        
        impl.notifyAltered(NCLElementAttributes.COMPONENT, aux, component);
        if(aux != null)
            aux.clean();
    }
    
    
    /**
     * Returns the node to be associated to the role or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The node referred must be a node defined in the composition parent of the
     * link parent of the bind element.
     * 
     * @return 
     *          element representing a reference to a node element or <i>null</i>
     *          if the attribute is not defined.
     */
    public En getComponent() {
        return component;
    }
    
    
    /**
     * Sets the node interface point to be associated to the role. This attribute
     * is optional. Set the interface to <i>null</i> to erase a interface
     * already defined.
     * 
     * <br/>
     * 
     * The interface referred must be a interface point of the node referred by
     * the component attribute.
     * 
     * @see #setComponent(br.uff.midiacom.ana.datatype.aux.reference.NodeReference) 
     * 
     * @param interfac
     *          element representing a reference to a interface element or
     *          <i>null</i> to erase a interface already defined.
     * @throws XMLException 
     */
    public void setInterface(Ei interfac) throws XMLException {
        Ei aux = this.interfac;
        
        this.interfac = interfac;
        if(this.interfac != null){
            this.interfac.setOwner((T) this);
            this.interfac.setOwnerAtt(NCLElementAttributes.INTERFACE);
        }
        
        impl.notifyAltered(NCLElementAttributes.INTERFACE, aux, interfac);
        if(aux != null)
            aux.clean();
    }
    
    
    /**
     * Returns the node interface point to be associated to the role or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The interface referred must be a interface point of the node referred by
     * the component attribute.
     * 
     * @see #setComponent(br.uff.midiacom.ana.datatype.aux.reference.NodeReference) 
     * 
     * @return 
     *          element representing a reference to a interface element or
     *          <i>null</i> if the attribute is not defined.
     */
    public Ei getInterface() {
        return interfac;
    }
    
    
    /**
     * Sets the descriptor that describes the node presentation. This attribute
     * is optional. Set the descriptor to <i>null</i> to erase a descriptor 
     * already defined.
     * 
     * <br/>
     * 
     * The descriptor referred can be defined in the document base of descriptors
     * or in a base defined in an external document, imported by the base of
     * descriptors or by the base of imported documents. When the descriptor is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @param descriptor
     *          element representing a reference to a descriptor element or
     *          <i>null</i> to erase a descriptor already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setDescriptor(Ed descriptor) throws XMLException {
        Ed aux = this.descriptor;
        
        this.descriptor = descriptor;
        if(this.descriptor != null){
            this.descriptor.setOwner((T) this);
            this.descriptor.setOwnerAtt(NCLElementAttributes.DESCRIPTOR);
        }
        
        impl.notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, descriptor);
        if(aux != null)
            aux.clean();
    }
    
    
    /**
     * Returns the descriptor that describes the node presentation or <i>null</i>
     * is the attribute is not defined.
     * 
     * <br/>
     * 
     * The descriptor referred can be defined in the document base of descriptors
     * or in a base defined in an external document, imported by the base of
     * descriptors or by the base of imported documents. When the descriptor is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @return 
     *          element representing a reference to a descriptor element or
     *          <i>null</i> if the attribute is not defined.
     */
    public Ed getDescriptor() {
        return descriptor;
    }
    
    
    /**
     * Adds a bind parameter to the bind. A bind parameter defines a value to a
     * parameter defined in the connector. The bind can have none or several
     * parameter elements.
     * 
     * @param param
     *          element representing a bind parameter.
     * @return
     *          true if the parameter was added.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean addBindParam(Ep param) throws XMLException {
        if(bindParams.add(param, (T) this)){
            impl.notifyInserted(NCLElementSets.BINDPARAMS, param);
            return true;
        }
        return false;
    }


    /**
     * Removes a bind parameter of the bind. A bind parameter defines a value to
     * a parameter defined in the connector. The bind can have none or several
     * parameter elements.
     * 
     * @param param
     *          element representing a bind parameter.
     * @return
     *          true if the parameter was removed.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean removeBindParam(Ep param) throws XMLException {
        if(bindParams.remove(param)){
            impl.notifyRemoved(NCLElementSets.BINDPARAMS, param);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the bind has a specific element representing a bind parameter.
     * A bind parameter defines a value to a parameter defined in the connector.
     * The bind can have none or several parameter elements.
     * 
     * @param param
     *          element representing a bind parameter.
     * @return
     *          true if the bind has the parameter element.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean hasBindParam(Ep param) throws XMLException {
        return bindParams.contains(param);
    }


    /**
     * Verifies if the bind has at least one bind parameter. A bind parameter
     * defines a value to a parameter defined in the connector. The bind can have
     * none or several parameter elements.
     * 
     * @return 
     *          true if the bind has at least one parameter.
     */
    public boolean hasBindParam() {
        return !bindParams.isEmpty();
    }


    /**
     * Returns the list of bind parameters that a bind have. A bind parameter
     * defines a value to a parameter defined in the connector. The bind can have
     * none or several parameter elements.
     * 
     * @return 
     *          element list with all parameters.
     */
    public ElementList<Ep, T> getBindParams() {
        return bindParams;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        boolean comp = true;

        String this_bind, other_bind;

        // Compara pelo role
        if(getRole() == null) this_bind = ""; else this_bind = ((NCLRolePrototype) getRole().getTarget()).getName();
        if(other.getRole() == null) other_bind = ""; else other_bind = ((NCLRolePrototype) other.getRole().getTarget()).getName();
        comp &= this_bind.equals(other_bind);

        // Compara pelo componente
        if(getComponent() != null && other.getComponent() != null)
            comp &= getComponent().compare(other.getComponent());

        // Compara pela interface
        if(getInterface() != null && other.getInterface() != null)
            comp &= getInterface().compare(other.getInterface());

        // Compara pelo descritor
        if(getDescriptor() != null && other.getDescriptor() != null)
            comp &= getDescriptor().compare(other.getDescriptor());

        // Compara o número de parâmetros
        comp &= bindParams.size() == other.getBindParams().size();

        // Compara os parâmetros
        Iterator it = other.getBindParams().iterator();
        for(Ep param : bindParams){
            if(!it.hasNext())
                continue;
            Ep other_param = (Ep) it.next();
            comp = param.compare(other_param);
            if(comp)
                break;
        }


        return comp;
    }
}
