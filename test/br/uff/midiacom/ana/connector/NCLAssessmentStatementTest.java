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

import org.junit.Test;
import static org.junit.Assert.*;


public class NCLAssessmentStatementTest {

    @Test
    public void test_roleset() throws Exception {
        NCLAssessmentStatement nclel1, nclel2, nclel3, nclel4;
        NCLAttributeAssessment nclela, nclelb, nclelc, ncleld;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        nclel1 = new NCLAssessmentStatement();
        nclela = new NCLAttributeAssessment();
        nclela.setRole("R1");
        nclel1.addAttributeAssessment(nclela);

        nclel2 = new NCLAssessmentStatement();
        nclelb = new NCLAttributeAssessment();
        nclelb.setRole("R2");
        nclel2.addAttributeAssessment(nclelb);

        nclel3 = new NCLAssessmentStatement();
        nclelc = new NCLAttributeAssessment();
        nclelc.setRole("R3");
        nclel3.addAttributeAssessment(nclelc);

        nclel4 = new NCLAssessmentStatement();
        ncleld = new NCLAttributeAssessment();
        ncleld.setRole("R4");
        nclel4.addAttributeAssessment(ncleld);

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