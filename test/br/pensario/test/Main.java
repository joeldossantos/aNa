package br.pensario.test;

import br.pensario.NCLBody;
import br.pensario.NCLDoc;
import br.pensario.NCLHead;
import br.pensario.NCLValues.NCLActionOperator;
import br.pensario.NCLValues.NCLColor;
import br.pensario.NCLValues.NCLConditionOperator;
import br.pensario.NCLValues.NCLDefaultActionRole;
import br.pensario.NCLValues.NCLDefaultConditionRole;
import br.pensario.NCLValues.NCLKey;
import br.pensario.NCLValues.NCLNamespace;
import br.pensario.NCLValues.NCLSampleType;
import br.pensario.NCLValues.NCLSystemVariable;
import br.pensario.connector.NCLActionRole;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLCompoundCondition;
import br.pensario.connector.NCLConditionRole;
import br.pensario.connector.NCLConnectorBase;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.connector.NCLSimpleAction;
import br.pensario.connector.NCLSimpleCondition;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.interfaces.NCLArea;
import br.pensario.interfaces.NCLPort;
import br.pensario.interfaces.NCLProperty;
import br.pensario.interfaces.NCLSample;
import br.pensario.interfaces.NCLTime;
import br.pensario.link.NCLBind;
import br.pensario.link.NCLLink;
import br.pensario.node.NCLMedia;
import br.pensario.node.NCLNode;
import br.pensario.region.NCLRegion;
import br.pensario.region.NCLRegionBase;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			NCLHead head = montaHead();
			NCLBody body = montaBody(head);

			NCLDoc document = new NCLDoc("meudocumento", NCLNamespace.EDTV,
					head, body);

			System.out.println(document);

		} catch (Exception e) { //
			e.printStackTrace();
		}

	}

	private static NCLHead montaHead() throws Exception {
		NCLRegionBase region_base = new NCLRegionBase("rgBase");
		NCLDescriptorBase descriptor_base = new NCLDescriptorBase();
		NCLConnectorBase connector_base = new NCLConnectorBase();

		NCLDescriptor descriptor1 = new NCLDescriptor("descriptor01");

		descriptor1.setExplicitDur("valexpDur");
		descriptor1.setFocusBorderColor(NCLColor.gray);
		descriptor1.setFocusSelSrc("valFocusSelSrc");

		NCLDescriptor descriptor2 = new NCLDescriptor("descriptor02");

		descriptor2.setSelBorderColor(NCLColor.green);
		descriptor2.setFocusBorderTransparency(40);
		descriptor2.setMoveUp(descriptor1);

		descriptor1.setMoveDown(descriptor2);

		descriptor_base.addDescriptor(descriptor1);
		descriptor_base.addDescriptor(descriptor2);

		NCLRegion region1 = new NCLRegion("regiao01");
		NCLRegion region2 = new NCLRegion("regiao02");

		region1.setWidth(45, true);
		region1.setHeight(150, false);
		region1.setBottom(56, true);
		region1.addRegion(region2);
		region2.setTitle("T�tulo de teste");

		region_base.addRegion(region1);

		// INICIO DE CONECTOR COM CONDICAO E ACOES SIMPLES
		NCLSimpleCondition scond0 = new NCLSimpleCondition(
				new NCLConditionRole(NCLDefaultConditionRole.ONBEGIN));
		scond0.setDelay("strdelay0");
		scond0.setKey(NCLKey.BACK);
		scond0.setMin(1);
		scond0.setMax(2);
		scond0.setQualifier(NCLConditionOperator.AND);

		NCLSimpleAction sact0 = new NCLSimpleAction(new NCLActionRole(
				NCLDefaultActionRole.START));
		sact0.setDelay("strdelay0");
		sact0.setValue("value0");
		sact0.setMin(1);
		sact0.setMax(2);
		sact0.setQualifier(NCLActionOperator.PAR);

		NCLConnectorParam connparam0 = new NCLConnectorParam("param0");
		connparam0.setType("type0");

		NCLCausalConnector conn0 = new NCLCausalConnector("conn0", scond0,
				sact0);
		conn0.addConnectorParam(connparam0);

		// INICIO DE CONECTOR COM CONDICAO E ACOES COMPOSTOS

		NCLSimpleCondition scond1 = new NCLSimpleCondition(
				new NCLConditionRole(NCLDefaultConditionRole.ONBEGIN));
		scond1.setDelay("strdelay1");
		scond1.setKey(NCLKey.KEY_A);
		scond1.setMin(1);
		scond1.setMax(2);
		scond1.setQualifier(NCLConditionOperator.AND);

		NCLCompoundCondition ccond0 = new NCLCompoundCondition();
		ccond0.setDelay("strdelay0");
		ccond0.setOperator(NCLConditionOperator.AND);
		ccond0.addCondition(scond0);
		ccond0.addCondition(scond1);

		NCLSimpleAction sact1 = new NCLSimpleAction(new NCLActionRole(
				NCLDefaultActionRole.START));
		sact1.setDelay("strdelay1");
		sact1.setValue("value0");
		sact1.setMin(1);
		sact1.setMax(2);
		sact1.setQualifier(NCLActionOperator.PAR);

		NCLConnectorParam connparam1 = new NCLConnectorParam("param1");
		connparam1.setType("type1");

		NCLCausalConnector conn1 = new NCLCausalConnector("conn1", ccond0,
				sact0);
		conn1.addConnectorParam(connparam0);
		conn1.addConnectorParam(connparam1);

		connector_base.addCausalConnector(conn0);
		connector_base.addCausalConnector(conn1);

		NCLHead head = new NCLHead(region_base, descriptor_base, connector_base);
		
		return head;

	}
	
	private static NCLBody montaBody(NCLHead head) throws Exception {
		
		
		NCLBody body = new NCLBody();
		
		body.setId("body0");
		
		NCLCausalConnector conn0 = head.getConnectorBase().getCausalConnector("conn0");
		
		
		NCLMedia node = new NCLMedia("mcsaa");		
						
		NCLPort port = new NCLPort("csasc", node);		
		NCLProperty property = new NCLProperty(NCLSystemVariable.CHANNEL_KEYCAPTURE);
		
		NCLArea area = new NCLArea("area0");
		area.setCoords(new int[]{1,3,5,1});
		area.setLabel("label0");
		area.setPosition(123);
		area.setText("qcbq");
				
		NCLSample sample = new NCLSample(123,NCLSampleType.F);
		NCLTime time0 = new NCLTime(4124);
		NCLTime time1 = new NCLTime(7124);
		
		area.setLast(sample);
		area.setFirst(sample);
		area.setBegin(time0);
		area.setEnd(time1);
		
		node.addArea(area);
						
		
		NCLRole role0 = new NCLConditionRole(NCLDefaultConditionRole.ONBEGIN);
		NCLRole role1 = new NCLConditionRole(NCLDefaultConditionRole.ONEND);
		
		NCLBind bind1 = new NCLBind(role0,node);		
		NCLBind bind2 = new NCLBind(role1,node);		
		
		NCLLink link = new NCLLink(conn0,bind1,bind2);

		body.addLink(link);

		body.addNode(node);		
		
		body.addPort(port);		
		body.addProperty(property);
		
		return body;

	}

}
