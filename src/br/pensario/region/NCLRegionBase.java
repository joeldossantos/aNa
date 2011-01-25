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
 * Esta classe define o elemento <i>regioBase</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma base de regiões de um documento NCL.<br>
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
     * Atribui o dispositivo de exibição para as regiões presentes na base de regiões.
     * 
     * @param device
     *          String representando o dispositivo de exibição.
     * @throws java.lang.IllegalArgumentException
     *          se o dispositivo a ser atribuido for uma string vazia.
     */
    public void setDevice(String device) throws IllegalArgumentException {
        if(device != null && "".equals(device.trim()))
            throw new IllegalArgumentException("Empty device String");

        this.device = device;
    }


    /**
     * Retorna qual o dispositivo de exibição utilizado para as regiões presentes na base de regiões.
     * 
     * @return
     *          String representando o dispositivo de exibição.
     */
    public String getDevice() {
        return device;
    }    


    /**
     * Atribui uma região que será a região pai das regiões da base de regiões.
     * 
     * @param region
     *          elemento representando a região a ser utilizada como pai.
     */
    public void setParentRegion(R region) {
        this.parent_region = region;
    }


    /**
     * Retorna a região que será a região pai das regiões da base de regiões.
     * 
     * @return
     *          elemento representando a região a ser utilizada como pai.
     */
    public NCLRegion getParentRegion() {
        return parent_region;
    }


    /**
     * Adiciona uma região à base de regiões.
     * 
     * @param region
     *          elemento representando a região a ser adicionada.
     * @return
     *          verdadeiro se a região foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRegion(R region) {
        return regions.add(region);
    }


    /**
     * Remove uma região da base de regiões.
     *
     * @param id
     *          identificador da região a ser removida.
     * @return
     *          Verdadeiro se a região foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(String id) {
        for(R region : regions){
            if(region.getId().equals(id))
                return regions.remove(region);
        }
        return false;
    }


    /**
     * Remove uma região da base de regiões.
     * 
     * @param region
     *          elemento representando a região a ser removida.
     * @return
     *          Verdadeiro se a região foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(R region) {
        return regions.remove(region);        
    }


    /**
     * Verifica se a base de regiões contém uma região.
     * 
     * @param region
     *          elemento representando a região a ser verificada.
     * @return
     *          verdadeiro se a região existir.
     */    
    public boolean hasRegion(R region) {
        return regions.contains(region);        
    }


    /**
     * Verifica se a base de regiões possui alguma região.
     * 
     * @return
     *          verdadeiro se a base de regiões pussuir alguma região.
     */
    public boolean hasRegion() {
        return regions.size() > 0;        
    }


    /**
     * Retorna as regiões da base de regiões.
     *
     * @return
     *          objeto Iterable contendo as regiões da base de regiões.
     */
    public Iterable<R> getRegions() {
        return regions;        
    }


    /**
     * Adiciona um importador de base à base de regiões.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(I importBase) {
        return imports.add(importBase);
    }


    /**
     * Remove um importador de base da base de regiões.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(I importBase) {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de regiões contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de regiões possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de regiões possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de regiões.
     *
     * @return
     *          objeto Iterable contendo os importadores de base da base de regiões.
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
                        setParentRegion((R) new NCLRegion(attributes.getValue(i)));
                }
            }
            else if(localName.equals("importBase")){
                NCLImport i = new NCLImport(NCLImportType.BASE, getReader(), this);
                i.startElement(uri, localName, qName, attributes);
                addImportBase((I) i); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("region")){
                NCLRegion r = new NCLRegion(getReader(), this);
                r.startElement(uri, localName, qName, attributes);
                addRegion((R) r); //TODO: retirar o cast. Como melhorar isso?
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
}
