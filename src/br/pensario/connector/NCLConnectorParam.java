package br.pensario.connector;

public class NCLConnectorParam implements Comparable<NCLConnectorParam>{
	
	private String name;
	private String type;
	
	public NCLConnectorParam(String name)
	{
		setName(name);		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
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
