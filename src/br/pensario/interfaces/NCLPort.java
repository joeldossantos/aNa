package br.pensario.interfaces;

import br.pensario.node.NCLNode;

public class NCLPort extends NCLInterface {

	private String id;
	private NCLNode component;
	private NCLInterface interfac;
	
	
	public NCLPort(String id, NCLNode component) throws Exception {
		if (!setId(id) || !setComponent(component)){
			Exception ex = new Exception("Invalid id or component");
			throw ex;
		}
	}
	
	public boolean setId(String id) {
		//TODO: validar entrada de id
		this.id = id;
		return true;
	}
	
	public String getId() {
		return id;
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
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
