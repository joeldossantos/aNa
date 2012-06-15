/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.util.TimeType;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLTimeTest {

    static TimeType t1, t2, t3, t4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new TimeType(10);
                t2 = new TimeType(null, null, null, null, null, 10.100);
                t3 = new TimeType(null, null, null, 20, 54, 54.2548);
		t4 = new TimeType(154, 10, 22, 23, 55, 55.1568);
	}

	@Test
	public void testToString_t1() {
		String expResult = "10.0s";
		String result = t1.toString();
		assertEquals(expResult, result);
	}

	@Test
	public void testToString_t2() {
		String expResult = "10.1s";
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
		Double expResult = 10.0;
		Double result = t1.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t2() {
		Double expResult = 10.1;
		Double result = t2.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t3() {
		Double expResult = 54.2548;
		Double result = t3.getSecond();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSecond_t4() {
		Double expResult = 55.1568;
		Double result = t4.getSecond();
		assertEquals(expResult, result);
	}

        @Test
        public void testStringToTime_t1() {
            String expResult = "1:12:23:12:56:2.1";
            TimeType t = new TimeType(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t2() {
            String expResult = "125:56:2.1";
            TimeType t = new TimeType(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t3() {
            String expResult = "200.1s";
            TimeType t = new TimeType(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t4() {
            String expResult = "100.0s";
            TimeType t = new TimeType(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }

        @Test
        public void testStringToTime_t5() {
            String expResult = "1:12:23:12:56:2.0";
            TimeType t = new TimeType(expResult);

            String result = t.toString();
            assertEquals(expResult, result);
        }
}