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
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorSwitchPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLDescriptorSwitch<T extends NCLDescriptorSwitch,
                                 P extends NCLElement,
                                 I extends NCLElementImpl,
                                 El extends NCLLayoutDescriptor,
                                 Edd extends NCLDescriptor,
                                 Ed extends DescriptorReference,
                                 Eb extends NCLDescriptorBindRule>
        extends NCLDescriptorSwitchPrototype<T, P, I, El, Edd, Ed, Eb>
        implements NCLLayoutDescriptor<El, P> {


    public NCLDescriptorSwitch() throws XMLException {
        super();
    }
    
    
    public NCLDescriptorSwitch(String id) throws XMLException {
        super();
        setId(id);
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

        content = space + "<descriptorSwitch";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</descriptorSwitch>\n";


        return content;
    }


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
        Ed aux = getDefaultDescriptor();
        if(aux == null)
            return "";
        
        String space = "";
        if(ident < 0)
            ident = 0;
        
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        return space + "<defaultDescriptor descriptor='" + aux.parse() + "'/>\n";
    }
    
    
    protected void loadDefaultDescriptor(Element element) throws XMLException {
        String att_name, att_var;
        
        // create the defaultDescriptor
        if(element.getTagName().equals(NCLElementAttributes.DEFAULTDESCRIPTOR.toString())){
            att_name = NCLElementAttributes.DESCRIPTOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDefaultDescriptor(createDescriptorRef(descriptors.get(att_var)));
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
    
    
    public El findDescriptor(FocusIndexType focusIndex) throws XMLException {
        El result;
        
        for(Edd desc : descriptors){
            result = (El) desc.findDescriptor(focusIndex);
            if(result != null)
                return result;
        }
        
        return null;
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


    /**
     * Function to create a reference to a descriptor.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a descriptor.
     */
    protected Ed createDescriptorRef(Edd ref) throws XMLException {
        return (Ed) new DescriptorReference(ref, NCLElementAttributes.ID);
    }
}
