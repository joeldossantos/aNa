package br.pensario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class represents an Identifiable NCL element. That is an NCL element that
 * has an unique id or name attribute.
 * 
 * @version 1.0
 * @author Wagner Schau
 * @author Joel Santos
 */
public abstract class NCLIdentifiableElement extends NCLElement{
    
	private String id;    
    
    /**
     * Sets the NCL element identification.
     * 
     * @param identification NCL element id or name attribute.
     * @throws NCLInvalidIdentifierException if the identification is unvalid.
     */
    protected void setId(String id) throws NCLInvalidIdentifierException {
        if(!validate(id))
        {
        	throw new NCLInvalidIdentifierException("Invalid identifier");
        }
    
        this.id = id;
    }
    
    
    /**
     * Gets the NCL element identification. The element identification may be its
     * id or name attribute.
     * 
     * @return String with the identification.
     */
    public String getId() {
        return id;
    }
        
    /**
     * Validates the identification. The validation follows the format: [_:A-Za-z] \c = [-._:A-Za-z0-9]
     * 
     * @param id identification to be tested and used.
     * @throws InvalidIdentifierException if the identification is unvalid.
     */
    private boolean validate(String id) {
    
    	Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");

        Matcher matcher = pattern.matcher(id);

        return !matcher.matches();  
        
    }
	
}
