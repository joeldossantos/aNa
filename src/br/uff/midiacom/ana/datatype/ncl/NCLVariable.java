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

import br.uff.midiacom.ana.datatype.aux.basic.SysVarType;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRulePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


/**
 * Class that represents an NCL document global variable. This variable is
 * referred by document rules and is declared by property elements children
 * of a media element of type <i>settings</i>.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <V> 
 */
public class NCLVariable<T extends NCLVariable,
                         P extends NCLRulePrototype,
                         I extends NCLElementImpl,
                         V extends SysVarType>
        extends NCLElementPrototype<T, P, I>
        implements ReferredElement<ReferenceType> {

    protected V Rname;
    protected StringType Sname;
    
    protected ItemList<ReferenceType> references;
    
    
    /**
     * Global variable constructor.
     * 
     * @param name
     *          element representing the name of the variable from the list of
     *          reserved system variable names.
     * @throws XMLException 
     *          if the name is null.
     */
    public NCLVariable(V name) throws XMLException {
        super();
        setName(name);
        references = new ItemList<ReferenceType>();
    }
    
    
    /**
     * Global variable constructor.
     * 
     * @param name
     *          string representing the name of the variable.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public NCLVariable(String name) throws XMLException {
        super();
        setName(name);
        references = new ItemList<ReferenceType>();
    }
    
    
    /**
     * Sets the name of the global variable as a reserved name. The name is
     * required and can not be <i>null</i>.
     * 
     * @param name
     *          element representing the name of the variable from the list of
     *          reserved system variable names.
     * @throws XMLException 
     *          if the name is null.
     */
    protected void setName(V name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        this.Rname = name;
    }
    
    
    /**
     * Sets the name of the global variable. The name is required and can not
     * be <i>null</i>.
     * 
     * @param name
     *          string representing the name of the variable.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    protected void setName(String name) throws XMLException {
        V aux = (V) new SysVarType(name);
        
        if(aux.getValue() != null)
            Rname = aux;
        else
            this.Sname = new StringType(name);
    }
    
    
    /**
     * Returns the name of the global variable.
     * 
     * @return 
     *          element representing the name of the variable from the list of
     *          reserved system variable names or <i>null</i> if the variable
     *          name is not a reserved name.
     */
    public V getReservedName() {
        return Rname;
    }
    
    
    /**
     * Returns the name of the global variable.
     * 
     * @return 
     *          string representing the name of the variable.
     */
    public String getName() {
        if(Rname != null)
            return Rname.parse();
        else
            return Sname.getValue();
    }
    
    
    public String parse(int ident) {
        return getName();
    }
    
    
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        return getName().equals(other.getName());
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
    
    
    @Deprecated
    public void load(Element element) throws XMLException {
    }
}
