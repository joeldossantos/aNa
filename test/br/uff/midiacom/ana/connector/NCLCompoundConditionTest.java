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

import br.uff.midiacom.xml.XMLException;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLCompoundConditionTest {

    @Test
    public void test_roleset() throws XMLException {
        NCLCompoundCondition nclel1, nclel2, nclel3, nclel4;
        NCLSimpleCondition nclela, nclelb, nclelc, ncleld;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        nclel1 = new NCLCompoundCondition();
        nclela = new NCLSimpleCondition();
        nclela.setRole(new NCLRole("R1"));
        nclel1.addCondition(nclela);

        nclel2 = new NCLCompoundCondition();
        nclelb = new NCLSimpleCondition();
        nclelb.setRole(new NCLRole("R2"));
        nclel2.addCondition(nclelb);

        nclel3 = new NCLCompoundCondition();
        nclelc = new NCLSimpleCondition();
        nclelc.setRole(new NCLRole("R3"));
        nclel3.addCondition(nclelc);

        nclel4 = new NCLCompoundCondition();
        ncleld = new NCLSimpleCondition();
        ncleld.setRole(new NCLRole("R4"));
        nclel4.addCondition(ncleld);

        ccon.addCondition(nclel1);
        ccon.addCondition(nclel2);
        ccon.addCondition(nclel3);
        ccon.addCondition(nclel4);

        con.setCondition(ccon);

        result &= ccon.hasCondition(nclel1);
        result &= ccon.hasCondition(nclel2);
        result &= ccon.hasCondition(nclel3);
        result &= ccon.hasCondition(nclel4);

        assertTrue(result);
    }
}