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

import br.uff.midiacom.ana.util.enums.NCLUriType;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.io.Serializable;


/**
 * This class represents a source locator. The source locator my be defined as:
 * <ul>
 *   <li>untyped locator (ex.: media.ext or /home/media.ext)</li>
 *   <li>typed locator (ex.: file:///home/media.ext)</li>
 *   <li>time locator (ex.: 2011:01:01:12:10:10.500)</li>
 * </ul>
 */
public class SrcType implements Serializable {

    private NCLUriType type;
    private String src;


    /**
     * Creates a untyped locator.
     *
     * @param src
     *          String representing the locator.
     * @throws XMLException
     *          if the String is empty.
     */
    public SrcType(String src) throws XMLException {
        setSrc(src);
    }


    /**
     * Creates a typed locator.
     *
     * @param type
     *          element representing the locator type.
     * @param src
     *          String representing the locator.
     * @throws XMLException
     *          if the String is empty.
     */
    public SrcType(NCLUriType type, String src) throws XMLException {
        setType(type);
        setSrc(src);
    }


    /**
     * Creates a time locator.
     *
     * @param time
     *          element representing the time locator content.
     * @throws XMLException
     *          if the time is not in the required format or null.
     */
    public SrcType(TimeType time) throws XMLException {
        if(time == null)
            throw new XMLException("Null time");
        if(!time.isUTC())
            throw new XMLException("Invalid src");

        setSrc(time.toString());
    }


    private void setSrc(String src) throws XMLException {
        if(src != null && "".equals(src.trim()))
            throw new XMLException("Empty src String");

        this.src = src;
    }


    private void setType(NCLUriType type) {
        this.type = type;
    }


    public String getExtension() {
        return src.substring(src.lastIndexOf("."));
    }


    @Override
    public String toString() {
        String value = "";

        if(type != null)
            value += type.toString();

        value += src;

        return value;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof SrcType))
            return false;
        
        return toString().equals(o.toString());
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 97 * hash + (this.src != null ? this.src.hashCode() : 0);
        return hash;
    }
}
