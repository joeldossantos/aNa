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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.link.NCLBindPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um bind de um elo de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLBind<T extends NCLBind, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRole, En extends NCLNode, Ei extends NCLInterface, Ed extends NCLLayoutDescriptor, Ep extends NCLParam>
        extends NCLBindPrototype<T, P, I, Er, En, Ei, Ed, Ep> implements NCLElement<T, P>{


    /**
     * Construtor do elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLBind() {}


    /**
     * Atribui um papel ao bind.
     * 
     * @param role
     *          elemento representando o papel ao qual o bind será associado.
     */
    @Override
    public void setRole(Er role) {
        Er aux = this.role;
        super.setRole(role);
        impl.notifyAltered(NCLElementAttributes.ROLE, aux, role);
    }
    
    
    /**
     * Atribui um nó ao bind.
     * 
     * @param component
     *          elemento representando o nó mapeado pelo bind.
     */
    @Override
    public void setComponent(En component) {
        En aux = this.component;
        super.setComponent(component);
        impl.notifyAltered(NCLElementAttributes.COMPONENT, aux, component);
    }
    
    
    /**
     * Determina a interface do nó atribuído ao bind.
     * 
     * @param interfac
     *          elemento representando a interface do nó.
     */
    @Override
    public void setInterface(Ei interfac) {
        Ei aux = this.interfac;
        super.setInterface(interfac);
        impl.notifyAltered(NCLElementAttributes.INTERFACE, aux, interfac);
    }
    
    
    /**
     * Atribui um descritor ao bind.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser atribuido.
     */
    @Override
    public void setDescriptor(Ed descriptor) {
        Ed aux = this.descriptor;
        super.setDescriptor(descriptor);
        impl.notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, descriptor);
    }
    
        
    /**
     * Adiciona um parâmetro ao bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addBindParam(Ep param) throws XMLException {
        if(super.addBindParam(param)){
            impl.notifyInserted(NCLElementSets.BINDPARAMS, param);
            return true;
        }
        return false;
    }


    /**
     * Remove um parâmetro do bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeBindParam(Ep param) throws XMLException {
        if(super.removeBindParam(param)){
            impl.notifyRemoved(NCLElementSets.BINDPARAMS, param);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("bind")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("role"))
//                        setRole((R) new NCLRole(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("component"))
//                        setComponent((N) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("interface"))
//                        setInterface((I) new NCLPort(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("descriptor"))
//                        setDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("bindParam")){
//                P child = createBindParam();
//                child.startElement(uri, localName, qName, attributes);
//                addBindParam(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getRole() != null)
//                roleReference();
//            if(getComponent() != null)
//                componentReference();
//            if(getComponent() != null && getInterface() != null)
//                interfaceReference();
//            if(getDescriptor() != null)
//                descriptorReference();
//        }
//
//        if(hasBindParam()){
//            for(P param : bindParams){
//                param.endDocument();
//                addWarning(param.getWarnings());
//                addError(param.getErrors());
//            }
//        }
//    }


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
//
//
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
//
//
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
//
//
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
//
//
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
//
//
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
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


//    /**
//     * Função de criação do elemento filho <i>bindParam</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>bindParam</i>.
//     */
//    protected P createBindParam() {
//        return (P) new NCLParam(NCLParamInstance.BINDPARAM, getReader(), this);
//    }
}
