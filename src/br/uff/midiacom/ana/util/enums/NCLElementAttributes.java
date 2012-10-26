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
 * Nomes dos atributos dos elementos NCL
 */
public enum NCLElementAttributes {

    ACTION("action"),
    ACTIONTYPE("actionType"),
    ALIAS("alias"),
    ASSESSMENTSTATEMENT("assessmentStatement"),
    ATTRIBUTETYPE("attributeType"),
    ATTRIBUTEASSESSMENT("attributeAssessment"),
    AREA("area"),
    BASEID("baseId"),
    BEGIN("begin"),
    BIND("bind"),
    BINDRULE("bindRule"),
    BINDPARAM("bindParam"),
    BODY("body"),
    BORDERCOLOR("borderColor"),
    BORDERWIDTH("borderWidth"),
    BOTTOM("bottom"),
    BY("by"),
    CAUSALCONNECTOR("causalConnector"),
    COMPARATOR("comparator"),
    COMPONENT("component"),
    COMPOSITERULE("compositeRule"),
    COMPOUNDACTION("compoundAction"),
    COMPOUNDCONDITION("compoundCondition"),
    COMPOUNDSTATEMENT("compoundStatement"),
    CONDITION("condition"),
    CONNECTORBASE("connectorBase"),
    CONNECTORPARAM("connectorParam"),
    CONSTITUENT("constituent"),
    CONTENT("content"),
    CONTEXT("context"),
    COORDS("coords"),
    DEFAULTCOMPONENT("defaultComponent"),
    DEFAULTDESCRIPTOR("defaultDescriptor"),
    DELAY("delay"),
    DESCRIPTOR("descriptor"),
    DESCRIPTORBASE("descriptorBase"),
    DESCRIPTORPARAM("descriptorParam"),
    DESCRIPTORSWITCH("descriptorSwitch"),
    DEVICE("device"),
    DIRECTION("direction"),
    DOCUMENTURI("documentURI"),
    DUR("dur"),
    DURATION("duration"),
    END("end"),
    ENDPROGRESS("endProgress"),
    EVENTTYPE("eventType"),
    EXPLICITDUR("explicitDur"),
    FADECOLOR("fadeColor"),
    FIRST("first"),
    FOCUSBORDERCOLOR("focusBorderColor"),
    FOCUSBORDERTRANSPARENCY("focusBorderTransparency"),
    FOCUSBORDERWIDTH("focusBorderWidth"),
    FOCUSINDEX("focusIndex"),
    FOCUSSELSRC("focusSelSrc"),
    FOCUSSRC("focusSrc"),
    FREEZE("freeze"),
    HEAD("head"),
    HEIGHT("height"),
    HORREPEAT("horRepeat"),
    ID("id"),
    IMPORTBASE("importBase"),
    IMPORTNCL("importNCL"),
    IMPORTEDDOCUMENTBASE("importedDocumentBase"),
    INSTANCE("instance"),
    INTERFACE("interface"),
    ISNEGATED("isNegated"),
    KEY("key"),
    LABEL("label"),
    LAST("last"),
    LEFT("left"),
    LINK("link"),
    LINKPARAM("linkParam"),
    MAX("max"),
    MAPPING("mapping"),
    MEDIA("media"),
    META("meta"),
    METADATA("metadata"),
    MIN("min"),
    MOVEDOWN("moveDown"),
    MOVELEFT("moveLeft"),
    MOVERIGHT("moveRight"),
    MOVEUP("moveUp"),
    NAME("name"),
    OFFSET("offset"),
    OPERATOR("operator"),
    PARENT("parent"),
    PLAYER("player"),
    POSITION("position"),
    PORT("port"),
    PROPERTY("property"),
    QUALIFIER("qualifier"),
    RDFTREE("rdfTree"),
    REFER("refer"),
    REGION("region"),
    REGIONBASE("regionBase"),
    RELATIVEBOTTOM("relativeBottom"),
    RELATIVEHEIGHT("relativeHeight"),
    RELATIVELEFT("relativeLeft"),
    RELATIVERIGHT("relativeRight"),
    RELATIVETOP("relativeTop"),
    RELATIVEWIDTH("relativeWidth"),
    REPEAT("repeat"),
    REPEATDELAY("repeatDelay"),
    RIGHT("right"),
    ROLE("role"),
    RULE("rule"),
    RULEBASE("ruleBase"),
    SIMPLEACTION("simpleAction"),
    SIMPLECONDITION("simpleCondition"),
    SELBORDERCOLOR("selBorderColor"),
    SRC("src"),
    STARTPROGRESS("startProgress"),
    SUBTYPE("subtype"),
    SWITCH("switch"),
    SWITCHPORT("switchPort"),
    TEXT("text"),
    TITLE("title"),
    TOP("top"),
    TRANSIN("transIn"),
    TRANSITION("transition"),
    TRANSITIONBASE("transitionBase"),
    TRANSOUT("transOut"),
    TYPE("type"),
    VALUE("value"),
    VALUEASSESSMENT("valueAssessment"),
    VAR("var"),
    VERTREPEAT("vertRepeat"),
    WIDTH("width"),
    XCONNECTOR("xconnector"),
    XMLNS("xmlns"),
    ZINDEX("zIndex");

    private String name;
    private NCLElementAttributes(String name) { this.name = name;}
    @Override
    public String toString() { return name;}
}
