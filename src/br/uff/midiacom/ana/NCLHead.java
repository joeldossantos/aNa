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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents the NCL document head element.
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importedDocumentBase</i> - base of imported documents. This element
 *                                    is optional.</li>
 *  <li><i>ruleBase</i> - base of rules. This element is optional.</li>
 *  <li><i>transitionBase</i> - base of transitions. This element is optional.</li>
 *  <li><i>regionBase</i> - base of regions. The head can have none or several 
 *                          bases of regions.</li>
 *  <li><i>descriptorBase</i> - base of descriptors. This element is optional.</li>
 *  <li><i>connectorBase</i> - base of connectors. This element is optional.</li>
 *  <li><i>meta</i> - element defining meta data. The head can have none or several
 *                    meta elements.</li>
 *  <li><i>metadata</i> - element defining a RDF tree. The head can have none
 *                        or several metadata elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Eib>
 * @param <Erl>
 * @param <Etb>
 * @param <Erb>
 * @param <Edb>
 * @param <Ecb>
 * @param <Em>
 * @param <Emt> 
 */
public class NCLHead<T extends NCLElement,
                     Eib extends NCLImportedDocumentBase,
                     Erl extends NCLRuleBase,
                     Etb extends NCLTransitionBase,
                     Erb extends NCLRegionBase,
                     Edb extends NCLDescriptorBase,
                     Ecb extends NCLConnectorBase,
                     Em extends NCLMeta,
                     Emt extends NCLMetadata>
        extends NCLElementPrototype<T>
        implements NCLElement<T> {

    protected Eib importedDocumentBase;
    protected Erl ruleBase;
    protected Etb transitionBase;
    protected ElementList<Erb> regionBases;
    protected Edb descriptorBase;
    protected Ecb connectorBase;
    protected ElementList<Em> metas;
    protected ElementList<Emt> metadatas;


    /**
     * Head element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLHead() {
        super();
        regionBases = new ElementList<Erb>();
        metas = new ElementList<Em>();
        metadatas = new ElementList<Emt>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        if(doc == null)
            doc = getParent(); // doc is the parent of head
        
        super.setDoc(doc);
        if(importedDocumentBase != null) importedDocumentBase.setDoc(doc);
        if(ruleBase != null) ruleBase.setDoc(doc);
        if(transitionBase != null) transitionBase.setDoc(doc);
        if(descriptorBase != null) descriptorBase.setDoc(doc);
        if(connectorBase != null) connectorBase.setDoc(doc);
        for (Erb aux : regionBases) {
            aux.setDoc(doc);
        }
        for (Em aux : metas) {
            aux.setDoc(doc);
        }
        for (Emt aux : metadatas) {
            aux.setDoc(doc);
        }
    }

    
    /**
     * Adds a base of imported documents to the head. The base of imported
     * documents is optional. Set the base to <i>null</i> to remove the base
     * already defined.
     *
     * @param importedDocumentBase 
     *          element representing the base of imported documents or <i>null</i>
     *          to remove the base already defined.
     */
    public void setImportedDocumentBase(Eib importedDocumentBase) throws XMLException {
        // Remove the parent of the actual base, if exists
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(null);
            notifyRemoved((T) this.importedDocumentBase);
        }
        // Add the new base element
        this.importedDocumentBase = importedDocumentBase;
        // Set the parent of the new base, if it exists
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(this);
            notifyInserted((T) this.importedDocumentBase);
        }
    }


    /**
     * Returns the base of imported documents used by the head or <i>null</i> if
     * the head does not have a base of imported documents.
     *
     * @return
     *          element representing the base of imported documents or <i>null</i>
     *          if the head does not have a base of imported documents.
     */

    public Eib getImportedDocumentBase() {
        return importedDocumentBase;
    }


    /**
     * Adds a base of rules to the head. The base of rules is optional. Set the
     * base to <i>null</i> to remove the base already defined.
     *
     * @param ruleBase
     *          element representing the base of rules or <i>null</i> to remove
     *          the base already defined.
     */
    public void setRuleBase(Erl ruleBase) throws XMLException {
        // Remove the parent of the actual base, if exists
        if(this.ruleBase != null){
            this.ruleBase.setParent(null);
            notifyRemoved((T) this.ruleBase);
        }
        // Add the new base element
        this.ruleBase = ruleBase;
        // Set the parent of the new base, if it exists
        if(this.ruleBase != null){
            this.ruleBase.setParent(this);
            notifyInserted((T) this.ruleBase);
        }
    }


    /**
     * Returns the base of rules used by the head or <i>null</i> if the head
     * does not have a base of rules.
     *
     * @return
     *          element representing the base of rules or <i>null</i> if the
     *          head does not have a base of rules.
     */

    public Erl getRuleBase() {
        return ruleBase;
    }


    /**
     * Adds a base of transitions to the head. The base of transitions is
     * optional. Set the base to <i>null</i> to remove the base already defined.
     *
     * @param transitionBase
     *          element representing the base of transitions or <i>null</i> to
     *          remove the base already defined.
     */
    public void setTransitionBase(Etb transitionBase) throws XMLException {
        // Remove the parent of the actual base, if exists
        if(this.transitionBase != null){
            this.transitionBase.setParent(null);
            notifyRemoved((T) this.transitionBase);
        }
        // Add the new base element
        this.transitionBase = transitionBase;
        // Set the parent of the new base, if it exists
        if(this.transitionBase != null){
            this.transitionBase.setParent(this);
            notifyInserted((T) this.transitionBase);
        }
    }


    /**
     * Returns the base of transitions used by the head or <i>null</i> if the
     * head does not have a base of transitions.
     *
     * @return
     *          element representing the base of transitions or <i>null</i> if
     *          the head does not have a base of transitions.
     */

    public Etb getTransitionBase() {
        return transitionBase;
    }


    /**
     * Adds a base of regions to the head element. The head can have none or
     * several bases of regions.
     * 
     * @param regionBase
     *          element representing a base of regions.
     * @return
     *          true if the base was added.
     * @throws XMLException 
     *          if the element representing the base is null.
     */
    public boolean addRegionBase(Erb regionBase) throws XMLException {
        if(regionBases.add(regionBase)){
            notifyInserted((T) regionBase);
            regionBase.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes a base of regions of the head element. The head can have none or
     * several bases of regions.
     * 
     * @param regionBase
     *          element representing a base of regions.
     * @return
     *          true if the base was removed.
     * @throws XMLException 
     *          if the element representing the base is null.
     */
    public boolean removeRegionBase(Erb regionBase) throws XMLException {
        if(regionBases.remove(regionBase)){
            notifyRemoved((T) regionBase);
            regionBase.setParent(null);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a base of regions of the head element. The head can have none or
     * several bases of regions.
     * 
     * @param id
     *          string representing the id of the base of regions.
     * @return
     *          true if the base was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeRegionBase(String id) throws XMLException {
        Erb aux = regionBases.get(id);
        return removeRegionBase(aux);
    }


    /**
     * Verifies if the head element has a specific base of regions. The head can
     * have none or several bases of regions.
     * 
     * @param regionBase
     *          element representing a base of regions.
     * @return
     *          true if the head element has the base of regions.
     * @throws XMLException 
     *          if the element representing the base is null.
     */
    public boolean hasRegionBase(Erb regionBase) throws XMLException {
        return regionBases.contains(regionBase);
    }
    
    
    /**
     * Verifies if the head element has a base of regions with a specific id.
     * The head can have none or several bases of regions.
     * 
     * @param id
     *          string representing the id of the base of regions.
     * @return
     *          true if the head element has the base of regions.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasRegionBase(String id) throws XMLException {
        return regionBases.get(id) != null;
    }


    /**
     * Verifies if the head element has at least one base of regions. The head
     * can have none or several bases of regions.
     * 
     * @return 
     *          true if the head has at least one base of regions.
     */
    public boolean hasRegionBase() {
        return !regionBases.isEmpty();
    }


    /**
     * Returns the list of bases of regions that a head element have. The head
     * can have none or several bases of regions.
     * 
     * @return 
     *          element list with all bases of regions.
     */
    public ElementList<Erb> getRegionBases() {
        return regionBases;
    }


    /**
     * Returns the base of regions with a specific id. The head can have none or
     * several bases of regions.
     * 
     * @param id
     *          string representing the id of the base of regions.
     * @return 
     *          element representing a base of regions.
     */
    public Erb getRegionBase(String id) throws XMLException {
        return regionBases.get(id);
    }


    /**
     * Adds a base of descriptors to the head. The base of descriptors is
     * optional. Set the base to <i>null</i> to remove the base already defined.
     *
     * @param descriptorBase
     *          element representing the base of descriptors or <i>null</i> to
     *          remove the base already defined.
     */    
    public void setDescriptorBase(Edb descriptorBase) throws XMLException {
        // Remove the parent of the actual base, if exists
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(null);
            notifyRemoved((T) this.descriptorBase);
        }
        // Add the new base element
        this.descriptorBase = descriptorBase;
        // Set the parent of the new base, if it exists
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(this);
            notifyInserted((T) this.descriptorBase);
        }
    }

    
    /**
     * Returns the base of descriptors used by the head or <i>null</i> if the
     * head does not have a base of descriptors.
     *
     * @return
     *          element representing the base of descriptors or <i>null</i> if
     *          the head does not have a base of descriptors.
     */
    public Edb getDescriptorBase() {
        return descriptorBase;
    }


    /**
     * Adds a base of connectors to the head. The base of connectors is optional.
     * Set the base to <i>null</i> to remove the base already defined.
     *
     * @param connectorBase
     *          element representing the base of connectors or <i>null</i> to
     *          remove the base already defined.
     */
    public void setConnectorBase(Ecb connectorBase) throws XMLException {
        // Remove the parent of the actual base, if exists
        if(this.connectorBase != null){
            this.connectorBase.setParent(null);
            notifyRemoved((T) this.connectorBase);
        }
        // Add the new base element
        this.connectorBase = connectorBase;
        // Set the parent of the new base, if it exists
        if(this.connectorBase != null){
            this.connectorBase.setParent(this);
            notifyInserted((T) this.connectorBase);
        }
    }

    
    /**
     * Returns the base of connectors used by the head or <i>null</i> if the
     * head does not have a base of connectors.
     *
     * @return
     *          element representing the base of connectors or <i>null</i> if
     *          the head does not have a base of connectors.
     */
    public Ecb getConnectorBase() {
        return connectorBase;
    }


    /**
     * Adds an element defining meta data to the head element. The head can have
     * none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the meta element was added.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean addMeta(Em meta) throws XMLException {
        if(metas.add(meta)){
            notifyInserted((T) meta);
            meta.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element defining meta data of the head element. The head can
     * have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the meta element was removed.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean removeMeta(Em meta) throws XMLException {
        if(metas.remove(meta)){
            notifyRemoved((T) meta);
            meta.setParent(null);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the head element has a specific meta element. The head can
     * have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the head element has the meta element.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean hasMeta(Em meta) throws XMLException {
        return metas.contains(meta);
    }


    /**
     * Verifies if the head element has at least one meta element. The head
     * can have none or several meta elements.
     * 
     * @return 
     *          true if the head has at least one meta element.
     */
    public boolean hasMeta() {
        return !metas.isEmpty();
    }


    /**
     * Returns the list of meta elements that a head element have. The head
     * can have none or several meta elements.
     * 
     * @return 
     *          element list with all meta elements.
     */
    public ElementList<Em> getMetas() {
        return metas;
    }


    /**
     * Adds an element defining an RDF tree to the head element. The head can have
     * none or several metadata elements.
     * 
     * @param metadata
     *          element defining an RDF tree.
     * @return
     *          true if the metadata element was added.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean addMetadata(Emt metadata) throws XMLException {
        if(metadatas.add(metadata)){
            notifyInserted((T) metadata);
            metadata.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element defining an RDF tree of the head element. The head can
     * have none or several metadata elements.
     * 
     * @param metadata
     *          element defining an RDF tree.
     * @return
     *          true if the metadata element was removed.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean removeMetadata(Emt metadata) throws XMLException {
        if(metadatas.remove(metadata)){
            notifyRemoved((T) metadata);
            metadata.setParent(null);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the head element has a specific metadata element. The head can
     * have none or several metadata elements.
     * 
     * @param meta
     *          element defining an RDF tree.
     * @return
     *          true if the head element has the metadata element.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean hasMetadata(Emt metadata) throws XMLException {
        return metadatas.contains(metadata);
    }


    /**
     * Verifies if the head element has at least one metadata element. The head
     * can have none or several metadata elements.
     * 
     * @return 
     *          true if the head has at least one metadata element.
     */
    public boolean hasMetadata() {
        return !metadatas.isEmpty();
    }


    /**
     * Returns the list of metadata elements that a head element have. The head
     * can have none or several metadata elements.
     * 
     * @return 
     *          element list with all metadata elements.
     */
    public ElementList<Emt> getMetadatas() {
        return metadatas;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLHead))
            return false;
        
        boolean result = true;
        
        T el;
        
        if((el = (T) getImportedDocumentBase()) != null)
            result &= el.compare(((NCLHead) other).getImportedDocumentBase());
        if((el = (T) getRuleBase()) != null)
            result &= el.compare(((NCLHead) other).getRuleBase());
        if((el = (T) getTransitionBase()) != null)
            result &= el.compare(((NCLHead) other).getTransitionBase());
        
        ElementList<Erb> otherrb = ((NCLHead) other).getRegionBases();
        result &= regionBases.size() == otherrb.size();
        for (Erb aux : regionBases) {
            try {
                result &= otherrb.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        if((el = (T) getDescriptorBase()) != null)
            result &= el.compare(((NCLHead) other).getDescriptorBase());
        if((el = (T) getConnectorBase()) != null)
            result &= el.compare(((NCLHead) other).getConnectorBase());
        
        ElementList<Em> othermet = ((NCLHead) other).getMetas();
        result &= metas.size() == othermet.size();
        for (Em aux : metas) {
            try {
                result &= othermet.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Emt> othermtd = ((NCLHead) other).getMetadatas();
        result &= metadatas.size() == othermtd.size();
        for (Emt aux : metadatas) {
            try {
                result &= othermtd.contains(aux);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        return result;
    }
    
    
    @Override
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
                
        content = space + "<head>\n";
        
        content += parseElements(ident + 1);
        
        content += space + "</head>\n";
        
        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        Element el;

        try{
            loadImportedDocumentBase(element);

            loadRuleBase(element);

            loadTransitionBase(element);

            // create the child nodes (regionBases, metas and metadatas)
            NodeList nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    el = (Element) nl.item(i);

                    loadRegionBases(el);
                    loadMetas(el);
                    loadMetadatas(el);
                }
            }

            loadDescriptorBase(element);

            loadConnectorBase(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("Head > " + ex.getMessage());
        }
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportedDocumentBase(ident);
        content += parseRuleBase(ident);
        content += parseTransitionBase(ident);
        content += parseRegionBases(ident);
        content += parseDescriptorBase(ident);
        content += parseConnectorBase(ident);
        content += parseMetas(ident);
        content += parseMetadatas(ident);
        
        return content;
    }
    
    
    protected String parseImportedDocumentBase(int ident) {
        Eib aux = getImportedDocumentBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadImportedDocumentBase(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the importedDocumentBase
        ch_name = NCLElementAttributes.IMPORTEDDOCUMENTBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Eib inst = createImportedDocumentBase();
            setImportedDocumentBase(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseRuleBase(int ident) {
        Erl aux = getRuleBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadRuleBase(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the ruleBase
        ch_name = NCLElementAttributes.RULEBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Erl inst = createRuleBase();
            setRuleBase(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseTransitionBase(int ident) {
        Etb aux = getTransitionBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadTransitionBase(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the transitionBase
        ch_name = NCLElementAttributes.TRANSITIONBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Etb inst = createTransitionBase();
            setTransitionBase(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseRegionBases(int ident) {
        if(!hasRegionBase())
            return "";
        
        String content = "";
        for(Erb aux : regionBases)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadRegionBases(Element element) throws XMLException {
        // create the regionBase
        if(element.getTagName().equals(NCLElementAttributes.REGIONBASE.toString())){
            Erb inst = createRegionBase();
            addRegionBase(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseDescriptorBase(int ident) {
        Edb aux = getDescriptorBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadDescriptorBase(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the descriptorBase
        ch_name = NCLElementAttributes.DESCRIPTORBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Edb inst = createDescriptorBase();
            setDescriptorBase(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseConnectorBase(int ident) {
        Ecb aux = getConnectorBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadConnectorBase(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the connectorBase
        ch_name = NCLElementAttributes.CONNECTORBASE.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Ecb inst = createConnectorBase();
            setConnectorBase(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseMetas(int ident) {
        if(!hasMeta())
            return "";
        
        String content = "";
        for(Em aux : metas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMetas(Element element) throws XMLException {
        // create the meta
        if(element.getTagName().equals(NCLElementAttributes.META.toString())){
            Em inst = createMeta();
            addMeta(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseMetadatas(int ident) {
        if(!hasMetadata())
            return "";
        
        String content = "";
        for(Emt aux : metadatas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMetadatas(Element element) throws XMLException {
        // create the metadata
        if(element.getTagName().equals(NCLElementAttributes.METADATA.toString())){
            Emt inst = createMetadata();
            addMetadata(inst);
            inst.load(element);
        }
    }

    
    /**
     * Searches for a connector inside a connectorBase or in an imported
     * document.
     * 
     * @param alias
     *          alias of the importBase the imports the connector.
     * @param id
     *          id of the connector to be found.
     * @return 
     *          connector or null if no connector was found.
     */
    public Object findConnector(String alias, String id) throws XMLException {
        Object result;
        
        Ecb cb = getConnectorBase();
        if(cb != null){
            result = cb.findConnector(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the connector base of the imported documents
        Eib ib;
        if((ib = getImportedDocumentBase()) != null){
            result = ib.findConnector(alias, id);
            if(result != null)
                return result;
        }
        

        String msg = "Could not find connector with id: ";
        if(alias != null)
            msg += alias + "#";
        msg += id;
        throw new NCLParsingException(msg);
    }
    
    
    /**
     * Searches for a descriptor inside a descriptorBase or in an imported
     * document.
     * 
     * @param alias
     *          alias of the importBase the imports the descriptor.
     * @param id
     *          id of the descriptor to be found.
     * @return 
     *          descriptor or null if no descriptor was found.
     */
    public Object findDescriptor(String alias, String id) throws XMLException {
        Object result;
        
        Edb db = getDescriptorBase();
        if(db != null){
            result = db.findDescriptor(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the descriptor base of the imported documents
        Eib ib;
        if((ib = getImportedDocumentBase()) != null){
            result = ib.findDescriptor(alias, id);
            if(result != null)
                return result;
        }
        

        String msg = "Could not find descriptor with id: ";
        if(alias != null)
            msg += alias + "#";
        msg += id;
        throw new NCLParsingException(msg);
    }
    
    
    /**
     * Searches for a descriptor inside a descriptorBase or in an imported
     * document.
     * 
     * @param focusIndex
     *          focusIndex of the descriptor to be found.
     * @return 
     *          descriptor or null if no descriptor was found.
     */
    public Object findDescriptor(Integer focusIndex) throws XMLException {
        Object result;
        
        Edb db = getDescriptorBase();
        if(db != null){
            result = db.findDescriptor(focusIndex);
            if(result != null)
                return result;
        }
        
        
        // search in the descriptor base of the imported documents
        Eib ib;
        if((ib = getImportedDocumentBase()) != null){
            result = ib.findDescriptor(focusIndex);
            if(result != null)
                return result;
        }
        

        String msg = "Could not find descriptor with focusIndex: " + focusIndex;
        throw new NCLParsingException(msg);
    }
    
    
    /**
     * Searches for a region inside a regionBase and its descendants or in a
     * region base imported together with a descriptorBase or in an imported
     * document.
     * 
     * @param baseId
     *          id of the base where the region is in.
     * @param alias
     *          alias of the importBase the imports the region.
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public Object findRegion(String baseId, String alias, String id) throws XMLException {
        Object result;
        
        if(baseId == null){
            for(Erb aux : regionBases){
                result = aux.findRegion(alias, id);
                if(result != null)
                    return result;
            }
        }
        else{
            result = regionBases.get(baseId).findRegion(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the base imported by a descriptor base
        Edb db = getDescriptorBase();
        if(db != null){
            result = db.findRegion(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the region base of the imported documents
        Eib ib = getImportedDocumentBase();
        if(ib != null){
            result = ib.findRegion(alias, id);
            if(result != null)
                return result;
        }
        

        String msg = "Could not find region with id: ";
        if(alias != null)
            msg += alias + "#";
        msg += id;
        throw new NCLParsingException(msg);
    }
    
    
    /**
     * Searches for a rule inside a regionBase or in a rule base imported
     * together with a descriptorBase or in an imported document.
     * 
     * @param alias
     *          alias of the importBase the imports the rule.
     * @param id
     *          id of the rule to be found.
     * @return 
     *          rule or null if no rule was found.
     */
    public Object findRule(String alias, String id) throws XMLException {
        Object result;
        
        Erl rl = getRuleBase();
        if(rl != null){
            result = rl.findRule(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the base imported by a descriptor base
        Edb db = getDescriptorBase();
        if(db != null){
            result = db.findRule(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the rule base of the imported documents
        Eib ib = getImportedDocumentBase();
        if(ib != null){
            result = ib.findRule(alias, id);
            if(result != null)
                return result;
        }
        

        String msg = "Could not find rule with id: ";
        if(alias != null)
            msg += alias + "#";
        msg += id;
        throw new NCLParsingException(msg);
    }
    
    
    /**
     * Searches for a transition inside a transitionBase or in a transition base
     * imported together with a descriptorBase or in an imported document.
     * 
     * @param alias
     *          alias of the importBase the imports the transition.
     * @param id
     *          id of the transition to be found.
     * @return 
     *          transition or null if no transition was found.
     */
    public Object findTransition(String alias, String id) throws XMLException {
        Object result;
        
        Etb tb = getTransitionBase();
        if(tb != null){
            result = tb.findTransition(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the base imported by a descriptor base
        Edb db = getDescriptorBase();
        if(db != null){
            result = db.findTransition(alias, id);
            if(result != null)
                return result;
        }
        
        
        // search in the transition base of the imported documents
        Eib ib = getImportedDocumentBase();
        if(ib != null){
            result = ib.findTransition(alias, id);
            if(result != null)
                return result;
        }
        

        String msg = "Could not find transition with id: ";
        if(alias != null)
            msg += alias + "#";
        msg += id;
        throw new NCLParsingException(msg);
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        importedDocumentBase = null;
        ruleBase = null;
        transitionBase = null;
        descriptorBase = null;
        connectorBase = null;
        
        for(Erb rb : regionBases)
            rb.clean();
        
        for(Em m : metas)
            m.clean();
        
        for(Emt m : metadatas)
            m.clean();
    }
    

    /**
     * Function to create the child element <i>importedDocumentBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importedDocumentBase</i>.
     */
    protected Eib createImportedDocumentBase() throws XMLException {
        return (Eib) new NCLImportedDocumentBase();
    }


    /**
     * Function to create the child element <i>ruleBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>ruleBase</i>.
     */
    protected Erl createRuleBase() throws XMLException {
        return (Erl) new NCLRuleBase();
    }


    /**
     * Function to create the child element <i>transitionBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>transitionBase</i>.
     */
    protected Etb createTransitionBase() throws XMLException {
        return (Etb) new NCLTransitionBase();
    }


    /**
     * Function to create the child element <i>regionBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>regionBase</i>.
     */
    protected Erb createRegionBase() throws XMLException {
        return (Erb) new NCLRegionBase();
    }


    /**
     * Function to create the child element <i>descriptorBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorBase</i>.
     */
    protected Edb createDescriptorBase() throws XMLException {
        return (Edb) new NCLDescriptorBase();
    }


    /**
     * Function to create the child element <i>connectorBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connectorBase</i>.
     */
    protected Ecb createConnectorBase() throws XMLException {
        return (Ecb) new NCLConnectorBase();
    }


    /**
     * Function to create the child element <i>meta</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>meta</i>.
     */
    protected Em createMeta() throws XMLException {
        return (Em) new NCLMeta();
    }


    /**
     * Function to create the child element <i>metadata</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>metadata</i>.
     */
    protected Emt createMetadata() throws XMLException {
        return (Emt) new NCLMetadata();
    }
}
