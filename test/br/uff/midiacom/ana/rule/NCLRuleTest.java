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
package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.util.enums.NCLComparator;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLRuleTest {

    @Test
    public void test1() throws XMLException {
        NCLRule rule = new NCLRule("r1");
        rule.setVar(new NCLVariable("legenda"));
        rule.setComparator(NCLComparator.EQ);
        rule.setValue("ligada");

        String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";
        String result = rule.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test2() throws XMLException {
//        String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";
//
//        XMLLoader loader = new XMLLoader(expResult);
//        NCLRule instance = new NCLRule();
//        instance.load(loader.getElement());
//
//        String result = instance.parse(0);
//        assertEquals(expResult, result);
//    }

//    @Test
//    public void test3() throws XMLException {
//        String xml = "<ncl><head><ruleBase>"+
//                "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>"+
//                "</ruleBase></head>"+
//                "<body><media id='m1'>"+
//                "<property name='legenda' value='on'/>"+
//                "</media></body></ncl>";
//
//        XMLLoader loader = new XMLLoader(xml);
//        NCLDoc instance = new NCLDoc();
//        instance.load(loader.getElement());
//
//        String expResult = "on";
//        String result = ((NCLRule) instance.getHead().getRuleBase().getRules().get("r1")).getVar().getValue();
//        assertEquals(expResult, result);
//    }
}