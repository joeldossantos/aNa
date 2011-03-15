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
 *        This product includes the Api for NCL Authoring - aNa, developed
 *        by MídiaCom Lab (www.midiacom.uff.br) and its contributors.
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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLMediaType;
import br.uff.midiacom.ana.node.NCLMedia;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma âncora de um nó.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
     * Construtor do elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLArea(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
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
        if(getId() != null)
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


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }

        if(getPosition() != null && getText() == null){
            addWarning("Atributo position deve ser especificado em conjunto com atributo text.");
            valid = false;
        }


        int i = 0;
        if(getCoords() != null){
            i++;
            if(getParent() != null && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.IMAGE) && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.VIDEO)){
                addWarning("Elemento media não suporta âncora espacial.");
                valid = false;
            }
        }
        if(getBegin() != null || getEnd() != null){
            i++;
            if(getParent() != null && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.AUDIO) && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.VIDEO)){
                addWarning("Elemento media não suporta âncora temporal.");
                valid = false;
            }
        }
        if(getText() != null || getPosition() != null){
            i++;
            if(getParent() != null && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.TEXT)){
                addWarning("Elemento media não suporta âncora textual.");
                valid = false;
            }
        }
        if(getFirst() != null || getLast() != null){
            i++;
            if(getParent() != null && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.AUDIO) && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.VIDEO)){
                addWarning("Elemento media não suporta âncora temporal.");
                valid = false;
            }
        }
        if(getLabel() != null){
            i++;
            if(getParent() != null && !((NCLMedia)getParent()).getMediaType().equals(NCLMediaType.PROCEDURAL)){
                addWarning("Elemento media não suporta âncora pocedural.");
                valid = false;
            }
        }
        if(i > 1){
            addWarning("A âncora não pode ter mais de um tipo.");
            valid = false;
        }


        //@todo: validar begin com end
        //@todo: validar first com last

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("coords")){
                    Vector<Integer>  coord = new Vector<Integer>();
                    String value = attributes.getValue(i);
                    while(value.contains(",")){
                        int index = value.indexOf(",");
                        coord.add(new Integer(value.substring(0, index)));
                        value = value.substring(index + 1);
                    }
                    coord.add(new Integer(value));
                    int[] a = new int[coord.size()];
                    for(int k = 0; k < coord.size(); k++)
                        a[k] = (int) coord.elementAt(k);
                    setCoords(a);
                }
                else if(attributes.getLocalName(i).equals("begin"))
                    setBegin(new NCLTime(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("end"))
                    setEnd(new NCLTime(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("text"))
                    setText(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("position"))
                    setPosition(new Integer(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("first"))
                    setFirst(new NCLSample(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("last"))
                    setLast(new NCLSample(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("label"))
                    setLabel(attributes.getValue(i));
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }
}
