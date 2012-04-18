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
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


/**
 * Class that represents a base of connectors.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of connectors. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importBase</i> - element that imports a connector base defined in another
 *                          NCL document. The base can have none or several import
 *                          elements.</li>
 *  <li><i>causalConnector</i> - element representing a connector inside the base. The
 *                          base can have none or several connector elements.</li>
 * </ul>
 * 
 * Note that the base of connectors must have at least one child element, which
 * can be a import or a causalConnector.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec>
 * @param <Ei> 
 */
public abstract class NCLConnectorBasePrototype<T extends NCLConnectorBasePrototype,
                                                P extends NCLElement,
                                                I extends NCLElementImpl,
                                                Ec extends NCLCausalConnectorPrototype,
                                                Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLBase<T, P> {

    protected IdentifiableElementList<Ec, T> connectors;
    protected ElementList<Ei, T> imports;
    

    /**
     * Base of connectors constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLConnectorBasePrototype() throws XMLException {
        super();
        connectors = new IdentifiableElementList<Ec, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Adds a connector to the base of connectors. The base of connectors can
     * have none or several connector elements.
     * 
     * @param connector
     *          element representing a connector.
     * @return
     *          true if the element representing a connector was added.
     * @throws XMLException 
     *          if the element representing the connector is null.
     */
    public boolean addCausalConnector(Ec connector) throws XMLException {
        if(connectors.add(connector, (T) this)){
            impl.notifyInserted(NCLElementSets.CONNECTORS, connector);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a connector of the base of connectors. The base of connectors can
     * have none or several connector elements.
     * 
     * @param connector
     *          element representing a connector.
     * @return
     *          true if the element representing a connector was removed.
     * @throws XMLException 
     *          if the element representing the connector is null.
     */
    public boolean removeCausalConnector(Ec connector) throws XMLException {
        if(connectors.remove(connector)){
            impl.notifyRemoved(NCLElementSets.CONNECTORS, connector);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a connector of the base of connectors. The base of connectors can
     * have none or several connector elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          connector.
     * @return
     *          true if the connector was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeCausalConnector(String id) throws XMLException {
        if(connectors.remove(id)){
            impl.notifyRemoved(NCLElementSets.CONNECTORS, id);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the base of connectors has a specific element representing
     * connector. The base of connectors can have none or several connector
     * elements.
     * 
     * @param connector
     *          element representing a connector.
     * @return
     *          true if the base of connectors has the connector element.
     * @throws XMLException 
     *          if the element representing the connector is null.
     */
    public boolean hasCausalConnector(Ec connector) throws XMLException {
        return connectors.contains(connector);        
        
    }
    
    
    /**
     * Verifies if the base of connectors has a connector with a specific id.
     * The base of connectors can have none or several connector elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          connector.
     * @return
     *          true if the base of connectors has the connector element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasCausalConnector(String id) throws XMLException {
        return connectors.get(id) != null;
    }
    
    
    /**
     * Verifies if the base of connectors has at least one connector. The base
     * of connectors can have none or several connector elements.
     * 
     * @return 
     *          true if the base of connectors has at least one connector.
     */
    public boolean hasCausalConnector() {
        return !connectors.isEmpty();
    }

    
    /**
     * Returns the list of connectors that a base of connectors have. The base
     * of connectors can have none or several connector elements.
     * 
     * @return 
     *          element list with all connectors.
     */
    public IdentifiableElementList<Ec, T> getCausalConnectors() {
        return connectors;        
    }


    /**
     * Adds an element that imports a base of connectors defined in another NCL
     * document to the base of connectors. The base can have none or several
     * import elements.
     * 
     * @param importBase
     *          element that imports a base of connectors defined in another NCL
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
     * Removes an element that imports a base of connectors defined in another
     * NCL document of the base of connectors. The base can have none or several
     * import elements.
     * 
     * @param importBase
     *          element that imports a base of connectors defined in another NCL
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
     * Verifies if the base of connectors has a specific element that imports a
     * base of connectors defined in another NCL document. The base can have
     * none or several import elements.
     * 
     * @param importBase
     *          element that imports a base of connectors defined in another NCL
     *          document.
     * @return
     *          true if the base of connectors has the import element.
     * @throws XMLException 
     *          if the import element is null.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifies if the base of connectors has at least one element that imports
     * a base of connectors defined in another NCL document. The base can have
     * none or several import elements.
     * 
     * @return 
     *          true if the base of connectors has at least import element.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Returns the list of elements that imports a base of connectors defined in
     * another NCL document. The base can have none or several import elements.
     * 
     * @return 
     *          element list with all import elements.
     */
    public ElementList<Ei, T> getImportBases() {
        return imports;
    }
}
