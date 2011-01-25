/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.rule;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.NCLValues.NCLOperator;
import br.pensario.interfaces.NCLProperty;
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
public class NCLCompositeRuleTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLCompositeRule crule = new NCLCompositeRule("crule");
        crule.setOperator(NCLOperator.AND);

        NCLRule rule = new NCLRule("r1");
        rule.setVar(new NCLProperty("legenda"));
        rule.setComparator(NCLComparator.EQ);
        rule.setValue("ligada");

        NCLRule rule2 = new NCLRule("r2");
        rule2.setVar(new NCLProperty("idioma"));
        rule2.setComparator(NCLComparator.EQ);
        rule2.setValue("en");

        crule.addRule(rule);
        crule.addRule(rule2);

        String expResult = "<compositeRule id='crule' operator='and'>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n\t<rule id='r2' var='idioma' comparator='eq' value='en'/>\n</compositeRule>\n";
        String result = crule.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCompositeRule rule = new NCLCompositeRule(reader, null);
            String expResult = "<compositeRule id='crule' operator='and'>\n\t<rule id='r1' var='legenda' comparator='eq' value='ligada'/>\n\t<rule id='r2' var='idioma' comparator='eq' value='en'/>\n</compositeRule>\n";

            reader.setContentHandler(rule);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = rule.parse(0);
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