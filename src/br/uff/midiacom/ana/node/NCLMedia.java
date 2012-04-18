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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.NodeReference;
import br.uff.midiacom.ana.datatype.aux.reference.PostReferenceElement;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLInstanceType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.datatype.ncl.node.NCLMediaPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLMedia<T extends NCLMedia,
                      P extends NCLElement,
                      I extends NCLElementImpl,
                      Ea extends NCLArea,
                      Ep extends NCLProperty,
                      Ed extends DescriptorReference,
                      En extends NCLNode,
                      Ei extends NCLInterface,
                      Rn extends NodeReference>
        extends NCLMediaPrototype<T, P, I, Ea, Ep, Ed, En, Rn>
        implements NCLNode<En, P, Ei>, PostReferenceElement {
    
    
    public NCLMedia(String id) throws XMLException {
        super(id);
    }


    public NCLMedia() throws XMLException {
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
        
        
        // <media> element and attributes declaration
        content = space + "<media";
        content += parseAttributes();
        
        // Test if the media has content
        if(hasArea() || hasProperty()){
            content += ">\n";
            
            content += parseElements(ident + 1);
            
            content += space + "</media>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseSrc();
        content += parseRefer();
        content += parseInstance();
        content += parseType();
        content += parseDescriptor();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseAreas(ident);
        content += parseProperties(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseSrc() {
        SrcType aux = getSrc();
        if(aux != null)
            return " src='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseType() {
        NCLMimeType aux = getType();
        if(aux != null)
            return " type='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseDescriptor() {
        Ed aux = getDescriptor();
        if(aux != null)
            return " descriptor='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseRefer() {
        Rn aux = getRefer();
        if(aux != null)
            return " refer='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseInstance() {
        NCLInstanceType aux = getInstance();
        if(aux != null)
            return " instance='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseAreas(int ident) {
        if(!hasArea())
            return "";
        
        String content = "";
        for(Ea aux : areas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseProperties(int ident) {
        if(!hasProperty())
            return "";
        
        String content = "";
        for(Ep aux : properties)
            content += aux.parse(ident);
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the src (optional)
            att_name = NCLElementAttributes.SRC.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setSrc(new SrcType(att_var));

            // set the type (optional)
            att_name = NCLElementAttributes.TYPE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setType(NCLMimeType.getEnumType(att_var));

            // set the descriptor (optional)
            att_name = NCLElementAttributes.DESCRIPTOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                setDescriptor((Ed) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), att_var));
            }

            // set the instance (optional)
            att_name = NCLElementAttributes.INSTANCE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setInstance(NCLInstanceType.getEnumType(att_var));
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Media" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    //create the areas
                    if(el.getTagName().equals(NCLElementAttributes.AREA.toString())){
                        Ea inst = createArea();
                        addArea(inst);
                        inst.load(el);
                    }
                    // create the properties
                    if(el.getTagName().equals(NCLElementAttributes.PROPERTY.toString())){
                        Ep inst = createProperty();
                        addProperty(inst);
                        inst.load(el);
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
            
            throw new NCLParsingException("Media" + aux + " > " + ex.getMessage());
        }

        try{
            // set the refer (optional)
            att_name = NCLElementAttributes.REFER.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                En ref = (En) new NCLMedia(att_var);
                setRefer(createNodeRef(ref));
                NCLReferenceManager.getInstance().waitReference(this);
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Media" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    public Ei findInterface(String id) throws XMLException {
        Ei result;
        
        // search as a property
        result = (Ei) properties.get(id);
        if(result != null)
            return result;
        
        // search as an area
        result = (Ei) areas.get(id);
        if(result != null)
            return result;
        
        return null;
    }
    
    
    public En findNode(String id) {
        if(getId().equals(id))
            return (En) this;
        else
            return null;
    }
    
    
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // set the refer (optional)
            if((aux = ((En) getRefer().getTarget()).getId()) != null){
                En ref = (En) NCLReferenceManager.getInstance().findNodeReference(impl.getDoc(), aux);
                setRefer(createNodeRef(ref));
            }
        }
        catch(XMLException ex){
            aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Media" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
    }


    /**
     * Function to create the child element <i>area</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>area</i>.
     */
    protected Ea createArea() throws XMLException {
        return (Ea) new NCLArea();
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Ep createProperty() throws XMLException {
        return (Ep) new NCLProperty();
    }


    /**
     * Function to create a reference to a node.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a node.
     */
    protected Rn createNodeRef(En ref) throws XMLException {
        return (Rn) new NodeReference(ref, NCLElementAttributes.ID);
    }


    /**
     * Function to create a reference to a descriptor.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a descriptor.
     */
    protected Ed createDescriptorRef(NCLLayoutDescriptor ref) throws XMLException {
        return (Ed) new DescriptorReference(ref, NCLElementAttributes.ID);
    }
}
