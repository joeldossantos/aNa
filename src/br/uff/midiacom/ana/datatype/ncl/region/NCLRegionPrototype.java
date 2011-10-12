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
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.number.RelativeType;
import br.uff.midiacom.xml.datatype.string.StringType;



/**
 * Esta classe define o elemento <i>region</i> da <i>Nested Context Language</i> (NCL).<br/>
 *
 * Regions definem as regioes da tela dos dispositivos de saida onde os nos de midia poderao ser exibidos. Uma regiao pode ser definida
 * com relaco a area total associada aop dispositivo correspondente a uma base de regioes, ou aninhada a uma outra região, recursivamente.<br/>
 *
 * Os atributos de região definem suas coordenadas espacias e podem ser definidos em numeros absolutos, representando a quantidade de pixels,
 * ou em porcentagem, relativa a região pai. São eles:<br/>
 * <i>left</i> - coordenada x tomando como origem a extremidade esquerda da tela,com relação a area total do dispositivo ou (no caso da regiao estar aninhada) com a cordenada x esquerda da região pai.
 * <i>top</i> - coordenada y tomando como origem a extremidade superior da tela, com relação a area total ou a coordenada y superior da regiao pai.
 * <i>right</i> - semelhante ao atributo <i>left</i>, mas com a origem do eixo localizada na fronteira direita da tela.
 * <i>bottom</i> - semelhante ao atributo <i>top</i>, mas tendo a origem do eixo localizada na fronteira inferior da tela.
 * <i>width</i> - dimensão horizontal da região, podendo ser expressa em pixels ou em porcentagem, relativa a regiao pai.
 * <i>height</i> - dimensão vertical da regiao, podendo ser expressa em pixels ou em porcentagem, relativa a pai.
 * <i>zIndex</i> - Coordenada z, tomando como o origem o plano da tela e crescente no sentido "para fora da tela". Esta coordenada serve para definir o critério de sobreposição de midias que
 * ocupem espaços coincidente na tela do dispositivo de saída. Quanto maior o valor do <i>zIndex</i>, maior a prioridade na sobreposição.<br/>
 *
 * 
 * @see br.pensario.region.NCLRegionBase
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLRegionPrototype<T extends NCLRegionPrototype, P extends NCLElement, I extends XMLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {

    protected StringType title;
    protected RelativeType left;
    protected RelativeType right;
    protected RelativeType top;
    protected RelativeType bottom;
    protected RelativeType height;
    protected RelativeType width;
    protected Integer zIndex;
    protected IdentifiableElementList<T, T> regions;


    /**
     * Construtor do elemento <i>region</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da região.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador for inválido.
     */
    public NCLRegionPrototype(String id) throws XMLException {
        setId(id);
        regions = new IdentifiableElementList<T, T>();
    }


    /**
     * Atribui um título para a região.
     * 
     * @param title
     *          String representando o título da região.
     * @throws XMLException
     *          se o título for uma String vazia.
     */
    public void setTitle(String title) throws XMLException {
        this.title = new StringType(title);
    }


    /**
     * Retorna o título da região.
     * 
     * @return
     *          String representando o título da região.
     */
    public String getTitle() {
        return title.getValue();
    }


    /**
     * Atribui o valor da posição a esquerda da região.
     * 
     * @param left
     *          inteiro representando a posição a esquerda da região.
     * @param relative
     *          booleano indicando se o valor de posição é uma porcentagem.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for uma porcentagem e seu valor estiver fora do intervalo [0,100]
     */
    public void setLeft(RelativeType left) {
        this.left = left;
    }


    /**
     * Retorna o valor da posição a esquerda da região.
     * 
     * @return
     *          inteiro representando a posição a esquerda da região.
     *          pode representar uma grandeza em pixels ou percentual, dependendo
     *          da especificação de posição relativa.
     * 
     * @see NCLRegionPrototype#setLeft
     * @see NCLRegionPrototype#isRelativeLeft
     */
    public RelativeType getLeft() {
        return left;
    }


    /**
     * Atribui o valor da posição a direita da região.
     *
     * @param right
     *          inteiro representando a posição a direita da região.
     * @param relative
     *          booleano indicando se o valor de posição é uma porcentagem.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for uma porcentagem e seu valor estiver fora do intervalo [0,100]
     */
    public void setRight(RelativeType right) {
        this.right = right;
    }


    /**
     * Retorna o valor da posição a direita da região.
     *
     * @return
     *          inteiro representando a posição a direita da região.
     *          pode representar uma grandeza em pixels ou percentual, dependendo
     *          da especificação de posição relativa.
     *
     * @see NCLRegionPrototype#setRight
     * @see NCLRegionPrototype#isRelativeRight
     */
    public RelativeType getRight() {
        return right;
    }


    /**
     * Atribui o valor da posição de topo da região.
     *
     * @param top
     *          inteiro representando a posição de topo da região.
     * @param relative
     *          booleano indicando se o valor de posição é uma porcentagem.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for uma porcentagem e seu valor estiver fora do intervalo [0,100]
     */
    public void setTop(RelativeType top) {
        this.top = top;
    }


    /**
     * Retorna o valor da posição de topo da região.
     *
     * @return
     *          inteiro representando a posição de topo da região.
     *          pode representar uma grandeza em pixels ou percentual, dependendo
     *          da especificação de posição relativa.
     *
     * @see NCLRegionPrototype#setRight
     * @see NCLRegionPrototype#isRelativeRight
     */
    public RelativeType getTop() {
        return top;
    }


    /**
     * Atribui o valor da posição inferior da região.
     *
     * @param bottom
     *          inteiro representando a posição inferior da região.
     * @param relative
     *          booleano indicando se o valor de posição é uma porcentagem.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for uma porcentagem e seu valor estiver fora do intervalo [0,100]
     */
    public void setBottom(RelativeType bottom) {
        this.bottom = bottom;
    }


    /**
     * Retorna o valor da posição inferior da região.
     *
     * @return
     *          inteiro representando a posição inferior da região.
     *          pode representar uma grandeza em pixels ou percentual, dependendo
     *          da especificação de posição relativa.
     *
     * @see NCLRegionPrototype#setBottom
     * @see NCLRegionPrototype#isRelativeBottom
     */
    public RelativeType getBottom() {
        return bottom;
    }


    /**
     * Atribui uma altura a região.
     * 
     * @param height
     *          inteiro representando a altura da região.
     * @param relative
     *          booleano indicando se o valor de altura é uma porcentagem.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for uma porcentagem e seu valor estiver fora do intervalo [0,100]
     */
    public void setHeight(RelativeType height) {
        this.height = height;
    }


    /**
     * Retorna o valor da altura da região.
     *
     * @return
     *          inteiro representando a altura da região.
     *          pode representar uma grandeza em pixels ou percentual, dependendo
     *          da especificação de posição relativa.
     *
     * @see NCLRegionPrototype#setHeight
     * @see NCLRegionPrototype#isRelativeHeight
     */
    public RelativeType getHeight() {
        return height;
    }


    /**
     * Atribui uma largura a região.
     *
     * @param width
     *          inteiro representando a largura da região.
     * @param relative
     *          booleano indicando se o valor de altura é uma porcentagem.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for uma porcentagem e seu valor estiver fora do intervalo [0,100]
     */
    public void setWidth(RelativeType width) {
        this.width = width;
    }


    /**
     * Retorna o valor da largura da região.
     *
     * @return
     *          inteiro representando a largura da região.
     *          pode representar uma grandeza em pixels ou percentual, dependendo
     *          da especificação de posição relativa.
     *
     * @see NCLRegionPrototype#setWidth
     * @see NCLRegionPrototype#isRelativeWidth
     */
    public RelativeType getWidth() {
        return width;
    }


    /**
     * Atribui um indice no eixo z (usado para sobreposição) a região.
     *
     * @param zIndex
     *          inteiro representando o índice da região.
     * @throws java.lang.IllegalArgumentException
     *          se o índice for um valor negativo.
     */
    public void setzIndex(Integer zIndex) throws IllegalArgumentException {
        if(zIndex != null && zIndex < 0 && zIndex > 250)
            throw new IllegalArgumentException("Illegal index value");

        this.zIndex = zIndex;
    }


    /**
     * Retorna o indice no eixo z (usado para sobreposição) da região.
     *
     * @return
     *          inteiro representando o índice da região.
     */
    public Integer getzIndex() {
        return zIndex;
    }


    /**
     * Adiciona uma região filha à região. A região filha é considerada uma região interna
     * tendo seus parâmetros relativos a esta região.
     *
     * @param region
     *          elemento representando a região a ser adicionada.
     * @return
     *          verdadeiro se a região foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRegion(T region) throws XMLException {
        return regions.add(region, (T) this);
    }


    /**
     * Remove uma região filha da região.
     *
     * @param region
     *          elemento representando a região a ser removida.
     * @return
     *          verdadeiro se a região foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(T region) throws XMLException {
        return regions.remove(region);
    }


    /**
     * Remove uma região filha da região.
     *
     * @param id
     *          identificador da região a ser removida.
     * @return
     *          Verdadeiro se a região foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRegion(String id) throws XMLException {
        return regions.remove(id);
    }


    /**
     * Verifica se a região possui uma região como filha.
     *
     * @param child
     *          elemento representando a região a ser verificada.
     * @return
     *          verdadeiro se a região existir.
     */
    public boolean hasRegion(T child) throws XMLException {
        return regions.contains(child);
    }


    public boolean hasRegion(String id) throws XMLException {
        return regions.get(id) != null;
    }


    /**
     * Verifica se a região possui alguma região como filha.
     *
     * @return
     *          verdadeiro se a região possuir alguma região filha.
     */
    public boolean hasRegion() {
        return !regions.isEmpty();
    }


    /**
     * Retorna as regiões internas a região.
     *
     * @return
     *          lista contendo as regiões internas.
     */
    public IdentifiableElementList<T, T> getRegions() {
        return regions;
    }
    

    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<region";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getLeft() != null)
            content += " left='" + getLeft().parse() + "'";
        if(getRight() != null)
            content += " right='" + getRight().parse() + "'";
        if(getTop() != null)
            content += " top='" + getTop().parse() + "'";
        if(getBottom() != null)
            content += " bottom='" + getBottom().parse() + "'";
        if(getHeight() != null)
            content += " height='" + getHeight().parse() + "'";
        if(getWidth() != null)
            content += " width='" + getWidth().parse() + "'";
        if(getzIndex() != null)
            content += " zIndex='" + getzIndex() + "'";
        if(getTitle() != null)
            content += " title='" + getTitle() + "'";
        if(hasRegion()) {
            content += ">\n";

            for(T region : getRegions())
                content += region.parse(ident + 1);
            
            content += space + "</region>\n";
        }
        else
            content += "/>\n";

        return content;
    }
}
