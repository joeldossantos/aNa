package br.pensario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class represents an Identiable NCL element. That is an NCL element that
 * has an unique id or name attribute.
 * 
 * @version 1.0
 * @author Wagner Schau
 * @author Joel Santos
 */
public class NCLIdentifiable {
    private String identification;
    
    
    /**
     * Sets the NCL element identification.
     * 
     * @param identification NCL element id or name attribute.
     * @throws InvalidIdentifierException if the identification is unvalid.
     */
    protected void setIdentification(String identification) throws InvalidIdentifierException {
        validate(identification);
        this.identification = identification;
    }
    
    
    /**
     * Gets the NCL element identification. The element identification may be its
     * id or name attribute.
     * 
     * @return String with the identification.
     */
    public String getIdentification() {
        return identification;
    }
    
    
    /**
     * Validates the identification. The validation follows the format: [_:A-Za-z] \c = [-._:A-Za-z0-9]
     * 
     * @param identification identification to be tested and used.
     * @throws InvalidIdentifierException if the identification is unvalid.
     */
    private void validate(String identification) throws InvalidIdentifierException {
        Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");

        Matcher matcher = pattern.matcher(identification);

        if (!matcher.matches()){
            IllegalArgumentException ex = new IllegalArgumentException("Invalid identifier.");
            throw ex;
        }
    }
}
