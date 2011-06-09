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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLColor;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.transition.NCLTransition;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>descriptor</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um descritor em um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLDescriptor<D extends NCLDescriptor, R extends NCLRegion, L extends NCLLayoutDescriptor, T extends NCLTransition, P extends NCLDescriptorParam> extends NCLIdentifiableElement implements NCLLayoutDescriptor<L> {

    private String player;
    private Integer explicitDur;
    private Boolean freeze;
    private D moveLeft;
    private D moveRight;
    private D moveUp;
    private D moveDown;
    private Integer focusIndex;
    private NCLColor focusBorderColor;
    private Integer focusBorderWidth;
    private Integer focusBorderTransparency;
    private String focusSrc;
    private String focusSelSrc;
    private NCLColor selBorderColor;
    private T transIn;
    private T transOut;
    private R region;

    private Set<P> params = new TreeSet<P>();


    /**
     * Construtor do elemento <i>descriptor</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do descritor não for válido.
     */
    public NCLDescriptor(String id) throws NCLInvalidIdentifierException {        
        setId(id);
    }


    /**
     * Construtor do elemento <i>descriptor</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLDescriptor(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Identifica o node da ferramenta de apresentação utilizada pelo descritor.
     *
     * @param player
     *          nome da ferramenta a ser utilizada.
     * @throws java.lang.IllegalArgumentException
     *          se a String passada como parâmetro for vazia.
     */
    public void setPlayer(String player) throws IllegalArgumentException {
        if(player != null && "".equals(player.trim()))
            throw new IllegalArgumentException("Empty player String");

        this.player = player;
    }


    /**
     * Retorna o node da ferramenta de apresentação utilizada pelo descritor.
     *
     * @return
     *          String contendo o nome da ferramenta.
     */
    public String getPlayer() {
        return player;
    }


    /**
     * Atribui uma duração explícita ao descritor.
     *
     * @param explicitDur
     *          inteiro representando a duração a ser usada pelo descritor em segundos.
     */
    public void setExplicitDur(Integer explicitDur) {
        this.explicitDur = explicitDur;
    }


    /**
     * Retorna a duração explícita definida no descritor.
     *
     * @return
     *          inteiro representando a duração definida no descritor em segundos.
     */
    public Integer getExplicitDur() {
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
    public void setMoveLeft(D descriptor) {
        this.moveLeft = descriptor;
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * a esquerda do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public D getMoveLeft() {
        return moveLeft;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * a direita do controle remoto for pressionada e o foco estiver neste descritor.
     * 
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveRight(D descriptor) {
        this.moveRight = descriptor;
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * a direita do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public D getMoveRight() {
        return moveRight;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * cima do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveUp(D descriptor) {
        this.moveUp = descriptor;
    }


    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * cima do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public D getMoveUp() {
        return moveUp;
    }


    /**
     * Atribui qual o próximo descritor deverá receber foco quando a tecla seta para
     * baixo do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @param descriptor
     *          elemento representando o descritor que receberá foco.
     */
    public void setMoveDown(D descriptor) {
        this.moveDown = descriptor;
    }

    
    /**
     * Retorna o próximo descritor deverá receber foco quando a tecla seta para
     * baixo do controle remoto for pressionada e o foco estiver neste descritor.
     *
     * @return
     *          elemento representando o descritor que receberá foco.
     */
    public D getMoveDown() {
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
    public void setFocusBorderTransparency(Integer focusBorderTransparency) throws IllegalArgumentException {
        if(focusBorderTransparency != null && (focusBorderTransparency < 0 || focusBorderTransparency > 100))
            throw new IllegalArgumentException("Ilegal value");

        this.focusBorderTransparency = focusBorderTransparency;
    }


    /**
     * Retorna o grau de transparência à borda que é apresentada quando o descritor está em foco.
     *
     * @return
     *          inteiro representando a percentagem relativa a transparência da borda.
     *          o inteiro está no intervalo [0,100].
     */
    public Integer getFocusBorderTransparency() {
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
    public void setFocusSrc(String focusSrc) throws URISyntaxException {
        if(focusSrc != null)
            this.focusSrc = new URI(focusSrc).toString();

        this.focusSrc = focusSrc;
    }


    /**
     * Retorna a URI do conteúdo alternativo que é exibido quando o descritor está em foco.
     *
     * @return
     *          String contendo a URI do conteúdo alternativo.
     */
    public String getFocusSrc() {
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
    public void setFocusSelSrc(String focusSelSrc) throws URISyntaxException {
        if(focusSelSrc != null)
            this.focusSelSrc = new URI(focusSelSrc).toString();

        this.focusSelSrc = focusSelSrc;
    }


    /**
     * Retorna a URI do conteúdo alternativo que é exibido quando o descritor é selecionado.
     *
     * @return
     *          String contendo a URI do conteúdo alternativo.
     */    
    public String getFocusSelSrc() {
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
    public void setTransIn(T transIn) {
        this.transIn = transIn;
    }


    /**
     * Retorna a transição de entrada do descritor.
     *
     * @return
     *          elemento representando uma transição.
     */
    public T getTransIn() {
        return transIn;
    }


    /**
     * Atribui uma transição de saida ao descritor.
     *
     * @param transOut
     *          elemento representando uma transição.
     */
    public void setTransOut(T transOut) {
        this.transOut = transOut;
    }


    /**
     * Retorna a transição de saida do descritor.
     *
     * @return
     *          elemento representando uma transição.
     */
    public T getTransOut() {
        return transOut;
    }


    /**
     * Atribui uma região ao descritor.
     *
     * @param region
     *          elemento representando uma região.
     */
    public void setRegion(R region) {
        this.region = region;
    }


    /**
     * Retorna a região relacionada ao descritor.
     *
     * @return
     *          elemento representando uma região.
     */
    public R getRegion() {
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
    public boolean addDescriptorParam(P descriptorParam) {
        if(params.add(descriptorParam)){
            //Se descriptorParam existe, atribui este como seu parente
            if(descriptorParam != null)
                descriptorParam.setParent(this);

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
    public boolean removeDescriptorParam(P descriptorParam) {
        if(params.remove(descriptorParam)){
            //Se descriptorParam existe, retira o seu parentesco
            if(descriptorParam != null)
                descriptorParam.setParent(null);

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
    public boolean hasDescriptorParam(P descriptorParam) {
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
     *          objeto Iterable contendo os parâmetros do descritor.
     */
    public Iterable<P> getDescriptorParams() {
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

            for(P param : params)
                content += param.parse(ident + 1);

            content += space + "</descriptor>\n";
        }
        else
            content += "/>\n";

        
        return content;
    }


    public int compareTo(L other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }

        if(hasDescriptorParam()){
            for(P param : params){
                valid &= param.validate();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("descriptor")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("region"))
                        setRegion((R) new NCLRegion(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("explicitDur")){
                        String value = attributes.getValue(i);
                        if(value.contains("s"))
                            value = value.substring(0, value.length() - 1);
                        setExplicitDur(new Integer(value));
                    }
                    else if(attributes.getLocalName(i).equals("freeze"))
                        setFreeze(new Boolean(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("player"))
                        setPlayer(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("moveLeft")){
                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
                        d.setFocusIndex(new Integer(attributes.getValue(i)));
                        setMoveLeft((D) d);
                    }
                    else if(attributes.getLocalName(i).equals("moveRight")){
                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
                        d.setFocusIndex(new Integer(attributes.getValue(i)));
                        setMoveRight((D) d);
                    }
                    else if(attributes.getLocalName(i).equals("moveDown")){
                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
                        d.setFocusIndex(new Integer(attributes.getValue(i)));
                        setMoveDown((D) d);
                    }
                    else if(attributes.getLocalName(i).equals("moveUp")){
                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
                        d.setFocusIndex(new Integer(attributes.getValue(i)));
                        setMoveUp((D) d);
                    }
                    else if(attributes.getLocalName(i).equals("focusIndex"))
                        setFocusIndex(new Integer(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("focusBorderColor")){
                        for(NCLColor c : NCLColor.values()){
                            if(c.toString().equals(attributes.getValue(i)))
                                setFocusBorderColor(c);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("focusBorderWidth"))
                        setFocusBorderWidth(new Integer(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("focusBorderTransparency")){
                        String value = attributes.getValue(i);
                        if(value.contains("%"))
                            value = value.substring(0, value.length() - 1);
                        setFocusBorderTransparency(new Integer(value));
                    }
                    else if(attributes.getLocalName(i).equals("focusSrc"))
                        setFocusSrc(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("focusSelSrc"))
                        setFocusSelSrc(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("SelBorderColor")){
                        for(NCLColor c : NCLColor.values()){
                            if(c.toString().equals(attributes.getValue(i)))
                                setSelBorderColor(c);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("transIn"))
                        setTransIn((T) new NCLTransition(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("transOut"))
                        setTransOut((T) new NCLTransition(attributes.getValue(i)));//cast retirado na correcao das referencias
                }
            }
            else if(localName.equals("descriptorParam")){
                P child = createDescriptorParam();
                child.startElement(uri, localName, qName, attributes);
                addDescriptorParam(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
        catch(URISyntaxException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getRegion() != null)
                regionReference();

            if(getMoveUp() != null || getMoveDown() != null || getMoveLeft() != null || getMoveRight() != null)
                descriptorReference();

            if(getTransIn() != null)
                setTransIn(transitionReference(getTransIn()));

            if(getTransOut() != null)
                setTransOut(transitionReference(getTransOut()));
        }

        if(hasDescriptorParam()){
            for(P param : params){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
    }


    private void regionReference() {
        //Search for the interface inside the node
        NCLElement head = getParent();

        while(!(head instanceof NCLHead)){
            head = head.getParent();
            if(head == null){
                addWarning("Could not find a head");
                return;
            }
        }

        if(((NCLHead) head).getRegionBase() == null){
            addWarning("Could not find a regionBase");
            return;
        }

        R reg = findRegion(((NCLHead) head).getRegionBase().getRegions());
        if(reg == null)
            addWarning("Could not find region in regionBase with id: " + getRegion().getId());

        setRegion(reg);
    }


    private R findRegion(Iterable<R> regions) {
        for(R reg : regions){
            if(reg.getId().equals(getRegion().getId()))
                return (R) reg;
            else if(reg.hasRegion())
            {
                R r = findRegion(reg.getRegions());
                if(r != null)
                    return (R) r;
            }
        }

        return null;
    }


    private void descriptorReference() {
        //Search for the interface inside the node
        NCLElement head = getParent();

        while(!(head instanceof NCLHead)){
            head = head.getParent();
            if(head == null){
                addWarning("Could not find a head");
                return;
            }
        }

        if(((NCLHead) head).getDescriptorBase() == null){
            addWarning("Could not find a descriptorBase");
            return;
        }
        if(getMoveUp() != null)
            setMoveUp(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveUp()));
        if(getMoveDown() != null)
            setMoveDown(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveDown()));
        if(getMoveLeft() != null)
            setMoveLeft(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveLeft()));
        if(getMoveRight() != null)
            setMoveRight(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveRight()));
    }


    private D findDescriptor(Iterable<NCLLayoutDescriptor> descriptors, D move) {
        for(NCLLayoutDescriptor descriptor : descriptors){
            if(descriptor instanceof NCLDescriptorSwitch){
                NCLDescriptor desc = findDescriptor(((NCLDescriptorSwitch)descriptor).getDescriptors(), move);
                if(desc != null)
                    return (D) desc;
            }
            else{
                int indexa, indexb;
                if(((NCLDescriptor) descriptor).getFocusIndex() != null) indexa = ((NCLDescriptor) descriptor).getFocusIndex(); else indexa = 0;
                if(move.getFocusIndex() != null) indexb = move.getFocusIndex(); else indexb = 0;
                if(indexa == indexb)
                    return (D) descriptor;
            }
        }

        addWarning("Could not find descriptor in descriptorBase with focusIndex: " + move.getFocusIndex());
        return null;
    }


    private T transitionReference(T transition) {
        //Search for the interface inside the node
        NCLElement head = getParent();

        while(!(head instanceof NCLHead)){
            head = head.getParent();
            if(head == null){
                addWarning("Could not find a head");
                return null;
            }
        }

        if(((NCLHead) head).getTransitionBase() == null){
            addWarning("Could not find a transitionBase");
            return null;
        }

        Iterable<T> transitions = ((NCLHead) head).getTransitionBase().getTransitions();
        for(T trans : transitions){
            if(trans.getId().equals(transition.getId()))
             return (T) trans;
        }

        addWarning("Could not find transition in transitionBase with id: " + transition.getId());
        return null;
    }


    /**
     * Função de criação do elemento filho <i>descriptorParam</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>descriptorParam</i>.
     */
    protected P createDescriptorParam() {
        return (P) new NCLDescriptorParam(getReader(), this);
    }
}
