package br.uff.midiacom.ana;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
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
    private RB regionBase;
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
        if(this.importedDocumentBase != null)
            this.importedDocumentBase.setParent(null);

        this.importedDocumentBase = importedDocumentBase;
        //Se importedDocumentBase existe, atribui este como seu parente
        if(this.importedDocumentBase != null)
            this.importedDocumentBase.setParent(this);
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
        if(this.ruleBase != null)
            this.ruleBase.setParent(null);

        this.ruleBase = ruleBase;
        //Se ruleBase existe, atribui este como seu parente
        if(this.ruleBase != null)
            this.ruleBase.setParent(this);
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
        if(this.transitionBase != null)
            this.transitionBase.setParent(null);

        this.transitionBase = transitionBase;
        //Se transitionBase existe, atribui este como seu parente
        if(this.transitionBase != null)
            this.transitionBase.setParent(this);
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
     * Atribui uma base de regiões ao cabeçalho do documento NCL.
     *
     * @param regionBase
     *          elemento representando a base de regiões NCL a ser utilizada pelo cabeçalho.
     */
    public void setRegionBase(RB regionBase) {
        //Retira o parentesco do regionBase atual
        if(this.regionBase != null)
            this.regionBase.setParent(null);

        this.regionBase = regionBase;
        //Se regionBase existe, atribui este como seu parente
        if(this.regionBase != null)
            this.regionBase.setParent(this);
    }


    /**
     * Retorna a base de regiões utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de regiões NCL a ser utilizada pelo cabeçalho.
     */
    
    public RB getRegionBase() {
        return regionBase;
    }


    /**
     * Atribui uma base de descritores ao cabeçalho do documento NCL.
     *
     * @param descriptorBase
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */    
    public void setDescriptorBase(DB descriptorBase) {
        //Retira o parentesco do descriptorBase atual
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(null);

        this.descriptorBase = descriptorBase;
        //Se descriptorBase existe, atribui este como seu parente
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(this);
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
        if(this.connectorBase != null)
            this.connectorBase.setParent(null);

        this.connectorBase = connectorBase;
        //Se connectorBase existe, atribui este como seu parente
        if(this.connectorBase != null)
            this.connectorBase.setParent(this);
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
     *          objeto Iterable contendo os metadados do cabeçalho do documento NCL.
     */
    public Iterable<M> getMetas() {
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
     *          objeto Iterable contendo os metadados do cabeçalho do documento NCL.
     */
    public Iterable<MT> getMetadatas() {
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
        if(getRegionBase() != null)
            content += getRegionBase().parse(ident + 1);
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


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        // Cabecalho nao pode ser vazio
        if(getImportedDocumentBase() == null && getRuleBase() == null && getTransitionBase() == null &&
                getRegionBase() == null && getDescriptorBase() == null && getConnectorBase() == null && !hasMeta() && !hasMetadata()){
            addWarning("Cabeçalho do documento NCL vazio.");
            valid = false;
        }


        if(getImportedDocumentBase() != null){
            valid &= getImportedDocumentBase().validate();
            addWarning(getImportedDocumentBase().getWarnings());
            addError(getImportedDocumentBase().getErrors());
        }
        if(getRuleBase() != null){
            valid &= getRuleBase().validate();
            addWarning(getRuleBase().getWarnings());
            addError(getRuleBase().getErrors());
        }
        if(getTransitionBase() != null){
            valid &= getTransitionBase().validate();
            addWarning(getTransitionBase().getWarnings());
            addError(getTransitionBase().getErrors());
        }
        if(getRegionBase() != null){
            valid &= getRegionBase().validate();
            addWarning(getRegionBase().getWarnings());
            addError(getRegionBase().getErrors());
        }
        if(getDescriptorBase() != null){
            valid &= getDescriptorBase().validate();
            addWarning(getDescriptorBase().getWarnings());
            addError(getDescriptorBase().getErrors());
        }
        if(getConnectorBase() != null){
            valid &= getConnectorBase().validate();
            addWarning(getConnectorBase().getWarnings());
            addError(getConnectorBase().getErrors());
        }
        if(hasMeta()){
            for(M meta : metas){
                valid &= meta.validate();
                addWarning(meta.getWarnings());
                addError(meta.getErrors());
            }
        }
        if(hasMetadata()){
            for(MT metadata : metadatas){
                valid &= metadata.validate();
                addWarning(metadata.getWarnings());
                addError(metadata.getErrors());
            }
        }

        return valid;
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
            setRegionBase(createRegionBase());
            getRegionBase().startElement(uri, localName, qName, attributes);
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
        if(getRegionBase() != null){
            getRegionBase().endDocument();
            addWarning(getRegionBase().getWarnings());
            addError(getRegionBase().getErrors());
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
