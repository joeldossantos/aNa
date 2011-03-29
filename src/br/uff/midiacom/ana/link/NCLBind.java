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
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLParamInstance;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um bind de um elo de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLBind<B extends NCLBind, R extends NCLRole, N extends NCLNode, I extends NCLInterface, D extends NCLDescriptor, P extends NCLParam>
        extends NCLElement implements Comparable<B>{

    private R role;
    private N component;
    private I interfac;
    private D descriptor;
    
    private Set<P> bindParams = new TreeSet<P>();
    

    /**
     * Construtor do elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLBind() {}


    /**
     * Construtor do elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLBind(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um papel ao bind.
     * 
     * @param role
     *          elemento representando o papel ao qual o bind será associado.
     */
    public void setRole(R role) {
        this.role = role;
    }
    
    
    /**
     * Retorna o papel relacionado ao bind.
     * 
     * @return
     *          elemento representando o papel ao qual o bind será associado.
     */
    public R getRole() {
        return role;
    }
    
    
    /**
     * Atribui um nó ao bind.
     * 
     * @param component
     *          elemento representando o nó mapeado pelo bind.
     */
    public void setComponent(N component) {
        this.component = component;
    }
    
    
    /**
     * Retorna o nó atribuído ao bind.
     * 
     * @return
     *          elemento representando o nó mapeado pelo bind.
     */
    public N getComponent() {
        return component;
    }
    
    
    /**
     * Determina a interface do nó atribuído ao bind.
     * 
     * @param interfac
     *          elemento representando a interface do nó.
     */
    public void setInterface(I interfac) {
        this.interfac = interfac;
    }
    
    
    /**
     * Retorna a interface do nó atribuído ao bind.
     * 
     * @return
     *          elemento representando a interface do nó.
     */
    public I getInterface() {
        return interfac;
    }
    
    
    /**
     * Atribui um descritor ao bind.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser atribuido.
     */
    public void setDescriptor(D descriptor) {
        this.descriptor = descriptor;
    }
    
    
    /**
     * Retorna o descritor atribuido ao bind.
     * 
     * @return
     *          elemento representando o descritor a ser atribuido.
     */
    public D getDescriptor() {
        return descriptor;
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
    public boolean addBindParam(P param) {
        if(bindParams.add(param)){
            //Se param existe, atribui este como seu parente
            if(param != null)
                param.setParent(this);

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
    public boolean removeBindParam(P param) {
        if(bindParams.remove(param)){
            //Se param existe, retira o seu parentesco
            if(param != null)
                param.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o bind possui um parâmetro.
     *
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasBindParam(P param) {
        return bindParams.contains(param);
    }


    /**
     * Verifica se o bind possui algum parâmetro.
     *
     * @return
     *          verdadeiro se o bind possui algum parâmetro.
     */
    public boolean hasBindParam() {
        return !bindParams.isEmpty();
    }


    /**
     * Retorna os parâmetros do bind.
     *
     * @return
     *          objeto Iterable contendo os parâmetros do bind.
     */
    public Iterable<P> getBindParams() {
        return bindParams;
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
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        if(getDescriptor() != null)
            content += " descriptor='" + getDescriptor().getId() + "'";
        
        // <bind> element content
        if(hasBindParam()){
            content += ">\n";

            for(P param : bindParams)
                content += param.parse(ident + 1);
            
            content += space + "</bind>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    public int compareTo(B other) {
        int comp = 0;

        String this_bind, other_bind;

        // Compara pelo role
        if(getRole() == null) this_bind = ""; else this_bind = getRole().getName();
        if(other.getRole() == null) other_bind = ""; else other_bind = other.getRole().getName();
        comp = this_bind.compareTo(other_bind);

        // Compara pelo componente
        if(comp == 0){
            if(getComponent() != null)
                comp = getComponent().compareTo(other.getComponent());
            else
                comp = -1;
        }

        // Compara pela interface
        if(comp == 0){
            if(getInterface() != null)
                comp = getInterface().compareTo(other.getInterface());
            else
                comp = -1;
        }

        // Compara pelo descritor
        if(comp == 0){
            if(getDescriptor() != null)
                comp = getDescriptor().compareTo(other.getDescriptor());
            else
                comp = -1;
        }

        // Compara o número de parâmetros
        if(comp == 0)
            comp = bindParams.size() - ((Set) other.getBindParams()).size();

        // Compara os parâmetros
        if(comp == 0){
            Iterator it = other.getBindParams().iterator();
            for(P param : bindParams){
                P other_param = (P) it.next();
                comp = param.compareTo(other_param);
                if(comp != 0)
                    break;
            }
        }


        return comp;
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getRole() == null){
            addError("Elemento não possui atributo obrigatório role.");
            valid = false;
        }
        if(getComponent() == null){
            addError("Elemento não possui atributo obrigatório component.");
            valid = false;
        }

        if(getComponent() != null && getParent() != null && getParent().getParent() != null){
            if(getParent().getParent() instanceof NCLNode && getComponent() instanceof NCLNode && getComponent().compareTo(getParent().getParent()) == 0){
                addError("Atributo component deve referênciar elemento interno a composição.");
                valid = false;
            }

            if(getParent().getParent() instanceof NCLContext && !((NCLContext) getParent().getParent()).hasNode(getComponent())){
                addError("Atributo component deve referênciar elemento interno ao contexto.");
                valid = false;
            }
            else if(getParent().getParent() instanceof NCLBody && !((NCLBody) getParent().getParent()).hasNode(getComponent())){
                addError("Atributo component deve referênciar elemento interno ao corpo do documento.");
                valid = false;
            }
        }

        if(getInterface() != null && getComponent() != null){
            if(getComponent() instanceof NCLMedia){
                if(getInterface() instanceof NCLArea && !((NCLMedia) getComponent()).hasArea((NCLArea) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(getInterface() instanceof NCLProperty && !((NCLMedia) getComponent()).hasProperty((NCLProperty) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(!(getInterface() instanceof NCLProperty) && !(getInterface() instanceof NCLArea)){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
            }
            else if(getComponent() instanceof NCLContext){
                if(getInterface() instanceof NCLPort && !((NCLContext) getComponent()).hasPort((NCLPort) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(getInterface() instanceof NCLProperty && !((NCLContext) getComponent()).hasProperty((NCLProperty) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(!(getInterface() instanceof NCLProperty) && !(getInterface() instanceof NCLPort)){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
            }
            else if(getComponent() instanceof NCLSwitch){
                if(getInterface() instanceof NCLSwitchPort && !((NCLSwitch) getComponent()).hasPort((NCLSwitchPort) getInterface())){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
                else if(!(getInterface() instanceof NCLProperty)){
                    addError("Atributo interface deve referênciar interface contida no elemento referênciado em component.");
                    valid = false;
                }
            }
            else
                valid = false;
        }

        if(hasBindParam()){
            for(P param : bindParams){
                if(!param.getType().equals(NCLParamInstance.BINDPARAM)){
                    addError("Bind não pode possuir parâmetros que não sejam bindParam.");
                    valid = false;
                }
                valid &= param.validate();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("bind")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("role"))
                        setRole((R) new NCLRole(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("component"))
                        setComponent((N) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("interface"))
                        setInterface((I) new NCLPort(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("descriptor"))
                        setDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
                }
            }
            else if(localName.equals("bindParam")){
                P child = createBindParam();
                child.startElement(uri, localName, qName, attributes);
                addBindParam(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getRole() != null)
                roleReference();
            if(getComponent() != null)
                componentReference();
            if(getComponent() != null && getInterface() != null)
                interfaceReference();
            if(getDescriptor() != null)
                descriptorReference();
        }

        if(hasBindParam()){
            for(P param : bindParams){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
    }


    private boolean roleReference() {
        //Search for the role inside the connector
        if(((NCLLink) getParent()).getXconnector() == null){
            addWarning("Could not find a connector");
            return false;
        }

        NCLCondition cond = ((NCLLink) getParent()).getXconnector().getCondition();
        if(cond != null){
            NCLRole r = findRole(cond);
            if(r != null){
                setRole((R) r);
                return true;
            }
        }

        NCLAction act = ((NCLLink) getParent()).getXconnector().getAction();
        if(act != null){
            NCLRole r = findRole(act);
            if(r != null){
                setRole((R) r);
                return true;
            }
        }

        return false;
    }


    private R findRole(NCLCondition cond) {
        if(cond instanceof NCLSimpleCondition){
            if(((NCLSimpleCondition) cond).getRole().getName().equals(getRole().getName()))
                return (R) ((NCLSimpleCondition) cond).getRole();
        }
        else if(cond instanceof NCLCompoundCondition){
            Iterable<NCLCondition> conditions = ((NCLCompoundCondition)cond).getConditions();
            for(NCLCondition c : conditions){
                NCLRole r = findRole(c);
                if(r != null)
                    return (R) r;
            }
            Iterable<NCLStatement> statements = ((NCLCompoundCondition)cond).getStatements();
            for(NCLStatement s : statements){
                NCLRole r = findRole(s);
                if(r != null)
                    return (R) r;
            }
        }

        addWarning("Could not find role in connector with name: " + getRole().getName());
        return null;
    }


    private R findRole(NCLStatement stat) {
        if(stat instanceof NCLAssessmentStatement){
            Iterable<NCLAttributeAssessment> attributes = ((NCLAssessmentStatement) stat).getAttributeAssessments();
            for(NCLAttributeAssessment at : attributes){
                if(at.getRole().getName().equals(getRole().getName()))
                    return (R) at.getRole();
            }
        }
        else if(stat instanceof NCLCompoundStatement){
            Iterable<NCLStatement> statements = ((NCLCompoundStatement)stat).getStatements();
            for(NCLStatement s : statements){
                NCLRole r = findRole(s);
                if(r != null)
                    return (R) r;
            }
        }

        addWarning("Could not find role in connector with name: " + getRole().getName());
        return null;
    }


    private R findRole(NCLAction act) {
        if(act instanceof NCLSimpleAction){
            if(((NCLSimpleAction) act).getRole().getName().equals(getRole().getName()))
                return (R) ((NCLSimpleAction) act).getRole();
        }
        else if(act instanceof NCLCompoundAction){
            Iterable<NCLAction> actions = ((NCLCompoundAction)act).getActions();
            for(NCLAction a : actions){
                NCLRole r = findRole(a);
                if(r != null)
                    return (R) r;
            }
        }

        addWarning("Could not find role in connector with name: " + getRole().getName());
        return null;
    }


    private void componentReference() {
        //Search for a component node in its parent
        if(getParent().getParent() == null){
            addWarning("Could not find a link parent");
            return;
        }

        Iterable<N> nodes;
        if(getParent().getParent() instanceof NCLBody)
            nodes = ((NCLBody) getParent().getParent()).getNodes();
        else
            nodes = ((NCLContext) getParent().getParent()).getNodes();

        for(N node : nodes){
            if(node.getId().equals(getComponent().getId())){
                setComponent(node);
                return;
            }
        }

        addWarning("Could not find role in node with id: " + getComponent().getId());
    }


    private void interfaceReference() {
        //Search for the interface inside the node
        Iterable<I> ifaces;
        if(getComponent() instanceof NCLMedia){
            ifaces = ((NCLMedia) getComponent()).getAreas();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((NCLMedia) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof NCLContext){
            ifaces = ((NCLContext) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((NCLContext) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof NCLSwitch){
            ifaces = ((NCLSwitch) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }

        addWarning("Could not find interface with id: " + getInterface().getId());
    }


    private Iterable<D> getDescriptors() {
        NCLElement root = getParent();

        while(!(root instanceof NCLDoc)){
            root = root.getParent();
            if(root == null){
                addWarning("Could not find a root element");
                return null;
            }
        }

        if(((NCLDoc) root).getHead() == null){
            addWarning("Could not find a head");
            return null;
        }
        if(((NCLDoc) root).getHead().getDescriptorBase() == null){
            addWarning("Could not find a descriptorBase");
            return null;
        }

        return ((NCLDoc) root).getHead().getDescriptorBase().getDescriptors();
    }


    private void descriptorReference() {
        //Search for the descriptor inside the node
        Iterable<D> descriptors = getDescriptors();
        for(D desc : descriptors){
            if(desc.getId().equals(getDescriptor().getId())){
                setDescriptor(desc);
                return;
            }
        }
        //@todo: descritores internos a switch de descritores podem ser utilizados?

        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
    }


    /**
     * Função de criação do elemento filho <i>bindParam</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>bindParam</i>.
     */
    protected P createBindParam() {
        return (P) new NCLParam(NCLParamInstance.BINDPARAM, getReader(), this);
    }
}
