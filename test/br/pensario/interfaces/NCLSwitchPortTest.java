/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.node.NCLMedia;
import br.pensario.node.NCLSwitch;
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

            NCLSwitchPort instance = new NCLSwitchPort(reader, null);
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
                    "<switch id='sw'><switchPort><mapping component='m1' interface='a1'/></switchPort>"+
                    "<media id='m1' src='media.png'><area id='a1' label='teste'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "media.png";
            String result = ((NCLMedia) ((NCLMapping) ((NCLSwitchPort) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getPorts().iterator().next()).getMappings().iterator().next()).getComponent()).getSrc();
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
                    "<switch id='sw'><switchPort><mapping component='m1' interface='a1'/></switchPort>"+
                    "<media id='m1' src='media.png'><area id='a1' label='teste'/></media>"+
                    "</switch></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLArea) ((NCLMapping) ((NCLSwitchPort) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getPorts().iterator().next()).getMappings().iterator().next()).getInterface()).getLabel();
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