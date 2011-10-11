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
package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;


/**
 * This class represents a system variable name. Depending on the system variable
 * name it may receive a argument.
 */
public class SysVarType {

    private NCLSystemVariable value;
    private Integer argument;


    /**
     * Creates the variable without an argument.
     *
     * @param value
     *          element representing the variable name.
     * @throws IllegalArgumentException
     *          if the variable needs an argument.
     */
    public SysVarType(NCLSystemVariable value) throws IllegalArgumentException {
        if(value.equals(NCLSystemVariable.SYSTEM_RETURNBITRATE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_SCREENSIZE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_SCREENGRAPHICSIZE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_AUDIOTYPE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_DEVNUMBER_i) ||
                value.equals(NCLSystemVariable.SYSTEM_CLASSTYPE_i) ||
                value.equals(NCLSystemVariable.SYSTEM_INFO_i))
            throw new IllegalArgumentException("The indicated value needs an argument.");

        this.value = value;
    }


    /**
     * Creates the variable with an argument.
     *
     * @param value
     *          element representing the variable name.
     * @param argument
     *          integer representing the variable argument.
     * @throws IllegalArgumentException
     *          if the variable does not receive an argument.
     */
    public SysVarType(NCLSystemVariable value, int argument) throws IllegalArgumentException {
        if(!value.equals(NCLSystemVariable.SYSTEM_RETURNBITRATE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_SCREENSIZE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_SCREENGRAPHICSIZE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_AUDIOTYPE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_DEVNUMBER_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_CLASSTYPE_i) ||
                !value.equals(NCLSystemVariable.SYSTEM_INFO_i))
            throw new IllegalArgumentException("The indicated value does not receive argument.");

        this.value = value;
        this.argument = argument;
    }


    public SysVarType(String value) {
        if(value == null)
            throw new NullPointerException("Null value String");
        if("".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

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
     * Returns the variable.
     *
     * @return
     *          element representing the variable name. It does not represent the
     *          variable argument in case it uses one.
     * @see #getArgument()
     */
    public NCLSystemVariable getValue() {
        return value;
    }


    /**
     * Returns the variable argument.
     *
     * @return
     *          integer representing the variable argument.
     */
    public Integer getArgument() {
        return argument;
    }


    /**
     * Returns the variable.
     *
     * @return
     *          String representing the variable. If the variable has an argument
     *          the String will represent it.
     */
    public String parse() {
        if(argument == null)
            return value.toString();
        else
            return value.toString() + "(" + argument + ")";
    }
}
