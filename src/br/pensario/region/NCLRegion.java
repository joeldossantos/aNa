package br.pensario.region;


public class NCLRegion extends NCLRegionContainer {

	private String id;
	private String title;
	
	//Quais são as regras de validação?
	
	//Pode ser negativo?
	private int left = -1;
	private int right = -1;
	private int top = -1;
	private int bottom = -1;
	private int height = -1;	
	private int width = -1;
	private int zIndex = -1;
	
	
	//Se relativo = true então o atributo correspondente tem que ter de 0 a 100
	private boolean relativeLeft = false;
	private boolean relativeRight = false;
	private boolean relativeTop = false;
	private boolean relativeBottom = false;
	private boolean relativeHeight = false;
	private boolean relativeWidth = false;	
	
	public NCLRegion (String id)
	{
		setId(id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NCLRegion other = (NCLRegion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setLeft(Integer left) {		
		this.left = left;
	}

	public Integer getLeft() {
		return left;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getRight() {
		return right;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getTop() {
		return top;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getBottom() {
		return bottom;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setRelativeLeft(boolean relativeLeft) {
		this.relativeLeft = relativeLeft;
	}

	public boolean isRelativeLeft() {
		return relativeLeft;
	}

	public void setRelativeRight(boolean relativeRight) {
		this.relativeRight = relativeRight;
	}

	public boolean isRelativeRight() {
		return relativeRight;
	}

	public void setRelativeTop(boolean relativeTop) {
		this.relativeTop = relativeTop;
	}

	public boolean isRelativeTop() {
		return relativeTop;
	}

	public void setRelativeBottom(boolean relativeBottom) {
		this.relativeBottom = relativeBottom;
	}

	public boolean isRelativeBottom() {
		return relativeBottom;
	}

	public void setRelativeHeight(boolean relativeHeight) {
		this.relativeHeight = relativeHeight;
	}

	public boolean isRelativeHeight() {
		return relativeHeight;
	}

	public void setRelativeWidth(boolean relativeWidth) {
		this.relativeWidth = relativeWidth;
	}

	public boolean isRelativeWidth() {
		return relativeWidth;
	}
			
}
