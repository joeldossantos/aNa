package br.pensario.connector;

import br.pensario.NCLValues.NCLAttributeType;
import br.pensario.NCLValues.NCLEventType;
import br.pensario.NCLValues.NCLKey;

public class NCLAttributeAssessment {

    private Role role;
    private NCLEventType eventType;
    private NCLKey key;
    private NCLAttributeType attributeType;
    private int offset = -1;
    
    
    public NCLAttributeAssessment(String role, NCLEventType eventType) throws Exception {
        setRole(role);
        setEventType(eventType);
    }
    
    public void setRole(String role) throws Exception {
        this.role = new Role(role);
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setEventType(NCLEventType eventType) throws Exception {
        if (eventType == null){
            Exception ex = new NullPointerException("null eventType");
            throw ex;
        }
        
        this.eventType = eventType;
    }
    
    public NCLEventType getEventType() {
        return eventType;
    }
    
    public void setKey(NCLKey key) throws Exception {
        if (key == null){
            Exception ex = new NullPointerException("null key");
            throw ex;
        }
        
        this.key = key;
    }
    
    public NCLKey getKey() {
        return key;
    }
    
    public boolean hasKey() {
        return (key != null);
    }
    
    public void setAttributeType(NCLAttributeType attributeType) throws Exception {
        if (attributeType == null){
            Exception ex = new NullPointerException("null attributeType");
            throw ex;
        }
        
        this.attributeType = attributeType;
    }
    
    public NCLAttributeType getAttributeType() {
        return attributeType;
    }
    
    public boolean hasAttributeType() {
        return (attributeType != null);
    }
    
    public void setOffset(int offset) throws Exception {
        if (offset < 0){
            Exception ex = new IllegalArgumentException("illegal offset");
            throw ex;
        }
        
        this.offset = offset;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public boolean hasOffset() {
        return (offset != -1);
    }
}
