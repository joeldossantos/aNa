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
import br.uff.midiacom.ana.util.SrcType;
import br.uff.midiacom.ana.util.TimeType;
import br.uff.midiacom.ana.util.enums.NCLAttributes;
import br.uff.midiacom.ana.util.enums.NCLColor;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.ana.util.PercentageType;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLDescriptorTest {

    @Test
    public void test1() throws XMLException {
        NCLDescriptor descriptor = new NCLDescriptor("dTV");
        descriptor.setRegion(new NCLRegion("rgTV"));
        descriptor.setExplicitDur(new TimeType(20));
        descriptor.setFreeze(true);
        descriptor.setPlayer("teste");
            NCLDescriptor d1 = new NCLDescriptor("d1");
            d1.setFocusIndex(1);
            NCLDescriptor d2 = new NCLDescriptor("d2");
            d2.setFocusIndex(2);
            NCLDescriptor d3 = new NCLDescriptor("d3");
            d3.setFocusIndex(3);
            NCLDescriptor d4 = new NCLDescriptor("d4");
            d4.setFocusIndex(4);
        descriptor.setMoveLeft(d1);
        descriptor.setMoveRight(d2);
        descriptor.setMoveDown(d3);
        descriptor.setMoveUp(d4);
        descriptor.setFocusIndex(10);
        descriptor.setFocusBorderColor(NCLColor.BLACK);
        descriptor.setFocusBorderWidth(5);
        descriptor.setFocusBorderTransparency(new PercentageType(1, true));
        descriptor.setFocusSrc(new SrcType("foco.jpg"));
        descriptor.setFocusSelSrc(new SrcType("sel.jpg"));
        descriptor.setSelBorderColor(NCLColor.AQUA);
        descriptor.setTransIn(new NCLTransition("tin"));
        descriptor.setTransOut(new NCLTransition("tout"));

        String expResult = "<descriptor id='dTV' region='rgTV' explicitDur='20.0s' freeze='true' player='teste' moveLeft='1' moveRight='2' moveDown='3' moveUp='4'"+
                " focusIndex='10' focusBorderColor='black' focusBorderWidth='5' focusBorderTransparency='1.0%' focusSrc='foco.jpg'"+
                " focusSelSrc='sel.jpg' SelBorderColor='aqua' transIn='tin' transOut='tout'/>\n";
        String result = descriptor.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws XMLException {
        NCLDescriptor descriptor = new NCLDescriptor("dTV");
        NCLDescriptorParam param = new NCLDescriptorParam();
        param.setName(NCLAttributes.TOP);
        param.setValue(100);
        descriptor.addDescriptorParam(param);

        String expResult = "<descriptor id='dTV'>\n\t<descriptorParam name='top' value='100'/>\n</descriptor>\n";
        String result = descriptor.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test3() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDescriptor instance = new NCLDescriptor(reader, null);
//            String expResult = "<descriptor id='dTV' region='rgTV' explicitDur='20s' freeze='true' player='teste' moveLeft='1' moveRight='2' moveDown='3' moveUp='4'"+
//                " focusIndex='10' focusBorderColor='black' focusBorderWidth='5' focusBorderTransparency='1%' focusSrc='foco.jpg'"+
//                " focusSelSrc='sel.jpg' SelBorderColor='aqua' transIn='tin' transOut='tout'/>\n";
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
//            NCLDescriptor instance = new NCLDescriptor(reader, null);
//            String expResult = "<descriptor id='dTV'>\n\t<descriptorParam name='top' value='100'/>\n</descriptor>\n";
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
//                    "<regionBase><region id='rgt' title='teste'/></regionBase>"+
//                    "<descriptorBase>"+
//                    "<descriptor id='dpt' region='rgt'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getRegion().getTitle();
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
//                    "<transitionBase><transition id='trans' startProgress='0.5'/></transitionBase>"+
//                    "<descriptorBase>"+
//                    "<descriptor id='dpt' transIn='trans'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            Double expResult = 0.5;
//            Double result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getTransIn().getStartProgress();
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
//            String xml = "<ncl><head>"+
//                    "<transitionBase><transition id='trans' startProgress='0.5'/></transitionBase>"+
//                    "<descriptorBase>"+
//                    "<descriptor id='dpt' transOut='trans'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            Double expResult = 0.5;
//            Double result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getTransOut().getStartProgress();
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
//            String xml = "<ncl><head><descriptorBase>"+
//                    "<descriptor id='dpa' moveLeft='1'/>"+
//                    "<descriptor id='dpz' focusIndex='1' player='teste'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getMoveLeft().getPlayer();
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
//            String xml = "<ncl><head><descriptorBase>"+
//                    "<descriptor id='dpa' moveRight='1'/>"+
//                    "<descriptor id='dpz' focusIndex='1' player='teste'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getMoveRight().getPlayer();
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
//    public void test10() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><head><descriptorBase>"+
//                    "<descriptor id='dpa' moveUp='1'/>"+
//                    "<descriptor id='dpz' focusIndex='1' player='teste'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getMoveUp().getPlayer();
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
//    public void test11() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLDoc instance = new NCLDoc();
//            instance.setReader(reader);
//            String xml = "<ncl><head><descriptorBase>"+
//                    "<descriptor id='dpa' moveDown='1'/>"+
//                    "<descriptor id='dpz' focusIndex='1' player='teste'/>"+
//                    "</descriptorBase></head></ncl>";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//            String result = ((NCLDescriptor) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getMoveDown().getPlayer();
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
