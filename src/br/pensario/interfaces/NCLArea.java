package br.pensario.interfaces;

import br.pensario.NCLIdentification;

public class NCLArea extends NCLInterface implements Comparable<NCLArea> {

	//REV: quando ocorre isto (propriedades referenciando outras classes do pacote) e n„o est· modelado como
	//relacionamento (mas como propriedade) deve-se gerar uma relaÁ„o de dependÍncia (seta tracejada) entre
	//NCLArea -> NCLTime e NCLArea-> NCLSample
	
	private String id;
	private int[] coords;
	private NCLTime begin;
	private NCLTime end;
	private String text;
	private int position = -1;
	private NCLSample first;
	private NCLSample last;
	private String label;
	
	
	public NCLArea(String id) throws Exception {
		setId(id);
	}
	
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	public String getId() {
		return id.toString();
	}
	
	public String getIdentifier(){
		return getId();
	}
	
	/*
	 * Assim, ele permite a defini√ß√£o de √¢ncoras de conte√∫do representando
	 * por√ß√µes espaciais, atrav√©s de atributo coords (como em XHTML);
	 * 
	 * The position and shape of an image map element on the screen.
	 * 
	 * x1,y1,x2,y2,...,xN,yN
	 *
	 */
	/**
	 * define ancora de conteudo representando porcoes espaciais.
	 * @param coords uma coordenada ver: NCLCoords
	 * @return true se cordenada existe falso contrario
	 */
	public void setCoords(int[] coords) throws Exception {
		for (int i = 0; i < coords.length; i++){
			if (coords[i] < 0){
				Exception ex = new IllegalArgumentException("Invalid coordenate: " + coords[i]);
				throw ex;
			}
		}
		this.coords = coords;
	}
	
	public int[] getCoords() {
		return coords;
	}
	
	public boolean hasCoords() {
		if (coords != null && coords.length != 0)
			return true;
		else
			return false;
	}
	
	/**
	 * define ancora de conteudo representando porcoes temporais
	 * se a media for application/x-ginga-time o tempo deve ser completo
	 * @param begin um tempo de inicio ver:NCLTime
	 * @return true se tempo existe falso contrario
	 */
	public void setBegin(NCLTime begin) throws Exception {
		if (begin != null){
			this.begin = begin;
		}
		else{
			Exception ex = new NullPointerException("Null begin");
			throw ex;
		}
	}
	
	public NCLTime getBegin() {
		return begin;
	}
	
	public boolean hasBegin() {
		if (begin != null)
			return true;
		else
			return false;
	}
	
	/**
	 * define ancora de conteudo representando porcoes temporais
	 * se a media for application/x-ginga-time o tempo deve ser completo
	 * @param end um tempo de fim ver:NCLTime
	 * @return true se tempo existe falso contrario
	 */
	public void setEnd(NCLTime end) throws Exception {
		if (end != null){
			this.end = end;
		}
		else{
			Exception ex = new NullPointerException("Null end");
			throw ex;
		}
	}
	
	public NCLTime getEnd() {
		return end;
	}
	
	public boolean hasEnd() {
		if (end != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora textual
	 * @param text padrao de texto usado como ancora
	 */
	public void setText(String text) throws Exception {
		if (text != null){
			this.text = text;
		}
		else{
			Exception ex = new NullPointerException("Null text");
			throw ex;
		}
	}
	
	public String getText() {
		return text;
	}
	
	public boolean hasText() {
		if (text != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora textual
	 * @param position posicao do texto
	 * @return true se posicao valida falso contrario
	 */
	public void setPosition(int position) throws Exception {
		if (position >= 0){
			this.position = position;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid position");
			throw ex;
		}
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean hasPosition() {
		if (position != -1)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora de conteudo com base em amostra
	 * @param first inicio da ancora ver:NCLSample
	 * @return true se valido falso contrario
	 */
	public void setFirst(NCLSample first) throws Exception {
		if (first != null){
			this.first = first;
		}
		else{
			Exception ex = new NullPointerException("Null first");
			throw ex;
		}
	}
	
	public NCLSample getFirst() {
		return first;
	}
	
	public boolean hasFirst() {
		if (first != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora de conteudo com base em amostra
	 * @param last fim da ancora var:NCLSample
	 * @return true se valido falso contrario
	 */
	public void setLast(NCLSample last) throws Exception {
		if (last != null){
			this.last = last;
		}
		else{
			Exception ex = new NullPointerException("Null last");
			throw ex;
		}
	}
	
	public NCLSample getLast() {
		return last;
	}
	
	public boolean hasLast() {
		if (last != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definir uma ancora de conteudo baseada no atributo label,
	 * que especifica uma cadeia de caracteres que deve ser utilizada
	 * pelo exibidor de midias para identificar uma regiao de conteudo
	 * @param label string de identificacao
	 */
	public void setLabel(String label) throws Exception {
		if (label != null){
			this.label = label;
		}
		else{
			Exception ex = new NullPointerException("Null label");
			throw ex;
		}
	}
	
	public String getLabel() {
		return label;
	}
	
	public boolean hasLabel() {
		if (label != null)
			return true;
		else
			return false;
	}
	
	public boolean equals(NCLArea area) {
		if (getId().equals(area.getId()))
			return true;
		else
			return false;
	}
	
	public String parse(int ident) {
		//TODO: Verificar os atributos que a ancora tem para fazer algum tipo de validacao?
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <area> element and attributes declaration
		content = space + "<area";
		content += " id='" + getId() + "'";
		if (hasCoords())
			content += " coords='" + coordsToString() + "'";
		if (hasBegin())
			content += " begin='" + getBegin().toString() + "'";
		if (hasEnd())
			content += " end='" + getEnd().toString() + "'";
		if (hasText())
			content += " text='" + getText() + "'";
		if (hasPosition())
			content += " position='" + getPosition() + "'";
		if (hasFirst())
			content += " first='" + getFirst().toString() + "'";
		if (hasLast())
			content += " last='" + getLast().toString() + "'";
		if (hasLabel())
			content += " label='" + getLabel() + "'";
		content += "/>\n";
		
		return content;
	}
	
	private String coordsToString() {
		String result = "";
		for (int i = 0; i < coords.length; i++){
			result += coords[i];
			if (i < coords.length - 1)
				result += ",";
		}
		return result;
	}
	
	public String toString() {
		return parse(0);
	}

	public int compareTo(NCLArea area) {
		return getId().compareTo(area.getId());
	}
}
