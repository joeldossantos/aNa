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
package br.uff.midiacom.ana.util;

import br.uff.midiacom.ana.util.exception.XMLException;
import java.io.Serializable;


/**
 * This class percentage value. The value can be represented:
 * <ul>
 *   <li>without the percentage sign as a number between 0 and 1</li>
 *   <li>with the percentage sign as a number between 0 and 100 (ex.: 99.99%)</li>
 * </ul>
 */
public class PercentageType implements Serializable {

    private double value;
    private boolean signed;


    /**
     * Creates the percentage as a signed value.
     *
     * @param value
     *          double representing a number between 0 and 100.
     * @throws XMLException
     *          if the value is out of bounds.
     */
    public PercentageType(double value) throws XMLException {
        signed = true;
        setValue(value);
    }


    /**
     * Creates the percentage indicating if it has a percent sign.
     *
     * @param value
     *          double representing the percentage.
     * @param signed
     *          boolean indicating if the percentage value has or not a percent
     *          sign. true for a signed value and false for unsigned.
     * @throws XMLException
     *          if the value is out of bounds.
     */
    public PercentageType(double value, boolean signed) throws XMLException {
        this.signed = signed;
        setValue(value);
    }


    /**
     * Creates the percentage as a String.
     *
     * @param value
     *          String representing the percentage.
     * @throws XMLException
     *          if the String is null, empty or out of bounds.
     */
    public PercentageType(String value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value String");
        if("".equals(value.trim()))
            throw new XMLException("Empty value String");

        int index = value.indexOf("%");
        if(index > 0){
            value = value.substring(0, index);
            signed = true;
        }

        setValue(new Double(value));
    }


    private void setValue(double value) throws XMLException {
        if(!signed && (value < 0 || value > 1))
            throw new XMLException("The number must be between 0 and 1.");
        if(signed && (value < 0 || value > 100))
            throw new XMLException("The number must be between 0 and 100.");

        this.value = value;
    }


    /**
     * Returns the percentage value.
     *
     * @return
     *          double representing the number.
     */
    public double getValue() {
        return value;
    }


    /**
     * Indicates if the percentage has a percentage sign.
     *
     * @return
     *          boolean indicating if the percentage value has or not a percent
     *          sign. true for a signed value and false for unsigned.
     */
    public boolean isRelative() {
        return signed;
    }


    /**
     * Returns the number value.
     *
     * @return
     *          String representing the number.
     */
    public String parse() {
        if(signed)
            return "" + value + "%";
        else
            return "" + value;
    }
}
