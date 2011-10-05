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
package br.uff.midiacom.ana.datatype.ncl.transition;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLTransitionBasePrototype<T extends NCLTransitionBasePrototype, P extends NCLElement, Et extends NCLTransitionPrototype, Ei extends NCLImportPrototype> extends NCLIdentifiableElementPrototype<T, P> implements NCLIdentifiableElement<T, P> {

    protected IdentifiableElementList<Et, T> transitions;
    protected ElementList<Ei, T> imports;


    /**
     * Construtor do elemento <i>transitionBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLTransitionBasePrototype() {
        transitions = new IdentifiableElementList<Et, T>();
        imports = new ElementList<Ei, T>();
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
    public boolean addTransition(Et transition) throws XMLException {
        return transitions.add(transition, (T) this);
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
    public boolean removeTransition(Et transition) throws XMLException {
        return transitions.remove(transition);
    }


    public boolean removeTransition(String id) throws XMLException {
        return transitions.remove(id);
    }


    /**
     * Verifica se a base de transições possui uma transição.
     *
     * @param transition
     *          elemento representando a transição a ser verificada.
     * @return
     *          verdadeiro se a transição existir.
     */
    public boolean hasTransition(Et transition) throws XMLException {
        return transitions.contains(transition);
    }


    public boolean hasTransition(String id) throws XMLException {
        return transitions.get(id) != null;
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
    public IdentifiableElementList<Et, T> getTransitions() {
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
    public boolean addImportBase(Ei importBase) throws XMLException {
        return imports.add(importBase, (T) this);
    }


    /**
     * Remove um importador de base da base de transições.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de transições contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
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
    public ElementList<Ei, T> getImportBases() {
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
                for(Ei imp : imports)
                    content += imp.parse(ident + 1);
            }

            if(hasTransition()){
                for(Et transition : transitions)
                    content += transition.parse(ident + 1);
            }

            content += space + "</transitionBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }
}
