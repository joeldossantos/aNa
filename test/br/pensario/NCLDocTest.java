/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLDocTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLDoc d = new NCLDoc();
        d.setXmlns(NCLValues.NCLNamespace.EDTV);
        d.setId("meudoc");
        d.setTitle("documento de teste");
        d.setHead(new NCLHead());
        d.setBody(new NCLBody());

        String expResult = "<?xml version='1.0' encoding='ISO-8859-1'?>\n<!-- Generated with NCL API -->\n\n"+
                "<ncl id='meudoc' title='documento de teste' xmlns='http://www.ncl.org.br/NCL3.0/profiles/NCL30EDTV.xsd'>\n\t"+
                "<head>\n\t</head>\n\t<body>\n\t</body>\n</ncl>\n";
        String result = d.parse(0);
        assertEquals(expResult, result);
    }
}