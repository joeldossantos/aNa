/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.link;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLParamInstance;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLArea;
import br.pensario.node.NCLMedia;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLBindTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLBind b = new NCLBind();
        b.setRole(new NCLRole("start"));
        b.setComponent(new NCLMedia("video"));
        b.setInterface(new NCLArea("track"));
        b.setDescriptor(new NCLDescriptor("dvideo"));

        String expResult = "<bind role='start' component='video' interface='track' descriptor='dvideo'/>\n";
        String result = b.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException {
        NCLBind b = new NCLBind();
        b.setRole(new NCLRole("set"));
        b.setComponent(new NCLMedia("video"));
        NCLParam p = new NCLParam(NCLParamInstance.BINDPARAM);
        p.setName(new NCLConnectorParam("var"));
        p.setValue("10");
        b.addBindParam(p);

        String expResult = "<bind role='set' component='video'>\n\t<bindParam name='$var' value='10'/>\n</bind>\n";
        String result = b.parse(0);
        assertEquals(expResult, result);
    }
}