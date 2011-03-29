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

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;


public class NCLAssessmentStatementTest {

    @Test
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement/>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'/>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<valueAssessment value='aa'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }



    @Test
    public void test_validacao4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>"+
                    "<valueAssessment/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao7() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences'/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao8() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='selection'/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao9() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='nodeProperty'/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao10() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='presentation' key='ENTER'/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao11() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='presentation' attributeType='nodeProperty'/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao12() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                    "<attributeAssessment role='testeOccurences' eventType='attribution' key='ENTER'/>"+
                    "<valueAssessment value='abc'/>"+
                    "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLAssessmentStatement instance = new NCLAssessmentStatement(reader, null);
            String xml = "<assessmentStatement comparator='eq'>"+
                "<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences'/>"+
                "<valueAssessment value='abc'/>"+
                "</assessmentStatement>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            boolean result = instance.validate();

            for(String msg : instance.getWarnings())
                System.out.println(msg);
            for(String msg : instance.getErrors())
                System.out.println(msg);

            assertTrue(result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_roleset() throws NCLInvalidIdentifierException, Exception {
        NCLAssessmentStatement nclel1, nclel2, nclel3, nclel4;
        NCLAttributeAssessment nclela, nclelb, nclelc, ncleld;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        nclel1 = new NCLAssessmentStatement();
        nclela = new NCLAttributeAssessment();
        nclela.setRole(new NCLRole("R1"));
        nclel1.addAttributeAssessment(nclela);

        nclel2 = new NCLAssessmentStatement();
        nclelb = new NCLAttributeAssessment();
        nclelb.setRole(new NCLRole("R2"));
        nclel2.addAttributeAssessment(nclelb);

        nclel3 = new NCLAssessmentStatement();
        nclelc = new NCLAttributeAssessment();
        nclelc.setRole(new NCLRole("R3"));
        nclel3.addAttributeAssessment(nclelc);

        nclel4 = new NCLAssessmentStatement();
        ncleld = new NCLAttributeAssessment();
        ncleld.setRole(new NCLRole("R4"));
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