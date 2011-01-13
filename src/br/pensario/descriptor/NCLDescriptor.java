package br.pensario.descriptor;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLColor;
import br.pensario.region.NCLRegion;
import br.pensario.transition.NCLTransition;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>descriptor</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um descritor em um documento NCL.<br>
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
        return params.add(descriptorParam);
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
        return params.remove(descriptorParam);
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
        boolean valid = true;

        valid &= (getId() != null);

        if(hasDescriptorParam()){
            for(P param : params)
                valid &= param.validate();
        }

        return valid;
    }
}
