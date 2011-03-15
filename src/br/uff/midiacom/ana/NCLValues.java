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
 *        This product includes the Api for NCL Authoring - aNa, developed
 *        by MídiaCom Lab (www.midiacom.uff.br) and its contributors.
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
package br.uff.midiacom.ana;


/**
 * Classe que define um conjunto de valores padronizados para os elementos da
 * linguagem NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public abstract class NCLValues {


    /**
     * Valores padronizados de Namespace do documento NCL (atributo <i>xmlns</i>.
     */
    public enum NCLNamespace {
        
        EDTV("http://www.ncl.org.br/NCL3.0/profiles/NCL30EDTV.xsd"),
        BDTV("http://www.ncl.org.br/NCL3.0/profiles/NCL30BDTV.xsd"),
        CAUSAL_CONNECTOR("http://www.ncl.org.br/NCL3.0/profiles/NCL30CausalConnector.xsd");


        private String name;
        private NCLNamespace(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Operadores lógicos da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLOperator {
        
        AND("and"),
        OR("or");
        
        
        private String name;
        private NCLOperator(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }


    /**
     * Operadores de comparação da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLComparator {
        
        EQ("eq"),
        NE("ne"),
        GT("gt"),
        LT("lt"),
        GTE("gte"),
        LTE("lte");
        
        
        private String name;
        private NCLComparator(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Cores definidas pela <i>Nested Context Language</i> (NCL).
     */
    public enum NCLColor {
        
        GRAY("gray"),
        WHITE("white"),
        BLACK("black"),
        SILVER("silver"),
        RED("red"),
        MAROON("maroon"),
        FUCHSIA("fuchsia"),
        PURPLE("purple"),
        LIME("lime"),
        GREEN("green"),
        YELLOW("yellow"),
        OLIVE("olive"),
        BLUE("blue"),
        NAVY("navy"),
        AQUA("aqua"),
        TEAL("teal");
        
        
        private String name;
        private NCLColor(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Operadores lógicos para condições compostas da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLConditionOperator {
        
        AND("and"),
        OR("or");
        
        
        private String name;
        private NCLConditionOperator(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Operadores lógicos para ações compostas da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLActionOperator {
        
        PAR("par"),
        SEQ("seq");
        
        
        private String name;
        private NCLActionOperator(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
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
        private NCLMimeType(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }


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
    }

    
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
        CHANNEL_KEYBOARDBOUNDS("channel.keyboardBounds");

        
        private String name;
        private NCLSystemVariable(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
    /**
     * Nomes das variáveis de sistema (com argumento) da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLArgSystemVariable {

        SYSTEM_RETURNBITRATE_i("system.returnBitRate(i)"),
        SYSTEM_SCREENSIZE_i("system.screenSize(i)"),
        SYSTEM_SCREENGRAPHICSIZE_i("system.screenGraphicSize(i)"),
        SYSTEM_AUDIOTYPE_i("system.audioType(i)"),
        SYSTEM_DEVNUMBER_i("system.devNumber(i)"),
        SYSTEM_CLASSTYPE_i("system.classType(i)"),
        SYSTEM_INFO_i("system.info(i)");

        
        private String name;
        private NCLArgSystemVariable(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Tipos de amostra definidos paro o atributo <i>first</i> e <i>last</i> do
     * elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLSampleType {

    S, F, NPT
    }

    
    /**
     * Papéis padronizados para condições da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLDefaultConditionRole {
        
        ONBEGIN("onBegin"),
        ONEND("onEnd"),
        ONABORT("onAbort"),
        ONPAUSE("onPause"),
        ONRESUME("onResume"),
        ONSELECTION("onSelection"),
        ONBEGINATTRIBUTION("onBeginAttribution"),
        ONENDATTRIBUTION("onEndAttribution");
        
        
        private String name;
        private NCLDefaultConditionRole(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Papéis padronizados para ações da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLDefaultActionRole {
        
        START("start"),
        STOP("stop"),
        ABORT("abort"),
        PAUSE("pause"),
        RESUME("resume"),
        SET("set");
        
        
        private String name;
        private NCLDefaultActionRole(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Tipos de URI definidas para o atributo <i>src</i> do elemento
     * <i>media</i> da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLUriType {

        FILE("file://"),
        HTTP("http://"),
        HTTPS("https://"),
        RSTP("rstp://"),
        RTP("rtp://"),
        NCLMIRROR("nclmirror://"),
        SBTVD_TS("sbtvd-ts://");
        
        
        private String name;
        private NCLUriType(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
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
        private NCLKey(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }


    /**
     * Tipos de eventos de uma mídia da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLEventType {

        PRESENTATION("presentation"),
        SELECTION("selection"),
        ATTRIBUTION("attribution");


        private String name;
        private NCLEventType(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
    /**
     * Transições dos estados dos eventos de uma mídia da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLEventTransition {

        STARTS("starts"),
        STOPS("stops"),
        ABORTS("aborts"),
        PAUSES("pauses"),
        RESUMES("resumes");


        private String name;
        private NCLEventTransition(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
    /**
     * Ações possíveis de serem realizadas sobre máquinas de estados de uma
     * mídia da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLEventAction {

        START("start"),
        STOP("stop"),
        ABORT("abort"),
        PAUSE("pause"),
        RESUME("resume");


        private String name;
        private NCLEventAction(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }

    
    /**
     * Valores do atributo <i>attributeType</i> do elemento
     * <i>attributeAssessment</i> da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLAttributeType {

        STATE("state"),
        OCCURRENCES("occurrences"),
        REPETITIONS("repetitions"),
        NODE_PROPERTY("nodeProperty");


        private String name;
        private NCLAttributeType(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
    /**
     * Valores padronizados para o elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLDefaultValueAssessment {

        SLEEPING("sleeping"),
        OCCURRING("occurring"),
        PAUSED("paused");


        private String name;
        private NCLDefaultValueAssessment(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
    /**
     * Tipos de instância usados no reúso de elementos da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLInstanceType {
        
        NEW("new"),
        INST_SAME("instSame"),
        GRAD_SAME("gradSame");


        private String name;
        private NCLInstanceType(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
    /**
     * Tipos de transições da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLTransitionType {
        
        BAR("barWipe"),
        IRIS("irisWipe"),
        CLOCK("clockWipe"),
        SNAKE("snakeWipe"),
        FADE("fade");


        private String name;
        private NCLTransitionType(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
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
        private NCLTransitionSubtype(String name, NCLTransitionType type) { this.name = name; this.type = type;}
        @Override
        public String toString() { return name; }
        public NCLTransitionType getType() { return type; }
    }
    
    
    /**
     * Direção de uma transição da <i>Nested Context Language</i> (NCL).
     */
    public enum NCLTransitionDirection {
        
        FORWARD("forward"),
        REVERSE("reverse");


        private String name;
        private NCLTransitionDirection(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }
    
    
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
        FONT_STYLE("fontStyle"),
        FONT_SIZE("fontSize"),
        FONT_VARIANT("fontVariant"),
        FONT_WEIGHT("fontWeight"),
        FONT_COLOR("fontColor"),
        REUSE_PLAYER("reusePlayer"),
        PLAYER_LIFE("playerLife");


        private String name;
        private NCLAttributes(String name) { this.name = name;}
        @Override
        public String toString() { return name; }
    }


    /**
     * Tipos do parâmetro usado dentro de links (pode ser parâmetro do link ou do bind).
     */
    public enum NCLParamInstance {

        LINKPARAM("linkParam"),
        BINDPARAM("bindParam");

        private String name;
        private NCLParamInstance(String name) { this.name = name;}
        @Override
        public String toString() { return name;}
    }


    /**
     * Tipos do importador usado no cabeçalho.
     */
    public enum NCLImportType {

        BASE("importBase"),
        NCL("importNCL");

        private String name;
        private NCLImportType(String name) { this.name = name;}
        @Override
        public String toString() { return name;}
    }
}