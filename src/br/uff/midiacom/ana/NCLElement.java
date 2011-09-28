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
import br.uff.midiacom.ana.datatype.enums.NCLNotifier;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;


/**
 * Esta classe define um elemento da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public abstract class NCLElement extends DefaultHandler implements Element {

    private NCLElement parent;
    private XMLReader reader;
    private NCLModificationListener listener;
    private List<String> warnings = new ArrayList<String>();
    private List<String> errors = new ArrayList<String>();


    /**
     * Atribui um elemento pai ao elemento NCL.
     *
     * @param parent
     *          elemento NCL representando o elemento pai.
     * @return
     *          verdadeiro se o elemento pai foi atribuido. Caso o elemento já possua um elemento pai, o retorno será falso.
     */
    public boolean setParent(NCLElement parent) {
        if(this.parent != null && parent != null)
            return false;

        this.parent = parent;
        return true;
    }


    /**
     * Retorna o elemento pai do elemento NCL.
     *
     * @return
     *          elemento NCL representando o elemento pai.
     */
    public NCLElement getParent() {
        return parent;
    }


    /**
     * Atribui um leitor XML ao elemento NCL.
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @return
     *          verdadeiro se o leitor XML foi atribuido. Caso o elemento já possua um leitor, o retorno será falso.
     */
    public boolean setReader(XMLReader reader) {
        if(this.reader != null && reader != null)
            return false;

        this.reader = reader;
        return true;
    }


    /**
     * Retorna o leitor XML do elemento NCL.
     *
     * @return
     *          elemento representando o leitor XML do parser SAX.
     */
    public XMLReader getReader() {
        return reader;
    }


    /**
     * Atribui um ouvinte para notificações de mudança do elemento. Caso o
     * argumento seja nulo, não utilizará nenhum ouvinte.
     *
     * @param listener
     *          objeto que receberá as notificações.
     */
    public void setModificationListener(NCLModificationListener listener) {
        this.listener = listener;
    }


    /**
     * Retorna o ouvinte para notificações de mudança do elemento.
     *
     * @return
     *          objeto que recebe as notificações ou null se nenhum ouvinte
     *          estiver assiciado.
     */
    public NCLModificationListener getModificationListener() {
        return listener;
    }


    /**
     * Cria o código XML do elemento da <i>Nested Context Language</i> (NCL).<br>
     * 
     * @param ident 
     *          Inteiro indicando o nível de indentação do elemento, ou seja,
     *          o número de tabulações usadas na indentação do elemento.
     *
     * @return
     *          String contendo o código XML do elemento.
     */
    public abstract String parse(int ident);


    /**
     * Adiciona uma mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     * 
     * @param warning
     *          String contendo a mensagem de aviso.
     */
    public void addWarning(String warning) {
        warnings.add(warning);
    }


    /**
     * Adiciona mensagens de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param warnings
     *          Lista contendo mensagens de aviso.
     */
    public void addWarning(List<String> warnings) {
        for(String warning : warnings)
            addWarning(warning);
    }


    /**
     * Retorna a mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de aviso.
     */
    public List<String> getWarnings() {
        return warnings;
    }


    /**
     * Limpa a lista de mensagens de aviso.
     */
    public void cleanWarnings() {
        warnings.clear();
    }


    /**
     * Adiciona uma mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param error
     *          String contendo a mensagem de erro.
     */
    public void addError(String error) {
        errors.add(error);
    }


    /**
     * Adiciona mensagens de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param errors
     *          Lista contendo mensagens de erro.
     */
    public void addError(List<String> errors) {
        for(String error : errors)
            addError(error);
    }


    /**
     * Retorna a mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de erro.
     */
    public List<String> getErrors() {
        return errors;
    }


    /**
     * Limpa a lista de mensagens de erro.
     */
    public void cleanErrors() {
        errors.clear();
    }


    /**
     * Implementa o método startElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    @Override
    public abstract void startElement(String uri, String localName, String qName, Attributes attributes);


    /**
     * Implementa o método endElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if(getParent() != null)
            getReader().setContentHandler(getParent());
    }


    /**
     * Implementa o método endDocument do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    @Override
    public void endDocument() {}


    /**
     * Notify the listener about a child node inserted.
     *
     * @param setName
     *          name of the child set.
     * @param inserted
     *          element inserted.
     */
    protected void notifyInserted(NCLElementSets setName, Element inserted) {
        if(listener != null)
            (new NCLNotifier(0, listener, this, setName, inserted)).start();
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param setName
     *          name of the child set.
     * @param inserted
     *          element removed.
     */
    protected void notifyRemoved(NCLElementSets setName, Element removed) {
        if(listener != null)
            (new NCLNotifier(1, listener, this, setName, removed)).start();
    }


    /**
     * Notify the listener about an attribute changed.
     *
     * @param attributeName
     *          the attribute changed.
     * @param oldValue
     *          the attribute old value.
     * @param newValue
     *          the attribute new value.
     */
    protected void notifyAltered(NCLElementAttributes attributeName, Object oldValue, Object newValue) {
        if(listener != null)
            (new NCLNotifier(listener, this, attributeName, oldValue, newValue)).start();
    }
}
