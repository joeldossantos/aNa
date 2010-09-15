package br.pensario.node;

import br.pensario.NCLValues.NCLMimeType;
import br.pensario.NCLValues.NCLUriType;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.*;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLMedia extends NCLNode {

	private String id;
	private String src;
	private NCLMimeType type;
	private NCLDescriptor descriptor;
	
	private Set<NCLArea> areas = new TreeSet<NCLArea>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	
	
	public NCLMedia(String id) throws Exception {
		if (!setId(id)){
			Exception ex = new Exception("Invalid id");
			throw ex;
		}
	}
	
	public Boolean setId(String id) {
		//TODO: colocar a validacao do id
		this.id = id;
		return true;
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * define a URI nao padrao de src da media.
	 * @param src
	 */
	public Boolean setSrc(String src) {
		//TODO: validacao do src??
		this.src = src;
		return true;
	}
	
	/**
	 * define a URI nao padrao de src da media. Tem que ser uma URI padrao
	 * @param src
	 */
	public Boolean setSrc(NCLUriType type, String src) {
		//TODO: validacao do src com relacao ao tipo??
		if (type != null){
			this.src = type.toString() + src;
			return true;
		}
		return false;
	}
	
	public String getSrc() {
		return src;
	}
	
	/**
	 * define o tipo da media, sera um dos tipos padroes
	 * @param type
	 * @return true se valido false contrario
	 */
	public Boolean setType(NCLMimeType type) {
		if (type != null){
			this.type = type;
			return true;
		}
		else
			return false;
	}
	
	public NCLMimeType getType() {
		return type;
	}
	
	/**
	 * define o descritor da media
	 * @param descriptor
	 * @return true se valido false contrario
	 */
	public Boolean setDescriptor(NCLDescriptor descriptor) {
		if (descriptor != null){
			this.descriptor = descriptor;
			return true;
		}
		else
			return false;
	}
	
	public NCLDescriptor getDescriptor() {
		return descriptor;
	}
	
	/**
	 * retorna true se a area foi substituida e falso se nao
	 */
	public boolean addArea(NCLArea area) {
		//TODO: validar o begin com relacao ao tipo da midia???????
		boolean contains = false;

		if (!hasArea(area.getId()))
			contains = true;

		areas.add(area);

		return contains;
	}

	public boolean removeArea(String id) {
		Iterator<NCLArea> it = areas.iterator();

		while (it.hasNext()) {
			NCLArea a = it.next();

			if (a.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasArea(String id) {
		Iterator<NCLArea> it = areas.iterator();

		while (it.hasNext()) {
			NCLArea a = it.next();

			if (a.getId().equals(id))
				return true;
		}
		return false;
	}
	
	/**
	 * retorna true se a propriedade foi substituida e falso se nao
	 */
	public boolean addProperty(NCLProperty property) {
		boolean contains = false;

		if (!hasProperty(property.getName()))
			contains = true;

		properties.add(property);

		return contains;
	}

	public boolean removeProperty(String name) {
		Iterator<NCLProperty> it = properties.iterator();

		while (it.hasNext()) {
			NCLProperty p = it.next();

			if (p.getName().equals(name)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasProperty(String name) {
		Iterator<NCLProperty> it = properties.iterator();

		while (it.hasNext()) {
			NCLProperty p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
}
