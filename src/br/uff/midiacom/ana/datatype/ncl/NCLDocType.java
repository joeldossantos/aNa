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
package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLNamespace;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Esta classe define o elemento <i>ncl</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento raiz de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLDocType<H extends NCLHeadPrototype, B extends NCLBodyPrototype> extends NCLIdentifiableElementPrototype {

    private String title;
    private NCLNamespace xmlns; //atributo obrigatório

    private H head;
    private B body;

    
    /**
     * Determina o título do documento NCL.
     * 
     * @param title
     *          String contendo o título a ser atribuído ao documento.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o título a ser atribuído for uma String vazia.
     */
    public void setTitle(String title) throws IllegalArgumentException {
        if(title != null && "".equals(title.trim()))
            throw new IllegalArgumentException("Empty title String");
        
        notifyAltered(NCLElementAttributes.TITLE, this.title, title);
        this.title = title;
    }


    /**
     * Retorna o título do documento NCL.
     * 
     * @return
     *          String contendo o título do documento NCL.
     */
    public String getTitle() {
        return title;
    }


    /**
     * Determina o namespace utilizado pelo documento NCL.
     * 
     * @param xmlns
     *          namespace usado pelo documento NCL.
     */
    public void setXmlns(NCLNamespace xmlns) {
        notifyAltered(NCLElementAttributes.XMLNS, this.xmlns, xmlns);
        this.xmlns = xmlns;
    }


    /**
     * Retorna o namespace utilizado pelo documento NCL.
     * 
     * @return
     *          NCLNamespace representando o namespace usado pelo documento NCL.
     */
    public NCLNamespace getXmlns() {
        return xmlns;
    }


    /**
     * Atribui um cabeçalho ao documento NCL.
     * 
     * @param head
     *          elemento representando o cabeçalho do documento NCL.
     */
    public void setHead(H head) {
        //Retira o parentesco do head atual
        if(this.head != null){
            this.head.setParent(null);
            notifyRemoved(NCLElementSets.HEAD, this.head);
        }

        this.head = head;
        //Se head existe, atribui este como seu parente
        if(this.head != null){
            this.head.setParent(this);
            notifyInserted(NCLElementSets.HEAD, head);
        }
    }


    /**
     * Retorna o cabeçalho do documento NCL.
     * 
     * @return
     *          elemento representando o cabeçalho do documento NCL.
     */
    public H getHead() {
        return head;
    }


    /**
     * Atribui um corpo ao documento NCL.
     * 
     * @param body
     *          elemento representando o corpo do documento NCL.
     */
    public void setBody(B body) {
        //Retira o parentesco do body atual
        if(this.body != null){
            this.body.setParent(null);
            notifyRemoved(NCLElementSets.BODY, this.body);
        }

        this.body = body;
        //Se body existe, atribui este como seu parente
        if(this.body != null){
            this.body.setParent(this);
            notifyInserted(NCLElementSets.BODY, body);
        }
    }


    /**
     * Retorna o corpo de um documento NCL.
     * 
     * @return
     *          elemento representando o cabeçalho do documento NCL.
     */
    public B getBody() {
        return body;
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
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getTitle() != null)
            content += " title='" + getTitle() + "'";
        if(getXmlns() != null)
            content += " xmlns='" + getXmlns() + "'";
        content += ">\n";

        // <ncl> element content
        if(getHead() != null)
            content += getHead().parse(ident + 1);
        if(getBody() != null)
            content += getBody().parse(ident + 1);

        // <ncl> element end declaration
        content += space + "</ncl>\n";

        return content;
    }


    /**
     * Recupera a estrutura de classes que representam elementos NCL a partir
     * de um arquivo XML especificado de acordo com a linguagem NCL.
     *
     * @param path
     *          String contendo o caminho absoluto para o arquivo XML.
     * @throws NCLParsingException
     *          se algum erro ocorrer durante a recuperação do arquivo.
     */
    public void loadXML(File xmlFile) throws NCLParsingException {
        try{
            setReader(XMLReaderFactory.createXMLReader());

            getReader().setContentHandler(this);
            getReader().setErrorHandler(new NCLParsingErrorHandler(getReader()));

            FileReader r = new FileReader(xmlFile);
            getReader().parse(new InputSource(r));
        }
        catch(SAXException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(FileNotFoundException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(IOException ex){
            throw new NCLParsingException(ex.getMessage());
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("ncl")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("title"))
                        setTitle(attributes.getValue(i));
                }
                if(!uri.equals("")){
                    for(NCLNamespace ns : NCLNamespace.values()){
                        if(ns.toString().equals(uri))
                            setXmlns(ns);
                    }
                }
            }
            else if(localName.equals("head")){
                setHead(createHead());
                getHead().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("body")){
                setBody(createBody());
                getBody().startElement(uri, localName, qName, attributes);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getHead() != null){
            getHead().endDocument();
            addWarning(getHead().getWarnings());
            addError(getHead().getErrors());
        }
        if(getBody() != null){
            getBody().endDocument();
            addWarning(getBody().getWarnings());
            addError(getBody().getErrors());
        }
    }


    /**
     * Função de criação do elemento filho <i>head</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>head</i>.
     */
    protected H createHead() {
        return (H) new NCLHeadPrototype(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>body</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>body</i>.
     */
    protected B createBody() {
        return (B) new NCLBodyPrototype(getReader(), this);
    }
}
