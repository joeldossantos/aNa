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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.node.NCLSwitchPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define a apresentação de conteudos alternativos em um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLSwitch<T extends NCLSwitch, P extends NCLElement, I extends NCLElementImpl, En extends NCLNode, Ep extends NCLSwitchPort, Eb extends NCLSwitchBindRule>
        extends NCLSwitchPrototype<T, P, I, En, Ep, Eb> implements NCLNode<En, P> {


    /**
     * Construtor do elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch for inválido.
     */
    public NCLSwitch(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Atribui um switch para ser reutilizado pelo switch.
     *
     * @param refer
     *          elemento representando o switch a ser reutilizado.
     */
    @Override
    public void setRefer(T refer) {
        T aux = this.refer;
        super.setRefer(refer);
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    /**
     * Adiciona uma porta ao switch.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addPort(Ep port) throws XMLException {
        if(super.addPort(port)){
            impl.notifyInserted(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Remove uma porta do switch.
     *
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removePort(String id) throws XMLException {
        for(Ep port : ports){
            if(port.getId().equals(id))
                return removePort(port);
        }
        return false;
    }


    /**
     * Remove uma porta do switch.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removePort(Ep port) throws XMLException {
        if(super.removePort(port)){
            impl.notifyRemoved(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Determina o componente padrão do switch.
     *
     * @param defaultComponent
     *          elemento representando o componente padrão.
     */
    @Override
    public void setDefaultComponent(En defaultComponent) {
        En aux = this.defaultComponent;
        super.setDefaultComponent(defaultComponent);
        impl.notifyAltered(NCLElementAttributes.DEFAULTCOMPONENT, aux, defaultComponent);
    }


    /**
     * Adiciona um bind ao switch.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see ArrayList#add
     */
    @Override
    public boolean addBind(Eb bind) throws XMLException {
        if(super.addBind(bind)){
            impl.notifyInserted(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    /**
     * Remove um bind do switch.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see ArrayList#remove
     */
    @Override
    public boolean removeBind(Eb bind) throws XMLException {
        if(super.removeBind(bind)){
            impl.notifyRemoved(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um nó ao switch.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addNode(En node) throws XMLException {
        if(super.addNode(node)){
            impl.notifyInserted(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    /**
     * Remove um nó do switch.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeNode(String id) {
        for(En node : nodes){
            if(node.getId().equals(id))
                return removeNode(node);
        }
        return false;
    }


    /**
     * Remove um nó do switch.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeNode(En node) throws XMLException {
        if(super.removeNode(node)){
            impl.notifyRemoved(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("switch") && !insideSwitch){
//                cleanWarnings();
//                cleanErrors();
//                insideSwitch = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((S) new NCLSwitch(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("bindRule")){
//                B child = createBindRule();
//                child.startElement(uri, localName, qName, attributes);
//                addBind(child);
//            }
//            else if(localName.equals("defaultComponent")){
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("component"))
//                        setDefaultComponent((N) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("switchPort")){
//                P child = createSwitchPort();
//                child.startElement(uri, localName, qName, attributes);
//                addPort(child);
//            }
//            else if(localName.equals("media")){
//                N child = createMedia();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("context")){
//                N child = createContext();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("switch") && insideSwitch){
//                N child = createSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endElement(String uri, String localName, String qName) {
//        if(localName.equals("switch"))
//            super.endElement(uri, localName, qName);
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(getDefaultComponent() != null)
//            defaultComponentReference();
//        if(getParent() != null && getRefer() != null)
//                switchReference();
//
//        if(hasBind()){
//            for(B bind : binds){
//                bind.endDocument();
//                addWarning(bind.getWarnings());
//                addError(bind.getErrors());
//            }
//        }
//        if(hasPort()){
//            for(P port : ports){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//        if(hasNode()){
//            for(N node : nodes){
//                node.endDocument();
//                addWarning(node.getWarnings());
//                addError(node.getErrors());
//            }
//        }
//    }


//    private void defaultComponentReference() {
//        //Search for a component node in its parent
//        for(N node : nodes){
//            if(node.getId().equals(getDefaultComponent().getId())){
//                setDefaultComponent(node);
//                return;
//            }
//        }
//
//        addWarning("Could not find node in switch with id: " + getDefaultComponent().getId());
//    }
//
//
//    private void switchReference() {
//        //Search for the interface inside the node
//        NCLElementImpl body = getParent();
//
//        while(!(body instanceof NCLBody)){
//            body = body.getParent();
//            if(body == null){
//                addWarning("Could not find a body");
//                return;
//            }
//        }
//
//        setRefer(findSwitch(((NCLBody) body).getNodes()));
//    }
//
//
//    private S findSwitch(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof NCLContext){
//                if( ((NCLContext) n).hasNode()){
//                    Set<N> cnodes = ((NCLContext) n).getNodes();
//                        S s = findSwitch(cnodes);
//                        if(s != null)
//                            return (S) s;
//                }
//            }
//            else if(n instanceof NCLSwitch){
//                if(n.getId().equals(getRefer().getId()))
//                    return (S) n;
//                else if( ((NCLSwitch) n).hasNode()){
//                    Set<N> snodes = ((NCLSwitch) n).getNodes();
//                    S s = findSwitch(snodes);
//                    if(s != null)
//                        return (S) s;
//                }
//            }
//        }
//
//        addWarning("Could not find switch with id: " + getRefer().getId());
//        return null;
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
//     * Função de criação do elemento filho <i>bindRule</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>bindRule</i>.
//     */
//    protected B createBindRule() {
//        return (B) new NCLSwitchBindRule(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>switchPort</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>switchPort</i>.
//     */
//    protected P createSwitchPort() {
//        return (P) new NCLSwitchPort(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>media</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>media</i>.
//     */
//    protected N createMedia() {
//        return (N) new NCLMedia(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>context</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>context</i>.
//     */
//    protected N createContext() {
//        return (N) new NCLContext(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>switch</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>switch</i>.
//     */
//    protected N createSwitch() {
//        return (N) new NCLSwitch(getReader(), this);
//    }
}
