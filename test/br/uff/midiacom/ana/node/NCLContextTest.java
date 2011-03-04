package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLPort;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLContextTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLContext cont = new NCLContext("ctx");
        NCLMedia m1 = new NCLMedia("video");
        NCLPort p1 = new NCLPort("pInicio");
        p1.setComponent(m1);

        cont.addNode(m1);
        cont.addPort(p1);

        String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";
        String result = cont.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLContext instance = new NCLContext(reader, null);
            String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";

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
            String xml = "<ncl><body>"+
                    "<context id='da' refer='db'/>"+
                    "<context id='db'>"+
                    "<media id='m1'/>"+
                    "</context></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "m1";
            String result = ((NCLMedia) ((NCLContext) instance.getBody().getNodes().iterator().next()).getRefer().getNodes().iterator().next()).getId();
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

            NCLContext instance = new NCLContext(reader, null);
            String xml = "<context/>";

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

            NCLContext instance = new NCLContext(reader, null);
            String xml = "<context id='dLegenda' refer='dLegenda'>"+
                    "<media id='dpt'/>"+
                    "</context>";

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

            NCLContext instance = new NCLContext(reader, null);
            String xml = "<context id='dLegenda' refer='interno'>"+
                    "<media id='dpt'/>"+
                    "<context id='interno'>"+
                    "<media id='dpa'/>"+
                    "</context>"+
                    "</context>";

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

            NCLContext instance = new NCLContext(reader, null);
            String xml = "<context id='dLegenda'>"+
                    "<media id='dpt'/>"+
                    "</context>";

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