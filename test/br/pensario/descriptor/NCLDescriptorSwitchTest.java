/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.descriptor;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.rule.NCLRule;
import br.pensario.rule.NCLTestRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLDescriptorSwitchTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLDescriptorSwitch ds = new NCLDescriptorSwitch("dLegenda");
        NCLDescriptor desc = new NCLDescriptor("dpt");
            NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(desc);
        ds.addBind(bind);
        ds.addDescriptor(desc);
        ds.setDefaultDescriptor(desc);

        String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule rule='rpt' constituent='dpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";
        String result = ds.parse(0);
        assertEquals(expResult, result);
    }
}