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
 * Tipos de mídia da <i>Nested Context Language</i> (NCL).
 */
public enum NCLMediaType {

    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    PROCEDURAL,
    OTHER;
    
    
    public static NCLMediaType getEnumType(String ext) throws NCLParsingException {
        if(ext == null)
            throw new NCLParsingException("Null extension");
        
        boolean status = false;

        status |= ext.contentEquals(".html");
        status |= ext.contentEquals(".html");
        status |= ext.contentEquals(".xhtml");
        status |= ext.contentEquals(".css");
        status |= ext.contentEquals(".xml");
        status |= ext.contentEquals(".txt");
        if(status)
            return TEXT;

        status |= ext.contentEquals(".bmp");
        status |= ext.contentEquals(".png");
        status |= ext.contentEquals(".gif");
        status |= ext.contentEquals(".jpg");
        status |= ext.contentEquals(".jpeg");
        status |= ext.contentEquals(".jpe");
        if(status)
            return IMAGE;

        status |= ext.contentEquals(".ua");
        status |= ext.contentEquals(".wav");
        status |= ext.contentEquals(".mp1");
        status |= ext.contentEquals(".mp2");
        status |= ext.contentEquals(".mp3");
        status |= ext.contentEquals(".mp4");
        status |= ext.contentEquals(".mpg4");
        if(status)
            return AUDIO;

        status |= ext.contentEquals(".mpeg");
        status |= ext.contentEquals(".mpg");
        status |= ext.contentEquals(".mpe");
        status |= ext.contentEquals(".mng");
        status |= ext.contentEquals(".qt");
        status |= ext.contentEquals(".mov");
        status |= ext.contentEquals(".avi");
        if(status)
            return VIDEO;

        status |= ext.contentEquals(".class");
        status |= ext.contentEquals(".xlet");
        status |= ext.contentEquals(".xlt");
        status |= ext.contentEquals(".lua");
        if(status)
            return PROCEDURAL;

        return OTHER;
    }
    
    
    public static NCLMediaType getEnumType(NCLMimeType type){
        if(type == null)
            return null;

        boolean status = false;

        status |= (type == NCLMimeType.APPLICATION_X_GINGA_NCLET);
        status |= (type == NCLMimeType.APPLICATION_X_GINGA_NCLUA);
        if(status)
            return PROCEDURAL;

        status |= (type == NCLMimeType.AUDIO_BASIC);
        status |= (type == NCLMimeType.AUDIO_MP2);
        status |= (type == NCLMimeType.AUDIO_MP3);
        status |= (type == NCLMimeType.AUDIO_MPEG);
        status |= (type == NCLMimeType.AUDIO_MPEG4);
        if(status)
            return AUDIO;

        status |= (type == NCLMimeType.IMAGE_BMP);
        status |= (type == NCLMimeType.IMAGE_GIF);
        status |= (type == NCLMimeType.IMAGE_JPEG);
        status |= (type == NCLMimeType.IMAGE_PNG);
        if(status)
            return IMAGE;

        status |= (type == NCLMimeType.VIDEO_MPEG);
        if(status)
            return VIDEO;

        status |= (type == NCLMimeType.TEXT_CSS);
        status |= (type == NCLMimeType.TEXT_HTML);
        status |= (type == NCLMimeType.TEXT_PLAIN);
        status |= (type == NCLMimeType.TEXT_XML);
        if(status)
            return TEXT;

        return OTHER;
    }
}
