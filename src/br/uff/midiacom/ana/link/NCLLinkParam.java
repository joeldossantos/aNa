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
package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.exception.XMLException;


/**
 * Class that represents a link parameter element. This element is used
 * to define a value to a parameter defined in a connector.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - reference to the name of the parameter defined by a
 *                    connector. This attribute is required.</li>
 *  <li><i>value</i> - value of the parameter defined by a connector. This
 *                    attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec> 
 */
public class NCLLinkParam<T extends NCLElement,
                          Ec extends NCLConnectorParam,
                          R extends ExternalReferenceType>
        extends NCLParam<T, Ec, R> {
    
    
    /**
     * Link parameter element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLLinkParam() throws XMLException {
        super();
    }
    
    
    @Override
    protected String getType() {
        return "linkParam";
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLLinkParam))
            return false;
        
        return super.compare(other);
    }
}
