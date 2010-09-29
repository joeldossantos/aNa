package test.br.pensario;

import br.pensario.*;
import br.pensario.node.*;
import br.pensario.connector.*;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.*;
import br.pensario.link.*;

public class testBody {

	static NCLDoc doc;
	static NCLBody body;
	NCLMedia m1, m2, m3;
	NCLArea a1, a2;
	NCLProperty prop1;
	NCLPort p1;
	
	public testBody() throws Exception {
		//doc = new NCLDoc("teste", NCLValues.NCLNamespace.EDTV, null, body);
		body = new NCLBody();
		
		createMedia();
		createLink();
	}
	
	public void createMedia() throws Exception {
		// media video
		NCLDescriptor desc = new NCLDescriptor("dpVideo");
		m1 = new NCLMedia("video");
		m1.setSrc("media/video.mp4");
		m1.setDescriptor(desc);
		
		a1 = new NCLArea("apresentaIcone");
		a1.setBegin(new NCLTime(20));
		a1.setEnd(new NCLTime(40));
		
		a2 = new NCLArea("apresentaFigura");
		a2.setBegin(new NCLTime(50));
		
		m1.addArea(a1);
		m1.addArea(a2);
		
		// media settings
		m2 = new NCLMedia("variaveis");
		m2.setType(NCLValues.NCLMimeType.APPLICATION_X_GINGA_SETTINGS);
		
		prop1 = new NCLProperty("interacao");
		
		m2.addProperty(prop1);
        
		// media icone
		NCLDescriptor desc2 = new NCLDescriptor("dpIcone");
		m3 = new NCLMedia("icone");
		m3.setSrc("media/icone.png");
		m3.setDescriptor(desc2);
		
		
		
		p1 = new NCLPort("pInicio", m1);
		
		body.addNode(m1);
		body.addNode(m2);
		body.addNode(m3);
		body.addPort(p1);
	}
	
	public void createLink() throws Exception {
		
		NCLCausalConnector c1 = new NCLCausalConnector(null, null, null);
		c1.setId("onBeginStart");
		NCLCausalConnector c2 = new NCLCausalConnector(null, null, null);
		c2.setId("onEndStop");
		NCLCausalConnector c3 = new NCLCausalConnector(null, null, null);
		c3.setId("onKeySelectionSet");
		
		NCLConditionRole r1 = new NCLConditionRole();
		r1.setId(NCLValues.NCLDefaultConditionRole.ONBEGIN);
		NCLActionRole r2 = new NCLActionRole();
		r2.setId(NCLValues.NCLDefaultActionRole.START);
		NCLConditionRole r3 = new NCLConditionRole();
		r3.setId(NCLValues.NCLDefaultConditionRole.ONEND);
		NCLActionRole r4 = new NCLActionRole();
		r4.setId(NCLValues.NCLDefaultActionRole.STOP);
		NCLActionRole r5 = new NCLActionRole();
		r5.setId(NCLValues.NCLDefaultActionRole.SET);
		NCLConditionRole r6 = new NCLConditionRole();
		r6.setId(NCLValues.NCLDefaultConditionRole.ONSELECTION);
		
		
		NCLBind b1 = new NCLBind(r1, m1);
		b1.setInterface(a1);
		NCLBind b2 = new NCLBind(r2, m3);
		NCLLink l1 = new NCLLink(c1, b1, b2);
		
		NCLBind b3 = new NCLBind(r3, m1);
		b3.setInterface(a1);
		NCLBind b4 = new NCLBind(r4, m3);
		NCLLink l2 = new NCLLink(c2, b3, b4);
		
		NCLBind b5 = new NCLBind(r1, m1);
		b5.setInterface(a2);
		NCLBind b6 = new NCLBind(r2, new NCLMedia("decide_figura"));
		NCLLink l3 = new NCLLink(c1, b5, b6);
		
		NCLBind b7 = new NCLBind(r3, new NCLMedia("decide_figura"));
		NCLBind b8 = new NCLBind(r4, m1);
		NCLLink l4 = new NCLLink(c2, b7, b8);
		
		
		NCLBind b10 = new NCLBind(r6, m3);//onSelection
			NCLConnectorParam pa = new NCLConnectorParam();
			pa.setName("vKey");
			b10.addBindParam(pa, "RED");
		NCLBind b11 = new NCLBind(r5, m2);//set
			b11.setInterface(prop1);
			NCLConnectorParam p = new NCLConnectorParam();
			p.setName("var");
			b11.addBindParam(p, "sim");
		NCLBind b12 = new NCLBind(r4, m3);//stop
		NCLLink l5 = new NCLLink(c3, b10, b11);
		l5.addBind(b12);
		
		
		body.addLink(l1);
		body.addLink(l2);
		body.addLink(l3);
		body.addLink(l4);
		body.addLink(l5);
	}
	
	public void createContext() throws Exception {
		
	}
	
	private static boolean getA() {
		return !true;
	}
	
	public static void main(String[] args) throws Exception {
		new testBody();
		
		//System.out.println(doc.parse());
		System.out.println(body.parse(0));
		System.out.print(getA());
	}
}
