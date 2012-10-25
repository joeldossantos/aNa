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

import java.net.URI;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.io.File;
import br.uff.midiacom.ana.util.enums.NCLNamespace;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLDocTest {

    @Test
    public void test1() throws XMLException {
        NCLDoc d = new NCLDoc();
        d.setXmlns(NCLNamespace.EDTV);
        d.setId("meudoc");
        d.setTitle("documento de teste");
        d.setHead(new NCLHead());
        d.setBody(new NCLBody());

        String expResult = "<?xml version='1.0' encoding='ISO-8859-1'?>\n<!-- Generated with aNa - API for NCL Authoring -->\n\n"+
                "<ncl id='meudoc' title='documento de teste' xmlns='http://www.ncl.org.br/NCL3.0/EDTVProfile'>\n\t"+
                "<head>\n\t</head>\n\t<body>\n\t</body>\n</ncl>\n";
        String result = d.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        String expResult = "<?xml version='1.0' encoding='ISO-8859-1'?>\n<!-- Generated with aNa - API for NCL Authoring -->\n\n"+
                "<ncl id='meudoc' title='documento de teste' xmlns='http://www.ncl.org.br/NCL3.0/EDTVProfile'>\n\t"+
                "<head>\n\t</head>\n\t<body>\n\t</body>\n</ncl>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLDoc instance = new NCLDoc();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLoadFile() throws XMLException, URISyntaxException {
        URI path = getClass().getResource("../../../../doc.ncl").toURI();
        File f = new File(path);
        NCLDoc instance = new NCLDoc();
        instance.loadXML(f);
        
        assertTrue(true);
        System.out.println(instance.parse(0));
    }
}