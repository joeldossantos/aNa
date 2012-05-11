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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.aux.basic.FocusIndexType;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLDescriptorBase<T extends NCLDescriptorBase,
                               P extends NCLElement,
                               I extends NCLElementImpl,
                               El extends NCLLayoutDescriptor,
                               Ei extends NCLImport>
        extends NCLDescriptorBasePrototype<T, P, I, El, Ei>
        implements NCLIdentifiableElement<T, P> {


    public NCLDescriptorBase() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


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
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadImportBases(Element element) throws XMLException {
        //create the imports
        if(element.getTagName().equals(NCLElementAttributes.IMPORTBASE.toString())){
            Ei inst = createImportBase();
            addImportBase(inst);
            inst.load(element);
        }
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
    public DescriptorReference findDescriptor(String id) throws XMLException {
        El result;
        
        if(!id.contains("#")){
            for(El desc : descriptors){
                result = (El) desc.findDescriptor(id);
                if(result != null)
                    return new DescriptorReference(result, NCLElementAttributes.ID);
            }   
        }
        else{
            int index = id.indexOf("#");
            String alias = id.substring(0, index);
            id = id.substring(index + 1);
            
            for(Ei imp : imports){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    DescriptorReference ref = findDescriptorReference(d, id);
                    return new DescriptorReference(imp, (El) ref.getTarget(), (NCLElementAttributes) ref.getTargetAtt());
                }
            }
            
            NCLImportedDocumentBase ib = (NCLImportedDocumentBase) ((NCLHead) getParent()).getImportedDocumentBase();
            for(Ei imp : (ElementList<Ei, NCLImportedDocumentBase>) ib.getImportNCLs()){
                if(imp.getAlias().equals(alias)){
                    NCLDoc d = (NCLDoc) imp.getImportedDoc();
                    DescriptorReference ref = findDescriptorReference(d, id);
                    return new DescriptorReference(imp, (El) ref.getTarget(), (NCLElementAttributes) ref.getTargetAtt());
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
    public El findDescriptor(FocusIndexType focusIndex) throws XMLException {
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
        for(Ei imp : (ElementList<Ei, NCLImportedDocumentBase>) ib.getImportNCLs()){
            NCLDoc d = (NCLDoc) imp.getImportedDoc();
            result = (El) findDescriptorReference(d, focusIndex);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public DescriptorReference findDescriptorReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        DescriptorReference result = base.findDescriptor(id);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with id: " + id);
        
        return result;
    }
    
    
    protected NCLDescriptor findDescriptorReference(NCLDoc doc, FocusIndexType focusIndex) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        NCLDescriptor result = (NCLDescriptor) base.findDescriptor(focusIndex);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with focusIndex: " + focusIndex);
        
        return result;
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
}
