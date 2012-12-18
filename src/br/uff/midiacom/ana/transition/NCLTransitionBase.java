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
package br.uff.midiacom.ana.transition;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLBase;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
public class NCLTransitionBase<T extends NCLElement,
                               Et extends NCLTransition,
                               Ei extends NCLImportBase,
                               R extends ExternalReferenceType>
        extends NCLBase<T, Ei> {

    protected ElementList<Et> transitions;


    /**
     * Base of transitions constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLTransitionBase() {
        super();
        transitions = new ElementList<Et>();
    }
    
    
    @Override
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Et aux : transitions) {
            aux.setDoc(doc);
        }
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
        if(transitions.add(transition)){
            transition.setParent(this);
            notifyInserted((T) transition);
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
        if(!transition.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(transitions.remove(transition)){
            notifyRemoved((T) transition);
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
        Et aux = transitions.get(id);
        return removeTransition(aux);
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
    public ElementList<Et> getTransitions() {
        return transitions;
    }


    /**
     * Returns the transition with a specific id. The base of transitions can
     * have none or several transition elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          transition.
     * @return 
     *          element representing a transition.
     */
    public Et getTransition(String id) throws XMLException {
        return transitions.get(id);
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLTransitionBase))
            return false;
        
        boolean result = true;
        ElementList<Et> othertra = ((NCLTransitionBase) other).getTransitions();
        
        result &= super.compareImports((NCLBase) other);
        
        result &= transitions.size() == othertra.size();
        for (Et tra : transitions) {
            try {
                result &= othertra.contains(tra);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        return result;
    }


    @Override
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<transitionBase";
        content += parseAttributes();

        if(hasImportBase() || hasTransition()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</transitionBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadId(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("TransitionBase:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadImportBases(el);
                    loadTransitions(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("TransitionBase > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportBases(ident);
        content += parseTransitions(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
    }
    
    
    protected String parseTransitions(int ident) {
        if(!hasTransition())
            return "";
        
        String content = "";
        for(Et aux : transitions)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadTransitions(Element element) throws XMLException {
        //create the transitions
        if(element.getTagName().equals(NCLElementAttributes.TRANSITION.toString())){
            Et inst = createTransition();
            addTransition(inst);
            inst.load(element);
        }
    }
    
    
    /**
     * Searches for a transition inside a transitionBase and its imported bases.
     * 
     * @param alias
     *          alias of the importBase the imports the transition.
     * @param id
     *          id of the transition to be found.
     * @return 
     *          transition or null if no transition was found.
     */
    public Object findTransition(String alias, String id) throws XMLException {
        Object result;
        
        if(alias == null){
            result = getTransitions().get(id);
            if(result != null)
                return result;
        }
        else{
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = d.getHead().findTransition(null, id);
                    if(ref instanceof NCLTransition)
                        return createExternalRef(imp, (Et) ref);
                    else
                        return createExternalRef(imp, (Et) ((R) ref).getTarget());
                }
            }
        }
        
        
        return null;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        for(Et t : transitions)
            t.clean();
    }
    

    /**
     * Function to create the child element <i>transition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>transition</i>.
     */
    protected Et createTransition() throws XMLException {
        return (Et) new NCLTransition();
    }


    /**
     * Function to create a reference to a transition.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a transition.
     */
    protected R createExternalRef(Ei imp, Et ref) throws XMLException {
        return (R) new ExternalReferenceType(imp, ref);
    }
}
