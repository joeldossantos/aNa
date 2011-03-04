package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.node.NCLMedia;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLPortTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLPort port = new NCLPort("pinit");
        port.setComponent(new NCLMedia("med1"));
        port.setInterface(new NCLArea("trac1"));

        String expResult = "<port id='pinit' component='med1' interface='trac1'/>\n";
        String result = port.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLPort instance = new NCLPort(reader, null);
            String expResult = "<port id='pinit' component='med1' interface='trac1'/>\n";

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
                    "<port id='pa' component='m1'/>"+
                    "<media id='m1' src='media.png'/>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "media.png";
            String result = ((NCLMedia) ((NCLPort) instance.getBody().getPorts().iterator().next()).getComponent()).getSrc();
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
                    "<port id='pa' component='m1' interface='a1'/>"+
                    "<media id='m1'><area id='a1' label='teste'/></media>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLArea) ((NCLPort) instance.getBody().getPorts().iterator().next()).getInterface()).getLabel();
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

            NCLPort instance = new NCLPort(reader, null);
            String xml = "<port component='video'/>";

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

            NCLPort instance = new NCLPort(reader, null);
            String xml = "<port id='start'/>";

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

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<port id='pa' component='m1' interface='a2'/>"+
                    "<media id='m1'><area id='a1' label='teste'/></media>"+
                    "<media id='m2'><area id='a2' label='teste'/></media>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLPort port = (NCLPort) instance.getBody().getPorts().iterator().next();

            assertFalse(port.validate());
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
            String xml = "<ncl><body>"+
                    "<port id='pa' component='m4'/>"+
                    "<media id='m1'><area id='a1' label='teste'/></media>"+
                    "<media id='m2'><area id='a2' label='teste'/></media>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLPort port = (NCLPort) instance.getBody().getPorts().iterator().next();

            assertFalse(port.validate());
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
            String xml = "<ncl><body>"+
                    "<port id='pa' component='m1'/>"+
                    "<media id='m1'><area id='a1' label='teste'/></media>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLPort port = (NCLPort) instance.getBody().getPorts().iterator().next();

            boolean result = port.validate();

            for(String msg : port.getWarnings())
                System.out.println(msg);
            for(String msg : port.getErrors())
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