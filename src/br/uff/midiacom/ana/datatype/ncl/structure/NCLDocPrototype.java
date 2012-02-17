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
package br.uff.midiacom.ana.datatype.ncl.structure;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLNamespace;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;


public abstract class NCLDocPrototype<T extends NCLDocPrototype,
                                      P extends NCLElement,
                                      I extends NCLElementImpl,
                                      Eh extends NCLHeadPrototype,
                                      Eb extends NCLBodyPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P> {

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
        
        String aux = this.title;
        this.title = title;
        impl.notifyAltered(NCLElementAttributes.TITLE, aux, title);
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

        NCLNamespace aux = this.xmlns;
        this.xmlns = xmlns;
        impl.notifyAltered(NCLElementAttributes.XMLNS, aux, xmlns);
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
            impl.notifyRemoved(NCLElementSets.HEAD, this.head);
        }

        this.head = head;
        //Se head existe, atribui este como seu parente
        if(this.head != null){
            this.head.setParent(this);
            impl.notifyInserted(NCLElementSets.HEAD, this.head);
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
            impl.notifyRemoved(NCLElementSets.BODY, this.body);
        }

        this.body = body;
        //Se body existe, atribui este como seu parente
        if(this.body != null){
            this.body.setParent(this);
            impl.notifyInserted(NCLElementSets.BODY, this.body);
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
}
