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
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.connector.NCLSimpleAction;
import br.uff.midiacom.ana.util.enums.NCLAttributes;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
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


public class NCLLinkTest {

    @Test
    public void test1() throws XMLException {
        NCLSimpleAction a = new NCLSimpleAction();
        a.setRole("set");
        
        NCLLink l = new NCLLink();
        l.setId("l1");
        l.setXconnector(new NCLCausalConnector("onBeginSet"));

        NCLBind b = new NCLBind();
        b.setRole(a);
        b.setComponent(new NCLMedia("video"));

        NCLLinkParam p = new NCLLinkParam();
        p.setName(new NCLConnectorParam("var"));
        p.setValue("10");

        l.addLinkParam(p);
        l.addBind(b);

        String expResult = "<link id='l1' xconnector='onBeginSet'>\n\t<linkParam name='var' value='10'/>\n\t<bind role='set' component='video'/>\n</link>\n";
        String result = l.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test2() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLLink instance = new NCLLink(reader, null);
//            String expResult = "<link id='l1' xconnector='onBeginSet'>\n\t<linkParam name='var' value='10'/>\n\t<bind role='set' component='video'/>\n</link>\n";
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
//    public void test3() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><head>"+
//                    "<connectorBase><causalConnector id='ca'>"+
//                    "<connectorParam name='teste'/>"+
//                    "</causalConnector></connectorBase>"+
//                    "</head><body>"+
//                    "<link id='l1' xconnector='ca'/>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLConnectorParam param = (NCLConnectorParam) link.getXconnector().getConnectorParams().iterator().next();
//            String result = param.getName();
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
//            String xml = "<ncl><head>"+
//                    "<connectorBase><causalConnector id='ca'>"+
//                    "<connectorParam name='pa1' type='teste'/>"+
//                    "</causalConnector></connectorBase>"+
//                    "</head><body>"+
//                    "<link id='l1' xconnector='ca'>"+
//                    "<linkParam name='pa1'/>"+
//                    "</link>"+
//                    "</body></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            NCLParam lparam = (NCLParam) link.getLinkParams().iterator().next();
//            String result = lparam.getName().getType();
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