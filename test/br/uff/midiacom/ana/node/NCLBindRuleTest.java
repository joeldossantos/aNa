package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.rule.NCLRule;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLBindRuleTest {

    @Test
    public void test_roleset() throws NCLInvalidIdentifierException, Exception {
        NCLBindRule nclel1, nclel2, nclel3, nclel4;
        boolean result = true;

        NCLSwitch con = new NCLSwitch("teste");

        nclel1 = new NCLBindRule();
        nclel1.setRule(new NCLRule("R1"));

        nclel2 = new NCLBindRule();
        nclel2.setRule(new NCLRule("R2"));

        nclel3 = new NCLBindRule();
        nclel3.setRule(new NCLRule("R3"));

        nclel4 = new NCLBindRule();
        nclel4.setRule(new NCLRule("R4"));

        con.addBind(nclel1);
        con.addBind(nclel2);
        con.addBind(nclel3);
        con.addBind(nclel4);

        result &= con.hasBind(nclel1);
        result &= con.hasBind(nclel2);
        result &= con.hasBind(nclel3);
        result &= con.hasBind(nclel4);

        assertTrue(result);
    }
}