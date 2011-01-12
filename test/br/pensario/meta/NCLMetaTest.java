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
public class NCLMetaTest {

    @Test
    public void test1() {
        NCLMeta meta = new NCLMeta();
        meta.setName("autor");
        meta.setContent("joel");

        String expResult = "<meta name='autor' content='joel'/>\n";
        String result = meta.parse(0);
        assertEquals(expResult, result);
    }
}