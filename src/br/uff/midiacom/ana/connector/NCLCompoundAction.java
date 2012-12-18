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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLActionOperator;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a connector compound action.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>operator</i> - compound action operator. This attribute is required.</li>
 *  <li><i>delay</i> - delay waited by the action. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>simpleAction</i> - element representing a connector action. The
 *                            compound action can have none or several action
 *                            elements.</li>
 *  <li><i>compundAction</i> - element representing a connector action. The
 *                             compound action can have none or several action
 *                             elements.</li>
 * </ul>
 * 
 * Note that the compound action must have at least two actions.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ea>
 * @param <Ep>
 * @param <R> 
 */
public class NCLCompoundAction<T extends NCLElement,
                               Ea extends NCLAction,
                               Ep extends NCLConnectorParam,
                               Er extends NCLRoleElement>
        extends ParamElement<T>
        implements NCLAction<T, Ep, Er> {

    protected NCLActionOperator operator;
    protected Object delay;
    protected ElementList<Ea> actions;


    /**
     * Compound action element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompoundAction() {
        super();
        actions = new ElementList<Ea>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ea aux : actions) {
            ((NCLElementPrototype) aux).setDoc(doc);
        }
    }
    
    
    /**
     * Sets the compound action operator. This attribute is required and can not
     * be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound action children elements. Its
     * possible values are "par" and "seq" and are defined in the enumeration
     * <i>NCLActionOperator</i>.
     * 
     * <br/>
     * 
     * Using a "par" operator means that the actions of the compound action will
     * be executed in any order. Using a "seq" operator means that the actions of
     * the compound action will be executed in a predetermined order.
     *
     * @param operator
     *          element representing the compound action operator from the
     *          enumeration <i>NCLActionOperator</i>.
     * @throws XMLException 
     *          if the element representing the operator is null.
     */
    public void setOperator(NCLActionOperator operator) throws XMLException {
        if(operator == null)
            throw new XMLException("Null operator.");
        
        NCLActionOperator aux = this.operator;
        this.operator = operator;
        notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
    
    /**
     * Returns the compound action operator or <i>null</i> if the attribute is
     * not defined.
     * 
     * <br/>
     * 
     * The operator attribute relates the compound action children elements. Its
     * possible values are "par" and "seq" and are defined in the enumeration
     * <i>NCLActionOperator</i>.
     * 
     * <br/>
     * 
     * Using a "par" operator means that the actions of the compound action will
     * be executed in any order. Using a "seq" operator means that the actions of
     * the compound action will be executed in a predetermined order.
     * 
     * @return 
     *          element representing the compound action operator from the
     *          enumeration <i>NCLActionOperator</i> or <i>null</i> if the
     *          attribute is not defined.
     */
    public NCLActionOperator getOperator() {
        return operator;
    }

    
    /**
     * Adds an action to the compound action. The compound action must have at
     * least two action elements.
     * 
     * @param action
     *          element representing an action.
     * @return
     *          true if the element representing an action was added.
     * @throws XMLException 
     *          if the element representing the action is null.
     */
    public boolean addAction(Ea action) throws XMLException {
        if(actions.add(action)){
            notifyInserted((T) action);
            action.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an action of the compound action. The compound action must have at
     * least two action elements.
     * 
     * @param action
     *          element representing an action.
     * @return
     *          true if the element representing an action was removed.
     * @throws XMLException 
     *          if the element representing the action is null.
     */
    public boolean removeAction(Ea action) throws XMLException {
        if(action instanceof ReferredElement && !((ReferredElement) action).getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(actions.remove(action)){
            notifyRemoved((T) action);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the compound action has a specific element representing an
     * action. The compound action must have at least two action elements.
     * 
     * @param action
     *          element representing an action.
     * @return
     *          true if the compound action has the action element.
     * @throws XMLException 
     *          if the element representing the action is null.
     */
    public boolean hasAction(Ea action) throws XMLException {
        return actions.contains(action);
    }


    /**
     * Verifies if the compound action has at least one action. The compound
     * action must have at least two action elements.
     * 
     * @return 
     *          true if the compound action has at least one action.
     */
    public boolean hasAction() {
        return !actions.isEmpty();
    }


    /**
     * Returns the list of actions that a compound action have. The compound
     * action must have at least two action elements.
     * 
     * @return 
     *          element list with all actions.
     */
    public ElementList<Ea> getActions() {
        return actions;
    }


    @Override
    public void setDelay(Object delay) throws XMLException {
        Object aux = this.delay;
        
        if(delay == null){
            this.delay = delay;
            notifyAltered(NCLElementAttributes.DELAY, aux, delay);
            
            if(aux != null && aux instanceof NCLConnectorParam)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(delay instanceof String){
            String value = (String) delay;
            if("".equals(value.trim()))
                throw new XMLException("Empty delay String");
            
            if(!value.contains("$")){
                if(value.contains("s"))
                    value = value.substring(0, value.length() - 1);
                this.delay = new Double(value);
            }
            else{
                this.delay = findConnectorParam(value.substring(1));
                ((Ep) this.delay).addReference(this);
            }
        }
        else if(delay instanceof Double)
            this.delay = delay;
        else if(delay instanceof NCLConnectorParam){
            this.delay = delay;
            ((Ep) this.delay).addReference(this);
        }
        else
            throw new XMLException("Wrong delay type.");
        
        notifyAltered(NCLElementAttributes.DELAY, aux, delay);
    }


    @Override
    public Object getDelay() {
        return delay;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLCompoundAction))
            return false;
        
        boolean comp = true;

        String this_act, other_act;
        NCLCompoundAction other_comp = (NCLCompoundAction) other;
        
        // Compare the operator
        if(getOperator() == null) this_act = ""; else this_act = getOperator().toString();
        if(other_comp.getOperator() == null) other_act = ""; else other_act = other_comp.getOperator().toString();
        comp = this_act.equals(other_act);

        // Compare the delay
        if(getDelay() == null) this_act = ""; else this_act = getDelay().toString();
        if(other_comp.getDelay() == null) other_act = ""; else other_act = other_comp.getDelay().toString();
        comp &= this_act.equals(other_act);

        ElementList<Ea> otherlist = ((NCLCompoundAction) other).getActions();
        comp &= actions.size() == otherlist.size();
        for (Ea aux : actions) {
            try {
                comp &= otherlist.contains(aux);
            } catch (XMLException ex) {}
            if(!comp)
                break;
        }
        
        return comp;
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

        content = space + "<compoundAction";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</compoundAction>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        try{
            loadOperator(element);
            loadDelay(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("CompoundAction:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadSimpleActions(el);
                    loadCompoundActions(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("CompoundAction > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseOperator();
        content += parseDelay();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseActions(ident);
        
        return content;
    }
    
    
    protected String parseOperator() {
        NCLActionOperator aux = getOperator();
        if(aux != null)
            return " operator='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadOperator(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the operator (required)
        att_name = NCLElementAttributes.OPERATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setOperator(NCLActionOperator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseDelay() {
        Object aux = getDelay();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLConnectorParam)
            return " delay='$" + ((Ep) aux).getName() + "'";
        else
            return " delay='" + aux.toString() + "s'";
    }
    
    
    protected void loadDelay(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the delay (optional)
        att_name = NCLElementAttributes.DELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDelay(att_var);
    }


    protected String parseActions(int ident) {
        if(!hasAction())
            return "";
        
        String content = "";
        for(Ea aux : actions)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadSimpleActions(Element element) throws XMLException {
        //create the simpleAction
        if(element.getTagName().equals(NCLElementAttributes.SIMPLEACTION.toString())){
            Ea inst = createSimpleAction();
            addAction(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundActions(Element element) throws XMLException {
        // create the compoundAction
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDACTION.toString())){
            Ea inst = createCompoundAction();
            addAction(inst);
            inst.load(element);
        }
    }
    
    
    @Override
    public Er findRole(String name) {
        Er result;
        
        for(Ea action : actions){
            result = (Er) action.findRole(name);
            if(result != null)
                return result;
        }
        
        return null;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(delay != null && delay instanceof NCLConnectorParam)
            ((Ep)delay).removeReference(this);
        
        operator = null;
        delay = null;
        
        for(Ea a : actions)
            a.clean();
    }
    

    /**
     * Function to create the child element <i>simpleAction</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleAction</i>.
     */
    protected Ea createSimpleAction() throws XMLException {
        return (Ea) new NCLSimpleAction();
    }


    /**
     * Function to create the child element <i>compoundAction</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundAction</i>.
     */
    protected Ea createCompoundAction() throws XMLException {
        return (Ea) new NCLCompoundAction();
    }
}
