/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.reuse;

import br.pensario.NCLDoc;
import br.pensario.region.NCLRegion;
import java.io.IOException;
import java.io.StringReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
public class NCLImportTest {

    @Test
    public void test1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head><regionBase>"+
                    "<importBase alias='base' documentURI='base.ncl' region='rgTV'/>"+
                    "<region id='rgTV' title='teste'/>"+
                    "</regionBase></head></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            String result = ((NCLImport) instance.getHead().getRegionBase().getImportBases().iterator().next()).getRegion().getTitle();
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