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
 * Nomes dos conjuntos dos elementos NCL
 */
public enum NCLElementSets {

    ACTIONS("actions"),
    AREAS("areas"),
    ATTRIBUTEASSESSMENTS("attributeAssessments"),
    BINDPARAMS("bindParams"),
    BINDS("binds"),
    BODY("body"),
    CONDITIONS("conditions"),
    CONNECTORS("connectors"),
    CONNECTOR_PARAMS("connector_params"),
    CONNECTORBASE("connectorBase"),
    DEFAULTCOMPONENT("defaultComponent"),
    DEFAULTDESCRIPTOR("defaultDescriptor"),
    DESCRIPTORS("descriptors"),
    DESCRIPTORPARAM("descriptorParam"),
    DESCRIPTORBASE("descriptorBase"),
    HEAD("head"),
    IMPORTS("imports"),
    IMPORTEDDOCUMENTBASE("importedDocumentBase"),
    LINKPARAMS("linkParams"),
    LINKS("links"),
    MAPPINGS("mappings"),
    METADATAS("metadatas"),
    METAS("metas"),
    NODES("nodes"),
    PORTS("ports"),
    PROPERTIES("properties"),
    REGIONS("regions"),
    REGIONBASE("regionBase"),
    RULES("rules"),
    RULEBASE("ruleBase"),
    STATEMENTS("statements"),
    TRANSITIONS("transitions"),
    TRANSITIONBASE("transitionBase"),
    VALUEASSESSMENT("valueAssessment");

    private String name;
    private NCLElementSets(String name) { this.name = name;}
    @Override
    public String toString() { return name;}
}
