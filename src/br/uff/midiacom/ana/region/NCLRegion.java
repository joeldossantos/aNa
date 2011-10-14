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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.RelativeType;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.TreeSet;
import org.w3c.dom.Element;


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
public class NCLRegion<T extends NCLRegion, P extends NCLElement, I extends NCLElementImpl>
        extends NCLRegionPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {


    /**
     * Construtor do elemento <i>region</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da região.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador for inválido.
     */
    public NCLRegion(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Atribui um título para a região.
     * 
     * @param title
     *          String representando o título da região.
     * @throws java.lang.IllegalArgumentException
     *          se o título for uma String vazia.
     */
    @Override
    public void setTitle(String title) throws XMLException {
        StringType aux = this.title;
        super.setTitle(title);
        impl.notifyAltered(NCLElementAttributes.TITLE, aux, title);
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
    @Override
    public void setLeft(RelativeType left) {
        RelativeType aux = this.left;
        super.setLeft(left);
        impl.notifyAltered(NCLElementAttributes.LEFT, aux, left);
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
    @Override
    public void setRight(RelativeType right) {
        RelativeType aux = this.right;
        super.setRight(right);
        impl.notifyAltered(NCLElementAttributes.RIGHT, aux, right);
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
    @Override
    public void setTop(RelativeType top) {
        RelativeType aux = this.top;
        super.setTop(top);
        impl.notifyAltered(NCLElementAttributes.TOP, aux, top);
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
    @Override
    public void setBottom(RelativeType bottom) {
        RelativeType aux = this.bottom;
        super.setBottom(bottom);
        impl.notifyAltered(NCLElementAttributes.BOTTOM, aux, bottom);
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
    @Override
    public void setHeight(RelativeType height) {
        RelativeType aux = this.height;
        super.setHeight(height);
        impl.notifyAltered(NCLElementAttributes.HEIGHT, aux, height);
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
    @Override
    public void setWidth(RelativeType width) {
        RelativeType aux = this.width;
        super.setWidth(width);
        impl.notifyAltered(NCLElementAttributes.WIDTH, aux, width);
    }


    /**
     * Atribui um indice no eixo z (usado para sobreposição) a região.
     *
     * @param zIndex
     *          inteiro representando o índice da região.
     * @throws java.lang.IllegalArgumentException
     *          se o índice for um valor negativo.
     */
    @Override
    public void setzIndex(Integer zIndex) {
        Integer aux = this.zIndex;
        super.setzIndex(zIndex);
        impl.notifyAltered(NCLElementAttributes.ZINDEX, aux, zIndex);
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
    @Override
    public boolean addRegion(T region) throws XMLException {
        if(super.addRegion(region)){
            impl.notifyInserted(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
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
    @Override
    public boolean removeRegion(String id) throws XMLException {
        for(T region : regions){
            if(region.getId().equals(id))
                return removeRegion(region);
        }
        return false;
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
    @Override
    public boolean removeRegion(T region) throws XMLException {
        if(super.removeRegion(region)){
            impl.notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(!insideRegion){
//                cleanWarnings();
//                cleanErrors();
//                insideRegion = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("left")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setLeft(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("right")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setRight(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("top")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setTop(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("bottom")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setBottom(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("height")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setHeight(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("width")){
//                        String value = attributes.getValue(i);
//                        Boolean isRelative = attributes.getValue(i).contains("%");
//                        if(isRelative)
//                            value = value.substring(0, value.length() - 1);
//                        setWidth(new Integer(value), isRelative);
//                    }
//                    else if(attributes.getLocalName(i).equals("zIndex"))
//                        setzIndex(new Integer(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("title"))
//                        setTitle(attributes.getValue(i));
//                }
//            }
//            else{
//                // Region e um elemento interno
//                R child = createRegion();
//                child.startElement(uri, localName, qName, attributes);
//                addRegion(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(hasRegion()){
//            for(R region : regions){
//                region.endDocument();
//                addWarning(region.getWarnings());
//                addError(region.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


//    /**
//     * Função de criação do elemento filho <i>region</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>region</i>.
//     */
//    protected R createRegion() {
//        return (R) new NCLRegion(getReader(), this);
//    }
}
