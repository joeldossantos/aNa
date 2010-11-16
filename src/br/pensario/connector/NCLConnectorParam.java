package br.pensario.connector;

public class NCLConnectorParam implements Comparable<NCLConnectorParam>{
	
	private String name;
	private String type;
	
	
	/**
	 * Construtor padrão
	 * 
	 * @param name Nome do parâmetro de conector
	 */
	public NCLConnectorParam(String name)
	{
		setName(name);		
	}
	
	/**
	 * Retorna o nome do parâmetro
	 * 
	 * @return name Nome do parâmetro
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Atribui um novo nome ao parâmetro
	 * 
	 * @param name Novo nome do parâmetro
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Retorna o tipo do parâmetro
	 * 
	 * @return Tipo do parâmetro
	 */
	public String getType() {
		return type;
	}
	
	
	/**
	 * Atribui um novo tipo ao parâmetro
	 * 
	 * @param type Novo tipo do parâmetro
	 */
	public void setType(String type) {
		this.type = type;
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

		content = space + "<connectorParam";
		
		if(getName()!=null)
			content += " name='" + getName() + "'";

		if(getType()!=null)
			content += " type='" + getType() + "'";		
		
		content += " />\n";

		return content;
	}
	
	public String toString()	
	{
		return parse(0);		
	}

	@Override
	public int compareTo(NCLConnectorParam arg0) {
		return this.getName().compareTo(arg0.getName());
	}

}
