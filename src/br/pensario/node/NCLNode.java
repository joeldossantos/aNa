package br.pensario.node;

public abstract class NCLNode {
	
	private String id;
	
	
	public boolean setId(String id) {
		//TODO: colocar a validacao do id
		this.id = id;
		return true;
	}
	
	public String getId() {
		return id;
	}
	
	public abstract String parse(int ident);
}
