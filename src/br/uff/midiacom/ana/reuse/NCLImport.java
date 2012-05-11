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
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import java.io.File;
import java.net.URI;
import org.w3c.dom.Element;


public class NCLImport<T extends NCLImport,
                       P extends NCLElement,
                       I extends NCLElementImpl,
                       Er extends RegionReference,
                       Ed extends NCLDoc>
        extends NCLImportPrototype<T, P, I, Er, Ed>
        implements NCLElement<T, P> {


    public NCLImport(NCLImportType type) throws XMLException {
        super(type);
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

        content = space + "<" + type.toString();
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


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
            
            throw new NCLParsingException(type.toString() + aux + ":\n" + ex.getMessage());
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
    
    
    protected String parseRegion() {
        Er aux = getRegion();
        if(aux != null)
            return " region='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRegion(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setRegion((Er) NCLReferenceManager.getInstance().findRegionReference(impl.getDoc(), att_var));
        }
    }


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
