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
package br.uff.midiacom.ana.datatype.ncl.structure;

import br.uff.midiacom.ana.datatype.ncl.NCLCompositeNodeElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPortPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.ana.datatype.ncl.link.NCLLinkPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetaPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetadataPrototype;
import br.uff.midiacom.ana.datatype.ncl.node.NCLNode;
import br.uff.midiacom.xml.XMLException;


/**
 * Class that represents the NCL document body element. The body is a special
 * type of context node, representing the most external document context.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the document body element. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>port</i> - element representing a body interface point. The body can
 *                    have none or several port elements.</li>
 *  <li><i>property</i> - element representing a property. The body can have none
 *                        or several property elements.</li>
 *  <li><i>media</i> - element representing a media object. The body can have none
 *                     or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The body can have
 *                       none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      body can have none or several switch elements.</li>
 *  <li><i>link</i> - element representing a link among medias or compositions.
 *                    The body can have none or several link elements.</li>
 *  <li><i>meta</i> - elements defining meta data. The body can have none or several
 *                    meta elements.</li>
 *  <li><i>metadata</i> - elements defining a RDF tree. The body can have none
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
 */
public abstract class NCLBodyPrototype<T extends NCLBodyPrototype,
                                       P extends NCLElement,
                                       I extends NCLElementImpl,
                                       Ept extends NCLPortPrototype,
                                       Epp extends NCLPropertyPrototype,
                                       En extends NCLNode,
                                       El extends NCLLinkPrototype,
                                       Em extends NCLMetaPrototype,
                                       Emt extends NCLMetadataPrototype>
        extends NCLCompositeNodeElement<En, P, I, Ept, Epp, En, El, Em, Emt>
        implements NCLNode<En, P> {

    
    /**
     * Body element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLBodyPrototype() throws XMLException {
        super();
    }
}
