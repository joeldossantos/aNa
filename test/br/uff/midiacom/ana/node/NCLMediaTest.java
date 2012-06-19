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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.util.SrcType;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLInstanceType;
import br.uff.midiacom.ana.util.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.interfaces.NCLArea;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLMediaTest {

    @Test
    public void test1() throws XMLException {
        NCLMedia med = new NCLMedia("m1");
        med.setType(NCLMimeType.AUDIO_MP2);
        med.setSrc(new SrcType("audio.mp2"));
        med.setRefer(new NCLMedia("m2"));
        med.setInstance(NCLInstanceType.NEW);
        med.setDescriptor(new NCLDescriptor("dm1"));

        String expResult = "<media id='m1' src='audio.mp2' refer='m2' instance='new' type='audio/mp2' descriptor='dm1'/>\n";
        String result = med.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        NCLMedia med = new NCLMedia("m1");
        NCLArea a = new NCLArea("a1");
        NCLProperty p = new NCLProperty("top");
        med.addArea(a);
        med.addProperty(p);

        String expResult = "<media id='m1'>\n\t<area id='a1'/>\n\t<property name='top'/>\n</media>\n";
        String result = med.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test3() throws XMLException {
//        String expResult = "<media id='m1' src='audio.mp2' type='audio/mp2' descriptor='dm1' refer='m2' instance='new'/>\n";
//
//        XMLLoader loader = new XMLLoader(expResult);
//        NCLMedia instance = new NCLMedia();
//        instance.load(loader.getElement());
//
//        String result = instance.parse(0);
//        assertEquals(expResult, result);
//    }

    @Test
    public void test4() throws XMLException {
        String expResult = "<media id='m1'>\n\t<area id='a1'/>\n\t<property name='top'/>\n</media>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLMedia instance = new NCLMedia();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test5() throws XMLException {
        String xml = "<ncl><body>"+
                    "<media id='da' refer='db'/>"+
                    "<media id='db' src='media.png'/>"+
                    "</body></ncl>";
        
        XMLLoader loader = new XMLLoader(xml);
        NCLDoc instance = new NCLDoc();
        instance.load(loader.getElement());

        String expResult = "media.png";
        String result = ((NCLMedia) ((NCLMedia) instance.getBody().getNodes().get("da")).getRefer()).getSrc().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void test6() throws XMLException {
        String xml = "<ncl><head><descriptorBase><descriptor id='dpa' player='teste'/></descriptorBase></head><body>"+
                    "<media id='da' descriptor='dpa'/>"+
                    "</body></ncl>";
        
        XMLLoader loader = new XMLLoader(xml);
        NCLDoc instance = new NCLDoc();
        instance.load(loader.getElement());

        String expResult = "teste";
        String result = ((NCLDescriptor) ((NCLMedia) instance.getBody().getNodes().get("da")).getDescriptor()).getPlayer();
        assertEquals(expResult, result);
    }
}