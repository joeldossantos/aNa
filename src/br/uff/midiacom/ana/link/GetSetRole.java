package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.connector.NCLRoleElement;
import br.uff.midiacom.ana.util.exception.XMLException;
import java.util.ArrayList;


public class GetSetRole<Ep extends NCLParam> implements NCLRoleElement<Ep> {

    private String role;
    private NCLBind bind;
    
    
    public GetSetRole(String role) throws XMLException {
        if(role == null)
            throw new XMLException("Null role");
        if("".equals(role.trim()))
            throw new XMLException("Empty role String");
        
        this.role = role;
    }
    
    
    protected void setBind(NCLBind bind) throws XMLException {
        if(bind == null)
            throw new XMLException("Null bind");
        
        this.bind = bind;
    }
    
    
    @Override
    public String getRole() {
        return role;
    }
    
    
    @Override
    public boolean addReference(Ep reference) throws XMLException {
        return bind.addReference(reference);
    }
    
    
    @Override
    public boolean removeReference(Ep reference) throws XMLException {
        return bind.removeReference(reference);
    }
    
    
    @Override
    public ArrayList<Ep> getReferences() {
        return bind.getReferences();
    }
    
    
    @Override
    public String toString() {
        return "$" + role;
    }
}
