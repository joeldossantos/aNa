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
 *        This product includes the Api for NCL Authoring - aNa, developed
 *        by MídiaCom Lab (www.midiacom.uff.br) and its contributors.
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

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLSwitch;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLSwitchPortTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLSwitchPort port = new NCLSwitchPort("pinit");
        NCLMapping map = new NCLMapping();
        map.setComponent(new NCLMedia("med1"));
        map.setInterface(new NCLArea("trac1"));
        port.addMapping(map);

        String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";
        String result = port.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSwitchPort instance = new NCLSwitchPort(reader, null);
            String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<switch id='sw'><switchPort><mapping component='m1' interface='a1'/></switchPort>"+
                    "<media id='m1' src='media.png'><area id='a1' label='teste'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "media.png";
            String result = ((NCLMedia) ((NCLMapping) ((NCLSwitchPort) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getPorts().iterator().next()).getMappings().iterator().next()).getComponent()).getSrc();
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<switch id='sw'><switchPort><mapping component='m1' interface='a1'/></switchPort>"+
                    "<media id='m1' src='media.png'><area id='a1' label='teste'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLArea) ((NCLMapping) ((NCLSwitchPort) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getPorts().iterator().next()).getMappings().iterator().next()).getInterface()).getLabel();
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSwitchPort instance = new NCLSwitchPort(reader, null);
            String xml = "<switchPort/>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSwitchPort instance = new NCLSwitchPort(reader, null);
            String xml = "<switchPort id='start'/>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSwitchPort instance = new NCLSwitchPort(reader, null);
            String xml = "<switchPort id='start'>"+
                    "<mapping/>"+
                    "</switchPort>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body><switch id='sw'>"+
                    "<switchPort><mapping component='m1' interface='a2'/></switchPort>"+
                    "<media id='m1'><area id='a1'/></media>"+
                    "<media id='m2'><area id='a2'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLSwitch swt = (NCLSwitch) instance.getBody().getNodes().iterator().next();
            NCLSwitchPort swtp = (NCLSwitchPort) swt.getPorts().iterator().next();
            NCLMapping map = (NCLMapping) swtp.getMappings().iterator().next();

            assertFalse(map.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body><switch id='sw'>"+
                    "<switchPort><mapping component='m4'/></switchPort>"+
                    "<media id='m1'><area id='a1'/></media>"+
                    "<media id='m2'><area id='a2'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLSwitch swt = (NCLSwitch) instance.getBody().getNodes().iterator().next();
            NCLSwitchPort swtp = (NCLSwitchPort) swt.getPorts().iterator().next();
            NCLMapping map = (NCLMapping) swtp.getMappings().iterator().next();

            assertFalse(map.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body><switch id='sw'>"+
                    "<switchPort><mapping component='m1'/></switchPort>"+
                    "<media id='m1'><area id='a1'/></media>"+
                    "<media id='m2'><area id='a2'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLSwitch swt = (NCLSwitch) instance.getBody().getNodes().iterator().next();
            NCLSwitchPort swtp = (NCLSwitchPort) swt.getPorts().iterator().next();
            NCLMapping map = (NCLMapping) swtp.getMappings().iterator().next();

            boolean result = map.validate();

            for(String msg : map.getWarnings())
                System.out.println(msg);
            for(String msg : map.getErrors())
                System.out.println(msg);

            assertTrue(result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}