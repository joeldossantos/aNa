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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.rule.NCLBindRule;
import br.uff.midiacom.ana.rule.NCLRule;
import br.uff.midiacom.ana.util.exception.XMLException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLDescriptorSwitchTest {

    @Test
    public void test1() throws XMLException {
        NCLDescriptorSwitch ds = new NCLDescriptorSwitch("dLegenda");
        NCLDescriptor desc = new NCLDescriptor("dpt");
            NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(desc);
        ds.addBind(bind);
        ds.addDescriptor(desc);
        ds.setDefaultDescriptor(desc);

        String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule constituent='dpt' rule='rpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";
        String result = ds.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test2() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
//            String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule rule='rpt' constituent='dpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";
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
//            String xml = "<ncl><head><descriptorBase><descriptorSwitch id='dLegenda'>"+
//                "<bindRule rule='rpt' constituent='dpt'/><defaultDescriptor descriptor='dpt'/>"+
//                "<descriptor id='dpt' player='teste'/>"+
//                "</descriptorSwitch></descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) ((NCLDescriptorBindRule) ((NCLDescriptorSwitch) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getBinds().iterator().next()).getConstituent()).getPlayer();
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
//            String xml = "<ncl><head><ruleBase><rule id='rpt' value='teste'/></ruleBase><descriptorBase><descriptorSwitch id='dLegenda'>"+
//                "<bindRule rule='rpt' constituent='dpt'/><defaultDescriptor descriptor='dpt'/>"+
//                "<descriptor id='dpt'/>"+
//                "</descriptorSwitch></descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLRule) ((NCLDescriptorBindRule) ((NCLDescriptorSwitch) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getBinds().iterator().next()).getRule()).getValue();
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
//            String xml = "<ncl><head><descriptorBase><descriptorSwitch id='dLegenda'>"+
//                "<bindRule rule='rpt' constituent='dpt'/><defaultDescriptor descriptor='dpt'/>"+
//                "<descriptor id='dpt' player='teste'/>"+
//                "</descriptorSwitch></descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) ((NCLDescriptorSwitch) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getDefaultDescriptor()).getPlayer();
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