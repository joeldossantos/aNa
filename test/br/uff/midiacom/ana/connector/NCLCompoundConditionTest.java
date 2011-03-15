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


public class NCLCompoundConditionTest {

    @Test
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCompoundCondition instance = new NCLCompoundCondition(reader, null);
            String xml = "<compoundCondition/>";

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

            NCLCompoundCondition instance = new NCLCompoundCondition(reader, null);
            String xml = "<compoundCondition operator='and'/>";

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

            NCLCompoundCondition instance = new NCLCompoundCondition(reader, null);
            String xml = "<compoundCondition operator='or'>"+
                    "<simpleCondition role='onBegin'/>"+
                    "<simpleCondition role='onEnd'/>"+
                    "</compoundCondition>";

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
        NCLCompoundCondition nclel1, nclel2, nclel3, nclel4;
        NCLSimpleCondition nclela, nclelb, nclelc, ncleld;
        boolean result = true;

        NCLCausalConnector con = new NCLCausalConnector("teste");

        NCLCompoundCondition ccon = new NCLCompoundCondition();

        nclel1 = new NCLCompoundCondition();
        nclela = new NCLSimpleCondition();
        nclela.setRole(new NCLRole("R1"));
        nclel1.addCondition(nclela);

        nclel2 = new NCLCompoundCondition();
        nclelb = new NCLSimpleCondition();
        nclelb.setRole(new NCLRole("R2"));
        nclel2.addCondition(nclelb);

        nclel3 = new NCLCompoundCondition();
        nclelc = new NCLSimpleCondition();
        nclelc.setRole(new NCLRole("R3"));
        nclel3.addCondition(nclelc);

        nclel4 = new NCLCompoundCondition();
        ncleld = new NCLSimpleCondition();
        ncleld.setRole(new NCLRole("R4"));
        nclel4.addCondition(ncleld);

        ccon.addCondition(nclel1);
        ccon.addCondition(nclel2);
        ccon.addCondition(nclel3);
        ccon.addCondition(nclel4);

        con.setCondition(ccon);

        result &= ccon.hasCondition(nclel1);
        result &= ccon.hasCondition(nclel2);
        result &= ccon.hasCondition(nclel3);
        result &= ccon.hasCondition(nclel4);

        assertTrue(result);
    }
}