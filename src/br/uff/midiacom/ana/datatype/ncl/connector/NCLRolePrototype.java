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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.enums.NCLDefaultActionRole;
import br.uff.midiacom.ana.datatype.enums.NCLDefaultConditionRole;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;
import org.w3c.dom.Element;


/**
 * Class that represent the name of a role. This name can be a default name or
 * a user defined name.
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public abstract class NCLRolePrototype<T extends NCLRolePrototype,
                              P extends NCLElement,
                              I extends NCLElementImpl>
        extends NCLElementPrototype<T, P, I>
        implements ReferredElement<ReferenceType> {

    protected String name;
    protected NCLDefaultConditionRole cname;
    protected NCLDefaultActionRole aname;

    protected ItemList<ReferenceType> references;
    
    
    /**
     * Role constructor. Creates the role name from a string. If the string
     * represents a default role name, it is represented as a value from the
     * enumerations <i>NCLDefaultConditionRole</i> or <i>NCLDefaultActionRole</i>.
     * The name is required and can not be set to <i>null</i>.
     * 
     * @param name
     *          string representing the role name.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public NCLRolePrototype(String name) throws XMLException {
        super();
        setName(name);
        references = new ItemList<ReferenceType>();
    }


    /**
     * Role constructor. Creates the role name as a default condition role. The
     * name is required and can not be set to <i>null</i>. The possible default
     * condition names are defined in the enumeration <i>NCLDefaultConditionRole</i>.
     *
     * @param name
     *          value representing the role name from the enumeration
     *          <i>NCLDefaultConditionRole</i>.
     * @throws XMLException
     *          if the name is null.
     */
    public NCLRolePrototype(NCLDefaultConditionRole name) throws XMLException {
        super();
        setName(name);
        references = new ItemList<ReferenceType>();
    }


    /**
     * Role constructor. Creates the role name as a default action role. The name
     * is required and can not be set to <i>null</i>. The possible default action
     * names are defined in the enumeration <i>NCLDefaultActionRole</i>.
     *
     * @param name
     *          value representing the role name from the enumeration
     *          <i>NCLDefaultActionRole</i>.
     * @throws XMLException
     *          if the name is null.
     */
    public NCLRolePrototype(NCLDefaultActionRole name) throws XMLException {
        super();
        setName(name);
        references = new ItemList<ReferenceType>();
    }


    /**
     * Sets the role name from a string. If the string represents a default
     * role name, it is represented as a value from the enumerations
     * <i>NCLDefaultConditionRole</i> or <i>NCLDefaultActionRole</i>. The name
     * is required and can not be set to <i>null</i>.
     *
     * @param name
     *          string representing the role name.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public void setName(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        if("".equals(name.trim()))
            throw new XMLException("Empty name String");

        
        for(NCLDefaultConditionRole role : NCLDefaultConditionRole.values()){
            if(name.equals(role.toString())){
                setName(role);
                return;
            }
        }
        for(NCLDefaultActionRole role : NCLDefaultActionRole.values()){
            if(name.equals(role.toString())){
                setName(role);
                return;
            }
        }
        
        this.name = name;
        this.cname = null;
        this.aname = null;
    }


    /**
     * Sets the role name as a default condition name. The name is required and
     * can not be set to <i>null</i>. The possible default condition names are
     * defined in the enumeration <i>NCLDefaultConditionRole</i>.
     *
     * @param name
     *          value representing the role name from the enumeration
     *          <i>NCLDefaultConditionRole</i>.
     * @throws XMLException
     *          if the name is null.
     */
    public void setName(NCLDefaultConditionRole name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        this.cname = name;
        this.name = null;
        this.aname = null;
    }


    /**
     * Sets the role name as a default action name. The name is required and
     * can not be set to <i>null</i>. The possible default action names are
     * defined in the enumeration <i>NCLDefaultActionRole</i>.
     *
     * @param name
     *          value representing the role name from the enumeration
     *          <i>NCLDefaultActionRole</i>.
     * @throws XMLException
     *          if the name is null.
     */
    public void setName(NCLDefaultActionRole name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        aname = name;
        this.name = null;
        this.cname= null;
    }
    
    
    /**
     * Returns the string that represents the role name.
     *
     * @return
     *          string representing the role name.
     */
    public String getName() {
        if(cname != null)
            return cname.toString();
        if(aname != null)
            return aname.toString();
        else
            return name;
    }


    /**
     * Returns the role name as a default condition name or <i>null</i> if the
     * name is not a default condition name. The possible default condition names
     * are defined in the enumeration <i>NCLDefaultConditionRole</i>.
     *
     * @return
     *          value representing the role name from the enumeration
     *          <i>NCLDefaultConditionRole</i> or <i>null</i> if the name is not
     *          a default condition name.
     */
    public NCLDefaultConditionRole getConditionName() {
        return cname;
    }


    /**
     * Returns the role name as a default action name or <i>null</i> if the name
     * is not a default action name. The possible default action names are defined
     * in the enumeration <i>NCLDefaultConditionRole</i>.
     *
     * @return
     *          value representing the role name from the enumeration
     *          <i>NCLDefaultActionRole</i> or <i>null</i> if the name is not
     *          a default action name.
     */
    public NCLDefaultActionRole getActionName() {
        return aname;
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


    public boolean compare(T other) {
        return getName().equals(other.getName());
    }

    
    @Deprecated
    public String parse(int ident) {
        return getName();
    }

    
    @Deprecated
    public void load(Element element) throws XMLException {
    }
}
