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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.reference.ExternalReferenceType;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.NCLBase;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import org.w3c.dom.Element;
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
public class NCLConnectorBase<T extends NCLConnectorBase,
                              P extends NCLElement,
                              I extends NCLElementImpl,
                              Ec extends NCLCausalConnector,
                              Ei extends NCLImport>
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
    public NCLConnectorBase() throws XMLException {
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
        String att_name, att_var;
        NodeList nl;

        try{
            loadId(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("ConnectorBase:\n" + ex.getMessage());
        }
        
        try{
            loadImportBases(element);
            loadCausalConnectors(element);
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
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadImportBases(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the import child nodes
        ch_name = NCLElementAttributes.IMPORTBASE.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ei inst = createImportBase();
            addImportBase(inst);
            inst.load(el);
        }
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
        String ch_name;
        NodeList nl;
        
        // create the region child nodes
        ch_name = NCLElementAttributes.CAUSALCONNECTOR.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ec inst = createCausalConnector();
            addCausalConnector(inst);
            inst.load(el);
        }
    }
    
    
    /**
     * Searches for a connector inside a connectorBase and its imported bases.
     * 
     * @param focusIndex
     *          focusIndex of the descriptor to be found.
     * @return 
     *          connector, reference to the connector or null if no connector
     *          was found.
     */
    public Object findConnector(String id) throws XMLException {
        Ec result;
        
        if(!id.contains("#")){
            result = getCausalConnectors().get(id);
            if(result != null)
                return result;
        }
        else{
            int index = id.indexOf("#");
            String alias = id.substring(0, index);
            id = id.substring(index + 1);
            
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = NCLReferenceManager.getInstance().findConnectorReference(d, id);
                    if(ref instanceof NCLCausalConnector)
                        return createExternalRef(imp, (Ec) ref);
                    else
                        return createExternalRef(imp, (Ec) ((ExternalReferenceType) ref).getTarget());
                }
            }
        }
        
        
        return null;
    }


    /**
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected Ei createImportBase() throws XMLException {
        return (Ei) new NCLImport(NCLImportType.BASE);
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
    protected ExternalReferenceType createExternalRef(Ei imp, Ec ref) throws XMLException {
        return new ExternalReferenceType(imp, ref);
    }
}
