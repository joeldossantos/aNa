/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.rule.NCLRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}