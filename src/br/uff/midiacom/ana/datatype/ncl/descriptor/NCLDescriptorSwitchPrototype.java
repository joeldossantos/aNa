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
package br.uff.midiacom.ana.datatype.ncl.descriptor;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLDescriptorSwitchPrototype<T extends NCLLayoutDescriptor, P extends NCLElement, Eb extends NCLDescriptorBindRulePrototype> extends NCLIdentifiableElementPrototype<T, P> implements NCLIdentifiableElement<T, P> {

    protected IdentifiableElementList<T, T> descriptors;
    protected ElementList<Eb, T> binds;
    protected T defaultDescriptor;


    /**
     * Construtor do elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch de descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch de descritor não for válido.
     */
    public NCLDescriptorSwitchPrototype(String id) throws XMLException {
        setId(id);
        descriptors = new IdentifiableElementList<T, T>();
        binds = new ElementList<Eb, T>();
    }


    /**
     * Adiciona um descritor ao switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addDescriptor(T descriptor) throws XMLException {
        return descriptors.add(descriptor, (T) this);
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(T descriptor) throws XMLException {
        return descriptors.remove(descriptor);
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param id
     *          identificador do descritor a ser removido.
     * @return
     *          Verdadeiro se o descritor foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(String id) throws XMLException {
        return descriptors.remove(id);
    }


    /**
     * Verifica se o switch de descritor contém um descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser verificado.
     */
    public boolean hasDescriptor(T descriptor) throws XMLException {
        return descriptors.contains(descriptor);
    }


    public boolean hasDescriptor(String id) throws XMLException {
        return descriptors.get(id) != null;
    }


    /**
     * Verifica se o switch de descritor possui algum descritor.
     *
     * @return
     *          verdadeiro se o switch de descritor possuir algum descritor.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Retorna os descritores do switch de descritor.
     *
     * @return
     *          lista contendo os descritores do switch de descritor.
     */
    public IdentifiableElementList<T, T> getDescriptors() {
        return descriptors;
    }


    /**
     * Adiciona um bind ao switch de descritor.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see ArrayList#add
     */
    public boolean addBind(Eb bind) throws XMLException {
        return binds.add(bind, (T) this);
    }


    /**
     * Remove um bind do switch de descritor.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeBind(Eb bind) throws XMLException {
        return binds.remove(bind);
    }


    /**
     * Verifica se o switch de descritor contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch de descritor possui algum bind.
     *
     * @return
     *          verdadeiro se o switch de descritor possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch de descritor.
     *
     * @return
     *          lista contendo os binds do switch de descritor.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }


    /**
     * Determina o descritor padrão do switch de descritor.
     *
     * @param defaultDescriptor
     *          elemento representando o descritor padrão.
     */
    public void setDefaultDescriptor(T defaultDescriptor) {
        this.defaultDescriptor = defaultDescriptor;
    }


    /**
     * Retorna o descritor padrão do switch de descritor.
     *
     * @return
     *          elemento representando o descritor padrão.
     */
    public T getDefaultDescriptor() {
        return defaultDescriptor;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<descriptorSwitch";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasBind()){
            for(Eb bind : binds)
                content += bind.parse(ident + 1);
        }

        if(getDefaultDescriptor() != null)
            content += space + "\t" + "<defaultDescriptor descriptor='" + getDefaultDescriptor().getId() + "'/>\n";

        if(hasDescriptor()){
            for(T descriptor : descriptors)
                content += descriptor.parse(ident + 1);
        }

        content += space + "</descriptorSwitch>\n";


        return content;
    }
}
