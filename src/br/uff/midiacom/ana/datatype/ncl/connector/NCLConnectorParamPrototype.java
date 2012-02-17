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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.auxiliar.ReferenceType;
import br.uff.midiacom.ana.datatype.auxiliar.ReferredElement;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.TreeSet;


public abstract class NCLConnectorParamPrototype<T extends NCLConnectorParamPrototype,
                                                 P extends NCLElement,
                                                 I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement {
    
    protected StringType type;
    
    protected TreeSet<ReferenceType> references;
    
    
    /**
     * Construtor do elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     *         java.lang.IllegalArgumentException
     *          se a String do tipo for vazia.
     */
    public NCLConnectorParamPrototype(String name) throws XMLException {
        super();
        setName(name);
        references = new TreeSet<ReferenceType>();
    }

    
    public NCLConnectorParamPrototype() throws XMLException {
        super();
        references = new TreeSet<ReferenceType>();
    }
    
    
    @Deprecated
    @Override
    public void setId(String id) throws XMLException {
        super.setId(id);
    }
    
    
    @Deprecated
    @Override
    public String getId() {
        return super.getId();
    }
    
    
    /**
     * Atribui um nome ao parâmetro
     *
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     */
    public void setName(String name) throws XMLException {
        String aux = this.getName();
        super.setId(name);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, name);
    }
    
    
    /**
     * Retorna o nome do parâmetro
     * 
     * @return
     *          String contendo o nome atribuido ao parâmetro.
     */
    public String getName() {
        return super.getId();
    }
    
    
    /**
     * Atribui um tipo ao parâmetro
     *
     * @param type
     *      String contendo o tipo do parâmetro.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setType(String type) throws XMLException {
        StringType aux = this.type;
        this.type = new StringType(type);
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }


    /**
     * Retorna o tipo do parâmetro
     * 
     * @return
     *      String contendo o tipo do parâmetro.
     */
    public String getType() {
        if(type == null)
            return null;
        else
            return type.getValue();
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) {
        return references.remove(reference);
    }
    
    
    @Override
    public TreeSet<ReferenceType> getReferences() {
        return references;
    }
}
