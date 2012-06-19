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
package br.uff.midiacom.ana.util.ncl;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;


/**
 * Class that implements the XMLIdentifiableElement interface.
 *
 * @param <T>
 *          XML element type.
 */
public abstract class NCLIdentifiableElementPrototype<T extends NCLElement>
        extends NCLElementPrototype<T>
        implements NCLElement<T> {

    protected String id;
    
    
    public NCLIdentifiableElementPrototype() {
        super();
    }


    /**
     * Sets the XML element id attribute.
     *
     * @param id
     *          string representing the element id.
     * @throws IllegalArgumentException
     *          if the id is not valid.
     */
    public void setId(String id) throws XMLException {
        if(id != null && "".equals(id.trim()))
            throw new XMLException("Empty id String");
        if(!validate(id))
            throw new XMLException("Invalid identifier: " + id);
        
        String aux = this.id;
        this.id = id;
        notifyAltered(NCLElementAttributes.ID, aux, id);
    }


    /**
     * Returns the XML element id attribute.
     *
     * @return
     *          string representing the element id.
     */
    public String getId() {
        return id;
    }
}
