/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.descriptor;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLAttributes;
import br.pensario.NCLValues.NCLColor;
import br.pensario.region.NCLRegion;
import br.pensario.transition.NCLTransition;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLDescriptorTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLDescriptor descriptor = new NCLDescriptor("dTV");
        descriptor.setRegion(new NCLRegion("rgTV"));
        descriptor.setExplicitDur(20);
        descriptor.setFreeze(true);
        descriptor.setPlayer("teste");
            NCLDescriptor d1 = new NCLDescriptor("d1");
            d1.setFocusIndex(1);
            NCLDescriptor d2 = new NCLDescriptor("d2");
            d2.setFocusIndex(2);
            NCLDescriptor d3 = new NCLDescriptor("d3");
            d3.setFocusIndex(3);
            NCLDescriptor d4 = new NCLDescriptor("d4");
            d4.setFocusIndex(4);
        descriptor.setMoveLeft(d1);
        descriptor.setMoveRight(d2);
        descriptor.setMoveDown(d3);
        descriptor.setMoveUp(d4);
        descriptor.setFocusIndex(10);
        descriptor.setFocusBorderColor(NCLColor.BLACK);
        descriptor.setFocusBorderWidth(5);
        descriptor.setFocusBorderTransparency(1);
        descriptor.setFocusSrc("foco.jpg");
        descriptor.setFocusSelSrc("sel.jpg");
        descriptor.setSelBorderColor(NCLColor.AQUA);
        descriptor.setTransIn(new NCLTransition("tin"));
        descriptor.setTransOut(new NCLTransition("tout"));

        String expResult = "<descriptor id='dTV' region='rgTV' explicitDur='20s' freeze='true' player='teste' moveLeft='1' moveRight='2' moveDown='3' moveUp='4'"+
                " focusIndex='10' focusBorderColor='black' focusBorderWidth='5' focusBorderTransparency='1%' focusSrc='foco.jpg'"+
                " focusSelSrc='sel.jpg' SelBorderColor='aqua' transIn='tin' transOut='tout'/>\n";
        String result = descriptor.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLDescriptor descriptor = new NCLDescriptor("dTV");
        NCLDescriptorParam param = new NCLDescriptorParam();
        param.setName(NCLAttributes.TOP);
        param.setValue("100");
        descriptor.addDescriptorParam(param);

        String expResult = "<descriptor id='dTV'>\n\t<descriptorParam name='top' value='100'/>\n</descriptor>\n";
        String result = descriptor.parse(0);
        assertEquals(expResult, result);
    }
}
