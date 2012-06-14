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
package br.uff.midiacom.util.elementList;

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;


/**
 * This class represents a list of xml elements with id. This element list does not
 * stores null elements or repeated elements.
 *
 * @param <T>
 *          the type of element stored in the list.
 */
public class IdentifiableElementList<T extends NCLIdentifiableElementPrototype>
        extends ElementList<T> {


    /**
     * Removes an element with an specific id.
     *
     * @param id
     *          id of the element to be removed.
     * @return
     *          true if the element was removed.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public boolean remove(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string.");
        if("".equals(id.trim()))
            throw new XMLException("Empty id string");

        return elements.remove(get(id));
    }


    /**
     * Returns the element with an specific id.
     *
     * @param id
     *          id of the element to be retrieved.
     * @return
     *          element of the list with the id.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public T get(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string.");
        if("".equals(id.trim()))
            throw new XMLException("Empty id string");

        for(T el : elements){
            if(el.getId().equals(id))
                return el;
        }
        return null;
    }
}
