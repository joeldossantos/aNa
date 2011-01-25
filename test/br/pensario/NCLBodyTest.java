/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario;

import br.pensario.interfaces.NCLPort;
import br.pensario.meta.NCLMeta;
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
public class NCLBodyTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLBody bd = new NCLBody();
        bd.setId("bod");

        NCLMedia m1 = new NCLMedia("video");
        NCLPort p1 = new NCLPort("pInicio");
        p1.setComponent(m1);
        
        NCLMeta m = new NCLMeta();
        m.setName("autor");
        m.setContent("joel");
        bd.addMeta(m);

        bd.addNode(m1);
        bd.addPort(p1);

        String expResult = "<body id='bod'>\n\t<meta name='autor' content='joel'/>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</body>\n";
        String result = bd.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLBody instance = new NCLBody(reader, null);
            String expResult = "<body id='bod'>\n\t<meta name='autor' content='joel'/>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</body>\n";

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