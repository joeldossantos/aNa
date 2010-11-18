package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLInterface;
import br.pensario.node.NCLNode;

/**
 * Classe representando o elemento bind da linguagem NCL.
 */
public class NCLBind implements Comparable<NCLBind>{

	private NCLRole role;
	private NCLNode component;
	private NCLInterface interfac;
	private NCLDescriptor descriptor;
	
	private Set<NCLParam> bindParams = new TreeSet<NCLParam>();
	
	
	/**
	 * Construtor do bind.
	 * 
	 * @param role papel ao qual o bind está associada.
	 * @param component componente mapeado pelo bind.
	 * @throws NullPointerException se o papel ou o componente forem nulos.
	 */
	public NCLBind(NCLRole role, NCLNode component) throws Exception {
		setRole(role);
		setComponent(component);
	}
	
	
	/**
	 * Determina o papel ao qual o bind está relacionado.
	 * 
	 * @param role papel associado.
	 * @throws NullPointerException se o papel for nulos.
	 */
	public void setRole(NCLRole role) throws Exception {
		if (role != null){
			this.role = role;
		}
		else{
			Exception ex = new NullPointerException("Null role");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o papel relacionado ao bind.
	 * 
	 * @return NCLRole representando o papel.
	 */
	public NCLRole getRole() {
		return role;
	}
	
	
	/**
	 * Determina o componente ao qual o bind está relacionado.
	 * 
	 * @param component componente associado.
	 * @throws NullPointerException se o componente for nulos.
	 */
	public void setComponent(NCLNode component) throws Exception {
		if (component != null){
			this.component = component;
		}
		else{
			Exception ex = new NullPointerException("Null descriptor");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o componente ralcionado ao bind.
	 * 
	 * @return NCLNode representando o componente.
	 */
	public NCLNode getComponent() {
		return component;
	}
	
	
	/**
	 * Determina a interface do componente relacionado ao bind.
	 * 
	 * @param interfac interface do componente.
	 * @throws NullPointerException se a interface for nulos.
	 */
	public void setInterface(NCLInterface interfac) throws Exception {
		if (interfac != null){
			this.interfac = interfac;
		}
		else{
			Exception ex = new NullPointerException("Null descriptor");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a interface do componente relacionado ao bind.
	 * 
	 * @return NCLInterface representando a interface.
	 */
	public NCLInterface getInterface() {
		return interfac;
	}
	
	
	/**
	 * Verifica se o bind tem o atributo interface.
	 * 
	 * @return True se o atributo existir.
	 */
	public boolean hasInterface() {
		return (interfac != null);
	}
	
	
	/**
	 * Determina o o valor do atributo descriptor do bind.
	 * 
	 * @param descriptor descritor associado ao bind.
	 * @throws NullPointerException se o descritor for nulo.
	 */
	public void setDescriptor(NCLDescriptor descriptor) throws Exception {
		if (descriptor != null){
			this.descriptor = descriptor;
		}
		else{
			Exception ex = new NullPointerException("Null descriptor");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o conteúdo do atributo descritor de um bind.
	 * 
	 * @return NCLDescriptor descritor associado ao bind.
	 */
	public NCLDescriptor getDescriptor() {
		return descriptor;
	}
	
	
	/**
	 * Verifica se o bind possui um descritor.
	 * 
	 * @return True se o atributo descriptor tiver valor.
	 */
	public boolean hasDescriptor() {
		return (descriptor != null);
	}
	
	
	/**
	 * Adiciona um bindParam ao bind.
	 * 
	 * @param name nome do parâmetro.
	 * @param value valor do parâmetro.
	 * @throws Exception se o parâmetro já existir.
	 */
	public void addBindParam(NCLConnectorParam name, String value) throws Exception {
		NCLParam param = new NCLParam(name, value);

		if (!bindParams.add(param)){
			Exception ex = new Exception("BindParam already exists.");
			throw ex;
		}
	}
	
	
	/**
	 * Remove um bindParam do bind.
	 * 
	 * @param name Nome do parâmetro a ser removido.
	 * @throws Exception se o bind com o nome passado não existir.
	 */
	public void removeBindParam(NCLConnectorParam name) throws Exception {
		NCLParam param = new NCLParam(name, "any"); // o valor foi criado apenas para ter um valor.

		if (!bindParams.remove(param)){
			Exception ex = new Exception("There is no BindParam with this name.");
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o bind possui um bindParam.
	 * 
	 * @param name nome do bindParam a ser verificado.
	 * @return True se o bindParam existir.
	 * @throws NullPointerException se o nome for nulo.
	 */
	public boolean hasBindParam(NCLConnectorParam name) throws Exception {
		NCLParam param = new NCLParam(name, "any"); // o valor foi criado apenas para ter um valor.
		
		return bindParams.contains(param);
	}
	
	
	/**
	 * Verifica se o bind possui algum bindParam.
	 * 
	 * @return True se o bind possui alguma âncora.
	 */
	public boolean hasBindParam() {
		return !bindParams.isEmpty();
	}
	
	
	/**
	 * Obtém os bindParams de um bind.
	 * 
	 * @return Iterable com os bindParams.
	 */
	public Iterable<NCLParam> getBindParams() {
		return bindParams;
	}
	
	
	/**
	 * Verifica se dois binds são iguais.
	 * 
	 * @param bind bind com o qual comparar.
	 * @return True se os binds forem iguais.
	 */
	public boolean equals(NCLBind bind) {
		//bind tem role diferente?
		if (!bind.getRole().equals(getRole()))
			return false;
		//bind tem componente diferente?
		if (!bind.getComponent().equals(getComponent()))
			return false;
		//bind ou this não tem interface ou são diferentes
		if (!hasInterface() || !bind.hasInterface() || !bind.getInterface().equals(getInterface()))
			return false;
		//bind ou this não tem descriptor ou são diferentes
		if (!hasDescriptor() || !bind.hasDescriptor() || !bind.getDescriptor().equals(getDescriptor()))
			return false;
		else
			return true;
	}
	
	
	/**
	 * Cria o código XML de um bind.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML do bind.
	 */
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <bind> element and attributes declaration
		content = space + "<bind";
		content += " role='" + getRole().getId().toString() + "'";
		content += " component='" + getComponent().getId() + "'";
		if (hasInterface())
			content += " interface='" + getInterface().getId() + "'";
		if (hasDescriptor())
			content += " descriptor='" + getDescriptor().getId() + "'";
		
		// <bind> element content
		if (hasBindParam()){
			content += ">\n";
//			content += "<!-- Bind element parameters -->\n";
			
			Iterator<NCLParam> it = bindParams.iterator();
			while (it.hasNext()) {
				NCLParam b = it.next();
				content += b.parse(ident+1, "bindParam");
			}
			
			// <bind> element end declaration
			content += space + "</bind>\n";
		}
		else
			content += "/>\n";
		
		return content;
	}
	
	
	/**
	 * Cria o código XML de um bind desconsiderando a indentação.
	 * 
	 * @return String com o código XML do bind.
	 */
	public String toString(){
		return parse(0);
	}
	
	
	/**
	 * Determina se dois binds são iguais.
	 * 
	 * @param bind bind com o qual comparar.
	 * @return Inteiro indicando se os binds são iguais. O valor será 0 se os binds forem iguais.
	 */
	public int compareTo(NCLBind bind) {
		if (equals(bind))
			return 0;
		else
			return 1;
	}
}
