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
package br.uff.midiacom.ana.datatype.ncl.link;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.aux.reference.ConnectorReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


/**
 * Class that represents a link element. A link represents a relationship among
 * nodes in an NCL document.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the link element. This attribute is optional.</li>
 *  <li><i>xconnector</i> - connector that defines the relation represented by
 *                          the link element. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>linkParam</i> - element that defines a value to a parameter defined
 *                         in the connector. The link can have none or several
 *                         parameter elements.</li>
 *  <li><i>bind</i> - element that associates a role defined in the connector to
 *                    a node. The link must have at least two bind elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ep>
 * @param <Eb>
 * @param <Ec> 
 */
public abstract class NCLLinkPrototype<T extends NCLLinkPrototype,
                                       P extends NCLElement,
                                       I extends NCLElementImpl,
                                       Ep extends NCLParamPrototype,
                                       Eb extends NCLBindPrototype,
                                       Ec extends ConnectorReference>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>{

    protected Ec xconnector;
    protected ElementList<Ep, T> linkParams;
    protected ElementList<Eb, T> binds;
    

    /**
     * Link element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLLinkPrototype() throws XMLException {
        super();
        linkParams = new ElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
    }


    /**
     * Sets the connector that defines the relation represented by the link
     * element. This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The connector referred can be defined in the document base of connectors
     * or in a base defined in an external document, imported by the base of
     * connectors or by the base of imported documents. When the connector is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @param xconnector
     *          element representing a reference to a connector element.
     * @throws XMLException 
     *          if the connector is null or any error occur while creating the
     *          reference to the connector.
     */
    public void setXconnector(Ec xconnector) throws XMLException {
        if(xconnector == null)
            throw new XMLException("Null connector.");
        
        Ec aux = this.xconnector;
        
        this.xconnector = xconnector;
        this.xconnector.setOwner((T) this);
        this.xconnector.setOwnerAtt(NCLElementAttributes.XCONNECTOR);
        
        impl.notifyAltered(NCLElementAttributes.XCONNECTOR, aux, xconnector);
        if(aux != null)
            aux.clean();
    }
    
    
    /**
     * Returns the connector that defines the relation represented by the link
     * element or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The connector referred can be defined in the document base of connectors
     * or in a base defined in an external document, imported by the base of
     * connectors or by the base of imported documents. When the connector is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @return
     *          element representing a reference to a connector element or
     *          <i>null</i> if the attribute is not defined.
     */
    public Ec getXconnector() {
        return xconnector;
    }
    
    
    /**
     * Adds a link parameter to the link. A link parameter defines a value to a
     * parameter defined in the connector. The link can have none or several
     * parameter elements.
     * 
     * @param param
     *          element representing a link parameter.
     * @return
     *          true if the parameter was added.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean addLinkParam(Ep param) throws XMLException {
        if(linkParams.add(param, (T) this)){
            impl.notifyInserted(NCLElementSets.LINKPARAMS, param);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a link parameter of the link. A link parameter defines a value to
     * a parameter defined in the connector. The link can have none or several
     * parameter elements.
     * 
     * @param param
     *          element representing a link parameter.
     * @return
     *          true if the parameter was removed.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean removeLinkParam(Ep param) throws XMLException {
        if(linkParams.remove(param)){
            impl.notifyRemoved(NCLElementSets.LINKPARAMS, param);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the link has a specific element representing a link parameter.
     * A link parameter defines a value to a parameter defined in the connector.
     * The link can have none or several parameter elements.
     * 
     * @param param
     *          element representing a link parameter.
     * @return
     *          true if the link has the parameter element.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean hasLinkParam(Ep param) throws XMLException {
        return linkParams.contains(param);
    }
    
    
    /**
     * Verifies if the link has at least one link parameter. A link parameter
     * defines a value to a parameter defined in the connector. The link can have
     * none or several parameter elements.
     * 
     * @return 
     *          true if the link has at least one parameter.
     */
    public boolean hasLinkParam() {
        return !linkParams.isEmpty();
    }
    
    
    /**
     * Returns the list of link parameters that a link have. A link parameter
     * defines a value to a parameter defined in the connector. The link can have
     * none or several parameter elements.
     * 
     * @return 
     *          element list with all parameters.
     */
    public ElementList<Ep, T> getLinkParams() {
        return linkParams;
    }
    
    
    /**
     * Adds a bind to the link. A bind element associates a role defined in the
     * connector to a node. The link must have at least two bind elements.
     * 
     * @param bind
     *          element representing a bind.
     * @return
     *          true if the bind was added.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean addBind(Eb bind) throws XMLException {
        if(binds.add(bind, (T) this)){
            impl.notifyInserted(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a bind of the link. A bind element associates a role defined in
     * the connector to a node. The link must have at least two bind elements.
     * 
     * @param bind
     *          element representing a bind.
     * @return
     *          true if the bind was removed.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean removeBind(Eb bind) throws XMLException {
        if(binds.remove(bind)){
            impl.notifyRemoved(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the link has a specific bind element. A bind element
     * associates a role defined in the connector to a node. The link must have
     * at least two bind elements.
     * 
     * @param bind
     *          element representing a bind.
     * @return
     *          true if the link has the bind element.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifies if the link has at least one bind element. A bind element
     * associates a role defined in the connector to a node. The link must have
     * at least two bind elements.
     * 
     * @return 
     *          true if the link has at least one bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }
    
    
    /**
     * Returns the list of bind elements that a link have. A bind element
     * associates a role defined in the connector to a node. The link must have
     * at least two bind elements.
     * 
     * @return 
     *          element list with all binds.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }


    @Override
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        boolean comp = true;

        // Compara pelo xconnector
        if(getXconnector() != null && other.getXconnector() != null)
            comp &= ((NCLCausalConnector) getXconnector().getTarget())
                    .compare((NCLCausalConnector) other.getXconnector().getTarget());

        // Compara o número de parâmetros
        comp &= linkParams.size() == other.getLinkParams().size();

        // Compara o número de binds
        comp &= binds.size() == other.getBinds().size();

        // Compara os parâmetros
        Iterator it = other.getLinkParams().iterator();
        for(Ep param : linkParams){
            Ep other_param = (Ep) it.next();
            comp &= param.compare(other_param);
            if(comp)
                break;
        }

        // Compara os binds
        it = other.getBinds().iterator();
        for(Eb bind : binds){
            if(!it.hasNext())
                continue;
            Eb other_bind = (Eb) it.next();
            comp &= bind.compare(other_bind);
            if(comp)
                break;
        }


        return comp;
    }
}
