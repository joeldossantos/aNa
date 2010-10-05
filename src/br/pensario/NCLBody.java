package br.pensario;

import br.pensario.node.NCLMedia;
import br.pensario.node.NCLNode;
import br.pensario.interfaces.NCLPort;
import br.pensario.interfaces.NCLProperty;
import br.pensario.link.NCLLink;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLBody {

	private String id;
	
	private Set<NCLPort> ports = new TreeSet<NCLPort>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	private Set<NCLNode> nodes = new TreeSet<NCLNode>();
	private Set<NCLLink> links = new TreeSet<NCLLink>();
	
	
	public NCLBody() {
		
	}
	
	
	/**
	 * Determina o id de um body.
	 * 
	 * @param id String com o id do body.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	
	/**
	 * Obtém o id do body.
	 * 
	 * @return String com o id do body.
	 */
	public String getId() {
		return id;
	}
	
	
	/**
	 * Verifica se o body possui um id.
	 * 
	 * @return True se o body possuir um id.
	 */
	public boolean hasId() {
		return (id != null);
	}
	
	
	/**
	 * Adiciona uma porta ao body.
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
	 * Remove uma porta do body.
	 * 
	 * @param id id da porta a ser removida.
	 * @throws IllegalArgumentException se o id for inválido ou Exception se a porta não existir.
	 */
	public void removePort(String id) throws Exception {
		NCLMedia node = new NCLMedia("any");//node passado como componente para poder criar a porta.
		NCLPort port = new NCLPort(id, node);
		
		if (!ports.remove(port)){
			Exception ex = new Exception("Port does not existis");
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o body possui uma porta.
	 * 
	 * @param id da porta a ser verificada.
	 * @return True se a porta existir.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public boolean hasPort(String id) throws Exception {
		NCLMedia node = new NCLMedia("any");//node passado como componente para poder criar a porta.
		NCLPort port = new NCLPort(id, node);
		
		return ports.contains(port);
	}
	
	
	/**
	 * Verifica se o body possui alguma porta.
	 * 
	 * @return True se o body possui alguma porta.
	 */
	public boolean hasPort() {
		return !ports.isEmpty();
	}
	
	
	/**
	 * Obtém as portas do body.
	 * 
	 * @return Iterable com as portas do body.
	 */
	public Iterable<NCLPort> getPorts() {
		return ports;
	}
	
	
	/**
	 * Adiciona uma propriedade a um body.
	 * 
	 * @throws Exception se o body já possuir a propriedade sendo adicionada.
	 */
	public void addProperty(NCLProperty property) throws Exception {
		if (!properties.add(property)){
			Exception ex = new Exception("Property already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma propriedade do body.
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
	 * Verifica se o body possui uma propriedade.
	 * 
	 * @param name nome da propriedade sendo verificada.
	 * @return True se a propriedade for uma propriedade do body.
	 * @throws Exception se o nome for inválido.
	 */
	public boolean hasProperty(String name) throws Exception {
		NCLProperty p = new NCLProperty(name);
		
		return properties.contains(p);
	}
	
	
	/**
	 * Verifica se o body possui alguma propriedade.
	 * 
	 * @return True se o body possuir alguma propriedade.
	 */
	public boolean hasProperty() {
		return !properties.isEmpty();
	}
	
	
	/**
	 * Obtém as propriedades do body.
	 * 
	 * @return Iterable com as propriedades do body.
	 */
	public Iterable<NCLProperty> getProperties() {
		return properties;
	}
	
	
	/**
	 * Adiciona um nó ao body.
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
	 * Remove um nó do body.
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
	 * Verifica se o body possui um nó.
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
	 * Verifica se o body possui algum nó.
	 * 
	 * @return True se o body possuir algum nó.
	 */
	public boolean hasNode() {
		return(!nodes.isEmpty());
	}
	
	
	/**
	 * Obtém os nós do body.
	 * 
	 * @return Iterable com os nós do body.
	 */
	public Iterable<NCLNode> getNodes() {
		return nodes;
	}
	
	
	/**
	 * Adiciona um link ao body.
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
	 * Remove um link do body.
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
	 * Verifica se o body contém um link.
	 * 
	 * @param link link a ser verificado.
	 * @return True se o link estiver no body.
	 */
	public boolean hasLink(NCLLink link) {//TODO: receber link ou algum identificador??
		return links.contains(link);
	}
	
	
	/**
	 * Verifica se o body possui algum link.
	 * 
	 * @return True se o body possuir algum link.
	 */
	public boolean hasLink() {
		return !links.isEmpty();
	}
	
	
	/**
	 * Obtém os links de um body.
	 * 
	 * @return Iterable com os links do body.
	 */
	public Iterable<NCLLink> getLinks() {
		return links;
	}
	
	
	/**
	 * Determina se dois bodys são iguais.
	 * 
	 * @param context bodys com a qual comparar.
	 * @return True se os bodys forem iguais.
	 */
	public boolean equals(NCLBody body) {
		if (getId().equals(body.getId()))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Cria o código XML de um body.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML do body.
	 */
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";		
		
		// <body> element and attributes declaration
		content = space + "<body";
		if (hasId())
			content += " id='" + getId() + "'";
		content += ">\n";
		
		
		// <body> element content
		if (hasPort()){
//			content += "<!-- Body element ports -->\n";
			
			Iterator<NCLPort> it = ports.iterator();
			while (it.hasNext()) {
				NCLPort p = it.next();
				content += p.parse(ident+1);
			}
		}
		
		if (hasProperty()){
//			content += "<!-- Body element properties -->\n";
			
			Iterator<NCLProperty> it = properties.iterator();
			while (it.hasNext()) {
				NCLProperty p = it.next();
				content += p.parse(ident+1);
			}
		}
		
		if (hasNode()){
//			content += "<!-- Body element nodes -->\n";
			
			Iterator<NCLNode> it = nodes.iterator();
			while (it.hasNext()) {
				NCLNode n = (NCLNode) it.next();
				content += n.parse(ident+1);
			}
		}
		
		if (hasLink()){
//			content += "<!-- Body element links -->\n";
			
			Iterator<NCLLink> it = links.iterator();
			while (it.hasNext()) {
				NCLLink l = (NCLLink) it.next();
				content += l.parse(ident+1);
			}
		}
		
		
		// <body> element end declaration
		content += space + "</body>\n";
		
		return content;
	}
	
	
	/**
	 * Cria o código XML de um body desconsiderando a indentação.
	 * 
	 * @return String com o código XML do body.
	 */
	public String toString() {
		return parse(0);
	}
}
