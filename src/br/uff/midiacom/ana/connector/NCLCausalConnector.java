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
import br.uff.midiacom.ana.util.reference.ReferredElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a causal connector.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the connector. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>connectorParam</i> - parameter used in the connector. The connector
 *                              can have none or several parameter elements.</li>
 *  <li><i>simpleCondition</i> - element representing a connector condition. The
 *                               connector can have none or several condition elements.</li>
 *  <li><i>compundCondition</i> - element representing a connector condition. The
 *                                connector can have none or several condition elements.</li>
 *  <li><i>simpleAction</i> - element representing a connector action. The
 *                            connector can have none or several action elements.</li>
 *  <li><i>compundAction</i> - element representing a connector action. The
 *                             connector can have none or several action elements.</li>
 * </ul>
 * 
 * Note that the connector must have at least one condition and one action.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec>
 * @param <Ea>
 * @param <Ep> 
 */
public class NCLCausalConnector<T extends NCLElement,
                                Ec extends NCLCondition,
                                Ea extends NCLAction,
                                Ep extends NCLConnectorParam,
                                Er extends NCLRoleElement,
                                El extends NCLLink>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLElement<T>, ReferredElement<El> {

    protected Ec condition;
    protected Ea action;
    protected ElementList<Ep> conn_params;
    
    protected ArrayList<El> references;


    /**
     * Connector element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */  
    public NCLCausalConnector() {
        super();
        conn_params = new ElementList<Ep>();
        references = new ArrayList<El>();
    }
    
    
    public NCLCausalConnector(String id) throws XMLException {
        super();
        conn_params = new ElementList<Ep>();
        references = new ArrayList<El>();
        setId(id);
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        if(condition != null) ((NCLElementPrototype) condition).setDoc(doc);
        if(action != null) ((NCLElementPrototype) action).setDoc(doc);
        for (Ep aux : conn_params) {
            aux.setDoc(doc);
        }
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
    }
    
    
    /**
     * Sets the connector condition. The connector condition can be a simple
     * condition or a compound condition.
     * 
     * <br/>
     * 
     * The connector must have at least one condition.
     * 
     * @param condition 
     *          element representing a connector condition or <i>null</i> to
     *          erase a condition already defined.
     */
    public void setCondition(Ec condition) throws XMLException {
        //Removes the parent of the actual condition
        if(this.condition != null){
            if(this.condition instanceof ReferredElement && !((ReferredElement) this.condition).getReferences().isEmpty())
                throw new NCLRemovalException("This element has a reference to it."
                        + " The reference must be undone before erasing this element.");
        
            this.condition.setParent(null);
            notifyRemoved((T) this.condition);
        }

        this.condition = condition;
        //Sets this as the parent of the new condition
        if(this.condition != null){
            this.condition.setParent(this);
            notifyInserted((T) this.condition);
        }
    }
    
    
    /**
     * Returns the connector condition or <i>null</i> if the condition is not
     * defined. The connector condition can be a simple condition or a compound
     * condition.
     * 
     * <br/>
     * 
     * The connector must have at least one condition.
     * 
     * @return
     *          element representing a connector condition or <i>null</i> if the
     *          condition is not defined.
     */
    public Ec getCondition() {
        return condition;
    }


    /**
     * Sets the connector action. The connector action can be a simple action
     * or a compound action.
     * 
     * <br/>
     * 
     * The connector must have at least one action.
     * 
     * @param action 
     *          element representing a connector action or <i>null</i> to
     *          erase an action already defined.
     */
    public void setAction(Ea action) throws XMLException {
        //Removes the parent of the actual action
        if(this.action != null){
            if(this.action instanceof ReferredElement && !((ReferredElement) this.action).getReferences().isEmpty())
                throw new NCLRemovalException("This element has a reference to it."
                        + " The reference must be undone before erasing this element.");
            
            this.action.setParent(null);
            notifyRemoved((T) this.action);
        }

        this.action = action;
        //Sets this as the parent of the new action
        if(this.action != null){
            this.action.setParent(this);
            notifyInserted((T) this.action);
        }
    }
    
    
    /**
     * Returns the connector action or <i>null</i> if the action is not
     * defined. The connector action can be a simple action or a compound
     * action.
     * 
     * <br/>
     * 
     * The connector must have at least one action.
     * 
     * @return
     *          element representing a connector action or <i>null</i> if the
     *          action is not defined.
     */
    public Ea getAction() {
        return action;
    }

    
    /**
     * Adds a connector parameter to the connector. The connector can have none
     * or several parameter elements.
     * 
     * @param param
     *          element representing a connector parameter.
     * @return
     *          true if the element representing a connector parameter was added.
     * @throws XMLException 
     *          if the element representing the connector parameter is null.
     */
    public boolean addConnectorParam(Ep param) throws XMLException {
        if(conn_params.add(param)){
            notifyInserted((T) param);
            param.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes a connector parameter of the connector. The connector can have none
     * or several parameter elements.
     * 
     * @param param
     *          element representing a connector parameter.
     * @return
     *          true if the element representing a connector parameter was removed.
     * @throws XMLException 
     *          if the element representing the connector parameter is null.
     */
    public boolean removeConnectorParam(Ep param) throws XMLException {
        if(!param.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(conn_params.remove(param)){
            notifyRemoved((T) param);
            return true;
        }
        return false;
    }

    
    /**
     * Removes a connector parameter of the connector. The connector can have none
     * or several parameter elements.
     * 
     * @param name
     *          string representing the name of the element representing a
     *          connector parameter.
     * @return
     *          true if the connector parameter was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeConnectorParam(String name) throws XMLException {
        Ep aux = conn_params.get(name);
        return removeConnectorParam(aux);
    }


    /**
     * Verifies if the connector has a specific element representing
     * connector parameter. The connector can have none or several parameter
     * elements.
     * 
     * @param param
     *          element representing a connector parameter.
     * @return
     *          true if the connectors has the connector parameter element.
     * @throws XMLException 
     *          if the element representing the connector parameter is null.
     */
    public boolean hasConnectorParam(Ep param) throws XMLException {
        return conn_params.contains(param);
    }


    /**
     * Verifies if the connector has a connector parameter with a specific name.
     * The connector can have none or several parameter elements.
     * 
     * @param name
     *          string representing the name of the element representing a
     *          connector parameter.
     * @return
     *          true if the connectors has the connector parameter element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasConnectorParam(String name) throws XMLException {
        return conn_params.get(name) != null;
    }


    /**
     * Verifies if the connector has at least one connector parameter. The
     * connector can have none or several parameter elements.
     * 
     * @return 
     *          true if the connector has at least one connector parameter.
     */
    public boolean hasConnectorParam() {
        return !conn_params.isEmpty();
    }


    /**
     * Returns the list of connector parameters that a connector have. The
     * connector can have none or several parameter elements.
     * 
     * @return 
     *          element list with all connector parameters.
     */
    public ElementList<Ep> getConnectorParams() {
        return conn_params;
    }


    /**
     * Returns the connector parameter with a specific name. The connector can
     * have none or several parameter elements.
     * 
     * @param name
     *          string representing the name of the connector parameter.
     * @return 
     *          element representing a connector parameter.
     */
    public Ep getConnectorParam(String name) throws XMLException {
        return conn_params.get(name);
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLCausalConnector))
            return false;
        
        boolean result = true;

        Object aux;

        if((aux = getId()) != null)
            result &= aux.equals(((NCLCausalConnector) other).getId());

        T el;
        if((el = (T) getCondition()) != null)
            result &= el.compare(((NCLCausalConnector) other).getCondition());
        if((el = (T) getAction()) != null)
            result &= el.compare(((NCLCausalConnector) other).getAction());

        ElementList<Ep> otherpar = ((NCLCausalConnector) other).getConnectorParams();
        result &= conn_params.size() == otherpar.size();
        for (Ep par : conn_params) {
            try {
                result &= otherpar.contains(par);
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

        content = space + "<causalConnector";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</causalConnector>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadId(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("CausalConnector" + aux + ":\n" + ex.getMessage());
        }

        try{
            loadConnectorParams(element);

            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadSimpleConditions(el);
                    loadCompoundConditions(el);
                    loadSimpleActions(el);
                    loadCompoundActions(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("CausalConnector" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseConnectorParams(ident);
        content += parseCondition(ident);
        content += parseAction(ident);
        
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
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseConnectorParams(int ident) {
        if(!hasConnectorParam())
            return "";
        
        String content = "";
        for(Ep aux : conn_params)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadConnectorParams(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the connectorParam nodes
        ch_name = NCLElementAttributes.CONNECTORPARAM.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ep inst = createConnectorParam();
            addConnectorParam(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseCondition(int ident) {
        Ec aux = getCondition();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadSimpleConditions(Element element) throws XMLException {
        //create the simpleCondition
        if(element.getTagName().equals(NCLElementAttributes.SIMPLECONDITION.toString())){
            Ec inst = createSimpleCondition();
            setCondition(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundConditions(Element element) throws XMLException {
        // create the compoundCondition
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDCONDITION.toString())){
            Ec inst = createCompoundCondition();
            setCondition(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseAction(int ident) {
        Ea aux = getAction();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadSimpleActions(Element element) throws XMLException {
        //create the simpleAction
        if(element.getTagName().equals(NCLElementAttributes.SIMPLEACTION.toString())){
            Ea inst = createSimpleAction();
            setAction(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundActions(Element element) throws XMLException {
        // create the compoundAction
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDACTION.toString())){
            Ea inst = createCompoundAction();
            setAction(inst);
            inst.load(element);
        }
    }
    
    
    /**
     * Searches for a role inside the connector.
     * 
     * @param name
     *          name of the role to be found.
     * @return 
     *          role or null if no role was found.
     */
    public Er findRole(String name) {
        Er result;
        
        result = (Er) getCondition().findRole(name);
        if(result != null)
            return result;
        
        result = (Er) getAction().findRole(name);
        if(result != null)
            return result;
        
        return null;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        condition = null;
        action = null;
        
        for(Ep p : conn_params)
            p.clean();
    }
    

    /**
     * Function to create the child element <i>connectorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connectorParam</i>.
     */
    protected Ep createConnectorParam() throws XMLException {
        return (Ep) new NCLConnectorParam();
    }


    /**
     * Function to create the child element <i>simpleCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleCondition</i>.
     */
    protected Ec createSimpleCondition() throws XMLException {
        return (Ec) new NCLSimpleCondition();
    }


    /**
     * Function to create the child element <i>compoundCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundCondition</i>.
     */
    protected Ec createCompoundCondition() throws XMLException {
        return (Ec) new NCLCompoundCondition();
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
    
    
    @Override
    public boolean addReference(El reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(El reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
}
