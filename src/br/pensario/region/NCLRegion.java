package br.pensario.region;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;

/**
 * Esta classe define uma região do dispositivo para a exibição de conteúdo. 
 * Esta classe é referente ao elemento <i>region</i> da <i>Nested Context Language</i> (NCL).<br>
 * 
 * @author Wagner Schau
 * @author Joel Santos
 * @version 1.0
 * 
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 * 
 */

public class NCLRegion extends NCLIdentifiableElement implements Comparable<NCLRegion> {

	private String title;

	// Pode ser negativo? (a priori, sim)
	private Integer left;
	private Integer right;
	private Integer top;
	private Integer bottom;
	private Integer height;
	private Integer width;
	private Integer zIndex;

	// Se relativo = true entao o atributo correspondente tem que ter de 0 a 100
	private Boolean relativeLeft;
	private Boolean relativeRight;
	private Boolean relativeTop;
	private Boolean relativeBottom;
	private Boolean relativeHeight;
	private Boolean relativeWidth;

	private Set<NCLRegion> regions = new TreeSet<NCLRegion>();

	/**
	 * NCLRegion class constructor. Create a new region with default parameters.
	 * 
	 * @param id
	 *            Region identification.
	 * 
	 * @exception Exception
	 *                Invalid identification format.
	 */
	public NCLRegion(String id) throws NCLInvalidIdentifierException {
		setId(id);
	}
	
	/**
	 * Two regions are considered equal based only on their identification
	 * strings.
	 * 
	 * @return True if the region and obj identifications strings are the same.
	 *         False otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NCLRegion other = (NCLRegion) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	/**
	 * Sets the region a new title.
	 * 
	 * @param title
	 *            New title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Return region title.
	 * 
	 * @return Region title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets region left position.
	 * 
	 * @param left
	 *            Left position. Based on pixels or percentage (see below)	
	 * @param relative
	 *            Set the position as a percentage value relative to the size of
	 *            the device screen or parent region.	            
	 *           
	 */
	public void setLeft(Integer left, boolean relative) throws Exception {

		setRelativeLeft(relative);

		if (isRelativeLeft() && (left < 0 || left > 100)) {
			Exception ex = new Exception(
					"Invalid percentage position value ( %left = " + left
							+ "). It must be between 0 and 100.");

			throw ex;
		}

		// DUVIDA - else existe alguma restri��o para valores n�o
		// relativos?

		this.left = left;

	}

	/**
	 * Return left position value.
	 * 
	 * @return Left position value, based in pixels or percentage depending on
	 *         relative specification.
	 * 
	 * @see NCLRegion#setLeft
	 * @see NCLRegion#isRelativeLeft
	 */
	public Integer getLeft() {
		return left;
	}

	/**
	 * Sets region right position.
	 * 
	 * @param right
	 *            Right position. Based on pixels or percentage (see below)
	 * @param relative
	 *            Set the position as a percentage value relative to the size of
	 *            the device screen.
	 */
	public void setRight(int right, boolean relative) throws Exception {

		setRelativeRight(relative);

		if (isRelativeRight() && (right < 0 || right > 100)) {
			Exception ex = new Exception(
					"Invalid percentage position value ( %right = " + right
							+ "). It must be between 0 and 100.");

			throw ex;
		}

		// DUVIDA - else existe alguma restricao para valores nao relativos?

		this.right = right;
	}

	/**
	 * Return right position value.
	 * 
	 * @return Right position value, based in pixels or percentage depending on
	 *         relative specification.
	 * 
	 * @see NCLRegion#setRight
	 * @see NCLRegion#isRelativeRight
	 */
	public Integer getRight() {
		return right;
	}

	/**
	 * Sets region top position.
	 * 
	 * @param top
	 *            Top position, in pixels or percentage (see below)
	 * @param relative
	 *            Set the position as a percentage value relative to the size of
	 *            the device screen.
	 */
	public void setTop(int top, boolean relative) throws Exception {
		setRelativeTop(relative);

		// TODO - Inverter valor de porcentagem
		if (isRelativeTop() && top >= 0 && top <= 100) {

			// TODO - Passar para todos os sets
			Exception ex = new Exception(
					"Invalid percentage position value ( %top = " + top
					+ "). It must be between 0 and 100.");

			throw ex;
		}

		// DUVIDA - else existe alguma restri��o para valores n�o
		// relativos?

		this.top = top;
	}

	/**
	 * Return top position value.
	 * 
	 * @return Top position value, in pixels or percentage depending on relative
	 *         specification.
	 * 
	 * @see NCLRegion#setRight
	 * @see NCLRegion#isRelativeRight
	 */
	public Integer getTop() {
		return top;
	}

	public void setBottom(int bottom, boolean relative) throws Exception {
		setRelativeBottom(relative);

		if (isRelativeBottom() && (bottom < 0 || bottom > 100)) {
			Exception ex = new Exception(
					"Invalid percentage position value ( %bottom = " + bottom
					+ "). It must be between 0 and 100.");

			throw ex;
		}

		// DUVIDA - else existe alguma restri��o para valores n�o
		// relativos?

		this.bottom = bottom;
	}

	public Integer getBottom() {
		return bottom;
	}

	/**
	 * Sets region height position.
	 * 
	 * @param height
	 *            Height position. Based on pixels or percentage (see below)
	 * @param relative
	 *            Set the position as a percentage value relative to the size of
	 *            the device screen.
	 */

	public void setHeight(int height, boolean relative) throws Exception {
		setRelativeHeight(relative);

		if (isRelativeHeight() && (height < 0 || height > 100)) {
			throw new Exception(
					"Valor n�o porcentual para atributo relativo de posicionamento ( %height = "
							+ height + ")");
		}

		// else existe alguma restri��o para valores n�o relativos?

		this.height = height;
	}

	public Integer getHeight() {
		return height;
	}

	/**
	 * Sets region width position.
	 * 
	 * @param width
	 *            Width position. Based on pixels or percentage (see below)
	 * @param relative
	 *            Set the position as a percentage value relative to the size of
	 *            the device screen.
	 */
	public void setWidth(int width, boolean relative) throws Exception {
		setRelativeWidth(relative);

		if (isRelativeWidth() && (width < 0 || width > 100)) {

			Exception ex = new Exception(
					"Valor n�o porcentual para atributo relativo de posicionamento ( %width = "
							+ width + ")");
			throw ex;
		}

		// else existe alguma restri��o para valores n�o relativos?

		this.width = width;
	}

	public Integer getWidth() {
		return width;
	}

	public void setzIndex(int zIndex) {

		// TODO - qual a valida��o para zIndex?
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
	public int compareTo(NCLRegion arg0) {
		return getId().compareTo(arg0.getId());
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
			content += space + "</region>\n";
		} else
			content += "/>\n";

		return content;
	}

	/**
	 * Adds a child to the parent region. The child is considered an inner region, has size and position relative
	 * to the parent region properties.
	 * 
	 * @param child
	 *            New child to be set as an inner region.
	 * 
	 * @return True if the new region replaces an old one with the same
	 *         identification string. False otherwise.
	 * 
	 * @exception Exception
	 *                The region cannot be added as a child of its own.
	 * 
	 */
	public boolean addRegion(NCLRegion child) throws Exception {

		if (equals(child))
			throw new Exception(
					"The region cannot be used as a child of its own.");

		return regions.add(child);

	}

	/**
	 * Remove the child region.
	 * 
	 * @param region
	 *            New region to be added.
	 * 
	 * @return True if the new region replaces an old one with the same
	 *         identification string. False otherwise.
	 * 
	 */
	public boolean removeRegion(NCLRegion region) {
		return regions.remove(region);
	}

	/**
	 * Used to indicate if a given region is a child of the parent region. 
	 * 
	 * @param child
	 *            Child region.
	 * 
	 * @return True if the region has the child as an inner region. False otherwise.
	 *	
	 * 
	 */
	
	public boolean hasRegion(NCLRegion child) {
		return regions.contains(child);
	}

	
	/**
	 * Indicates if the region has at least one child. 
	 * 
	 * @return True if the region has child. False otherwise.
	 *	 
	 * 
	 */
	public boolean hasRegion() {
		return regions.size() > 0;
	}

	public Iterable<NCLRegion> getRegions() {
		return regions;
	}

}
