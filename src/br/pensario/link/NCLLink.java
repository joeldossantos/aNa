package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentification;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLInterface;
import br.pensario.node.NCLNode;

/**
 * Classe representando o elemento link da linguagem NCL.
 */
public class NCLLink implements Comparable<NCLLink>{

	private String id;
	private NCLCausalConnector xconnector;
	
	private Set<NCLParam> linkParams = new TreeSet<NCLParam>();
	private Set<NCLBind> binds = new TreeSet<NCLBind>();
	
	
	/**
	 * Construtor do link.
	 * 
	 * @param xconnector conector utilizado pelo link.
	 * @param bind1 bind associado ao link.
	 * @param bind2 bind associado ao link.
	 * @throws NullPointerException se algum dos parâmetros for nulo.
	 */
	public NCLLink(NCLCausalConnector xconnector, NCLBind bind1, NCLBind bind2) throws Exception {
		setXconnector(xconnector);
		addBind(bind1);
		addBind(bind2);
	}
	
	
	/**
	 * Determina o id do link.
	 * 
	 * @param id String com o id do link.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	
	/**
	 * Obtém o id do link.
	 * 
	 * @return String com o id do link.
	 */
	public String getId() {
		return id;
	}
	
	
	/**
	 * Verifica se o link possui um id.
	 * 
	 * @return True se o link possuir um id.
	 */
	public boolean hasId() {
		return (id != null);
	}
	
	
	/**
	 * Determina o conector utilizado pelo link.
	 * 
	 * @param xconnector conector a ser usado pelo link.
	 * @throws NullPointerException se o conector for nulo.
	 */
	public void setXconnector(NCLCausalConnector xconnector) throws Exception {
		if (xconnector != null){
			this.xconnector = xconnector;
		}
		else{
			Exception ex = new NullPointerException("Null xconnector");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o conector utilizado pelo link.
	 * 
	 * @return NCLCausalConnector representando o conector.
	 */
	public NCLCausalConnector getXconnector() {
		return xconnector;
	}
	
	
	/**
	 * Adiciona um parâmetro ao link.
	 * 
	 * @param name nome do parâmetro.
	 * @param value valor do parâmetro.
	 * @throws Exception se o parâmetro já existir.
	 */
	public void addLinkParam(NCLConnectorParam name, String value) throws Exception {
		NCLParam param = new NCLParam(name, value);

		if (!linkParams.add(param)){
			Exception ex = new Exception("linkParam already exists.");
			throw ex;
		}
	}
	
	
	/**
	 * Remove um linkParam do link.
	 * 
	 * @param name Nome do parâmetro a ser removido.
	 * @throws Exception se o parâmetro com o nome passado não existir.
	 */
	public void removeLinkParam(NCLConnectorParam name) throws Exception {
		NCLParam param = new NCLParam(name, "any"); // o valor foi criado apenas para ter um valor.

		if (!linkParams.remove(param)){
			Exception ex = new Exception("There is no linkParam with this name.");
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o link possui um linkParam.
	 * 
	 * @param name nome do linkParam a ser verificado.
	 * @return True se o linkParam existir.
	 * @throws NullPointerException se o nome for nulo.
	 */
	public boolean hasLinkParam(NCLConnectorParam name) throws Exception {
		NCLParam param = new NCLParam(name, "any"); // o valor foi criado apenas para ter um valor.
		
		return linkParams.contains(param);
	}
	
	
	/**
	 * Verifica se o link possui algum linkParam.
	 * 
	 * @return True se o link possui algum parâmetro.
	 */
	public boolean hasLinkParam() {
		return !linkParams.isEmpty();
	}
	
	
	/**
	 * Obtém os parâmetros do link.
	 * 
	 * @return Iterable com os parâmetros do link.
	 */
	public Iterable<NCLParam> getLinkParams() {
		return linkParams;
	}
	
	
	/**
	 * Obtém a quantidade de linkParams existentes no link.
	 * 
	 * @return Inteiro com a quantidade existente.
	 */
	public int numLinkParams() {
		return linkParams.size();
	}
	
	
	/**
	 * Adiciona um bind ao link.
	 * 
	 * @param bind bind a ser adicionado.
	 * @throws Exception se o bind já existir.
	 */
	public void addBind(NCLBind bind) throws Exception {
		if (!binds.add(bind)){
			Exception ex = new Exception("bind already exists.");
			throw ex;
		}
	}
	
	
	/**
	 * Remove um bind do link.
	 * 
	 * @param bind bind a ser removido.
	 * @throws Exception se o bind passado não existir.
	 */
	public void removeBind(NCLBind bind) throws Exception {
		if (!binds.remove(bind)){
			Exception ex = new Exception("This bind does not exists.");
			throw ex;
		}
	}
	
	
	/**
	 * Remove um bind do link.
	 * 
	 * @param role role do bind.
	 * @param component componente do bind.
	 * @param interfac interface do bind.
	 * @param descriptor descritor do bind.
	 * @throws NullPointerException se os atributos forem nulos ou Exception se o bind não existir.
	 */
	public void removeBind(NCLRole role, NCLNode component, NCLInterface interfac, NCLDescriptor descriptor) throws Exception {
		NCLBind b = new NCLBind(role, component);
		if (interfac != null)
			b.setInterface(interfac);
		if (descriptor != null)
			b.setDescriptor(descriptor);
			
		if (!binds.remove(b)){
			Exception ex = new Exception("This bind does not exists.");
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se o link possui um bind.
	 * 
	 * @param bind bind a ser verificado.
	 * @return True se o bind existir.
	 */
	public boolean hasBind(NCLBind bind) {
		return binds.contains(bind);
	}
	
	
	/**
	 * Verifica se o link possui um bind.
	 * 
	 * @@param role role do bind.
	 * @param component componente do bind.
	 * @param interfac interface do bind.
	 * @param descriptor descritor do bind.
	 * @return True se o bind existir.
	 * @throws NullPointerException se os atributos forem nulos ou Exception se o bind não existir.
	 */
	public boolean hasBind(NCLRole role, NCLNode component, NCLInterface interfac, NCLDescriptor descriptor) throws Exception {
		NCLBind b = new NCLBind(role, component);
		if (interfac != null)
			b.setInterface(interfac);
		if (descriptor != null)
			b.setDescriptor(descriptor);
		
		return binds.contains(b);
	}
	
	
	/**
	 * Obtém os binds do link
	 * 
	 * @return Iterable com os bind do link.
	 */
	public Iterable<NCLBind> getBinds() {
		return binds;
	}
	
	
	/**
	 * Obtém o número de binds que um link possui.
	 * 
	 * @return Inteiro com o número de binds.
	 */
	public int numBinds() {
		return(binds.size());
	}
	
	
	/**
	 * Verifica se dois links são iguais.
	 * 
	 * @param link link com o qual comparar.
	 * @return True se os links forem iguais.
	 */
	public boolean equals(NCLLink link) {
		// Link tem id? é diferente?
		if (!hasId() || !link.hasId() || !getId().equals(link.getId()))
			return false;
		// The xconnector attributes are different
		if (!getXconnector().equals(link.getXconnector()))
			return false;
		//TODO - testar link params
		if (numLinkParams() != link.numLinkParams())
			return false;
		// tem o mesmo numero de binds
		if (numBinds() != link.numBinds())
			return false;
		// testa se os binds sao iguais
		if (!compareBinds(link))
			return false;
		else
			return true;
	}
	
	
	/**
	 * Verifica se dois links possuem os mesmos binds.
	 * 
	 * @param link link com o qual comparar.
	 * @return True se os links possuirem os mesmos binds.
	 */
	private boolean compareBinds(NCLLink link) {
		int equalBinds = 0;
		Iterator<NCLBind> bindsIterator = binds.iterator();

		while (bindsIterator.hasNext()){
			NCLBind bind = bindsIterator.next();

			Iterator<NCLBind> it = binds.iterator();
			
			while (it.hasNext()){
				NCLBind b = it.next();
				
				if (bind.equals(b))
					equalBinds++;
			}
		}
		
		if (equalBinds == numBinds())
			return true;
		else
			return false;
	}
	
	
	/**
	 * Cria o código XML do link.
	 * 
	 * @param ident nível de indentação do código.
	 * @return String com o código XML do link.
	 */
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <link> element and attributes declaration
		content = space + "<link";
		if (hasId())
			content += " id='" + getId() + "'";
		content += " xconnector='" + getXconnector().getId() + "'";
		content += ">\n";
		
		
		// <link> element content
		if (hasLinkParam()){
//			content += "<!-- Link element parameters -->\n";
			
			Iterator<NCLParam> it = linkParams.iterator();
			while (it.hasNext()) {
				NCLParam p = it.next();
				content += p.parse(ident+1, "linkParam");
			}
		}
		
//		content += "<!-- Link element binds -->\n";
		
		Iterator<NCLBind> it = binds.iterator();
		while (it.hasNext()) {
			NCLBind b = it.next();
			content += b.parse(ident+1);
		}
		
		
		// <link> element end declaration
		content += space + "</link>\n";
		
		return content;
	}
	
	
	/**
	 * Cria o código XML do link desconsiderando a indentação.
	 * 
	 * @return String com o código XML do link.
	 */
	public String toString() {
		return parse(0);
	}
	
	
	/**
	 * Compara dois links.
	 * 
	 * @param link link com o qual comparar.
	 * @return Inteiro indicando se os links são iguais. O valor será 0 se os links forem iguais.
	 */
	public int compareTo(NCLLink link) {
		if (equals(link))
			return 0;
		else
			return 1;
	}
}
