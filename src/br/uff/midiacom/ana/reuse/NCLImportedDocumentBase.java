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
package br.uff.midiacom.ana.reuse;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Class that represents a base of imported documents.
 * 
 * <br/>
 * 
 * All bases defined in an imported document, as well as its body are imported.
 * The bases defined in the imported document are treated as if they were imported
 * by a base in the NCL document head. The body of the imported document is treated
 * as a context element.
 * 
 * <br/>
 * 
 * The body of the imported document is not included in the NCL document body. The
 * elements defined in the imported document body just become avaliable to be
 * reused inside the NCL document that imports it.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of imported documents. This attribute is
 *                  optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importNCL</i> - element that imports an NCL document. The base must have
 *                         at least one import element.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ei> 
 */
public class NCLImportedDocumentBase<T extends NCLElement,
                                     Ei extends NCLImportNCL,
                                     R extends ExternalReferenceType>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLElement<T> {

    protected ElementList<Ei> imports;


    /**
     * Base of imported documents constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLImportedDocumentBase() throws XMLException {
        super();
        imports = new ElementList<Ei>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ei aux : imports) {
            aux.setDoc(doc);
        }
    }


    /**
     * Adds an element that imports an NCL document to the base of imported
     * documents. The base must have at least one import element.
     * 
     * @param importNCL
     *          element that imports an NCL document.
     * @return
     *          true if the import element was added.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean addImportNCL(Ei importNCL) throws XMLException {
        if(imports.add(importNCL)){
            notifyInserted((T) importNCL);
            importNCL.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element that imports an NCL document of the base of imported
     * documents. The base must have at least one import element.
     * 
     * @param importNCL
     *          element that imports an NCL document.
     * @return
     *          true if the import element was removed.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean removeImportNCL(Ei importNCL) throws XMLException {
        if(!importNCL.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(imports.remove(importNCL)){
            notifyRemoved((T) importNCL);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of imported documents has a specific element that
     * imports an NCL document. The base must have at least one import element.
     * 
     * @param importNCL
     *          element that imports an NCL document.
     * @return
     *          true if the base of imported documents has the import element.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean hasImportNCL(Ei importNCL) throws XMLException {
        return imports.contains(importNCL);
    }


    /**
     * Verifies if the base of imported documents has at least one element that
     * imports an NCL document. The base must have at least one import element.
     * 
     * @return 
     *          true if the base of imported documents has at least import element.
     */
    public boolean hasImportNCL() {
        return !imports.isEmpty();
    }


    /**
     * Returns the list of elements that imports an NCL document. The base can
     * have none or several import elements.
     * 
     * @return 
     *          element list with all import elements.
     */
    public ElementList<Ei> getImportNCLs() {
        return imports;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLImportedDocumentBase))
            return false;
        
        boolean result = true;
        ElementList<Ei> otherimp = ((NCLImportedDocumentBase) other).getImportNCLs();
        
        String aux = getId();
        if(aux != null)
            result &= aux.equals(((NCLImportedDocumentBase) other).getId());
        
        result &= imports.size() == otherimp.size();
        for (Ei imp : imports) {
            try {
                result &= otherimp.contains(imp);
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

        content = space + "<importedDocumentBase";
        content += parseAttributes();

        if(hasImportNCL()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</importedDocumentBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("ImportedDocumentBase:\n" + ex.getMessage());
        }

        try{
            loadImportNCL(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("ImportedDocumentBase > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportNCL(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
    }
    
    
    protected String parseImportNCL(int ident) {
        if(!hasImportNCL())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadImportNCL(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the child nodes
        ch_name = NCLElementAttributes.IMPORTNCL.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ei inst = createImportNCL();
            addImportNCL(inst);
            inst.load(el);
        }
    }
    
    
    /**
     * Searches for a connector inside the imported documents.
     * 
     * @param alias
     *          alias of the importBase the imports the connector.
     * @param id
     *          id of the connector to be found.
     * @return 
     *          connector or null if no connector was found.
     */
    public Object findConnector(String alias, String id) throws XMLException {
        for(Ei imp : imports){
            if(imp.getAlias().equals(alias)){
                NCLDoc d = (NCLDoc) imp.getImportedDoc();
                Object ref = d.getHead().findConnector(null, id);
                if(ref instanceof NCLCausalConnector)
                    return createExternalRef(imp, (NCLCausalConnector) ref);
                else
                    return createExternalRef(imp, (NCLCausalConnector) ((R) ref).getTarget());
            }
        }
        
        return null;
    }
    
    
    /**
     * Searches for a descriptor inside the imported documents.
     * 
     * @param alias
     *          alias of the importBase the imports the descriptor.
     * @param id
     *          id of the descriptor to be found.
     * @return 
     *          descriptor or null if no descriptor was found.
     */
    public Object findDescriptor(String alias, String id) throws XMLException {
        for(Ei imp : imports){
            if(imp.getAlias().equals(alias)){
                NCLDoc d = (NCLDoc) imp.getImportedDoc();
                Object ref = d.getHead().findDescriptor(null, id);
                if(ref instanceof NCLLayoutDescriptor)
                    return createExternalRef(imp, (NCLLayoutDescriptor) ref);
                else
                    return createExternalRef(imp, (NCLLayoutDescriptor) ((R) ref).getTarget());
            }
        }
        
        return null;
    }
    
    
    /**
     * Searches for a descriptor inside the imported documents.
     * 
     * @param focusIndex
     *          focusIndex of the descriptor to be found.
     * @return 
     *          descriptor or null if no descriptor was found.
     */
    public Object findDescriptor(Integer focusIndex) throws XMLException {
        for(Ei imp : imports){
            NCLDoc d = (NCLDoc) imp.getImportedDoc();
            Object ref = d.getHead().findDescriptor(focusIndex);
            if(ref != null)
                return ref;
        }
        
        return null;
    }
    
    
    /**
     * Searches for a region inside the imported documents.
     * 
     * @param alias
     *          alias of the importBase the imports the region.
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public Object findRegion(String alias, String id) throws XMLException {
        for(Ei imp : imports){
            if(imp.getAlias().equals(alias)){
                NCLDoc d = (NCLDoc) imp.getImportedDoc();
                Object ref = d.getHead().findRegion(null, null, id);
                if(ref instanceof NCLRegion)
                    return createExternalRef(imp, (NCLRegion) ref);
                else
                    return createExternalRef(imp, (NCLRegion) ((R) ref).getTarget());
            }
        }
        
        return null;
    }
    
    
    /**
     * Searches for a rule inside the imported documents.
     * 
     * @param alias
     *          alias of the importBase the imports the rule.
     * @param id
     *          id of the rule to be found.
     * @return 
     *          rule or null if no rule was found.
     */
    public Object findRule(String alias, String id) throws XMLException {
        for(Ei imp : imports){
            if(imp.getAlias().equals(alias)){
                NCLDoc d = (NCLDoc) imp.getImportedDoc();
                Object ref = d.getHead().findRule(null, id);
                if(ref instanceof NCLTestRule)
                    return createExternalRef(imp, (NCLTestRule) ref);
                else
                    return createExternalRef(imp, (NCLTestRule) ((R) ref).getTarget());
            }
        }
        
        return null;
    }
    
    
    /**
     * Searches for a transition inside the imported documents.
     * 
     * @param alias
     *          alias of the importBase the imports the transition.
     * @param id
     *          id of the transition to be found.
     * @return 
     *          transition or null if no transition was found.
     */
    public Object findTransition(String alias, String id) throws XMLException {
        for(Ei imp : imports){
            if(imp.getAlias().equals(alias)){
                NCLDoc d = (NCLDoc) imp.getImportedDoc();
                Object ref = d.getHead().findTransition(null, id);
                if(ref instanceof NCLTransition)
                    return createExternalRef(imp, (NCLTransition) ref);
                else
                    return createExternalRef(imp, (NCLTransition) ((R) ref).getTarget());
            }
        }
        
        return null;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        for(Ei i : imports)
            i.clean();
    }
    

    /**
     * Function to create the child element <i>importNCL</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importNCL</i>.
     */
    protected Ei createImportNCL() throws XMLException {
        return (Ei) new NCLImportNCL();
    }


    /**
     * Function to create a reference to an NCL element.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to an NCL element.
     */
    protected R createExternalRef(Ei imp, ReferredElement ref) throws XMLException {
        return (R) new ExternalReferenceType(imp, ref);
    }
}
