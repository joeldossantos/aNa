package br.pensario.region;

import br.pensario.NCLElement;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>region</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento define uma região de exibição de conteúdo.<br>
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
public class NCLRegion<R extends NCLRegion> extends NCLIdentifiableElement implements Comparable<R> {

    private String title;
    private Integer left;
    private Integer right;
    private Integer top;
    private Integer bottom;
    private Integer height;
    private Integer width;
    private Integer zIndex;

    private boolean relativeLeft;
    private boolean relativeRight;
    private boolean relativeTop;
    private boolean relativeBottom;
    private boolean relativeHeight;
    private boolean relativeWidth;

    private Set<R> regions = new TreeSet<R>();

    private boolean insideRegion;


    /**
     * Construtor do elemento <i>region</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da região.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador for inválido.
     */
    public NCLRegion(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>region</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLRegion(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideRegion = false;
    }


    /**
     * Atribui um título para a região.
     * 
     * @param title
     *          String representando o títuloo da região.
     * @throws java.lang.IllegalArgumentException
     *          se o título for uma String vazia.
     */
    public void setTitle(String title) throws IllegalArgumentException {
        if(title != null && "".equals(title.trim()))
            throw new IllegalArgumentException("Empty title String");

        this.title = title;
    }


    /**
     * Retorna o título da região.
     * 
     * @return
     *          String representando o títuloo da região.
     */
    public String getTitle() {
        return title;
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
    public void setLeft(Integer left, boolean relative) throws IllegalArgumentException {
        setRelativeLeft(relative);

        if(isRelativeLeft() && left != null && (left < 0 || left > 100))
            throw new IllegalArgumentException("Invalid percentage position value (%left= " +
                    left + "). It must be between 0 and 100.");

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
     * @see NCLRegion#setLeft
     * @see NCLRegion#isRelativeLeft
     */
    public Integer getLeft() {
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
    public void setRight(Integer right, boolean relative) throws IllegalArgumentException {
        setRelativeRight(relative);

        if(isRelativeRight() && right != null && (right < 0 || right > 100))
            throw new IllegalArgumentException("Invalid percentage position value (%right= " +
                    right + "). It must be between 0 and 100.");

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
     * @see NCLRegion#setRight
     * @see NCLRegion#isRelativeRight
     */
    public Integer getRight() {
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
    public void setTop(Integer top, boolean relative) throws IllegalArgumentException {
        setRelativeTop(relative);

        if(isRelativeTop() && top != null && (top < 0 || top > 100))
            throw new IllegalArgumentException("Invalid percentage position value (%top= " +
                    top + "). It must be between 0 and 100.");

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
     * @see NCLRegion#setRight
     * @see NCLRegion#isRelativeRight
     */
    public Integer getTop() {
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
    public void setBottom(Integer bottom, boolean relative) throws IllegalArgumentException {
        setRelativeBottom(relative);

        if(isRelativeBottom() && bottom != null && (bottom < 0 || bottom > 100))
            throw new IllegalArgumentException("Invalid percentage position value (%bottom= " +
                    bottom + "). It must be between 0 and 100.");


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
     * @see NCLRegion#setBottom
     * @see NCLRegion#isRelativeBottom
     */
    public Integer getBottom() {
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
    public void setHeight(Integer height, boolean relative) throws IllegalArgumentException {
        setRelativeHeight(relative);

        if(isRelativeHeight() && height != null && (height < 0 || height > 100))
            throw new IllegalArgumentException("Valor não percentual para atributo relativo de posicionamento (%height= "
                            + height + ")");

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
     * @see NCLRegion#setHeight
     * @see NCLRegion#isRelativeHeight
     */
    public Integer getHeight() {
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
    public void setWidth(Integer width, boolean relative) throws IllegalArgumentException {
        setRelativeWidth(relative);

        if(isRelativeWidth() && width != null && (width < 0 || width > 100))
            throw new IllegalArgumentException("Valor não porcentual para atributo relativo de posicionamento (%width= "
                            + width + ")");

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
     * @see NCLRegion#setWidth
     * @see NCLRegion#isRelativeWidth
     */
    public Integer getWidth() {
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
        if(zIndex != null && zIndex < 0)
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
     * Determina se a posição a esquerda da região é uma porcentagem.
     *
     * @param relativeLeft
     *          booleano indicando se a posição a esquerda da região é uma porcentagem.
     */
    private void setRelativeLeft(boolean relativeLeft) {
        this.relativeLeft = relativeLeft;
    }


    /**
     * Retorna se a posição a esquerda da região é uma porcentagem.
     *
     * @return
     *          booleano indicando se a posição a esquerda da região é uma porcentagem.
     */
    private boolean isRelativeLeft() {
        return relativeLeft;
    }


    /**
     * Determina se a posição a direita da região é uma porcentagem.
     *
     * @param relativeRight
     *          booleano indicando se a posição a direita da região é uma porcentagem.
     */
    private void setRelativeRight(boolean relativeRight) {
        this.relativeRight = relativeRight;
    }


    /**
     * Retorna se a posição a direita da região é uma porcentagem.
     *
     * @return
     *          booleano indicando se a posição a direita da região é uma porcentagem.
     */
    private boolean isRelativeRight() {
        return relativeRight;
    }


    /**
     * Determina se a posição de topo da região é uma porcentagem.
     *
     * @param relativeTop
     *          booleano indicando se a posição de topo da região é uma porcentagem.
     */
    private void setRelativeTop(boolean relativeTop) {
        this.relativeTop = relativeTop;
    }


    /**
     * Retorna se a posição de topo da região é uma porcentagem.
     *
     * @return
     *          booleano indicando se a posição de topo da região é uma porcentagem.
     */
    private boolean isRelativeTop() {
        return relativeTop;
    }


    /**
     * Determina se a posição inferior da região é uma porcentagem.
     *
     * @param relativeBottom
     *          booleano indicando se a posição inferior da região é uma porcentagem.
     */
    private void setRelativeBottom(boolean relativeBottom) {
        this.relativeBottom = relativeBottom;
    }


    /**
     * Retorna se a posição inferior da região é uma porcentagem.
     *
     * @return
     *          booleano indicando se a posição inferior da região é uma porcentagem.
     */
    private boolean isRelativeBottom() {
        return relativeBottom;
    }


    /**
     * Determina se a altura da região é uma porcentagem.
     *
     * @param relativeHeight
     *          booleano indicando se a altura da região é uma porcentagem.
     */
    private void setRelativeHeight(boolean relativeHeight) {
        this.relativeHeight = relativeHeight;
    }


    /**
     * Retorna se a altura da região é uma porcentagem.
     *
     * @return
     *          booleano indicando se a altura da região é uma porcentagem.
     */
    private boolean isRelativeHeight() {
        return relativeHeight;
    }


    /**
     * Determina se a largura da região é uma porcentagem.
     *
     * @param relativeWidth
     *          booleano indicando se a largura da região é uma porcentagem.
     */
    private void setRelativeWidth(boolean relativeWidth) {
        this.relativeWidth = relativeWidth;
    }


    /**
     * Retorna se a largura da região é uma porcentagem.
     *
     * @return
     *          booleano indicando se a largura da região é uma porcentagem.
     */
    private boolean isRelativeWidth() {
        return relativeWidth;
    }


    /**
     * Adiciona uma região filha à região. A região filha é considerada uma região interna
     * tendo seus parâmetros relativos a esta região.
     *
     * @param child
     *          elemento representando a região a ser adicionada.
     * @return
     *          verdadeiro se a região foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRegion(R child) {
        return regions.add(child);
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
    public boolean removeRegion(String id) {
        for(R region : regions){
            if(region.getId().equals(id))
                return regions.remove(region);
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
    public boolean removeRegion(R region) {
        return regions.remove(region);
    }


    /**
     * Verifica se a região possui uma região como filha.
     *
     * @param child
     *          elemento representando a região a ser verificada.
     * @return
     *          verdadeiro se a região existir.
     */
    public boolean hasRegion(R child) {
        return regions.contains(child);
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
     *          objeto Iterable contendo as regiões internas.
     */
    public Iterable<R> getRegions() {
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
        if(getLeft() != null) {
            String percent = "";
            if(isRelativeLeft())
                percent = "%";
            content += " left='" + getLeft() + percent + "'";
        }
        if(getRight() != null) {
            String percent = "";
            if(isRelativeRight())
                percent = "%";
            content += " right='" + getRight() + percent + "'";
        }
        if(getTop() != null) {
            String percent = "";
            if(isRelativeTop())
                percent = "%";
            content += " top='" + getTop() + percent + "'";
        }
        if(getBottom() != null) {
            String percent = "";
            if(isRelativeBottom())
                percent = "%";
            content += " bottom='" + getBottom() + percent + "'";
        }
        if(getHeight() != null) {
            String percent = "";
            if(isRelativeHeight())
                percent = "%";
            content += " height='" + getHeight() + percent + "'";
        }
        if(getWidth() != null) {
            String percent = "";
            if(isRelativeWidth())
                percent = "%";
            content += " width='" + getWidth() + percent + "'";
        }
        if(getzIndex() != null)
            content += " zIndex='" + getzIndex() + "'";
        if(getTitle() != null)
            content += " title='" + getTitle() + "'";
        if(hasRegion()) {
            content += ">\n";

            for(R region : getRegions())
                content += region.parse(ident + 1);
            
            content += space + "</region>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public int compareTo(R other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);

        if(hasRegion()){
            for(R region : regions)
                valid &= region.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(!insideRegion){
                insideRegion = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("left")){
                        String value = attributes.getValue(i);
                        Boolean isRelative = attributes.getValue(i).contains("%");
                        if(isRelative)
                            value = value.substring(0, value.length() - 1);
                        setLeft(new Integer(value), isRelative);
                    }
                    else if(attributes.getLocalName(i).equals("right")){
                        String value = attributes.getValue(i);
                        Boolean isRelative = attributes.getValue(i).contains("%");
                        if(isRelative)
                            value = value.substring(0, value.length() - 1);
                        setRight(new Integer(value), isRelative);
                    }
                    else if(attributes.getLocalName(i).equals("top")){
                        String value = attributes.getValue(i);
                        Boolean isRelative = attributes.getValue(i).contains("%");
                        if(isRelative)
                            value = value.substring(0, value.length() - 1);
                        setTop(new Integer(value), isRelative);
                    }
                    else if(attributes.getLocalName(i).equals("bottom")){
                        String value = attributes.getValue(i);
                        Boolean isRelative = attributes.getValue(i).contains("%");
                        if(isRelative)
                            value = value.substring(0, value.length() - 1);
                        setBottom(new Integer(value), isRelative);
                    }
                    else if(attributes.getLocalName(i).equals("height")){
                        String value = attributes.getValue(i);
                        Boolean isRelative = attributes.getValue(i).contains("%");
                        if(isRelative)
                            value = value.substring(0, value.length() - 1);
                        setHeight(new Integer(value), isRelative);
                    }
                    else if(attributes.getLocalName(i).equals("width")){
                        String value = attributes.getValue(i);
                        Boolean isRelative = attributes.getValue(i).contains("%");
                        if(isRelative)
                            value = value.substring(0, value.length() - 1);
                        setWidth(new Integer(value), isRelative);
                    }
                    else if(attributes.getLocalName(i).equals("zIndex"))
                        setzIndex(new Integer(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("title"))
                        setTitle(attributes.getValue(i));
                }
            }
            else{
                // Region e um elemento interno
                NCLRegion r = new NCLRegion(getReader(), this);
                r.startElement(uri, localName, qName, attributes);
                addRegion((R) r); //TODO: retirar o cast. Como melhorar isso?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            //TODO: fazer o que?
        }
    }

/*TODO: retirar isso
    @Override
    public void endElement(String uri, String localName, String qName) {
        getReader().setContentHandler(getParent());
        insideRegion = false;
    }*/
}
