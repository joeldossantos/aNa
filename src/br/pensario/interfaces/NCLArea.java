package br.pensario.interfaces;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;


/**
 * Esta classe define o elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma âncora de um nó.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLArea<I extends NCLInterface> extends NCLIdentifiableElement implements NCLInterface<I> {

    private int[] coords;
    private NCLTime begin;
    private NCLTime end;
    private String text;
    private Integer position;
    private NCLSample first;
    private NCLSample last;
    private String label;
    
    
    /**
     * Construtor do elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da âncora.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da âncora for inválido.
     */
    public NCLArea(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Define uma âncora de conteudo representando porções espaciais.
     * x1,y1,x2,y2,...,xN,yN
     * 
     * @param coords
     *          coordenadas da âncora espacial.
     * @throws java.lang.IllegalArgumentException
     *          se alguma das coordenadas for inválida.
     */
    public void setCoords(int[] coords) throws IllegalArgumentException {
        if(coords != null){
            for(int coord : coords){
                if(coord < 0)
                    throw new IllegalArgumentException("Invalid coordenate: " + coord);
            }
        }

        this.coords = coords;
    }
    
    
    /**
     * Retorna as coordenadas espaciais da âncora.
     * 
     * @return
     *          array de inteiros com as coordenadas.
     */
    public int[] getCoords() {
        return coords;
    }
    
    
    /**
     * Define uma âncora de conteúdo representando porções temporais.
     * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
     * 
     * @param begin
     *          tempo de inicio da âncora temporal.
     *
     * @see NCLTime
     */
    public void setBegin(NCLTime begin) {
        this.begin = begin;
    }
    
    
    /**
     * Retorna o valor de inicio da âncora.
     * 
     * @return
     *          elemento representando o valor de tempo.
     */
    public NCLTime getBegin() {
        return begin;
    }
    
    
    /**
     * Define uma âncora de conteúdo representando porções temporais.
     * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
     * 
     * @param end
     *          tempo de fim da âncora temporal.
     *
     * @see NCLTime
     */
    public void setEnd(NCLTime end) {
        this.end = end;
    }
    
    
    /**
     * Retorna o valor de fim da âncora.
     * 
     * @return
     *          elemento representando o valor de tempo.
     */
    public NCLTime getEnd() {
        return end;
    }
    
    
    /**
     * Define uma ancora textual.
     * 
     * @param text
     *          padrao de texto usado como âncora textual.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setText(String text) throws IllegalArgumentException {
        if(text != null && "".equals(text.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.text = text;
    }
    
    
    /**
     * Retorna o conteúdo da ancora textual.
     * 
     * @return
     *          String com conteúdo da âncora textual.
     */
    public String getText() {
        return text;
    }
    
    
    /**
     * Define a posição do texto da ancora textual.
     * 
     * @param position
     *          posicao do texto.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for um valor negativo.
     */
    public void setPosition(Integer position) throws IllegalArgumentException {
        if(position != null && position < 0)
            throw new IllegalArgumentException("Invalid position");

        this.position = position;
    }
    
    
    /**
     * Retorna a posição do texto da ancora textual.
     * 
     * @return
     *          inteiro com a posicao do texto.
     */
    public Integer getPosition() {
        return position;
    }
    
    
    /**
     * Define uma ancora de conteudo com base em uma amostra.
     * 
     * @param first
     *          amostra de inicio da ancora.
     *
     * @see NCLSample
     */
    public void setFirst(NCLSample first) {
        this.first = first;
    }
    
    
    /**
     * Retorna o início da ancora de conteudo com base em uma amostra.
     * 
     * @return
     *          amostra de inicio da ancora.
     */
    public NCLSample getFirst() {
        return first;
    }
    
    
    /**
     * Define uma ancora de conteudo com base em uma amostra.
     *
     * @param last
     *          amostra de fim da ancora.
     *
     * @see NCLSample
     */
    public void setLast(NCLSample last) {
        this.last = last;
    }
    
    
    /**
     * Retorna o fim da ancora de conteudo com base em uma amostra.
     *
     * @return
     *          amostra de fim da ancora.
     */
    public NCLSample getLast() {
        return last;
    }
    
    
    /**
     * define uma ancora de conteudo baseada no atributo label,
     * que especifica uma cadeia de caracteres que deve ser utilizada
     * pelo exibidor de midias para identificar uma regiao de conteudo.
     * 
     * @param label
     *          identificacao da região do conteúdo.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setLabel(String label) throws IllegalArgumentException {
        if(label != null && "".equals(label.trim()))
            throw new IllegalArgumentException("Empty label String");

        this.label = label;
    }
    
    
    /**
     * Retorna o label da ancora de conteudo.
     * 
     * @return
     *          String contendo a identificacao da região do conteúdo.
     */
    public String getLabel() {
        return label;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
                
        // <area> element and attributes declaration
        content = space + "<area";
        content += " id='" + getId() + "'";
        if(getCoords() != null)
            content += " coords='" + coordsToString() + "'";
        if(getBegin() != null)
            content += " begin='" + getBegin().toString() + "'";
        if(getEnd() != null)
            content += " end='" + getEnd().toString() + "'";
        if(getText() != null)
            content += " text='" + getText() + "'";
        if(getPosition() != null)
            content += " position='" + getPosition() + "'";
        if(getFirst() != null)
            content += " first='" + getFirst().toString() + "'";
        if(getLast() != null)
            content += " last='" + getLast().toString() + "'";
        if(getLabel() != null)
            content += " label='" + getLabel() + "'";
        content += "/>\n";
        
        return content;
    }
    
    
    /**
     * Transforma as coordenadas em uma string.
     * 
     * @return
     *          String representando as coordenadas espaciais.
     */
    private String coordsToString() {
        String result = "";
        for(int i = 0; i < coords.length; i++){
            result += coords[i];
            if(i < coords.length - 1)
                result += ",";
        }
        return result;
    }
    
    
    public int compareTo(I other) {
        return getId().compareTo(other.getId());
    }
}
