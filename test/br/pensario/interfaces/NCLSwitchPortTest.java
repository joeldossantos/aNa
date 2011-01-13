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
public class NCLSwitchPortTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLSwitchPort port = new NCLSwitchPort("pinit");
        NCLMapping map = new NCLMapping();
        map.setComponent(new NCLMedia("med1"));
        map.setInterface(new NCLArea("trac1"));
        port.addMapping(map);

        String expResult = "<switchPort id='pinit'>\n\t<mapping component='med1' interface='trac1'/>\n</switchPort>\n";
        String result = port.parse(0);
        assertEquals(expResult, result);
    }
}