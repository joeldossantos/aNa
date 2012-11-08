/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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
package br.uff.midiacom.ana.util.enums;

import br.uff.midiacom.ana.util.exception.XMLException;


/**
 * Enumeration of the region base device types. A device type receives an
 * argument.
 */
public class NCLDevice {
    
    public enum DeviceName { SYSTEM_SCREEN, SYSTEM_AUDIO };
    
    protected DeviceName name;
    protected Integer param;
    
    
    public NCLDevice(DeviceName name) {
        this.name = name;
    }
    
    
    public NCLDevice(DeviceName name, Integer param) throws XMLException {
        this.name = name;
        setParamenter(param);
    }
    
    
    public NCLDevice(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        String n,a;
        Integer i, p = null;

        n = name;
        if("".equals(n.trim()))
            throw new XMLException("Empty name String");

        i = n.indexOf("(");
        if(i > 0){
            a = n.substring(i+1, n.length()-1);
            n = n.substring(0, i);
            p = new Integer(a);
        }

        if(n.equals("systemScreen"))
            this.name = DeviceName.SYSTEM_SCREEN;
        else if(n.equals("systemAudio"))
            this.name = DeviceName.SYSTEM_AUDIO;
        else
            throw new XMLException("Wrong device name");
        
        if(p != null)
            setParamenter(p);
    }
    
    
    public boolean hasParameter() {
        return param != null;
    }
    
    
    public void setParamenter(int param) throws XMLException {
        if(param < 0)
            throw new XMLException("Parameter can not be negative.");
        
        this.param = param;
    }
    
    
    public NCLDevice getDefaultValue(){
        NCLDevice def = new NCLDevice(DeviceName.SYSTEM_SCREEN);
        param = 0;
        return def;
    }
    
    
    @Override
    public String toString() {
        String result = "";
        switch(name){
            case SYSTEM_SCREEN: result += "systemScreen"; break;
            case SYSTEM_AUDIO: result += "systemAudio";
        }
        
        if(hasParameter())
            result += "(" + param + ")";
        
        return result;
    }
    
    
    public boolean compare(Object o) {
        if(o == null || o instanceof NCLDevice)
            return false;
        
        return toString().equals(((NCLDevice) o).toString());
    }
}
