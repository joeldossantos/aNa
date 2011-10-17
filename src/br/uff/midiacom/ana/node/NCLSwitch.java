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
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.node.NCLSwitchPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLSwitch<T extends NCLSwitch, P extends NCLElement, I extends NCLElementImpl, En extends NCLNode, Ep extends NCLSwitchPort, Eb extends NCLSwitchBindRule>
        extends NCLSwitchPrototype<T, P, I, En, Ep, Eb> implements NCLNode<En, P> {


    public NCLSwitch(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public void setRefer(T refer) {
        T aux = this.refer;
        super.setRefer(refer);
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    @Override
    public boolean addPort(Ep port) throws XMLException {
        if(super.addPort(port)){
            impl.notifyInserted(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    @Override
    public boolean removePort(String id) throws XMLException {
        if(super.removePort(id)){
            impl.notifyRemoved(NCLElementSets.PORTS, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean removePort(Ep port) throws XMLException {
        if(super.removePort(port)){
            impl.notifyRemoved(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    @Override
    public void setDefaultComponent(En defaultComponent) {
        En aux = this.defaultComponent;
        super.setDefaultComponent(defaultComponent);
        impl.notifyAltered(NCLElementAttributes.DEFAULTCOMPONENT, aux, defaultComponent);
    }


    @Override
    public boolean addBind(Eb bind) throws XMLException {
        if(super.addBind(bind)){
            impl.notifyInserted(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeBind(Eb bind) throws XMLException {
        if(super.removeBind(bind)){
            impl.notifyRemoved(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    @Override
    public boolean addNode(En node) throws XMLException {
        if(super.addNode(node)){
            impl.notifyInserted(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeNode(String id) throws XMLException {
        if(super.removeNode(id)){
            impl.notifyRemoved(NCLElementSets.NODES, id);
            return true;
        }
        return false;
    }


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


//    @Override
//    public void endElement(String uri, String localName, String qName) {
//        if(localName.equals("switch"))
//            super.endElement(uri, localName, qName);
//    }


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


    /**
     * Function to create the child element <i>bindRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindRule</i>.
     */
    protected NCLSwitchBindRule createBindRule() throws XMLException {
        return new NCLSwitchBindRule();
    }


    /**
     * Function to create the child element <i>switchPort</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switchPort</i>.
     */
    protected NCLSwitchPort createSwitchPort(String id) throws XMLException {
        return new NCLSwitchPort(id);
    }


    /**
     * Function to create the child element <i>media</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>media</i>.
     */
    protected NCLMedia createMedia(String id) throws XMLException {
        return new NCLMedia(id);
    }


    /**
     * Function to create the child element <i>context</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>context</i>.
     */
    protected NCLContext createContext(String id) throws XMLException {
        return new NCLContext(id);
    }


    /**
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected NCLSwitch createSwitch(String id) throws XMLException {
        return new NCLSwitch(id);
    }
}
