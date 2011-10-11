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

import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.param.NCLDescriptorParamPrototype;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.number.PercentageType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class NCLDescriptorPrototype<T extends NCLLayoutDescriptor, P extends NCLElement, Er extends NCLRegionPrototype, Ed extends NCLDescriptorPrototype, Et extends NCLTransitionPrototype, Ep extends NCLDescriptorParamPrototype> extends NCLIdentifiableElementPrototype<T, P> implements NCLLayoutDescriptor<T, P> {

    protected StringType player;
    protected TimeType explicitDur;
    protected Boolean freeze;
    protected Ed moveLeft;
    protected Ed moveRight;
    protected Ed moveUp;
    protected Ed moveDown;
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


    /**
     * Construtor do elemento <i>descriptor</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do descritor não for válido.
     */
    public NCLDescriptorPrototype(String id) throws XMLException {
        setId(id);
        params = new ElementList<Ep, T>();
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
        this.player = new StringType(player);
    }


    /**
     * Retorna o node da ferramenta de apresentação utilizada pelo descritor.
     *
     * @return
     *          String contendo o nome da ferramenta.
     */
    public String getPlayer() {
        return player.getValue();
    }


    /**
     * Atribui uma duração explícita ao descritor.
     *
     * @param explicitDur
     *          inteiro representando a duração a ser usada pelo descritor em segundos.
     */
    public void setExplicitDur(TimeType explicitDur) {
        this.explicitDur = explicitDur;
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
        this.freeze = freeze;
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
    public void setMoveLeft(Ed descriptor) {
        this.moveLeft = descriptor;
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * a esquerda do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public Ed getMoveLeft() {
        return moveLeft;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * a direita do controle remoto for pressionada e o foco estiver neste descritor.
     * 
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveRight(Ed descriptor) {
        this.moveRight = descriptor;
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * a direita do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public Ed getMoveRight() {
        return moveRight;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * cima do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveUp(Ed descriptor) {
        this.moveUp = descriptor;
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * cima do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public Ed getMoveUp() {
        return moveUp;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * baixo do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveDown(Ed descriptor) {
        this.moveDown = descriptor;
    }

    
    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * baixo do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public Ed getMoveDown() {
        return moveDown;
    }  
    
    
    /**
     * Atribui um índice de foco para o descritor.
     *
     * @param focusIndex
     *          inteiro representando o índice de foco do descritor.
     */
    public void setFocusIndex(Integer focusIndex) {
        this.focusIndex = focusIndex;
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
        this.focusBorderColor = focusBorderColor;
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
        this.focusBorderWidth = focusBorderWidth;
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
        this.focusBorderTransparency = focusBorderTransparency;
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
        this.focusSrc = focusSrc;
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
        this.focusSelSrc = focusSelSrc;
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
        this.selBorderColor = selBorderColor;
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
        this.transIn = transIn;
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
        this.transOut = transOut;
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
        this.region = region;
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
        return params.add(descriptorParam, (T) this);
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
        return params.remove(descriptorParam);
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


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<descriptor";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getRegion() != null)
            content += " region='" + getRegion().getId() + "'";
        if(getExplicitDur() != null)
            content += " explicitDur='" + getExplicitDur() + "s'";
        if(getFreeze() != null)
            content += " freeze='" + getFreeze().toString() + "'";
        if(getPlayer() != null)
            content += " player='" + getPlayer() + "'";
        if(getMoveLeft() != null)
            content += " moveLeft='" + getMoveLeft().getFocusIndex() + "'";
        if(getMoveRight() != null)
            content += " moveRight='" + getMoveRight().getFocusIndex() + "'";
        if(getMoveDown() != null)
            content += " moveDown='" + getMoveDown().getFocusIndex() + "'";
        if(getMoveUp() != null)
            content += " moveUp='" + getMoveUp().getFocusIndex() + "'";
        if(getFocusIndex() != null)
            content += " focusIndex='" + getFocusIndex() + "'";
        if(getFocusBorderColor() != null)
            content += " focusBorderColor='" + getFocusBorderColor().toString() + "'";
        if(getFocusBorderWidth() != null)
            content += " focusBorderWidth='" + getFocusBorderWidth() + "'";
        if(getFocusBorderTransparency() != null)
            content += " focusBorderTransparency='" + getFocusBorderTransparency() + "%'";
        if(getFocusSrc() != null)
            content += " focusSrc='" + getFocusSrc() + "'";
        if(getFocusSelSrc() != null)
            content += " focusSelSrc='" + getFocusSelSrc() + "'";
        if(getSelBorderColor() != null)
            content += " SelBorderColor='" + getSelBorderColor().toString() + "'";
        if(getTransIn() != null)
            content += " transIn='" + getTransIn().getId() + "'";
        if(getTransOut() != null)
            content += " transOut='" + getTransOut().getId() + "'";

        // Test if the descriptor has content
        if(hasDescriptorParam()){
            content += ">\n";

            for(Ep param : params)
                content += param.parse(ident + 1);

            content += space + "</descriptor>\n";
        }
        else
            content += "/>\n";

        
        return content;
    }
}
