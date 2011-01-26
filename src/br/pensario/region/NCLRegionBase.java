package br.pensario.region;

import br.pensario.NCLElement;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.reuse.NCLImport;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
 * NCL nos permite definir onde cada nó de mídia será apresentado, tanto em que classe de dispositivos como em qual região
 * de apresentação de cada dispositivo. Para cada classe de dispositivos de saída definimos, no cabeçalho do documento ( dentro do  elemento <i>head</i>),
 * uma coleção de regiões através do elemento <i>regionBase</i>.
 * Dentro de uma base de regiões definimos os elementos <i>region</i> que especificam
 * em que regiões da tela do dispositivo de saída serão exibidos os nós de conteúdo. Alem de elementos <i>region</i>, uma base de regiões também pode
 * conter elementos <i>importBase</i> para importar uma base definida em outro documento NCL. Ela ainda pode conter o atributo device, que especifica o dispositivo
 * de exibição relacionado aquela base.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 * @see br.pensario.region.NCLRegion
 *
 *
 *
 * @version 1.0.0
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 */
public class NCLRegionBase<R extends NCLRegion, I extends NCLImport> extends NCLIdentifiableElement {

    private String device;
    private R parent_region;
    
    private Set<R> regions = new TreeSet<R>();
    private Set<I> imports = new TreeSet<I>();


    /**
     * Construtor do elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLRegionBase() {}


    /**
     * Construtor do elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLRegionBase(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Define um dispositivo de exibição associado ao elemento <i>regionBase</i>. Ou seja, esse método determina para qual
     * dispositivo as regiões contidas naquela base serão válidas.
     * @param device
     *          O método recebe como parâmetro um String representando o dispositivo de exibição a que se quer relacionar a base de regiões atual.
     * @throws java.lang.IllegalArgumentException
     *          O método dispara uma exceção caso o usuário passe uma string vazia como parâmetro.
     */
    public void setDevice(String device) throws IllegalArgumentException {
        if(device != null && "".equals(device.trim()))
            throw new IllegalArgumentException("Empty device String");

        this.device = device;
    }


    /**
     * Método de acesso ao elemento device de uma base de regiões.
     * 
     * @return
     *          Retorna uma String representando o elemento device associado a <i>regionBase</i> em questão.
     */
    public String getDevice() {
        return device;
    }    


    /**
     * NCL permite que regiões sejam aninhadas. Dessa forma, o posicionamento e tamanho de uma região filha pode ser relativa a uma região pai
     * e expressa como porcentagem. Esse método atribui a <i>regionBase</i> a região pai, ou seja, a região mais externa do aninhamento
     * 
     * @param region
     *          Elemento representando a região a ser utilizada como pai.
     */
    public void setParentRegion(R region) {
        this.parent_region = region;
    }


    /**
     * NCL permite que regiões sejam aninhadas. Dessa forma, o posicionamento e tamanho de uma região filha pode ser relativa a uma região pai
     * e expressa como porcentagem. Esse método atribui a <i>regionBase</i> a região pai, ou seja, a região mais externa do aninhamento.
     * Esse método retorna a região pai da base de regiões.
     * 
     * @return
     *          elemento representando a região pai de uma base de regiões.
     */
    public NCLRegion getParentRegion() {
        return parent_region;
    }


    /**
     * Uma base de regiões pode conter como filhos elementos <i>region</i> e elementos <i>importBase</i>. Este método adiciona a base uma
     * um elemento <i>region</i>, uma região da tela do dispositivo relacionado a esta base.
     *
     * @see br.pensario.region.NCLRegion
     * 
     * @param region
     *          O método recebe como parâmetro um elemento <i>region</i> a ser adicionado.
     * @return
     *          Retorna verdadeiro se a região for adicionada com sucesso.
     *
     * @see TreeSet#add
     */
    public boolean addRegion(R region) {
        if(regions.add(region)){
            //Se region existe, atribui este como seu parente
            if(region != null)
                region.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Este método remove um elemento <i>region</i> da base. Nesta implementação, o método recebe o identificador da região a ser removida.
     *
     * @param id
     *          O método recebe com parametro uma string representando o  identificador da região a ser removida.
     * @return
     *          Retorna verdadeiro se a região for removida com sucesso.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(String id) {
        for(R region : regions){
            if(region.getId().equals(id))
                return removeRegion(region);
        }
        return false;
    }


    /**
     * Remove uma região da base de regiões. Nesta implementação, o metodo recebe como parâmetro um objeto <i>region</i> a ser retirado da base.
     * 
     * @param region
     *          Recebe como parametro o proprio elemento region a ser removido.
     * @return
     *          Retorna verdadeiro se a região for removida com sucesso.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(R region) {
        if(regions.remove(region)){
            //Se region existe, retira o seu parentesco
            if(region != null)
                region.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se uma região pertence a base.
     * 
     * @param region
     *          elemento representando a região a ser verificada.
     * @return
     *           Verdadeiro caso a base contenha a região.
     *           Falso caso a base não contenha a região.
     *          
     */    
    public boolean hasRegion(R region) {
        return regions.contains(region);        
    }


    /**
     * Verifica se a base de regiões possui alguma região.
     * 
     * @return
     *          Verdadeiro caso a base possua, pelo menos, uma  região.
     *          Falso caso a base não possua elementos <i>region</i>
     */
    public boolean hasRegion() {
        return regions.size() > 0;        
    }


    /**
     * Retorna a coleção de regiões da base.
     *
     * @return
     *          objeto Iterable contendo as regiões da base de regiões.
     */
    public Iterable<R> getRegions() {
        return regions;        
    }


    /**
     * Adiciona um elemento importBase a base de regiões. O elemento <i>importBase</i>, permite ao documento importar uma base de regiões
     * pertencente a outro documento NCL.
     *
     * @param importBase
     *          O método recebe como parâmetro um elemento <i>importBase</i> a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(I importBase) {
        if(imports.add(importBase)){
            //Se importBase existe, atribui este como seu parente
            if(importBase != null)
                importBase.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um elemento <i>importBase</i> da base de regiões.
     *
     * @param importBase
     *           O método recebe como parâmetro um elemento <i>importBase</i> a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(I importBase) {
        if(imports.remove(importBase)){
            //Se importBase existe, retira o seu parentesco
            if(importBase != null)
                importBase.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se a base de regiões contém um determinado elemento <i>importBase</i>.
     *
     * @param importBase
     *          Elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de regiões possui algum importador de regiões.
     *
     * @return
     *          Verdadeiro se a base de regiões possuir algum elemento <i>importBase</i>.
     *          Falso se a base de regiões não possuir nenhum elemento <i>importBase</i>.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os elementos <i>importBase</i> da base de regiões.
     *
     * @return
     *          Objeto Iterable contendo os elementos <i>importBase</i> da base de regiões.
     */
    public Iterable<I> getImportBases() {
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

        content = space + "<regionBase";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getDevice() != null)                         
            content += " device='" + getDevice() + "'";
        if(getParentRegion() != null)                         
            content += " region='" + getParentRegion().getId() + "'";
        
        if(hasRegion() || hasImportBase()) {
            content += ">\n";

            if(hasImportBase()){
                for(I imp : imports)
                    content += imp.parse(ident + 1);
            }
            if(hasRegion()){
                for(R region : regions)
                    content += region.parse(ident + 1);
            }
            content += space + "</regionBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (hasImportBase() || hasRegion());

        if(hasImportBase()){
            for(I imp : imports)
                valid &= imp.validate();
        }
        if(hasRegion()){
            for(R region : regions)
                valid &= region.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("regionBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("device"))
                        setDevice(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("region"))
                        setParentRegion((R) new NCLRegion(attributes.getValue(i)));//TODO: precisa retirar cast?
                }
            }
            else if(localName.equals("importBase")){
                I child = createImportBase();
                child.startElement(uri, localName, qName, attributes);
                addImportBase(child);
            }
            else if(localName.equals("region")){
                R child = createRegion();
                child.startElement(uri, localName, qName, attributes);
                addRegion(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getParentRegion() != null)
                setParentRegion(findRegion(getRegions()));
        }

        if(hasImportBase()){
            for(I imp : imports){
                imp.endDocument();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }
        if(hasRegion()){
            for(R region : regions){
                region.endDocument();
                addWarning(region.getWarnings());
                addError(region.getErrors());
            }
        }
    }

   
    private R findRegion(Iterable<R> regions) {
        for(R reg : regions){
            if(reg.hasRegion()){
                NCLRegion r = findRegion(reg.getRegions());
                if(r != null)
                    return (R) r;
            }
            else{
                if(reg.getId().equals(getParentRegion().getId()))
                    return (R) reg;
            }
        }

        addWarning("Could not find region in regionBase with id: " + getParentRegion().getId());
        return null;
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
     * Função de criação do elemento filho <i>region</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>region</i>.
     */
    protected R createRegion() {
        return (R) new NCLRegion(getReader(), this);
    }
}
