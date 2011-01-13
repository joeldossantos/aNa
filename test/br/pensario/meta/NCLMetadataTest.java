/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.meta;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLMetadataTest {

    @Test
    public void test1() {
        NCLMetadata meta = new NCLMetadata();
        meta.setRDFTree("<rdf:RDF ...>\n...\n</rdf:RDF>");

        String expResult = "<metadata>\n<rdf:RDF ...>\n...\n</rdf:RDF>\n</metadata>\n";
        String result = meta.parse(0);
        assertEquals(expResult, result);
    }
}