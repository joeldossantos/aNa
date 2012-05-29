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

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
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
public class NCLImportedDocumentBase<T extends NCLImportedDocumentBase,
                                     P extends NCLElement,
                                     I extends NCLElementImpl,
                                     Ei extends NCLImport>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected ElementList<Ei, T> imports;


    /**
     * Base of imported documents constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLImportedDocumentBase() throws XMLException {
        super();
        imports = new ElementList<Ei, T>();
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
        if(imports.add(importNCL, (T) this)){
            impl.notifyInserted(NCLElementSets.IMPORTS, importNCL);
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
        if(imports.remove(importNCL)){
            impl.notifyRemoved(NCLElementSets.IMPORTS, importNCL);
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
    public ElementList<Ei, T> getImportNCLs() {
        return imports;
    }


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
     * Function to create the child element <i>importNCL</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importNCL</i>.
     */
    protected Ei createImportNCL() throws XMLException {
        return (Ei) new NCLImport(NCLImportType.NCL);
    }
}
