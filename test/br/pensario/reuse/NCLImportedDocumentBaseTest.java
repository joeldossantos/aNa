/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.reuse;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLImportedDocumentBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLImportedDocumentBase base = new NCLImportedDocumentBase();
        base.setId("IDb");

        String expResult = "<importedDocumentBase id='IDb'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLImportedDocumentBase base = new NCLImportedDocumentBase();
        NCLImport imp = new NCLImport(NCLImportType.NCL);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportNCL(imp);

        String expResult = "<importedDocumentBase>\n\t<importNCL alias='base' documentURI='base.ncl'/>\n</importedDocumentBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }
}