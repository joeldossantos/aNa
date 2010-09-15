package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLCausalConnector;

public class NCLLink {

	private String id;
	private NCLCausalConnector xconnector;
	
	private Set<NCLLinkParam> linkParams = new TreeSet<NCLLinkParam>();
	private Set<NCLBind> binds = new TreeSet<NCLBind>();
	
	
	public NCLLink(NCLCausalConnector xconnector, NCLBind bind1, NCLBind bind2) throws Exception {
		if (!setXconnector(xconnector) || !addBind(bind1) || !addBind(bind2)){
			Exception ex = new Exception("Invalid xconnector");
			throw ex;
		}
	}
	
	public boolean setId(String id) {
		//TODO: colocar a validacao do id
		this.id = id;
		return true;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean hasId() {
		if (id != null)
			return true;
		else
			return false;
	}
	
	public boolean setXconnector(NCLCausalConnector xconnector) {
		if (xconnector != null){
			this.xconnector = xconnector;
			return true;
		}
		else
			return false;
	}
	
	public NCLCausalConnector getXconnector() {
		return xconnector;
	}
	
	/**
	 * retorna true se o parametro foi substituido e falso se nao
	 */
	public boolean addLinkParam(NCLLinkParam linkParam) {
		boolean contains = false;

		if (!hasLinkParam(linkParam.getName()))
			contains = true;

		linkParams.add(linkParam);

		return contains;
	}

	public boolean removeLinkParam(String name) {
		Iterator<NCLLinkParam> it = linkParams.iterator();

		while (it.hasNext()) {
			NCLLinkParam p = it.next();

			if (p.getName().equals(name)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasLinkParam(String name) {
		Iterator<NCLLinkParam> it = linkParams.iterator();

		while (it.hasNext()) {
			NCLLinkParam p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean hasLinkParam() {
		return(!linkParams.isEmpty());
	}
	
	/**
	 * retorna true se o bind foi substituido e falso se nao
	 */
	public boolean addBind(NCLBind bind) {
		boolean contains = false;

		if (!hasBind(bind))
			contains = true;

		binds.add(bind);

		return contains;
	}

	public boolean removeBind(NCLBind bind) {//TODO: receber o bind ou alguma identificacao?
		Iterator<NCLBind> it = binds.iterator();

		while (it.hasNext()) {
			NCLBind b = it.next();

			if (b.equals(bind) && numBinds() > 2) {//so remove se tiver mais de 2 binds
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasBind(NCLBind bind) {//TODO: receber o bind ou alguma identificacao?
		Iterator<NCLBind> it = binds.iterator();

		while (it.hasNext()) {
			NCLBind b = it.next();

			if (b.equals(bind))
				return true;
		}
		return false;
	}
	
	public int numBinds() {
		return(binds.size());
	}
	
	public String parse(int ident) {
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
