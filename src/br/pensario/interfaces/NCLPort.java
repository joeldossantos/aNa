package br.pensario.interfaces;

import br.pensario.NCLIdentification;
import br.pensario.node.NCLNode;

public class NCLPort extends NCLInterface {

	private NCLIdentification id;
	private NCLNode component;
	private NCLInterface interfac;
	
	
	public NCLPort(String id, NCLNode component) throws Exception {
		if (!setId(id) || !setComponent(component)){
			Exception ex = new Exception("Invalid id or component");
			throw ex;
		}
	}
	
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
	
	public String getIdentifier(){
		return getId();
	}
	
	/**
	 * define o component que a porta mapeia
	 * @param component componente relacionado pela porta
	 * @return true se valido falso contrario
	 */
	public boolean setComponent(NCLNode component) {
		if (component != null){
			this.component = component;
			return true;
		}
		else
			return false;
	}
	
	public NCLNode getComponent() {
		return component;
	}
	
	/**
	 * define uma interface do componente
	 * @param interfac interface do componente
	 * @return true se valido falso contrario
	 */
	public boolean setInterface(NCLInterface interfac) {
		if (interfac != null){
			this.interfac = interfac;
			return true;
		}
		else
			return false;
	}
	
	public NCLInterface getInterface() {
		return interfac;
	}
	
	public boolean hasInterface() {
		if (interfac != null)
			return true;
		else
			return false;
	}
	
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <port> element and attributes declaration
		content = space + "<body";
		content += " id='" + getId() + "'";
		content += " component='" + getComponent().getId() + "'";
		if (hasInterface())
			content += " interface='" + getInterface().getIdentifier() + "'";
		content += "/>\n";
		
		return content;
	}
}
