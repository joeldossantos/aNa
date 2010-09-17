package test.br.pensario.interfaces;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.pensario.interfaces.NCLTime;

public class NCLTimeTest {
	
	static NCLTime t1, t2, t3, t4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new NCLTime(10);
		t2 = new NCLTime(10, 100);
		t3 = new NCLTime(20, 54, 54, 2548);
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
	public void testHasYear_t1() {
		boolean expResult = false;
		boolean result = t1.hasYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasYear_t2() {
		boolean expResult = false;
		boolean result = t2.hasYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasYear_t3() {
		boolean expResult = false;
		boolean result = t3.hasYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasYear_t4() {
		boolean expResult = true;
		boolean result = t4.hasYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetYear_t1() {
		int expResult = -1;
		int result = t1.getYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetYear_t2() {
		int expResult = -1;
		int result = t2.getYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetYear_t3() {
		int expResult = -1;
		int result = t3.getYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetYear_t4() {
		int expResult = 154;
		int result = t4.getYear();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMonth_t1() {
		boolean expResult = false;
		boolean result = t1.hasMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMonth_t2() {
		boolean expResult = false;
		boolean result = t2.hasMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMonth_t3() {
		boolean expResult = false;
		boolean result = t3.hasMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMonth_t4() {
		boolean expResult = true;
		boolean result = t4.hasMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMonth_t1() {
		int expResult = -1;
		int result = t1.getMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMonth_t2() {
		int expResult = -1;
		int result = t2.getMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMonth_t3() {
		int expResult = -1;
		int result = t3.getMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMonth_t4() {
		int expResult = 10;
		int result = t4.getMonth();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasDay_t1() {
		boolean expResult = false;
		boolean result = t1.hasDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasDay_t2() {
		boolean expResult = false;
		boolean result = t2.hasDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasDay_t3() {
		boolean expResult = false;
		boolean result = t3.hasDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasDay_t4() {
		boolean expResult = true;
		boolean result = t4.hasDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetDay_t1() {
		int expResult = -1;
		int result = t1.getDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetDay_t2() {
		int expResult = -1;
		int result = t2.getDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetDay_t3() {
		int expResult = -1;
		int result = t3.getDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetDay_t4() {
		int expResult = 22;
		int result = t4.getDay();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasHour_t1() {
		boolean expResult = false;
		boolean result = t1.hasHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasHour_t2() {
		boolean expResult = false;
		boolean result = t2.hasHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasHour_t3() {
		boolean expResult = true;
		boolean result = t3.hasHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasHour_t4() {
		boolean expResult = true;
		boolean result = t4.hasHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetHour_t1() {
		int expResult = -1;
		int result = t1.getHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetHour_t2() {
		int expResult = -1;
		int result = t2.getHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetHour_t3() {
		int expResult = 20;
		int result = t3.getHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetHour_t4() {
		int expResult = 23;
		int result = t4.getHour();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMinute_t1() {
		boolean expResult = false;
		boolean result = t1.hasMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMinute_t2() {
		boolean expResult = false;
		boolean result = t2.hasMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMinute_t3() {
		boolean expResult = true;
		boolean result = t3.hasMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasMinute_t4() {
		boolean expResult = true;
		boolean result = t4.hasMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMinute_t1() {
		int expResult = -1;
		int result = t1.getMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMinute_t2() {
		int expResult = -1;
		int result = t2.getMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMinute_t3() {
		int expResult = 54;
		int result = t3.getMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetMinute_t4() {
		int expResult = 55;
		int result = t4.getMinute();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasSecond_t1() {
		boolean expResult = true;
		boolean result = t1.hasSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasSecond_t2() {
		boolean expResult = true;
		boolean result = t2.hasSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasSecond_t3() {
		boolean expResult = true;
		boolean result = t3.hasSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasSecond_t4() {
		boolean expResult = true;
		boolean result = t4.hasSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetSecond_t1() {
		int expResult = 10;
		int result = t1.getSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetSecond_t2() {
		int expResult = 10;
		int result = t2.getSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetSecond_t3() {
		int expResult = 54;
		int result = t3.getSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetSecond_t4() {
		int expResult = 55;
		int result = t4.getSecond();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasFraction_t1() {
		boolean expResult = false;
		boolean result = t1.hasFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasFraction_t2() {
		boolean expResult = true;
		boolean result = t2.hasFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasFraction_t3() {
		boolean expResult = true;
		boolean result = t3.hasFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testHasFraction_t4() {
		boolean expResult = true;
		boolean result = t4.hasFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetFraction_t1() {
		int expResult = -1;
		int result = t1.getFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetFraction_t2() {
		int expResult = 100;
		int result = t2.getFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetFraction_t3() {
		int expResult = 2548;
		int result = t3.getFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetFraction_t4() {
		int expResult = 1568;
		int result = t4.getFraction();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetYear_false() {
		boolean expResult = false;
		boolean result = t1.setYear(-1);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetYear_true() {
		boolean expResult = true;
		boolean result = t1.setYear(1);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetMonth_false_inf() {
		boolean expResult = false;
		boolean result = t1.setMonth(0);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetMonth_false_sup() {
		boolean expResult = false;
		boolean result = t1.setMonth(20);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetMonth_true() {
		boolean expResult = true;
		boolean result = t1.setMonth(5);
		assertEquals(expResult, result);
	}

	@Test
	public void testSetDay_false_inf() {
		boolean expResult = false;
		boolean result = t1.setDay(0);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetDay_false_sup() {
		boolean expResult = false;
		boolean result = t1.setDay(40);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetDay_true() {
		boolean expResult = true;
		boolean result = t1.setDay(5);
		assertEquals(expResult, result);
	}

	@Test
	public void testSetHour_false_inf() {
		boolean expResult = false;
		boolean result = t1.setHour(-2);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetHour_false_sup() {
		boolean expResult = false;
		boolean result = t1.setHour(40);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetHour_true() {
		boolean expResult = true;
		boolean result = t1.setHour(5);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetHour_true2() throws Exception {
		NCLTime t = new NCLTime(0);
		boolean expResult = true;
		boolean result = t.setHour(100);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetMinute_false_inf() {
		boolean expResult = false;
		boolean result = t1.setMinute(-2);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetMinute_false_sup() {
		boolean expResult = false;
		boolean result = t1.setMinute(70);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetMinute_true() {
		boolean expResult = true;
		boolean result = t1.setMinute(5);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetSecond_false_inf() {
		boolean expResult = false;
		boolean result = t1.setSecond(-2);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetSecond_false_sup() {
		boolean expResult = false;
		boolean result = t1.setSecond(90);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetSecond_true() {
		boolean expResult = true;
		boolean result = t1.setSecond(5);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetFraction_false() {
		boolean expResult = false;
		boolean result = t1.setFraction(-2);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetFraction_true() {
		boolean expResult = true;
		boolean result = t1.setHour(5);
		assertEquals(expResult, result);
	}
}
