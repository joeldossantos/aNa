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
package br.uff.midiacom.ana.datatype;


/**
 * This class represents a number. The number can be:
 * <ul>
 *   <li>absolute number in the format 99.99</li>
 *   <li>relative number in the format 99.99%</li>
 * </ul>
 */
public class RelativeType {

    private double value;
    private boolean isRelative;


    /**
     * Creates the number as a absolute number.
     *
     * @param value
     *          double representing the number.
     */
    public RelativeType(double value) {
        this.value = value;
        isRelative = false;
    }


    /**
     * Creates a number that can be relative.
     *
     * @param value
     *          double representing the number.
     * @param isRelative
     *          boolean indicating if the number is relative or absolute.
     *          true for relative and false for absolute.
     */
    public RelativeType(double value, boolean isRelative) {
        this.value = value;
        this.isRelative = isRelative;
    }


    /**
     * Creates the number as a String.
     *
     * @param value
     *          String representing the number.
     * @throws NullPointerException
     *          if the String is null.
     * @throws IllegalArgumentException
     *          if the String is empty.
     */
    public RelativeType(String value) throws NullPointerException, IllegalArgumentException {
        if(value == null)
            throw new NullPointerException("Null value String");
        if("".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        int index = value.indexOf("%");
        if(index > 0){
            value = value.substring(0, index);
            isRelative = true;
        }

        this.value = new Double(value);
    }


    /**
     * Returns the number value.
     *
     * @return
     *          double representing the number.
     */
    public double getValue() {
        return value;
    }


    /**
     * Indicates if the number is relative.
     *
     * @return
     *          boolean indicating if the number is relative.
     */
    public boolean isRelative() {
        return isRelative;
    }


    /**
     * Returns the number value.
     *
     * @return
     *          String representing the number.
     */
    public String parse() {
        if(isRelative)
            return "" + value + "%";
        else
            return "" + value;
    }
}
