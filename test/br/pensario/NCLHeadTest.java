/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario;

import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.meta.NCLMeta;
import br.pensario.meta.NCLMetadata;
import br.pensario.region.NCLRegionBase;
import br.pensario.reuse.NCLImportedDocumentBase;
import br.pensario.rule.NCLRuleBase;
import br.pensario.transition.NCLTransitionBase;
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
public class NCLHeadTest {

    @Test
    public void test1() {
        NCLHead h = new NCLHead();
        h.setImportedDocumentBase(new NCLImportedDocumentBase());
        h.setRuleBase(new NCLRuleBase());
        h.setTransitionBase(new NCLTransitionBase());
        h.setRegionBase(new NCLRegionBase());
        h.setDescriptorBase(new NCLDescriptorBase());
        h.setConnectorBase(new NCLConnectorBase());

        NCLMeta m = new NCLMeta();
        m.setName("autor");
        m.setContent("joel");

        NCLMetadata mt = new NCLMetadata();
        mt.setRDFTree("arvore rdf");

        h.addMeta(m);
        h.addMetadata(mt);

        String expResult = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>\narvore rdf\n\t</metadata>\n</head>\n";
        String result = h.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLHead instance = new NCLHead();
            instance.setReader(reader);
            instance.setParent(instance);
            String expResult = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>\narvore rdf\n\t</metadata>\n</head>\n";

            reader.setContentHandler(instance);
            String xml = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>arvore rdf</metadata>\n</head>\n";
            reader.parse(new InputSource(new StringReader(xml)));

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