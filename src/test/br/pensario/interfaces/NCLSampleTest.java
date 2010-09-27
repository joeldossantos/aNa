package test.br.pensario.interfaces;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.pensario.NCLValues.NCLSampleType;
import br.pensario.interfaces.NCLSample;

public class NCLSampleTest {

	static NCLSample sample;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sample = new NCLSample(10, NCLSampleType.F);
	}

	/*@Test
	public void testSetValue_false() {
		boolean expResult = false;
		boolean result = sample.setValue(-1);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetValue_true() {
		boolean expResult = true;
		boolean result = sample.setValue(1000);
		assertEquals(expResult, result);
	}

	@Test
	public void testGetValue() {
		int expResult = 1000;
		sample.setValue(expResult);
		int result = sample.getValue();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetType_false() {
		boolean expResult = false;
		boolean result = sample.setType(null);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetType_true() {
		boolean expResult = true;
		boolean result = sample.setType(NCLSampleType.NPT);
		assertEquals(expResult, result);
	}

	@Test
	public void testGetType() {
		NCLSampleType expResult = NCLSampleType.NPT;
		NCLSampleType result = sample.getType();
		assertEquals(expResult, result);
	}

	@Test
	public void testToString_npt() {
		String expResult = "102npt";
		sample.setValue(102);
		sample.setType(NCLSampleType.NPT);
		String result = sample.toString();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testToString_f() {
		String expResult = "102f";
		sample.setValue(102);
		sample.setType(NCLSampleType.F);
		String result = sample.toString();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testToString_s() {
		String expResult = "102s";
		sample.setValue(102);
		sample.setType(NCLSampleType.S);
		String result = sample.toString();
		assertEquals(expResult, result);
	}*/
}
