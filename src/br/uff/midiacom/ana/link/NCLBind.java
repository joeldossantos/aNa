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

import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.connector.*;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ncl.NCLNamedElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


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
public class NCLBind<T extends NCLElement,
                     Er extends NCLRoleElement,
                     En extends NCLNode,
                     Ei extends NCLInterface,
                     El extends NCLLayoutDescriptor,
                     Ep extends NCLBindParam,
                     Epr extends NCLParam,
                     R extends ExternalReferenceType>
        extends NCLElementPrototype<T>
        implements NCLElement<T>, ReferredElement<Epr>, PostReferenceElement {

    protected Er role;
    protected En component;
    protected Ei interfac;
    protected Object descriptor;
    protected ElementList<Ep> bindParams;
    
    protected ArrayList<Epr> references;
    

    /**
     * Bind element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLBind() throws XMLException {
        super();
        bindParams = new ElementList<Ep>();
        references = new ArrayList<Epr>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ep aux : bindParams) {
            aux.setDoc(doc);
        }
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
        if(role instanceof GetSetRole)
            ((GetSetRole) this.role).setBind(this);
        else
            this.role.addReference(this);
        
        notifyAltered(NCLElementAttributes.ROLE, aux, role);
        if(aux != null)
            aux.removeReference(this);
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
        this.component.addReference(this);
        
        notifyAltered(NCLElementAttributes.COMPONENT, aux, component);
        if(aux != null)
            aux.removeReference(this);
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
            this.interfac.addReference(this);
        }
        
        notifyAltered(NCLElementAttributes.INTERFACE, aux, interfac);
        if(aux != null)
            aux.removeReference(this);
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
     *          element representing a descriptor or a reference to a descriptor
     *          or <i>null</i> to erase a descriptor already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setDescriptor(Object descriptor) throws XMLException {
        Object aux = this.descriptor;
        
        if(descriptor instanceof NCLLayoutDescriptor){
            this.descriptor = descriptor;
            ((El) descriptor).addReference(this);
            
        }
        else if(descriptor instanceof ExternalReferenceType){
            this.descriptor = descriptor;
            ((R) descriptor).getTarget().addReference(this);
            ((R) descriptor).getAlias().addReference(this);
        }
        
        this.descriptor = descriptor;
        notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, descriptor);
        
        if(aux != null){
            if(aux instanceof NCLLayoutDescriptor)
                ((El) descriptor).removeReference(this);
            else{
                ((R) descriptor).getTarget().removeReference(this);
                ((R) descriptor).getAlias().removeReference(this);
            }
        }
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
     *          element representing a descriptor or a reference to a descriptor
     *          or <i>null</i> if the attribute is not defined.
     */
    public Object getDescriptor() {
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
        if(bindParams.add(param)){
            notifyInserted((T) param);
            param.setParent(this);
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
            notifyRemoved((T) param);
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
    public ElementList<Ep> getBindParams() {
        return bindParams;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLBind))
            return false;
        
        boolean result = true;

        Object aux;

        if((aux = getRole()) != null)
            result &= aux.equals(((NCLBind) other).getRole());

        T el;
        if((el = (T) getComponent()) != null)
            result &= el.compare(((NCLBind) other).getComponent());
        if((el = (T) getInterface()) != null)
            result &= el.compare(((NCLBind) other).getInterface());

        aux = getDescriptor();
        Object oaux = ((NCLBind) other).getDescriptor();
        if(aux != null && oaux != null){
            if(aux instanceof NCLLayoutDescriptor && oaux instanceof NCLLayoutDescriptor)
                result &= ((El) aux).compare((El) oaux);
            else
                result &= ((R) aux).equals((R) oaux);
        }

        ElementList<Ep> otherpar = ((NCLBind) other).getBindParams();
        result &= bindParams.size() == otherpar.size();
        for (Ep par : bindParams) {
            try {
                result &= otherpar.contains(par);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }

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
        
        
        // <bind> element and attributes declaration
        content = space + "<bind";
        content += parseAttributes();
        
        // <bind> element content
        if(hasBindParam()){
            content += ">\n";

            content += parseElements(ident + 1);
            
            content += space + "</bind>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadRole(element);
            loadComponent(element);
            loadInterface(element);
            loadDescriptor(element);
        }
        catch(XMLException ex){
            String aux = null;
            if(role != null)
                aux = role.getRole().toString();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Bind" + aux + ":\n" + ex.getMessage());
        }

        try{
            loadBindParams(element);
        }
        catch(XMLException ex){
            String aux = null;
            if(role != null)
                aux = role.getRole().toString();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Bind" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseRole();
        content += parseComponent();
        content += parseInterface();
        content += parseDescriptor();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseBindParams(ident);
        
        return content;
    }
    
    
    protected String parseRole() {
        Er aux = getRole();
        if(aux != null)
            return " role='" + aux.getRole().toString() + "'";
        else
            return "";
    }
    
    
    protected void loadRole(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T aux;
            if((aux = (T) getParent()) == null)
                throw new NCLParsingException("Could not find element " + att_var);

            Object conn = ((NCLLink) aux).getXconnector();
            if(conn instanceof ExternalReferenceType)
                conn = ((R) conn).getTarget();
            if(conn == null)
                throw new NCLParsingException("Could not find element " + att_var);

            Er rol = (Er) ((NCLCausalConnector) conn).findRole(att_var);
            if(rol == null){
                att_name = NCLElementAttributes.INTERFACE.toString();
                if(element.getAttribute(att_name).isEmpty())
                    throw new NCLParsingException("Could not find element " + att_var);
                else
                    rol = (Er) new GetSetRole(att_var);
            }
            setRole(rol);
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseComponent() {
        En aux = getComponent();
        if(aux != null)
            return " component='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected void loadComponent(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the component (required)
        att_name = NCLElementAttributes.COMPONENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T aux;
            if((aux = (T) getParent()) == null || (aux = (T) aux.getParent()) == null)
                throw new NCLParsingException("Could not find element " + att_var);

            En refEl;
            if(aux instanceof NCLBody)
                refEl = (En) ((NCLBody) aux).findNode(att_var);
            else
                refEl = (En) ((NCLNode) aux).findNode(att_var);
            if(refEl == null)
                throw new NCLParsingException("Could not find element " + att_var);

            setComponent(refEl);
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseInterface() {
        Ei aux = getInterface();
        if(aux != null){
            if(aux instanceof NCLIdentifiableElementPrototype)
                return " interface='" + ((NCLIdentifiableElementPrototype) aux).getId() + "'";
            else{
                Object name = ((NCLNamedElementPrototype) aux).getName();
                if(name instanceof NCLVariable)
                    return " interface='" + ((NCLVariable) name).parse(0) + "'";
                else
                    return " interface='" + name.toString() + "'";
            }
        }
        else
            return "";
    }
    
    
    protected void loadInterface(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the interface (optional)
        att_name = NCLElementAttributes.INTERFACE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            Ei refEl = (Ei) getComponent().findInterface(att_var);
            if(refEl == null){
                refEl = (Ei) new NCLArea(att_var);
                ((NCLDoc) getDoc()).waitReference(this);
            }
//            throw new NCLParsingException("Could not find element " + att_var);
            setInterface(refEl);
        }
    }
    
    
    protected String parseDescriptor() {
        Object aux = getDescriptor();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLLayoutDescriptor)
            return " descriptor='" + ((El) aux).getId() + "'";
        else
            return " descriptor='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadDescriptor(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the descriptor (optional)
        att_name = NCLElementAttributes.DESCRIPTOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] des = adjustReference(att_var);
            setDescriptor(d.getHead().findDescriptor(des[0], des[1]));
        }
    }
    
    
    protected String parseBindParams(int ident) {
        if(!hasBindParam())
            return "";
        
        String content = "";
        for(Ep aux : bindParams)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadBindParams(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the child nodes
        ch_name = NCLElementAttributes.BINDPARAM.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ep inst = createBindParam();
            addBindParam(inst);
            inst.load(el);
        }
    }

    
    @Override
    @Deprecated
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // fix the interface reference
            if(getInterface() != null && (aux = ((NCLArea) getInterface()).getId()) != null){
                Ei ref = (Ei) ((NCLBody) ((NCLDoc) getDoc()).getBody()).findInterface(aux);
                if(ref == null)
                    throw new NCLParsingException("Could not find bind interface.");
                else
                    setInterface(ref);
            }
        }
        catch(XMLException ex){
            aux = getRole().getRole().toString();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Bind" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(Epr reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(Epr reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList<Epr> getReferences() {
        return references;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        role.removeReference(this);
        component.removeReference(this);
        if(interfac != null)
            interfac.removeReference(this);
        
        if(descriptor != null){
            if(descriptor instanceof NCLLayoutDescriptor)
                ((El) descriptor).removeReference(this);
            else{
                ((R) descriptor).getTarget().removeReference(this);
                ((R) descriptor).getAlias().removeReference(this);
            }
        }
        
        role = null;
        component = null;
        interfac = null;
        descriptor = null;
        
        for (Ep b : bindParams)
            b.clean();
    }
    

    /**
     * Function to create the child element <i>bindParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindParam</i>.
     */
    protected Ep createBindParam() throws XMLException {
        return (Ep) new NCLBindParam();
    }
}
