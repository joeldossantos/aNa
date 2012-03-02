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
package br.uff.midiacom.ana.datatype.ncl.reuse;

import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.ana.datatype.ncl.structure.NCLDocPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.TreeSet;


public abstract class NCLImportPrototype<T extends NCLImportPrototype,
                                         P extends NCLElement,
                                         I extends NCLElementImpl,
                                         Er extends NCLRegionPrototype,
                                         Ed extends NCLDocPrototype>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P>, ReferredElement<ReferenceType> {

    protected StringType alias;
    protected SrcType documentURI;
    protected Er region;

    protected NCLImportType type;
    protected Ed importedDoc;
    protected TreeSet<ReferenceType> references;


    /**
     * Construtor do elemento de importação.
     * 
     * @param type
     *          tipo do elemento, importBase ou importNCL.
     * 
     * @throws XMLException
     *          se o tipo for nulo.
     */
    public NCLImportPrototype(NCLImportType type) throws XMLException {
        super();
        if(type == null)
            throw new XMLException("Null type");

        this.type = type;
        references = new TreeSet<ReferenceType>();
    }


    /**
     * Atribui um alias ao elemento de importação.
     *
     * @param alias
     *          String representando o alias.
     */
    public void setAlias(String alias) throws XMLException {
        StringType aux = this.alias;
        this.alias = new StringType(alias);
        impl.notifyAltered(NCLElementAttributes.ALIAS, aux, alias);
    }


    /**
     * Retorna o alias do elemento de importação.
     *
     * @return
     *          String representando o alias.
     */
    public String getAlias() {
        if(alias != null)
            return alias.getValue();
        else
            return null;
    }


    /**
     * Atribui o endereço do documento sendo importado.
     *
     * @param documentURI
     *          String representando o endereço.
     *
     * @throws java.net.URISyntaxException
     *          se o endereço não for válido.
     *
     * @see java.net.URI
     */
    public void setDocumentURI(SrcType documentURI) throws XMLException {
        SrcType aux = this.documentURI;
        this.documentURI = documentURI;
        impl.notifyAltered(NCLElementAttributes.DOCUMENTURI, aux, documentURI);
    }


    /**
     * Retorna o endereço do documento sendo importado.
     *
     * @return
     *          String representando o endereço.
     */
    public SrcType getDocumentURI() {
        return documentURI;
    }


    /**
     * Atribui uma região ao importador.
     *
     * @param region
     *          elemento representando a região associada.
     */
    public void setRegion(Er region) {
        Er aux = this.region;
        this.region = region;
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
    }


    /**
     * Retorna a região associada ao importador.
     *
     * @return
     *          elemento representando a região associada.
     */
    public Er getRegion() {
        return region;
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
    public boolean addReference(ReferenceType reference) {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) {
        return references.remove(reference);
    }
    
    
    @Override
    public TreeSet<ReferenceType> getReferences() {
        return references;
    }


    @Override
    public boolean compare(T other) {
        return getAlias().equals(other.getAlias());
    }
}
