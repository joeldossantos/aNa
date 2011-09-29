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

import br.uff.midiacom.ana.datatype.enums.NCLUriType;


/**
 * This class represents a source locator. The source locator my be defined as:
 * <ul>
 *   <li>untyped locator (ex.: media.ext or /home/media.ext)</li>
 *   <li>typed locator (ex.: file:///home/media.ext)</li>
 *   <li>time locator (ex.: 2011:01:01:12:10:10.500)</li>
 * </ul>
 */
public class SrcType {

    private NCLUriType type;
    private String src;


    /**
     * Creates a untyped locator.
     *
     * @param src
     *          String representing the locator.
     * @throws IllegalArgumentException
     *          if the String is empty.
     */
    public SrcType(String src) throws IllegalArgumentException {
        setSrc(src);
    }


    /**
     * Creates a typed locator.
     *
     * @param type
     *          element representing the locator type.
     * @param src
     *          String representing the locator.
     * @throws IllegalArgumentException
     *          if the String is empty.
     */
    public SrcType(NCLUriType type, String src) throws IllegalArgumentException {
        setType(type);
        setSrc(src);
    }


    /**
     * Creates a time locator.
     *
     * @param time
     *          element representing the time locator content.
     * @throws IllegalArgumentException
     *          if the time is not in the required format.
     * @throws NullPointerException
     *          if the time is null.
     */
    public SrcType(TimeType time) throws IllegalArgumentException, NullPointerException {
        if(time == null)
            throw new NullPointerException("Null time");
        if(!time.isUTC())
            throw new IllegalArgumentException("Invalid src");

        setSrc(time.toString());
    }


    private void setSrc(String src) throws IllegalArgumentException {
        if(src != null && "".equals(src.trim()))
            throw new IllegalArgumentException("Empty src String");

        this.src = src;
    }


    private void setType(NCLUriType type) {
        this.type = type;
    }


    /**
     * Returns the source locator.
     *
     * @return
     *          String representing the source locator.
     */
    public String parse() {
        String value = "";

        if(type != null)
            value += type.toString();

        value += src;

        return value;
    }
}
