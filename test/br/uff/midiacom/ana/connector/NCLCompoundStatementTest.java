package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLCompoundStatementTest {

    @Test
    public void test_roleset() throws NCLInvalidIdentifierException, Exception {
        NCLCompoundStatement nclel1, nclel2, nclel3, nclel4;
        NCLAssessmentStatement nclela, nclelb, nclelc, ncleld;
        NCLAttributeAssessment nclelaa, nclelbb, nclelcc, ncleldd;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        nclel1 = new NCLCompoundStatement();
        nclela = new NCLAssessmentStatement();
        nclelaa = new NCLAttributeAssessment();
        nclelaa.setRole(new NCLRole("R1"));
        nclela.addAttributeAssessment(nclelaa);
        nclel1.addStatement(nclela);

        nclel2 = new NCLCompoundStatement();
        nclelb = new NCLAssessmentStatement();
        nclelbb = new NCLAttributeAssessment();
        nclelbb.setRole(new NCLRole("R2"));
        nclelb.addAttributeAssessment(nclelbb);
        nclel2.addStatement(nclelb);

        nclel3 = new NCLCompoundStatement();
        nclelc = new NCLAssessmentStatement();
        nclelcc = new NCLAttributeAssessment();
        nclelcc.setRole(new NCLRole("R3"));
        nclelc.addAttributeAssessment(nclelcc);
        nclel3.addStatement(nclelc);

        nclel4 = new NCLCompoundStatement();
        ncleld = new NCLAssessmentStatement();
        ncleldd = new NCLAttributeAssessment();
        ncleldd.setRole(new NCLRole("R4"));
        ncleld.addAttributeAssessment(ncleldd);
        nclel4.addStatement(ncleld);

        ccon.addStatement(nclel1);
        ccon.addStatement(nclel2);
        ccon.addStatement(nclel3);
        ccon.addStatement(nclel4);

        con.setCondition(ccon);

        result &= ccon.hasStatement(nclel1);
        result &= ccon.hasStatement(nclel2);
        result &= ccon.hasStatement(nclel3);
        result &= ccon.hasStatement(nclel4);

        assertTrue(result);
    }
}