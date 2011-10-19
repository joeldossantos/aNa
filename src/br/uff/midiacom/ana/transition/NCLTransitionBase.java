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
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLTransitionBase<T extends NCLTransitionBase, P extends NCLElement, I extends NCLElementImpl, Et extends NCLTransition, Ei extends NCLImport>
        extends NCLTransitionBasePrototype<T, P, I, Et, Ei> implements NCLIdentifiableElement<T, P> {


    public NCLTransitionBase() throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public boolean addTransition(Et transition) throws XMLException {
        if(super.addTransition(transition)){
            impl.notifyInserted(NCLElementSets.TRANSITIONS, transition);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeTransition(Et transition) throws XMLException {
        if(super.removeTransition(transition)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONS, transition);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeTransition(String id) throws XMLException {
        if(super.removeTransition(id)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONS, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(super.addImportBase(importBase)){
            impl.notifyInserted(NCLElementSets.TRANSITIONBASE, importBase);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(super.removeImportBase(importBase)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONBASE, importBase);
            return true;
        }
        return false;
    }


    public void load(Element element) throws XMLException {
        String att_name, ch_name;
        int length;

        ch_name = NCLElementAttributes.TRANSITION.toString();
        NodeList nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();

        att_name = NCLElementAttributes.ID.toString();
        for(int i=0; i<length; i++){
            Element elem_child = (Element) nl.item(i);
            addTransition((Et) new NCLTransition(elem_child.getAttribute(att_name)));
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected NCLImport createImportBase() throws XMLException {
        return new NCLImport(NCLImportType.BASE);
    }


    /**
     * Function to create the child element <i>transition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>transition</i>.
     */
    protected NCLTransition createTransition(String id) throws XMLException {
        return new NCLTransition(id);
    }
}
