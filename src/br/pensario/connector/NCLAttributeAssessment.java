package br.pensario.connector;

import br.pensario.NCLValues.NCLAttributeType;
import br.pensario.NCLValues.NCLEventType;
import br.pensario.NCLValues.NCLKey;


/**
 * Esta classe define o elemento <i>attributeStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um atributo da assertiva de um conector de um documento NCL.<br>
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
public class NCLAttributeAssessment<A extends NCLAttributeAssessment, R extends NCLRole, P extends NCLConnectorParam> implements Comparable<A>{

    private R role;
    private NCLEventType eventType;
    private NCLKey key;
    private NCLAttributeType attributeType;
    private Integer offset;

    private P parKey;
    private P parOffset;
    
    
    /**
     * Determina o papel do atributo da assertiva.
     * 
     * @param role
     *          String representando o papel definido pela assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o role for uma String vazia.
     */
    public void setRole(R role) {
        this.role = role;
    }
    
    
    /**
     * Retorna o papel atribuido ao atributo da assertiva.
     * 
     * @return
     *          elemento representando o papel.
     */
    public R getRole() {
        return role;
    }
    
    
    /**
     * Determina o tipo do evento testado pelo atributo da assertiva.
     * 
     * @param eventType
     *          tipo do evento.
     */
    public void setEventType(NCLEventType eventType) {
        this.eventType = eventType;
    }
    
    
    /**
     * Retorna o tipo do evento testado pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando o tipo do evento.
     */
    public NCLEventType getEventType() {
        return eventType;
    }
    
    
    /**
     * Determina a tecla testada pelo atributo da assertiva.
     * 
     * @param key
     *          elemento representando a tecla.
     */
    public void setKey(NCLKey key) {
        this.key = key;
        this.parKey = null;
    }


    /**
     * Determina a tecla testada pelo atributo da assertiva.
     *
     * @param key
     *          parâmetro representando a tecla.
     */
    public void setKey(P key) {
        this.parKey = key;
        this.key = null;
    }
    
    
    /**
     * Retorna a tecla testada pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando a tecla.
     */
    public NCLKey getKey() {
        return key;
    }


    /**
     * Retorna a tecla testada pelo atributo da assertiva.
     *
     * @return
     *          parâmetro representando a tecla.
     */
    public P getParamKey() {
        return parKey;
    }
    
    
    /**
     * Determina o tipo do atributo testado pelo atributo da assertiva.
     * 
     * @param attributeType
     *          elemento representando o tipo do atributo.
     */
    public void setAttributeType(NCLAttributeType attributeType) {
        this.attributeType = attributeType;
    }
    
    
    /**
     * Retorna o tipo do atributo testado pelo atributo da assertiva.
     * 
     * @return
     *          elemento representando o tipo do atributo.
     */
    public NCLAttributeType getAttributeType() {
        return attributeType;
    }
    
    
    /**
     * Determina o offset de teste.
     * 
     * @param offset
     *          inteiro representando o valor do offset a ser utilizado no teste.
     * @throws java.lang.IllegalArgumentException
     *          se o offset for inválido.
     */
    public void setOffset(Integer offset) throws IllegalArgumentException {
        if(offset != null && offset < 0)
            throw new IllegalArgumentException("illegal offset");
        
        this.offset = offset;
        this.parOffset = null;
    }


    /**
     * Determina o offset de teste.
     *
     * @param offset
     *          parâmetro representando o valor do offset a ser utilizado no teste.
     */
    public void setOffset(P offset) {
        this.parOffset = offset;
        this.offset = null;
    }
    
    
    /**
     * Retorna o offset de teste.
     * 
     * @return
     *          inteiro representando o valor do offset a ser utilizado no teste.
     */
    public Integer getOffset() {
        return offset;
    }


    /**
     * Retorna o offset de teste.
     *
     * @return
     *          parâmetro representando o valor do offset a ser utilizado no teste.
     */
    public P getParamOffset() {
        return parOffset;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident< 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<attributeAssessment";

        content += " role='" + getRole().getName() + "'";

        if(getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";
        
        if(getKey() != null)
            content += " key='" + getKey().toString() + "'";
        if(getParamKey() != null)
            content += " key='$" + getParamKey().getId() + "'";
        
        if(getAttributeType() != null)
            content += " attributeType='" + getAttributeType().toString() + "'";        
        
        if(getOffset() != null)
            content += " offset='" + getOffset() + "'";
        if(getParamOffset() != null)
            content += " offset='$" + getParamOffset().getId() + "'";
        
        content += "/>\n";

        return content;
    }

    
    public int compareTo(A other) {
        int comp = 0;

        String this_att, other_att;

        // Compara pelo role
        if(getRole() == null) this_att = ""; else this_att = getRole().getName();
        if(other.getRole() == null) other_att = ""; else other_att = other.getRole().getName();
        comp = this_att.compareTo(other_att);

        // Compara pelo tipo do evento
        if(comp == 0){
            if(getEventType() == null) this_att = ""; else this_att = getEventType().toString();
            if(other.getEventType() == null) other_att = ""; else other_att = other.getEventType().toString();
            comp = this_att.compareTo(other_att);
        }

        // Compara pelo tipo do atributo
        if(comp == 0){
            if(getAttributeType() == null) this_att = ""; else this_att = getAttributeType().toString();
            if(other.getAttributeType() == null) other_att = ""; else other_att = other.getAttributeType().toString();
            comp = this_att.compareTo(other_att);
        }

        // Compara pelo offset
        if(comp == 0){
            int this_off, other_off;
            if(getOffset() == null) this_off = 0; else this_off = getOffset();
            if(other.getOffset() == null) other_off = 0; else other_off = other.getOffset();
            comp = this_off - other_off;
        }

        // Compara pelo offset (parametro)
        if(comp == 0){
            if(getParamOffset() == null && other.getParamOffset() == null)
                comp = 0;
            else if(getParamOffset() != null && other.getParamOffset() != null)
                comp = getParamOffset().compareTo(other.getParamOffset());
            else
                comp = 1;
        }

        // Compara pela tecla
        if(comp == 0){
            if(getKey() == null) this_att = ""; else this_att = getKey().toString();
            if(other.getKey() == null) other_att = ""; else other_att = other.getKey().toString();
            comp = this_att.compareTo(other_att);
        }

        // Compara pela tecla (parametro)
        if(comp == 0){
            if(getParamKey() == null && other.getParamKey() == null)
                comp = 0;
            else if(getParamKey() != null && other.getParamKey() != null)
                comp = getParamKey().compareTo(other.getParamKey());
            else
                comp = 1;
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }
}
