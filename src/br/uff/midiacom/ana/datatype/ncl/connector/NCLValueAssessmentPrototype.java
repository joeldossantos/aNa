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

import br.uff.midiacom.ana.datatype.auxiliar.AssValueParamType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;


public class NCLValueAssessmentPrototype<T extends NCLValueAssessmentPrototype, P extends NCLElement, I extends XMLElementImpl, Ep extends NCLConnectorParamPrototype>
        extends XMLElementPrototype<T, P, I> implements NCLElement<T, P> {

    protected AssValueParamType<Ep> value;
    

    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLValueAssessmentPrototype() {}


    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param value
     *          String contendo o valor da assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public NCLValueAssessmentPrototype(AssValueParamType<Ep> value) throws IllegalArgumentException {
        setValue(value);
    }
    

    /**
     * Determina o valor da assertiva do conector.
     *
     * @param value
     *          String contendo o valor da assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(AssValueParamType<Ep> value) {
        this.value = value;
    }
    

    /**
     * Retorna o valor da assetiva do conector. Retorna a String que representa um valor
     * padrao caso o valor tenha sido determinado desta forma.
     *
     * @return
     *          String contendo o valor da assertiva.
     */
    public AssValueParamType<Ep> getValue() {
        return value;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<valueAssessment";
        if(getValue() != null)
            content += " value='" + getValue().parse() + "'";
        content += "/>\n";

        return content;
    }


    public boolean compare(T other) {
        boolean comp = true;

        String this_stat, other_stat;

        if(getValue() == null) this_stat = ""; else this_stat = getValue().parse();
        if(other.getValue() == null) other_stat = ""; else other_stat = other.getValue().parse();
        comp &= this_stat.equals(other_stat);

        return comp;
    }
}
