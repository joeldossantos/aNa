/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLInstanceType;
import br.pensario.NCLValues.NCLMimeType;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLArea;
import br.pensario.interfaces.NCLProperty;
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
public class NCLMediaTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLMedia med = new NCLMedia("m1");
        med.setType(NCLMimeType.AUDIO_MP2);
        med.setSrc("audio.mp2");
        med.setRefer(new NCLMedia("m2"));
        med.setInstance(NCLInstanceType.NEW);
        med.setDescriptor(new NCLDescriptor("dm1"));

        String expResult = "<media id='m1' src='audio.mp2' type='audio/mp2' descriptor='dm1' refer='m2' instance='new'/>\n";
        String result = med.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLMedia med = new NCLMedia("m1");
        NCLArea a = new NCLArea("a1");
        NCLProperty p = new NCLProperty("top");
        med.addArea(a);
        med.addProperty(p);

        String expResult = "<media id='m1'>\n\t<area id='a1'/>\n\t<property name='top'/>\n</media>\n";
        String result = med.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia t = new NCLMedia("teste");
            NCLMedia instance = new NCLMedia(reader, t);
            instance.setParent(instance);
            String expResult = "<media id='m1' src='audio.mp2' type='audio/mp2' descriptor='dm1' refer='m2' instance='new'/>\n";

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

    @Test
    public void test4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMedia t = new NCLMedia("teste");
            NCLMedia instance = new NCLMedia(reader, t);
            instance.setParent(instance);
            String expResult = "<media id='m1'>\n\t<area id='a1'/>\n\t<property name='top'/>\n</media>\n";

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