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
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
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

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLHeadTest {

    @Test
    public void test1() throws XMLException {
        NCLHead h = new NCLHead();
        h.setImportedDocumentBase(new NCLImportedDocumentBase());
        h.setRuleBase(new NCLRuleBase());
        h.setTransitionBase(new NCLTransitionBase());
        h.addRegionBase(new NCLRegionBase());
        h.setDescriptorBase(new NCLDescriptorBase());
        h.setConnectorBase(new NCLConnectorBase());

        NCLMeta m = new NCLMeta();
        m.setName("autor");
        m.setContent("joel");

        NCLMetadata mt = new NCLMetadata();
        mt.setRDFTree("arvore rdf");

        h.addMeta(m);
        h.addMetadata(mt);

        String expResult = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>\narvore rdf\n\t</metadata>\n</head>\n";
        String result = h.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        String expResult = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>\narvore rdf\n\t</metadata>\n</head>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLHead instance = new NCLHead();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }
}