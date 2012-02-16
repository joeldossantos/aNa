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

import br.uff.midiacom.ana.datatype.enums.NCLNamespace;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;


public class NCLDocPrototype<T extends NCLDocPrototype, P extends NCLElement, I extends NCLElementImpl, Eh extends NCLHeadPrototype, Eb extends NCLBodyPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {

    protected String title;
    protected NCLNamespace xmlns;
    protected Eh head;
    protected Eb body;


    public NCLDocPrototype() throws XMLException {
        super();
    }

    
    /**
     * Determina o título do documento NCL.
     * 
     * @param title
     *          String contendo o título a ser atribuído ao documento.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o título a ser atribuído for uma String vazia.
     */
    public void setTitle(String title) throws XMLException {
        if(title != null && "".equals(title.trim()))
            throw new XMLException("Empty title String");
        
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
    public void setXmlns(NCLNamespace xmlns) throws XMLException {
        if(xmlns == null)
            throw new XMLException("Null xmlns.");

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
    public void setHead(Eh head) {
        //Retira o parentesco do head atual
        if(this.head != null){
            this.head.setParent(null);
        }

        this.head = head;
        //Se head existe, atribui este como seu parente
        if(this.head != null){
            this.head.setParent(this);
        }
    }


    /**
     * Retorna o cabeçalho do documento NCL.
     * 
     * @return
     *          elemento representando o cabeçalho do documento NCL.
     */
    public Eh getHead() {
        return head;
    }


    /**
     * Atribui um corpo ao documento NCL.
     * 
     * @param body
     *          elemento representando o corpo do documento NCL.
     */
    public void setBody(Eb body) {
        //Retira o parentesco do body atual
        if(this.body != null){
            this.body.setParent(null);
        }

        this.body = body;
        //Se body existe, atribui este como seu parente
        if(this.body != null){
            this.body.setParent(this);
        }
    }


    /**
     * Retorna o corpo de um documento NCL.
     * 
     * @return
     *          elemento representando o cabeçalho do documento NCL.
     */
    public Eb getBody() {
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
}
