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

import br.uff.midiacom.ana.datatype.NCLSample;
import br.uff.midiacom.ana.datatype.NCLTime;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLSampleType;
import br.uff.midiacom.ana.node.NCLMedia;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLAreaTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setBegin(new NCLTime(5));
        area.setEnd(new NCLTime(20));

        String expResult = "<area id='anchor' begin='5s' end='20s'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setFirst(new NCLSample(5,NCLSampleType.F));
        area.setLast(new NCLSample(20,NCLSampleType.F));

        String expResult = "<area id='anchor' first='5f' last='20f'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        int[] cord = {1, 2, 6, 56};
        area.setCoords(cord);
        
        String expResult = "<area id='anchor' coords='1,2,6,56'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test4() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setLabel("bla");

        String expResult = "<area id='anchor' label='bla'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test5() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setText("texto");
        area.setPosition(2);

        String expResult = "<area id='anchor' text='texto' position='2'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String expResult = "<area id='anchor' begin='5s' end='20s'/>\n";

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
    public void test7() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String expResult = "<area id='anchor' first='5f' last='20f'/>\n";

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
    public void test8() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String expResult = "<area id='anchor' coords='1,2,6,56'/>\n";

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
    public void test9() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String expResult = "<area id='anchor' label='bla'/>\n";

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
    public void test10() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String expResult = "<area id='anchor' text='texto' position='2'/>\n";

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
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String xml = "<area/>";

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

            NCLArea instance = new NCLArea(reader, null);
            String xml = "<area id='a1' position='1'/>";

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

            NCLArea instance = new NCLArea(reader, null);
            String xml = "<area id='a1' begin='3s' last='3f'/>";

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

            NCLArea instance = new NCLArea(reader, null);
            String xml = "<area id='a1' coords='1' first='2npt'/>";

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
    public void test_validacao5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLArea instance = new NCLArea(reader, null);
            String xml = "<area id='a1' text='aaa' label='bbb'/>";

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
    public void test_validacao6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='teste' type='image/bmp'>"+
                    "<area id='a1' text='bla'/>"+
                    "</media>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLArea area = (NCLArea) instance.getAreas().iterator().next();
            assertFalse(area.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao7() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='teste' type='text/css'>"+
                    "<area id='a1' begin='2s'/>"+
                    "</media>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLArea area = (NCLArea) instance.getAreas().iterator().next();
            assertFalse(area.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao8() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='teste' type='image/bmp'>"+
                    "<area id='a1' last='2f'/>"+
                    "</media>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLArea area = (NCLArea) instance.getAreas().iterator().next();
            assertFalse(area.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao9() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='teste' type='audio/mp3'>"+
                    "<area id='a1' coords='3'/>"+
                    "</media>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLArea area = (NCLArea) instance.getAreas().iterator().next();
            assertFalse(area.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao10() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='teste' type='image/bmp'>"+
                    "<area id='a1' label='bla'/>"+
                    "</media>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLArea area = (NCLArea) instance.getAreas().iterator().next();
            assertFalse(area.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao11() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='teste' type='image/bmp'>"+
                    "<area id='a1' coords='2,5'/>"+
                    "</media>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLArea area = (NCLArea) instance.getAreas().iterator().next();
            boolean result = area.validate();

            for(String msg : area.getWarnings())
                System.out.println(msg);
            for(String msg : area.getErrors())
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