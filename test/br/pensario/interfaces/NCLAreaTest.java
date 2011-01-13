/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLSampleType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLAreaTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setBegin(new NCLTime(5));
        area.setEnd(new NCLTime(20));

        String expResult = "<area id='anchor' begin='5s' end='20s'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setFirst(new NCLSample(5,NCLSampleType.F));
        area.setLast(new NCLSample(20,NCLSampleType.F));

        String expResult = "<area id='anchor' first='5f' last='20f'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        int[] cord = {1, 2, 6, 56};
        area.setCoords(cord);
        
        String expResult = "<area id='anchor' coords='1,2,6,56'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test4() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setLabel("bla");

        String expResult = "<area id='anchor' label='bla'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test5() throws NCLInvalidIdentifierException {
        NCLArea area = new NCLArea("anchor");
        area.setText("texto");
        area.setPosition(2);

        String expResult = "<area id='anchor' text='texto' position='2'/>\n";
        String result = area.parse(0);
        assertEquals(expResult, result);
    }
}