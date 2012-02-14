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
package br.uff.midiacom.ana.datatype.ncl.region;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.string.StringType;


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
 * @see br.pensario.region.NCLRegionPrototype
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLRegionBasePrototype<T extends NCLRegionBasePrototype, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegionPrototype, Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {

    protected StringType device;
    protected Er parent_region;
    protected IdentifiableElementList<Er, T> regions;
    protected ElementList<Ei, T> imports;


    /**
     * Construtor do elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLRegionBasePrototype() throws XMLException {
        super();
        regions = new IdentifiableElementList<Er, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Define um dispositivo de exibicao associado ao elemento <i>regionBase</i>. Ou seja, esse metodo determina para qual
     * dispositivo as regioes contidas naquela base serao validas.
     * @param device
     *          O metodo recebe como parametro um String representando o dispositivo de exibicao a que se quer relacionar a base de regioes atual.
     * @throws java.lang.IllegalArgumentException
     *          O metodo dispara uma excecao caso o usuario passe uma string vazia como parametro.
     */
    public void setDevice(String device) throws XMLException {
        this.device = new StringType(device);
    }


    /**
     * Metodo de acesso ao elemento device de uma base de regioes.
     * 
     * @return
     *          Retorna uma String representando o elemento device associado a <i>regionBase</i> em questao.
     */
    public String getDevice() {
        if(device != null)
            return device.getValue();
        else
            return null;
    }    


    /**
     * NCL permite que regioes sejam aninhadas. Dessa forma, o posicionamento e tamanho de uma regiao filha pode ser relativa a uma regiao pai
     * e expressa como porcentagem. Esse metodo atribui a <i>regionBase</i> a regiao pai, ou seja, a regiao mais externa do aninhamento
     * 
     * @param region
     *          Elemento representando a regiao a ser utilizada como pai.
     */
    public void setParentRegion(Er region) {
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
    public Er getParentRegion() {
        return parent_region;
    }


    /**
     * Uma base de regioes pode conter como filhos elementos <i>region</i> e elementos <i>importBase</i>. Este metodo adiciona a base uma
     * um elemento <i>region</i>, uma regiao da tela do dispositivo relacionado a esta base.
     *
     * @see br.pensario.region.NCLRegionPrototype
     * 
     * @param region
     *          O método recebe como parametro um elemento <i>region</i> a ser adicionado.
     * @return
     *          Retorna verdadeiro se a regiao for adicionada com sucesso.
     *
     * @see TreeSet#add
     */
    public boolean addRegion(Er region) throws XMLException {
        return regions.add(region, (T) this);
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
    public boolean removeRegion(Er region) throws XMLException {
        return regions.remove(region);
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
    public boolean removeRegion(String id) throws XMLException {
        return regions.remove(id);
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
    public boolean hasRegion(Er region) throws XMLException {
        return regions.contains(region);        
    }


    public boolean hasRegion(String id) throws XMLException {
        return regions.get(id) != null;
    }


    /**
     * Verifica se a base de regioes possui alguma regiao.
     * 
     * @return
     *          Verdadeiro caso a base possua, pelo menos, uma  regiao.
     *          Falso caso a base nao possua elementos <i>region</i>
     */
    public boolean hasRegion() {
        return !regions.isEmpty();
    }


    /**
     * Retorna a coleção de regioes da base.
     *
     * @return
     *          lista contendo as regioes da base de regioes.
     */
    public IdentifiableElementList<Er, T> getRegions() {
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
    public boolean addImportBase(Ei importBase) throws XMLException {
        return imports.add(importBase, (T) this);
    }


    /**
     * Remove um elemento <i>importBase</i> da base de regioes.
     *
     * @param importBase
     *           O metodo recebe como parâmetro um elemento <i>importBase</i> a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de regioes contem um determinado elemento <i>importBase</i>.
     *
     * @param importBase
     *          Elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
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

        content = space + "<regionBase";
        content += parseAttributes();
        
        if(hasRegion() || hasImportBase()) {
            content += ">\n";

            content += parseElements(ident + 1);
            
            content += space + "</regionBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseDevice();
        content += parseParentRegion();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportBases(ident);
        content += parseRegions(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseDevice() {
        String aux = getDevice();
        if(aux != null)
            return " device='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseParentRegion() {
        Er aux = getParentRegion();
        if(aux != null)
            return " region='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseRegions(int ident) {
        if(!hasRegion())
            return "";
        
        String content = "";
        for(Er aux : regions)
            content += aux.parse(ident);
        
        return content;
    }
}
