/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.util.exception.XMLException;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLCompoundStatementTest {

    @Test
    public void test_roleset() throws XMLException, Exception {
        NCLCompoundStatement nclel1, nclel2, nclel3, nclel4;
        NCLAssessmentStatement nclela, nclelb, nclelc, ncleld;
        NCLAttributeAssessment nclelaa, nclelbb, nclelcc, ncleldd;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        nclel1 = new NCLCompoundStatement();
        nclela = new NCLAssessmentStatement();
        nclelaa = new NCLAttributeAssessment();
        nclelaa.setRole("R1");
        nclela.addAttributeAssessment(nclelaa);
        nclel1.addStatement(nclela);

        nclel2 = new NCLCompoundStatement();
        nclelb = new NCLAssessmentStatement();
        nclelbb = new NCLAttributeAssessment();
        nclelbb.setRole("R2");
        nclelb.addAttributeAssessment(nclelbb);
        nclel2.addStatement(nclelb);

        nclel3 = new NCLCompoundStatement();
        nclelc = new NCLAssessmentStatement();
        nclelcc = new NCLAttributeAssessment();
        nclelcc.setRole("R3");
        nclelc.addAttributeAssessment(nclelcc);
        nclel3.addStatement(nclelc);

        nclel4 = new NCLCompoundStatement();
        ncleld = new NCLAssessmentStatement();
        ncleldd = new NCLAttributeAssessment();
        ncleldd.setRole("R4");
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