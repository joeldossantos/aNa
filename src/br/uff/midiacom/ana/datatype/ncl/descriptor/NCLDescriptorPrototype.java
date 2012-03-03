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
package br.uff.midiacom.ana.datatype.ncl.descriptor;

import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.param.NCLDescriptorParam;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.number.PercentageType;
import br.uff.midiacom.xml.datatype.string.StringType;


public abstract class NCLDescriptorPrototype<T extends NCLDescriptorPrototype,
                                             P extends NCLElement,
                                             I extends NCLElementImpl,
                                             Er extends NCLRegionPrototype,
                                             El extends NCLLayoutDescriptor,
                                             Et extends NCLTransitionPrototype,
                                             Ep extends NCLDescriptorParam>
        extends NCLIdentifiableElementPrototype<El, P, I>
        implements NCLLayoutDescriptor<El, P> {

    protected StringType player;
    protected TimeType explicitDur;
    protected Boolean freeze;
    protected T moveLeft;
    protected T moveRight;
    protected T moveUp;
    protected T moveDown;
    protected Integer focusIndex;
    protected NCLColor focusBorderColor;
    protected Integer focusBorderWidth;
    protected PercentageType focusBorderTransparency;
    protected SrcType focusSrc;
    protected SrcType focusSelSrc;
    protected NCLColor selBorderColor;
    protected Et transIn;
    protected Et transOut;
    protected Er region;
    protected ElementList<Ep, T> params;
    
    protected ItemList<ReferenceType> references;


    /**
     * Construtor do elemento <i>descriptor</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do descritor não for válido.
     */
    public NCLDescriptorPrototype(String id) throws XMLException {
        super();
        setId(id);
        params = new ElementList<Ep, T>();
        references = new ItemList<ReferenceType>();
    }
    
    
    public NCLDescriptorPrototype() throws XMLException {
        super();
        params = new ElementList<Ep, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Identifica o node da ferramenta de apresentação utilizada pelo descritor.
     *
     * @param player
     *          nome da ferramenta a ser utilizada.
     * @throws java.lang.IllegalArgumentException
     *          se a String passada como parâmetro for vazia.
     */
    public void setPlayer(String player) throws XMLException {
        StringType aux = this.player;
        this.player = new StringType(player);
        impl.notifyAltered(NCLElementAttributes.PLAYER, aux, player);
    }


    /**
     * Retorna o node da ferramenta de apresentação utilizada pelo descritor.
     *
     * @return
     *          String contendo o nome da ferramenta.
     */
    public String getPlayer() {
        if(player != null)
            return player.getValue();
        else
            return null;
    }


    /**
     * Atribui uma duração explícita ao descritor.
     *
     * @param explicitDur
     *          inteiro representando a duração a ser usada pelo descritor em segundos.
     */
    public void setExplicitDur(TimeType explicitDur) {
        TimeType aux = this.explicitDur;
        this.explicitDur = explicitDur;
        impl.notifyAltered(NCLElementAttributes.EXPLICITDUR, aux, explicitDur);
    }


    /**
     * Retorna a duração explícita definida no descritor.
     *
     * @return
     *          inteiro representando a duração definida no descritor em segundos.
     */
    public TimeType getExplicitDur() {
        return explicitDur;
    }


    /**
     * Indica se o último quadro de uma mídia continuará sendo apresentando após seu fim.
     * Este último quadro será apresentando até o fim da duração definida no descritor.
     *
     * @param freeze
     *          booleano definindo se o último quadro deverá ser exibido continuamente.
     */
    public void setFreeze(Boolean freeze) {
        Boolean aux = this.freeze;
        this.freeze = freeze;
        impl.notifyAltered(NCLElementAttributes.FREEZE, aux, freeze);
    }


    /**
     * Retorna o valor do atributo freeze que indica se o último quadro de uma mídia
     * continuará sendo apresentando após seu fim.
     *
     * @return
     *          Verdadeiro se o último quadro deverá ser exibido continuamente.
     */
    public Boolean getFreeze() {
        return freeze;
    }

    
    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * a esquerda do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveLeft(T descriptor) {
        T aux = this.moveLeft;
        this.moveLeft = descriptor;
        impl.notifyAltered(NCLElementAttributes.MOVELEFT, aux, descriptor);
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * a esquerda do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public T getMoveLeft() {
        return moveLeft;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * a direita do controle remoto for pressionada e o foco estiver neste descritor.
     * 
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveRight(T descriptor) {
        T aux = this.moveRight;
        this.moveRight = descriptor;
        impl.notifyAltered(NCLElementAttributes.MOVERIGHT, aux, descriptor);
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * a direita do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public T getMoveRight() {
        return moveRight;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * cima do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveUp(T descriptor) {
        T aux = this.moveUp;
        this.moveUp = descriptor;
        impl.notifyAltered(NCLElementAttributes.MOVEUP, aux, descriptor);
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * cima do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public T getMoveUp() {
        return moveUp;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * baixo do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveDown(T descriptor) {
        T aux = this.moveDown;
        this.moveDown = descriptor;
        impl.notifyAltered(NCLElementAttributes.MOVEDOWN, aux, descriptor);
    }

    
    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * baixo do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public T getMoveDown() {
        return moveDown;
    }  
    
    
    /**
     * Atribui um índice de foco para o descritor.
     *
     * @param focusIndex
     *          inteiro representando o índice de foco do descritor.
     */
    public void setFocusIndex(Integer focusIndex) {
        Integer aux = this.focusIndex;
        this.focusIndex = focusIndex;
        impl.notifyAltered(NCLElementAttributes.FOCUSINDEX, aux, focusIndex);
    }


    /**
     * Retorna o índice de foco atribuído ao descritor.
     *
     * @return
     *          inteiro representando o índice de foco do descritor.
     */
    public Integer getFocusIndex() {
        return focusIndex;
    }


    /**
     * Atribui uma cor para a borda do descritor quando este está em foco.
     *
     * @param focusBorderColor
     *          cor da borda do descritor.
     */
    public void setFocusBorderColor(NCLColor focusBorderColor) {
        NCLColor aux = this.focusBorderColor;
        this.focusBorderColor = focusBorderColor;
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERCOLOR, aux, focusBorderColor);
    }


    /**
     * Retorna a cor para a borda do descritor quando este está em foco utilizada.
     * 
     * @return
     *          cor da borda do descritor.
     */
    public NCLColor getFocusBorderColor() {
        return focusBorderColor;
    }

    
    /**
     * Artribui a largura da borda que é apresentada quando o descritor está em foco.
     *
     * @param focusBorderWidth
     *          inteiro representando a largura da borda em pixels.
     */
    public void setFocusBorderWidth(Integer focusBorderWidth) {
        Integer aux = this.focusBorderWidth;
        this.focusBorderWidth = focusBorderWidth;
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERWIDTH, aux, focusBorderWidth);
    }


    /**
     * Retorna a largura da borda que é apresentada quando o descritor está em foco.
     *
     * @return
     *          inteiro representando a largura da borda em pixels.
     */
    public Integer getFocusBorderWidth() {
        return focusBorderWidth;
    }


    /**
     * Atribui um grau de transparência à borda que é apresentada quando o descritor está em foco.
     *
     * @param focusBorderTransparency
     *          inteiro representando a percentagem relativa a transparência da borda.
     *          o inteiro deve estar no intervalo [0,100].
     * @throws java.lang.IllegalArgumentException
     *          se o valor estiver fora do intervalo.
     */
    public void setFocusBorderTransparency(PercentageType focusBorderTransparency) {
        PercentageType aux = this.focusBorderTransparency;
        this.focusBorderTransparency = focusBorderTransparency;
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERTRANSPARENCY, aux, focusBorderTransparency);
    }


    /**
     * Retorna o grau de transparência à borda que é apresentada quando o descritor está em foco.
     *
     * @return
     *          inteiro representando a percentagem relativa a transparência da borda.
     *          o inteiro está no intervalo [0,100].
     */
    public PercentageType getFocusBorderTransparency() {
        return focusBorderTransparency;
    }


    /**
     * Atribui a URI do conteúdo alternativo que é exibido quando o descritor está em foco.
     *
     * @param focusSrc
     *          String contendo a URI do conteúdo alternativo.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida.
     *
     * @see java.net.URI
     */
    public void setFocusSrc(SrcType focusSrc) {
        SrcType aux = this.focusSrc;
        this.focusSrc = focusSrc;
        impl.notifyAltered(NCLElementAttributes.FOCUSSRC, aux, focusSrc);
    }


    /**
     * Retorna a URI do conteúdo alternativo que é exibido quando o descritor está em foco.
     *
     * @return
     *          String contendo a URI do conteúdo alternativo.
     */
    public SrcType getFocusSrc() {
        return focusSrc;
    }


    /**
     * Atribui a URI do conteúdo alternativo que é exibido quando o descritor é selecionado.
     *
     * @param focusSelSrc
     *          String contendo a URI do conteúdo alternativo.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida (@see java.net.URI).
     */
    public void setFocusSelSrc(SrcType focusSelSrc) {
        SrcType aux = this.focusSelSrc;
        this.focusSelSrc = focusSelSrc;
        impl.notifyAltered(NCLElementAttributes.FOCUSSELSRC, aux, focusSelSrc);
    }


    /**
     * Retorna a URI do conteúdo alternativo que é exibido quando o descritor é selecionado.
     *
     * @return
     *          String contendo a URI do conteúdo alternativo.
     */    
    public SrcType getFocusSelSrc() {
        return focusSelSrc;
    }


    /**
     * Atribui a cor da borda que é exibida quando o descritor é selecionado.
     *
     * @param selBorderColor
     *          cor da borda do descritor.
     */
    public void setSelBorderColor(NCLColor selBorderColor) {
        NCLColor aux = this.selBorderColor;
        this.selBorderColor = selBorderColor;
        impl.notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, selBorderColor);
    }


    /**
     * Retorna a cor da borda que é exibida quando o descritor é selecionado.
     *
     * @return
     *          cor da borda do descritor.
     */
    public NCLColor getSelBorderColor() {
        return selBorderColor;
    }


    /**
     * Atribui uma transição de entrada ao descritor.
     *
     * @param transIn
     *          elemento representando uma transição.
     */
    public void setTransIn(Et transIn) {
        Et aux = this.transIn;
        this.transIn = transIn;
        impl.notifyAltered(NCLElementAttributes.TRANSIN, aux, transIn);
    }


    /**
     * Retorna a transição de entrada do descritor.
     *
     * @return
     *          elemento representando uma transição.
     */
    public Et getTransIn() {
        return transIn;
    }


    /**
     * Atribui uma transição de saida ao descritor.
     *
     * @param transOut
     *          elemento representando uma transição.
     */
    public void setTransOut(Et transOut) {
        Et aux = this.transOut;
        this.transOut = transOut;
        impl.notifyAltered(NCLElementAttributes.TRANSOUT, aux, transOut);
    }


    /**
     * Retorna a transição de saida do descritor.
     *
     * @return
     *          elemento representando uma transição.
     */
    public Et getTransOut() {
        return transOut;
    }


    /**
     * Atribui uma região ao descritor.
     *
     * @param region
     *          elemento representando uma região.
     */
    public void setRegion(Er region) {
        Er aux = this.region;
        this.region = region;
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
    }


    /**
     * Retorna a região relacionada ao descritor.
     *
     * @return
     *          elemento representando uma região.
     */
    public Er getRegion() {
        return region;
    }


    /**
     * Adiciona um parâmetro ao descritor.
     *
     * @param descriptorParam
     *          elemento representando o parâmetro a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addDescriptorParam(Ep descriptorParam) throws XMLException {
        if(params.add(descriptorParam, (T) this)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORPARAM, descriptorParam);
            return true;
        }
        return false;
    }


    /**
     * Remove um parâmetro do descritor.
     *
     * @param descriptorParam
     *          elemento representando o parâmetro a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptorParam(Ep descriptorParam) throws XMLException {
        if(params.remove(descriptorParam)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORPARAM, descriptorParam);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o descritor contém um parâmetro.
     *
     * @param descriptorParam
     *          elemento representando o parâmetro a ser verificado.
     */
    public boolean hasDescriptorParam(Ep descriptorParam) throws XMLException {
        return params.contains(descriptorParam);
    }


    /**
     * Verifica se o descritor possui algum parâmetro.
     *
     * @return
     *          verdadeiro se o descritor possui algum parâmetro.
     */
    public boolean hasDescriptorParam() {
        return !params.isEmpty();
    }


    /**
     * Retorna os parâmetros do descritor.
     *
     * @return
     *          lista contendo os parâmetros do descritor.
     */
    public ElementList<Ep, T> getDescriptorParams() {
        return params;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
