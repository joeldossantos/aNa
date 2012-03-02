/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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
                                 Eb extends NCLDescriptorBindRule>
        extends NCLDescriptorSwitchPrototype<T, P, I, El, Eb>
        implements NCLLayoutDescriptor<El, P> {


    public NCLDescriptorSwitch(String id) throws XMLException {
        super(id);
    }
    
    
    public NCLDescriptorSwitch() throws XMLException {
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

        content = space + "<descriptorSwitch";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</descriptorSwitch>\n";


        return content;
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
    
    
    protected String parseBinds(int ident) {
        if(!hasBind())
            return "";
        
        String content = "";
        for(Eb aux : binds)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseDefaultDescriptor(int ident) {
        El aux = getDefaultDescriptor();
        if(aux == null)
            return "";
        
        String space = "";
        if(ident < 0)
            ident = 0;
        
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        return space + "<defaultDescriptor descriptor='" + aux.getId() + "'/>\n";
    }
    
    
    protected String parseDescriptors(int ident) {
        if(!hasDescriptor())
            return "";
        
        String content = "";
        for(El aux : descriptors)
            content += aux.parse(ident);
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var, ch_name;
        NodeList nl;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");
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
            // create the descriptorSwitch child nodes
            ch_name = NCLElementAttributes.DESCRIPTOR.toString();
            nl = element.getElementsByTagName(ch_name);
            for(int i=0; i < nl.getLength(); i++){
                Element el = (Element) nl.item(i);
                if(!el.getParentNode().equals(element))
                    continue;

                El inst = createDescriptor();
                addDescriptor(inst);
                inst.load(el);
            }

            // create the child nodes (ports, binds and defaultComponent)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    // create the bindRule
                    if(el.getTagName().equals(NCLElementAttributes.BINDRULE.toString())){
                        Eb inst = createBindRule();
                        addBind(inst);
                        inst.load(el);
                    }
                    // create the defaultDescriptor
                    if(el.getTagName().equals(NCLElementAttributes.DEFAULTDESCRIPTOR.toString())){
                        att_name = NCLElementAttributes.DESCRIPTOR.toString();
                        if(!(att_var = el.getAttribute(att_name)).isEmpty())
                            setDefaultDescriptor(descriptors.get(att_var));
                    }
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
    
    
    public El findDescriptor(String id) throws XMLException {
        El result;
        
        if(getId().equals(id))
            return (El) this;
        
        for(El desc : descriptors){
            result = (El) desc.findDescriptor(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public El findDescriptor(Integer focusIndex) throws XMLException {
        El result;
        
        for(El desc : descriptors){
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
    protected El createDescriptor() throws XMLException {
        return (El) new NCLDescriptor();
    }
}
