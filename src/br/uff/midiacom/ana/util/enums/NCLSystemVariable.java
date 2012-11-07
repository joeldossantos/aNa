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
import br.uff.midiacom.ana.util.exception.XMLException;


/**
 * Nomes das variáveis de sistema (que não tem argumento) da <i>Nested Context Language</i> (NCL).
 */
public enum NCLSystemVariable {

    SYSTEM_LANGUAGE("system.language"),
    SYSTEM_CAPTION("system.caption"),
    SYSTEM_SUBTITLE("system.subtitle"),
    SYSTEM_SCREENSIZE("system.screenSize"),
    SYSTEM_SCREENGRAPHICSIZE("system.screenGraphicSize"),
    SYSTEM_AUDIOTYPE("system.audioType"),
    SYSTEM_CLASSNUMBER("system.classNumber"),
    SYSTEM_CPU("system.CPU"),
    SYSTEM_MEMORY("system.memory"),
    SYSTEM_OPERATINGSYSTEM("system.operatingSystem"),
    SYSTEM_JAVACONFIGURATION("system.javaConfiguration"),
    SYSTEM_JAVAPROFILE("system.javaProfile"),
    SYSTEM_LUAVERSION("system.luaVersion"),


    DEFAULT_FOCUSBORDERCOLOR("default.focusBorderColor"),
    DEFAULT_SELBORDERCOLOR("default.selBorderColor"),
    DEFAULT_FOCUSBORDERWIDTH("default.focusBorderWidth"),
    DEFAULT_FOCUSBORDERTRANSPARENCY("default.focusBorderTransparency"),


    SERVICE_CURRENTFOCUS("service.currentFocus"),
    SERVICE_CURRENTKEYMASTER("service.currentKeyMaster"),


    SI_NUMBEROFSERVICES("si.numberOfServices"),
    SI_NUMBEROFPARTIALSERVICES("si.numberOfPartialServices"),
    SI_CHANNELNUMBER("si.channelNumber"),


    CHANNEL_KEYCAPTURE("channel.keyCapture"),
    CHANNEL_VIRTUALKEYBOARD("channel.virtualKeyboard"),
    CHANNEL_KEYBOARDBOUNDS("channel.keyboardBounds"),

    SYSTEM_RETURNBITRATE_i("system.returnBitRate"),
    SYSTEM_SCREENSIZE_i("system.screenSize"),
    SYSTEM_SCREENGRAPHICSIZE_i("system.screenGraphicSize"),
    SYSTEM_AUDIOTYPE_i("system.audioType"),
    SYSTEM_DEVNUMBER_i("system.devNumber"),
    SYSTEM_CLASSTYPE_i("system.classType"),
    SYSTEM_INFO_i("system.info");


    private String name;
    
    private NCLSystemVariable(String name) {
        this.name = name;
    }
    
    public static NCLSystemVariable getEnumType(String name){
        for(NCLSystemVariable opt : values()){
            if(name.equals(opt.name))
                return opt;
        }
        return null;
    }
    
    
    public boolean isParameterized() {
        switch(this){
            case SYSTEM_RETURNBITRATE_i: return true;
            case SYSTEM_SCREENSIZE_i: return true;
            case SYSTEM_SCREENGRAPHICSIZE_i: return true;
            case SYSTEM_AUDIOTYPE_i: return true;
            case SYSTEM_DEVNUMBER_i: return true;
            case SYSTEM_CLASSTYPE_i: return true;
            case SYSTEM_INFO_i: return true;
            default: return false;
        }
    }
    
    
    @Override
    public String toString() {
        return name;
    }
    
    
    public boolean compare(Object o) {
        if(o == null || o instanceof NCLSystemVariable)
            return false;
        
        return toString().equals(((NCLSystemVariable) o).toString());
    }
}
