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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLNamespace;
import br.uff.midiacom.ana.datatype.ncl.NCLDocPrototype;
import br.uff.midiacom.xml.XMLException;
import java.io.File;
import org.w3c.dom.Element;


public class NCLDoc<T extends NCLDoc, P extends NCLElement, I extends NCLElementImpl, Eh extends NCLHead, Eb extends NCLBody>
        extends NCLDocPrototype<T, P, I, Eh, Eb> implements NCLIdentifiableElement<T, P> {


    public NCLDoc() throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public void setTitle(String title) throws XMLException {
        String aux = this.title;
        super.setTitle(title);
        impl.notifyAltered(NCLElementAttributes.TITLE, aux, title);
    }


    @Override
    public void setXmlns(NCLNamespace xmlns) throws XMLException {
        NCLNamespace aux = this.xmlns;
        super.setXmlns(xmlns);
        impl.notifyAltered(NCLElementAttributes.XMLNS, aux, xmlns);
    }


    @Override
    public void setHead(Eh head) {
        Eh aux = this.head;
        super.setHead(head);
        impl.notifyAltered(NCLElementAttributes.HEAD, aux, head);
    }


    @Override
    public void setBody(Eb body) {
        Eb aux = this.body;
        super.setBody(body);
        impl.notifyAltered(NCLElementAttributes.BODY, aux, body);
    }


    /**
     * Loads the objects structure representing an NCL document from an XML file.
     *
     * @param xmlFile
     *          file with the NCL document content.
     * @throws NCLParsingException
     *          if an error occur while parsing the document.
     */
    public void loadXML(File xmlFile) throws NCLParsingException {
//        try{
//            setReader(XMLReaderFactory.createXMLReader());
//
//            getReader().setContentHandler(this);
//            getReader().setErrorHandler(new NCLParsingErrorHandler(getReader()));
//
//            FileReader r = new FileReader(xmlFile);
//            getReader().parse(new InputSource(r));
//        }
//        catch(SAXException ex){
//            throw new NCLParsingException(ex.getMessage());
//        }
//        catch(FileNotFoundException ex){
//            throw new NCLParsingException(ex.getMessage());
//        }
//        catch(IOException ex){
//            throw new NCLParsingException(ex.getMessage());
//        }
    }


//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("ncl")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("title"))
//                        setTitle(attributes.getValue(i));
//                }
//                if(!uri.equals("")){
//                    for(NCLNamespace ns : NCLNamespace.values()){
//                        if(ns.toString().equals(uri))
//                            setXmlns(ns);
//                    }
//                }
//            }
//            else if(localName.equals("head")){
//                setHead(createHead());
//                getHead().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("body")){
//                setBody(createBody());
//                getBody().startElement(uri, localName, qName, attributes);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    public void endDocument() {
//        if(getHead() != null){
//            getHead().endDocument();
//            addWarning(getHead().getWarnings());
//            addError(getHead().getErrors());
//        }
//        if(getBody() != null){
//            getBody().endDocument();
//            addWarning(getBody().getWarnings());
//            addError(getBody().getErrors());
//        }
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>head</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>head</i>.
     */
    protected NCLHead createHead() throws XMLException {
        return new NCLHead();
    }


    /**
     * Function to create the child element <i>body</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>body</i>.
     */
    protected NCLBody createBody() throws XMLException {
        return new NCLBody();
    }
}
