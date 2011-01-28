/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.transition;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLColor;
import br.pensario.NCLValues.NCLTransitionDirection;
import br.pensario.NCLValues.NCLTransitionSubtype;
import br.pensario.NCLValues.NCLTransitionType;
import br.pensario.interfaces.NCLTime;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLTransitionTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLTransition trans = new NCLTransition("tr1");
        trans.setType(NCLTransitionType.FADE);
        trans.setSubtype(NCLTransitionSubtype.CROSSFADE);
        trans.setDirection(NCLTransitionDirection.FORWARD);
        trans.setDur(new NCLTime(5));
        trans.setStartProgress(0.1);
        trans.setEndProgress(0.9);
        trans.setFadeColor(NCLColor.BLACK);
        trans.setHorRepeat(4);
        trans.setVertRepeat(6);
        trans.setBorderWidth(20);
        trans.setBorderColor(NCLColor.BLUE);

        String expResult = "<transition id='tr1' type='fade' subtype='crossfade' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";
        String result = trans.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String expResult = "<transition id='tr1' type='fade' subtype='crossfade' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String xml = "<transition type='fade' subtype='crossfade' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));
            
            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String xml = "<transition id='tr1' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String xml = "<transition id='tr1' subtype='crossfade' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String xml = "<transition id='tr1' type='barWipe' subtype='crossfade' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String xml = "<transition id='tr1' type='barWipe' subtype='leftToRight' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' fadeColor='black' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLTransition instance = new NCLTransition(reader, null);
            String xml = "<transition id='tr1' type='barWipe' subtype='leftToRight' dur='5s' startProgress='0.1' endProgress='0.9'"+
                " direction='forward' horRepeat='4' vertRepeat='6' borderWidth='20' borderColor='blue'/>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            boolean result = instance.validate();

            for(String msg : instance.getWarnings())
                System.out.println(msg);
            for(String msg : instance.getErrors())
                System.out.println(msg);

            assertTrue(result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}