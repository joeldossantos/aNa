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
package br.uff.midiacom.ana.datatype.aux.basic;

import br.uff.midiacom.ana.datatype.enums.NCLColor;


/**
 * This class represents a value that can be a color or the "transparent" String.
 */
public class TranspColorType {

    private NCLColor color;
    private String transparent = "transparent";


    /**
     * Creates the value as a color.
     *
     * @param color
     *          element representing the color.
     * @throws NullPointerException
     *          if the color is null.
     */
    public TranspColorType(NCLColor color) throws NullPointerException {
        if(color == null)
            throw new NullPointerException("null color");

        this.color = color;
    }


    /**
     * Creates the value as a String.
     *
     * @param color
     *          String representing a color or the "transparent" String.
     * @throws NullPointerException
     *          if the String is null.
     * @throws IllegalArgumentException
     *          if the String is empty.
     */
    public TranspColorType(String color) throws NullPointerException, IllegalArgumentException {
        if(color == null)
            throw new NullPointerException("Null color String");
        if("".equals(color.trim()))
            throw new IllegalArgumentException("Empty color String");

        if(!color.equals(transparent)){
            for(NCLColor c : NCLColor.values()){
                if(c.toString().equals(color))
                    this.color = c;
            }
        }
    }


    /**
     * Return the value.
     *
     * @return
     *          element representing a color.
     */
    public NCLColor getColor() {
        return color;
    }


    /**
     * Check if the value is the String "transparent".
     *
     * @return
     *          true if the value is "transparent".
     */
    public boolean isTransparent() {
        return color == null;
    }


    /**
     * Returns the value.
     *
     * @return
     *          String representing the value.
     */
    public String parse() {
        if(color == null)
            return transparent;
        else
            return color.toString();
    }
}
