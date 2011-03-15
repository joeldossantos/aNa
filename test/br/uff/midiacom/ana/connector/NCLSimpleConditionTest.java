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


public class NCLSimpleConditionTest {

    @Test
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='onSelection'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='onBegin' key='ENTER'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='meurole'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='meurole' eventType='selection'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='meurole' eventType='selection' transition='starts'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='meurole' eventType='presentation' transition='starts' key='ENTER'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='onBegin' max='1' qualifier='or'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='onBegin' max='unbounded'/>";

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

            NCLSimpleCondition instance = new NCLSimpleCondition(reader, null);
            String xml = "<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>";

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
    public void test_roleset() throws NCLInvalidIdentifierException {
        NCLSimpleCondition scon1, scon2, scon3, scon4;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        scon1 = new NCLSimpleCondition();
        scon1.setRole(new NCLRole("R1"));

        scon2 = new NCLSimpleCondition();
        scon2.setRole(new NCLRole("R2"));

        scon3 = new NCLSimpleCondition();
        scon3.setRole(new NCLRole("R3"));

        scon4 = new NCLSimpleCondition();
        scon4.setRole(new NCLRole("R4"));

        ccon.addCondition(scon1);
        ccon.addCondition(scon2);
        ccon.addCondition(scon3);
        ccon.addCondition(scon4);

        con.setCondition(ccon);

        result &= ccon.hasCondition(scon1);
        result &= ccon.hasCondition(scon2);
        result &= ccon.hasCondition(scon3);
        result &= ccon.hasCondition(scon4);

        assertTrue(result);
    }
}