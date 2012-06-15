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
package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.ana.datatype.enums.NCLNodeAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents an node attribute name. This attribute name is declared
 * by property elements.
 * 
 * @param <T>
 */
public class NCLAttribute<T extends NCLAttribute> {

    protected NCLNodeAttributes Aname;
    protected StringType Sname;
    
    
    /**
     * Attribute name constructor. Creates the name of the attribute as a
     * reserved name. The name is required and can not be <i>null</i>.
     * 
     * @param name
     *          element representing the name of the attribute from the list of
     *          reserved attribute names.
     * @throws XMLException 
     *          if the name is null.
     */
    public NCLAttribute(NCLNodeAttributes name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        this.Aname = name;
    }
    
    
    /**
     * Attribute name constructor.
     * 
     * @param name
     *          string representing the name of the attribute.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public NCLAttribute(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        this.Sname = new StringType(name);
        //Check if the string is a reserved name.
        for(NCLNodeAttributes att : NCLNodeAttributes.values()){
            if(name.equals(att.toString())){
                this.Aname = att;
                this.Sname = null;
            }
        }
    }
    
    
    /**
     * Returns the name of the attribute.
     * 
     * @return 
     *          element representing the name of the attribute from the list of
     *          reserved attribute names or <i>null</i> if the attribute name
     *          is not a reserved name.
     */
    public NCLNodeAttributes getReservedName() {
        return Aname;
    }
    
    
    /**
     * Returns the name of the attribute.
     * 
     * @return 
     *          string representing the name of the attribute.
     */
    public String getName() {
        if(Aname != null)
            return Aname.toString();
        else
            return Sname.getValue();
    }
    
    
    /**
     * Compares to attribute names.
     * 
     * @param other
     *          other name to compare with.
     * @return 
     *          true if the names are equals.
     */
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        return getName().equals(other.getName());
    }
}
