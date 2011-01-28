/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLSampleType;
import br.pensario.node.NCLMedia;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author joel
 */
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