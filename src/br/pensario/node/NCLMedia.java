package br.pensario.node;

import br.pensario.NCLValues.NCLMimeType;
import br.pensario.NCLValues.NCLUriType;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.*;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLMedia extends NCLNode implements Comparable<NCLMedia> {

	private String src;
	private NCLMimeType type;
	private NCLDescriptor descriptor;
	//REV: Eu nao encontrei estes relacionamentos no modelo
	private Set<NCLArea> areas = new TreeSet<NCLArea>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	
	
	public NCLMedia(String id) throws Exception {
		setId(id);
	}
	
	/**
	 * define a URI nao padrao de src da media.
	 * @param src
	 */
	public void setSrc(String src) throws Exception {
		URI uri = new URI(src);
		this.src = uri.toString();
	}
	
	/**
	 * define a URI nao padrao de src da media. Tem que ser uma URI padrao
	 * 
	 * media do tipo settings nao tem src
	 * @param src
	 */
	public void setSrc(NCLUriType type, String src) throws Exception {
		if (type == null){
			Exception ex = new NullPointerException("Null src type");
			throw ex;
		}
		if (getType() != NCLMimeType.APPLICATION_X_GINGA_SETTINGS){
			setSrc(type.toString() + src);
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid src");
			throw ex;
		}
	}
	
	/**
	 * define o src para a media do tipo time
	 * @param time
	 * @return
	 */
	public void setSrc(NCLTime time) throws Exception {
		if (time == null){
			Exception ex = new NullPointerException("Null time");
			throw ex;
		}
		// So aceita NCLTime completo
		if (time.isUTC() && getType() == NCLMimeType.APPLICATION_X_GINGA_TIME){
			this.src = time.toString();
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid src");
			throw ex;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSrc() {
		return src;
	}
	
	public boolean hasSrc() {
		if (src != null)
			return true;
		else
			return false;
	}
	
	/**
	 * define o tipo da media, sera um dos tipos padroes
	 * @param type
	 * @return true se valido false contrario
	 */
	public void setType(NCLMimeType type) throws Exception {
		if (type != null){
			this.type = type;
		}
		else{
			Exception ex = new NullPointerException("Null type");
			throw ex;
		}
	}
	
	public NCLMimeType getType() {
		return type;
	}
	
	public boolean hasType() {
		if (type != null)
			return true;
		else
			return false;
	}
	
	/**
	 * define o descritor da media
	 * @param descriptor
	 * @return true se valido false contrario
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
	
	public NCLDescriptor getDescriptor() {
		return descriptor;
	}
	
	public boolean hasDescriptor() {
		if (descriptor != null)
			return true;
		else
			return false;
	}
	
	/**
	 * retorna true se a area foi substituida e falso se nao
	 */
	public void addArea(NCLArea area) throws Exception {
		/* TODO - isso deve ser implementado na camada de validacao
		 * 
		if (getType() == NCLMimeType.APPLICATION_X_GINGA_TIME){
			// Test if area begin or end is not in UTC format. Media of type Time must have a begin in UTC format
			if((area.hasBegin() && !area.getBegin().isUTC()) || (area.hasEnd() && !area.getEnd().isUTC())){
				Exception ex = new Exception("Areas of media with type application.x-ginga-time must have begin or end attribute in UTC format.");
				throw ex;
			}
		}
		else{
			// Test if area begin or end is in UTC format. UTC format is reserved to medias of type Time.
			if((area.hasBegin() && area.getBegin().isUTC()) || (area.hasEnd() && area.getEnd().isUTC())){
				Exception ex = new Exception("Areas of media without type application.x-ginga-time can not have begin or end attribute in UTC format.");
				throw ex;
			}
		}
		*/
		if (!areas.add(area)){
			Exception ex = new Exception("Area already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}

	public void removeArea(String id) throws Exception {
		NCLArea a = new NCLArea(id);
		
		if (!areas.remove(a)){
			Exception ex = new Exception("Area does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}

	public boolean hasArea(String id) throws Exception {
		NCLArea a = new NCLArea(id);
		
		return areas.contains(a);
	}
	
	public boolean hasArea() {
		return !areas.isEmpty();
	}
	
	public Iterable<NCLArea> getAreas() {
		return areas;
	}
	
	
	/**
	 * retorna true se a propriedade foi substituida e falso se nao
	 */
	public void addProperty(NCLProperty property) throws Exception {
		if (!properties.add(property)){
			Exception ex = new Exception("Property already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}

	public void removeProperty(String name) throws Exception {
		NCLProperty p = new NCLProperty(name);
		
		if (!properties.remove(p)){
			Exception ex = new Exception("Property does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}

	public boolean hasProperty(String name) throws Exception {
		NCLProperty p = new NCLProperty(name);
		
		return properties.contains(p);
	}
	
	public boolean hasProperty() {
		return !properties.isEmpty();
	}
	
	public Iterable<NCLProperty> getProperties() {
		return properties;
	}
	
	
	public boolean equals(NCLMedia media) {
		if (getId().equals(media.getId()))
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
		
		
		// <media> element and attributes declaration
		content = space + "<media";
		content += " id='" + getId() + "'";
		if (hasSrc())
			content += " src='" + getSrc() + "'";
		if (hasType())
			content += " type='" + getType().toString() + "'";
		if (hasDescriptor())
			content += " descriptor='" + getDescriptor().getId() + "'";
		
		// Test if the media has content
		if (hasArea() || hasProperty()){
			content += ">\n";
			
			
			// <media> element content
			if (hasArea()){
//				content += "<!-- Media element anchors -->\n";
				
				Iterator<NCLArea> it = areas.iterator();
				while (it.hasNext()) {
					NCLArea a = it.next();
					content += a.parse(ident+1);
				}
			}
			
			if (hasProperty()){
//				content += "<!-- Media element properties -->\n";
				
				Iterator<NCLProperty> it = properties.iterator();
				while (it.hasNext()) {
					NCLProperty p = it.next();
					content += p.parse(ident+1);
				}
			}
			
			
			// <media> element end declaration
			content += space + "</media>\n";
		}
		else
			content += "/>\n";
		
		return content;
	}
	
	public String toString() {
		return parse(0);
	}
	
	public int compareTo(NCLMedia media) {
		return getId().compareTo(media.getId());
	}
}
