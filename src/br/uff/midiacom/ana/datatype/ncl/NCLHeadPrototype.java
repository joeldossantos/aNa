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
package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorBasePrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorBasePrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetaPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetadataPrototype;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionBasePrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportedDocumentBasePrototype;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRuleBasePrototype;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionBasePrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLHeadPrototype<T extends NCLHeadPrototype, P extends NCLElement, I extends XMLElementImpl, Eib extends NCLImportedDocumentBasePrototype, Erl extends NCLRuleBasePrototype, Etb extends NCLTransitionBasePrototype, Erb extends NCLRegionBasePrototype,
        Edb extends NCLDescriptorBasePrototype, Ecb extends NCLConnectorBasePrototype, Em extends NCLMetaPrototype, Emt extends NCLMetadataPrototype>
        extends XMLElementPrototype<T, P, I> implements NCLElement<T, P> {

    protected Eib importedDocumentBase;
    protected Erl ruleBase;
    protected Etb transitionBase;
    protected IdentifiableElementList<Erb, T> regionBases;
    protected Edb descriptorBase;
    protected Ecb connectorBase;
    protected ElementList<Em, T> metas;
    protected ElementList<Emt, T> metadatas;


    /**
     * Construtor do elemento <i>head</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLHeadPrototype() throws XMLException {
        super();
        regionBases = new IdentifiableElementList<Erb, T>();
        metas = new ElementList<Em, T>();
        metadatas = new ElementList<Emt, T>();
    }

    
    /**
     * Atribui uma base de documentos importados ao cabeçalho do documento NCL.
     *
     * @param importedDocumentBase
     *          elemento representando a base de documentos importados a ser utilizada pelo cabeçalho.
     */
    public void setImportedDocumentBase(Eib importedDocumentBase) {
        //Retira o parentesco do importedDocumentBase atual
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(null);
        }

        this.importedDocumentBase = importedDocumentBase;
        //Se importedDocumentBase existe, atribui este como seu parente
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(this);
        }
    }


    /**
     * Retorna a base de documentos importados utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de documentos importados a ser utilizada pelo cabeçalho.
     */

    public Eib getImportedDocumentBase() {
        return importedDocumentBase;
    }


    /**
     * Atribui uma base de regras ao cabeçalho do documento NCL.
     *
     * @param ruleBase
     *          elemento representando a base de regras NCL a ser utilizada pelo cabeçalho.
     */
    public void setRuleBase(Erl ruleBase) {
        //Retira o parentesco do ruleBase atual
        if(this.ruleBase != null){
            this.ruleBase.setParent(null);
        }

        this.ruleBase = ruleBase;
        //Se ruleBase existe, atribui este como seu parente
        if(this.ruleBase != null){
            this.ruleBase.setParent(this);
        }
    }


    /**
     * Retorna a base de regras utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de regras NCL a ser utilizada pelo cabeçalho.
     */

    public Erl getRuleBase() {
        return ruleBase;
    }


    /**
     * Atribui uma base de transições ao cabeçalho do documento NCL.
     *
     * @param transitionBase
     *          elemento representando a base de transições NCL a ser utilizada pelo cabeçalho.
     */
    public void setTransitionBase(Etb transitionBase) {
        //Retira o parentesco do transitionBase atual
        if(this.transitionBase != null){
            this.transitionBase.setParent(null);
        }

        this.transitionBase = transitionBase;
        //Se transitionBase existe, atribui este como seu parente
        if(this.transitionBase != null){
            this.transitionBase.setParent(this);
        }
    }


    /**
     * Retorna a base de transições utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de transições NCL a ser utilizada pelo cabeçalho.
     */

    public Etb getTransitionBase() {
        return transitionBase;
    }


    /**
     * Adiciona uma base de regiões ao cabeçalho do documento NCL.
     *
     * @param regionBase
     *          elemento representando a base de regiões NCL a ser adicionada ao cabeçalho.
     * @return
     *          Verdadeiro se a base de regiões foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRegionBase(Erb regionBase) throws XMLException {
        return regionBases.add(regionBase, (T) this);
    }


    /**
     * Remove uma base de regiões do cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando uma base de regiões a ser removida.
     * @return
     *          Verdadeiro se uma base de regiões foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegionBase(Erb regionBase) throws XMLException {
        return regionBases.remove(regionBase);
    }
    
    
    public boolean removeRegionBase(String id) throws XMLException {
        return regionBases.remove(id);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui uma base de regiões.
     *
     * @param meta
     *          elemento representando a base de regiões a ser verificado.
     * @return
     *          verdadeiro se a base de regiões existir.
     */
    public boolean hasRegionBase(Erb regionBase) throws XMLException {
        return regionBases.contains(regionBase);
    }
    
    
    public boolean hasRegionBase(String id) throws XMLException {
        return regionBases.get(id) != null;
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui alguma base de regiões.
     *
     * @return
     *          verdadeiro se o cabeçalho do documento NCL possuir alguma base de regiões.
     */
    public boolean hasRegionBase() {
        return !regionBases.isEmpty();
    }


    /**
     * Retorna as bases de regiões do cabeçalho do documento NCL.
     *
     * @return
     *          conjunto contendo as bases de regiões do cabeçalho do documento NCL.
     */
    public IdentifiableElementList<Erb, T> getRegionBases() {
        return regionBases;
    }


    /**
     * Atribui uma base de descritores ao cabeçalho do documento NCL.
     *
     * @param descriptorBase
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */    
    public void setDescriptorBase(Edb descriptorBase) {
        //Retira o parentesco do descriptorBase atual
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(null);
        }

        this.descriptorBase = descriptorBase;
        //Se descriptorBase existe, atribui este como seu parente
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(this);
        }
    }

    
    /**
     * Retorna a base de descritores utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */
    public Edb getDescriptorBase() {
        return descriptorBase;
    }


    /**
     * Atribui uma base de conectores ao cabeçalho do documento NCL.
     *
     * @param connectorBase
     *          elemento representando a base de conectores NCL a ser utilizada pelo cabeçalho.
     */
    public void setConnectorBase(Ecb connectorBase) {
        //Retira o parentesco do connectorBase atual
        if(this.connectorBase != null){
            this.connectorBase.setParent(null);
        }

        this.connectorBase = connectorBase;
        //Se connectorBase existe, atribui este como seu parente
        if(this.connectorBase != null){
            this.connectorBase.setParent(this);
        }
    }

    
    /**
     * Retorna a base de conectores utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de conectores NCL a ser utilizada pelo cabeçalho.
     */
    public Ecb getConnectorBase() {
        return connectorBase;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMeta(Em meta) throws XMLException {
        return metas.add(meta, (T) this);
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMeta(Em meta) throws XMLException {
        return metas.remove(meta);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param meta
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMeta(Em meta) throws XMLException {
        return metas.contains(meta);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui algum metadado.
     *
     * @return
     *          verdadeiro se o corpo do cabeçalho NCL possuir algum metadado.
     */
    public boolean hasMeta() {
        return !metas.isEmpty();
    }


    /**
     * Retorna os metadados do cabeçalho do documento NCL.
     *
     * @return
     *          conjunto contendo os metadados do cabeçalho do documento NCL.
     */
    public ElementList<Em, T> getMetas() {
        return metas;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMetadata(Emt metadata) throws XMLException {
        return metadatas.add(metadata, (T) this);
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMetadata(Emt metadata) throws XMLException {
        return metadatas.remove(metadata);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param metadata
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMetadata(Emt metadata) throws XMLException {
        return metadatas.contains(metadata);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui algum metadado.
     *
     * @return
     *          verdadeiro se o corpo do cabeçalho NCL possuir algum metadado.
     */
    public boolean hasMetadata() {
        return !metadatas.isEmpty();
    }


    /**
     * Retorna os metadados do cabeçalho do documento NCL.
     *
     * @return
     *          conjunto contendo os metadados do cabeçalho do documento NCL.
     */
    public ElementList<Emt, T> getMetadatas() {
        return metadatas;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
                
        content = space + "<head>\n";    

        if(getImportedDocumentBase() != null)
            content += getImportedDocumentBase().parse(ident + 1);
        if(getRuleBase() != null)
            content += getRuleBase().parse(ident + 1);
        if(getTransitionBase() != null)
            content += getTransitionBase().parse(ident + 1);
        if(hasRegionBase()){
            for(Erb base : regionBases)
                content += base.parse(ident + 1);
        }
        if(getDescriptorBase() != null)
            content += getDescriptorBase().parse(ident + 1);
        if(getConnectorBase() != null)
            content += getConnectorBase().parse(ident + 1);
        if(hasMeta()){
            for(Em meta : metas)
                content += meta.parse(ident + 1);
        }
        if(hasMetadata()){
            for(Emt metadata : metadatas)
                content += metadata.parse(ident + 1);
        }
        
        content += space + "</head>\n";
        
        return content;
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
