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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLInstanceType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.datatype.ncl.node.NCLMediaPrototype;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLMedia<T extends NCLMedia, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLArea, Ep extends NCLProperty, Ed extends NCLLayoutDescriptor, En extends NCLNode, Ei extends NCLInterface>
        extends NCLMediaPrototype<T, P, I, Ea, Ep, Ed, En> implements NCLNode<En, P, Ei> {
    
    
    public NCLMedia(String id) throws XMLException {
        super(id);
    }


    public NCLMedia(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }

    
    @Override
    public void setSrc(SrcType src) {
        SrcType aux = this.src;
        super.setSrc(src);
        impl.notifyAltered(NCLElementAttributes.SRC, aux, src);
    }
    
    
    @Override
    public void setType(NCLMimeType type) {
        NCLMimeType aux = this.type;
        super.setType(type);
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }
    
        
    @Override
    public void setDescriptor(Ed descriptor) {
        Ed aux = this.descriptor;
        super.setDescriptor(descriptor);
        impl.notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, descriptor);
    }
    

    @Override
    public void setRefer(T refer) {
        T aux = this.refer;
        super.setRefer(refer);
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    @Override
    public void setInstance(NCLInstanceType instance) {
        NCLInstanceType aux = this.instance;
        super.setInstance(instance);
        impl.notifyAltered(NCLElementAttributes.INSTANCE, aux, instance);
    }

    
    @Override
    public boolean addArea(Ea area) throws XMLException {
        if(super.addArea(area)){
            impl.notifyInserted(NCLElementSets.AREAS, area);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean removeArea(String id) throws XMLException {
        if(super.removeArea(id)){
            impl.notifyRemoved(NCLElementSets.AREAS, id);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean removeArea(Ea area) throws XMLException {
        if(super.removeArea(area)){
            impl.notifyRemoved(NCLElementSets.AREAS, area);
            return true;
        }
        return false;
    }
    
        
    @Override
    public boolean addProperty(Ep property) throws XMLException {
        if(super.addProperty(property)){
            impl.notifyInserted(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeProperty(String name) throws XMLException {
        if(super.removeProperty(name)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, name);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeProperty(Ep property) throws XMLException {
        if(super.removeProperty(property)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;
        NodeList nl;

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
            Ed desc = (Ed) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), att_var);
            setDescriptor(desc);
        }

        // set the instance (optional)
        att_name = NCLElementAttributes.INSTANCE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setInstance(NCLInstanceType.getEnumType(att_var));

        // create the child nodes
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                //create the areas
                if(el.getTagName().equals(NCLElementAttributes.AREA.toString()))
                    addArea(createArea(el));
                // create the properties
                if(el.getTagName().equals(NCLElementAttributes.PROPERTY.toString()))
                    addProperty(createProperty(el));
            }
        }

        // set the refer (optional)
        att_name = NCLElementAttributes.REFER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T ref = (T) NCLReferenceManager.getInstance().findNodeReference(impl.getDoc(), att_var);
            setRefer(ref);
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
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


    /**
     * Function to create the child element <i>area</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>area</i>.
     */
    protected Ea createArea(Element element) throws XMLException {
        return (Ea) new NCLArea(element);
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Ep createProperty(Element element) throws XMLException {
        return (Ep) new NCLProperty(element);
    }
}
