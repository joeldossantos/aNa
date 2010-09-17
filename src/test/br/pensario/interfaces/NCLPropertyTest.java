package test.br.pensario.interfaces;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.pensario.NCLValues.NCLSystemVariable;
import br.pensario.interfaces.NCLProperty;

public class NCLPropertyTest {

	static NCLProperty prop;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		prop = new NCLProperty("");
	}

	@Test
	public void testParse() {
		prop.setName("interacao");
		prop.setValue("nao");
		String expResult = "<property name='interacao' value='nao'/>\n";
		String result = prop.parse(0);
		assertEquals(expResult, result);
	}

	@Test
	public void testSetName_null() {
		assertEquals(false, prop.setName((String)null));
	}
	
	@Test
	public void testSetName_correto() {
		assertEquals(true, prop.setName("qualquer-coisa"));
	}
	
	@Test
	public void testSetName_enum_null() {
		assertEquals(false, prop.setName((NCLSystemVariable)null));
	}
	
	@Test
	public void testSetName_enum_correto() {
		assertEquals(true, prop.setName(NCLSystemVariable.SYSTEM_SCREENSIZE));
	}
	
	@Test
	public void testGetName_string() {
		String expResult = "nome.para.teste";
		prop.setName(expResult);
        String result = prop.getName();
        assertEquals(expResult, result);
	}
	
	@Test
	public void testGetName_enum() {
		String expResult = NCLSystemVariable.CHANNEL_KEYBOARDBOUNDS.toString();
		prop.setName(NCLSystemVariable.CHANNEL_KEYBOARDBOUNDS);
        String result = prop.getName();
        assertEquals(expResult, result);
	}

	@Test
	public void testSetValue_null() {
		assertEquals(false, prop.setValue(null));
	}
	
	@Test
	public void testSetValue_correto() {
		assertEquals(true, prop.setValue("valor"));
	}
	

	@Test
	public void testGetValue() {
		assertEquals("valor", prop.getValue());
	}

	
	@Test
	public void testHasValue_false() throws Exception {
		NCLProperty p = new NCLProperty("foo");
		assertEquals(false, p.hasValue());
	}
	
	@Test
	public void testHasValue_true() throws Exception {
		NCLProperty p = new NCLProperty("foo");
		p.setValue("foo");
		assertEquals(true, p.hasValue());
	}
}
