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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLSwitch;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLSwitchPortTest {

    @Test
    public void test1() throws XMLException {
        NCLSwitchPort port = new NCLSwitchPort("pinit");
        NCLMapping map = new NCLMapping();
        map.setComponent(new NCLMedia("med1"));
        map.setInterface(new NCLArea("trac1"));
        port.addMapping(map);

        String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";
        String result = port.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test2() throws XMLException {
//        String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";
//
//        XMLLoader loader = new XMLLoader(expResult);
//        NCLSwitchPort instance = new NCLSwitchPort();
//        instance.load(loader.getElement());
//
//        String result = instance.parse(0);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test3() throws XMLException {
//        String xml = "<switch id='sw'><switchPort><mapping component='m1' interface='a1'/></switchPort>"+
//                "<media id='m1' src='media.png'><area id='a1' label='teste'/></media>"+
//                "</switch>";
//        XMLLoader loader = new XMLLoader(xml);
//        NCLSwitch instance = new NCLSwitch();
//        instance.load(loader.getElement());
//
//        NCLSwitchPort i2 = (NCLSwitchPort) instance.getPorts().get(0);
//        NCLMapping i1 = (NCLMapping) i2.getMappings().get(0);
//        NCLMedia i = (NCLMedia) i1.getComponent();
//
//        String expResult = "media.png";
//        String result = i.getSrc().parse();
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test4() throws XMLException {
//        String xml = "<switch id='sw'><switchPort><mapping component='m1' interface='a1'/></switchPort>"+
//                "<media id='m1' src='media.png'><area id='a1' label='teste'/></media>"+
//                "</switch>";
//        XMLLoader loader = new XMLLoader(xml);
//        NCLSwitch instance = new NCLSwitch();
//        instance.load(loader.getElement());
//
//        NCLSwitchPort i2 = (NCLSwitchPort) instance.getPorts().get(0);
//        NCLMapping i1 = (NCLMapping) i2.getMappings().get(0);
//        NCLArea i = (NCLArea) i1.getInterface();
//
//        String expResult = "teste";
//        String result = i.getLabel();
//        assertEquals(expResult, result);
//    }
}