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
package br.uff.midiacom.ana.transition;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLImportType;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma base de transições da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLTransitionBase<T extends NCLTransition, I extends NCLImport> extends NCLIdentifiableElement {

    private Set<T> transitions = new TreeSet<T>();
    private Set<I> imports = new TreeSet<I>();


    /**
     * Construtor do elemento <i>transitionBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLTransitionBase() {}


    /**
     * Construtor do elemento <i>transitionBase</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLTransitionBase(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Adiciona uma transição a base de transições.
     *
     * @param transition
     *          elemento representando a transição a ser adicionada.
     * @return
     *          verdadeiro se a transição foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addTransition(T transition) {
        if(transitions.add(transition)){
            //Se transition existe, atribui este como seu parente
            if(transition != null)
                transition.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove uma transição da base de transições.
     *
     * @param transition
     *          elemento representando a transição a ser removida.
     * @return
     *          verdadeiro se a transição foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeTransition(T transition) {
        if(transitions.remove(transition)){
            //Se transition existe, retira o seu parentesco
            if(transition != null)
                transition.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se a base de transições possui uma transição.
     *
     * @param transition
     *          elemento representando a transição a ser verificada.
     * @return
     *          verdadeiro se a transição existir.
     */
    public boolean hasTransition(T transition) {
        return transitions.contains(transition);
    }


    /**
     * Verifica se a base de transições possui alguma transição.
     *
     * @return
     *          verdadeiro se a base de transições possui alguma transição.
     */
    public boolean hasTransition() {
        return !transitions.isEmpty();
    }


    /**
     * Retorna as transições da base de transições.
     *
     * @return
     *          lista contendo as transições da base de transições.
     */
    public Set<T> getTransitions() {
        return transitions;
    }


    /**
     * Adiciona um importador de base à base de transições.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(I importBase) {
        if(imports.add(importBase)){
            //Se importBase existe, atribui este como seu parente
            if(importBase != null)
                importBase.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um importador de base da base de transições.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(I importBase) {
        if(imports.remove(importBase)){
            //Se importBase existe, retira o seu parentesco
            if(importBase != null)
                importBase.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se a base de transições contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de transições possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de transições possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de transições.
     *
     * @return
     *          lista contendo os importadores de base da base de transições.
     */
    public Set<I> getImportBases() {
        return imports;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<transitionBase";
        if(getId() != null)
            content += " id='" + getId() + "'";

        if(hasImportBase() || hasTransition()){
            content += ">\n";

            if(hasImportBase()){
                for(I imp : imports)
                    content += imp.parse(ident + 1);
            }

            if(hasTransition()){
                for(T transition : transitions)
                    content += transition.parse(ident + 1);
            }

            content += space + "</transitionBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("transitionBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("importBase")){
                I child = createImportBase();
                child.startElement(uri, localName, qName, attributes);
                addImportBase(child);
            }
            else if(localName.equals("transition")){
                T child = createTransition();
                child.startElement(uri, localName, qName, attributes);
                addTransition(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasImportBase()){
            for(I imp : imports){
                imp.endDocument();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }
        if(hasTransition()){
            for(T transition : transitions){
                transition.endDocument();
                addWarning(transition.getWarnings());
                addError(transition.getErrors());
            }
        }
    }


    /**
     * Função de criação do elemento filho <i>importBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>importBase</i>.
     */
    protected I createImportBase() {
        return (I) new NCLImport(NCLImportType.BASE, getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>transition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>transition</i>.
     */
    protected T createTransition() {
        return (T) new NCLTransition(getReader(), this);
    }
}
