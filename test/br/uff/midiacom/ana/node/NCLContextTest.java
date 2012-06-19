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
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.interfaces.NCLPort;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLContextTest {

    @Test
    public void test1() throws XMLException {
        NCLContext cont = new NCLContext("ctx");
        NCLMedia m1 = new NCLMedia("video");
        NCLPort p1 = new NCLPort("pInicio");
        p1.setComponent(m1);

        cont.addNode(m1);
        cont.addPort(p1);

        String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";
        String result = cont.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLContext instance = new NCLContext();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() throws XMLException {
        String xml = "<ncl><body>"+
                "<context id='da' refer='db'/>"+
                "<context id='db'>"+
                "<media id='m1'/>"+
                "</context></body></ncl>";
        
        XMLLoader loader = new XMLLoader(xml);
        NCLDoc instance = new NCLDoc();
        instance.load(loader.getElement());

        String expResult = "m1";
        NCLContext aux1 = (NCLContext) instance.getBody().getNodes().get("da");
        NCLContext aux2 = (NCLContext) aux1.getRefer();
        NCLMedia aux3 = (NCLMedia) aux2.getNodes().iterator().next();
        String result = aux3.getId();
        assertEquals(expResult, result);
    }
}