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

import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.aux.reference.InterfaceReference;
import br.uff.midiacom.ana.datatype.aux.reference.NodeReference;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import org.w3c.dom.Element;


/**
 * Class that represents a port element. A port maps a context inner node or
 * inner node interface point making it possible to be reached from outside the
 * context.
 * 
 * <br/>
 * 
 * When a node or a node interface point is mapped by a port, every action over
 * the port is reflected to the node or node interface point mapped by it. If the
 * port interface attribute is not defined, it is assumed that the port maps
 * the whole node.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the port element. This attribute is required.</li>
 *  <li><i>component</i> - node mapped by the port. This attribute is required.</li>
 *  <li><i>interface</i> - node interface point mapped by the port. This attribute
 *                         is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <It>
 * @param <En>
 * @param <Ei> 
 */
public class NCLPort<T extends NCLPort,
                     P extends NCLElement,
                     I extends NCLElementImpl,
                     It extends NCLInterface,
                     En extends NodeReference,
                     Ei extends InterfaceReference>
        extends NCLIdentifiableElementPrototype<It, P, I>
        implements NCLInterface<It, P> {

    protected En component;
    protected Ei interfac;
    
    protected ItemList<ReferenceType> references;


    /**
     * Port element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLPort() throws XMLException{
        super();
        references = new ItemList<ReferenceType>();
    }
    
    
    public NCLPort(String id) throws XMLException{
        super();
        references = new ItemList<ReferenceType>();
        setId(id);
    }


    /**
     * Sets the node mapped by the port. This attribute is required and can not
     * be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The node referred must be a node defined in the composition parent of the
     * port element.
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
     * Returns the node mapped by the port or <i>null</i> if the attribute is
     * not defined.
     * 
     * <br/>
     * 
     * The node referred must be a node defined in the composition parent of the
     * port element.
     * 
     * @return 
     *          element representing a reference to a node element or <i>null</i>
     *          if the attribute is not defined.
     */
    public En getComponent() {
        return component;
    }
    
    
    /**
     * Sets the node interface point mapped by the port. This attribute
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
     * Returns the node interface point mapped by the port or <i>null</i> if the
     * attribute is not defined.
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
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <port> element and attributes declaration
        content = space + "<port";
        content += parseAttributes();
        content += "/>\n";
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);

            loadComponent(element);

            loadInterface(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Port" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseComponent();
        content += parseInterface();
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseComponent() {
        En aux = getComponent();
        if(aux != null)
            return " component='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadComponent(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the component (required)
        att_name = NCLElementAttributes.COMPONENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            P aux;
            if((aux = (P) getParent()) == null)
                throw new NCLParsingException("Could not find element " + att_var);

            NCLNode refEl;
            if(aux instanceof NCLBody)
                refEl = (NCLNode) ((NCLBody) aux).findNode(att_var);
            else
                refEl = (NCLNode) ((NCLNode) aux).findNode(att_var);
            if(refEl == null)
                throw new NCLParsingException("Could not find element " + att_var);

            setComponent(createNodeRef(refEl));
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseInterface() {
        Ei aux = getInterface();
        if(aux != null)
            return " interface='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadInterface(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the interface (optional)
        att_name = NCLElementAttributes.INTERFACE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLInterface refEl = (NCLInterface) ((NCLNode) getComponent().getTarget()).findInterface(att_var);
            if(refEl == null)
                throw new NCLParsingException("Could not find element " + att_var);

            setInterface(createInterfaceRef(refEl));
        }
    }


    /**
     * Function to create a reference to a node.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a node.
     */
    protected En createNodeRef(NCLNode ref) throws XMLException {
        return (En) new NodeReference(ref, NCLElementAttributes.ID);
    }


    /**
     * Function to create a reference to a interface.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a interface.
     */
    protected Ei createInterfaceRef(NCLInterface ref) throws XMLException {
        return (Ei) new InterfaceReference(ref, NCLElementAttributes.ID);
    }
}
