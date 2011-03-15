package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLImportType;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;


public class NCLConnectorBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLConnectorBase base = new NCLConnectorBase();
        base.setId("cb");

        String expResult = "<connectorBase id='cb'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLConnectorBase base = new NCLConnectorBase();
        NCLCausalConnector con = new NCLCausalConnector("c1");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportBase(imp);
        base.addCausalConnector(con);

        String expResult = "<connectorBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<causalConnector id='c1'>\n\t</causalConnector>\n</connectorBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLConnectorBase instance = new NCLConnectorBase(reader, null);
            String expResult = "<connectorBase id='cb'/>\n";

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

            NCLConnectorBase instance = new NCLConnectorBase(reader, null);
            String expResult = "<connectorBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<causalConnector id='c1'>\n\t</causalConnector>\n</connectorBase>\n";

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

            NCLConnectorBase instance = new NCLConnectorBase(reader, null);
            String xml = "<connectorBase id='rb'/>";

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

            NCLConnectorBase instance = new NCLConnectorBase(reader, null);
            String xml = "<connectorBase><importNCL alias='base' documentURI='base.ncl'/></connectorBase>";

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

            NCLConnectorBase instance = new NCLConnectorBase(reader, null);
            String xml = "<connectorBase><importBase alias='base' documentURI='base.ncl'/></connectorBase>";

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