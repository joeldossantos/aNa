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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.reference.ReferredElement;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.io.File;
import java.net.URI;
import org.w3c.dom.Element;


/**
 * Class that represents an element used to import a base defined in an external
 * document (importBase) or an element used to import a document (importNCL).
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>alias</i> - name used to refer to the document referred in the attribute
 *                     documentURI. This attribute is required.</li>
 *  <li><i>documentURI</i> - URI of the document to be imported (if it is an
 *                           importNCL element) or the document that will have
 *                           its base imported (if it is an importBase element).
 *                           This attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Er>
 * @param <Ed> 
 */
public abstract class NCLImport<T extends NCLImport,
                                P extends NCLElement,
                                I extends NCLElementImpl,
                                Ed extends NCLDoc>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P>, ReferredElement<P> {

    protected StringType alias;
    protected SrcType documentURI;

    protected Ed importedDoc;
    protected ElementList<P, P> references;


    /**
     * Import element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLImport() throws XMLException {
        super();
        references = new ElementList<P, P>();
    }


    /**
     * Sets the name used to refer to the document referred in the attribute
     * documentURI. This attribute is required and can not be set to <i>null</i>.
     * 
     * @param alias
     *          string representing the name used to refer to the document
     *          referred in the attribute documentURI.
     * @throws XMLException 
     *          if the string is empty or null.
     */
    public void setAlias(String alias) throws XMLException {
        if(alias == null)
            throw new XMLException("Null alias");
        
        StringType aux = this.alias;
        this.alias = new StringType(alias);
        impl.notifyAltered(NCLElementAttributes.ALIAS, aux, alias);
    }


    /**
     * Returns the name used to refer to the document referred in the attribute
     * documentURI or <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          string representing the name used to refer to the document
     *          referred in the attribute documentURI or <i>null</i> if the
     *          attribute is not defined.
     */
    public String getAlias() {
        if(alias != null)
            return alias.getValue();
        else
            return null;
    }


    /**
     * Sets the URI of the document to be imported (if it is an importNCL element)
     * or the document that will have its base imported (if it is an importBase
     * element). This attribute is required and can not be set to <i>null</i>.
     * 
     * @param documentURI
     *          element representing a URI.
     * @throws XMLException 
     *          if the URI is null.
     */
    public void setDocumentURI(SrcType documentURI) throws XMLException {
        if(documentURI == null)
            throw new XMLException("Null documentURI.");
        
        SrcType aux = this.documentURI;
        this.documentURI = documentURI;
        impl.notifyAltered(NCLElementAttributes.DOCUMENTURI, aux, documentURI);
    }


    /**
     * Returns the URI of the document to be imported (if it is an importNCL element)
     * or the document that will have its base imported (if it is an importBase
     * element) or <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          element representing a URI or <i>null</i> if the attribute is not
     *          defined.
     */
    public SrcType getDocumentURI() {
        return documentURI;
    }
    
    
    /**
     * Sets the document imported by the import element.
     * 
     * @param importedDoc
     *          document imported.
     * @throws XMLException 
     *          if the import element does not import a document.
     */
    public void setImportedDoc(Ed importedDoc) {
        this.importedDoc = importedDoc;
    }
    
    
    /**
     * Returns the document imported by the import element.
     * 
     * @return 
     *          document imported.
     */
    public Ed getImportedDoc() {
        return importedDoc;
    }


    @Override
    public boolean compare(T other) {
        return getAlias().equals(other.getAlias());
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

        content = space + "<" + getType();
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadAlias(element);
            loadDocumentURI(element);
            loadRegion(element);
            
            // load the imported document or base depending on the element type
            try{
                Ed aux = createDoc();
                URI base = new URI(impl.getDoc().getLocation() + File.separator);
                URI path = base.resolve(getDocumentURI().parse());
                aux.loadXML(new File(path.getPath()));
                setImportedDoc(aux);
                impl.getDoc().mergeGlobalVariables(aux);
            }catch(Exception e){
                throw new NCLParsingException("Could not find document in location: " + getDocumentURI().parse());
            }
        }
        catch(XMLException ex){
            String aux = getAlias();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException(getType() + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseAlias();
        content += parseDocumentURI();
        content += parseRegion();
        
        return content;
    }
    
    
    protected String parseAlias() {
        String aux = getAlias();
        if(aux != null)
            return " alias='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadAlias(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the alias (required)
        att_name = NCLElementAttributes.ALIAS.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setAlias(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseDocumentURI() {
        SrcType aux = getDocumentURI();
        if(aux != null)
            return " documentURI='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadDocumentURI(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the documentURI (required)
        att_name = NCLElementAttributes.DOCUMENTURI.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDocumentURI(new SrcType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected abstract String parseRegion();
    
    
    protected abstract void loadRegion(Element element) throws XMLException;
    
    
    @Override
    public boolean addReference(P reference) throws XMLException {
        return references.add(reference, null);
    }
    
    
    @Override
    public boolean removeReference(P reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ElementList getReferences() {
        return references;
    }
    
    
    protected abstract String getType();


    /**
     * Function to create a document element.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the document element.
     */
    protected Ed createDoc() throws XMLException {
        return (Ed) new NCLDoc();
    }
}
