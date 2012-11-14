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
package br.uff.midiacom.ana.util.ncl;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLSystemVariable;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import br.uff.midiacom.ana.rule.NCLRule;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.util.ArrayList;
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
public class NCLVariable<T extends NCLElement>
        extends NCLNamedElementPrototype<T, Object>
        implements ReferredElement<T> {

    protected Integer param;
    protected ArrayList<T> references;
    
    
    /**
     * Global variable constructor.
     * 
     * @param name
     *          element representing the name of the variable. The name can be a
     *          string of a name from the list of reserved system variable names.
     * @throws XMLException 
     *          if the name is null.
     */
    public NCLVariable(Object name) throws XMLException {
        super();
        setName(name);
        references = new ArrayList<T>();
    }
    
    
    /**
     * Sets the name of the global variable as a reserved name. The name is
     * required and can not be <i>null</i>.
     * 
     * @param name
     *          element representing the name of the variable. The name can be a
     *          string of a name from the list of reserved system variable names.
     * @throws XMLException 
     *          if the name is null.
     */
    @Override
    public void setName(Object name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        if(name instanceof String){
            String n,a;
            Integer i, p = null;
            
            n = (String) name;
            if("".equals(n.trim()))
                throw new XMLException("Empty role String");
            
            i = n.indexOf("(");
            if(i > 0){
                a = n.substring(i+1, n.length()-1);
                n = n.substring(0, i);
                p = new Integer(a);
            }
            
            NCLSystemVariable v = NCLSystemVariable.getEnumType(n);
            if(v != null)
                this.name = v;
            if(p != null)
                setParamenter(p);
            
            this.name = name;
        }
        else if(name instanceof NCLSystemVariable){
            this.name = name;
        }
        else{
            throw new XMLException("Wrong name type.");
        }
    }
    
    
    public boolean hasParameter() {
        return param != null;
    }
    
    
    public void setParamenter(int param) throws XMLException {
        if(param < 0)
            throw new XMLException("Parameter can not be negative.");
        
        this.param = param;
    }
    
    
    @Override
    public String parse(int ident) {
        if(name instanceof NCLSystemVariable &&
                ((NCLSystemVariable) name).isParameterized() && hasParameter())
            return name + "(" + param + ")";
        
        
        return name.toString();
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || other instanceof NCLVariable)
            return false;
        
        return getName().equals(((NCLVariable) other).getName());
    }
    
    
    public void mergeVariables(NCLVariable old_var) throws XMLException {
        ArrayList<T> old_refs = old_var.getReferences();
        
        for(T ref : old_refs){
            if(ref instanceof NCLProperty)
                ((NCLProperty) ref).setName(this);
            else if(ref instanceof NCLRule)
                ((NCLRule) ref).setVar(this);
        }
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(T reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(T reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList<T> getReferences() {
        return references;
    }
    
    
    @Override
    @Deprecated
    public void load(Element element) throws XMLException {}

    @Override
    @Deprecated
    public void clean() throws XMLException {
        setParent(null);
        
        param = null;
    }
}
