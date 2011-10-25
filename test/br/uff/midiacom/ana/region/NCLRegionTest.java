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
package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.RelativeType;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLRegionTest {

    @Test
    public void testRegion1() throws XMLException {
        NCLRegion region = new NCLRegion("rgTV");
        region.setLeft(new RelativeType(10, true));
        region.setRight(new RelativeType(20, true));
        region.setTop(new RelativeType(10, true));
        region.setBottom(new RelativeType(20, true));
        region.setWidth(new RelativeType(80, true));
        region.setHeight(new RelativeType(80, true));
        region.setzIndex(1);
        region.setTitle("Titulo de Teste");

        String expResult = "<region id='rgTV' left='10.0%' right='20.0%' top='10.0%' bottom='20.0%' height='80.0%' width='80.0%' zIndex='1' title='Titulo de Teste'/>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegion2() throws XMLException {
        NCLRegion region = new NCLRegion("rgTV");
        region.setLeft(new RelativeType(10));
        region.setRight(new RelativeType(20));
        region.setTop(new RelativeType(10));
        region.setBottom(new RelativeType(20));
        region.setWidth(new RelativeType(80));
        region.setHeight(new RelativeType(80));
        region.setzIndex(1);
        region.setTitle("Titulo de Teste");

        String expResult = "<region id='rgTV' left='10.0' right='20.0' top='10.0' bottom='20.0' height='80.0' width='80.0' zIndex='1' title='Titulo de Teste'/>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegion3() throws XMLException {
        NCLRegion region = new NCLRegion("rgTV");
        NCLRegion region2 = new NCLRegion("rg1");
        region.addRegion(region2);

        String expResult = "<region id='rgTV'>\n\t<region id='rg1'/>\n</region>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2() throws XMLException {
        String expResult = "<region id='rgTV' left='10.0%' right='20.0%' top='10.0%' bottom='20.0%' height='80.0%' width='80.0%' zIndex='1' title='Titulo de Teste'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLRegion instance = new NCLRegion();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() throws XMLException {
        String expResult = "<region id='rgTV' left='10.0' right='20.0' top='10.0' bottom='20.0' height='80.0' width='80.0' zIndex='1' title='Titulo de Teste'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLRegion instance = new NCLRegion();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test4() throws XMLException {
        String expResult = "<region id='rgTV'>\n\t<region id='rg1'/>\n</region>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLRegion instance = new NCLRegion();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }
}