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
package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.connector.NCLSimpleAction;
import br.uff.midiacom.ana.connector.NCLSimpleCondition;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.interfaces.NCLArea;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.util.exception.XMLException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLBindTest {

    @Test
    public void test1() throws XMLException {
        NCLSimpleAction a = new NCLSimpleAction();
        a.setRole("start");
        
        NCLBind b = new NCLBind();
        b.setRole(a);
        b.setComponent(new NCLMedia("video"));
        b.setInterface(new NCLArea("track"));
        b.setDescriptor(new NCLDescriptor("dvideo"));

        String expResult = "<bind role='start' component='video' interface='track' descriptor='dvideo'/>\n";
        String result = b.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        NCLSimpleAction a = new NCLSimpleAction();
        a.setRole("set");
        
        NCLBind b = new NCLBind();
        b.setRole(a);
        b.setComponent(new NCLMedia("video"));
        NCLBindParam p = new NCLBindParam();
        p.setName(new NCLConnectorParam("var"));
        p.setValue("10");
        b.addBindParam(p);

        String expResult = "<bind role='set' component='video'>\n\t<bindParam name='var' value='10'/>\n</bind>\n";
        String result = b.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test3() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLBind instance = new NCLBind(reader, null);
//            String expResult = "<bind role='start' component='video' interface='track' descriptor='dvideo'/>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(expResult)));
//
//            String result = instance.parse(0);
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
//            NCLBind instance = new NCLBind(reader, null);
//            String expResult = "<bind role='set' component='video'>\n\t<bindParam name='var' value='10'/>\n</bind>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(expResult)));
//
//            String result = instance.parse(0);
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
//            String xml = "<ncl><head>"+
//                    "<connectorBase><causalConnector id='ca'>"+
//                    "<simpleCondition role='pa1' min='1'/>"+
//                    "</causalConnector></connectorBase>"+
//                    "</head><body>"+
//                    "<link id='l1' xconnector='ca'>"+
//                    "<bind role='pa1'/>"+
//                    "</link>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
//            NCLSimpleCondition cond = (NCLSimpleCondition) link.getXconnector().getCondition();
//            NCLRole expResult = cond.getRole();
//            NCLRole result = bind.getRole();
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
//            String xml = "<ncl><head>"+
//                    "<descriptorBase><descriptor id='dpa' player='teste'/></descriptorBase>"+
//                    "</head><body>"+
//                    "<link id='l1' xconnector='ca'>"+
//                    "<bind descriptor='dpa'/>"+
//                    "</link>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
//            String result = ((NCLDescriptor) bind.getDescriptor()).getPlayer();
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
//    public void test7() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><body>"+
//                    "<link id='l1' xconnector='ca'>"+
//                    "<bind component='m1'/>"+
//                    "</link>"+
//                    "<media id='m1' src='media.png'/>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
//
//            String expResult = "media.png";
//            String result = ((NCLMedia) bind.getComponent()).getSrc();
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
//    public void test8() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><body>"+
//                    "<link id='l1' xconnector='ca'>"+
//                    "<bind component='m1' interface='a1'/>"+
//                    "</link>"+
//                    "<media id='m1'><area id='a1' label='teste'/></media>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
//
//            String expResult = "teste";
//            String result = ((NCLArea) bind.getInterface()).getLabel();
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
//    public void test9() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><head>"+
//                    "<connectorBase><causalConnector id='ca'>"+
//                    "<connectorParam name='pa1' type='teste'/>"+
//                    "</causalConnector></connectorBase>"+
//                    "</head><body>"+
//                    "<link id='l1' xconnector='ca'>"+
//                    "<bind><bindParam name='pa1'/></bind>"+
//                    "</link>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
//            NCLParam param = (NCLParam) bind.getBindParams().iterator().next();
//
//            String expResult = "teste";
//            String result = param.getName().getType();
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

    @Test
    public void test_roleset() throws XMLException {
        NCLSimpleAction nclsa1, nclsa2, nclsa3, nclsa4;
        NCLBind nclel1, nclel2, nclel3, nclel4;
        boolean result = true;
        
        nclsa1 = new NCLSimpleAction();
        nclsa1.setRole("R1");
        
        nclsa2 = new NCLSimpleAction();
        nclsa2.setRole("R2");
        
        nclsa3 = new NCLSimpleAction();
        nclsa3.setRole("R3");
        
        nclsa4 = new NCLSimpleAction();
        nclsa4.setRole("R4");

        NCLLink con = new NCLLink();

        nclel1 = new NCLBind();
        nclel1.setRole(nclsa1);

        nclel2 = new NCLBind();
        nclel2.setRole(nclsa2);

        nclel3 = new NCLBind();
        nclel3.setRole(nclsa3);

        nclel4 = new NCLBind();
        nclel4.setRole(nclsa4);

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