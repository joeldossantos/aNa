/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLTimeTest {

    static NCLTime t1, t2, t3, t4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new NCLTime(10);
                t2 = new NCLTime(null, null, null, null, null, 10, 100);
                t3 = new NCLTime(null, null, null, 20, 54, 54, 2548);
		t4 = new NCLTime(154, 10, 22, 23, 55, 55, 1568);
	}

	@Test
	public void testToString_t1() {
		String expResult = "10s";
		String result = t1.toString();
		assertEquals(expResult, result);
	}

	@Test
	public void testToString_t2() {
		String expResult = "10.100s";
		String result = t2.toString();
		assertEquals(expResult, result);
	}

	@Test
	public void testToString_t3() {
		String expResult = "20:54:54.2548";
		String result = t3.toString();
		assertEquals(expResult, result);
	}

	@Test
	public void testToString_t4() {
		String expResult = "154:10:22:23:55:55.1568";
		String result = t4.toString();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetYear_t1() {
		Integer result = t1.getYear();
		assertNull(result);
	}

	@Test
	public void testGetYear_t2() {
		Integer result = t2.getYear();
		assertNull(result);
	}

	@Test
	public void testGetYear_t3() {
		Integer result = t3.getYear();
		assertNull(result);
	}

	@Test
	public void testGetYear_t4() {
		Integer expResult = 154;
		Integer result = t4.getYear();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetMonth_t1() {
		Integer result = t1.getMonth();
		assertNull(result);
	}

	@Test
	public void testGetMonth_t2() {
		Integer result = t2.getMonth();
		assertNull(result);
	}

	@Test
	public void testGetMonth_t3() {
		Integer result = t3.getMonth();
		assertNull(result);
	}

	@Test
	public void testGetMonth_t4() {
		Integer expResult = 10;
		Integer result = t4.getMonth();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetDay_t1() {
		Integer result = t1.getDay();
		assertNull(result);
	}

	@Test
	public void testGetDay_t2() {
		Integer result = t2.getDay();
		assertNull(result);
	}

	@Test
	public void testGetDay_t3() {
		Integer result = t3.getDay();
		assertNull(result);
	}

	@Test
	public void testGetDay_t4() {
		Integer expResult = 22;
		Integer result = t4.getDay();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetHour_t1() {
		Integer result = t1.getHour();
		assertNull(result);
	}

	@Test
	public void testGetHour_t2() {
		Integer result = t2.getHour();
		assertNull(result);
	}

	@Test
	public void testGetHour_t3() {
		Integer expResult = 20;
		Integer result = t3.getHour();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetHour_t4() {
		Integer expResult = 23;
		Integer result = t4.getHour();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetMinute_t1() {
		Integer result = t1.getMinute();
		assertNull(result);
	}

	@Test
	public void testGetMinute_t2() {
		Integer result = t2.getMinute();
		assertNull(result);
	}

	@Test
	public void testGetMinute_t3() {
		Integer expResult = 54;
		Integer result = t3.getMinute();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetMinute_t4() {
		Integer expResult = 55;
		Integer result = t4.getMinute();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t1() {
		Integer expResult = 10;
		Integer result = t1.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t2() {
		Integer expResult = 10;
		Integer result = t2.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t3() {
		Integer expResult = 54;
		Integer result = t3.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t4() {
		Integer expResult = 55;
		Integer result = t4.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetFraction_t1() {
		Integer result = t1.getFraction();
		assertNull(result);
	}

	@Test
	public void testGetFraction_t2() {
		Integer expResult = 100;
		Integer result = t2.getFraction();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetFraction_t3() {
		Integer expResult = 2548;
		Integer result = t3.getFraction();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetFraction_t4() {
		Integer expResult = 1568;
		Integer result = t4.getFraction();
		assertEquals(expResult, result);
	}

        @Test
        public void testStringToTime_t1() {
            String expResult = "1:12:23:12:56:2.100";
            NCLTime t = new NCLTime(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t2() {
            String expResult = "125:56:2.100";
            NCLTime t = new NCLTime(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t3() {
            String expResult = "200.100s";
            NCLTime t = new NCLTime(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t4() {
            String expResult = "100s";
            NCLTime t = new NCLTime(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t5() {
            String expResult = "1:12:23:12:56:2";
            NCLTime t = new NCLTime(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }
}