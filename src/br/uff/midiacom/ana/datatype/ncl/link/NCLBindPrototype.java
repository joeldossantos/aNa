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
package br.uff.midiacom.ana.datatype.ncl.link;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLRolePrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLInterface;
import br.uff.midiacom.ana.datatype.ncl.node.NCLNode;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class NCLBindPrototype<T extends NCLBindPrototype, P extends NCLElement, I extends XMLElementImpl, Er extends NCLRolePrototype, En extends NCLNode, Ei extends NCLInterface, Ed extends NCLLayoutDescriptor, Ep extends NCLParamPrototype>
        extends XMLElementPrototype<T, P, I> implements NCLElement<T, P>{

    protected Er role;
    protected En component;
    protected Ei interfac;
    protected Ed descriptor;
    protected ElementList<Ep, T> bindParams;
    

    /**
     * Construtor do elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLBindPrototype() throws XMLException {
        super();
        bindParams = new ElementList<Ep, T>();
    }


    /**
     * Atribui um papel ao bind.
     * 
     * @param role
     *          elemento representando o papel ao qual o bind será associado.
     */
    public void setRole(Er role) {
        this.role = role;
    }
    
    
    /**
     * Retorna o papel relacionado ao bind.
     * 
     * @return
     *          elemento representando o papel ao qual o bind será associado.
     */
    public Er getRole() {
        return role;
    }
    
    
    /**
     * Atribui um nó ao bind.
     * 
     * @param component
     *          elemento representando o nó mapeado pelo bind.
     */
    public void setComponent(En component) {
        this.component = component;
    }
    
    
    /**
     * Retorna o nó atribuído ao bind.
     * 
     * @return
     *          elemento representando o nó mapeado pelo bind.
     */
    public En getComponent() {
        return component;
    }
    
    
    /**
     * Determina a interface do nó atribuído ao bind.
     * 
     * @param interfac
     *          elemento representando a interface do nó.
     */
    public void setInterface(Ei interfac) {
        this.interfac = interfac;
    }
    
    
    /**
     * Retorna a interface do nó atribuído ao bind.
     * 
     * @return
     *          elemento representando a interface do nó.
     */
    public Ei getInterface() {
        return interfac;
    }
    
    
    /**
     * Atribui um descritor ao bind.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser atribuido.
     */
    public void setDescriptor(Ed descriptor) {
        this.descriptor = descriptor;
    }
    
    
    /**
     * Retorna o descritor atribuido ao bind.
     * 
     * @return
     *          elemento representando o descritor a ser atribuido.
     */
    public Ed getDescriptor() {
        return descriptor;
    }
    
    
    /**
     * Adiciona um parâmetro ao bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBindParam(Ep param) throws XMLException {
        return bindParams.add(param, (T) this);
    }


    /**
     * Remove um parâmetro do bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBindParam(Ep param) throws XMLException {
        return bindParams.remove(param);
    }


    /**
     * Verifica se o bind possui um parâmetro.
     *
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasBindParam(Ep param) throws XMLException {
        return bindParams.contains(param);
    }


    /**
     * Verifica se o bind possui algum parâmetro.
     *
     * @return
     *          verdadeiro se o bind possui algum parâmetro.
     */
    public boolean hasBindParam() {
        return !bindParams.isEmpty();
    }


    /**
     * Retorna os parâmetros do bind.
     *
     * @return
     *          lista contendo os parâmetros do bind.
     */
    public ElementList<Ep, T> getBindParams() {
        return bindParams;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <bind> element and attributes declaration
        content = space + "<bind";
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        if(getDescriptor() != null)
            content += " descriptor='" + getDescriptor().getId() + "'";
        
        // <bind> element content
        if(hasBindParam()){
            content += ">\n";

            for(Ep param : bindParams)
                content += param.parse(ident + 1);
            
            content += space + "</bind>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    public boolean compare(T other) {
        boolean comp = true;

        String this_bind, other_bind;

        // Compara pelo role
        if(getRole() == null) this_bind = ""; else this_bind = getRole().getName();
        if(other.getRole() == null) other_bind = ""; else other_bind = other.getRole().getName();
        comp &= this_bind.equals(other_bind);

        // Compara pelo componente
        if(getComponent() != null && other.getComponent() != null)
            comp &= getComponent().compare(other.getComponent());

        // Compara pela interface
        if(getInterface() != null && other.getInterface() != null)
            comp &= getInterface().compare(other.getInterface());

        // Compara pelo descritor
        if(getDescriptor() != null && other.getDescriptor() != null)
            comp &= getDescriptor().compare(other.getDescriptor());

        // Compara o número de parâmetros
        comp &= bindParams.size() == other.getBindParams().size();

        // Compara os parâmetros
        Iterator it = other.getBindParams().iterator();
        for(Ep param : bindParams){
            if(!it.hasNext())
                continue;
            Ep other_param = (Ep) it.next();
            comp = param.compare(other_param);
            if(comp)
                break;
        }


        return comp;
    }
}
