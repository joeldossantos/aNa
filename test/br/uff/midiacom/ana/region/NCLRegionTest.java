package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLRegionTest {

    @Test
    public void testRegion1() throws NCLInvalidIdentifierException {
        NCLRegion region = new NCLRegion("rgTV");
        region.setLeft(10, true);
        region.setRight(20, true);
        region.setTop(10, true);
        region.setBottom(20, true);
        region.setWidth(80, true);
        region.setHeight(80, true);
        region.setzIndex(1);
        region.setTitle("Titulo de Teste");

        String expResult = "<region id='rgTV' left='10%' right='20%' top='10%' bottom='20%' height='80%' width='80%' zIndex='1' title='Titulo de Teste'/>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegion2() throws NCLInvalidIdentifierException {
        NCLRegion region = new NCLRegion("rgTV");
        region.setLeft(10, false);
        region.setRight(20, false);
        region.setTop(10, false);
        region.setBottom(20, false);
        region.setWidth(80, false);
        region.setHeight(80, false);
        region.setzIndex(1);
        region.setTitle("Titulo de Teste");

        String expResult = "<region id='rgTV' left='10' right='20' top='10' bottom='20' height='80' width='80' zIndex='1' title='Titulo de Teste'/>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegion3() throws NCLInvalidIdentifierException {
        NCLRegion region = new NCLRegion("rgTV");
        NCLRegion region2 = new NCLRegion("rg1");
        region.addRegion(region2);

        String expResult = "<region id='rgTV'>\n\t<region id='rg1'/>\n</region>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLRegion instance = new NCLRegion(reader, null);
            String expResult = "<region id='rgTV' left='10%' right='20%' top='10%' bottom='20%' height='80%' width='80%' zIndex='1' title='Titulo de Teste'/>\n";

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

            NCLRegion instance = new NCLRegion(reader, null);
            String expResult = "<region id='rgTV' left='10' right='20' top='10' bottom='20' height='80' width='80' zIndex='1' title='Titulo de Teste'/>\n";

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

            NCLRegion instance = new NCLRegion(reader, null);
            String expResult = "<region id='rgTV'>\n\t<region id='rg1'/>\n</region>\n";

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

            NCLRegion instance = new NCLRegion(reader, null);
            String xml = "<region/>";

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

            NCLRegion instance = new NCLRegion(reader, null);
            String xml = "<region id='rgTV'><region/></region>";

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

            NCLRegion instance = new NCLRegion(reader, null);
            String xml = "<region id='rgTV'><region id='rg1'/></region>";

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