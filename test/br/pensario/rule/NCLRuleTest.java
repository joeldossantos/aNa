/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.rule;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.interfaces.NCLProperty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLRuleTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRule rule = new NCLRule("r1");
        rule.setVar(new NCLProperty("legenda"));
        rule.setComparator(NCLComparator.EQ);
        rule.setValue("ligada");

        String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";
        String result = rule.parse(0);
        assertEquals(expResult, result);
    }
}