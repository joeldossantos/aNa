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
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLDescriptorBasePrototype<T extends NCLDescriptorBasePrototype, P extends NCLElement, I extends XMLElementImpl, El extends NCLLayoutDescriptor, Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {

    protected IdentifiableElementList<El, T> descriptors;
    protected ElementList<Ei, T> imports;


    /**
     * Construtor do elemento <i>descriptorBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLDescriptorBasePrototype() {
        descriptors = new IdentifiableElementList<El, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Adiciona um descritor à base de descritores.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addDescriptor(El descriptor) throws XMLException {
        return descriptors.add(descriptor, (T) this);
    }


    /**
     * Remove um descritor da base de descritores.
     *
     * @param descriptor
     *          elemento representando o descritor a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(El descriptor) throws XMLException {
        return descriptors.remove(descriptor);
    }


    /**
     * Remove um descritor da base de descritores.
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
     * Verifica se a base de descritores contém um descritor.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser verificado.
     */
    public boolean hasDescriptor(El descriptor) throws XMLException {
        return descriptors.contains(descriptor);
    }


    public boolean hasDescriptor(String id) throws XMLException {
        return descriptors.get(id) != null;
    }


    /**
     * Verifica se a base de descritores possui algum descritor.
     * 
     * @return
     *          verdadeiro se a base de descritores possuir algum descritor.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Retorna os descritores da base de descritores.
     *
     * @return
     *          lista contendo os descritores da base de descritores.
     */
    public IdentifiableElementList<El, T> getDescriptors() {
        return descriptors;
    }


    /**
     * Adiciona um importador de base à base de descritores.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(Ei importBase) throws XMLException {
        return imports.add(importBase, (T) this);
    }


    /**
     * Remove um importador de base da base de descritores.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de descritores contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de descritores possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de descritores possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de descritores.
     *
     * @return
     *          lista contendo os importadores de base da base de descritores.
     */
    public ElementList<Ei, T> getImportBases() {
        return imports;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<descriptorBase";
        if(getId() != null)
            content += " id='" + getId() + "'";

        if(hasDescriptor() || hasImportBase()){
            content += ">\n";

            if(hasImportBase()){
                for(Ei imp : imports)
                    content += imp.parse(ident + 1);
            }

            if(hasDescriptor()){
                for(El descriptor : descriptors)
                    content += descriptor.parse(ident + 1);
            }
            
            content += space + "</descriptorBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }
}
