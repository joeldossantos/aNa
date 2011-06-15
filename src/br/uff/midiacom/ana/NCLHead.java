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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>head</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define o cabeçalho de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLHead<IB extends NCLImportedDocumentBase, RLB extends NCLRuleBase, TB extends NCLTransitionBase, RB extends NCLRegionBase,
        DB extends NCLDescriptorBase, CB extends NCLConnectorBase, M extends NCLMeta, MT extends NCLMetadata> extends NCLElement {

    private IB importedDocumentBase;
    private RLB ruleBase;
    private TB transitionBase;
    private Set<RB> regionBases = new TreeSet<RB>();
    private DB descriptorBase;
    private CB connectorBase;
    private Set<M> metas = new TreeSet<M>();
    private Set<MT> metadatas = new TreeSet<MT>();


    /**
     * Construtor do elemento <i>head</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLHead() {}


    /**
     * Construtor do elemento <i>head</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLHead(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

    
    /**
     * Atribui uma base de documentos importados ao cabeçalho do documento NCL.
     *
     * @param importedDocumentBase
     *          elemento representando a base de documentos importados a ser utilizada pelo cabeçalho.
     */
    public void setImportedDocumentBase(IB importedDocumentBase) {
        //Retira o parentesco do importedDocumentBase atual
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(null);
            notifyRemoved(NCLElementSets.IMPORTEDDOCUMENTBASE, this.importedDocumentBase);
        }

        this.importedDocumentBase = importedDocumentBase;
        //Se importedDocumentBase existe, atribui este como seu parente
        if(this.importedDocumentBase != null){
            this.importedDocumentBase.setParent(this);
            notifyInserted(NCLElementSets.IMPORTEDDOCUMENTBASE, importedDocumentBase);
        }

    }


    /**
     * Retorna a base de documentos importados utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de documentos importados a ser utilizada pelo cabeçalho.
     */

    public IB getImportedDocumentBase() {
        return importedDocumentBase;
    }


    /**
     * Atribui uma base de regras ao cabeçalho do documento NCL.
     *
     * @param ruleBase
     *          elemento representando a base de regras NCL a ser utilizada pelo cabeçalho.
     */
    public void setRuleBase(RLB ruleBase) {
        //Retira o parentesco do ruleBase atual
        if(this.ruleBase != null){
            this.ruleBase.setParent(null);
            notifyRemoved(NCLElementSets.RULEBASE, this.ruleBase);
        }

        this.ruleBase = ruleBase;
        //Se ruleBase existe, atribui este como seu parente
        if(this.ruleBase != null){
            this.ruleBase.setParent(this);
            notifyInserted(NCLElementSets.RULEBASE, ruleBase);
        }
    }


    /**
     * Retorna a base de regras utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de regras NCL a ser utilizada pelo cabeçalho.
     */

    public RLB getRuleBase() {
        return ruleBase;
    }


    /**
     * Atribui uma base de transições ao cabeçalho do documento NCL.
     *
     * @param transitionBase
     *          elemento representando a base de transições NCL a ser utilizada pelo cabeçalho.
     */
    public void setTransitionBase(TB transitionBase) {
        //Retira o parentesco do transitionBase atual
        if(this.transitionBase != null){
            this.transitionBase.setParent(null);
            notifyRemoved(NCLElementSets.TRANSITIONBASE, this.transitionBase);
        }

        this.transitionBase = transitionBase;
        //Se transitionBase existe, atribui este como seu parente
        if(this.transitionBase != null){
            this.transitionBase.setParent(this);
            notifyInserted(NCLElementSets.TRANSITIONBASE, transitionBase);
        }
    }


    /**
     * Retorna a base de transições utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de transições NCL a ser utilizada pelo cabeçalho.
     */

    public TB getTransitionBase() {
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
    public boolean addRegionBase(RB regionBase) {
        if(regionBases.add(regionBase)){
            //Se base de regiões existe, atribui este como seu parente
            if(regionBase != null)
                regionBase.setParent(this);

            notifyInserted(NCLElementSets.REGIONBASE, regionBase);
            return true;
        }
        return false;
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
    public boolean removeRegionBase(RB regionBase) {
        if(regionBases.remove(regionBase)){
            //Se meta existe, retira o seu parentesco
            if(regionBase != null)
                regionBase.setParent(null);

            notifyRemoved(NCLElementSets.REGIONBASE, regionBase);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui uma base de regiões.
     *
     * @param meta
     *          elemento representando a base de regiões a ser verificado.
     * @return
     *          verdadeiro se a base de regiões existir.
     */
    public boolean hasRegionBase(RB regionBase) {
        return regionBases.contains(regionBase);
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
    public Set<RB> getRegionBases() {
        return regionBases;
    }


    /**
     * Atribui uma base de descritores ao cabeçalho do documento NCL.
     *
     * @param descriptorBase
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */    
    public void setDescriptorBase(DB descriptorBase) {
        //Retira o parentesco do descriptorBase atual
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(null);
            notifyRemoved(NCLElementSets.DESCRIPTORBASE, this.descriptorBase);
        }

        this.descriptorBase = descriptorBase;
        //Se descriptorBase existe, atribui este como seu parente
        if(this.descriptorBase != null){
            this.descriptorBase.setParent(this);
            notifyInserted(NCLElementSets.DESCRIPTORBASE, descriptorBase);
        }
    }

    
    /**
     * Retorna a base de descritores utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */
    public DB getDescriptorBase() {
        return descriptorBase;
    }


    /**
     * Atribui uma base de conectores ao cabeçalho do documento NCL.
     *
     * @param connectorBase
     *          elemento representando a base de conectores NCL a ser utilizada pelo cabeçalho.
     */
    public void setConnectorBase(CB connectorBase) {
        //Retira o parentesco do connectorBase atual
        if(this.connectorBase != null){
            this.connectorBase.setParent(null);
            notifyRemoved(NCLElementSets.CONNECTORBASE, this.connectorBase);
        }

        this.connectorBase = connectorBase;
        //Se connectorBase existe, atribui este como seu parente
        if(this.connectorBase != null){
            this.connectorBase.setParent(this);
            notifyInserted(NCLElementSets.CONNECTORBASE, connectorBase);
        }
    }

    
    /**
     * Retorna a base de conectores utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de conectores NCL a ser utilizada pelo cabeçalho.
     */
    public CB getConnectorBase() {
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
    public boolean addMeta(M meta) {
        if(metas.add(meta)){
            //Se meta existe, atribui este como seu parente
            if(meta != null)
                meta.setParent(this);

            notifyInserted(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
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
    public boolean removeMeta(M meta) {
        if(metas.remove(meta)){
            //Se meta existe, retira o seu parentesco
            if(meta != null)
                meta.setParent(null);

            notifyRemoved(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param meta
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMeta(M meta) {
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
    public Set<M> getMetas() {
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
    public boolean addMetadata(MT metadata) {
        if(metadatas.add(metadata)){
            //Se metadata existe, atribui este como seu parente
            if(metadata != null)
                metadata.setParent(this);

            notifyInserted(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
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
    public boolean removeMetadata(MT metadata) {
        if(metadatas.remove(metadata)){
            //Se metadata existe, retira o seu parentesco
            if(metadata != null)
                metadata.setParent(null);

            notifyRemoved(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param metadata
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMetadata(MT metadata) {
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
    public Set<MT> getMetadatas() {
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
            for(RB base : regionBases)
                content += base.parse(ident + 1);
        }
        if(getDescriptorBase() != null)
            content += getDescriptorBase().parse(ident + 1);
        if(getConnectorBase() != null)
            content += getConnectorBase().parse(ident + 1);
        if(hasMeta()){
            for(M meta : metas)
                content += meta.parse(ident + 1);
        }
        if(hasMetadata()){
            for(MT metadata : metadatas)
                content += metadata.parse(ident + 1);
        }
        
        content += space + "</head>\n";
        
        return content;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(localName.equals("head")){
            cleanWarnings();
            cleanErrors();
        }
        else if(localName.equals("importedDocumentBase")){
            setImportedDocumentBase(createImportedDocumentBase());
            getImportedDocumentBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("ruleBase")){
            setRuleBase(createRuleBase());
            getRuleBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("transitionBase")){
            setTransitionBase(createTransitionBase());
            getTransitionBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("regionBase")){
            RB base = createRegionBase();
            addRegionBase(base);
            base.startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("descriptorBase")){
            setDescriptorBase(createDescriptorBase());
            getDescriptorBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("connectorBase")){
            setConnectorBase(createConnectorBase());
            getConnectorBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("meta")){
            M child = createMeta();
            child.startElement(uri, localName, qName, attributes);
            addMeta(child);
        }
        else if(localName.equals("metadata")){
            MT child = createMetadata();
            child.startElement(uri, localName, qName, attributes);
            addMetadata(child);
        }
    }


    @Override
    public void endDocument() {
        if(getImportedDocumentBase() != null){
            getImportedDocumentBase().endDocument();
            addWarning(getImportedDocumentBase().getWarnings());
            addError(getImportedDocumentBase().getErrors());
        }
        if(getRuleBase() != null){
            getRuleBase().endDocument();
            addWarning(getRuleBase().getWarnings());
            addError(getRuleBase().getErrors());
        }
        if(getTransitionBase() != null){
            getTransitionBase().endDocument();
            addWarning(getTransitionBase().getWarnings());
            addError(getTransitionBase().getErrors());
        }
        if(hasRegionBase()){
            for(RB base : regionBases){
                base.endDocument();
                addWarning(base.getWarnings());
                addError(base.getErrors());
            }
        }
        if(getDescriptorBase() != null){
            getDescriptorBase().endDocument();
            addWarning(getDescriptorBase().getWarnings());
            addError(getDescriptorBase().getErrors());
        }
        if(getConnectorBase() != null){
            getConnectorBase().endDocument();
            addWarning(getConnectorBase().getWarnings());
            addError(getConnectorBase().getErrors());
        }
        if(hasMeta()){
            for(M meta : metas){
                meta.endDocument();
                addWarning(meta.getWarnings());
                addError(meta.getErrors());
            }
        }
        if(hasMetadata()){
            for(MT metadata : metadatas){
                metadata.endDocument();
                addWarning(metadata.getWarnings());
                addError(metadata.getErrors());
            }
        }
    }


    /**
     * Função de criação do elemento filho <i>importedDocumentBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>importedDocumentBase</i>.
     */
    protected IB createImportedDocumentBase() {
        return (IB) new NCLImportedDocumentBase(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>ruleBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>ruleBase</i>.
     */
    protected RLB createRuleBase() {
        return (RLB) new NCLRuleBase(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>transitionBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>transitionBase</i>.
     */
    protected TB createTransitionBase() {
        return (TB) new NCLTransitionBase(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>regionBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>regionBase</i>.
     */
    protected RB createRegionBase() {
        return (RB) new NCLRegionBase(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>descriptorBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>descriptorBase</i>.
     */
    protected DB createDescriptorBase() {
        return (DB) new NCLDescriptorBase(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>connectorBase</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>connectorBase</i>.
     */
    protected CB createConnectorBase() {
        return (CB) new NCLConnectorBase(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>meta</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>meta</i>.
     */
    protected M createMeta() {
        return (M) new NCLMeta(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>metadata</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>metadata</i>.
     */
    protected MT createMetadata() {
        return (MT) new NCLMetadata(getReader(), this);
    }
}
