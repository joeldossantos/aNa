/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.transition;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.NCLValues.NCLTransitionType;
import br.pensario.reuse.NCLImport;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
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
}