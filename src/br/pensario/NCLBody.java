package br.pensario;

import br.pensario.node.NCLNode;
import br.pensario.interfaces.NCLInterface;
import br.pensario.link.NCLLink;
import java.util.Set;
import java.util.TreeSet;

public class NCLBody {

	private String id;
	private Set<NCLNode> nodes = new TreeSet<NCLNode>();
	private Set<NCLInterface> interfaces = new TreeSet<NCLInterface>();
	private Set<NCLLink> links = new TreeSet<NCLLink>();
	
	public NCLBody() { //TODO: implementar o construtor
		
	}
	
}
