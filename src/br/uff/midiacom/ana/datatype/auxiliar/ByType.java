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
package br.uff.midiacom.ana.datatype.auxiliar;


/**
 * This class represents a value that can be a positive integer or the String
 * "indefinite".
 */
public class ByType {

    private Integer value;
    private String indefinite = "indefinite";


    /**
     * Creates the value as a integer number.
     *
     * @param value
     *          a positive integer.
     * @throws IllegalArgumentException
     *          if the integer is negative.
     */
    public ByType(int value) throws IllegalArgumentException {
        if(value < 0)
            throw new IllegalArgumentException("Negative value");

        this.value = value;
    }


    /**
     * Creates the value as a String.
     *
     * @param value
     *          String representing a positive integer or the String "unbounded".
     * @throws NullPointerException
     *          if the String is null.
     * @throws IllegalArgumentException
     *          if the String is empty.
     */
    public ByType(String value) throws NullPointerException, IllegalArgumentException {
        if(value == null)
            throw new NullPointerException("Null value String");
        if("".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        if(!value.equals(indefinite))
            this.value = new Integer(value);
    }


    /**
     * Return the value.
     *
     * @return
     *          element representing a positive integer.
     */
    public Integer getValue() {
        return value;
    }


    /**
     * Check if the value is the String "indefinite".
     *
     * @return
     *          true if the value is "indefinite".
     */
    public boolean isUnbounded() {
        return value == null;
    }


    /**
     * Returns the value.
     *
     * @return
     *          String representing the value.
     */
    public String parse() {
        if(value == null)
            return indefinite;
        else
            return value.toString();
    }
}
