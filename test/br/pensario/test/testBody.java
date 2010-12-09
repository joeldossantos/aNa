import br.pensario.*;
import br.pensario.NCLValues.NCLParamInstance;
import br.pensario.node.*;
import br.pensario.connector.*;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.*;
import br.pensario.link.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class testBody {

    static NCLDoc doc;
    static NCLBody body;
    NCLMedia m1, m2, m3;
    NCLArea a1, a2;
    NCLProperty prop1;
    NCLPort p1;
    
    public testBody() throws Exception {
        body = new NCLBody();
        
        createMedia();
        createLink();
    }
    
    public void createMedia() throws Exception {
        // media video
        NCLDescriptor desc = new NCLDescriptor("dpvid");
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
        
        
        
        p1 = new NCLPort("pInicio");
        p1.setComponent(m1);
        
        body.addNode(m1);
        body.addNode(m2);
        body.addNode(m3);
        body.addPort(p1);
    }
    
    public void createLink() throws Exception {
        
        NCLCausalConnector c1 = new NCLCausalConnector("onBeginStart");
        NCLCausalConnector c2 = new NCLCausalConnector("onEndStop");
        NCLCausalConnector c3 = new NCLCausalConnector("onKeySelectionSet");
        
        NCLRole r1 = new NCLRole(NCLValues.NCLDefaultConditionRole.ONBEGIN.toString());
        NCLRole r2 = new NCLRole(NCLValues.NCLDefaultActionRole.START.toString());
        NCLRole r3 = new NCLRole(NCLValues.NCLDefaultConditionRole.ONEND.toString());
        NCLRole r4 = new NCLRole(NCLValues.NCLDefaultActionRole.STOP.toString());
        NCLRole r5 = new NCLRole(NCLValues.NCLDefaultActionRole.SET.toString());
        NCLRole r6 = new NCLRole(NCLValues.NCLDefaultConditionRole.ONSELECTION.toString());
        
        
        NCLLink l1 = new NCLLink();
            NCLBind b1 = new NCLBind();
            b1.setRole(r1);
            b1.setComponent(m1);
            b1.setInterface(a1);
        
            NCLBind b2 = new NCLBind();
            b2.setRole(r2);
            b2.setComponent(m3);
        l1.setXconnector(c1);
        l1.addBind(b1);
        l1.addBind(b2);
        
        
        NCLLink l2 = new NCLLink();
            NCLBind b3 = new NCLBind();
            b3.setRole(r3);
            b3.setComponent(m1);
            b3.setInterface(a1);
            
            NCLBind b4 = new NCLBind();
            b4.setRole(r4);
            b4.setComponent(m3);
        l2.setXconnector(c2);
        l2.addBind(b3);
        l2.addBind(b4);
        
        
        NCLLink l3 = new NCLLink();
            NCLBind b5 = new NCLBind();
            b5.setRole(r1);
            b5.setComponent(m1);
            b5.setInterface(a2);
            
            NCLBind b6 = new NCLBind();
            b6.setRole(r2);
            b6.setComponent(new NCLMedia("decide_figura"));
        l3.setXconnector(c1);
        l3.addBind(b5);
        l3.addBind(b6);
        
        
        NCLLink l4 = new NCLLink();
            NCLBind b7 = new NCLBind();
            b7.setRole(r3);
            b7.setComponent(new NCLMedia("decide_figura"));
            
            NCLBind b8 = new NCLBind();
            b8.setRole(r4);
            b8.setComponent(m1);
        l4.setXconnector(c2);
        l4.addBind(b7);
        l4.addBind(b8);
        
        
        NCLLink l5 = new NCLLink();
            NCLBind b10 = new NCLBind();
            b10.setRole(r6);
            b10.setComponent(m3);//onSelection
                NCLConnectorParam pa = new NCLConnectorParam("vKey");
                NCLParam bpa = new NCLParam(NCLParamInstance.BINDPARAM);
                bpa.setName(pa);
                bpa.setValue("RED");
            b10.addBindParam(bpa);
            
            NCLBind b11 = new NCLBind();
            b11.setRole(r5);
            b11.setComponent(m2);//set
            b11.setInterface(prop1);
                NCLConnectorParam p = new NCLConnectorParam("var");
                NCLParam bpa2 = new NCLParam(NCLParamInstance.BINDPARAM);
                bpa2.setName(p);
                bpa2.setValue("sim");
            b11.addBindParam(bpa2);
            
            NCLBind b12 = new NCLBind();
            b12.setRole(r4);
            b12.setComponent(m3);//stop
        l5.setXconnector(c3);
        l5.addBind(b10);
        l5.addBind(b11);
        l5.addBind(b12);
        
        
        body.addLink(l1);
        body.addLink(l2);
        body.addLink(l3);
        body.addLink(l4);
        body.addLink(l5);
    }
    
    public void createContext() throws Exception {
        
    }
    
    @Test
    public void teste() throws Exception {
        new testBody();
        
        String expResult = "";
        String result = body.parse(0);
        
        System.out.println(result);
        assertNotSame(expResult, result);
    }
}
