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

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLBase;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.util.elementList.ElementList;
import br.uff.midiacom.util.elementList.IdentifiableElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a base of descriptors.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the base of descriptors. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>importBase</i> - element that imports a descriptor base defined in another
 *                          NCL document. The base can have none or several import
 *                          elements.</li>
 *  <li><i>descriptor</i> - element representing a descriptor inside the base. The
 *                          base can have none or several descriptor elements.</li>
 *  <li><i>descriptorSwitch</i> - element representing a descriptor switch inside
 *                                the base. The base can have none or several
 *                                descriptor switch elements.</li>
 * </ul>
 * 
 * Note that the base of descriptors must have at least one child element, which
 * can be a import, a descriptor or a descriptor switch.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <El>
 * @param <Ei> 
 */
public class NCLDescriptorBase<T extends NCLElement,
                               El extends NCLLayoutDescriptor,
                               Ei extends NCLImportBase,
                               R extends ExternalReferenceType>
        extends NCLBase<T, Ei> {

    protected IdentifiableElementList<El> descriptors;


    /**
     * Base of descriptors constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorBase() {
        super();
        descriptors = new IdentifiableElementList<El>();
    }


    /**
     * Adds a descriptor or a descriptor switch to the base of descriptors. The
     * base of descriptors can have none or several descriptor or descriptor
     * switch elements.
     * 
     * @param descriptor
     *          element representing a descriptor or a descriptor switch.
     * @return
     *          true if the element representing a descriptor or descriptor
     *          switch was added.
     * @throws XMLException 
     *          if the element representing the descriptor or descriptor switch
     *          is null.
     */
    public boolean addDescriptor(El descriptor) throws XMLException {
        if(descriptors.add(descriptor)){
            notifyInserted((T) descriptor);
            descriptor.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor or a descriptor switch of the base of descriptors.
     * The base of descriptors can have none or several descriptor or descriptor
     * switch elements.
     * 
     * @param descriptor
     *          element representing a descriptor or a descriptor switch.
     * @return
     *          true if the element representing a descriptor or descriptor
     *          switch was removed.
     * @throws XMLException 
     *          if the element representing the descriptor or descriptor switch
     *          is null.
     */
    public boolean removeDescriptor(El descriptor) throws XMLException {
        if(descriptors.remove(descriptor)){
            notifyRemoved((T) descriptor);
            descriptor.setParent(null);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor or a descriptor switch of the base of descriptors.
     * The base of descriptors can have none or several descriptor or descriptor
     * switch elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor or a descriptor switch.
     * @return
     *          true if the descriptor or descriptor switch was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeDescriptor(String id) throws XMLException {
        El aux = descriptors.get(id);
        if(descriptors.remove(aux)){
            notifyRemoved((T) aux);
            aux.setParent(null);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the base of descriptors has a specific element representing
     * a descriptor or descriptor switch. The base of descriptors can have none
     * or several descriptor or descriptor switch elements.
     * 
     * @param descriptor
     *          element representing a descriptor or descriptor switch.
     * @return
     *          true if the base of descriptors has the descriptor or descriptor
     *          switch element.
     * @throws XMLException 
     *          if the element representing the descriptor or descriptor switch
     *          is null.
     */
    public boolean hasDescriptor(El descriptor) throws XMLException {
        return descriptors.contains(descriptor);
    }


    /**
     * Verifies if the base of descriptors has a descriptor or descriptor switch
     * with a specific id. The base of descriptors can have none or several
     * descriptor or descriptor switch elements.
     * 
     * @param id
     *          string representing the id of the element representing a
     *          descriptor or descriptor switch.
     * @return
     *          true if the base of descriptors has the descriptor or descriptor
     *          switch element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasDescriptor(String id) throws XMLException {
        return descriptors.get(id) != null;
    }


    /**
     * Verifies if the base of descriptors has at least one descriptor or
     * descriptor switch. The base of descriptors can have none or several
     * descriptor or descriptor switch elements.
     * 
     * @return 
     *          true if the base of descriptors has at least one descriptor or
     *          descriptor switch.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Returns the list of descriptors and descriptor switches that a base of
     * descriptors have. The base of descriptors can have none or several
     * descriptor or descriptor switch elements.
     * 
     * @return 
     *          element list with all descriptors and descriptor switches.
     */
    public IdentifiableElementList<El> getDescriptors() {
        return descriptors;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLDescriptorBase))
            return false;
        
        boolean result = true;
        ElementList<El> otherdes = ((NCLDescriptorBase) other).getDescriptors();
        
        result &= super.compareImports((NCLBase) other);
        
        result &= descriptors.size() == otherdes.size();
        for (El des : descriptors) {
            try {
                result &= otherdes.contains(des);
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

        content = space + "<descriptorBase";
        content += parseAttributes();

        if(hasDescriptor() || hasImportBase()){
            content += ">\n";

            content += parseElements(ident + 1);
            
            content += space + "</descriptorBase>\n";
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
            throw new NCLParsingException("DescriptorBase:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadImportBases(el);
                    loadDescriptors(el);
                    loadDescriptorSwitches(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("DescriptorBase > " + ex.getMessage());
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
        
        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
    }
    
    
    protected String parseDescriptors(int ident) {
        if(!hasDescriptor())
            return "";
        
        String content = "";
        for(El aux : descriptors)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadDescriptors(Element element) throws XMLException {
        //create the descriptor
        if(element.getTagName().equals(NCLElementAttributes.DESCRIPTOR.toString())){
            El inst = createDescriptor();
            addDescriptor(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadDescriptorSwitches(Element element) throws XMLException {
        // create the descriptorSwitch
        if(element.getTagName().equals(NCLElementAttributes.DESCRIPTORSWITCH.toString())){
            El inst = createDescriptorSwitch();
            addDescriptor(inst);
            inst.load(element);
        }
    }
    
    
    /**
     * Searches for a descriptor inside the descriptorBase and its descendants.
     * The descriptor can be a descriptor or a descriptorSwitch.
     * 
     * @param id
     *          id of the descriptor to be found.
     * @return 
     *          interface or null if no descriptor was found.
     */
    public Object findDescriptor(String id) throws XMLException {
        Object result;
        
        if(!id.contains("#")){
            for(El desc : descriptors){
                result = (El) desc.findDescriptor(id);
                if(result != null)
                    return result;
            }   
        }
        else{
            int index = id.indexOf("#");
            String alias = id.substring(0, index);
            id = id.substring(index + 1);
            
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = findDescriptorReference(d, id);
                    if(ref instanceof NCLLayoutDescriptor)
                        return createExternalRef(imp, (El) ref);
                    else
                        return createExternalRef(imp, (El) ((R) ref).getTarget());
                }
            }
            
            NCLImportedDocumentBase ib = (NCLImportedDocumentBase) ((NCLHead) getParent()).getImportedDocumentBase();
            for(Ei imp : (ElementList<Ei>) ib.getImportNCLs()){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    Object ref = findDescriptorReference(d, id);
                    if(ref instanceof NCLLayoutDescriptor)
                        return createExternalRef(imp, (El) ref);
                    else
                        return createExternalRef(imp, (El) ((R) ref).getTarget());
                }
            }
        }
        
        
        return null;
    }
    
    
    /**
     * Searches for a descriptor inside a descriptorBase and its descendants.
     * 
     * @param focusIndex
     *          focusIndex of the descriptor to be found.
     * @return 
     *          descriptor or null if no descriptor was found.
     */
    public El findDescriptor(Object focusIndex) throws XMLException {
        El result;
        
        for(El desc : descriptors){
            result = (El) desc.findDescriptor(focusIndex);
            if(result != null)
                return result;
        }
        
        for(Ei imp : imports){
            NCLDoc d = (NCLDoc) imp.getImportedDoc();
            result = (El) findDescriptorReference(d, focusIndex);
            if(result != null)
                return result;
        }

        NCLImportedDocumentBase ib = (NCLImportedDocumentBase) ((NCLHead) getParent()).getImportedDocumentBase();
        for(Ei imp : (ElementList<Ei>) ib.getImportNCLs()){
            NCLDoc d = (NCLDoc) imp.getImportedDoc();
            result = (El) findDescriptorReference(d, focusIndex);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public Object findDescriptorReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        Object result = base.findDescriptor(id);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with id: " + id);
        
        return result;
    }
    
    
    protected El findDescriptorReference(NCLDoc doc, Object focusIndex) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        El result = (El) base.findDescriptor(focusIndex);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with focusIndex: " + focusIndex);
        
        return result;
    }


    /**
     * Function to create the child element <i>descriptor</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptor</i>.
     */
    protected El createDescriptor() throws XMLException {
        return (El) new NCLDescriptor();
    }


    /**
     * Function to create the child element <i>descriptorSwitch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorSwitch</i>.
     */
    protected El createDescriptorSwitch() throws XMLException {
        return (El) new NCLDescriptorSwitch();
    }


    /**
     * Function to create a reference to a descriptor.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a descriptor.
     */
    protected R createExternalRef(Ei imp, El ref) throws XMLException {
        return (R) new ExternalReferenceType(imp, ref);
    }
}
