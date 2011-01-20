/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.rule;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.interfaces.NCLProperty;
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
public class NCLRuleTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRule rule = new NCLRule("r1");
        rule.setVar(new NCLProperty("legenda"));
        rule.setComparator(NCLComparator.EQ);
        rule.setValue("ligada");

        String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";
        String result = rule.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLRule r = new NCLRule("teste");
            NCLRule rule = new NCLRule(reader, r);
            rule.setParent(rule);
            String expResult = "<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n";

            reader.setContentHandler(rule);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = rule.parse(0);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(NCLInvalidIdentifierException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}