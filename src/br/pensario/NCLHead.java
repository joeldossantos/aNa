package br.pensario;

import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.region.NCLRegionBase;

public class NCLHead {
	
	private NCLRegionBase rbase;
	private NCLDescriptorBase dbase;
	private NCLConnectorBase cbase; 

	/**
	 * Construtor padrão
	 */
	
	public NCLHead()
	{	
	}
	
	/**
	 * Construtor iniciado com as bases de regiões, descritores e conectores.
	 * 
	 * @param region_base Base de regiões NCL a ser utilizada pelo cabeçalho
	 * @param descriptor_base Base de descritores NCL a ser utilizada pelo cabeçalho
	 * @param connector_base Base de conectores NCL a ser utilizada pelo cabeçalho
	 */
	
	public NCLHead(NCLRegionBase region_base, NCLDescriptorBase descriptor_base, NCLConnectorBase connector_base)	{
		setRegionBase(region_base);
		setDescriptorBase(descriptor_base);
		setConnectorBase(connector_base);
	}
	
	/**
	 * Atribui uma base de regiões para o cabeçalho.
	 * @param rbase NCLRegionBase Base de regiões.
	 * @throws IllegalArgumentException se o argumento for inválido.	 
	 */
	public void setRegionBase(NCLRegionBase rbase) throws IllegalArgumentException{
		if(rbase!=null)
			this.rbase = rbase;
		else
			throw new IllegalArgumentException("A base região utilizada no cabeçalho é nula.");
	}

	/**
	 * Retorna a base de regiões utilizada pelo cabeçalho.
	 * @return Base de regiões
	 */
	
	public NCLRegionBase getRegionBase() {
		return rbase;
	}

	/**
	 * Atribui uma base de descritores para o cabeçalho.
	 * @param dbase NCLDescriptoBase Base de descritores.
	 * @throws IllegalArgumentException Se o argumento for inválido.	 
	 */	
	public void setDescriptorBase(NCLDescriptorBase dbase) throws IllegalArgumentException {
		if(dbase!=null)
			this.dbase = dbase;
		else
			throw new IllegalArgumentException("A base descritores utilizada no cabeçalho é nula.");		
	}

	
	/**
	 * Retorna a base de descritores utilizada pelo cabeçalho
	 * @return Base de descritores
	 */
	public NCLDescriptorBase getDescriptorBase() {
		return dbase;
	}

	/**
	 * Atribui uma base de conectores para o cabeçalho.
	 * @param cbase NCLConnectorBase Base de conectores.
	 * @throws IllegalArgumentException Se o argumento for inválido.	 
	 */
	public void setConnectorBase(NCLConnectorBase cbase) {
		if(cbase!=null)
			this.cbase = cbase;
		else
			throw new IllegalArgumentException("A base conectores utilizada no cabeçalho é nula.");
	}

	
	/**
	 * Retorna a base de conectores utilizada pelo cabeçalho
	 * @return Base de conectores
	 */
	public NCLConnectorBase getConnectorBase() {
		return cbase;
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
				
		content = space + "<head>\n";	
		
		if(rbase != null)
			content += rbase.parse(ident + 1);		
		
		if(dbase != null)
			content += dbase.parse(ident + 1);
		
		if(cbase != null)
			content += cbase.parse(ident + 1);		
		
		content += space + "</head>\n";
		
		return content;
	}
	
	
}
