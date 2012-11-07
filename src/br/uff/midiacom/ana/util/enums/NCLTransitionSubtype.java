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
 * Subtipos de transições da <i>Nested Context Language</i> (NCL).
 */
public enum NCLTransitionSubtype {

    LEFT_TO_RIGHT("leftToRight", NCLTransitionType.BAR),
    TOP_TO_BOTTOM("topToBottom", NCLTransitionType.BAR),

    RECTANGLE("rectangle", NCLTransitionType.IRIS),
    DIAMOND("diamond", NCLTransitionType.IRIS),

    CLOCKWISE_TWELVE("clockwiseTwelve", NCLTransitionType.CLOCK),
    CLOCKWISE_THREE("clockwiseThree", NCLTransitionType.CLOCK),
    CLOCKWISE_SIX("clockwiseSix", NCLTransitionType.CLOCK),
    CLOCKWISE_NINE("clockwiseNine", NCLTransitionType.CLOCK),

    TOP_LEFT_HORIZONTAL("topLeftHorizontal", NCLTransitionType.SNAKE),
    TOP_LEFT_VERTICAL("topLeftVertical", NCLTransitionType.SNAKE),
    TOP_LEFT_DIAGONAL("topLeftDiagonal", NCLTransitionType.SNAKE),
    TOP_RIGHT_DIAGONAL("topRightDiagonal", NCLTransitionType.SNAKE),
    BOTTOM_RIGHT_DIAGONAL("bottomRightDiagonal", NCLTransitionType.SNAKE),
    BOTTOM_LEFT_DIAGONAL("bottomLeftDiagonal", NCLTransitionType.SNAKE),

    CROSSFADE("crossfade", NCLTransitionType.FADE),
    FADE_TO_COLOR("fadeToColor", NCLTransitionType.FADE),
    FADE_FROM_COLOR("fadeFromColor", NCLTransitionType.FADE);


    private String name;
    private NCLTransitionType type;


    private NCLTransitionSubtype(String name, NCLTransitionType type) {
        this.name = name;
        this.type = type;
    }


    public static NCLTransitionSubtype getEnumType(String name){
        for(NCLTransitionSubtype opt : values()){
            if(name.equals(opt.name))
                return opt;
        }
        return null;
    }


    @Override
    public String toString() {
        return name;
    }


    public NCLTransitionType getType() {
        return type;
    }
}