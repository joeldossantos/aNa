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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.aux.parameterized.AssValueParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;


/**
 * Class that represents a valueAssessment element. The valueAssessment element
 * represents a value used in an comparison.
 * 
 * <br/>
 * 
 * This element defines the attribute:
 * <ul>
 *  <li><i>value</i> - value used in the comparison. This attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ep>
 * @param <R> 
 */
public abstract class NCLValueAssessmentPrototype<T extends NCLValueAssessmentPrototype,
                                                  P extends NCLElement,
                                                  I extends NCLElementImpl,
                                                  Ep extends NCLConnectorParamPrototype,
                                                  R extends ConParamReference>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected AssValueParamType<Ep, T, R> value;
    

    /**
     * ValueAssessment element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLValueAssessmentPrototype() throws XMLException {
        super();
    }


    /**
     * ValueAssessment element constructor. The element is created with a value.
     *
     * @param value
     *          element representing the value.
     * @throws XMLException
     *          if the element representing the value is null.
     */
    public NCLValueAssessmentPrototype(AssValueParamType<Ep, T, R> value) throws XMLException {
        super();
        setValue(value);
    }
    

    /**
     * Sets the element value. The value is required and can not be set to
     * <i>null</i>.
     *
     * @param value
     *          element representing the value.
     * @throws XMLException
     *          if the element representing the value is null.
     */
    public void setValue(AssValueParamType<Ep, T, R> value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
            
        AssValueParamType aux = this.value;
        
        this.value = value;
        this.value.setOwner((T) this, NCLElementAttributes.VALUE);
        
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
        if(aux != null)
            aux.removeOwner();
    }
    

    /**
     * Returns the element value or <i>null</i> if the attribute is not defined.
     *
     * @return
     *          element representing the value or <i>null</i> if the attribute
     *          is not defined.
     */
    public AssValueParamType<Ep, T, R> getValue() {
        return value;
    }


    @Override
    public boolean compare(T other) {
        boolean comp = true;

        String this_stat, other_stat;

        if(getValue() == null) this_stat = ""; else this_stat = getValue().parse();
        if(other.getValue() == null) other_stat = ""; else other_stat = other.getValue().parse();
        comp &= this_stat.equals(other_stat);

        return comp;
    }
}
