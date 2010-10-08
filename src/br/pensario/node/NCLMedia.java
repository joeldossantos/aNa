package br.pensario.node;

import br.pensario.NCLValues.NCLMimeType;
import br.pensario.NCLValues.NCLUriType;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.*;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe representando o elemento media da linguagem NCL.
 */
public class NCLMedia extends NCLNode {

	private String src;
	private NCLMimeType type;
	private NCLDescriptor descriptor;
	
	private Set<NCLArea> areas = new TreeSet<NCLArea>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	
	
	/**
	 * Construtor da media.
	 * 
	 * @param id String com o id da media.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public NCLMedia(String id) throws Exception {
		setId(id);
	}
	
	
	/**
	 * Determina a URI do atributo src da mídia.
	 * 
	 * @param src String com a src da mídia.
	 * @throws Exception se a src for inválida.
	 */
	public void setSrc(String src) throws Exception {
		URI uri = new URI(src);
		this.src = uri.toString();
	}
	
	
	/**
	 * Determina a URI do atributo src da mídia. Tem que ser uma URI padrao.
	 * Mídias do tipo settings não tem src.
	 * 
	 * @param type tipo da src.
	 * @param src URI da mídia.
	 * @throws Exception se o tipo ou src forem inválidos.
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
	 * Determina o atributo src para mídias do tipo Time.
	 * 
	 * @param time NCLTime indicador de tempo.
	 * @throws NullPointerException se o indicador for nulo;
	 *         IllegalArgumentException se o argumento for definido para mídias que não são do tipo Time.
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
	 * Obtém o valor do atributo src da mídia.
	 * 
	 * @return String com o valor do atributo.
	 */
	public String getSrc() {
		return src;
	}
	
	
	/**
	 * Verifica se a mídia possui um src.
	 * 
	 * @return True se a mídia possuir o atributo.
	 */
	public boolean hasSrc() {
		return (src != null);
	}
	
	
	/**
	 * Determina valor do atributo type da mídia. O valor desse atributo será um dos tipos padrões.
	 * 
	 * @param type tipo da mídia.
	 * @throws NullPointerException se o tipo for nulo.
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
	
	
	/**
	 * Obtém o tipo da mídia.
	 * 
	 * @return NCLMimeType representando o tipo da mídia.
	 */
	public NCLMimeType getType() {
		return type;
	}
	
	
	/**
	 * Verifica se a mídia tem um tipo.
	 * 
	 * @return True se a mídia possuir um tipo.
	 */
	public boolean hasType() {
		return (type != null);
	}
	
	
	/**
	 * Determina o descritor da mídia.
	 * 
	 * @param descriptor descritor da mídia.
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
	 * Obtém o descritor da mídia.
	 * 
	 * @return NCLDescriptor representando o descritor.
	 */
	public NCLDescriptor getDescriptor() {
		return descriptor;
	}
	
	
	/**
	 * Verifica se a mídia possui um descritor.
	 * 
	 * @return True se a mídia possui um descritor.
	 */
	public boolean hasDescriptor() {
		return (descriptor != null);
	}
	
	
	/**
	 * Adiciona uma âncora a uma mídia.
	 * 
	 * @throws Exception se a mídia já possuir a âncora sendo adicionada.
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
	
	
	/**
	 * Remove uma âncora da mídia.
	 * 
	 * @param id id da âncora a ser removida.
	 * @throws Exception se não existir âncora com o id indicado.
	 */
	public void removeArea(String id) throws Exception {
		NCLArea a = new NCLArea(id);
		
		if (!areas.remove(a)){
			Exception ex = new Exception("Area does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma âncora da mídia.
	 * 
	 * @param area âncora a ser removida.
	 * @throws Exception se não existir âncora com o id indicado.
	 */
	public void removeArea(NCLArea area) throws Exception {
		if (!areas.remove(area)){
			Exception ex = new Exception("Area does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se a mídia possui uma âncora.
	 * 
	 * @param id id da âncora sendo verificada.
	 * @return True se a âncora for uma âncora da mídia.
	 * @throws Exception se o id for inválido.
	 */
	public boolean hasArea(String id) throws Exception {
		NCLArea a = new NCLArea(id);
		
		return areas.contains(a);
	}
	
	
	/**
	 * Verifica se a mídia possui uma âncora.
	 * 
	 * @param area âncora sendo verificada.
	 * @return True se a âncora for uma âncora da mídia.
	 * @throws NullPointerException se area for nulo.
	 */
	public boolean hasArea(NCLArea area) throws Exception {
		return areas.contains(area);
	}
	
	
	/**
	 * Verifica se a mídia possui alguma âncora.
	 * 
	 * @return True se a mídia possuir alguma âncora.
	 */
	public boolean hasArea() {
		return !areas.isEmpty();
	}
	
	
	/**
	 * Obtém as âncoras da mídia.
	 * 
	 * @return Iterable com as âncoras da mídia.
	 */
	public Iterable<NCLArea> getAreas() {
		return areas;
	}
	
	
	/**
	 * Adiciona uma propriedade a uma mídia.
	 * 
	 * @throws Exception se a mídia já possuir a propriedade sendo adicionada.
	 */
	public void addProperty(NCLProperty property) throws Exception {
		if (!properties.add(property)){
			Exception ex = new Exception("Property already exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Remove uma propriedade da mídia.
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
	 * Remove uma propriedade da mídia.
	 * 
	 * @param property propriedade a ser removida.
	 * @throws Exception se não existir propriedade com o nome indicado.
	 */
	public void removeProperty(NCLProperty property) throws Exception {
		if (!properties.remove(property)){
			Exception ex = new Exception("Property does not exists"); //TODO: Criar tipo de excecao
			throw ex;
		}
	}
	
	
	/**
	 * Verifica se a mídia possui uma propriedade.
	 * 
	 * @param name nome da propriedade sendo verificada.
	 * @return True se a propriedade for uma propriedade da mídia.
	 * @throws Exception se o nome for inválido.
	 */
	public boolean hasProperty(String name) throws Exception {
		NCLProperty p = new NCLProperty(name);
		
		return properties.contains(p);
	}
	
	
	/**
	 * Verifica se a mídia possui uma propriedade.
	 * 
	 * @param property propriedade sendo verificada.
	 * @return True se a propriedade for uma propriedade da mídia.
	 * @throws Exception se o nome for inválido.
	 */
	public boolean hasProperty(NCLProperty property) throws Exception {
		return properties.contains(property);
	}
	
	
	/**
	 * Verifica se a mídia possui alguma propriedade.
	 * 
	 * @return True se a mídia possuir alguma propriedade.
	 */
	public boolean hasProperty() {
		return !properties.isEmpty();
	}
	
	
	/**
	 * Obtém as propriedades da mídia.
	 * 
	 * @return Iterable com as propriedades da mídia.
	 */
	public Iterable<NCLProperty> getProperties() {
		return properties;
	}
	
	
	/**
	 * Determina se duas mídias são iguais.
	 * 
	 * @param media mídia com a qual comparar.
	 * @return True se as mídias forem iguais.
	 */
	public boolean equals(NCLMedia media) {
		if (getId().equals(media.getId()))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Cria o código XML de uma mídia.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML da mídia.
	 */
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
	
	
	/**
	 * Cria o código XML de uma mídia desconsiderando a indentação.
	 * 
	 * @return String com o código XML da mídia.
	 */
	public String toString() {
		return parse(0);
	}
}
