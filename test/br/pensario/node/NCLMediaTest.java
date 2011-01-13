/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLInstanceType;
import br.pensario.NCLValues.NCLMimeType;
import br.pensario.NCLValues.NCLSystemVariable;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLArea;
import br.pensario.interfaces.NCLProperty;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}