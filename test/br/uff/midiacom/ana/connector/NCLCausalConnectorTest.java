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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.datatype.auxiliar.AssValueParamType;
import br.uff.midiacom.ana.datatype.auxiliar.ByParamType;
import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.auxiliar.IntegerParamType;
import br.uff.midiacom.ana.datatype.auxiliar.KeyParamType;
import br.uff.midiacom.ana.datatype.auxiliar.StringParamType;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLAttributeType;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLDefaultActionRole;
import br.uff.midiacom.ana.datatype.enums.NCLEventAction;
import br.uff.midiacom.ana.datatype.enums.NCLEventTransition;
import br.uff.midiacom.ana.datatype.enums.NCLEventType;
import br.uff.midiacom.ana.datatype.enums.NCLKey;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;


public class NCLCausalConnectorTest {

    @Test
    public void test1() throws XMLException {
        NCLCausalConnector instance = new NCLCausalConnector("conn1");

        //Condicoes
        NCLSimpleCondition simplecondition = new NCLSimpleCondition();
        simplecondition.setRole(new NCLRole("startSel"));
        simplecondition.setDelay(new DoubleParamType(6.0));
        simplecondition.setEventType(NCLEventType.SELECTION);
        simplecondition.setTransition(NCLEventTransition.STARTS);
        simplecondition.setKey(new KeyParamType(NCLKey.KEY_A));
        simplecondition.setMin(1);
        simplecondition.setMax(new MaxType(2));
        simplecondition.setQualifier(NCLConditionOperator.OR);

        NCLAssessmentStatement assessmentstatement = new NCLAssessmentStatement();
        assessmentstatement.setComparator(NCLComparator.EQ);
        NCLValueAssessment valueassessment = new NCLValueAssessment(new AssValueParamType("1"));
        NCLAttributeAssessment attributeassessment = new NCLAttributeAssessment();
        attributeassessment.setRole(new NCLRole("testeOccurences"));
        attributeassessment.setEventType(NCLEventType.SELECTION);
        attributeassessment.setKey(new KeyParamType(NCLKey.ENTER));
        attributeassessment.setAttributeType(NCLAttributeType.OCCURRENCES);
        attributeassessment.setOffset(new IntegerParamType(2));
        assessmentstatement.setValueAssessment(valueassessment);
        assessmentstatement.addAttributeAssessment(attributeassessment);

        NCLCompoundCondition compoundcondition = new NCLCompoundCondition();
        compoundcondition.setDelay(new DoubleParamType(5.0));
        compoundcondition.setOperator(NCLConditionOperator.AND);
        compoundcondition.addCondition(simplecondition);
        compoundcondition.addStatement(assessmentstatement);

        //Acoes
        NCLSimpleAction simpleaction1 = new NCLSimpleAction();
        simpleaction1.setRole(new NCLRole("seta2x"));
        simpleaction1.setDelay(new DoubleParamType(0.0));
        simpleaction1.setEventType(NCLEventType.ATTRIBUTION);
        simpleaction1.setActionType(NCLEventAction.START);
        simpleaction1.setValue(new StringParamType("value0"));
        simpleaction1.setMin(1);
        simpleaction1.setMax(new MaxType(2));
        simpleaction1.setQualifier(NCLActionOperator.PAR);
        simpleaction1.setRepeat(new IntegerParamType(2));
        simpleaction1.setRepeatDelay(new DoubleParamType(2.0));
        simpleaction1.setDuration(new DoubleParamType(20.0));
        simpleaction1.setBy(new ByParamType("2"));

        NCLSimpleAction simpleaction2 = new NCLSimpleAction();
        simpleaction2.setRole(new NCLRole(NCLDefaultActionRole.STOP));

        NCLCompoundAction compoundaction = new NCLCompoundAction();
        compoundaction.setDelay(new DoubleParamType(1.0));
        compoundaction.setOperator(NCLActionOperator.SEQ);
        compoundaction.addAction(simpleaction1);
        compoundaction.addAction(simpleaction2);

        //Termina o conector
        NCLConnectorParam connectorparam = new NCLConnectorParam("val");
        connectorparam.setType("Integer");
        instance.addConnectorParam(connectorparam);
        instance.setCondition(compoundcondition);
        instance.setAction(compoundaction);

        String expResult = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='Integer'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";
        String result = instance.parse(0);
        assertEquals(expResult, result);
    }

//    @Test
//    public void test2() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='Integer'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            //NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
//            //NCLBind bind = (NCLBind) link.getBinds().iterator().next();
//            //NCLSimpleCondition cond = (NCLSimpleCondition) link.getXconnector().getCondition();
//            String expResult = xml;
//            String result = instance.parse(0);
//            //System.out.println(result);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test3a() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='$val'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='$val'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundCondition cond = (NCLCompoundCondition) instance.getCondition();
//
//            String expResult = "teste";
//            String result = cond.getParamDelay().getType();
//            //System.out.println(result);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    public void test3b() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='$val'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='$val'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = "teste";
//
//            NCLCompoundAction act = (NCLCompoundAction) instance.getAction();
//
//            String result = act.getParamDelay().getType();
//            //System.out.println(result);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4a() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
//            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();
//
//            expResult = "teste";
//
//            result = sact.getParamDelay().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4b() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
//            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();
//
//            expResult = "teste";
//
//            result = sact.getParamValue().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4c() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
//            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();
//
//            expResult = "teste";
//
//            result = sact.getParamRepeat().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4d() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
//            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();
//
//            expResult = "teste";
//
//            result = sact.getParamRepeatDelay().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4e() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
//            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();
//
//            expResult = "teste";
//
//            result = sact.getParamDuration().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test4f() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
//                "\t\t\t<valueAssessment value='1'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
//            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();
//
//            expResult = "teste";
//
//            result = sact.getParamBy().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test5a() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
//                "\t\t\t<valueAssessment value='$val'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
//            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
//            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
//            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();
//
//            expResult = "teste";
//
//            result = scond.getParamDelay().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test5b() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
//                "\t\t\t<valueAssessment value='$val'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
//            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
//            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
//            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();
//
//            expResult = "teste";
//
//            result = scond.getParamKey().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test5c() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
//                "\t\t\t<valueAssessment value='$val'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
//            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
//            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
//            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();
//
//            expResult = "teste";
//
//            result = att.getParamKey().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test5d() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
//                "\t\t\t<valueAssessment value='$val'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
//            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
//            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
//            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();
//
//            expResult = "teste";
//
//            result = att.getParamOffset().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test5e() {
//        String expResult, result;
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='5s'>\n"+
//                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
//                "\t\t\t<valueAssessment value='$val'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='1s'>\n"+
//                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
//            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
//            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
//            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();
//
//            expResult = "teste";
//
//            result = ass.getValueAssessment().getParamValue().getType();
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void test6() {
//        try{
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//
//            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
//            String xml = "<causalConnector id='conn1'>\n"+
//                "\t<connectorParam name='val' type='teste'/>\n"+
//                "\t<compoundCondition operator='and' delay='$val'>\n"+
//                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
//                "\t\t<assessmentStatement comparator='eq'>\n"+
//                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
//                "\t\t\t<valueAssessment value='$val'/>\n"+
//                "\t\t</assessmentStatement>\n"+
//                "\t</compoundCondition>\n"+
//                "\t<compoundAction operator='seq' delay='$val'>\n"+
//                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
//                "\t\t<simpleAction role='stop'/>\n"+
//                "\t</compoundAction>\n"+
//                "</causalConnector>\n";
//
//            reader.setContentHandler(instance);
//            reader.parse(new InputSource(new StringReader(xml)));
//
//            String expResult = xml;
//            String result = instance.parse(0);
//            assertEquals(expResult, result);
//        }
//        catch(SAXException ex){
//            fail(ex.getMessage());
//        }
//        catch(IOException ex){
//            fail(ex.getMessage());
//        }
//    }
}