/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario;

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
public class NCLDocTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLDoc d = new NCLDoc();
        d.setXmlns(NCLValues.NCLNamespace.EDTV);
        d.setId("meudoc");
        d.setTitle("documento de teste");
        d.setHead(new NCLHead());
        d.setBody(new NCLBody());

        String expResult = "<?xml version='1.0' encoding='ISO-8859-1'?>\n<!-- Generated with NCL API -->\n\n"+
                "<ncl id='meudoc' title='documento de teste' xmlns='http://www.ncl.org.br/NCL3.0/profiles/NCL30EDTV.xsd'>\n\t"+
                "<head>\n\t</head>\n\t<body>\n\t</body>\n</ncl>\n";
        String result = d.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String expResult = "<?xml version='1.0' encoding='ISO-8859-1'?>\n<!-- Generated with NCL API -->\n\n"+
                "<ncl id='meudoc' title='documento de teste' xmlns='http://www.ncl.org.br/NCL3.0/profiles/NCL30EDTV.xsd'>\n\t"+
                "<head>\n\t</head>\n\t<body>\n\t</body>\n</ncl>\n";

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
}