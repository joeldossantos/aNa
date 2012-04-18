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
import br.uff.midiacom.ana.datatype.ncl.structure.NCLHeadPrototype;
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
        extends NCLHeadPrototype<T, P, I, Eib, Erl, Etb, Erb, Edb, Ecb, Em, Emt>
        implements NCLElement<T, P> {

    
    public NCLHead() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
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
    
    
    protected String parseRuleBase(int ident) {
        Erl aux = getRuleBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected String parseTransitionBase(int ident) {
        Etb aux = getTransitionBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected String parseRegionBases(int ident) {
        if(!hasRegionBase())
            return "";
        
        String content = "";
        for(Erb aux : regionBases)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseDescriptorBase(int ident) {
        Edb aux = getDescriptorBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected String parseConnectorBase(int ident) {
        Ecb aux = getConnectorBase();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected String parseMetas(int ident) {
        if(!hasMeta())
            return "";
        
        String content = "";
        for(Em aux : metas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseMetadatas(int ident) {
        if(!hasMetadata())
            return "";
        
        String content = "";
        for(Emt aux : metadatas)
            content += aux.parse(ident);
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String ch_name;
        Element el;

        try{
            // create the importedDocumentBase
            ch_name = NCLElementAttributes.IMPORTEDDOCUMENTBASE.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Eib inst = createImportedDocumentBase();
                setImportedDocumentBase(inst);
                inst.load(el);
            }

            // create the ruleBase
            ch_name = NCLElementAttributes.RULEBASE.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Erl inst = createRuleBase();
                setRuleBase(inst);
                inst.load(el);
            }

            // create the transitionBase
            ch_name = NCLElementAttributes.TRANSITIONBASE.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Etb inst = createTransitionBase();
                setTransitionBase(inst);
                inst.load(el);
            }

            // create the child nodes (regionBases, metas and metadatas)
            NodeList nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    el = (Element) nl.item(i);

                    // create the regionBase
                    if(el.getTagName().equals(NCLElementAttributes.REGIONBASE.toString())){
                        Erb inst = createRegionBase();
                        addRegionBase(inst);
                        inst.load(el);
                    }
                    // create the meta
                    if(el.getTagName().equals(NCLElementAttributes.META.toString())){
                        Em inst = createMeta();
                        addMeta(inst);
                        inst.load(el);
                    }
                    // create the metadata
                    if(el.getTagName().equals(NCLElementAttributes.METADATA.toString())){
                        Emt inst = createMetadata();
                        addMetadata(inst);
                        inst.load(el);
                    }
                }
            }

            // create the descriptorBase
            ch_name = NCLElementAttributes.DESCRIPTORBASE.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Edb inst = createDescriptorBase();
                setDescriptorBase(inst);
                inst.load(el);
            }

            // create the connectorBase
            ch_name = NCLElementAttributes.CONNECTORBASE.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Ecb inst = createConnectorBase();
                setConnectorBase(inst);
                inst.load(el);
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("Head > " + ex.getMessage());
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
