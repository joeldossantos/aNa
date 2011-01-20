/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.descriptor;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.rule.NCLRule;
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
public class NCLDescriptorSwitchTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLDescriptorSwitch ds = new NCLDescriptorSwitch("dLegenda");
        NCLDescriptor desc = new NCLDescriptor("dpt");
            NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(desc);
        ds.addBind(bind);
        ds.addDescriptor(desc);
        ds.setDefaultDescriptor(desc);

        String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule rule='rpt' constituent='dpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";
        String result = ds.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDescriptorSwitch t = new NCLDescriptorSwitch("teste");
            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, t);
            instance.setParent(instance);
            String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule rule='rpt' constituent='dpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(NCLInvalidIdentifierException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}