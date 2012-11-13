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
package br.uff.midiacom.ana.util.ncl;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import org.w3c.dom.Element;


/**
 * Interface that represent a base of the NCL document head.
 * 
 * @param <T>
 * @param <P> 
 */
public abstract class NCLBase<T extends NCLElement,
                              Ei extends NCLImportBase>
        extends NCLIdentifiableElementPrototype<T> {

    protected ElementList<Ei> imports;
    
    
    public NCLBase() {
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
     * Adds an element that imports a base of rules defined in another NCL
     * document to the base of rules. The base can have none or several import
     * elements.
     * 
     * @param importBase
     *          element that imports a base of rules defined in another NCL
     *          document.
     * @return
     *          true if the import element was added.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(imports.add(importBase)){
            importBase.setParent(this);
            notifyInserted((T) importBase);
            return true;
        }
        return false;
    }


    /**
     * Removes an element that imports a base of rules defined in another NCL
     * document of the base of rules. The base can have none or several import
     * elements.
     * 
     * @param importBase
     *          element that imports a base of rules defined in another NCL
     *          document.
     * @return
     *          true if the import element was removed.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(!importBase.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(imports.remove(importBase)){
            notifyRemoved((T) importBase);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of rules has a specific element that imports a base
     * of rules defined in another NCL document. The base can have none or
     * several import elements.
     * 
     * @param importBase
     *          element that imports a base of rules defined in another NCL
     *          document.
     * @return
     *          true if the base of rules has the import element.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifies if the base of rules has at least one element that imports a base
     * of rules defined in another NCL document. The base can have none or
     * several import elements.
     * 
     * @return 
     *          true if the base of rules has at least import element.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Returns the list of elements that imports a base of rules defined in
     * another NCL document. The base can have none or several import elements.
     * 
     * @return 
     *          element list with all import elements.
     */
    public ElementList<Ei> getImportBases() {
        return imports;
    }
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadImportBases(Element element) throws XMLException {
        //create the imports
        if(element.getTagName().equals(NCLElementAttributes.IMPORTBASE.toString())){
            Ei inst = createImportBase();
            addImportBase(inst);
            inst.load(element);
        }
    }
    
    
    public boolean compareImports(NCLBase other) {
        boolean result = true;
        ElementList<Ei> otherimp = other.getImportBases();
        
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


    /**
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected Ei createImportBase() throws XMLException {
        return (Ei) new NCLImportBase();
    }
}
