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
package br.uff.midiacom.ana.datatype.ncl.transition;

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


/**
 * Class that represents a base of transitions.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of transitions. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importBase</i> - element that imports a transition base defined in another
 *                          NCL document. The base can have none or several import
 *                          elements.</li>
 *  <li><i>transition</i> - element representing a transition inside the base. The
 *                          base can have none or several transition elements.</li>
 * </ul>
 * 
 * Note that the base of transitions must have at least one child element, which
 * can be a import or a transition.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Et>
 * @param <Ei> 
 */
public abstract class NCLTransitionBasePrototype<T extends NCLTransitionBasePrototype,
                                                 P extends NCLElement,
                                                 I extends NCLElementImpl,
                                                 Et extends NCLTransitionPrototype,
                                                 Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected IdentifiableElementList<Et, T> transitions;
    protected ElementList<Ei, T> imports;


    /**
     * Base of transitions constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLTransitionBasePrototype() throws XMLException {
        super();
        transitions = new IdentifiableElementList<Et, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Adds a transition to the base of transitions. The base of transitions can
     * have none or several transition elements.
     * 
     * @param transition
     *          element representing a transition.
     * @return
     *          true if the element representing a transition was added.
     * @throws XMLException 
     *          if the element representing the transition is null.
     */
    public boolean addTransition(Et transition) throws XMLException {
        if(transitions.add(transition, (T) this)){
            impl.notifyInserted(NCLElementSets.TRANSITIONS, transition);
            return true;
        }
        return false;
    }


    /**
     * Removes a transition of the base of transitions. The base of transitions can
     * have none or several transition elements.
     * 
     * @param transition
     *          element representing a transition.
     * @return
     *          true if the element representing a transition was removed.
     * @throws XMLException 
     *          if the element representing the transition is null.
     */
    public boolean removeTransition(Et transition) throws XMLException {
        if(transitions.remove(transition)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONS, transition);
            return true;
        }
        return false;
    }


    /**
     * Removes a transition of the base of transitions. The base of transitions can
     * have none or several transition elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          transition.
     * @return
     *          true if the transition was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeTransition(String id) throws XMLException {
        if(transitions.remove(id)){
            impl.notifyRemoved(NCLElementSets.TRANSITIONS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of transitions has a specific element representing
     * transition. The base of transitions can have none or several transition
     * elements.
     * 
     * @param transition
     *          element representing a transition.
     * @return
     *          true if the base of transitions has the transition element.
     * @throws XMLException 
     *          if the element representing the transition is null.
     */
    public boolean hasTransition(Et transition) throws XMLException {
        return transitions.contains(transition);
    }


    /**
     * Verifies if the base of transitions has a transition with a specific id.
     * The base of transitions can have none or several transition elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          transition.
     * @return
     *          true if the base of transitions has the transition element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasTransition(String id) throws XMLException {
        return transitions.get(id) != null;
    }


    /**
     * Verifies if the base of transitions has at least one transition. The base
     * of transitions can have none or several transition elements.
     * 
     * @return 
     *          true if the base of transitions has at least one transition.
     */
    public boolean hasTransition() {
        return !transitions.isEmpty();
    }


    /**
     * Returns the list of transitions that a base of transitions have. The base
     * of transitions can have none or several transition elements.
     * 
     * @return 
     *          element list with all transitions.
     */
    public IdentifiableElementList<Et, T> getTransitions() {
        return transitions;
    }


    /**
     * Adds an element that imports a base of transitions defined in another NCL
     * document to the base of transitions. The base can have none or several
     * import elements.
     * 
     * @param importBase
     *          element that imports a base of transitions defined in another NCL
     *          document.
     * @return
     *          true if the import element was added.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(imports.add(importBase, (T) this)){
            impl.notifyInserted(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Removes an element that imports a base of transitions defined in another
     * NCL document of the base of transitions. The base can have none or several
     * import elements.
     * 
     * @param importBase
     *          element that imports a base of transitions defined in another NCL
     *          document.
     * @return
     *          true if the import element was removed.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(imports.remove(importBase)){
            impl.notifyRemoved(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of transitions has a specific element that imports a
     * base of transitions defined in another NCL document. The base can have
     * none or several import elements.
     * 
     * @param importBase
     *          element that imports a base of transitions defined in another NCL
     *          document.
     * @return
     *          true if the base of transitions has the import element.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifies if the base of transitions has at least one element that imports
     * a base of transitions defined in another NCL document. The base can have
     * none or several import elements.
     * 
     * @return 
     *          true if the base of transitions has at least import element.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Returns the list of elements that imports a base of transitions defined in
     * another NCL document. The base can have none or several import elements.
     * 
     * @return 
     *          element list with all import elements.
     */
    public ElementList<Ei, T> getImportBases() {
        return imports;
    }
}
