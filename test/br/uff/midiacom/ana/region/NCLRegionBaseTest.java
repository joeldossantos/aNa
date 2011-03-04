/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.region;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.reuse.NCLImport;
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
public class NCLRegionBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRegionBase base = new NCLRegionBase();
        base.setId("rgb");
        base.setDevice("systemScreen(0)");

        String expResult = "<regionBase id='rgb' device='systemScreen(0)'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLRegionBase base = new NCLRegionBase();
        NCLRegion region = new NCLRegion("rgTV");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        imp.setRegion(region);
        base.addImportBase(imp);
        base.addRegion(region);

        String expResult = "<regionBase>\n\t<importBase alias='base' documentURI='base.ncl' region='rgTV'/>\n\t<region id='rgTV'/>\n</regionBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLRegionBase instance = new NCLRegionBase(reader, null);
            String expResult = "<regionBase id='rgb' device='systemScreen(0)'/>\n";

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

            NCLRegionBase instance = new NCLRegionBase(reader, null);
            String expResult = "<regionBase>\n\t<importBase alias='base' documentURI='base.ncl' region='rgTV'/>\n\t<region id='rgTV'/>\n</regionBase>\n";

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

            NCLDoc doc = new NCLDoc();
            doc.setReader(reader);
            String xml = "<ncl><head><regionBase region='rgTV'>"+
                "<region id='rgTV' title='teste'/>"+
                "</regionBase></head></ncl>";

            reader.setContentHandler(doc);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLRegion) doc.getHead().getRegionBase().getRegions().iterator().next()).getTitle();
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

            NCLRegionBase instance = new NCLRegionBase(reader, null);
            String xml = "<regionBase id='rb'/>";

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

            NCLRegionBase instance = new NCLRegionBase(reader, null);
            String xml = "<regionBase><importNCL alias='base' documentURI='base.ncl'/></regionBase>";

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

            NCLRegionBase instance = new NCLRegionBase(reader, null);
            String xml = "<regionBase><importBase alias='base' documentURI='base.ncl'/></regionBase>";

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