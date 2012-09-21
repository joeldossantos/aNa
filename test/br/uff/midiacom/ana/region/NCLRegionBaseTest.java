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

import br.uff.midiacom.ana.util.enums.NCLDevice.DeviceName;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.reuse.NCLImportBase;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.util.SrcType;
import br.uff.midiacom.ana.util.enums.NCLDevice;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLRegionBaseTest {

    @Test
    public void test1() throws XMLException {
        NCLRegionBase base = new NCLRegionBase();
        base.setId("rgb");
        NCLDevice dev = new NCLDevice(DeviceName.SYSTEM_SCREEN);
        dev.setParamenter(0);
        base.setDevice(dev);

        String expResult = "<regionBase id='rgb' device='systemScreen(0)'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        NCLRegionBase base = new NCLRegionBase();
        NCLRegion region = new NCLRegion("rgTV");
        NCLImportBase imp = new NCLImportBase();
        imp.setAlias("base");
        imp.setDocumentURI(new SrcType("base.ncl"));
        imp.setRegion(region);
        base.addImportBase(imp);
        base.addRegion(region);

        String expResult = "<regionBase>\n\t<importBase alias='base' documentURI='base.ncl' region='rgTV'/>\n\t<region id='rgTV'/>\n</regionBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() throws XMLException {
        String expResult = "<regionBase id='rgb' device='systemScreen(0)'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLRegionBase instance = new NCLRegionBase();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test5() throws XMLException {
        String xml = "<ncl><head><regionBase region='rgTV'>"+
                "<region id='rgTV' title='teste'/>"+
                "</regionBase></head></ncl>";
        
        XMLLoader loader = new XMLLoader(xml);
        NCLDoc instance = new NCLDoc();
        instance.load(loader.getElement());

        String expResult = "teste";
        String result = ((NCLRegion) ((NCLRegionBase) instance.getHead().getRegionBases().get(0)).getParentRegion()).getTitle();
        assertEquals(expResult, result);

    }
    
    @Test
    public void testDevice() throws XMLException {
        NCLHead h = new NCLHead();
        NCLRegionBase b1, b2;
        
        b1 = new NCLRegionBase();
        NCLDevice d1 = new NCLDevice(DeviceName.SYSTEM_AUDIO, 0);
        b1.setDevice(d1);
        
        b2 = new NCLRegionBase();
        NCLDevice d2 = new NCLDevice(DeviceName.SYSTEM_AUDIO, 1);
        b2.setDevice(d2);
        
        h.addRegionBase(b1);
        h.addRegionBase(b2);
        
        String expResult = "<head>\n\t<regionBase device='systemAudio(0)'/>\n\t<regionBase device='systemAudio(1)'/>\n</head>\n";
        String result = h.parse(0);
        assertEquals(expResult, result);
    }
}