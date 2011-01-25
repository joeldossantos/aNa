/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.connector;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.reuse.NCLImport;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
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
}