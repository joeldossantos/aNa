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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;
import org.w3c.dom.Element;


public class NCLTransitionBase<T extends NCLTransitionBase, P extends NCLElement, I extends NCLElementImpl, Et extends NCLTransition, Ei extends NCLImport>
        extends NCLTransitionBasePrototype<T, P, I, Et, Ei> implements NCLIdentifiableElement<T, P> {


    /**
     * Construtor do elemento <i>transitionBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLTransitionBase() throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
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
    @Override
    public boolean addTransition(Et transition) throws XMLException {
        if(super.addTransition(transition)){
            impl.notifyInserted(NCLElementSets.TRANSITIONS, transition);
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
    @Override
    public boolean removeTransition(Et transition) throws XMLException {
        if(super.removeTransition(transition)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONS, transition);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um importador de base à base de transições.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(super.addImportBase(importBase)){
            impl.notifyInserted(NCLElementSets.TRANSITIONBASE, importBase);
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
    @Override
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(super.removeImportBase(importBase)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONBASE, importBase);
            return true;
        }
        return false;
    }


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
     * Função de criação do elemento filho <i>importBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>importBase</i>.
     */
    protected Ei createImportBase() throws XMLException {
        return (Ei) new NCLImport(NCLImportType.BASE);
    }


    /**
     * Função de criação do elemento filho <i>transition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>transition</i>.
     */
    protected Et createTransition(String id) throws XMLException {
        return (Et) new NCLTransition(id);
    }
}
