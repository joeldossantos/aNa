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
 *        (http:joeldossantos.github.com/aNa).
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


public enum NCLNodeAttributes {
    
    TOP("top"),
    LEFT("left"),
    BOTTOM("bottom"),
    RIGHT("right"),
    WIDTH("width"),
    HEIGHT("height"),
    EXPLICITDUR("explicitDur,"),
    BACKGROUND("background"),
    TRANSPARENCY("transparency"),
    VISIBLE("visible"),
    FIT("fit"),
    SCROLL("scroll"),
    STYLE("style"),
    SOUND_LEVEL("soundLevel"),
    BALANCE_LEVEL("balanceLevel"),
    TREBLE_LEVEL("trebleLevel"),
    BASS_LEVEL("bassLevel"),
    FONT_COLOR("fontColor"),
    FONT_FAMILY("fontFamily"),
    FOTN_STYLE("fontStyle"),
    FONT_SIZE("fontSize"),
    FONT_VARIANT("fontVariant"),
    FONT_WEIGHT("fontWeight"),
    PLAYER("player"),
    REUSE_PLAYER("reusePlayer"),
    PLAYER_LIFE("playerLife"),
    LOCATION("location"),
    SIZE("size"),
    BOUNDS("bounds"),
    TEXT_ALIGN("textAlign"),
    RGB_CHROMAKEY("rgbChromakey"),
    Z_INDEX("zIndex"),
    PLAN("plan"),
    MOVE_LEFT("moveLeft"),
    MOVE_RIGHT("moveRight"),
    MOVE_UP("moveUp"),
    MOVE_DOWN("moveDown"),
    FOCUS_INDEX("focusIndex"),
    FOCUS_BORDER_COLOR("focusBorderColor"),
    SEL_BORDER_COLOR("selBorderColor"),
    FOCUS_BORDER_WIDTH("focusBorderWidth"),
    FOCUS_BORDER_TRANSPARENCY("focusBorderTransparency"),
    FOCUS_SRC("focusSrc"),
    FOCUS_SEL_SRC("focusSelSrc"),
    TRANS_IN("transIn"),
    TRANS_OUT("transOut"),
    FREEZE("freeze"),
    BASE_DEVICE_REGION("baseDeviceRegion"),
    DEVICE_CLASS("deviceClass");
    
    private String name;


    private NCLNodeAttributes(String name) {
        this.name = name;
    }

    public static NCLNodeAttributes getEnumType(String name) {
        for(NCLNodeAttributes opt : values()){
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
