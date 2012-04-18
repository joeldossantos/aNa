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
package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;


/**
 * Interface that represents a modification listener. This listener will receive
 * notifications as an NCL element is modified.
 */
public interface NCLModificationListener {


    /**
     * Notifies the modification listener that a child element was added to the
     * element the listener listen.
     * 
     * @param source
     *          element where the new child element was added.
     * @param setName
     *          name of the set of elements where the new child was added.
     * @param inserted 
     *          element added.
     */
    public void insertedElement(NCLElement source, NCLElementSets setName, NCLElement inserted);


    /**
     * Notifies the modification listener that a child element was removed of the
     * element the listener listen.
     * 
     * @param source
     *          element where the new child element was removed.
     * @param setName
     *          name of the set of elements where the new child was removed.
     * @param removed 
     *          element removed.
     */
    public void removedElement(NCLElement source, NCLElementSets setName, NCLElement removed);


    /**
     * Notifies the modification listener that a child element was removed of the
     * element the listener listen.
     * 
     * @param source
     *          element where the new child element was removed.
     * @param setName
     *          name of the set of elements where the new child was removed.
     * @param removed 
     *          string representing the id of the element removed.
     */
    public void removedElement(NCLElement source, NCLElementSets setName, String removed);


    /**
     * Notifies the modification listener about a change in an attribute of the
     * element the listener listen.
     * 
     * @param source
     *          element whose attribute was changed.
     * @param attributeName
     *          name of the attribute changed.
     * @param oldValue
     *          element attribute old value.
     * @param newValue 
     *          element attribute new value.
     */
    public void alteredElement(NCLElement source, NCLElementAttributes attributeName, Object oldValue, Object newValue);
}
