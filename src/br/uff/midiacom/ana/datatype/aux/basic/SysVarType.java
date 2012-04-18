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
package br.uff.midiacom.ana.datatype.aux.basic;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.xml.XMLException;


/**
 * This class represents a system variable name. Depending on the system variable
 * name it may receive a argument.
 */
public class SysVarType<T extends SysVarType> {

    private NCLSystemVariable value;
    private Integer argument;


    /**
     * Creates the variable name without an argument. The possible variable names
     * to be used are defined in the enumeration <i>NCLSystemVariable</i>.
     *
     * @param value
     *          element representing the variable name from the enumeration
     *          <i>NCLSystemVariable</i>.
     * @throws XMLException
     *          if the variable name is null or needs an argument.
     */
    public SysVarType(NCLSystemVariable value) throws XMLException {
        if(value == null)
            throw new XMLException("Null variable name.");
        if(value.equals(NCLSystemVariable.SYSTEM_RETURNBITRATE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_SCREENSIZE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_SCREENGRAPHICSIZE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_AUDIOTYPE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_DEVNUMBER_i) ||
                value.equals(NCLSystemVariable.SYSTEM_CLASSTYPE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_INFO_i))
            throw new XMLException("The indicated variable name needs an argument.");

        this.value = value;
    }


    /**
     * Creates the variable name with an argument. The possible variable names
     * to be used are defined in the enumeration <i>NCLSystemVariable</i>.
     *
     * @param value
     *          element representing the variable name from the enumeration
     *          <i>NCLSystemVariable</i>.
     * @param argument
     *          integer representing the variable argument.
     * @throws XMLException
     *          if the variable name is null or does not need an argument.
     */
    public SysVarType(NCLSystemVariable value, int argument) throws XMLException {
        if(value == null)
            throw new XMLException("Null variable name.");
        if(!value.equals(NCLSystemVariable.SYSTEM_RETURNBITRATE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_SCREENSIZE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_SCREENGRAPHICSIZE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_AUDIOTYPE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_DEVNUMBER_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_CLASSTYPE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_INFO_i))
            throw new XMLException("The indicated value does not receive argument.");

        this.value = value;
        this.argument = argument;
    }


    /**
     * Creates the variable name from a string.
     * 
     * @param value 
     *          string representing the variable name.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public SysVarType(String value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value String");
        if("".equals(value.trim()))
            throw new XMLException("Empty value String");

        int index = value.indexOf("(");
        if(index > 0){
            String argument = value.substring(index+1, value.length()-1);
            this.argument = new Integer(argument);
            value = value.substring(0, index);
        }
        
        for(NCLSystemVariable v : NCLSystemVariable.values()){
            if(v.toString().equals(value))
                this.value = v;
        }
    }


    /**
     * Returns the variable name. The possible variable names to be used are
     * defined in the enumeration <i>NCLSystemVariable</i>.
     *
     * @return
     *          element representing the variable name from the enumeration
     *          <i>NCLSystemVariable</i>. It does not represent the
     *          variable argument in case it uses one.
     * @see #getArgument()
     */
    public NCLSystemVariable getValue() {
        return value;
    }


    /**
     * Returns the variable name argument. The argument is <i>null</i> if the
     * variable name does not take argument.
     *
     * @return
     *          integer representing the variable argument or <i>null</i> if the
     *          variable name does not take argument.
     * @see #getValue() 
     */
    public Integer getArgument() {
        return argument;
    }


    /**
     * Returns the variable name.
     *
     * @return
     *          String representing the variable name.
     */
    public String parse() {
        if(argument == null)
            return value.toString();
        else
            return value.toString() + "(" + argument + ")";
    }
    
    
    /**
     * Compares two variable names.
     *
     * @param other
     *          element representing the name to compare.
     * @return
     *          true if the names are equal and false otherwise.
     */
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        boolean comp = true;
        
        // Test if the argument is equal
        int thisArg, otherArg;
        if(getArgument() != null) thisArg = getArgument(); else thisArg = -1;
        if(other.getArgument() != null) otherArg = other.getArgument(); else otherArg = -1;
        comp &= (thisArg == otherArg);
        
        // Test if the name is equal
        comp &= getValue().equals(other.getValue());
        
        return comp;
    }
}
