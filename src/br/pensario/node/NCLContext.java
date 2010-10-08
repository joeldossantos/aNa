package br.pensario.node;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.interfaces.NCLProperty;
import br.pensario.interfaces.NCLPort;
import br.pensario.link.NCLLink;

/**
 * Classe representando o elemento context da linguagem NCL.
 */
public class NCLContext extends NCLNode {

	private Set<NCLPort> ports = new TreeSet<NCLPort>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	private Set<NCLNode> nodes = new TreeSet<NCLNode>();
	private Set<NCLLink> links = new TreeSet<NCLLink>();
	
	
	/**
	 * Construtor do contexto.
	 * 
	 * @param id id do contexto.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public NCLContext(String id) throws Exception {
		setId(id);
	}
	
	
	/**
	 * Adiciona uma porta ao contexto.
	 * 
	 * @param port porta a ser adicionada.
	 * @throws Exception se a porta já existir.
	 */
	public void addPort(NCLPort port) throws Exception {
		if (!ports.add(port)){
			Exception ex = new Exception("Port already existis");
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma porta do contexto.
	 * 
	 * @param id id da porta a ser removida.
	 * @throws IllegalArgumentException se o id for inválido ou Exception se a porta não existir.
	 */
	public void removePort(String id) throws Exception {
		NCLPort port = new NCLPort(id, this); //this passado como componente para poder criar a porta.
		
		if (!ports.remove(port)){
			Exception ex = new Exception("Port does not existis");
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma porta do contexto.
	 * 
	 * @param port porta a ser removida.
	 * @throws Exception se a porta não existir.
	 */
	public void removePort(NCLPort port) throws Exception {
		if (!ports.remove(port)){
			Exception ex = new Exception("Port does not existis");
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o contexto possui uma porta.
	 * 
	 * @param id da porta a ser verificada.
	 * @return True se a porta existir.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public boolean hasPort(String id) throws Exception {
		NCLPort port = new NCLPort(id, this); //this passado como componente para poder criar a porta.
		
		return ports.contains(port);
	}
	
	
	/**
	 * Verifica se o contexto possui uma porta.
	 * 
	 * @param port porta a ser verificada.
	 * @return True se a porta existir.
	 * @throws NullPointerException se a porta for inválida.
	 */
	public boolean hasPort(NCLPort port) throws Exception {
		return ports.contains(port);
	}
	
	
	/**
	 * Verifica se o contexto possui alguma porta.
	 * 
	 * @return True se o contexto possui alguma porta.
	 */
	public boolean hasPort() {
		return !ports.isEmpty();
	}
	
	
	/**
	 * Obtém as portas do contexto.
	 * 
	 * @return Iterable com as portas do contexto.
	 */
	public Iterable<NCLPort> getPorts() {
		return ports;
	}
	
	
	/**
	 * Adiciona uma propriedade a um contexto.
	 * 
	 * @throws Exception se o contexto já possuir a propriedade sendo adicionada.
	 */
	public void addProperty(NCLProperty property) throws Exception {
		if (!properties.add(property)){
			Exception ex = new Exception("Property already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma propriedade do contexto.
	 * 
	 * @param name nome da propriedade a ser removida.
	 * @throws Exception se não existir propriedade com o nome indicado.
	 */
	public void removeProperty(String name) throws Exception {
		NCLProperty p = new NCLProperty(name);
		
		if (!properties.remove(p)){
			Exception ex = new Exception("Property does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma propriedade do contexto.
	 * 
	 * @param property propriedade a ser removida.
	 * @throws Exception se não existir a propriedade.
	 */
	public void removeProperty(NCLProperty property) throws Exception {
		if (!properties.remove(property)){
			Exception ex = new Exception("Property does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o contexto possui uma propriedade.
	 * 
	 * @param name nome da propriedade sendo verificada.
	 * @return True se a propriedade for uma propriedade do contexto.
	 * @throws Exception se o nome for inválido.
	 */
	public boolean hasProperty(String name) throws Exception {
		NCLProperty p = new NCLProperty(name);
		
		return properties.contains(p);
	}
	
	
	/**
	 * Verifica se o contexto possui uma propriedade.
	 * 
	 * @param property propriedade sendo verificada.
	 * @return True se a propriedade for uma propriedade do contexto.
	 * @throws Exception se a propriedade for inválida.
	 */
	public boolean hasProperty(NCLProperty property) throws Exception {
		return properties.contains(property);
	}
	
	
	/**
	 * Verifica se o contexto possui alguma propriedade.
	 * 
	 * @return True se o contexto possuir alguma propriedade.
	 */
	public boolean hasProperty() {
		return !properties.isEmpty();
	}
	
	
	/**
	 * Obtém as propriedades do contexto.
	 * 
	 * @return Iterable com as propriedades do contexto.
	 */
	public Iterable<NCLProperty> getProperties() {
		return properties;
	}
	
	
	/**
	 * Adiciona um nó ao contexto.
	 * 
	 * @param node nó a ser adicionado.
	 * @throws Exception se o nó já existir.
	 */
	public void addNode(NCLNode node) throws Exception {
		if (!nodes.add(node)){
			Exception ex = new Exception("node already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove um nó do contexto.
	 * 
	 * @param id id do nó a ser removido.
	 * @throws Exception se o nó não existir.
	 */
	public void removeNode(String id) throws Exception {
		
		Iterator<NCLNode> it = nodes.iterator();

		while (it.hasNext()) {
			NCLNode n = it.next();

			if (n.getId().equals(id)) {
				it.remove();
				return;
			}
		}
		
		Exception ex = new Exception("Node does not exists.");
		throw ex;
	}
	
	
	/**
	 * Remove um nó do contexto.
	 * 
	 * @param node nó a ser removido.
	 * @throws Exception se o nó não existir.
	 */
	public void removeNode(NCLNode node) throws Exception {
		if (!nodes.remove(node)){
			Exception ex = new Exception("Node does not exists."); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o contexto possui um nó.
	 * 
	 * @param id id do nó a ser verificado.
	 * @return True se o nó existir.
	 */
	public boolean hasNode(String id) {
		Iterator<NCLNode> it = nodes.iterator();

		while (it.hasNext()) {
			NCLNode n = it.next();

			if (n.getId().equals(id))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Verifica se o contexto possui um nó.
	 * 
	 * @param node nó a ser verificado.
	 * @return True se o nó existir.
	 */
	public boolean hasNode(NCLNode node) {
		return nodes.contains(node);
	}
	
	
	/**
	 * Verifica se o contexto possui algum nó.
	 * 
	 * @return True se o contexto possuir algum nó.
	 */
	public boolean hasNode() {
		return(!nodes.isEmpty());
	}
	
	
	/**
	 * Obtém os nós do contexto.
	 * 
	 * @return Iterable com os nós do contexto.
	 */
	public Iterable<NCLNode> getNodes() {
		return nodes;
	}
	
	
	/**
	 * Adiciona um link ao contexto.
	 * 
	 * @param link link a ser adicionado.
	 * @throws Exception se o link já existir.
	 */
	public void addLink(NCLLink link) throws Exception {
		if (!links.add(link)){
			Exception ex = new Exception("Link already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove um link do contexto.
	 * 
	 * @param link link a ser removido.
	 * @throws Exception se o link não existir.
	 */
	public void removeLink(NCLLink link) throws Exception {//TODO: receber link ou algum identificador??
		if (!links.remove(link)){
			Exception ex = new Exception("link does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o contexto contém um link.
	 * 
	 * @param link link a ser verificado.
	 * @return True se o link estiver no contexto.
	 */
	public boolean hasLink(NCLLink link) {//TODO: receber link ou algum identificador??
		return links.contains(link);
	}
	
	
	/**
	 * Verifica se o contexto possui algum link.
	 * 
	 * @return True se o contexto possuir algum link.
	 */
	public boolean hasLink() {
		return !links.isEmpty();
	}
	
	
	/**
	 * Obtém os links de um contexto.
	 * 
	 * @return Iterable com os links do contexto.
	 */
	public Iterable<NCLLink> getLinks() {
		return links;
	}
	
	
	/**
	 * Determina se dois contextos são iguais.
	 * 
	 * @param context contextos com a qual comparar.
	 * @return True se os contextos forem iguais.
	 */
	public boolean equals(NCLContext context) {
		if (getId().equals(context.getId()))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Cria o código XML de um contexto.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML do contexto.
	 */
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <context> element and attributes declaration
		content = space + "<context";
		content += " id='" + getId() + "'";
		
		// Test if the media has content
		if (hasPort() || hasProperty() || hasNode() || hasLink()){
			content += ">\n";
			
			
			// <context> element content
			if (hasPort()){
//				content += "<!-- Context element ports -->\n";
				
				Iterator<NCLPort> it = ports.iterator();
				while (it.hasNext()) {
					NCLPort p = it.next();
					content += p.parse(ident+1);
				}
			}
			
			if (hasProperty()){
//				content += "<!-- Context element properties -->\n";
				
				Iterator<NCLProperty> it = properties.iterator();
				while (it.hasNext()) {
					NCLProperty p = it.next();
					content += p.parse(ident+1);
				}
			}
			
			if (hasNode()){
//				content += "<!-- Context element nodes -->\n";
				
				Iterator<NCLNode> it = nodes.iterator();
				while (it.hasNext()) {
					NCLNode n = (NCLNode) it.next();
					content += n.parse(ident+1);
				}
			}
			
			if (hasLink()){
//				content += "<!-- Context element links -->\n";
				
				Iterator<NCLLink> it = links.iterator();
				while (it.hasNext()) {
					NCLLink l = (NCLLink) it.next();
					content += l.parse(ident+1);
				}
			}
			
			
			// <context> element end declaration
			content += space + "</context>\n";
		}
		else
			content += "/>\n";
		
		return content;
	}
	
	
	/**
	 * Cria o código XML de um contexto desconsiderando a indentação.
	 * 
	 * @return String com o código XML do contexto.
	 */
	public String toString() {
		return parse(0);
	}
}
