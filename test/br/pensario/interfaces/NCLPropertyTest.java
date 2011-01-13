/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSystemVariable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLPropertyTest {

    static NCLProperty prop;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
            prop = new NCLProperty("a");
    }

    @Test
    public void testParse() {
        try{
            prop.setName("interacao");
            prop.setValue("nao");
            String expResult = "<property name='interacao' value='nao'/>\n";
            String result = prop.parse(0);
            assertEquals(expResult, result);
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void testSetName() {
        try{
            prop.setName((String)null);
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setName("qualquer-coisa");
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
        try{
            prop.setName((NCLSystemVariable)null);
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setName(NCLSystemVariable.SYSTEM_SCREENSIZE);
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void testSetValue() {
        try{
            prop.setValue("");
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setValue("valor");
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }
}