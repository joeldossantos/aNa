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
public class NCLSwitchPortTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLSwitchPort port = new NCLSwitchPort("pinit");
        NCLMapping map = new NCLMapping();
        map.setComponent(new NCLMedia("med1"));
        map.setInterface(new NCLArea("trac1"));
        port.addMapping(map);

        String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";
        String result = port.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSwitchPort t = new NCLSwitchPort("teste");
            NCLSwitchPort instance = new NCLSwitchPort(reader, t);
            instance.setParent(instance);
            String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";

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