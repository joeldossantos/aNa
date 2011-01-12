/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.region;

import br.pensario.NCLInvalidIdentifierException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLRegionTest {

    @Test
    public void testRegion1() throws NCLInvalidIdentifierException {
        NCLRegion region = new NCLRegion("rgTV");
        region.setLeft(10, true);
        region.setRight(20, true);
        region.setTop(10, true);
        region.setBottom(20, true);
        region.setWidth(80, true);
        region.setHeight(80, true);
        region.setzIndex(1);
        region.setTitle("Titulo de Teste");

        String expResult = "<region id='rgTV' left='10%' right='20%' top='10%' bottom='20%' height='80%' width='80%' zIndex='1' title='Titulo de Teste'/>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegion2() throws NCLInvalidIdentifierException {
        NCLRegion region = new NCLRegion("rgTV");
        region.setLeft(10, false);
        region.setRight(20, false);
        region.setTop(10, false);
        region.setBottom(20, false);
        region.setWidth(80, false);
        region.setHeight(80, false);
        region.setzIndex(1);
        region.setTitle("Titulo de Teste");

        String expResult = "<region id='rgTV' left='10' right='20' top='10' bottom='20' height='80' width='80' zIndex='1' title='Titulo de Teste'/>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegion3() throws NCLInvalidIdentifierException {
        NCLRegion region = new NCLRegion("rgTV");
        NCLRegion region2 = new NCLRegion("rg1");
        region.addRegion(region2);

        String expResult = "<region id='rgTV'>\n\t<region id='rg1'/>\n</region>\n";
        String result = region.parse(0);
        assertEquals(expResult, result);
    }
}