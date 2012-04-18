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
package br.uff.midiacom.ana.datatype.ncl.node;

import br.uff.midiacom.ana.datatype.aux.reference.ContextReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLCompositeNodeElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPortPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.ana.datatype.ncl.link.NCLLinkPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetaPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetadataPrototype;
import br.uff.midiacom.xml.XMLException;


/**
 * Class that represents the context node element.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the context element. This attribute is required.</li>
 *  <li><i>refer</i> - reference to a context or body elements. This attribute
 *                     is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>port</i> - element representing a context interface point. The context
 *                    can have none or several port elements.</li>
 *  <li><i>property</i> - element representing a property. The context can have
 *                        none or several property elements.</li>
 *  <li><i>media</i> - element representing a media object. The context can have
 *                     none or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The context can have
 *                       none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      context can have none or several switch elements.</li>
 *  <li><i>link</i> - element representing a link among medias or compositions.
 *                    The context can have none or several link elements.</li>
 *  <li><i>meta</i> - elements defining meta data. The context can have none or
 *                    several meta elements.</li>
 *  <li><i>metadata</i> - elements defining a RDF tree. The context can have none
 *                        or several metadata elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ept>
 * @param <Epp>
 * @param <En>
 * @param <El>
 * @param <Em>
 * @param <Emt>
 * @param <Rn> 
 */
public abstract class NCLContextPrototype<T extends NCLContextPrototype,
                                          P extends NCLElement,
                                          I extends NCLElementImpl,
                                          Ept extends NCLPortPrototype,
                                          Epp extends NCLPropertyPrototype,
                                          En extends NCLNode,
                                          El extends NCLLinkPrototype,
                                          Em extends NCLMetaPrototype,
                                          Emt extends NCLMetadataPrototype,
                                          Rn extends ContextReference>
        extends NCLCompositeNodeElement<En, P, I, Ept, Epp, En, El, Em, Emt>
        implements NCLNode<En, P> {

    protected Rn refer;
    
    
    /**
     * Context element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLContextPrototype() throws XMLException {
        super();
    }


    /**
     * Sets the reference to a context or body elements. This attribute is
     * optional. Set the reference to <i>null</i> to erase a reference already
     * defined.
     * 
     * <br/>
     * 
     * The referred element must be a context element that represents the same
     * context defined in the body of the NCL document where this context is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child nodes.
     * It can not refer to another node.
     * 
     * <br/>
     * 
     * When a context element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attribute and
     * child nodes defined by the context that makes the reference are ignored,
     * except the id attribute.
     * 
     * <br/>
     * 
     * The referred element and the one that makes the reference must be
     * considered the same, in relation to its data structure. That means that
     * a node can be represented by more than one NCL element. Since nodes inside
     * a composite node defines a set of nodes, a node can not be represented by
     * more than one node inside a composition. That means that the id attribute
     * of an NCL element representing a node is not only a unique identifier for
     * the element, but also the only identifier corresponding to a node inside
     * a composition.
     * 
     * @param refer
     *          reference to a context or body elements or <i>null</i> to erase
     *          a reference already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the context
     *          or body elements.
     */
    public void setRefer(Rn refer) throws XMLException {
        Rn aux = this.refer;
        
        this.refer = refer;
        if(this.refer != null){
            this.refer.setOwner((T) this);
            this.refer.setOwnerAtt(NCLElementAttributes.REFER);
        }
        
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
        if(aux != null)
            aux.clean();
    }


    /**
     * Return the reference to a context or body elements or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The referred element must be a context element that represents the same
     * context defined in the body of the NCL document where this context is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child nodes.
     * It can not refer to another node.
     * 
     * <br/>
     * 
     * When a context element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attribute and
     * child nodes defined by the context that makes the reference are ignored,
     * except the id attribute.
     * 
     * <br/>
     * 
     * The referred element and the one that makes the reference must be
     * considered the same, in relation to its data structure. That means that
     * a node can be represented by more than one NCL element. Since nodes inside
     * a composite node defines a set of nodes, a node can not be represented by
     * more than one node inside a composition. That means that the id attribute
     * of an NCL element representing a node is not only a unique identifier for
     * the element, but also the only identifier corresponding to a node inside
     * a composition.
     * 
     * @return 
     *          reference to a context or body elements or <i>null</i> if the
     *          attribute is not defined.
     */
    public Rn getRefer() {
        return refer;
    }
}
