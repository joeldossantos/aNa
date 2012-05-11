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
import br.uff.midiacom.ana.connector.*;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.InterfaceReference;
import br.uff.midiacom.ana.datatype.aux.reference.NodeReference;
import br.uff.midiacom.ana.datatype.aux.reference.RoleReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.datatype.ncl.link.NCLBindPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLBind<T extends NCLBind,
                     P extends NCLElement,
                     I extends NCLElementImpl,
                     Er extends RoleReference,
                     En extends NodeReference,
                     Ei extends InterfaceReference,
                     Ed extends DescriptorReference,
                     Ep extends NCLParam>
        extends NCLBindPrototype<T, P, I, Er, En, Ei, Ed, Ep>
        implements NCLElement<T, P>{


    public NCLBind() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }
    
    
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
                aux = role.parse();
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
                aux = role.parse();
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
            return " role='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRole(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            P aux;
            if((aux = (P) getParent()) == null)
                throw new NCLParsingException("Could not find element " + att_var);

            NCLCausalConnector conn = (NCLCausalConnector) ((NCLLink) aux).getXconnector().getTarget();
            if(conn == null)
                throw new NCLParsingException("Could not find element " + att_var);

            NCLRole rol = (NCLRole) conn.findRole(att_var);
            if(rol == null)
                throw new NCLParsingException("Could not find element " + att_var);
            setRole(createRoleRef(rol));
        }
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
            if((aux = (P) getParent()) == null || (aux = (P) aux.getParent()) == null)
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
    
    
    protected String parseDescriptor() {
        Ed aux = getDescriptor();
        if(aux != null)
            return " descriptor='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadDescriptor(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the descriptor (optional)
        att_name = NCLElementAttributes.DESCRIPTOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setDescriptor((Ed) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), att_var));
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


    /**
     * Function to create the child element <i>bindParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindParam</i>.
     */
    protected Ep createBindParam() throws XMLException {
        return (Ep) new NCLParam(NCLParamInstance.BINDPARAM);
    }


    /**
     * Function to create a reference to element <i>role</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to element <i>role</i>.
     */
    protected Er createRoleRef(NCLRole ref) throws XMLException {
        return (Er) new RoleReference(ref, NCLElementAttributes.NAME);
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


    /**
     * Function to create a reference to a descriptor.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a descriptor.
     */
    protected Ed createDescriptorRef(NCLLayoutDescriptor ref) throws XMLException {
        return (Ed) new DescriptorReference(ref, NCLElementAttributes.ID);
    }
}
