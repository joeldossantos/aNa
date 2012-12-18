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

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLBase;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
public class NCLConnectorBase<T extends NCLElement,
                              Ec extends NCLCausalConnector,
                              Ei extends NCLImportBase,
                              R extends ExternalReferenceType>
        extends NCLBase<T, Ei> {

    protected ElementList<Ec> connectors;
    

    /**
     * Base of connectors constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLConnectorBase() {
        super();
        connectors = new ElementList<Ec>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ec aux : connectors) {
            aux.setDoc(doc);
        }
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
        if(connectors.add(connector)){
            notifyInserted((T) connector);
            connector.setParent(this);
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
        if(!connector.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(connectors.remove(connector)){
            notifyRemoved((T) connector);
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
        Ec aux = connectors.get(id);
        return removeCausalConnector(aux);
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
    public ElementList<Ec> getCausalConnectors() {
        return connectors;        
    }


    /**
     * Returns the connector with a specific id. The base of connectors can have
     * none or several connector elements.
     * 
     * @param id
     *          string representing the id of the connector.
     * @return 
     *          element representing a connector.
     */
    public Ec getCausalConnector(String id) throws XMLException {
        return connectors.get(id);
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLConnectorBase))
            return false;
        
        boolean result = true;
        ElementList<Ec> othercon = ((NCLConnectorBase) other).getCausalConnectors();
        
        result &= super.compareImports((NCLBase) other);
        
        result &= connectors.size() == othercon.size();
        for (Ec con : connectors) {
            try {
                result &= othercon.contains(con);
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

        content = space + "<connectorBase";
        content += parseAttributes();

        if(hasImportBase() || hasCausalConnector()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</connectorBase>\n";
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
            throw new NCLParsingException("ConnectorBase:\n" + ex.getMessage());
        }
        
        try{
            // create the child elements
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element) {
                    Element el = (Element) nl.item(i);
                    loadImportBases(el);
                    loadCausalConnectors(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("ConnectorBase > " + ex.getMessage());
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
        content += parseCausalConnectors(ident);
        
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
    
    
    protected String parseCausalConnectors(int ident) {
        if(!hasCausalConnector())
            return "";
        
        String content = "";
        for(Ec aux : connectors)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadCausalConnectors(Element element) throws XMLException {
        //create the connectors
        if(element.getTagName().equals(NCLElementAttributes.CAUSALCONNECTOR.toString())){
            Ec inst = createCausalConnector();
            addCausalConnector(inst);
            inst.load(element);
        }
    }
    
    
    /**
     * Searches for a connector inside a connectorBase and its imported bases.
     * 
     * @param alias
     *          alias of the importBase the imports the connector.
     * @param id
     *          id of the connector to be found.
     * @return 
     *          connector, reference to the connector or null if no connector
     *          was found.
     */
    public Object findConnector(String alias, String id) throws XMLException {
        Object result;
        
        if(alias == null){
            result = getCausalConnectors().get(id);
            if(result != null)
                return result;
        }
        else{
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = d.getHead().findConnector(null, id);
                    if(ref instanceof NCLCausalConnector)
                        return createExternalRef(imp, (Ec) ref);
                    else
                        return createExternalRef(imp, (Ec) ((R) ref).getTarget());
                }
            }
        }
        
        return null;
    }


    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        for(Ec c : connectors)
            c.clean();
    }
    
    
    /**
     * Function to create the child element <i>causalConnector</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>causalConnector</i>.
     */
    protected Ec createCausalConnector() throws XMLException {
        return (Ec) new NCLCausalConnector();
    }


    /**
     * Function to create a reference to a connector.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a connector.
     */
    protected R createExternalRef(Ei imp, Ec ref) throws XMLException {
        return (R) new ExternalReferenceType(imp, ref);
    }
}
