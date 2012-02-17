/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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
package br.uff.midiacom.ana.datatype.ncl.node;

import br.uff.midiacom.ana.datatype.auxiliar.ReferenceType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLCompositeNodeElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPortPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.ana.datatype.ncl.link.NCLLinkPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetaPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetadataPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;


public abstract class NCLContextPrototype<T extends NCLContextPrototype,
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

    protected T refer;
    
    
    /**
     * Construtor do elemento <i>context</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do contexto.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do contexto for inválido.
     */
    public NCLContextPrototype(String id) throws XMLException {
        super();
        setId(id);
    }


    public NCLContextPrototype() throws XMLException {
        super();
    }


    /**
     * Atribui um contexto para ser reutilizado pelo contexto.
     *
     * @param refer
     *          elemento representando o contexto a ser reutilizado.
     */
    public void setRefer(T refer) {
        T aux = this.refer;
        this.refer = refer;
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    /**
     * Retorna o contexto reutilizado pelo contexto.
     *
     * @return
     *          elemento representando o contexto a ser reutilizado.
     */
    public T getRefer() {
        return refer;
    }
}
