package br.pensario.node;

import br.pensario.NCLIdentification;

public abstract class NCLNode {
	
	private String id;
	
	
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public abstract String parse(int ident);
}
