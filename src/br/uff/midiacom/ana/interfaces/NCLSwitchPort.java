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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Class that represents a switch port element. A switch port maps alternative
 * interface points of the nodes inside a switch element.
 * 
 * <br/>
 * 
 * When a node or a node interface point is mapped by a port, every action over
 * the port is reflected to the node or node interface point mapped by it. If the
 * port interface attribute is not defined, it is assumed that the port maps
 * the whole node.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the switch port element. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>mapping</i> - element that maps a switch inner node interface point.
 *                       The switch port must have at least one mapping element.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Em>
 * @param <Ei> 
 */
public class NCLSwitchPort<T extends NCLElement,
                           Em extends NCLMapping>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLInterface<T> {

    protected ElementList<Em> mappings;
    
    protected ArrayList<T> references;


    /**
     * Switch port element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSwitchPort() {
        super();
        mappings = new ElementList<Em>();
        references = new ArrayList<T>();
    }
    
    
    public NCLSwitchPort(String id) throws XMLException {
        super();
        mappings = new ElementList<Em>();
        references = new ArrayList<T>();
        setId(id);
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Em aux : mappings) {
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
     * Adds an element that maps a switch inner node interface point to the
     * switch port. The switch port must have at least one mapping element.
     * 
     * @param mapping
     *          element that maps a switch inner node interface point.
     * @return
     *          true if the mapping was added.
     * @throws XMLException 
     *          if the element representing the mapping is null.
     */
    public boolean addMapping(Em mapping) throws XMLException {
        if(mappings.add(mapping)){
            notifyInserted((T) mapping);
            mapping.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element that maps a switch inner node interface point of the
     * switch port. The switch port must have at least one mapping element.
     * 
     * @param mapping
     *          element that maps a switch inner node interface point.
     * @return
     *          true if the mapping was removed.
     * @throws XMLException 
     *          if the element representing the mapping is null.
     */
    public boolean removeMapping(Em mapping) throws XMLException {
        if(mappings.remove(mapping)){
            notifyRemoved((T) mapping);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the switch port has a specific element that maps a switch
     * inner node interface point of the switch port. The switch port must have
     * at least one mapping element.
     * 
     * @param mapping
     *          element that maps a switch inner node interface point.
     * @return
     *          true if the switch port has the mapping element.
     * @throws XMLException 
     *          if the element representing the mapping is null.
     */
    public boolean hasMapping(Em mapping) throws XMLException {
        return mappings.contains(mapping);
    }


    /**
     * Verifies if the switch port has at least one element that maps a switch
     * inner node interface point of the switch port. The switch port must have
     * at least one mapping element.
     * 
     * @return 
     *          true if the switch port has at least one mapping.
     */
    public boolean hasMapping() {
        return !mappings.isEmpty();
    }


    /**
     * Returns the list of mapping that a switch port have. The switch port must have
     * at least one mapping element.
     * 
     * @return 
     *          element list with all mappings.
     */
    public ElementList<Em> getMappings() {
        return mappings;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLSwitchPort))
            return false;
        
        boolean result = true;
        
        String aux;
        if((aux = getId()) != null)
            result &= aux.equals(((NCLSwitchPort) other).getId());
        
        
        ElementList<Em> othermap = ((NCLSwitchPort) other).getMappings();
        result &= mappings.size() == othermap.size();
        for (Em map : mappings) {
            try {
                result &= othermap.contains(map);
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


        // <port> element and attributes declaration
        content = space + "<switchPort";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</switchPort>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("SwitchPort" + aux + ":\n" + ex.getMessage());
        }

        try{
            loadMappings(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("SwitchPort" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseMappings(ident);
        
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
    
    
    protected String parseMappings(int ident) {
        if(!hasMapping())
            return "";
        
        String content = "";
        for(Em aux : mappings)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMappings(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the child nodes
        ch_name = NCLElementAttributes.MAPPING.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Em inst = createMapping();
            addMapping(inst);
            inst.load(el);
        }
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(T reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(T reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    @Deprecated
    public ArrayList getReferences() {
        return references;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        for(Em m : mappings)
            m.clean();
    }
    

    /**
     * Function to create the child element <i>mapping</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>mapping</i>.
     */
    protected Em createMapping() throws XMLException {
        return (Em) new NCLMapping();
    }
}
