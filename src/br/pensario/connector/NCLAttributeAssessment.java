package br.pensario.connector;

import br.pensario.NCLValues.NCLAttributeType;
import br.pensario.NCLValues.NCLEventType;
import br.pensario.NCLValues.NCLKey;

public class NCLAttributeAssessment {

    private NCLRole role;
    private NCLEventType eventType;
    private NCLKey key;
    private NCLAttributeType attributeType;
    private Integer offset;
    
    
    /**
     * Determina o papel do atributo da assertiva.
     * 
     * @param role nome do papel.
     */
    public void setRole(String role) {
        this.role = new NCLRole(role);
    }
    
    
    /**
     * Retorna o papel do atributo da assertiva.
     * 
     * @return NCLRole representando o papel.
     */
    public NCLRole getRole() {
        return role;
    }
    
    
    /**
     * Determina o tipo do evento.
     * 
     * @param eventType tipo do evento.
     */
    public void setEventType(NCLEventType eventType) {
        this.eventType = eventType;
    }
    
    
    /**
     * Retorna o tipo do evento.
     * 
     * @return NCLEventType representando o tipo do evento.
     */
    public NCLEventType getEventType() {
        return eventType;
    }
    
    
    /**
     * Determina a tecla.
     * 
     * @param key NCLKey representando a tecla.
     */
    public void setKey(NCLKey key) {
        this.key = key;
    }
    
    
    /**
     * Retorna a tecla.
     * 
     * @return NCLKey representando a tecla.
     */
    public NCLKey getKey() {
        return key;
    }
    
    
    /**
     * Determina o atributo attributeType
     * 
     * @param attributeType valor do atributo.
     */
    public void setAttributeType(NCLAttributeType attributeType) {
        this.attributeType = attributeType;
    }
    
    
    /**
     * Retorna o valor do atributo attributeType.
     * 
     * @return NCLAttributeType representando o atributo.
     */
    public NCLAttributeType getAttributeType() {
        return attributeType;
    }
    
    
    /**
     * Determina o atributo offset.
     * 
     * @param offset valor do offset a ser utilizado.
     * @throws java.lang.Exception se o offset for inválido.
     */
    public void setOffset(Integer offset) throws Exception {
        if (offset != null && offset < 0){
            Exception ex = new IllegalArgumentException("illegal offset");
            throw ex;
        }
        
        this.offset = offset;
    }
    
    
    /**
     * Retorna o offset
     * 
     * @return Integer representando o offset.
     */
    public Integer getOffset() {
        return offset;
    }
    
    
    /**
     * Retorna a representação do elemento em XML.
     * @return Trecho XML referente ao elemento
     */
    public String parse(int ident) {
        String space, content;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<attributeAssessment";

        content += " role='" + getRole().getName() + "'";

        if(getEventType()!=null)
            content += " eventType='" + getEventType().toString() + "'";
        
        if(getKey()!=null)
            content += " key='" + getKey().toString() + "'";
        
        if(getAttributeType()!=null)
            content += " attributeType='" + getAttributeType().toString() + "'";        
        
        if(getOffset()!=null)
            content += " offset='" + getOffset() + "'";
        
        content += " />\n";

        return content;
    }

    
    public String toString() {
        return parse(0);
    }
}
