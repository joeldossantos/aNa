/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.rule;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.interfaces.NCLProperty;
import br.pensario.reuse.NCLImport;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLRuleBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRuleBase base = new NCLRuleBase();
        base.setId("rb");

        String expResult = "<ruleBase id='rb'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLRuleBase base = new NCLRuleBase();
        NCLRule rule = new NCLRule("r1");
            rule.setVar(new NCLProperty("legenda"));
            rule.setComparator(NCLComparator.EQ);
            rule.setValue("ligada");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportBase(imp);
        base.addRule(rule);

        String expResult = "<ruleBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n</ruleBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }
}