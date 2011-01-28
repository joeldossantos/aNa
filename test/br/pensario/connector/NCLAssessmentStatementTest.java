/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.connector;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
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
}