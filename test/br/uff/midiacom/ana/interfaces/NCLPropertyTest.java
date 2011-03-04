/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSystemVariable;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author joel
 */
public class NCLPropertyTest {

    static NCLProperty prop;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
            prop = new NCLProperty("a");
    }

    @Test
    public void testParse() {
        try{
            prop.setName("interacao");
            prop.setValue("nao");
            String expResult = "<property name='interacao' value='nao'/>\n";
            String result = prop.parse(0);
            assertEquals(expResult, result);
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void testSetName() {
        try{
            prop.setName((String)null);
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setName("qualquer-coisa");
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
        try{
            prop.setName((NCLSystemVariable)null);
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setName(NCLSystemVariable.SYSTEM_SCREENSIZE);
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void testSetValue() {
        try{
            prop.setValue("");
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setValue("valor");
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLProperty instance = new NCLProperty(reader, null);
            String expResult = "<property name='interacao' value='nao'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLProperty instance = new NCLProperty(reader, null);
            String xml = "<property/>";

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

            NCLProperty instance = new NCLProperty(reader, null);
            String xml = "<property name='interacao' value='nao'/>";

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