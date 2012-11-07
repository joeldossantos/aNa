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
package br.uff.midiacom.ana.util.enums;

import br.uff.midiacom.ana.util.exception.NCLParsingException;


/**
 * Mime Types dos elementos <i>media</i> da <i>Nested Context Language</i> (NCL).
 */
public enum NCLMimeType {

    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_CSS("text/css"),
    TEXT_XML("text/xml"),
    IMAGE_BMP("image/bmp"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    AUDIO_BASIC("audio/basic"),
    AUDIO_MP3("audio/mp3"),
    AUDIO_MP2("audio/mp2"),
    AUDIO_MPEG("audio/mpeg"),
    AUDIO_MPEG4("audio/mpeg4"),
    VIDEO_MPEG("video/mpeg"),
    APPLICATION_X_GINGA_NCL("application/x-ginga-NCL"),
    APPLICATION_X_GINGA_NCLUA("application/x-ginga-NCLua"),
    APPLICATION_X_GINGA_NCLET("application/x-ginga-NCLet"),
    APPLICATION_X_GINGA_SETTINGS("application/x-ginga-settings"),
    APPLICATION_X_GINGA_TIME("application/x-ginga-time");


    private String name;

    private NCLMimeType(String name) {
        this.name = name;
    }

    public static NCLMimeType getEnumType(String name){
        for(NCLMimeType opt : values()){
            if(name.equals(opt.name))
                return opt;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
