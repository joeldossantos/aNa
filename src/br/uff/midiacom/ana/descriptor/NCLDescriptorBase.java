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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import br.uff.midiacom.ana.datatype.NCLImportType;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>descriptorBase</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma base de descritores de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLDescriptorBase<D extends NCLLayoutDescriptor, I extends NCLImport> extends NCLIdentifiableElement {

    private Set<D> descriptors = new TreeSet<D>();
    private Set<I> imports = new TreeSet<I>();


    /**
     * Construtor do elemento <i>descriptorBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLDescriptorBase() {}


    /**
     * Construtor do elemento <i>descriptorBase</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLDescriptorBase(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Adiciona um descritor à base de descritores.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addDescriptor(D descriptor) {
        if(descriptors.add(descriptor)){
            //Se descriptor existe, atribui este como seu parente
            if(descriptor != null)
                descriptor.setParent(this);

            notifyInserted(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
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
    public boolean removeDescriptor(String id) {
        for(D descriptor : descriptors){
            if(descriptor.getId().equals(id))
                return removeDescriptor(descriptor);
        }
        return false;
    }
    

    /**
     * Remove um descritor da base de descritores.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(D descriptor) {
        if(descriptors.remove(descriptor)){
            //Se descriptor existe, retira o seu parentesco
            if(descriptor != null)
                descriptor.setParent(null);

            notifyRemoved(NCLElementSets.DESCRIPTORS, descriptor);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a base de descritores contém um descritor.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser verificado.
     */
    public boolean hasDescriptor(D descriptor) {
        return descriptors.contains(descriptor);
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
    public Set<D> getDescriptors() {
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
    public boolean addImportBase(I importBase) {
        if(imports.add(importBase)){
            //Se importBase existe, atribui este como seu parente
            if(importBase != null)
                importBase.setParent(this);

            notifyInserted(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Remove um importador de base da base de descritores.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(I importBase) {
        if(imports.remove(importBase)){
            //Se importBase existe, retira o seu parentesco
            if(importBase != null)
                importBase.setParent(null);

            notifyRemoved(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a base de descritores contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
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
    public Set<I> getImportBases() {
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
                for(I imp : imports)
                    content += imp.parse(ident + 1);
            }

            if(hasDescriptor()){
                for(D descriptor : descriptors)
                    content += descriptor.parse(ident + 1);
            }
            
            content += space + "</descriptorBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("descriptorBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("importBase")){
                I child = createImportBase();
                child.startElement(uri, localName, qName, attributes);
                addImportBase(child);
            }
            else if(localName.equals("descriptor")){
                D child = createDescriptor();
                child.startElement(uri, localName, qName, attributes);
                addDescriptor(child);
            }
            else if(localName.equals("descriptorSwitch")){
                D child = createDescriptorSwitch();
                child.startElement(uri, localName, qName, attributes);
                addDescriptor(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasImportBase()){
            for(I imp : imports){
                imp.endDocument();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }
        if(hasDescriptor()){
            for(D descriptor : descriptors){
                descriptor.endDocument();
                addWarning(descriptor.getWarnings());
                addError(descriptor.getErrors());
            }
        }
    }


    /**
     * Função de criação do elemento filho <i>importBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>importBase</i>.
     */
    protected I createImportBase() {
        return (I) new NCLImport(NCLImportType.BASE, getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>descriptor</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>descriptor</i>.
     */
    protected D createDescriptor() {
        return (D) new NCLDescriptor(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>descriptorSwitch</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>descriptorSwitch</i>.
     */
    protected D createDescriptorSwitch() {
        return (D) new NCLDescriptorSwitch(getReader(), this);
    }
}
