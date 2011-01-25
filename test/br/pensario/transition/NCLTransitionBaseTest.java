/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.transition;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.NCLValues.NCLTransitionType;
import br.pensario.reuse.NCLImport;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
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
}