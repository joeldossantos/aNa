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


/**
 * Atributos de uma mídia (podem ser parametrizados pelo descritor) da <i>Nested Context Language</i> (NCL).
 */
public enum NCLAttributes {

    TOP("top"),
    LEFT("left"),
    BOTTOM("bottom"),
    RIGHT("right"),
    WIDTH("width"),
    HEIGHT("height"),
    LOCATION("location"),
    SIZE("size"),
    BOUNDS("bounds"),
    BACKGROUND("background"),
    VISIBLE("visible"),
    TRANSPARENCY("transparency"),
    FIT("fit"),
    SCROLL("scroll"),
    STYLE("style"),
    SOUND_LEVEL("soundLevel"),
    BALANCE_LEVEL("balanceLevel"),
    TREBLE_LEVEL("trebleLevel"),
    BASS_LEVEL("bassLevel"),
    ZINDEX("zIndex"),
    FONT_FAMILY("fontFamily"),
    FONT_SIZE("fontSize"),
    FONT_VARIANT("fontVariant"),
    FONT_WEIGHT("fontWeight"),
    FONT_COLOR("fontColor"),
    REUSE_PLAYER("reusePlayer"),
    PLAYER_LIFE("playerLife"),
    DEFAULT("");


    private String name;
    
    private NCLAttributes(String name) {
        this.name = name;
    }
    
    public static NCLAttributes getEnumType(String name){
        for(NCLAttributes opt : values()){
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
