package br.pensario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains an valid NCL identification.
 * The identification is in the format: [_:A-Za-z] \c = [-._:A-Za-z0-9]
 */
public class NCLIdentification {

	protected String identification;
	
	
	/**
	 * NCLIdentification class constructor
	 * Sets the identification and test if its format is a valid format.
	 * 
	 * @param identification identification to be tested and used.
	 * @return True if the identification is valid and false otherwise.
	 */
	public NCLIdentification(String identification) throws Exception {
		Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");
		
		Matcher matcher = pattern.matcher(identification);

		if (!matcher.matches()){
			Exception ex = new Exception("Invalid identifier.");
			throw ex;
		}
		this.identification = identification;
	}
	
	/**
	 * Gets the identification.
	 * 
	 * @return String with the identification
	 */
	public String toString() {
		return identification;
	}

}
