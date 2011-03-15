package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.rule.NCLRule;
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
    public void test1() throws NCLInvalidIdentifierException {
        NCLDescriptorSwitch ds = new NCLDescriptorSwitch("dLegenda");
        NCLDescriptor desc = new NCLDescriptor("dpt");
            NCLBindRule bind = new NCLBindRule();
            bind.setRule(new NCLRule("rpt"));
            bind.setConstituent(desc);
        ds.addBind(bind);
        ds.addDescriptor(desc);
        ds.setDefaultDescriptor(desc);

        String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule rule='rpt' constituent='dpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";
        String result = ds.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String expResult = "<descriptorSwitch id='dLegenda'>\n\t<bindRule rule='rpt' constituent='dpt'/>\n\t<defaultDescriptor descriptor='dpt'/>\n\t<descriptor id='dpt'/>\n</descriptorSwitch>\n";

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
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head><descriptorBase><descriptorSwitch id='dLegenda'>"+
                "<bindRule rule='rpt' constituent='dpt'/><defaultDescriptor descriptor='dpt'/>"+
                "<descriptor id='dpt' player='teste'/>"+
                "</descriptorSwitch></descriptorBase></head></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLBindRule) ((NCLDescriptorSwitch) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getBinds().iterator().next()).getConstituent().getPlayer();
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
    public void test4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head><ruleBase><rule id='rpt' value='teste'/></ruleBase><descriptorBase><descriptorSwitch id='dLegenda'>"+
                "<bindRule rule='rpt' constituent='dpt'/><defaultDescriptor descriptor='dpt'/>"+
                "<descriptor id='dpt'/>"+
                "</descriptorSwitch></descriptorBase></head></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLRule) ((NCLBindRule) ((NCLDescriptorSwitch) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getBinds().iterator().next()).getRule()).getValue();
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
    public void test5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head><descriptorBase><descriptorSwitch id='dLegenda'>"+
                "<bindRule rule='rpt' constituent='dpt'/><defaultDescriptor descriptor='dpt'/>"+
                "<descriptor id='dpt' player='teste'/>"+
                "</descriptorSwitch></descriptorBase></head></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLDescriptorSwitch) instance.getHead().getDescriptorBase().getDescriptors().iterator().next()).getDefaultDescriptor().getPlayer();
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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch/>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<defaultDescriptor descriptor='dpt'/>"+
                    "<descriptor id='dpt' player='teste'/>"+
                    "</descriptorSwitch>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<bindRule rule='rpt' constituent='dpt'/>"+
                    "<defaultDescriptor descriptor='dpt'/>"+
                    "</descriptorSwitch>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<bindRule constituent='dpt'/>"+
                    "<defaultDescriptor descriptor='dpt'/>"+
                    "<descriptor id='dpt' player='teste'/>"+
                    "</descriptorSwitch>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<bindRule rule='rpt'/>"+
                    "<defaultDescriptor descriptor='dpt'/>"+
                    "<descriptor id='dpt' player='teste'/>"+
                    "</descriptorSwitch>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<bindRule rule='rpt' constituent='dpa'/>"+
                    "<defaultDescriptor descriptor='dpt'/>"+
                    "<descriptor id='dpt' player='teste'/>"+
                    "</descriptorSwitch>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<bindRule rule='rpt' constituent='dpt'/>"+
                    "<defaultDescriptor descriptor='dpb'/>"+
                    "<descriptor id='dpt' player='teste'/>"+
                    "</descriptorSwitch>";

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

            NCLDescriptorSwitch instance = new NCLDescriptorSwitch(reader, null);
            String xml = "<descriptorSwitch id='dLegenda'>"+
                    "<bindRule rule='rpt' constituent='dpt'/>"+
                    "<defaultDescriptor descriptor='dpt'/>"+
                    "<descriptor id='dpt' player='teste'/>"+
                    "</descriptorSwitch>";

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