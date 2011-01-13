/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.link;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLParamInstance;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.node.NCLMedia;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLLinkTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLLink l = new NCLLink();
        l.setId("l1");
        l.setXconnector(new NCLCausalConnector("onBeginSet"));

        NCLBind b = new NCLBind();
        b.setRole(new NCLRole("set"));
        b.setComponent(new NCLMedia("video"));

        NCLParam p = new NCLParam(NCLParamInstance.LINKPARAM);
        p.setName(new NCLConnectorParam("var"));
        p.setValue("10");

        l.addLinkParam(p);
        l.addBind(b);

        String expResult = "<link id='l1' xconnector='onBeginSet'>\n\t<linkParam name='$var' value='10'/>\n\t<bind role='set' component='video'/>\n</link>\n";
        String result = l.parse(0);
        assertEquals(expResult, result);
    }
}