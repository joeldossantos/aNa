/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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

import br.uff.midiacom.ana.connector.*;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.datatype.ncl.link.NCLBindPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLBind<T extends NCLBind, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRole, En extends NCLNode, Ei extends NCLInterface, Ed extends NCLLayoutDescriptor, Ep extends NCLParam>
        extends NCLBindPrototype<T, P, I, Er, En, Ei, Ed, Ep> implements NCLElement<T, P>{


    public NCLBind() throws XMLException {
        super();
    }

    public NCLBind(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setRole(Er role) {
        Er aux = this.role;
        super.setRole(role);
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
    }
    
    
    @Override
    public void setComponent(En component) {
        En aux = this.component;
        super.setComponent(component);
        impl.notifyAltered(NCLElementAttributes.COMPONENT, aux, component);
    }
    
    
    @Override
    public void setInterface(Ei interfac) {
        Ei aux = this.interfac;
        super.setInterface(interfac);
        impl.notifyAltered(NCLElementAttributes.INTERFACE, aux, interfac);
    }
    
    
    @Override
    public void setDescriptor(Ed descriptor) {
        Ed aux = this.descriptor;
        super.setDescriptor(descriptor);
        impl.notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, descriptor);
    }
    
        
    @Override
    public boolean addBindParam(Ep param) throws XMLException {
        if(super.addBindParam(param)){
            impl.notifyInserted(NCLElementSets.BINDPARAMS, param);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeBindParam(Ep param) throws XMLException {
        if(super.removeBindParam(param)){
            impl.notifyRemoved(NCLElementSets.BINDPARAMS, param);
            return true;
        }
        return false;
    }


//    private boolean roleReference() {
//        //Search for the role inside the connector
//        if(((NCLLink) getParent()).getXconnector() == null){
//            addWarning("Could not find a connector");
//            return false;
//        }
//
//        NCLCondition cond = ((NCLLink) getParent()).getXconnector().getCondition();
//        if(cond != null){
//            NCLRole r = findRole(cond);
//            if(r != null){
//                setRole((R) r);
//                return true;
//            }
//        }
//
//        NCLAction act = ((NCLLink) getParent()).getXconnector().getAction();
//        if(act != null){
//            NCLRole r = findRole(act);
//            if(r != null){
//                setRole((R) r);
//                return true;
//            }
//        }
//
//        return false;
//    }


//    private R findRole(NCLCondition cond) {
//        if(cond instanceof NCLSimpleCondition){
//            if(((NCLSimpleCondition) cond).getRole().getName().equals(getRole().getName()))
//                return (R) ((NCLSimpleCondition) cond).getRole();
//        }
//        else if(cond instanceof NCLCompoundCondition){
//            List<NCLCondition> conditions = ((NCLCompoundCondition)cond).getConditions();
//            for(NCLCondition c : conditions){
//                NCLRole r = findRole(c);
//                if(r != null)
//                    return (R) r;
//            }
//            List<NCLStatement> statements = ((NCLCompoundCondition)cond).getStatements();
//            for(NCLStatement s : statements){
//                NCLRole r = findRole(s);
//                if(r != null)
//                    return (R) r;
//            }
//        }
//
//        addWarning("Could not find role in connector with name: " + getRole().getName());
//        return null;
//    }


//    private R findRole(NCLStatement stat) {
//        if(stat instanceof NCLAssessmentStatement){
//            List<NCLAttributeAssessment> attributes = ((NCLAssessmentStatement) stat).getAttributeAssessments();
//            for(NCLAttributeAssessment at : attributes){
//                if(at.getRole().getName().equals(getRole().getName()))
//                    return (R) at.getRole();
//            }
//        }
//        else if(stat instanceof NCLCompoundStatement){
//            List<NCLStatement> statements = ((NCLCompoundStatement)stat).getStatements();
//            for(NCLStatement s : statements){
//                NCLRole r = findRole(s);
//                if(r != null)
//                    return (R) r;
//            }
//        }
//
//        addWarning("Could not find role in connector with name: " + getRole().getName());
//        return null;
//    }


//    private R findRole(NCLAction act) {
//        if(act instanceof NCLSimpleAction){
//            if(((NCLSimpleAction) act).getRole().getName().equals(getRole().getName()))
//                return (R) ((NCLSimpleAction) act).getRole();
//        }
//        else if(act instanceof NCLCompoundAction){
//            List<NCLAction> actions = ((NCLCompoundAction)act).getActions();
//            for(NCLAction a : actions){
//                NCLRole r = findRole(a);
//                if(r != null)
//                    return (R) r;
//            }
//        }
//
//        addWarning("Could not find role in connector with name: " + getRole().getName());
//        return null;
//    }


//    private void componentReference() {
//        //Search for a component node in its parent
//        if(getParent().getParent() == null){
//            addWarning("Could not find a link parent");
//            return;
//        }
//
//        Set<N> nodes;
//        if(getParent().getParent() instanceof NCLBody)
//            nodes = ((NCLBody) getParent().getParent()).getNodes();
//        else
//            nodes = ((NCLContext) getParent().getParent()).getNodes();
//
//        for(N node : nodes){
//            if(node.getId().equals(getComponent().getId())){
//                setComponent(node);
//                return;
//            }
//        }
//
//        addWarning("Could not find role in node with id: " + getComponent().getId());
//    }


//    private void interfaceReference() {
//        //Search for the interface inside the node
//        Set<I> ifaces;
//        if(getComponent() instanceof NCLMedia){
//            ifaces = ((NCLMedia) getComponent()).getAreas();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//            ifaces = ((NCLMedia) getComponent()).getProperties();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof NCLContext){
//            ifaces = ((NCLContext) getComponent()).getPorts();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//            ifaces = ((NCLContext) getComponent()).getProperties();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof NCLSwitch){
//            ifaces = ((NCLSwitch) getComponent()).getPorts();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//
//        addWarning("Could not find interface with id: " + getInterface().getId());
//    }


//    private void descriptorReference() {
//        //Search for the descriptor inside the node
//        Set<D> descriptors = getDescriptors();
//        for(D desc : descriptors){
//            if(desc.getId().equals(getDescriptor().getId())){
//                setDescriptor(desc);
//                return;
//            }
//        }
//        //@todo: descritores internos a switch de descritores podem ser utilizados?
//
//        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var, ch_name;
        NodeList nl;

        // set the role (required)
        att_name = NCLElementAttributes.ROLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setRole(); //@todo: achar role do conector pelo nome
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the component (required)
        att_name = NCLElementAttributes.COMPONENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;// setComponent(); //@todo: tem que buscar component pelo id
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the interface (optional)
        att_name = NCLElementAttributes.INTERFACE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;// setInterface(); //@todo: tem que buscar interface pelo id

        // set the descriptor (optional)
        att_name = NCLElementAttributes.DESCRIPTOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setDescriptor(); //@todo: usar metodo de busca pelo id do descritor

        // create the child nodes
        ch_name = NCLElementAttributes.BINDPARAM.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            addBindParam(createBindParam(el));
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>bindParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindParam</i>.
     */
    protected Ep createBindParam(Element element) throws XMLException {
        return (Ep) new NCLParam(NCLParamInstance.BINDPARAM, element);
    }
}
