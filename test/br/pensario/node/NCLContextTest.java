/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLArea;
import br.pensario.interfaces.NCLPort;
import br.pensario.interfaces.NCLTime;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLContextTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLContext cont = new NCLContext("ctx");
        NCLMedia m1 = new NCLMedia("video");
        NCLPort p1 = new NCLPort("pInicio");
        p1.setComponent(m1);

        cont.addNode(m1);
        cont.addPort(p1);

        String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";
        String result = cont.parse(0);
        assertEquals(expResult, result);
    }
}