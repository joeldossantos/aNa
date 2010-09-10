package br.pensario.interfaces;

public class NCLArea extends NCLInterface {

	private String id;
	private NCLCoords coords;
	private NCLTime begin;
	private NCLTime end;
	private String text;
	private int position = -1;
	private NCLSample first;
	private NCLSample last;
	private String label;
	
	private Boolean hasCoords = false;
	private Boolean hasBegin = false;
	private Boolean hasEnd = false;
	private Boolean hasText = false;
	private Boolean hasPosition = false;
	private Boolean hasFirst = false;
	private Boolean hasLast = false;
	private Boolean hasLabel = false;
	
	
	public NCLArea(String id) throws Exception {
		if (!setId(id)){
			Exception ex = new Exception("Invalid id");
			throw ex;
		}
	}
	
	public Boolean setId(String id) {
		//TODO: validar entrada de id
		this.id = id;
		return true;
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * define ancora de conteudo representando porcoes espaciais.
	 * @param coords uma coordenada ver: NCLCoords
	 * @return true se cordenada existe falso contrario
	 */
	public Boolean setCoords(NCLCoords coords) {
		if (coords != null){
			this.coords = coords;
			hasCoords = true;
			return true;
		}
		else
			return false;
	}
	
	public NCLCoords getCoords() {
		return coords;
	}
	
	public Boolean hasCoords() {
		return hasCoords;
	}
	
	/**
	 * define ancora de conteudo representando porcoes temporais
	 * se a media for application/x-ginga-time o tempo deve ser completo
	 * @param begin um tempo de inicio ver:NCLTime
	 * @return true se tempo existe falso contrario
	 */
	public Boolean setBegin(NCLTime begin) {
		//TODO: validar quanto ao tipo de media
		if (begin != null){
			this.begin = begin;
			hasBegin = true;
			return true;
		}
		else
			return false;
	}
	
	public NCLTime getBegin() {
		return begin;
	}
	
	public Boolean hasBegin() {
		return hasBegin;
	}
	
	/**
	 * define ancora de conteudo representando porcoes temporais
	 * se a media for application/x-ginga-time o tempo deve ser completo
	 * @param end um tempo de fim ver:NCLTime
	 * @return true se tempo existe falso contrario
	 */
	public Boolean setEnd(NCLTime end) {
		//TODO: validar quanto ao tipo de media
		if (end != null){
			this.end = end;
			hasEnd = true;
			return true;
		}
		else
			return false;
	}
	
	public NCLTime getEnd() {
		return end;
	}
	
	public Boolean hasEnd() {
		return hasEnd;
	}
	
	/**
	 * definicao de ancora textual
	 * @param text padrao de texto usado como ancora
	 */
	public void setText(String text) {
		this.text = text;
		hasText = true;
	}
	
	public String getText() {
		return text;
	}
	
	public Boolean hasText() {
		return hasText;
	}
	
	/**
	 * definicao de ancora textual
	 * @param position posicao do texto
	 * @return true se posicao valida falso contrario
	 */
	public Boolean setPosition(int position) {
		if (position >= 0){
			this.position = position;
			hasPosition = true;
			return true;
		}
		else
			return false;
	}
	
	public int getPosition() {
		return position;
	}
	
	public Boolean hasPosition() {
		return hasPosition;
	}
	
	/**
	 * definicao de ancora de conteudo com base em amostra
	 * @param first inicio da ancora ver:NCLSample
	 * @return true se valido falso contrario
	 */
	public Boolean setFirst(NCLSample first) {
		if (first != null){
			this.first = first;
			hasFirst = true;
			return true;
		}
		else
			return false;
	}
	
	public NCLSample getFirst() {
		return first;
	}
	
	public Boolean hasFirst() {
		return hasFirst;
	}
	
	/**
	 * definicao de ancora de conteudo com base em amostra
	 * @param last fim da ancora var:NCLSample
	 * @return true se valido falso contrario
	 */
	public Boolean setLast(NCLSample last) {
		if (last != null){
			this.last = last;
			hasLast = true;
			return true;
		}
		else
			return false;
	}
	
	public NCLSample getLast() {
		return last;
	}
	
	public Boolean hasLast() {
		return hasLast;
	}
	
	/**
	 * definir uma ancora de conteudo baseada no atributo label,
	 * que especifica uma cadeia de caracteres que deve ser utilizada
	 * pelo exibidor de midias para identificar uma regiao de conteudo
	 * @param label string de identificacao
	 */
	public void setLabel(String label) {
		this.label = label;
		hasLabel = true;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Boolean hasLabel() {
		return hasLabel;
	}
}
