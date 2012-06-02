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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.aux.basic.FocusIndexType;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a descriptor switch element. A descriptor switch element
 * represents a set of alternative descriptor to be used by a content node. The
 * descriptor to be used is chosen during presentation, according to a set of
 * rules.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the descriptor switch element. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>bindRule</i> - element relating a rule to a descriptor switch component
 *                        descriptor. The descriptor switch must have at least
 *                          one bindRule element.</li>
 *  <li><i>defaultDescriptor</i> - element representing the descriptor switch
 *                                 component descriptor to be used when no rule
 *                                 is true. This element is optional.</li>
 *  <li><i>descriptor</i> - element representing a descriptor. The descriptor
 *                          switch must have at least one descriptor element.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <El>
 * @param <Ed>
 * @param <Eb> 
 */
public class NCLDescriptorSwitch<T extends NCLDescriptorSwitch,
                                 P extends NCLElement,
                                 I extends NCLElementImpl,
                                 El extends NCLLayoutDescriptor,
                                 Edd extends NCLDescriptor,
                                 Ed extends DescriptorReference,
                                 Eb extends NCLDescriptorBindRule>
        extends NCLIdentifiableElementPrototype<El, P, I>
        implements NCLLayoutDescriptor<El, P> {

    protected IdentifiableElementList<Edd, T> descriptors;
    protected ElementList<Eb, T> binds;
    protected Edd defaultDescriptor;
    
    protected ElementList<P, P> references;


    /**
     * Descriptor switch element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorSwitch() throws XMLException {
        super();
        descriptors = new IdentifiableElementList<Edd, T>();
        binds = new ElementList<Eb, T>();
        references = new ElementList<P,P>();
    }
    
    
    public NCLDescriptorSwitch(String id) throws XMLException {
        super();
        descriptors = new IdentifiableElementList<Edd, T>();
        binds = new ElementList<Eb, T>();
        references = new ElementList<P,P>();
        setId(id);
    }


    /**
     * Adds a descriptor to the descriptor switch. The descriptor switch must
     * have at least one descriptor element.
     * 
     * @param descriptor
     *          element representing a descriptor.
     * @return
     *          true if the element representing a descriptor was added.
     * @throws XMLException 
     *          if the element representing the descriptor is null.
     */
    public boolean addDescriptor(Edd descriptor) throws XMLException {
        if(descriptors.add(descriptor, (T) this)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor of the descriptor switch. The descriptor switch must
     * have at least one descriptor element.
     * 
     * @param descriptor
     *          element representing a descriptor.
     * @return
     *          true if the element representing a descriptor was removed.
     * @throws XMLException 
     *          if the element representing the descriptor is null.
     */
    public boolean removeDescriptor(Edd descriptor) throws XMLException {
        if(descriptors.remove(descriptor)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor of the descriptor switch. The descriptor switch must
     * have at least one descriptor element.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor.
     * @return
     *          true if the descriptor was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeDescriptor(String id) throws XMLException {
        if(descriptors.remove(id)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the descriptor switch has a specific element representing
     * a descriptor. The descriptor switch must have at least one descriptor
     * element.
     * 
     * @param descriptor
     *          element representing a descriptor.
     * @return
     *          true if the descriptor switch has the descriptor element.
     * @throws XMLException 
     *          if the element representing the descriptor is null.
     */
    public boolean hasDescriptor(Edd descriptor) throws XMLException {
        return descriptors.contains(descriptor);
    }


    /**
     * Verifies if the descriptor switch has a descriptor with a specific id.
     * The descriptor switch must have at least one descriptor element.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor.
     * @return
     *          true if the descriptor switch has the descriptor element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasDescriptor(String id) throws XMLException {
        return descriptors.get(id) != null;
    }


    /**
     * Verifies if the descriptor switch has at least one descriptor. The
     * descriptor switch must have at least one descriptor element.
     * 
     * @return 
     *          true if the descriptor switch has at least one descriptor.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Returns the list of descriptors that a descriptor switch have. The
     * descriptor switch must have at least one descriptor element.
     * 
     * @return 
     *          element list with all descriptors.
     */
    public IdentifiableElementList<Edd, T> getDescriptors() {
        return descriptors;
    }


    /**
     * Adds an element relating a rule to a descriptor switch component descriptor.
     * The descriptor switch can have none or several bindRule elements.
     * 
     * @param bind
     *          element relating a rule to a descriptor switch component descriptor.
     * @return
     *          true if the element was added.
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
     * Removes an element relating a rule to a descriptor switch component
     * descriptor. The descriptor switch can have none or several bindRule elements.
     *
     * @param bind
     *          element relating a rule to a descriptor switch component descriptor.
     * @return
     *          true if the element was removed.
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
     * Verifies if the descriptor switch element has a specific element relating
     * a rule to a descriptor switch component descriptor. The descriptor switch
     * can have none or several bindRule elements.
     *
     * @param bind
     *          element relating a rule to a descriptor switch component descriptor.
     * @return
     *          true if the descriptor switch element has the bind.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifies if the descriptor switch element has at least one element relating
     * a rule to a descriptor switch component descriptor. The descriptor switch
     * can have none or several bindRule elements.
     * 
     * @return 
     *          true if the descriptor switch has at least one bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Returns the list of binds that a descriptor switch element have. The
     * descriptor switch can have none or several bindRule elements.
     * 
     * @return 
     *          element list with all binds.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }


    /**
     * Sets the element representing the descriptor switch component descriptor
     * to be used when no rule is true. This element is optional. Set the default
     * descriptor to <i>null</i> to erase a default descriptor already defined.
     * 
     * @param defaultDescriptor 
     *          element representing a reference to a descriptor switch component
     *          descriptor or <i>null</i> to erase a default descriptor already
     *          defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor
     *          switch component descriptor.
     */
    public void setDefaultDescriptor(Edd defaultDescriptor) throws XMLException {
        if(this.defaultDescriptor != null){
            impl.notifyRemoved(NCLElementSets.DEFAULTDESCRIPTOR, this.defaultDescriptor);
            this.defaultDescriptor.removeReference(this);
        }
        
        this.defaultDescriptor = defaultDescriptor;
        
        if(this.defaultDescriptor != null){
            this.defaultDescriptor.addReference(this);
            impl.notifyInserted(NCLElementSets.DEFAULTDESCRIPTOR, this.defaultDescriptor);
        }
    }


    /**
     * Returns the element representing the descriptor switch component descriptor
     * to be used when no rule is true or <i>null</i> if the attribute is not
     * defined.
     * 
     * @return 
     *          element representing a reference to a descriptor switch component
     *          descriptor or <i>null</i> if the attribute is not defined.
     */
    public Edd getDefaultDescriptor() {
        return defaultDescriptor;
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

        content = space + "<descriptorSwitch";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</descriptorSwitch>\n";


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
            
            throw new NCLParsingException("DescriptorSwitch" + aux + ":\n" + ex.getMessage());
        }

        try{
            loadDescriptors(element);

            // create the child nodes (ports, binds and defaultComponent)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadBinds(el);
                    loadDefaultDescriptor(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("DescriptorSwitch" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseBinds(ident);
        content += parseDefaultDescriptor(ident);
        content += parseDescriptors(ident);
        
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
    
    
    protected String parseBinds(int ident) {
        if(!hasBind())
            return "";
        
        String content = "";
        for(Eb aux : binds)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadBinds(Element element) throws XMLException {
        // create the bindRule
        if(element.getTagName().equals(NCLElementAttributes.BINDRULE.toString())){
            Eb inst = createBindRule();
            addBind(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseDefaultDescriptor(int ident) {
        Edd aux = getDefaultDescriptor();
        if(aux == null)
            return "";
        
        String space = "";
        if(ident < 0)
            ident = 0;
        
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        return space + "<defaultDescriptor descriptor='" + aux.getId() + "'/>\n";
    }
    
    
    protected void loadDefaultDescriptor(Element element) throws XMLException {
        String att_name, att_var;
        
        // create the defaultDescriptor
        if(element.getTagName().equals(NCLElementAttributes.DEFAULTDESCRIPTOR.toString())){
            att_name = NCLElementAttributes.DESCRIPTOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDefaultDescriptor(descriptors.get(att_var));
        }
    }
    
    
    protected String parseDescriptors(int ident) {
        if(!hasDescriptor())
            return "";
        
        String content = "";
        for(Edd aux : descriptors)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadDescriptors(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the descriptorSwitch child nodes
        ch_name = NCLElementAttributes.DESCRIPTOR.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            if(!el.getParentNode().equals(element))
                continue;

            Edd inst = createDescriptor();
            addDescriptor(inst);
            inst.load(el);
        }
    }
    
    
    @Override
    public El findDescriptor(String id) throws XMLException {
        El result;
        
        if(getId().equals(id))
            return (El) this;
        
        for(Edd desc : descriptors){
            result = (El) desc.findDescriptor(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    @Override
    public El findDescriptor(Object focusIndex) throws XMLException {
        El result;
        
        for(Edd desc : descriptors){
            result = (El) desc.findDescriptor(focusIndex);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    @Override
    public boolean addReference(P reference) throws XMLException {
        return references.add(reference, null);
    }
    
    
    @Override
    public boolean removeReference(P reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ElementList<P, P> getReferences() {
        return references;
    }


    /**
     * Function to create the child element <i>bindRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindRule</i>.
     */
    protected Eb createBindRule() throws XMLException {
        return (Eb) new NCLDescriptorBindRule();
    }


    /**
     * Function to create the child element <i>descriptor</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptor</i>.
     */
    protected Edd createDescriptor() throws XMLException {
        return (Edd) new NCLDescriptor();
    }
}
