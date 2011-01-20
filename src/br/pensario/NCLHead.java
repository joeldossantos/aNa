package br.pensario;

import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.meta.NCLMeta;
import br.pensario.meta.NCLMetadata;
import br.pensario.region.NCLRegionBase;
import br.pensario.reuse.NCLImportedDocumentBase;
import br.pensario.rule.NCLRuleBase;
import br.pensario.transition.NCLTransitionBase;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>head</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define o cabeçalho de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
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
        this.importedDocumentBase = importedDocumentBase;
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
        this.ruleBase = ruleBase;
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
        this.transitionBase = transitionBase;
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
        this.regionBase = regionBase;
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
        this.descriptorBase = descriptorBase;
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
        this.connectorBase = connectorBase;
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
        return metas.add(meta);
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
        return metadatas.add(metadata);
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
        boolean valid = true;

        // Cabecalho nao pode ser vazio
        valid &= (getImportedDocumentBase() != null || getRuleBase() != null || getTransitionBase() != null ||
                getRegionBase() != null || getDescriptorBase() != null || getConnectorBase() != null || hasMeta() || hasMetadata());

        if(getImportedDocumentBase() != null)
            valid &= getImportedDocumentBase().validate();
        if(getRuleBase() != null)
            valid &= getRuleBase().validate();
        if(getTransitionBase() != null)
            valid &= getTransitionBase().validate();
        if(getRegionBase() != null)
            valid &= getRegionBase().validate();
        if(getDescriptorBase() != null)
            valid &= getDescriptorBase().validate();
        if(getConnectorBase() != null)
            valid &= getConnectorBase().validate();
        if(hasMeta()){
            for(M meta : metas)
                valid &= meta.validate();
        }
        if(hasMetadata()){
            for(MT metadata : metadatas)
                valid &= metadata.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(localName.equals("importedDocumentBase")){
            setImportedDocumentBase((IB) new NCLImportedDocumentBase(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
            getImportedDocumentBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("ruleBase")){
            setRuleBase((RLB) new NCLRuleBase(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
            getRuleBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("transitionBase")){
            setTransitionBase((TB) new NCLTransitionBase(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
            getTransitionBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("regionBase")){
            setRegionBase((RB) new NCLRegionBase(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
            getRegionBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("descriptorBase")){
            setDescriptorBase((DB) new NCLDescriptorBase(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
            getDescriptorBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("connectorBase")){
            setConnectorBase((CB) new NCLConnectorBase(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
            getConnectorBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("meta")){
            NCLMeta m = new NCLMeta(getReader(), this);
            m.startElement(uri, localName, qName, attributes);
            addMeta((M) m); //TODO: retirar o cast. Como melhorar isso?
        }
        else if(localName.equals("metadata")){
            NCLMetadata m = new NCLMetadata(getReader(), this);
            m.startElement(uri, localName, qName, attributes);
            addMetadata((MT) m); //TODO: retirar o cast. Como melhorar isso?
        }
    }
}
