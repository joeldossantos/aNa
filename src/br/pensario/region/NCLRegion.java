package br.pensario.region;

import br.pensario.NCLContainer;
import br.pensario.NCLIdentifiableException;

public class NCLRegion extends NCLContainer {

	private String title;

	// Quais são as regras de validação?

	// Pode ser negativo?
	private int left = -1;
	private int right = -1;
	private int top = -1;
	private int bottom = -1;
	private int height = -1;
	private int width = -1;
	private int zIndex = -1;

	// Se relativo = true então o atributo correspondente tem que ter de 0 a 100
	private boolean relativeLeft = false;
	private boolean relativeRight = false;
	private boolean relativeTop = false;
	private boolean relativeBottom = false;
	private boolean relativeHeight = false;
	private boolean relativeWidth = false;

	public NCLRegion(String id) throws NCLIdentifiableException {
		setId(id);
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

	public void setLeft(Integer left, boolean relative)
			throws RegionSetException {

		setRelativeLeft(relative);

		if (isRelativeLeft() && (left < 0 || left > 100)) {
			throw new RegionSetException(
					"Valor não porcentual para atributo relativo de posicionamento ( %left = "
							+ left + ")");
		}

		// else existe alguma restrição para valores não relativos?

		this.left = left;

	}

	public Integer getLeft() {
		return left;
	}

	public void setRight(int right, boolean relative) throws RegionSetException {

		setRelativeRight(relative);

		if (isRelativeRight() && (right < 0 || right > 100)) {
			throw new RegionSetException(
					"Valor não porcentual para atributo relativo de posicionamento ( %right = "
							+ right + ")");
		}

		this.right = right;
	}

	public int getRight() {
		return right;
	}

	public void setTop(int top, boolean relative) throws RegionSetException {
		setRelativeTop(relative);

		if (isRelativeTop() && (top < 0 || top > 100)) {
			throw new RegionSetException(
					"Valor não porcentual para atributo relativo de posicionamento ( %top = "
							+ top + ")");
		}

		this.top = top;
	}

	public int getTop() {
		return top;
	}

	public void setBottom(int bottom, boolean relative)
			throws RegionSetException {
		setRelativeBottom(relative);

		if (isRelativeBottom() && (bottom < 0 || bottom > 100)) {
			throw new RegionSetException(
					"Valor não porcentual para atributo relativo de posicionamento ( %bottom = "
							+ bottom + ")");
		}

		this.bottom = bottom;
	}

	public int getBottom() {
		return bottom;
	}

	public void setHeight(int height, boolean relative)
			throws RegionSetException {
		setRelativeHeight(relative);

		if (isRelativeHeight() && (height < 0 || height > 100)) {
			throw new RegionSetException(
					"Valor não porcentual para atributo relativo de posicionamento ( %height = "
							+ height + ")");
		}

		// else existe alguma restrição para valores não relativos?

		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width, boolean relative) throws RegionSetException {
		setRelativeWidth(relative);

		if (isRelativeWidth() && (width < 0 || width > 100)) {
			throw new RegionSetException(
					"Valor não porcentual para atributo relativo de posicionamento ( %width = "
							+ width + ")");
		}

		// else existe alguma restrição para valores não relativos?

		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setzIndex(int zIndex) {

		// TODO - qual a validação para zIndex?
		this.zIndex = zIndex;
	}

	public int getzIndex() {
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

}
