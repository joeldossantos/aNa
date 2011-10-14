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

import br.uff.midiacom.ana.datatype.TimeType;
import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLInstanceType;
import br.uff.midiacom.ana.datatype.enums.NCLMediaType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.datatype.enums.NCLUriType;
import br.uff.midiacom.ana.datatype.ncl.node.NCLMediaPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conteúdo de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLMedia<T extends NCLMedia, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLArea, Ep extends NCLProperty, Ed extends NCLLayoutDescriptor, En extends NCLNode>
        extends NCLMediaPrototype<T, P, I, Ea, Ep, Ed, En> implements NCLNode<En, P> {
    
    
    /**
     * Construtor do elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do elemento media.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da media for inválido.
     */
    public NCLMedia(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }

    
    /**
     * Atribui a URI do conteúdo da mídia.
     * 
     * @param src
     *          String contendo a URI do conteúdo da mídia.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida.
     *
     * @see java.net.URI
     */
    @Override
    public void setSrc(SrcType src) {
        SrcType aux = this.src;
        super.setSrc(src);
        impl.notifyAltered(NCLElementAttributes.SRC, aux, src);
    }
    
    
    /**
     * Determina o tipo da mídia.
     * O tipo da mídia será um dos tipos padronizados na norma.
     * 
     * @param type
     *          tipo da mídia.
     */
    @Override
    public void setType(NCLMimeType type) {
        NCLMimeType aux = this.type;
        super.setType(type);
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }
    
        
    /**
     * Determina o descritor da mídia.
     * 
     * @param descriptor
     *          elemento representando o descritor da mídia.
     */
    @Override
    public void setDescriptor(Ed descriptor) {
        Ed aux = this.descriptor;
        super.setDescriptor(descriptor);
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, descriptor);
    }
    

    /**
     * Atribui uma media para ser reutilizada pela media.
     *
     * @param refer
     *          elemento representando a media a ser reutilizado.
     */
    @Override
    public void setRefer(T refer) {
        T aux = this.refer;
        super.setRefer(refer);
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    /**
     * Determina o tipo de instância de outra media essa media é.
     *
     * @param instance
     *          elemento representando o tipo de instancia.
     */
    @Override
    public void setInstance(NCLInstanceType instance) {
        NCLInstanceType aux = this.instance;
        super.setInstance(instance);
        impl.notifyAltered(NCLElementAttributes.INSTANCE, aux, instance);
    }

    
    /**
     * Adiciona uma âncora a mídia.
     * 
     * @param area
     *          elemento representando a âncora a ser adicionada.
     * @return
     *          Verdadeiro se a âncora foi adicionada.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addArea(Ea area) throws XMLException {
        if(super.addArea(area)){
            impl.notifyInserted(NCLElementSets.AREAS, area);
            return true;
        }
        return false;
    }
    
    
    /**
     * Remove uma âncora da mídia.
     *
     * @param id
     *          identificador da âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeArea(String id) {
        for(Ea area : areas){
            if(area.getId().equals(id))
                return removeArea(area);
        }
        return false;
    }
    
    
    /**
     * Remove uma âncora a mídia.
     *
     * @param area
     *          elemento representando a âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean removeArea(Ea area) throws XMLException {
        if(super.removeArea(area)){
            impl.notifyRemoved(NCLElementSets.AREAS, area);
            return true;
        }
        return false;
    }
    
        
    /**
     * Adiciona uma propriedade a mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addProperty(Ep property) throws XMLException {
        if(super.addProperty(property)){
            impl.notifyInserted(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Remove uma propriedade da mídia.
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
        for(Ep property : properties){
            if(property.getId().equals(name))
                return removeProperty(property);
        }
        return false;
    }


    /**
     * Remove uma propriedade da mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeProperty(Ep property) throws XMLException {
        if(super.removeProperty(property)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("media")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("src")){
//                        try{
//                            setSrc(attributes.getValue(i));
//                        }
//                        catch(URISyntaxException ex){
//                            setSrc(new TimeType(attributes.getValue(i)));
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("type")){
//                        for(NCLMimeType m : NCLMimeType.values()){
//                            if(m.toString().equals(attributes.getValue(i)))
//                                setType(m);
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("descriptor"))
//                        setDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((M) new NCLMedia(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("instance")){
//                        for(NCLInstanceType in : NCLInstanceType.values()){
//                            if(in.toString().equals(attributes.getValue(i)))
//                                setInstance(in);
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("area")){
//                A child = createArea();
//                child.startElement(uri, localName, qName, attributes);
//                addArea(child);
//            }
//            else if(localName.equals("property")){
//                P child = createProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addProperty(child);
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
//            if(getDescriptor() != null)
//                descriptorReference();
//            if(getRefer() != null)
//                mediaReference();
//        }
//
//        if(hasArea()){
//            for(A area : areas){
//                area.endDocument();
//                addWarning(area.getWarnings());
//                addError(area.getErrors());
//            }
//        }
//        if(hasProperty()){
//            for(P property : properties){
//                property.endDocument();
//                addWarning(property.getWarnings());
//                addError(property.getErrors());
//            }
//        }
//    }


//    private void descriptorReference() {
//        //Search for the interface inside the node
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
//
//
//    private void mediaReference() {
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
//        setRefer(findMedia(((NCLBody) body).getNodes()));
//    }
//
//
//    private M findMedia(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof NCLMedia){
//                if(n.getId().equals(getRefer().getId()))
//                    return (M) n;
//            }
//            else if(n instanceof NCLContext){
//                if( ((NCLContext) n).hasNode()){
//                    Set<N> cnodes = ((NCLContext) n).getNodes();
//                    M m = findMedia(cnodes);
//                    if(m != null)
//                        return (M) m;
//                }
//            }
//            else if(n instanceof NCLSwitch){
//                if( ((NCLSwitch) n).hasNode()){
//                    Set<N> snodes = ((NCLSwitch) n).getNodes();
//                    M m = findMedia(snodes);
//                    if(m != null)
//                        return (M) m;
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
//     * Função de criação do elemento filho <i>area</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>area</i>.
//     */
//    protected A createArea() {
//        return (A) new NCLArea(getReader(), this);
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
//    protected P createProperty() {
//        return (P) new NCLProperty(getReader(), this);
//    }
}
