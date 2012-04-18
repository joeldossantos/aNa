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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;


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
public abstract class NCLCausalConnectorPrototype<T extends NCLCausalConnectorPrototype,
                                                  P extends NCLElement,
                                                  I extends NCLElementImpl,
                                                  Ec extends NCLCondition,
                                                  Ea extends NCLAction,
                                                  Ep extends NCLConnectorParamPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement<ReferenceType> {

    protected Ec condition;
    protected Ea action;
    protected IdentifiableElementList<Ep, T> conn_params;
    
    protected ItemList<ReferenceType> references;


    /**
     * Connector element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */  
    public NCLCausalConnectorPrototype() throws XMLException {
        super();
        conn_params = new IdentifiableElementList<Ep, T>();
        references = new ItemList<ReferenceType>();
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
    public void setCondition(Ec condition) {
        //Removes the parent of the actual condition
        if(this.condition != null){
            this.condition.setParent(null);
            impl.notifyRemoved(NCLElementSets.CONDITIONS, this.condition);
        }

        this.condition = condition;
        //Sets this as the parent of the new condition
        if(this.condition != null){
            this.condition.setParent(this);
            impl.notifyInserted(NCLElementSets.CONDITIONS, this.condition);
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
    public void setAction(Ea action) {
        //Removes the parent of the actual action
        if(this.action != null){
            this.action.setParent(null);
            impl.notifyRemoved(NCLElementSets.ACTIONS, this.action);
        }

        this.action = action;
        //Sets this as the parent of the new action
        if(this.action != null){
            this.action.setParent(this);
            impl.notifyInserted(NCLElementSets.ACTIONS, this.action);
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
        if(conn_params.add(param, (T) this)){
            impl.notifyInserted(NCLElementSets.CONNECTOR_PARAMS, param);
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
        if(conn_params.remove(param)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, param);
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
        if(conn_params.remove(name)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, name);
            return true;
        }
        return false;
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
    public IdentifiableElementList<Ep, T> getConnectorParams() {
        return conn_params;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
