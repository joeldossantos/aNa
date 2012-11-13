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

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLBindConstituent;


/**
 * Interface that represents a node element of the NCL language. A node may
 * represent a media object inside the document or a set of other nodes, being
 * used to structure the document.
 * 
 * @param <T>
 * @param <P> 
 */
public interface NCLNode<T extends NCLElement,
                         En extends NCLNode,
                         Ei extends NCLInterface>
        extends NCLElement<T>, ReferredElement<T>, NCLBindConstituent<T> {

    
    /**
     * Searches for a interface inside an node and its descendants. The interface
     * could be: area, property, port, switchPort.
     * 
     * @param id
     *          id of the interface to be found.
     * @return 
     *          interface or null if no interface was found.
     */
    public Ei findInterface(String id) throws XMLException;
    
    
    /**
     * Searches for an node inside an node and its descendants. The node will be
     * searched inside contexts and switches.
     * 
     * @param id
     *          id of the node to be found.
     * @return 
     *          node or null if no node was found.
     */
    public En findNode(String id) throws XMLException;
    
    
    @Deprecated
    public void setDoc(T doc);
}
