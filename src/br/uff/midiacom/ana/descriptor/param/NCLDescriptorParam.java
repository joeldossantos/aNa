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
package br.uff.midiacom.ana.descriptor.param;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.xml.XMLException;


/**
 * Interface that represents a descriptor parameter. This element is used to
 * parameterize the presentation of the node associated to a descriptor. The
 * descriptorParam may redefine the value of an attribute defined by a region
 * element or define new attributes for the node presentation.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the descriptor parameter. This attribute is required.</li>
 *  <li><i>value</i> - value of the descriptor parameter. This attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <V> 
 */
public interface NCLDescriptorParam<T extends NCLDescriptorParam,
                                    P extends NCLElement,
                                    V>
        extends NCLElement<T, P> {


    /**
     * Sets the name of the descriptor parameter. The element name represents
     * an attribute of descriptor related node presentation. This attribute is
     * required and can not be set to <i>null</i>. The possible parameter names
     * to be used are defined in the enumeration <i>NCLAttributes</i>.
     * 
     * @param name 
     *          parameter name from the enumeration <i>NCLAttributes</i>.
     * @throws XMLException 
     *          if the value representing the name is null.
     */
    public void setName(NCLAttributes name) throws XMLException;


    /**
     * Returns the name of the descriptor parameter or <i>null</i> if the
     * attribute is not defined. The element name represents an attribute of
     * descriptor related node presentation. The possible parameter names
     * to be used are defined in the enumeration <i>NCLAttributes</i>.
     * 
     * @return
     *          parameter name from the enumeration <i>NCLAttributes</i> or
     *          <i>null</i> if the attribute is not defined.
     */
    public NCLAttributes getName();


    /**
     * Sets the value of the descriptor parameter. This attribute is required
     * and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * Each parameter have its possible value types. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @param value 
     *          value of the descriptor parameter. The type of the value will
     *          depends on the parameter name.
     * @throws XMLException 
     *          if the value is null.
     */
    public void setValue(V value) throws XMLException;


    /**
     * Returns the value of the descriptor parameter or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * Each parameter have its possible value types. See the reference
     * (ABNT NBR 15606-2) to more information.
     *
     * @return
     *          value of the descriptor parameter or <i>null</i> if the attribute
     *          is not defined. The type of the value will depends on the
     *          parameter name
     */
    public V getValue();
}
