/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.region;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
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
public class NCLRegionBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLRegionBase base = new NCLRegionBase();
        base.setId("rgb");
        base.setDevice("systemScreen(0)");

        String expResult = "<regionBase id='rgb' device='systemScreen(0)'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLRegionBase base = new NCLRegionBase();
        NCLRegion region = new NCLRegion("rgTV");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        imp.setRegion(region);
        base.addImportBase(imp);
        base.addRegion(region);

        String expResult = "<regionBase>\n\t<importBase alias='base' documentURI='base.ncl' region='rgTV'/>\n\t<region id='rgTV'/>\n</regionBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }
}