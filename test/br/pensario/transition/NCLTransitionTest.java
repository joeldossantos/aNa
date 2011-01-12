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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
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
}