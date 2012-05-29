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

import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
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
public class NCLHead<T extends NCLHead,
                     P extends NCLElement,
                     I extends NCLElementImpl,
                     Eib extends NCLImportedDocumentBase,
                     Erl extends NCLRuleBase,
                     Etb extends NCLTransitionBase,
                     Erb extends NCLRegionBase,
                     Edb extends NCLDescriptorBase,
                     Ecb extends NCLConnectorBase,
                     Em extends NCLMeta,
                     Emt extends NCLMetadata>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected Eib importedDocumentBase;
    protected Erl ruleBase;
    protected Etb transitionBase;
    protected IdentifiableElementList<Erb, T> regionBases;
    protected Edb descriptorBase;
    protected Ecb connectorBase;
    protected ElementList<Em, T> metas;
    protected ElementList<Emt, T> metadatas;


    /**
     * Head element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLHead() throws XMLException {
        super();
        regionBases = new IdentifiableElementList<Erb, T>();
        metas = new ElementList<Em, T>();
        metadatas = new ElementList<Emt, T>();
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
    public void setImportedDocumentBase(Eib importedDocumentBase) {
        // Remove the parent of the actual base, if exists
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(null);
            impl.notifyRemoved(NCLElementSets.IMPORTEDDOCUMENTBASE, this.importedDocumentBase);
        }
        // Add the new base element
        this.importedDocumentBase = importedDocumentBase;
        // Set the parent of the new base, if it exists
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(this);
            impl.notifyInserted(NCLElementSets.IMPORTEDDOCUMENTBASE, this.importedDocumentBase);
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
    public void setRuleBase(Erl ruleBase) {
        // Remove the parent of the actual base, if exists
        if(this.ruleBase != null){
            this.ruleBase.setParent(null);
            impl.notifyRemoved(NCLElementSets.RULEBASE, this.ruleBase);
        }
        // Add the new base element
        this.ruleBase = ruleBase;
        // Set the parent of the new base, if it exists
        if(this.ruleBase != null){
            this.ruleBase.setParent(this);
            impl.notifyInserted(NCLElementSets.RULEBASE, this.ruleBase);
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
    public void setTransitionBase(Etb transitionBase) {
        // Remove the parent of the actual base, if exists
        if(this.transitionBase != null){
            this.transitionBase.setParent(null);
            impl.notifyRemoved(NCLElementSets.TRANSITIONBASE, this.transitionBase);
        }
        // Add the new base element
        this.transitionBase = transitionBase;
        // Set the parent of the new base, if it exists
        if(this.transitionBase != null){
            this.transitionBase.setParent(this);
            impl.notifyInserted(NCLElementSets.TRANSITIONBASE, this.transitionBase);
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
        if(regionBases.add(regionBase, (T) this)){
            impl.notifyInserted(NCLElementSets.REGIONBASE, regionBase);
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
            impl.notifyRemoved(NCLElementSets.REGIONBASE, regionBase);
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
        if(regionBases.remove(id)){
            impl.notifyRemoved(NCLElementSets.REGIONBASE, id);
            return true;
        }
        return false;
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
    public IdentifiableElementList<Erb, T> getRegionBases() {
        return regionBases;
    }


    /**
     * Adds a base of descriptors to the head. The base of descriptors is
     * optional. Set the base to <i>null</i> to remove the base already defined.
     *
     * @param descriptorBase
     *          element representing the base of descriptors or <i>null</i> to
     *          remove the base already defined.
     */    
    public void setDescriptorBase(Edb descriptorBase) {
        // Remove the parent of the actual base, if exists
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(null);
            impl.notifyRemoved(NCLElementSets.DESCRIPTORBASE, this.descriptorBase);
        }
        // Add the new base element
        this.descriptorBase = descriptorBase;
        // Set the parent of the new base, if it exists
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(this);
            impl.notifyInserted(NCLElementSets.DESCRIPTORBASE, this.descriptorBase);
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
    public void setConnectorBase(Ecb connectorBase) {
        // Remove the parent of the actual base, if exists
        if(this.connectorBase != null){
            this.connectorBase.setParent(null);
            impl.notifyRemoved(NCLElementSets.CONNECTORBASE, this.connectorBase);
        }
        // Add the new base element
        this.connectorBase = connectorBase;
        // Set the parent of the new base, if it exists
        if(this.connectorBase != null){
            this.connectorBase.setParent(this);
            impl.notifyInserted(NCLElementSets.CONNECTORBASE, this.connectorBase);
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
        if(metas.add(meta, (T) this)){
            impl.notifyInserted(NCLElementSets.METAS, meta);
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
            impl.notifyRemoved(NCLElementSets.METAS, meta);
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
    public ElementList<Em, T> getMetas() {
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
        if(metadatas.add(metadata, (T) this)){
            impl.notifyInserted(NCLElementSets.METADATAS, metadata);
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
            impl.notifyRemoved(NCLElementSets.METADATAS, metadata);
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
    public ElementList<Emt, T> getMetadatas() {
        return metadatas;
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
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
