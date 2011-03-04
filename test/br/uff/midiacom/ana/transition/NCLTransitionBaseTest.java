package br.uff.midiacom.ana.transition;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLImportType;
import br.uff.midiacom.ana.NCLValues.NCLTransitionType;
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


public class NCLTransitionBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLTransitionBase base = new NCLTransitionBase();
        base.setId("tb");

        String expResult = "<transitionBase id='tb'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLTransitionBase base = new NCLTransitionBase();
        NCLTransition trans = new NCLTransition("tr1");
        trans.setType(NCLTransitionType.FADE);
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportBase(imp);
        base.addTransition(trans);

        String expResult = "<transitionBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<transition id='tr1' type='fade'/>\n</transitionBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransitionBase instance = new NCLTransitionBase(reader, null);
            String expResult = "<transitionBase id='tb'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
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

            NCLTransitionBase instance = new NCLTransitionBase(reader, null);
            String expResult = "<transitionBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<transition id='tr1' type='fade'/>\n</transitionBase>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
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

            NCLTransitionBase instance = new NCLTransitionBase(reader, null);
            String xml = "<transitionBase id='tb'/>";

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

            NCLTransitionBase instance = new NCLTransitionBase(reader, null);
            String xml = "<transitionBase><importNCL alias='base' documentURI='base.ncl'/></transitionBase>";

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

            NCLTransitionBase instance = new NCLTransitionBase(reader, null);
            String xml = "<transitionBase><importBase alias='base' documentURI='base.ncl'/><transition id='tr1' type='fade'/></transitionBase>";

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