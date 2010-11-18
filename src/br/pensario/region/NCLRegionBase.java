package br.pensario.region;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;

/**
 * Classe referente a base de regiões NCL.
 * 
 * @author Wagner Schau
 * @author Joel Santos
 * @since 24/09/2010
 * @version 1.0
 */

public class NCLRegionBase extends NCLIdentifiableElement{

	private String device;
	private NCLRegion parent_region;
	
	Set<NCLRegion> regions= new TreeSet<NCLRegion>();
	
	/**
	 * Contrutor padrão
	 * 
	 * @param id
	 */
	public NCLRegionBase(String id) throws NCLInvalidIdentifierException{		
		setId(id);
	}	
	
	/**
	 * Atribui o dispositivo de exibição para as regiões presentes na base
	 * 
	 * @param device Dispositivo de exibição
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * Retorna qual o dispositivo de exibição utilizado para as regiões presentes na base
	 * 
	 * @return Dispositivo de exibição
	 */
	public String getDevice() {
		return device;
	}	

	/**
	 * Atribui um novo pai a base de regiões.
	 * 
	 * @param region Região-pai
	 */
	public void setParentRegion(NCLRegion region) {
		this.parent_region = region;
	}

	/**
	 * Retorna qual a região pai da base de regiões.
	 * 
	 * @return Região-pai da base
	 */
	public NCLRegion getParentRegion() {
		return parent_region;
	}
	
	/**
	 * Adiciona uma nova região NCL à base de regiões
	 * 
	 * @param region Região NCL a ser adicionada
	 */
	public boolean addRegion(NCLRegion region)
	{
		return regions.add(region);
		
	}
	
	/**
	 * Remove uma região NCL da base de regiões
	 * 
	 * @param region Região NCL a ser removida
	 */
	public boolean removeRegion(NCLRegion region)
	{
		return regions.remove(region);		
	}
	
	/**
	 * Indica se a base de regiões contém uma determinada região NCL
	 * 
	 * @param region Região NCL a ser buscada
	 */	
	public boolean hasRegion(NCLRegion region)
	{
		return regions.contains(region);		
	}
	
	/**
	 * Indica se a base possui alguma Região NCL
	 * 
	 * @return Verdadeiro caso a base possua ao menos uma Região NCL
	 */
	
	public boolean hasRegion()
	{
		return regions.size() > 0;		
	}
	
	/**
	 * Retorna uma coleção iterável contendo as Regiões NCL da base
	 * 
	 * @return Iterável
	 */
	public Iterable<NCLRegion> getRegions()
	{
		return regions;		
	}

	/**
	 * Retorna a representação do elemento em XML.
	 * 
	 * @return Trecho XML referente ao elemento
	 */
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<regionBase ";
		
		if(getId()!=null)
			content += " id='" + getId() + "'";

		if (getDevice() != null) 						
			content += " device='" + getDevice() + "'";
		
		if (getParentRegion() != null) 						
			content += " region='" + getParentRegion().getId() + "'";
		
		if (hasRegion()) {
			
			content += ">\n";
			for (NCLRegion region : getRegions()) {
				content += region.parse(ident + 1);
			}
			content += space + "</regionBase>\n";
		}
		else
			content += "/>\n";

		return content;
	}
	
	//public boolean hasOverlayedRegions(); 
	// Camada de validacao
}
