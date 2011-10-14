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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorSwitchPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.ArrayList;
import java.util.TreeSet;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um switch de descritor em um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLDescriptorSwitch<T extends NCLDescriptorSwitch, P extends NCLElement, I extends NCLElementImpl, El extends NCLLayoutDescriptor, Eb extends NCLDescriptorBindRule>
        extends NCLDescriptorSwitchPrototype<T, P, I, El, Eb> implements NCLLayoutDescriptor<El, P> {


    /**
     * Construtor do elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch de descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch de descritor não for válido.
     */
    public NCLDescriptorSwitch(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Adiciona um descritor ao switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addDescriptor(El descriptor) throws XMLException {
        if(super.addDescriptor(descriptor)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param id
     *          identificador do descritor a ser removido.
     * @return
     *          Verdadeiro se o descritor foi removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeDescriptor(String id) {
        for(El descriptor : descriptors){
            if(descriptor.getId().equals(id))
                return removeDescriptor(descriptor);
        }
        return false;
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeDescriptor(El descriptor) throws XMLException {
        if(super.removeDescriptor(descriptor)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um bind ao switch de descritor.
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
     * Remove um bind do switch de descritor.
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
     * Determina o descritor padrão do switch de descritor.
     *
     * @param defaultDescriptor
     *          elemento representando o descritor padrão.
     */
    @Override
    public void setDefaultDescriptor(El defaultDescriptor) {
        El aux = this.defaultDescriptor;
        super.setDefaultDescriptor(defaultDescriptor);
        impl.notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, defaultDescriptor);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("descriptorSwitch")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("bindRule")){
//                B child = createBindRule();
//                child.startElement(uri, localName, qName, attributes);
//                addBind(child);
//            }
//            else if(localName.equals("descriptor")){
//                D child = createDescriptor();
//                child.startElement(uri, localName, qName, attributes);
//                addDescriptor(child);
//            }
//            else if(localName.equals("defaultDescriptor")){
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("descriptor"))
//                        setDefaultDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
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
//        if(localName.equals("descriptorSwitch"))
//            super.endElement(uri, localName, qName);
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(getDefaultDescriptor() != null)
//            defaultDescriptorReference();
//
//        if(hasBind()){
//            for(B bind : binds){
//                bind.endDocument();
//                addWarning(bind.getWarnings());
//                addError(bind.getErrors());
//            }
//        }
//        if(hasDescriptor()){
//            for(D descriptor : descriptors){
//                descriptor.endDocument();
//                addWarning(descriptor.getWarnings());
//                addError(descriptor.getErrors());
//            }
//        }
//    }


//    private void defaultDescriptorReference() {
//        //Search for a component node in its parent
//        for(D descriptor : descriptors){
//            if(descriptor.getId().equals(getDefaultDescriptor().getId())){
//                setDefaultDescriptor(descriptor);
//                return;
//            }
//        }
//
//        addWarning("Could not find descriptor in descriptorSwitch with id: " + getDefaultDescriptor().getId());
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
//        return (B) new NCLDescriptorBindRule(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>descriptor</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>descriptor</i>.
//     */
//    protected D createDescriptor() {
//        return (D) new NCLDescriptor(getReader(), this);
//    }
}
