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
package br.uff.midiacom.ana.datatype.ncl.rule;

import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.TreeSet;


public abstract class NCLRulePrototype<T extends NCLTestRule,
                                       P extends NCLElement,
                                       I extends NCLElementImpl,
                                       Ep extends NCLPropertyPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLTestRule<T, P> {

    protected Ep var;
    protected NCLComparator comparator;
    protected StringType value;
    
    protected TreeSet<ReferenceType> references;


    /**
     * Construtor do elemento <i>rule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLRulePrototype(String id) throws XMLException {
        super();
        setId(id);
        references = new TreeSet<ReferenceType>();
    }


    public NCLRulePrototype() throws XMLException {
        super();
        references = new TreeSet<ReferenceType>();
    }


    /**
     * Atribui uma propriedade ao atributo var.
     *
     * @param var
     *          elemento representando a propriedade associada ao atributo.
     */
    public void setVar(Ep var) {
        Ep aux = this.var;
        this.var = var;
        impl.notifyAltered(NCLElementAttributes.VAR, aux, var);
    }


    /**
     * Retorna a propriedade relacionada ao atributo var.
     *
     * @return
     *          elemento representando a propriedade associada ao atributo.
     */
    public Ep getVar() {
        return var;
    }


    /**
     * Atribui um comparador a regra.
     *
     * @param comparator
     *          elemento representando o comparador da regra.
     */
    public void setComparator(NCLComparator comparator) {
        NCLComparator aux = this.comparator;
        this.comparator = comparator;
        impl.notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }


    /**
     * Retorna o comparador da regra.
     *
     * @return
     *          elemento representando o comparador da regra.
     */
    public NCLComparator getComparator() {
        return comparator;
    }


    /**
     * Atribui um valor de comparação a regra.
     *
     * @param value
     *          String representando o valor de comparação.
     *
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        this.value = new StringType(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }


    /**
     * Retorna o valor de comparação da regra.
     *
     * @return
     *          String representando o valor de comparação.
     */
    public String getValue() {
        if(value != null)
            return value.getValue();
        else
            return null;
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
