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

import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.xml.XMLException;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;


public class NCLCompositeRuleTest {

    @Test
    public void test1() throws XMLException {
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

//    @Test
//    public void test2() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCompositeRule rule = new NCLCompositeRule(reader, null);
//            String expResult = "<compositeRule id='crule' operator='and'>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n\t<rule id='r2' var='idioma' comparator='eq' value='en'/>\n</compositeRule>\n";
//
//            reader.setContentHandler(rule);
//            reader.parse(new InputSource(new StringReader(expResult)));
//
//            String result = rule.parse(0);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
}