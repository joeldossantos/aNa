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
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.util.SampleType;
import br.uff.midiacom.ana.util.TimeType;
import br.uff.midiacom.ana.util.enums.NCLSampleType;
import br.uff.midiacom.ana.util.ArrayType;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLAreaTest {

    @Test
    public void test1() throws XMLException {
        NCLArea area = new NCLArea("anchor");
        area.setBegin(new TimeType(5));
        area.setEnd(new TimeType(20));

        String expResult = "<area id='anchor' begin='5.0s' end='20.0s'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        NCLArea area = new NCLArea("anchor");
        area.setFirst(new SampleType(5,NCLSampleType.F));
        area.setLast(new SampleType(20,NCLSampleType.F));

        String expResult = "<area id='anchor' first='5f' last='20f'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() throws XMLException {
        NCLArea area = new NCLArea("anchor");
        area.setCoords(new ArrayType("1, 2, 6, 56"));
        
        String expResult = "<area id='anchor' coords='1.0,2.0,6.0,56.0'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test4() throws XMLException {
        NCLArea area = new NCLArea("anchor");
        area.setLabel("bla");

        String expResult = "<area id='anchor' label='bla'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test5() throws XMLException {
        NCLArea area = new NCLArea("anchor");
        area.setText("texto");
        area.setPosition(2);

        String expResult = "<area id='anchor' text='texto' position='2'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test6() throws XMLException {
        String expResult = "<area id='anchor' begin='5.0s' end='20.0s'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLArea instance = new NCLArea();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test7() throws XMLException {
        String expResult = "<area id='anchor' first='5f' last='20f'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLArea instance = new NCLArea();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test8() throws XMLException {
        String expResult = "<area id='anchor' coords='1.0,2.0,6.0,56.0'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLArea instance = new NCLArea();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test9() throws XMLException {
        String expResult = "<area id='anchor' label='bla'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLArea instance = new NCLArea();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test10() throws XMLException {
        String expResult = "<area id='anchor' text='texto' position='2'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLArea instance = new NCLArea();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }
}