/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.node.NCLMedia;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLPortTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLPort port = new NCLPort("pinit");
        port.setComponent(new NCLMedia("med1"));
        port.setInterface(new NCLArea("trac1"));

        String expResult = "<port id='pinit' component='med1' interface='trac1'/>\n";
        String result = port.parse(0);
        assertEquals(expResult, result);
    }
}