import br.pensario.NCLHead;
import br.pensario.NCLValues.NCLActionOperator;
import br.pensario.NCLValues.NCLColor;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.NCLValues.NCLConditionOperator;
import br.pensario.NCLValues.NCLDefaultActionRole;
import br.pensario.NCLValues.NCLDefaultConditionRole;
import br.pensario.NCLValues.NCLKey;
import br.pensario.connector.NCLAssessmentStatement;
import br.pensario.connector.NCLAttributeAssessment;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLCompoundCondition;
import br.pensario.connector.NCLConnectorBase;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.connector.NCLSimpleAction;
import br.pensario.connector.NCLSimpleCondition;
import br.pensario.connector.NCLValueAssessment;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.region.NCLRegion;
import br.pensario.region.NCLRegionBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class Main {

    @Test
    public void testConnector() throws Exception {
        String expResult = "";
        String result = montaConnector().parse(0);
        
        System.out.println(result);
        assertNotSame(expResult, result);
    }
    
    @Test
    public void testDescriptor() throws Exception {
        String expResult = "";
        String result = montaDescriptor().parse(0);
        
        System.out.println(result);
        assertNotSame(expResult, result);
    }
    
    @Test
    public void testRegion() throws Exception {
        String expResult = "";
        String result = montaRegion().parse(0);
        
        System.out.println(result);
        assertNotSame(expResult, result);
    }
    
    private static NCLConnectorBase montaConnector() throws Exception {
        NCLConnectorBase connector_base = new NCLConnectorBase();
        
        
        // INICIO DE CONECTOR COM CONDICAO E ACOES SIMPLES
        NCLCausalConnector conn0 = new NCLCausalConnector("conn0");
        
        NCLSimpleCondition scond0 = new NCLSimpleCondition();
        NCLRole r = new NCLRole(NCLDefaultConditionRole.ONBEGIN);
        scond0.setRole(r);
        scond0.setDelay(2);
        scond0.setKey(NCLKey.BACK);
        scond0.setMin(1);
        scond0.setMax(2);
        scond0.setQualifier(NCLConditionOperator.AND);

        NCLSimpleAction sact0 = new NCLSimpleAction();
        NCLRole r2 = new NCLRole(NCLDefaultActionRole.START);
        sact0.setRole(r2);
        sact0.setDelay(2);
        sact0.setValue("value0");
        sact0.setMin(1);
        sact0.setMax(2);
        sact0.setQualifier(NCLActionOperator.PAR);

        conn0.setCondition(scond0);
        conn0.setAction(sact0);
            NCLConnectorParam p = new NCLConnectorParam("param0");
        conn0.addConnectorParam(p);

        
        // INICIO DE CONECTOR COM CONDICAO E ACOES COMPOSTOS
        NCLCausalConnector conn1 = new NCLCausalConnector("conn1");
        
        NCLSimpleCondition scond1 = new NCLSimpleCondition();
        scond1.setRole(r);
        scond1.setDelay(6);
        scond1.setKey(NCLKey.KEY_A);
        scond1.setMin(1);
        scond1.setMax(2);
        scond1.setQualifier(NCLConditionOperator.AND);
        
        NCLAssessmentStatement ass1 = new NCLAssessmentStatement();
        ass1.setComparator(NCLComparator.EQ);
        NCLValueAssessment v = new NCLValueAssessment("1");
        ass1.setValueAssessment(v);
            NCLAttributeAssessment att1 = new NCLAttributeAssessment();
            NCLRole r3 = new NCLRole("teste");
            att1.setRole(r3);
        ass1.addAttributeAssessment(att1);

        NCLCompoundCondition ccond0 = new NCLCompoundCondition();
        ccond0.setDelay(5);
        ccond0.setOperator(NCLConditionOperator.AND);
        ccond0.addCondition(scond0);
        ccond0.addCondition(scond1);
        ccond0.addStatement(ass1);

        NCLSimpleAction sact1 = new NCLSimpleAction();
        sact1.setRole(r2);
        sact1.setDelay(0);
        sact1.setValue("value0");
        sact1.setMin(1);
        sact1.setMax(2);
        sact1.setQualifier(NCLActionOperator.PAR);

        conn1.addConnectorParam(p);
            NCLConnectorParam p1 = new NCLConnectorParam("param1");
        conn1.addConnectorParam(p1);
        conn1.setCondition(ccond0);
        conn1.setAction(sact1);

        connector_base.addCausalConnector(conn0);
        connector_base.addCausalConnector(conn1);
        
        return connector_base;
    }

    private static NCLDescriptorBase montaDescriptor() throws Exception {
        NCLDescriptorBase descriptor_base = new NCLDescriptorBase();
        
        NCLDescriptor descriptor1 = new NCLDescriptor("descriptor01");
        descriptor1.setExplicitDur(5);
        descriptor1.setFocusBorderColor(NCLColor.GRAY);
        descriptor1.setFocusSelSrc("valFocusSelSrc");
        descriptor1.setFocusIndex(1);

        NCLDescriptor descriptor2 = new NCLDescriptor("descriptor02");
        descriptor2.setSelBorderColor(NCLColor.GREEN);
        descriptor2.setFocusBorderTransparency(40);
        descriptor2.setMoveUp(descriptor1);
        descriptor2.setFocusIndex(2);

        descriptor1.setMoveDown(descriptor2);

        descriptor_base.addDescriptor(descriptor1);
        descriptor_base.addDescriptor(descriptor2);
        
        return descriptor_base;
    }
    
    private static NCLRegionBase montaRegion() throws Exception {
        NCLRegionBase region_base = new NCLRegionBase();
        
        NCLRegion region1 = new NCLRegion("regiao01");
        NCLRegion region2 = new NCLRegion("regiao02");

        region1.setWidth(45, true);
        region1.setHeight(150, false);
        region1.setBottom(56, true);
        region1.addRegion(region2);
        region2.setTitle("Titulo de teste");

        region_base.addRegion(region1);
        
        return region_base;
    }
    
    private static NCLHead montaHead() throws Exception {
        NCLHead head = new NCLHead();
        head.setRegionBase(montaRegion());
        head.setDescriptorBase(montaDescriptor());
        head.setConnectorBase(montaConnector());
        
        return head;
    }
}
