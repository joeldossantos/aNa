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
package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import br.uff.midiacom.ana.datatype.NCLImportType;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
 * NCL nos permite definir onde cada no de midia sera apresentado, tanto em que classe de dispositivos como em qual regiao
 * de apresentaçao de cada dispositivo. Para cada classe de dispositivos de saida definimos, no cabeçalho do documento ( dentro do  elemento <i>head</i>),
 * uma colecao de regioes atraves do elemento <i>regionBase</i>.
 * Dentro de uma base de regioes definimos os elementos <i>region</i> que especificam
 * em que regioes da tela do dispositivo de saida serao exibidos os nos de conteudo. Alem de elementos <i>region</i>, uma base de regioes tambem pode
 * conter elementos <i>importBase</i> para importar uma base definida em outro documento NCL. Ela ainda pode conter o atributo device, que especifica o dispositivo
 * de exibicao relacionado aquela base.<br>
 *
 * 
 * @see br.pensario.region.NCLRegion
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLRegionBase<RB extends NCLRegionBase, R extends NCLRegion, I extends NCLImport> extends NCLIdentifiableElement implements Comparable<RB> {

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
     * Define um dispositivo de exibicao associado ao elemento <i>regionBase</i>. Ou seja, esse metodo determina para qual
     * dispositivo as regioes contidas naquela base serao validas.
     * @param device
     *          O metodo recebe como parametro um String representando o dispositivo de exibicao a que se quer relacionar a base de regioes atual.
     * @throws java.lang.IllegalArgumentException
     *          O metodo dispara uma excecao caso o usuario passe uma string vazia como parametro.
     */
    public void setDevice(String device) throws IllegalArgumentException {
        if(device != null && "".equals(device.trim()))
            throw new IllegalArgumentException("Empty device String");

        notifyAltered(NCLElementAttributes.DEVICE, this.device, device);
        this.device = device;
    }


    /**
     * Metodo de acesso ao elemento device de uma base de regioes.
     * 
     * @return
     *          Retorna uma String representando o elemento device associado a <i>regionBase</i> em questao.
     */
    public String getDevice() {
        return device;
    }    


    /**
     * NCL permite que regioes sejam aninhadas. Dessa forma, o posicionamento e tamanho de uma regiao filha pode ser relativa a uma regiao pai
     * e expressa como porcentagem. Esse metodo atribui a <i>regionBase</i> a regiao pai, ou seja, a regiao mais externa do aninhamento
     * 
     * @param region
     *          Elemento representando a regiao a ser utilizada como pai.
     */
    public void setParentRegion(R region) {
        this.parent_region = region;
    }


    /**
     * NCL permite que regioes sejam aninhadas. Dessa forma, o posicionamento e tamanho de uma regiao filha pode ser relativa a uma regiao pai
     * e expressa como porcentagem. Esse metodo atribui a <i>regionBase</i> a regiao pai, ou seja, a regiao imediatamente acima no aninhamento.
     * Esse método retorna a região pai da base de regioes.
     * 
     * @return
     *          elemento representando a regiao pai de uma base de regioes.
     */
    public NCLRegion getParentRegion() {
        return parent_region;
    }


    /**
     * Uma base de regioes pode conter como filhos elementos <i>region</i> e elementos <i>importBase</i>. Este metodo adiciona a base uma
     * um elemento <i>region</i>, uma regiao da tela do dispositivo relacionado a esta base.
     *
     * @see br.pensario.region.NCLRegion
     * 
     * @param region
     *          O método recebe como parametro um elemento <i>region</i> a ser adicionado.
     * @return
     *          Retorna verdadeiro se a regiao for adicionada com sucesso.
     *
     * @see TreeSet#add
     */
    public boolean addRegion(R region) {
        if(regions.add(region)){
            //Se region existe, atribui este como seu parente
            if(region != null)
                region.setParent(this);

            notifyInserted(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Este metodo remove um elemento <i>region</i> da base. Nesta implementacao, o metodo recebe o identificador da regiao a ser removida.
     *
     * @param id
     *          O metodo recebe com parametro uma string representando o  identificador da regiao a ser removida.
     * @return
     *          Retorna verdadeiro se a regiao for removida com sucesso.
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
     * Remove uma regiao da base de regioes. Nesta implementacao, o metodo recebe como parametro um objeto <i>region</i> a ser retirado da base.
     * 
     * @param region
     *          Recebe como parametro o proprio elemento region a ser removido.
     * @return
     *          Retorna verdadeiro se a regiao for removida com sucesso.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(R region) {
        if(regions.remove(region)){
            //Se region existe, retira o seu parentesco
            if(region != null)
                region.setParent(null);

            notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Verifica se uma regiao pertence a base.
     * 
     * @param region
     *          elemento representando a regiao a ser verificada.
     * @return
     *           Verdadeiro caso a base contenha a regiao.
     *           Falso caso a base não contenha a regiao.
     *          
     */    
    public boolean hasRegion(R region) {
        return regions.contains(region);        
    }


    /**
     * Verifica se a base de regioes possui alguma regiao.
     * 
     * @return
     *          Verdadeiro caso a base possua, pelo menos, uma  regiao.
     *          Falso caso a base nao possua elementos <i>region</i>
     */
    public boolean hasRegion() {
        return regions.size() > 0;        
    }


    /**
     * Retorna a coleção de regioes da base.
     *
     * @return
     *          lista contendo as regioes da base de regioes.
     */
    public Set<R> getRegions() {
        return regions;        
    }


    /**
     * Adiciona um elemento importBase a base de regioes. O elemento <i>importBase</i>, permite ao documento importar uma base de regioes
     * pertencente a outro documento NCL.
     *
     * @param importBase
     *          O método recebe como parametro um elemento <i>importBase</i> a ser adicionado.
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
     * Remove um elemento <i>importBase</i> da base de regioes.
     *
     * @param importBase
     *           O metodo recebe como parâmetro um elemento <i>importBase</i> a ser removido.
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
     * Verifica se a base de regioes contem um determinado elemento <i>importBase</i>.
     *
     * @param importBase
     *          Elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de regioes possui algum importador de regioes.
     *
     * @return
     *          Verdadeiro se a base de regioes possuir algum elemento <i>importBase</i>.
     *          Falso se a base de regioes não possuir nenhum elemento <i>importBase</i>.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os elementos <i>importBase</i> da base de regiões.
     *
     * @return
     *          lista contendo os elementos <i>importBase</i> da base de regiões.
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
                        setParentRegion((R) new NCLRegion(attributes.getValue(i)));//cast retirado na correcao das referencias
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

   
    private R findRegion(Set<R> regions) {
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
    
    
    
    public int compareTo(RB other) {
        return getId().compareTo(other.getId());
    }
}
