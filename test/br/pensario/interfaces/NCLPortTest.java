/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLInvalidIdentifierException;
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

            NCLPort t = new NCLPort("teste");
            NCLPort instance = new NCLPort(reader, t);
            instance.setParent(instance);
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
        catch(NCLInvalidIdentifierException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}