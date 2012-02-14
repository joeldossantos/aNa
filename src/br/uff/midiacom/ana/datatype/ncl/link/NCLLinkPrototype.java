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
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCausalConnectorPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class NCLLinkPrototype<T extends NCLLinkPrototype, P extends NCLElement, I extends NCLElementImpl, Ep extends NCLParamPrototype, Eb extends NCLBindPrototype, Ec extends NCLCausalConnectorPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P>{

    protected Ec xconnector;
    protected ElementList<Ep, T> linkParams;
    protected ElementList<Eb, T> binds;
    

    /**
     * Construtor do elemento <i>link</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLLinkPrototype() throws XMLException {
        super();
        linkParams = new ElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
    }


    /**
     * Atribui um conector ao link.
     * 
     * @param xconnector
     *          conector a ser atribuido ao link.
     */
    public void setXconnector(Ec xconnector) {
        this.xconnector = xconnector;
    }
    
    
    /**
     * Retorna o conector do link.
     * 
     * @return
     *          conector atribuido ao link.
     */
    public Ec getXconnector() {
        return xconnector;
    }
    
    
    /**
     * Adiciona um parâmetro ao link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLinkParam(Ep param) throws XMLException {
        return linkParams.add(param, (T) this);
    }
    
    
    /**
     * Remove um parâmetro do link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLinkParam(Ep param) throws XMLException {
        return linkParams.remove(param);
    }
    
    
    /**
     * Verifica se o link possui um parâmetro.
     * 
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasLinkParam(Ep param) throws XMLException {
        return linkParams.contains(param);
    }
    
    
    /**
     * Verifica se o link possui algum parâmetro.
     * 
     * @return
     *          verdadeiro se o link possui algum parâmetro.
     */
    public boolean hasLinkParam() {
        return !linkParams.isEmpty();
    }
    
    
    /**
     * Retorna os parâmetros do link.
     * 
     * @return
     *          lista contendo os parâmetros do link.
     */
    public ElementList<Ep, T> getLinkParams() {
        return linkParams;
    }
    
    
    /**
     * Adiciona um bind ao link.
     * 
     * @param bind
     *          elemento representando o bind a ser adicionado.
     * @return
     *          verdadeiro se o bind for adicionado.
     *
     * @see ArrayList#add
     */
    public boolean addBind(Eb bind) throws XMLException {
        return binds.add(bind, (T) this);
    }
    
    
    /**
     * Remove um bind do link.
     * 
     * @param bind
     *          elemento representando o bind a ser removido.
     * @return
     *          verdadeiro se o bind for removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeBind(Eb bind) throws XMLException {
        return binds.remove(bind);
    }
    
    
    /**
     * Verifica se o link possui um bind.
     * 
     * @param bind
     *          elemento representando o bind a ser verificado.
     * @return
     *          verdadeiro se o bind existir.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifica se o link possui algum bind.
     *
     * @return
     *          verdadeiro se o link possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }
    
    
    /**
     * Retorna os binds do link
     * 
     * @return
     *          lista contendo os binds do link.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <link> element and attributes declaration
        content = space + "<link";
        content += parseAttributes();
        content += ">\n";
        
        // <link> element content
        content += parseElements(ident + 1);

        // <link> element end declaration
        content += space + "</link>\n";
        
        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseXconnector();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseLinkParams(ident);
        content += parseBinds(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseXconnector() {
        Ec aux = getXconnector();
        if(aux != null)
            return " xconnector='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseLinkParams(int ident) {
        if(!hasLinkParam())
            return "";
        
        String content = "";
        for(Ep aux : linkParams)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseBinds(int ident) {
        if(!hasBind())
            return "";
        
        String content = "";
        for(Eb aux : binds)
            content += aux.parse(ident);
        
        return content;
    }


    public boolean compare(T other) {
        boolean comp = true;

        // Compara pelo xconnector
        if(getXconnector() != null && other.getXconnector() != null)
            comp &= getXconnector().compare(other.getXconnector());

        // Compara o número de parâmetros
        comp &= linkParams.size() == other.getLinkParams().size();

        // Compara o número de binds
        comp &= binds.size() == other.getBinds().size();

        // Compara os parâmetros
        Iterator it = other.getLinkParams().iterator();
        for(Ep param : linkParams){
            Ep other_param = (Ep) it.next();
            comp &= param.compare(other_param);
            if(comp)
                break;
        }

        // Compara os binds
        it = other.getBinds().iterator();
        for(Eb bind : binds){
            if(!it.hasNext())
                continue;
            Eb other_bind = (Eb) it.next();
            comp &= bind.compare(other_bind);
            if(comp)
                break;
        }


        return comp;
    }
}
