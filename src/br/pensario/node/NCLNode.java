package br.pensario.node;

import br.pensario.NCLIdentification;

public abstract class NCLNode {
	
	private NCLIdentification id;
	
	
	public boolean setId(String id) {
		try{
			this.id = new NCLIdentification(id);
			return true;
		}
		catch(Exception ex){
			System.err.println(ex);
			return false;
		}
	}
	
	public String getId() {
		return id.toString();
	}
	
	public abstract String parse(int ident);
}
