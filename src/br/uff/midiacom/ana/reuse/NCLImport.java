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
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento de importação da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLImport<T extends NCLImport, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegion>
        extends NCLImportPrototype<T, P, I, Er> implements NCLElement<T, P> {


    /**
     * Construtor do elemento de importação.
     * 
     * @param type
     *          tipo do elemento, importBase ou importNCL.
     * 
     * @throws java.lang.NullPointerException
     *          se o tipo for nulo.
     */
    public NCLImport(NCLImportType type) throws NullPointerException, XMLException {
        super(type);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Atribui um alias ao elemento de importação.
     *
     * @param alias
     *          String representando o alias.
     */
    @Override
    public void setAlias(String alias) throws XMLException {
        StringType aux = this.alias;
        super.setAlias(alias);
        impl.notifyAltered(NCLElementAttributes.ALIAS, aux, alias);
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
    @Override
    public void setDocumentURI(SrcType documentURI) throws XMLException{
        SrcType aux = this.documentURI;
        super.setDocumentURI(documentURI);
        impl.notifyAltered(NCLElementAttributes.DOCUMENTURI, aux, documentURI);
    }


    /**
     * Atribui uma região ao importador.
     *
     * @param region
     *          elemento representando a região associada.
     */
    @Override
    public void setRegion(Er region) {
        Er aux = this.region;
        super.setRegion(region);
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("alias"))
//                    setAlias(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("documentURI"))
//                    setDocumentURI(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("region")){
//                    setRegion((R) new NCLRegion(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//        catch(URISyntaxException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(getParent() == null)
//            return;
//
//        if(getRegion() != null)
//            regionReference();
//    }


    private void regionReference() {
        //Search for the interface inside the node
        NCLElementImpl head = getParent();

        while(!(head instanceof NCLHead)){
            head = head.getParent();
            if(head == null){
                addWarning("Could not find a head");
                return;
            }
        }

        if(!((NCLHead) head).hasRegionBase()){
            addWarning("Could not find a regionBase");
        }

        R reg = null;
        for(NCLRegionBase base : (Set<NCLRegionBase>) ((NCLHead) head).getRegionBases()){
            reg = findRegion(base.getRegions());
        }
        if(reg == null)
            addWarning("Could not find region in regionBase with id: " + getRegion().getId());

        setRegion(reg);
    }


    private R findRegion(Set<R> regions) {
        for(R reg : regions){
            if(reg.getId().equals(getRegion().getId()))
                return (R) reg;
            else if(reg.hasRegion())
            {
                R r = findRegion(reg.getRegions());
                if(r != null)
                    return (R) r;
            }
        }

        return null;
    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
//        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
//        return impl.getModificationListener();
    }
}
