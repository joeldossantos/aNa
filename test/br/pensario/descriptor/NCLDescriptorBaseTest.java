/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.descriptor;

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
public class NCLDescriptorBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLDescriptorBase base = new NCLDescriptorBase();
        base.setId("db");

        String expResult = "<descriptorBase id='db'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLDescriptorBase base = new NCLDescriptorBase();
        NCLDescriptor desc = new NCLDescriptor("dTV");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportBase(imp);
        base.addDescriptor(desc);

        String expResult = "<descriptorBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<descriptor id='dTV'/>\n</descriptorBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDescriptorBase instance = new NCLDescriptorBase();
            instance.setReader(reader);
            instance.setParent(instance);
            String expResult = "<descriptorBase id='db'/>\n";

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

            NCLDescriptorBase instance = new NCLDescriptorBase();
            instance.setReader(reader);
            instance.setParent(instance);
            String expResult = "<descriptorBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<descriptor id='dTV'/>\n</descriptorBase>\n";

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