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
package br.uff.midiacom.ana.reuse;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLModificationListener;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLImport<T extends NCLImport, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegion>
        extends NCLImportPrototype<T, P, I, Er> implements NCLElement<T, P> {


    public NCLImport(NCLImportType type) throws XMLException {
        super(type);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setAlias(String alias) throws XMLException {
        StringType aux = this.alias;
        super.setAlias(alias);
        impl.notifyAltered(NCLElementAttributes.ALIAS, aux, alias);
    }


    @Override
    public void setDocumentURI(SrcType documentURI) throws XMLException{
        SrcType aux = this.documentURI;
        super.setDocumentURI(documentURI);
        impl.notifyAltered(NCLElementAttributes.DOCUMENTURI, aux, documentURI);
    }


    @Override
    public void setRegion(Er region) {
        Er aux = this.region;
        super.setRegion(region);
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;

        try{
            // set the alias (required)
            att_name = NCLElementAttributes.ALIAS.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setAlias(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the documentURI (required)
            att_name = NCLElementAttributes.DOCUMENTURI.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDocumentURI(new SrcType(att_var));
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the region (optional)
            att_name = NCLElementAttributes.REGION.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Er reg = (Er) NCLReferenceManager.getInstance().findRegionReference(impl.getDoc(), att_var);
                setRegion(reg);
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


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
