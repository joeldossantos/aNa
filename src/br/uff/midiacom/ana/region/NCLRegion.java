/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.number.RelativeType;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Class that represents a region element.
 * 
 * <br/>
 * 
 * A region represents a space in the screen where a node can be presented. A
 * region can also have child regions defining screen spaces inside the space
 * defined by the parent region.
 * 
 * <br/>
 * 
 * The position of a region is relative to its parent, which can be another
 * region or the device screen, in case that region is a direct child of the
 * region base.
 * 
 * <br/>
 * 
 * The intrinsic size of a region is the same size of its parent. It a region
 * does not specify any position or size, it is assumed that the region has
 * the same position and size values as its parent region. In particular,
 * when the region is a direct child of the region base, it is assumed that
 * the region has all the area of the device screen.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the region. This attribute is required.</li>
 *  <li><i>title</i> - title of the region. This attribute is optional.</li>
 *  <li><i>left</i> - location of the region left side regarding the parent
 *                    left margin. This attribute is optional.</li>
 *  <li><i>right</i> - location of the region right side regarding the parent
 *                     right margin. This attribute is optional.</li>
 *  <li><i>top</i> - location of the region superior side regarding the parent
 *                   superior margin. This attribute is optional.</li>
 *  <li><i>bottom</i> - location of the region inferior side regarding the
 *                      parent inferior margin. This attribute is optional.</li>
 *  <li><i>height</i> - height of the region. This attribute is optional.</li>
 *  <li><i>width</i> - width of the region. This attribute is optional.</li>
 *  <li><i>zIndex</i> - precedence of the region regarding it superposition with
 *                      other regions. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>region</i> - element representing a region inside the region. The
 *                      region can have none or several region elements inside
 *                      it.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public class NCLRegion<T extends NCLRegion,
                       P extends NCLElement,
                       I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement<ReferenceType> {

    protected StringType title;
    protected RelativeType left;
    protected RelativeType right;
    protected RelativeType top;
    protected RelativeType bottom;
    protected RelativeType height;
    protected RelativeType width;
    protected Integer zIndex;
    protected IdentifiableElementList<T, T> regions;
    
    protected ItemList<ReferenceType> references;


    /**
     * Region element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLRegion() throws XMLException {
        super();
        regions = new IdentifiableElementList<T, T>();
        references = new ItemList<ReferenceType>();
    }
    
    
    public NCLRegion(String id) throws XMLException {
        super();
        regions = new IdentifiableElementList<T, T>();
        references = new ItemList<ReferenceType>();
        setId(id);
    }


    /**
     * Sets the title of the region. This attribute is optional. Set the title
     * to <i>null</i> to erase a title already defined.
     * 
     * @param title
     *          string representing the region title or <i>null</i> to erase a
     *          title already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setTitle(String title) throws XMLException {
        StringType aux = this.title;
        this.title = new StringType(title);
        impl.notifyAltered(NCLElementAttributes.TITLE, aux, title);
    }


    /**
     * Returns the title of the region or <i>null</i> if the attribute is not
     * defined.
     * 
     * @return 
     *          string representing the region title or <i>null</i> if the
     *          attribute is not defined.
     */
    public String getTitle() {
        if(title != null)
            return title.getValue();
        else
            return null;
    }


    /**
     * Sets the location of the region left side regarding the parent left
     * margin. This attribute is optional. Set the left location to <i>null</i>
     * to erase a left location already defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the left attribute is relative to the region
     * parent width.
     * 
     * <br/>
     * 
     * The left attribute has precedence over the other attribute that describes
     * the horizontal position of the region. It the attributes left, width and
     * right are defined, the values of the left and width attributes have
     * precedence over the attribute right.
     * 
     * @param left 
     *          region left location regarding its parent or <i>null</i> to
     *          erase a location already defined.
     */
    public void setLeft(RelativeType left) {
        RelativeType aux = this.left;
        this.left = left;
        impl.notifyAltered(NCLElementAttributes.LEFT, aux, left);
    }


    /**
     * Returns the location of the region left side regarding the parent left
     * margin or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the left attribute is relative to the region
     * parent width.
     * 
     * <br/>
     * 
     * The left attribute has precedence over the other attribute that describes
     * the horizontal position of the region. It the attributes left, width and
     * right are defined, the values of the left and width attributes have
     * precedence over the attribute right.
     * 
     * @return 
     *          region left location regarding its parent or <i>null</i> if the
     *          attribute is not defined.
     */
    public RelativeType getLeft() {
        return left;
    }


    /**
     * Sets the location of the region right side regarding the parent right
     * margin. This attribute is optional. Set the right location to <i>null</i>
     * to erase a right location already defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the right attribute is relative to the region
     * parent width.
     * 
     * <br/>
     * 
     * The right attribute has no precedence over the other attribute that
     * describes the horizontal position of the region. It the attributes left,
     * width and right are defined, the values of the left and width attributes
     * have precedence over the attribute right.
     * 
     * @param right 
     *          region right location regarding its parent or <i>null</i> to
     *          erase a location already defined.
     */
    public void setRight(RelativeType right) {
        RelativeType aux = this.right;
        this.right = right;
        impl.notifyAltered(NCLElementAttributes.RIGHT, aux, right);
    }


    /**
     * Returns the location of the region right side regarding the parent right
     * margin or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the right attribute is relative to the region
     * parent width.
     * 
     * <br/>
     * 
     * The right attribute has no precedence over the other attribute that
     * describes the horizontal position of the region. It the attributes left,
     * width and right are defined, the values of the left and width attributes
     * have precedence over the attribute right.
     * 
     * @return 
     *          region right location regarding its parent or <i>null</i> if the
     *          attribute is not defined.
     */
    public RelativeType getRight() {
        return right;
    }


    /**
     * Sets the location of the region superior side regarding the parent superior
     * margin. This attribute is optional. Set the superior location to <i>null</i>
     * to erase a superior location already defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the top attribute is relative to the region
     * parent height.
     * 
     * <br/>
     * 
     * The top attribute has precedence over the other attribute that describes
     * the vertical position of the region. It the attributes top, height and
     * bottom are defined, the values of the top and heigh attributes have
     * precedence over the attribute bottom.
     * 
     * @param top 
     *          region superior location regarding its parent or <i>null</i> to
     *          erase a location already defined.
     */
    public void setTop(RelativeType top) {
        RelativeType aux = this.top;
        this.top = top;
        impl.notifyAltered(NCLElementAttributes.TOP, aux, top);
    }


    /**
     * Returns the location of the region superior side regarding the parent
     * superior margin or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the top attribute is relative to the region
     * parent height.
     * 
     * <br/>
     * 
     * The top attribute has precedence over the other attribute that describes
     * the vertical position of the region. It the attributes top, height and
     * bottom are defined, the values of the top and heigh attributes have
     * precedence over the attribute bottom.
     * 
     * @return 
     *          region superior location regarding its parent or <i>null</i> if
     *          the attribute is not defined.
     */
    public RelativeType getTop() {
        return top;
    }


    /**
     * Sets the location of the region inferior side regarding the parent inferior
     * margin. This attribute is optional. Set the inferior location to <i>null</i>
     * to erase a inferior location already defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the bottom attribute is relative to the region
     * parent height.
     * 
     * <br/>
     * 
     * The bottom attribute has no precedence over the other attribute that
     * describes the vertical position of the region. It the attributes top,
     * height and bottom are defined, the values of the top and heigh attributes
     * have precedence over the attribute bottom.
     * 
     * @param top 
     *          region inferior location regarding its parent or <i>null</i> to
     *          erase a location already defined.
     */
    public void setBottom(RelativeType bottom) {
        RelativeType aux = this.bottom;
        this.bottom = bottom;
        impl.notifyAltered(NCLElementAttributes.BOTTOM, aux, bottom);
    }


    /**
     * Returns the location of the region inferior side regarding the parent
     * inferior margin or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the bottom attribute is relative to the region
     * parent height.
     * 
     * <br/>
     * 
     * The bottom attribute has no precedence over the other attribute that
     * describes the vertical position of the region. It the attributes top,
     * height and bottom are defined, the values of the top and heigh attributes
     * have precedence over the attribute bottom.
     * 
     * @return 
     *          region inferior location regarding its parent or <i>null</i> if
     *          the attribute is not defined.
     */
    public RelativeType getBottom() {
        return bottom;
    }


    /**
     * Sets the height of the region. This attribute is optional. Set the height
     * to <i>null</i> to erase a height already defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the height attribute is relative to the region
     * parent height.
     * 
     * <br/>
     * 
     * The height attribute has precedence over the bottom attribute when
     * describing the vertical position of the region. It the attributes top,
     * height and bottom are defined, the values of the top and heigh attributes
     * have precedence over the attribute bottom.
     * 
     * @param height 
     *          region height or <i>null</i> to erase a height already defined.
     */
    public void setHeight(RelativeType height) {
        RelativeType aux = this.height;
        this.height = height;
        impl.notifyAltered(NCLElementAttributes.HEIGHT, aux, height);
    }


    /**
     * Returns the height of the region or <i>null</i> if the attribute is not
     * defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the height attribute is relative to the region
     * parent height.
     * 
     * <br/>
     * 
     * The height attribute has precedence over the bottom attribute when
     * describing the vertical position of the region. It the attributes top,
     * height and bottom are defined, the values of the top and heigh attributes
     * have precedence over the attribute bottom.
     * 
     * @return 
     *          region height or <i>null</i> if the attribute is not defined.
     */
    public RelativeType getHeight() {
        return height;
    }


    /**
     * Sets the width of the region. This attribute is optional. Set the width
     * to <i>null</i> to erase a width already defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the width attribute is relative to the region
     * parent width.
     * 
     * <br/>
     * 
     * The width attribute has precedence over the right attribute when
     * describing the horizontal position of the region. It the attributes left,
     * width and right are defined, the values of the left and width attributes
     * have precedence over the attribute right.
     * 
     * @param width 
     *          region width or <i>null</i> to erase a width already defined.
     */
    public void setWidth(RelativeType width) {
        RelativeType aux = this.width;
        this.width = width;
        impl.notifyAltered(NCLElementAttributes.WIDTH, aux, width);
    }


    /**
     * Returns the width of the region or <i>null</i> if the attribute is not
     * defined.
     * 
     * <br/>
     * 
     * The value of the attribute can be a non-negative percent value or pixel
     * units. A percent value in the width attribute is relative to the region
     * parent width.
     * 
     * <br/>
     * 
     * The width attribute has precedence over the right attribute when
     * describing the horizontal position of the region. It the attributes left,
     * width and right are defined, the values of the left and width attributes
     * have precedence over the attribute right.
     * 
     * @return 
     *          region width or <i>null</i> if the attribute is not defined.
     */
    public RelativeType getWidth() {
        return width;
    }


    /**
     * Sets the precedence of the region regarding it superposition with other
     * regions. This attribute is optional. Set the zIndex to <i>null</i> to
     * erase a zIndex already defined.
     * 
     * <br/>
     * 
     * Regions with a higher zIndex value are on top of regions with smaller
     * zIndex values. If two nodes (A and B) are presented in two different
     * regions with the same zIndex value, if node B starts after node A, then
     * node B should be presented on top of node A. When both start at the same
     * time, the order is chosen by the presentation engine.
     * 
     * <br/>
     * 
     * The default value of a region zIndex is 0.
     * 
     * @param zIndex
     *          precedence of the region regarding it superposition with other
     *          regions or <i>null</i> to erase a zIndex already defined.
     * @throws XMLException 
     *          if the zIndex value is not in the interval [0, 250].
     */
    public void setzIndex(Integer zIndex) throws XMLException {
        if(zIndex != null && zIndex < 0 && zIndex > 250)
            throw new XMLException("Illegal index value");

        Integer aux = this.zIndex;
        this.zIndex = zIndex;
        impl.notifyAltered(NCLElementAttributes.ZINDEX, aux, zIndex);
    }


    /**
     * Returns the precedence of the region regarding it superposition with
     * other regions or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * Regions with a higher zIndex value are on top of regions with smaller
     * zIndex values. If two nodes (A and B) are presented in two different
     * regions with the same zIndex value, if node B starts after node A, then
     * node B should be presented on top of node A. When both start at the same
     * time, the order is chosen by the presentation engine.
     * 
     * <br/>
     * 
     * The default value of a region zIndex is 0.
     * 
     * @return 
     *          precedence of the region regarding it superposition with other
     *          regions or <i>null</i> if the attribute is not defined.
     */
    public Integer getzIndex() {
        return zIndex;
    }


    /**
     * Adds a region to the region. The region can have none or several region
     * elements inside it.
     * 
     * @param region
     *          element representing a region.
     * @return
     *          true if the region was added.
     * @throws XMLException 
     *          if the element representing the region is null.
     */
    public boolean addRegion(T region) throws XMLException {
        if(regions.add(region, (T) this)){
            impl.notifyInserted(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Removes a region of the region. The region can have none or several region
     * elements inside it.
     * 
     * @param region
     *          element representing a region.
     * @return
     *          true if the region was removed.
     * @throws XMLException 
     *          if the element representing the region is null.
     */
    public boolean removeRegion(T region) throws XMLException {
        if(regions.remove(region)){
            impl.notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Removes a region of the region. The region can have none or several region
     * elements inside it.
     * 
     * @param id
     *          string representing the id of the element representing a region.
     * @return
     *          true if the region was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeRegion(String id) throws XMLException {
        if(regions.remove(id)){
            impl.notifyRemoved(NCLElementSets.REGIONS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the region has a specific element representing a region
     * inside it. The region can have none or several region elements inside it.
     * 
     * @param region
     *          element representing a region.
     * @return
     *          true if the region has the region element.
     * @throws XMLException 
     *          if the element representing the region is null.
     */
    public boolean hasRegion(T child) throws XMLException {
        return regions.contains(child);
    }


    /**
     * Verifies if the region has a region with a specific id. The region can
     * have none or several region elements inside it.
     * 
     * @param id
     *          string representing the id of the element representing a region.
     * @return
     *          true if the region has the region element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasRegion(String id) throws XMLException {
        return regions.get(id) != null;
    }


    /**
     * Verifies if the region has at least one region inside it. The region can
     * have none or several region elements inside it.
     * 
     * @return 
     *          true if the region has at least one region inside it.
     */
    public boolean hasRegion() {
        return !regions.isEmpty();
    }


    /**
     * Returns the list of regions that a region have. The region can have none
     * or several region elements inside it.
     * 
     * @return 
     *          element list with all regions.
     */
    public IdentifiableElementList<T, T> getRegions() {
        return regions;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
    

    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<region";
        content += parseAttributes();
        
        if(hasRegion()) {
            content += ">\n";

            content += parseElements(ident + 1);
            
            content += space + "</region>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
            loadTitle(element);
            loadLeft(element);
            loadRight(element);
            loadTop(element);
            loadBottom(element);
            loadHeight(element);
            loadWidth(element);
            loadzIndex(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Region" + aux + ":\n" + ex.getMessage());
        }

        try{
            loadRegions(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Region" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseTitle();
        content += parseLeft();
        content += parseRight();
        content += parseTop();
        content += parseBottom();
        content += parseHeight();
        content += parseWidth();
        content += parsezIndex();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseRegions(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseLeft() {
        RelativeType aux = getLeft();
        if(aux != null)
            return " left='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadLeft(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the left (optional)
        att_name = NCLElementAttributes.LEFT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setLeft(new RelativeType(att_var));
    }
    
    
    protected String parseRight() {
        RelativeType aux = getRight();
        if(aux != null)
            return " right='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRight(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the right (optional)
        att_name = NCLElementAttributes.RIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRight(new RelativeType(att_var));
    }
    
    
    protected String parseTop() {
        RelativeType aux = getTop();
        if(aux != null)
            return " top='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadTop(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the top (optional)
        att_name = NCLElementAttributes.TOP.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setTop(new RelativeType(att_var));
    }
    
    
    protected String parseBottom() {
        RelativeType aux = getBottom();
        if(aux != null)
            return " bottom='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadBottom(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the bottom (optional)
        att_name = NCLElementAttributes.BOTTOM.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBottom(new RelativeType(att_var));
    }
    
    
    protected String parseHeight() {
        RelativeType aux = getHeight();
        if(aux != null)
            return " height='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadHeight(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the height (optional)
        att_name = NCLElementAttributes.HEIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setHeight(new RelativeType(att_var));
    }
    
    
    protected String parseWidth() {
        RelativeType aux = getWidth();
        if(aux != null)
            return " width='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadWidth(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the width (optional)
        att_name = NCLElementAttributes.WIDTH.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setWidth(new RelativeType(att_var));
    }
    
    
    protected String parsezIndex() {
        Integer aux = getzIndex();
        if(aux != null)
            return " zIndex='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadzIndex(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the zIndex (optional)
        att_name = NCLElementAttributes.ZINDEX.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            try{
                setzIndex(new Integer(att_var));
            }catch (Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        }
    }
    
    
    protected String parseTitle() {
        String aux = getTitle();
        if(aux != null)
            return " title='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadTitle(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the title (optional)
        att_name = NCLElementAttributes.TITLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setTitle(att_var);
    }
    
    
    protected String parseRegions(int ident) {
        if(!hasRegion())
            return "";
        
        String content = "";
        for(T aux : regions)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadRegions(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the child nodes
        ch_name = NCLElementAttributes.REGION.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            if(!el.getParentNode().equals(element))
                continue;

            T inst = createRegion();
            addRegion(inst);
            inst.load(el);
        }
    }
    
    
    /**
     * Searches for a region inside a region and its descendants.
     * 
     * @param id
     *          id of the region to be found.
     * @return 
     *          region or null if no region was found.
     */
    public T findRegion(String id) throws XMLException {
        T result;
        
        if(getId().equals(id))
            return (T) this;
        
        for(T region : regions){
            result = (T) region.findRegion(id);
            if(result != null)
                return result;
        }
        
        return null;
    }


    /**
     * Function to create the child element <i>region</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>region</i>.
     */
    protected T createRegion() throws XMLException {
        return (T) new NCLRegion();
    }
}
