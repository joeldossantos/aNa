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
package br.uff.midiacom.ana.datatype.aux.basic;

import br.uff.midiacom.ana.datatype.enums.NCLDefaultValueAssessment;
import br.uff.midiacom.xml.XMLException;


/**
 * This class represent the value of an value assessment element. This value can
 * be one of the default values defined in the enumeration <i>NCLDefaultValueAssessment</i>
 * or a string.
 */
public class AssValueType {

    private String value;
    private NCLDefaultValueAssessment defValue;


    /**
     * Creates the value as a default value.
     * 
     * @param value
     *          value from the enumeration <i>NCLDefaultValueAssessment</i>.
     * @throws XMLException 
     *          if the value is null.
     */
    public AssValueType(NCLDefaultValueAssessment value) throws XMLException {
        if(value == null)
            throw new XMLException("null value");

        this.defValue = value;
    }


    /**
     * Creates the value from a string. If the string represents a default value
     * it is transformed to a value from the enumeration <i>NCLDefaultValueAssessment</i>,
     * otherwise the value is created as a string value.
     * 
     * @param value
     *          string representing the value.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public AssValueType(String value) throws NullPointerException, IllegalArgumentException {
        if(value == null)
            throw new NullPointerException("Null value String");
        if("".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        if(value != null){
            for(NCLDefaultValueAssessment def : NCLDefaultValueAssessment.values()){
                if(value.equals(def.toString())){
                    defValue = def;
                    return;
                }
            }
        }

        this.value = value;
    }


    /**
     * Return the default value or <i>null</i> if the value is not a default
     * value.
     * 
     * @return 
     *          element from the enumeration <i>NCLDefaultValueAssessment</i> or
     *          <i>null</i> if the value is not a default value.
     */
    public NCLDefaultValueAssessment getValue() {
        return defValue;
    }


    /**
     * Verifies if the value is a default value.
     * 
     * @return 
     *          true if the value is a default value.
     */
    public boolean isDefaultValue() {
        return value == null;
    }


    /**
     * Returns the string that represents the value.
     * 
     * @return 
     *          string representing the value.
     */
    public String parse() {
        if(defValue != null)
            return defValue.toString();
        else
            return value;
    }
}
