/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario;

import br.pensario.interfaces.NCLPort;
import br.pensario.interfaces.NCLProperty;
import br.pensario.link.NCLLink;
import br.pensario.meta.NCLMeta;
import br.pensario.meta.NCLMetadata;
import br.pensario.node.NCLMedia;
import br.pensario.node.NCLNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}