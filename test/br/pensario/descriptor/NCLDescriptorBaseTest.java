/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.descriptor;

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
public class NCLDescriptorBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLDescriptorBase base = new NCLDescriptorBase();
        base.setId("db");

        String expResult = "<descriptorBase id='db'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLDescriptorBase base = new NCLDescriptorBase();
        NCLDescriptor desc = new NCLDescriptor("dTV");
        NCLImport imp = new NCLImport(NCLImportType.BASE);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportBase(imp);
        base.addDescriptor(desc);

        String expResult = "<descriptorBase>\n\t<importBase alias='base' documentURI='base.ncl'/>\n\t<descriptor id='dTV'/>\n</descriptorBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }
}