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

import br.uff.midiacom.xml.XMLElement;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


/**
 * Interface that represents an NCL element.
 * 
 * @param <T>
 * @param <P> 
 */
public interface NCLElement<T extends NCLElement,
                            P extends NCLElement>
        extends XMLElement<T, P> {

    
    /**
     * Adds a modification listener to the NCL element. The modification listener
     * will receive notifications as the NCL element is changed. The modification
     * listener is optional, set it to <i>null</i> to erase the modification
     * listener already defined.
     *
     * @param listener
     *          element to receive notifications about the NCL element modification
     *          or null to erase the modification listener already defined.
     */
    public void setModificationListener(NCLModificationListener listener);


    /**
     * Returns the modification listener of the NCL element or <i>null</i> if
     * no modification listener is defined. The modification listener will
     * receive notifications as the NCL element is changed.
     *
     * @return
     *          element to receive notifications about the NCL element modification
     *          or null if no modification listener is defined.
     */
    public NCLModificationListener getModificationListener();
    
    
    /**
     * Loads the NCL element from a DOM element. This method is used to load an
     * NCL document from a file.
     * 
     * @param element
     *          DOM element representing the XML element to be loaded
     * @throws XMLException 
     *          if any error occur while loading the NCL element.
     */
    public void load(Element element) throws XMLException;
}
