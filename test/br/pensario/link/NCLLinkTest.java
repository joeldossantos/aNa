/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.link;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLParamInstance;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.node.NCLMedia;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author joel
 */
public class NCLLinkTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLLink l = new NCLLink();
        l.setId("l1");
        l.setXconnector(new NCLCausalConnector("onBeginSet"));

        NCLBind b = new NCLBind();
        b.setRole(new NCLRole("set"));
        b.setComponent(new NCLMedia("video"));

        NCLParam p = new NCLParam(NCLParamInstance.LINKPARAM);
        p.setName(new NCLConnectorParam("var"));
        p.setValue("10");

        l.addLinkParam(p);
        l.addBind(b);

        String expResult = "<link id='l1' xconnector='onBeginSet'>\n\t<linkParam name='var' value='10'/>\n\t<bind role='set' component='video'/>\n</link>\n";
        String result = l.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLLink instance = new NCLLink(reader, null);
            String expResult = "<link id='l1' xconnector='onBeginSet'>\n\t<linkParam name='var' value='10'/>\n\t<bind role='set' component='video'/>\n</link>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
            //System.out.println(result);
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
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head>"+
                    "<connectorBase><causalConnector id='ca'>"+
                    "<connectorParam name='teste'/>"+
                    "</causalConnector></connectorBase>"+
                    "</head><body>"+
                    "<link id='l1' xconnector='ca'/>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLConnectorParam param = (NCLConnectorParam) link.getXconnector().getConnectorParams().iterator().next();
            String result = param.getName();
            //System.out.println(result);
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
    public void test4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head>"+
                    "<connectorBase><causalConnector id='ca'>"+
                    "<connectorParam name='pa1' type='teste'/>"+
                    "</causalConnector></connectorBase>"+
                    "</head><body>"+
                    "<link id='l1' xconnector='ca'>"+
                    "<linkParam name='pa1'/>"+
                    "</link>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLParam lparam = (NCLParam) link.getLinkParams().iterator().next();
            String result = lparam.getName().getType();
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}