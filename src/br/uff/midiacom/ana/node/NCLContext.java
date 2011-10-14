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

import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.node.NCLContextPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>context</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um contexto de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLContext<T extends NCLContext, P extends NCLElement, I extends NCLElementImpl, Ept extends NCLPort, Epp extends NCLProperty, En extends NCLNode, El extends NCLLink, Em extends NCLMeta, Emt extends NCLMetadata>
        extends NCLContextPrototype<T, P, I, Ept, Epp, En, El, Em, Emt> implements NCLNode<En, P> {
    
    
    /**
     * Construtor do elemento <i>context</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do contexto.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do contexto for inválido.
     */
    public NCLContext(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Atribui um contexto para ser reutilizado pelo contexto.
     *
     * @param refer
     *          elemento representando o contexto a ser reutilizado.
     */
    @Override
    public void setRefer(T refer) {
        T aux = this.refer;
        super.setRefer(refer);
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    /**
     * Adiciona uma porta ao contexto.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addPort(Ept port) throws XMLException {
        if(super.addPort(port)){
            impl.notifyInserted(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Remove uma porta do contexto.
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
        for(Ept port : ports){
            if(port.getId().equals(id))
                return removePort(port);
        }
        return false;
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removePort(Ept port) throws XMLException {
        if(super.removePort(port)){
            impl.notifyRemoved(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Adiciona uma propriedade ao contexto.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addProperty(Epp property) throws XMLException {
        if(super.addProperty(property)){
            impl.notifyInserted(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Remove uma propriedade do contexto.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeProperty(String name) {
        for(Epp property : properties){
            if(property.getId().equals(name))
                return removeProperty(property);
        }
        return false;
    }


    /**
     * Remove uma propriedade do contexto.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeProperty(Epp property) throws XMLException {
        if(super.removeProperty(property)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um nó ao contexto.
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
     * Remove um nó do contexto.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeNode(String id) throws XMLException {
        for(En node : nodes){
            if(node.getId().equals(id))
                return removeNode(node);
        }
        return false;
    }


    /**
     * Remove um nó do contexto.
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


    /**
     * Adiciona um link ao contexto.
     *
     * @param link
     *          elemento representando o link a ser adicionado.
     * @return
     *          Verdadeiro se o link foi adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addLink(El link) throws XMLException {
        if(super.addLink(link)){
            impl.notifyInserted(NCLElementSets.LINKS, link);
            return true;
        }
        return false;
    }


    /**
     * Remove um link do contexto.
     *
     * @param link
     *          elemento representando o link a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeLink(El link) throws XMLException {
        if(super.removeLink(link)){
            impl.notifyRemoved(NCLElementSets.LINKS, link);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addMeta(Em meta) throws XMLException {
        if(super.addMeta(meta)){
            impl.notifyInserted(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeMeta(Em meta) throws XMLException {
        if(super.removeMeta(meta)){
            impl.notifyRemoved(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addMetadata(Emt metadata) throws XMLException {
        if(super.addMetadata(metadata)){
            impl.notifyInserted(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeMetadata(Emt metadata) throws XMLException {
        if(super.removeMetadata(metadata)){
            impl.notifyRemoved(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("context") && !insideContext){
//                cleanWarnings();
//                cleanErrors();
//                insideContext = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((C) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("meta")){
//                M child = createMeta();
//                child.startElement(uri, localName, qName, attributes);
//                addMeta(child);
//            }
//            else if(localName.equals("metadata")){
//                MT child = createMetadata();
//                child.startElement(uri, localName, qName, attributes);
//                addMetadata(child);
//            }
//            else if(localName.equals("port")){
//                Pt child = createPort();
//                child.startElement(uri, localName, qName, attributes);
//                addPort(child);
//            }
//            else if(localName.equals("property")){
//                Pp child = createProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addProperty(child);
//            }
//            else if(localName.equals("media")){
//                N child = createMedia();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("context") && insideContext){
//                N child = createContext();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("switch")){
//                N child = createSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("link")){
//                L child = createLink();
//                child.startElement(uri, localName, qName, attributes);
//                addLink(child);
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
//            if(getRefer() != null)
//                contextReference();
//        }
//
//        if(hasMeta()){
//            for(M meta : metas){
//                meta.endDocument();
//                addWarning(meta.getWarnings());
//                addError(meta.getErrors());
//            }
//        }
//        if(hasMetadata()){
//            for(MT metadata : metadatas){
//                metadata.endDocument();
//                addWarning(metadata.getWarnings());
//                addError(metadata.getErrors());
//            }
//        }
//        if(hasPort()){
//            for(Pt port : ports){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//        if(hasProperty()){
//            for(Pp property : properties){
//                property.endDocument();
//                addWarning(property.getWarnings());
//                addError(property.getErrors());
//            }
//        }
//        if(hasNode()){
//            for(N node : nodes){
//                node.endDocument();
//                addWarning(node.getWarnings());
//                addError(node.getErrors());
//            }
//        }
//        if(hasLink()){
//            for(L link : links){
//                link.endDocument();
//                addWarning(link.getWarnings());
//                addError(link.getErrors());
//            }
//        }
//    }


//    private void contextReference() {
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
//        setRefer(findContext(((NCLBody) body).getNodes()));
//    }
//
//
//    private C findContext(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof NCLContext){
//                if(n.getId().equals(getRefer().getId()))
//                    return (C) n;
//                else if( ((NCLContext) n).hasNode()){
//                    Set<N> cnodes = ((NCLContext) n).getNodes();
//                    C c = findContext(cnodes);
//                    if(c != null)
//                        return (C) c;
//                }
//            }
//            else if(n instanceof NCLSwitch){
//                if( ((NCLSwitch) n).hasNode()){
//                    Set<N> snodes = ((NCLSwitch) n).getNodes();
//                    C c = findContext(snodes);
//                    if(c != null)
//                        return (C) c;
//                }
//            }
//        }
//
//        addWarning("Could not find media with id: " + getRefer().getId());
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
//     * Função de criação do elemento filho <i>meta</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>meta</i>.
//     */
//    protected M createMeta() {
//        return (M) new NCLMeta(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>metadata</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>metadata</i>.
//     */
//    protected MT createMetadata() {
//        return (MT) new NCLMetadata(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>port</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>port</i>.
//     */
//    protected Pt createPort() {
//        return (Pt) new NCLPort(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>property</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>property</i>.
//     */
//    protected Pp createProperty() {
//        return (Pp) new NCLProperty(getReader(), this);
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
//
//
//    /**
//     * Função de criação do elemento filho <i>link</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>link</i>.
//     */
//    protected L createLink() {
//        return (L) new NCLLink(getReader(), this);
//    }
}
