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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLNamespace;
import br.uff.midiacom.ana.datatype.ncl.structure.NCLDocPrototype;
import br.uff.midiacom.xml.XMLException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class NCLDoc<T extends NCLDoc,
                    P extends NCLElement,
                    I extends NCLElementImpl,
                    Eh extends NCLHead,
                    Eb extends NCLBody>
        extends NCLDocPrototype<T, P, I, Eh, Eb>
        implements NCLIdentifiableElement<T, P> {

    
    public NCLDoc() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        // XML document start declaration
        content = space + "<?xml version='1.0' encoding='ISO-8859-1'?>\n";

        content += space + "<!-- Generated with NCL API -->\n\n";

        // <ncl> element and attributes declaration
        content += space + "<ncl";
        content += parseAttributes();
        content += ">\n";

        // <ncl> element content
        content += parseElements(ident + 1);

        // <ncl> element end declaration
        content += space + "</ncl>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseTitle();
        content += parseXmlns();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseHead(ident);
        content += parseBody(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseTitle() {
        String aux = getTitle();
        if(aux != null)
            return " title='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseXmlns() {
        NCLNamespace aux = getXmlns();
        if(aux != null)
            return " xmlns='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseHead(int ident) {
        Eh aux = getHead();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected String parseBody(int ident) {
        Eb aux = getBody();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }


    /**
     * Loads the objects structure representing an NCL document from an XML file.
     *
     * @param xmlFile
     *          file with the NCL document content.
     * @throws NCLParsingException
     *          if an error occur while parsing the document.
     */
    public void loadXML(File xmlFile) throws XMLException {
        // Store the file name and location
        location = xmlFile.getParent();
        fileName = xmlFile.getName();
        
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.parse(xmlFile);
            load(doc.getDocumentElement());
        }catch(SAXException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }catch(ParserConfigurationException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }catch(IOException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var, ch_name;
        Element el;

        try{
            // set the id (optional)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);

            // set the title (optional)
            att_name = NCLElementAttributes.TITLE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setTitle(att_var);

            // set the xmlns (optional)
            att_name = NCLElementAttributes.XMLNS.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setXmlns(NCLNamespace.getEnumType(att_var));
        }
        catch(XMLException ex){
            throw new NCLParsingException("NCLDocument:\n" + ex.getMessage());
        }

        try{
            // create the head
            ch_name = NCLElementAttributes.HEAD.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Eh inst = createHead();
                setHead(inst);
                inst.load(el);
            }

            // create the body
            ch_name = NCLElementAttributes.BODY.toString();
            el = (Element) element.getElementsByTagName(ch_name).item(0);
            if(el != null){
                Eb inst = createBody();
                setBody(inst);
                inst.load(el);
            }

            // fix the references needed
            NCLReferenceManager.getInstance().fixReferences();
        }
        catch(XMLException ex){
            throw new NCLParsingException("Error pasring " + ex.getMessage());
        }
    }


    /**
     * Function to create the child element <i>head</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>head</i>.
     */
    protected Eh createHead() throws XMLException {
        return (Eh) new NCLHead();
    }


    /**
     * Function to create the child element <i>body</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>body</i>.
     */
    protected Eb createBody() throws XMLException {
        return (Eb) new NCLBody();
    }
}
