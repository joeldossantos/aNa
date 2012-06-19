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
 * Teclas do controle remoto definidas pela <i>Nested Context Language</i> (NCL).
 */
public enum NCLKey {

    KEY_0("0"),
    KEY_1("1"),
    KEY_2("2"),
    KEY_3("3"),
    KEY_4("4"),
    KEY_5("5"),
    KEY_6("6"),
    KEY_7("7"),
    KEY_8("8"),
    KEY_9("9"),
    KEY_A("A"),
    KEY_B("B"),
    KEY_C("C"),
    KEY_D("D"),
    KEY_E("E"),
    KEY_F("F"),
    KEY_G("G"),
    KEY_H("H"),
    KEY_I("I"),
    KEY_J("J"),
    KEY_K("K"),
    KEY_L("L"),
    KEY_M("M"),
    KEY_N("N"),
    KEY_O("O"),
    KEY_P("P"),
    KEY_Q("Q"),
    KEY_R("R"),
    KEY_S("S"),
    KEY_T("T"),
    KEY_U("U"),
    KEY_V("V"),
    KEY_W("W"),
    KEY_X("X"),
    KEY_Y("Y"),
    KEY_Z("Z"),
    KEY_AST("*"),
    KEY_TRALHA("#"),
    MENU("MENU"),
    INFO("INFO"),
    GUIDE("GUIDE"),
    DOWN("CURSOR_DOWN"),
    UP("CURSOR_UP"),
    LEFT("CURSOR_LEFT"),
    RIGHT("CURSOR_RIGHT"),
    CHANNEL_DOWN("CHANNEL_DOWN"),
    CHANNEL_UP("CHANNEL_UP"),
    VOLUME_DOWN("VOLUME_DOWN"),
    VOLUME_UP("VOLUME_UP"),
    ENTER("ENTER"),
    BLUE("BLUE"),
    RED("RED"),
    GREEN("GREEN"),
    YELLOW("YELLOW"),
    BACK("BACK"),
    EXIT("EXIT"),
    POWER("POWER"),
    REWIND("REWIND"),
    STOP("STOP"),
    EJECT("EJECT"),
    PLAY("PLAY"),
    RECORD("RECORD"),
    PAUSE("PAUSE");


    private String name;
    
    private NCLKey(String name) {
        this.name = name;
    }
    
    public static NCLKey getEnumType(String name) {
        for(NCLKey opt : values()){
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
