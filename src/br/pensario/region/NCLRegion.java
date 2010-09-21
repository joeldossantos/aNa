package br.pensario.region;

import java.util.Set;
import java.util.TreeSet;

public class NCLRegion implements Comparable {

	private String id;

	private String title;

	// Quais são as regras de validação?

	// Pode ser negativo?
	private Integer left;
	private Integer right;
	private Integer top;
	private Integer bottom;
	private Integer height;
	private Integer width;
	private Integer zIndex;

	// Se relativo = true então o atributo correspondente tem que ter de 0 a 100
	private Boolean relativeLeft;
	private Boolean relativeRight;
	private Boolean relativeTop;
	private Boolean relativeBottom;
	private Boolean relativeHeight;
	private Boolean relativeWidth;

	private Set<NCLRegion> regions = new TreeSet<NCLRegion>();

	// TODO - //Container separado em cada classe/ Implementar container por id
	// único em cada classe
	// TODO - Id setid getid por cada classe sem valdação
	// TODO - métodos parse
	// TODO - método has para todos os atributos que não são obrigatórios

	public NCLRegion(String id) {
		setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setLeft(Integer left, boolean relative) throws Exception {

		setRelativeLeft(relative);

		if (isRelativeLeft() && (left < 0 || left > 100)) {
			Exception ex = new Exception(
					"Valor não porcentual para atributo relativo de posicionamento ( %left = "
							+ left + ")");

			throw ex;
		}

		// DUVIDA - else existe alguma restrição para valores não relativos?

		this.left = left;

	}

	public Integer getLeft() {
		return left;
	}

	public void setRight(int right, boolean relative) throws Exception {

		setRelativeRight(relative);

		if (isRelativeRight() && (right < 0 || right > 100)) {
			Exception ex = new Exception(
					"Valor não porcentual para atributo relativo de posicionamento ( %right = "
							+ right + ")");

			throw ex;
		}

		// DUVIDA - else existe alguma restrição para valores não relativos?

		this.right = right;
	}

	public Integer getRight() {
		return right;
	}

	public void setTop(int top, boolean relative) throws Exception {
		setRelativeTop(relative);

		// TODO - Inverter valor de porcentagem
		if (isRelativeTop() && top >= 0 && top <= 100) {

			// TODO - Passar para todos os sets
			Exception ex = new Exception(
					"Valor não porcentual para atributo relativo de posicionamento ( %top = "
							+ top + ")");

			throw ex;
		}

		// DUVIDA - else existe alguma restrição para valores não relativos?

		this.top = top;
	}

	public Integer getTop() {
		return top;
	}

	public void setBottom(int bottom, boolean relative) throws Exception {
		setRelativeBottom(relative);

		if (isRelativeBottom() && (bottom < 0 || bottom > 100)) {
			Exception ex = new Exception(
					"Valor não porcentual para atributo relativo de posicionamento ( %bottom = "
							+ bottom + ")");

			throw ex;
		}

		// DUVIDA - else existe alguma restrição para valores não relativos?

		this.bottom = bottom;
	}

	public Integer getBottom() {
		return bottom;
	}

	public void setHeight(int height, boolean relative) throws Exception {
		setRelativeHeight(relative);

		if (isRelativeHeight() && (height < 0 || height > 100)) {
			throw new Exception(
					"Valor não porcentual para atributo relativo de posicionamento ( %height = "
							+ height + ")");
		}

		// else existe alguma restrição para valores não relativos?

		this.height = height;
	}

	public Integer getHeight() {
		return height;
	}

	public void setWidth(int width, boolean relative) throws Exception {
		setRelativeWidth(relative);

		if (isRelativeWidth() && (width < 0 || width > 100)) {

			Exception ex = new Exception(
					"Valor não porcentual para atributo relativo de posicionamento ( %width = "
							+ width + ")");
			throw ex;
		}

		// else existe alguma restrição para valores não relativos?

		this.width = width;
	}

	public Integer getWidth() {
		return width;
	}

	public void setzIndex(int zIndex) {

		// TODO - qual a validação para zIndex?
		this.zIndex = zIndex;
	}

	public Integer getzIndex() {
		return zIndex;
	}

	private void setRelativeLeft(boolean relativeLeft) {
		this.relativeLeft = relativeLeft;
	}

	private boolean isRelativeLeft() {
		return relativeLeft;
	}

	private void setRelativeRight(boolean relativeRight) {
		this.relativeRight = relativeRight;
	}

	private boolean isRelativeRight() {
		return relativeRight;
	}

	private void setRelativeTop(boolean relativeTop) {
		this.relativeTop = relativeTop;
	}

	private boolean isRelativeTop() {
		return relativeTop;
	}

	private void setRelativeBottom(boolean relativeBottom) {
		this.relativeBottom = relativeBottom;
	}

	private boolean isRelativeBottom() {
		return relativeBottom;
	}

	private void setRelativeHeight(boolean relativeHeight) {
		this.relativeHeight = relativeHeight;
	}

	private boolean isRelativeHeight() {
		return relativeHeight;
	}

	private void setRelativeWidth(boolean relativeWidth) {
		this.relativeWidth = relativeWidth;
	}

	private boolean isRelativeWidth() {
		return relativeWidth;
	}

	@Override
	public int compareTo(Object arg0) {
		return getId().compareTo(((NCLRegion) arg0).getId());
	}

	public String toString() {		
		return parse(0);
	}

	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<region";
		content += " id='" + getId() + "'";

		if (getLeft() != null) {
			String percent = "";
			if (isRelativeLeft())
				percent = "%";
			content += " left='" + getLeft() + percent + "'";
		}

		if (getRight() != null) {
			String percent = "";
			if (isRelativeRight())
				percent = "%";
			content += " right='" + getRight() + percent + "'";
		}

		if (getTop() != null) {
			String percent = "";
			if (isRelativeTop())
				percent = "%";
			content += " top='" + getTop() + percent + "'";
		}

		if (getBottom() != null) {
			String percent = "";
			if (isRelativeBottom())
				percent = "%";
			content += " bottom='" + getBottom() + percent + "'";
		}

		if (getHeight() != null) {
			String percent = "";
			if (isRelativeHeight())
				percent = "%";
			content += " height='" + getHeight() + percent + "'";
		}

		if (getWidth() != null) {
			String percent = "";
			if (isRelativeWidth())
				percent = "%";
			content += " width='" + getWidth() + percent + "'";
		}

		if (getzIndex() != null)
			content += " zIndex='" + getzIndex() + "'";

		if (getTitle() != null)
			content += " title='" + getTitle() + "'";

		if (hasRegion()) {
			
			content += ">\n";
			for (NCLRegion region : getRegions()) {
				content += region.parse(ident + 1);
			}
			content += "</region>\n";
		}
		else
			content += "/>\n";

		return content;
	}

	public boolean addRegion(NCLRegion region) {
		return regions.add(region);

	}

	public boolean removeRegion(NCLRegion region) {
		return regions.remove(region);
	}

	public boolean hasRegion(NCLRegion region) {
		return regions.contains(region);
	}

	public boolean hasRegion() {
		return regions.size() > 0;
	}

	public Iterable<NCLRegion> getRegions() {
		return regions;
	}

}
