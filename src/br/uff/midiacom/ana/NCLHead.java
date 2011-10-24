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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLHeadPrototype;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLHead<T extends NCLHead, P extends NCLElement, I extends NCLElementImpl, Eib extends NCLImportedDocumentBase, Erl extends NCLRuleBase, Etb extends NCLTransitionBase, Erb extends NCLRegionBase, Edb extends NCLDescriptorBase, Ecb extends NCLConnectorBase, Em extends NCLMeta, Emt extends NCLMetadata>
    extends NCLHeadPrototype<T, P, I, Eib, Erl, Etb, Erb, Edb, Ecb, Em, Emt> implements NCLElement<T, P> {

    
    public NCLHead() throws XMLException {
        super();
    }


    public NCLHead(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }

    
    @Override
    public void setImportedDocumentBase(Eib importedDocumentBase) {
        Eib aux = this.importedDocumentBase;
        super.setImportedDocumentBase(importedDocumentBase);
        impl.notifyAltered(NCLElementAttributes.IMPORTEDDOCUMENTBASE, aux, importedDocumentBase);
    }


    @Override
    public void setRuleBase(Erl ruleBase) {
        Erl aux = this.ruleBase;
        super.setRuleBase(ruleBase);
        impl.notifyAltered(NCLElementAttributes.RULEBASE, aux, ruleBase);
    }


    @Override
    public void setTransitionBase(Etb transitionBase) {
        Etb aux = this.transitionBase;
        super.setTransitionBase(transitionBase);
        impl.notifyAltered(NCLElementAttributes.TRANSITIONBASE, aux, transitionBase);
    }


    @Override
    public boolean addRegionBase(Erb regionBase) throws XMLException {
        if(super.addRegionBase(regionBase)){
            impl.notifyInserted(NCLElementSets.REGIONBASE, regionBase);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRegionBase(Erb regionBase) throws XMLException {
        if(super.removeRegionBase(regionBase)){
            impl.notifyRemoved(NCLElementSets.REGIONBASE, regionBase);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRegionBase(String id) throws XMLException {
        if(super.removeRegionBase(id)){
            impl.notifyRemoved(NCLElementSets.REGIONBASE, id);
            return true;
        }
        return false;
    }


    @Override
    public void setDescriptorBase(Edb descriptorBase) {
        Edb aux = this.descriptorBase;
        super.setDescriptorBase(descriptorBase);
        impl.notifyAltered(NCLElementAttributes.DESCRIPTORBASE, aux, descriptorBase);
    }

    
    @Override
    public void setConnectorBase(Ecb connectorBase) {
        Ecb aux = this.connectorBase;
        super.setConnectorBase(connectorBase);
        impl.notifyAltered(NCLElementAttributes.CONNECTORBASE, aux, connectorBase);
    }


    @Override
    public boolean addMeta(Em meta) throws XMLException {
        if(super.addMeta(meta)){
            impl.notifyInserted(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeMeta(Em meta) throws XMLException {
        if(super.removeMeta(meta)){
            impl.notifyRemoved(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    @Override
    public boolean addMetadata(Emt metadata) throws XMLException {
        if(super.addMetadata(metadata)){
            impl.notifyInserted(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeMetadata(Emt metadata) throws XMLException {
        if(super.removeMetadata(metadata)){
            impl.notifyRemoved(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    public void load(Element element) throws XMLException {
        String ch_name;
        Element el;

        // create the importedDocumentBase
        ch_name = NCLElementAttributes.IMPORTEDDOCUMENTBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        setImportedDocumentBase(createImportedDocumentBase(el));

        // create the ruleBase
        ch_name = NCLElementAttributes.RULEBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        setRuleBase(createRuleBase(el));

        // create the transitionBase
        ch_name = NCLElementAttributes.TRANSITIONBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        setTransitionBase(createTransitionBase(el));

        // create the child nodes (regionBases, metas and metadatas)
        NodeList nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                el = (Element) nl.item(i);

                // create the regionBase
                if(el.getTagName().equals(NCLElementAttributes.REGIONBASE.toString()))
                    addRegionBase(createRegionBase(el));
                // create the meta
                if(el.getTagName().equals(NCLElementAttributes.META.toString()))
                    addMeta(createMeta(el));
                // create the metadata
                if(el.getTagName().equals(NCLElementAttributes.METADATA.toString()))
                    addMetadata(createMetadata(el));
            }
        }

        // create the descriptorBase
        ch_name = NCLElementAttributes.DESCRIPTORBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        setDescriptorBase(createDescriptorBase(el));

        // create the connectorBase
        ch_name = NCLElementAttributes.CONNECTORBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        setConnectorBase(createConnectorBase(el));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>importedDocumentBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importedDocumentBase</i>.
     */
    protected Eib createImportedDocumentBase(Element element) throws XMLException {
        return (Eib) new NCLImportedDocumentBase(element);
    }


    /**
     * Function to create the child element <i>ruleBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>ruleBase</i>.
     */
    protected Erl createRuleBase(Element element) throws XMLException {
        return (Erl) new NCLRuleBase(element);
    }


    /**
     * Function to create the child element <i>transitionBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>transitionBase</i>.
     */
    protected Etb createTransitionBase(Element element) throws XMLException {
        return (Etb) new NCLTransitionBase(element);
    }


    /**
     * Function to create the child element <i>regionBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>regionBase</i>.
     */
    protected Erb createRegionBase(Element element) throws XMLException {
        return (Erb) new NCLRegionBase(element);
    }


    /**
     * Function to create the child element <i>descriptorBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorBase</i>.
     */
    protected Edb createDescriptorBase(Element element) throws XMLException {
        return (Edb) new NCLDescriptorBase(element);
    }


    /**
     * Function to create the child element <i>connectorBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connectorBase</i>.
     */
    protected Ecb createConnectorBase(Element element) throws XMLException {
        return (Ecb) new NCLConnectorBase(element);
    }


    /**
     * Function to create the child element <i>meta</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>meta</i>.
     */
    protected Em createMeta(Element element) throws XMLException {
        return (Em) new NCLMeta(element);
    }


    /**
     * Function to create the child element <i>metadata</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>metadata</i>.
     */
    protected Emt createMetadata(Element element) throws XMLException {
        return (Emt) new NCLMetadata(element);
    }
}
