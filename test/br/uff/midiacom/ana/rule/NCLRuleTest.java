/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.rule;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.interfaces.NCLProperty;
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
public class NCLRuleTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRule rule = new NCLRule("r1");
        rule.setVar(new NCLProperty("legenda"));
        rule.setComparator(NCLComparator.EQ);
        rule.setValue("ligada");

        String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";
        String result = rule.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLRule rule = new NCLRule(reader, null);
            String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";

            reader.setContentHandler(rule);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = rule.parse(0);
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
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc doc = new NCLDoc();
            doc.setReader(reader);
            String xml = "<ncl><head><ruleBase>"+
                "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>"+
                "</ruleBase></head>"+
                "<body><media id='m1'>"+
                "<property name='legenda' value='on'/>"+
                "</media></body></ncl>";

            reader.setContentHandler(doc);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "on";
            String result = ((NCLRule) doc.getHead().getRuleBase().getRules().iterator().next()).getVar().getValue();
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

            NCLRule instance = new NCLRule(reader, null);
            String xml = "<rule id='r1' var='legenda' value='ligada'/>";

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

            NCLRule instance = new NCLRule(reader, null);
            String xml = "<rule id='r1' var='legenda' value='ligada'/>";

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

            NCLRule instance = new NCLRule(reader, null);
            String xml = "<rule var='legenda' comparator='eq' value='ligada'/>";

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

            NCLRule instance = new NCLRule(reader, null);
            String xml = "<rule id='r1' var='legenda' comparator='eq'/>";

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

            NCLRule instance = new NCLRule(reader, null);
            String xml = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>";

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
}