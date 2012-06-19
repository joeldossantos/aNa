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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.XMLLoader;
import br.uff.midiacom.ana.util.enums.NCLNodeAttributes;
import br.uff.midiacom.ana.util.enums.NCLSystemVariable;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLPropertyTest {

    static NCLProperty prop;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
            prop = new NCLProperty("a");
    }

    @Test
    public void testParse() {
        try{
            prop.setName("interacao");
            prop.setValue("nao");
            String expResult = "<property name='interacao' value='nao'/>\n";
            String result = prop.parse(0);
            assertEquals(expResult, result);
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void testSetName() {
        try{
            prop.setName(null);
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setName("qualquer-coisa");
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
        try{
            prop.setName(null);
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setName(new NCLVariable(NCLSystemVariable.SYSTEM_SCREENSIZE));
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void testSetValue() {
        try{
            prop.setValue("");
            fail("Deveria gerar exceção");
        }
        catch(Exception e){
        }
        try{
            prop.setValue("valor");
        }
        catch(Exception e){
            fail("Exceção: " + e);
        }
    }

    @Test
    public void test2() throws XMLException {
        String expResult = "<property name='interacao' value='nao'/>\n";

        XMLLoader loader = new XMLLoader(expResult);
        NCLProperty instance = new NCLProperty();
        instance.load(loader.getElement());

        String result = instance.parse(0);
        assertEquals(expResult, result);
    }
}