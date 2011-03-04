/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLInstanceType;
import br.pensario.NCLValues.NCLMimeType;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLArea;
import br.pensario.interfaces.NCLProperty;
import java.net.URISyntaxException;
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
public class NCLMediaTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLMedia med = new NCLMedia("m1");
        med.setType(NCLMimeType.AUDIO_MP2);
        med.setSrc("audio.mp2");
        med.setRefer(new NCLMedia("m2"));
        med.setInstance(NCLInstanceType.NEW);
        med.setDescriptor(new NCLDescriptor("dm1"));

        String expResult = "<media id='m1' src='audio.mp2' type='audio/mp2' descriptor='dm1' refer='m2' instance='new'/>\n";
        String result = med.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLMedia med = new NCLMedia("m1");
        NCLArea a = new NCLArea("a1");
        NCLProperty p = new NCLProperty("top");
        med.addArea(a);
        med.addProperty(p);

        String expResult = "<media id='m1'>\n\t<area id='a1'/>\n\t<property name='top'/>\n</media>\n";
        String result = med.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String expResult = "<media id='m1' src='audio.mp2' type='audio/mp2' descriptor='dm1' refer='m2' instance='new'/>\n";

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
    public void test4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia instance = new NCLMedia(reader, null);
            String expResult = "<media id='m1'>\n\t<area id='a1'/>\n\t<property name='top'/>\n</media>\n";

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
    public void test5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<media id='da' refer='db'/>"+
                    "<media id='db' src='media.png'/>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "media.png";
            String result = ((NCLMedia) instance.getBody().getNodes().iterator().next()).getRefer().getSrc();
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
    public void test6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head><descriptorBase><descriptor id='dpa' player='teste'/></descriptorBase></head><body>"+
                    "<media id='da' descriptor='dpa'/>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLMedia) instance.getBody().getNodes().iterator().next()).getDescriptor().getPlayer();
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

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media/>";

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

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='dLegenda' refer='dLegenda'/>";

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

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='dLegenda' instance='new'/>";

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

            NCLMedia instance = new NCLMedia(reader, null);
            String xml = "<media id='dLegenda' refer='outro' instance='new'/>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            boolean result = instance.validate();

            for(String msg : instance.getWarnings())
                System.out.println(msg);
            for(String msg : instance.getErrors())
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