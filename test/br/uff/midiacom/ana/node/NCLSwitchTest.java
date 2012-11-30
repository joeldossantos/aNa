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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.rule.NCLBindRule;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.interfaces.NCLMapping;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.ana.rule.NCLRule;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLSwitchTest {

    @Test
    public void test1() throws XMLException {
        NCLSwitch s = new NCLSwitch("escolhe");
        NCLMedia med = new NCLMedia("m1");
        NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(med);
        s.addBind(bind);
        s.addNode(med);
        s.setDefaultComponent(med);
        
        String expResult = "<switch id='escolhe'>\n\t<bindRule constituent='m1' rule='rpt'/>\n\t<defaultComponent component='m1'/>\n\t<media id='m1'/>\n</switch>\n";
        String result = s.parse(0);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2() throws XMLException {
        NCLDoc doc = new NCLDoc();
        
        NCLSwitch s = new NCLSwitch("escolhe");
        s.setDoc(doc);
        
        NCLContext ctx = new NCLContext("m1");
        NCLPort p = new NCLPort("p1");
        ctx.addPort(p);
        s.addNode(ctx);
        
        NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(ctx);
        s.addBind(bind);
        
        NCLSwitchPort sp = new NCLSwitchPort("sp");
        NCLMapping map = new NCLMapping();
        map.setComponent(ctx);
        map.setInterface(p);
        sp.addMapping(map);
        s.addPort(sp);
        
        System.out.println(s.parse(0));
    }
    
    @Test
    public void test3() throws XMLException {
        NCLDoc doc = new NCLDoc();
        
        NCLSwitch s = new NCLSwitch("escolhe");
        s.setDoc(doc);
        
        NCLSwitch ctx = new NCLSwitch("m1");
        NCLSwitchPort p = new NCLSwitchPort("p1");
        ctx.addPort(p);
        s.addNode(ctx);
        
        NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(ctx);
        s.addBind(bind);
        
        NCLSwitchPort sp = new NCLSwitchPort("sp");
        NCLMapping map = new NCLMapping();
        map.setComponent(ctx);
        map.setInterface(p);
        sp.addMapping(map);
        s.addPort(sp);
        
        System.out.println(doc + "---" + ctx.getDoc());
        System.out.println(s.parse(0));
    }

//    @Test
//    public void test2() throws XMLException {
//        String expResult = "<switch id='escolhe'>\n\t<bindRule rule='rpt' constituent='m1'/>\n\t<defaultComponent component='m1'/>\n\t<media id='m1'/>\n\t<switch id='s1'/>\n</switch>\n";
//
//        XMLLoader loader = new XMLLoader(expResult);
//        NCLSwitch instance = new NCLSwitch();
//        instance.load(loader.getElement());
//
//        String result = instance.parse(0);
//        assertEquals(expResult, result);
//    }

//    @Test
//    public void test3() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><body><switch id='dLegenda'>"+
//                "<bindRule rule='rpt' constituent='m1'/>"+
//                "<media id='m1' src='media.png'/>"+
//                "</switch></body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "media.png";
//            String result = ((NCLMedia) ((NCLSwitchBindRule) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getBinds().iterator().next()).getConstituent()).getSrc();
//            //System.out.println(result);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><head><ruleBase><rule id='rpt' value='teste'/></ruleBase></head><body>"+
//                "<switch id='dLegenda'>"+
//                "<bindRule rule='rpt' constituent='m1'/>"+
//                "<media id='m1' src='media.png'/>"+
//                "</switch></body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLRule) ((NCLSwitchBindRule) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getBinds().iterator().next()).getRule()).getValue();
//            //System.out.println(result);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test5() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><body><switch id='dLegenda'>"+
//                "<bindRule rule='rpt' constituent='m1'/><defaultComponent component='m1'/>"+
//                "<media id='m1' src='media.png'/>"+
//                "</switch></body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "media.png";
//            String result = ((NCLMedia) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getDefaultComponent()).getSrc();
//            //System.out.println(result);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test6() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><body>"+
//                    "<switch id='da' refer='db'/>"+
//                    "<switch id='db'>"+
//                    "<media id='m1'/>"+
//                    "</switch></body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "m1";
//            String result = ((NCLMedia) ((NCLSwitch) instance.getBody().getNodes().iterator().next()).getRefer().getNodes().iterator().next()).getId();
//            //System.out.println(result);
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