package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLActionOperator;
import br.uff.midiacom.ana.NCLValues.NCLAttributeType;
import br.uff.midiacom.ana.NCLValues.NCLComparator;
import br.uff.midiacom.ana.NCLValues.NCLConditionOperator;
import br.uff.midiacom.ana.NCLValues.NCLDefaultActionRole;
import br.uff.midiacom.ana.NCLValues.NCLEventAction;
import br.uff.midiacom.ana.NCLValues.NCLEventTransition;
import br.uff.midiacom.ana.NCLValues.NCLEventType;
import br.uff.midiacom.ana.NCLValues.NCLKey;
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
    public void test1() throws NCLInvalidIdentifierException, Exception {
        NCLCausalConnector instance = new NCLCausalConnector("conn1");

        //Condicoes
        NCLSimpleCondition simplecondition = new NCLSimpleCondition();
        simplecondition.setRole(new NCLRole("startSel"));
        simplecondition.setDelay(6);
        simplecondition.setEventType(NCLEventType.SELECTION);
        simplecondition.setTransition(NCLEventTransition.STARTS);
        simplecondition.setKey(NCLKey.KEY_A);
        simplecondition.setMin(1);
        simplecondition.setMax(2);
        simplecondition.setQualifier(NCLConditionOperator.OR);

        NCLAssessmentStatement assessmentstatement = new NCLAssessmentStatement();
        assessmentstatement.setComparator(NCLComparator.EQ);
        NCLValueAssessment valueassessment = new NCLValueAssessment("1");
        NCLAttributeAssessment attributeassessment = new NCLAttributeAssessment();
        attributeassessment.setRole(new NCLRole("testeOccurences"));
        attributeassessment.setEventType(NCLEventType.SELECTION);
        attributeassessment.setKey(NCLKey.ENTER);
        attributeassessment.setAttributeType(NCLAttributeType.OCCURRENCES);
        attributeassessment.setOffset(2);
        assessmentstatement.setValueAssessment(valueassessment);
        assessmentstatement.addAttributeAssessment(attributeassessment);

        NCLCompoundCondition compoundcondition = new NCLCompoundCondition();
        compoundcondition.setDelay(5);
        compoundcondition.setOperator(NCLConditionOperator.AND);
        compoundcondition.addCondition(simplecondition);
        compoundcondition.addStatement(assessmentstatement);

        //Acoes
        NCLSimpleAction simpleaction1 = new NCLSimpleAction();
        simpleaction1.setRole(new NCLRole("seta2x"));
        simpleaction1.setDelay(0);
        simpleaction1.setEventType(NCLEventType.ATTRIBUTION);
        simpleaction1.setActionType(NCLEventAction.START);
        simpleaction1.setValue("value0");
        simpleaction1.setMin(1);
        simpleaction1.setMax(2);
        simpleaction1.setQualifier(NCLActionOperator.PAR);
        simpleaction1.setRepeat(2);
        simpleaction1.setRepeatDelay(2);
        simpleaction1.setDuration(20);
        simpleaction1.setBy(2);

        NCLSimpleAction simpleaction2 = new NCLSimpleAction();
        simpleaction2.setRole(new NCLRole(NCLDefaultActionRole.STOP));

        NCLCompoundAction compoundaction = new NCLCompoundAction();
        compoundaction.setDelay(1);
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

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
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

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            //NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            //NCLBind bind = (NCLBind) link.getBinds().iterator().next();
            //NCLSimpleCondition cond = (NCLSimpleCondition) link.getXconnector().getCondition();
            String expResult = xml;
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
    public void test3a() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='$val'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='$val'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundCondition cond = (NCLCompoundCondition) instance.getCondition();

            String expResult = "teste";
            String result = cond.getParamDelay().getType();
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

    public void test3b() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='$val'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='$val'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            
            NCLCompoundAction act = (NCLCompoundAction) instance.getAction();

            String result = act.getParamDelay().getType();
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
    public void test4a() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();

            expResult = "teste";

            result = sact.getParamDelay().getType();
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
    public void test4b() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();

            expResult = "teste";

            result = sact.getParamValue().getType();
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
    public void test4c() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();

            expResult = "teste";

            result = sact.getParamRepeat().getType();
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
    public void test4d() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();

            expResult = "teste";

            result = sact.getParamRepeatDelay().getType();
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
    public void test4e() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();

            expResult = "teste";

            result = sact.getParamDuration().getType();
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
    public void test4f() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='A' delay='6s' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='ENTER' attributeType='occurrences' offset='2'/>\n"+
                "\t\t\t<valueAssessment value='1'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundAction cact = (NCLCompoundAction) instance.getAction();
            NCLSimpleAction sact = (NCLSimpleAction) cact.getActions().iterator().next();

            expResult = "teste";

            result = sact.getParamBy().getType();
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
    public void test5a() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
                "\t\t\t<valueAssessment value='$val'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();

            expResult = "teste";

            result = scond.getParamDelay().getType();
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
    public void test5b() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
                "\t\t\t<valueAssessment value='$val'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();

            expResult = "teste";

            result = scond.getParamKey().getType();
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
    public void test5c() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
                "\t\t\t<valueAssessment value='$val'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();

            expResult = "teste";

            result = att.getParamKey().getType();
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
    public void test5d() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
                "\t\t\t<valueAssessment value='$val'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();

            expResult = "teste";

            result = att.getParamOffset().getType();
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
    public void test5e() {
        String expResult, result;
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='5s'>\n"+
                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
                "\t\t\t<valueAssessment value='$val'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='1s'>\n"+
                "\t\t<simpleAction role='seta2x' value='value0' delay='0s' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='2' repeatDelay='2s' duration='20s' by='2'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLCompoundCondition ccond = (NCLCompoundCondition) instance.getCondition();
            NCLSimpleCondition scond = (NCLSimpleCondition) ccond.getConditions().iterator().next();
            NCLAssessmentStatement ass = (NCLAssessmentStatement) ccond.getStatements().iterator().next();
            NCLAttributeAssessment att = (NCLAttributeAssessment) ass.getAttributeAssessments().iterator().next();

            expResult = "teste";

            result = ass.getValueAssessment().getParamValue().getType();
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
    public void test6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>\n"+
                "\t<connectorParam name='val' type='teste'/>\n"+
                "\t<compoundCondition operator='and' delay='$val'>\n"+
                "\t\t<simpleCondition role='startSel' key='$val' delay='$val' min='1' max='2' qualifier='or' eventType='selection' transition='starts'/>\n"+
                "\t\t<assessmentStatement comparator='eq'>\n"+
                "\t\t\t<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>\n"+
                "\t\t\t<valueAssessment value='$val'/>\n"+
                "\t\t</assessmentStatement>\n"+
                "\t</compoundCondition>\n"+
                "\t<compoundAction operator='seq' delay='$val'>\n"+
                "\t\t<simpleAction role='seta2x' value='$val' delay='$val' min='1' max='2' qualifier='par' eventType='attribution' actionType='start' repeat='$val' repeatDelay='$val' duration='$val' by='$val'/>\n"+
                "\t\t<simpleAction role='stop'/>\n"+
                "\t</compoundAction>\n"+
                "</causalConnector>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = xml;
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
    public void test_validate() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLCausalConnector instance = new NCLCausalConnector(reader, null);
            String xml = "<causalConnector id='conn1'>"+
                "<connectorParam name='val' type='teste'/>"+
                "<compoundCondition operator='and' delay='$val'>"+
                "<simpleCondition role='onBegin'/>"+
                "<assessmentStatement comparator='eq'>"+
                "<attributeAssessment role='testeOccurences' eventType='selection' key='$val' attributeType='occurrences' offset='$val'/>"+
                "<valueAssessment value='$val'/>"+
                "</assessmentStatement>"+
                "</compoundCondition>"+
                "<compoundAction operator='seq' delay='$val'>"+
                "<simpleAction role='start'/>"+
                "<simpleAction role='stop'/>"+
                "</compoundAction>"+
                "</causalConnector>";

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