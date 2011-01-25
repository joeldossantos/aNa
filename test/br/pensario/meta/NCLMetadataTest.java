/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.meta;

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
public class NCLMetadataTest {

    @Test
    public void test1() {
        NCLMetadata meta = new NCLMetadata();
        meta.setRDFTree("<rdf:RDF ...>\n...\n</rdf:RDF>");

        String expResult = "<metadata>\n<rdf:RDF ...>\n...\n</rdf:RDF>\n</metadata>\n";
        String result = meta.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMetadata meta = new NCLMetadata(reader, null);
            String expResult = "<metadata>\nrdftree\n</metadata>\n";

            reader.setContentHandler(meta);
            reader.parse(new InputSource(new StringReader("<metadata>rdftree</metadata>")));

            String result = meta.parse(0);
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