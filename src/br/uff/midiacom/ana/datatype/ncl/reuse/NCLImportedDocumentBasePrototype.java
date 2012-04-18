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
package br.uff.midiacom.ana.datatype.ncl.reuse;

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


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
public abstract class NCLImportedDocumentBasePrototype<T extends NCLImportedDocumentBasePrototype,
                                                       P extends NCLElement,
                                                       I extends NCLElementImpl,
                                                       Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected ElementList<Ei, T> imports;


    /**
     * Base of imported documents constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLImportedDocumentBasePrototype() throws XMLException {
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
}
