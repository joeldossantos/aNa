/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLParsingErrorHandler;
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
public class NCLSwitchTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLSwitch s = new NCLSwitch("escolhe");
        NCLMedia med = new NCLMedia("m1");
        NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(med);
        s.addBind(bind);
        s.addNode(med);
        s.setDefaultComponent(med);

        String expResult = "<switch id='escolhe'>\n\t<bindRule rule='rpt' constituent='m1'/>\n\t<defaultComponent component='m1'/>\n\t<media id='m1'/>\n</switch>\n";
        String result = s.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSwitch t = new NCLSwitch("teste");
            NCLSwitch instance = new NCLSwitch(reader, t);
            instance.setParent(instance);
            String expResult = "<switch id='escolhe'>\n\t<bindRule rule='rpt' constituent='m1'/>\n\t<defaultComponent component='m1'/>\n\t<media id='m1'/>\n\t<switch id='s1'/>\n</switch>\n";

            reader.setContentHandler(instance);
            reader.setErrorHandler(new NCLParsingErrorHandler());
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