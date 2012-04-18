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

import br.uff.midiacom.xml.XMLException;

/**
 * This class represents a value that can be an integer or a string.
 */
public class FocusIndexType {

    private Integer iValue;
    private String sValue;


    /**
     * Creates the focus index value as an integer value.
     *
     * @param value
     *          integer representing the focus index value.
     */
    public FocusIndexType(int value) {
        this.iValue = value;
    }


    /**
     * Creates the focus index value from a string. If the string represents an
     * integer value it transforms the value to an integer number, otherwise the
     * focus index value will be created as a string value.
     *
     * @param value
     *          string representing the focus index value.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public FocusIndexType(String value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value String");
        if("".equals(value.trim()))
            throw new XMLException("Empty value String");

        try{
            this.iValue = new Integer(value);
        }
        catch(Exception e){
            this.sValue = value;
        }
    }


    /**
     * Return the focus index value as an integer value or <i>null</i> if the
     * value is not an integer value.
     *
     * @return
     *          integer representing the value or <i>null</i> if the value is
     *          not an integer value.
     */
    public Integer getIntegerValue() {
        return iValue;
    }


    /**
     * Return the focus index value as a string value or <i>null</i> if the
     * value is not a string value.
     *
     * @return
     *          string representing the value or <i>null</i> if the value is
     *          not a string value.
     */
    public String getStringValue() {
        return sValue;
    }


    /**
     * Returns the string that represents the focus index value.
     *
     * @return
     *          String representing the value.
     */
    public String parse() {
        if(iValue == null)
            return sValue;
        else
            return iValue.toString();
    }
}
