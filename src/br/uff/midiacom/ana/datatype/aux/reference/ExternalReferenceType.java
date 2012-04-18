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
package br.uff.midiacom.ana.datatype.aux.reference;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;


/**
 * This class represents a reference inside an NCL document or in an imported
 * document.
 *
 * @param <O>
 *          the type of the reference owner element.
 * @param <T>
 *          the type of the referred (target) element.
 * @param <I>
 *          the type of the element representing the external base where the
 *          referred element is.
 */
public abstract class ExternalReferenceType<O extends NCLElement,
                                    T extends ReferredElement,
                                    I extends NCLImportPrototype,
                                    A extends NCLElementAttributes>
        extends br.uff.midiacom.xml.datatype.reference.ReferenceType<O, T, A> {

    protected I alias;
    

    /**
     * Reference constructor. Creates a reference to an element without presenting
     * any reference to where the element is.
     *
     * @param target
     *          referred element.
     * @param targetAtt
     *          referred element attribute.
     * @throws XMLException
     *          if one of the parameters is null.
     */
    public ExternalReferenceType(T target, A targetAtt) throws XMLException {
        setTarget(target);
        setTargetAtt(targetAtt);
    }


    /**
     * Reference constructor. Creates a reference to an element also indicating
     * where the element is.
     *
     * @param alias
     *          element representing the element location.
     * @param target
     *          referred element.
     * @param targetAtt
     *          referred element attribute.
     * @throws XMLException
     *          if one of the parameters is null.
     */
    public ExternalReferenceType(I alias, T target, A targetAtt) throws XMLException {
        setTarget(target);
        setTargetAtt(targetAtt);
        setAlias(alias);
    }


    /**
     * Set the reference alias. The alias is null if the referred element is in
     * the same document.
     * 
     * @param alias
     *          element representing the element location.
     */
    private void setAlias(I alias) throws XMLException {
        this.alias = alias;
        
        if(alias != null)
            alias.addReference(this);
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
    public void clean() throws XMLException {
        super.clean();
        
        if(alias != null)
            alias.removeReference(this);
    }


    /**
     * Returns the string that represents the element reference.
     *
     * @return
     *          string representing the element complete reference string.
     */
    public String parse() {
        String content = "";
        
        if(alias != null){
            content += alias.getAlias();
            content += "#";
        }
        
        content += parseIdent();
        
        return content;
    }
    
    
    /**
     * Returns the string that represents the element referred attribute.
     * 
     * @return 
     *          string representing the element referred attribute.
     */
    protected abstract String parseIdent();
}
