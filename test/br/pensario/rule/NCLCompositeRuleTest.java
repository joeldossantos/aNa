/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.rule;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.NCLValues.NCLOperator;
import br.pensario.interfaces.NCLProperty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLCompositeRuleTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLCompositeRule crule = new NCLCompositeRule("crule");
        crule.setOperator(NCLOperator.AND);

        NCLRule rule = new NCLRule("r1");
        rule.setVar(new NCLProperty("legenda"));
        rule.setComparator(NCLComparator.EQ);
        rule.setValue("ligada");

        NCLRule rule2 = new NCLRule("r2");
        rule2.setVar(new NCLProperty("idioma"));
        rule2.setComparator(NCLComparator.EQ);
        rule2.setValue("en");

        crule.addRule(rule);
        crule.addRule(rule2);

        String expResult = "<compositeRule id='crule' operator='and'>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n\t<rule id='r2' var='idioma' comparator='eq' value='en'/>\n</compositeRule>\n";
        String result = crule.parse(0);
        assertEquals(expResult, result);
    }
}