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
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ncl.NCLNamedElementPrototype;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import java.util.ArrayList;
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
public class NCLPort<T extends NCLElement,
                     It extends NCLInterface,
                     En extends NCLNode,
                     Ei extends NCLInterface>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLInterface<T> {

    protected En component;
    protected Ei interfac;
    
    protected ArrayList<T> references;


    /**
     * Port element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLPort() {
        super();
        references = new ArrayList<T>();
    }
    
    
    public NCLPort(String id) throws XMLException {
        super();
        references = new ArrayList<T>();
        setId(id);
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
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
        this.component.addReference(this);
        
        notifyAltered(NCLElementAttributes.COMPONENT, aux, component);
        if(aux != null)
            aux.removeReference(this);
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
            this.interfac.addReference(this);
        }
        
        notifyAltered(NCLElementAttributes.INTERFACE, aux, interfac);
        if(aux != null)
            aux.removeReference(this);
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
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLPort))
            return false;
        
        boolean result = true;
        T el;
        String aux;
        
        if((aux = getId()) != null)
            result &= aux.equals(((NCLPort) other).getId());
        
        if((el = (T) getComponent()) != null)
            result &= el.compare(((NCLPort) other).getComponent());
        if((el = (T) getInterface()) != null)
            result &= el.compare(((NCLPort) other).getInterface());

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
        
        
        // <port> element and attributes declaration
        content = space + "<port";
        content += parseAttributes();
        content += "/>\n";
        
        return content;
    }


    @Override
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
            if((aux = (T) getParent()) == null)
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
                    return " interface='" + ((NCLVariable) aux).parse(0) + "'";
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
            if(refEl == null)
                throw new NCLParsingException("Could not find element " + att_var);

            setInterface(refEl);
        }
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
        
        component.removeReference(this);
        if(interfac != null)
            interfac.removeReference(this);
        
        component = null;
        interfac = null;
    }
}
