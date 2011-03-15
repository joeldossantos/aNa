package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLComparator;
import br.uff.midiacom.ana.NCLValues.NCLImportType;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;


public class NCLRuleBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRuleBase base = new NCLRuleBase();
        base.setId("rb");

        String expResult = "<ruleBase id='rb'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLRuleBase base = new NCLRuleBase();
        NCLRule rule = new NCLRule("r1");
            rule.setVar(new NCLProperty("legenda"));
            rule.setComparator(NCLComparator.EQ);
            rule.setValue("ligada");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportBase(imp);
        base.addRule(rule);

        String expResult = "<ruleBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n</ruleBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLRuleBase base = new NCLRuleBase(reader, null);
            String expResult = "<ruleBase id='rb'/>\n";

            reader.setContentHandler(base);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = base.parse(0);
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

            NCLRuleBase base = new NCLRuleBase(reader, null);
            String expResult = "<ruleBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n</ruleBase>\n";

            reader.setContentHandler(base);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = base.parse(0);
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

            NCLRuleBase instance = new NCLRuleBase(reader, null);
            String xml = "<ruleBase id='rb'/>";

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

            NCLRuleBase instance = new NCLRuleBase(reader, null);
            String xml = "<ruleBase><importNCL alias='base' documentURI='base.ncl'/></ruleBase>";

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

            NCLRuleBase instance = new NCLRuleBase(reader, null);
            String xml = "<ruleBase><importBase alias='base' documentURI='base.ncl'/></ruleBase>";

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