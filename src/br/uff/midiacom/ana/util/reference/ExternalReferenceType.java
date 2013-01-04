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
package br.uff.midiacom.ana.util.reference;

import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import java.io.Serializable;


/**
 * This class represents a reference inside an NCL document or in an imported
 * document.
 *
 * @param <T>
 *          the type of the referred (target) element.
 * @param <I>
 *          the type of the element representing the external base where the
 *          referred element is.
 */
public class ExternalReferenceType<T extends ReferredElement,
                                   I extends NCLImport>
            implements Serializable {

    protected T target;
    protected I alias;
    
    
    /**
     * Reference constructor. Creates a reference to an element also indicating
     * where the element is.
     * 
     * <br/>
     * 
     * The target and alias element can not be null.
     *
     * @param alias
     *          element representing the element location.
     * @param target
     *          referred element.
     * @throws XMLException
     *          if one of the parameters is null.
     */
    public ExternalReferenceType(I alias, T target) throws XMLException {
        if(target == null)
            throw new XMLException("Null target element.");
        if(alias == null)
            throw new XMLException("Null alias element.");
        
        this.target = target;
        this.alias = alias;
    }
    
    
    /**
     * Get the reference target element.
     * 
     * @return
     *          referred element.
     */
    public T getTarget() {
        return target;
    }
    
    
    /**
     * Get the reference alias. The alias is null if the referred element is in
     * the same document.
     * 
     * @return
     *          element representing the element location.
     */
    public I getAlias() {
        return alias;
    }


    @Override
    public String toString() {
        String content = "";
        
        content += alias.getAlias();
        content += "#";
        
        if(target instanceof NCLIdentifiableElementPrototype)
            content += ((NCLIdentifiableElementPrototype) target).getId();
        
        return content;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof ExternalReferenceType))
            return false;
        
        boolean result = true;
        
        T aux;
        if((aux = (T) getAlias()) != null)
            result &= aux.equals(((ExternalReferenceType) o).getAlias());
        if((aux = getTarget()) != null)
            result &= aux.equals(((ExternalReferenceType) o).getTarget());
        
        return result;
    }
}
