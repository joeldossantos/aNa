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
import br.uff.midiacom.ana.datatype.aux.reference.ReferredElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
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
        implements NCLIdentifiableElement<T, P>, ReferredElement<P> {

    protected String title;
    protected Object left;
    protected Object right;
    protected Object top;
    protected Object bottom;
    protected Object height;
    protected Object width;
    protected Integer zIndex;
    protected IdentifiableElementList<T, T> regions;
    
    protected ElementList<P, NCLElement> references;


    /**
     * Region element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLRegion() throws XMLException {
        super();
        regions = new IdentifiableElementList<T, T>();
        references = new ElementList<P, NCLElement>();
    }
    
    
    public NCLRegion(String id) throws XMLException {
        super();
        regions = new IdentifiableElementList<T, T>();
        references = new ElementList<P, NCLElement>();
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
        if(title != null && "".equals(title.trim()))
            throw new XMLException("Empty title string");
        
        String aux = this.title;
        this.title = title;
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
        return title;
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
     *          integer representing an absolute and double representing a relative
     *          region left location or <i>null</i> to erase a location already defined.
     */
    public void setLeft(Object left) throws XMLException {
        Object aux = this.left;
        
        if(left == null){
            this.left = left;
            impl.notifyAltered(NCLElementAttributes.LEFT, aux, left);
            return;
        }
        
        if(left instanceof String){
            String value = (String) left;
            if("".equals(value.trim()))
                throw new XMLException("Empty left String");

            boolean relative = false;
            int index = value.indexOf("%");
            if(index > 0){
                value = value.substring(0, index);
                relative = true;
            }

            if(relative)
                this.left = new Double(value);
            else
                this.left = new Integer(value);
        }
        else if(left instanceof Integer || left instanceof Double)
            this.left = left;
        else
            throw new XMLException("Wrong left type.");
        
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
     *          integer representing an absolute and double representing a relative
     *          region left location or <i>null</i> if the attribute is not defined.
     */
    public Object getLeft() {
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
     *          integer representing an absolute and double representing a relative
     *          region right location or <i>null</i> to erase a location already defined.
     */
    public void setRight(Object right) throws XMLException {
        Object aux = this.right;
        
        if(right == null){
            this.right = right;
            impl.notifyAltered(NCLElementAttributes.RIGHT, aux, right);
            return;
        }
        
        if(right instanceof String){
            String value = (String) right;
            if("".equals(value.trim()))
                throw new XMLException("Empty right String");

            boolean relative = false;
            int index = value.indexOf("%");
            if(index > 0){
                value = value.substring(0, index);
                relative = true;
            }

            if(relative)
                this.right = new Double(value);
            else
                this.right = new Integer(value);
        }
        else if(right instanceof Integer || right instanceof Double)
            this.right = right;
        else
            throw new XMLException("Wrong right type.");
        
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
     *          integer representing an absolute and double representing a relative
     *          region right location or <i>null</i> if the attribute is not defined.
     */
    public Object getRight() {
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
     *          integer representing an absolute and double representing a relative
     *          region top location or <i>null</i> to erase a location already defined.
     */
    public void setTop(Object top) throws XMLException {
        Object aux = this.top;
        
        if(top == null){
            this.top = top;
            impl.notifyAltered(NCLElementAttributes.TOP, aux, top);
            return;
        }
        
        if(top instanceof String){
            String value = (String) top;
            if("".equals(value.trim()))
                throw new XMLException("Empty top String");

            boolean relative = false;
            int index = value.indexOf("%");
            if(index > 0){
                value = value.substring(0, index);
                relative = true;
            }

            if(relative)
                this.top = new Double(value);
            else
                this.top = new Integer(value);
        }
        else if(top instanceof Integer || top instanceof Double)
            this.top = top;
        else
            throw new XMLException("Wrong top type.");
        
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
     *          integer representing an absolute and double representing a relative
     *          region top location or <i>null</i> if the attribute is not defined.
     */
    public Object getTop() {
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
     * @param bottom 
     *          integer representing an absolute and double representing a relative
     *          region bottom location or <i>null</i> to erase a location already defined.
     */
    public void setBottom(Object bottom) throws XMLException {
        Object aux = this.bottom;
        
        if(bottom == null){
            this.bottom = bottom;
            impl.notifyAltered(NCLElementAttributes.BOTTOM, aux, bottom);
            return;
        }
        
        if(bottom instanceof String){
            String value = (String) bottom;
            if("".equals(value.trim()))
                throw new XMLException("Empty bottom String");

            boolean relative = false;
            int index = value.indexOf("%");
            if(index > 0){
                value = value.substring(0, index);
                relative = true;
            }

            if(relative)
                this.bottom = new Double(value);
            else
                this.bottom = new Integer(value);
        }
        else if(bottom instanceof Integer || bottom instanceof Double)
            this.bottom = bottom;
        else
            throw new XMLException("Wrong bottom type.");
        
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
     *          integer representing an absolute and double representing a relative
     *          region bottom location or <i>null</i> if the attribute is not defined.
     */
    public Object getBottom() {
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
     *          integer representing an absolute and double representing a relative
     *          region height or <i>null</i> to erase a height already defined.
     */
    public void setHeight(Object height) throws XMLException {
        Object aux = this.height;
        
        if(height == null){
            this.height = height;
            impl.notifyAltered(NCLElementAttributes.HEIGHT, aux, height);
            return;
        }
        
        if(height instanceof String){
            String value = (String) height;
            if("".equals(value.trim()))
                throw new XMLException("Empty bottom String");

            boolean relative = false;
            int index = value.indexOf("%");
            if(index > 0){
                value = value.substring(0, index);
                relative = true;
            }

            if(relative)
                this.height = new Double(value);
            else
                this.height = new Integer(value);
        }
        else if(height instanceof Integer || height instanceof Double)
            this.height = height;
        else
            throw new XMLException("Wrong bottom type.");
        
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
     *          integer representing an absolute and double representing a relative
     *          region height or <i>null</i> if the attribute is not defined.
     */
    public Object getHeight() {
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
     *          integer representing an absolute and double representing a relative
     *          region width or <i>null</i> to erase a width already defined.
     */
    public void setWidth(Object width) throws XMLException {
        Object aux = this.width;
        
        if(width == null){
            this.width = width;
            impl.notifyAltered(NCLElementAttributes.WIDTH, aux, width);
            return;
        }
        
        if(width instanceof String){
            String value = (String) width;
            if("".equals(value.trim()))
                throw new XMLException("Empty bottom String");

            boolean relative = false;
            int index = value.indexOf("%");
            if(index > 0){
                value = value.substring(0, index);
                relative = true;
            }

            if(relative)
                this.width = new Double(value);
            else
                this.width = new Integer(value);
        }
        else if(width instanceof Integer || width instanceof Double)
            this.width = width;
        else
            throw new XMLException("Wrong bottom type.");
        
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
     *          integer representing an absolute and double representing a relative
     *          region width or <i>null</i> if the attribute is not defined.
     */
    public Object getWidth() {
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


    @Override
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
        Object aux = getLeft();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer)
            return " left='" + aux.toString() + "'";
        else
            return " left='" + aux.toString() + "%'";
            
    }
    
    
    protected void loadLeft(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the left (optional)
        att_name = NCLElementAttributes.LEFT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setLeft(att_var);
    }
    
    
    protected String parseRight() {
        Object aux = getRight();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer)
            return " right='" + aux.toString() + "'";
        else
            return " right='" + aux.toString() + "%'";
    }
    
    
    protected void loadRight(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the right (optional)
        att_name = NCLElementAttributes.RIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setRight(att_var);
    }
    
    
    protected String parseTop() {
        Object aux = getTop();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer)
            return " top='" + aux.toString() + "'";
        else
            return " top='" + aux.toString() + "%'";
    }
    
    
    protected void loadTop(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the top (optional)
        att_name = NCLElementAttributes.TOP.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setTop(att_var);
    }
    
    
    protected String parseBottom() {
        Object aux = getBottom();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer)
            return " bottom='" + aux.toString() + "'";
        else
            return " bottom='" + aux.toString() + "%'";
    }
    
    
    protected void loadBottom(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the bottom (optional)
        att_name = NCLElementAttributes.BOTTOM.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setBottom(att_var);
    }
    
    
    protected String parseHeight() {
        Object aux = getHeight();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer)
            return " height='" + aux.toString() + "'";
        else
            return " height='" + aux.toString() + "%'";
    }
    
    
    protected void loadHeight(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the height (optional)
        att_name = NCLElementAttributes.HEIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setHeight(att_var);
    }
    
    
    protected String parseWidth() {
        Object aux = getWidth();
        if(aux == null)
            return "";
        
        if(aux instanceof Integer)
            return " width='" + aux.toString() + "'";
        else
            return " width='" + aux.toString() + "%'";
    }
    
    
    protected void loadWidth(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the width (optional)
        att_name = NCLElementAttributes.WIDTH.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setWidth(att_var);
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
    
    
    @Override
    public boolean addReference(P reference) throws XMLException {
        return references.add(reference, null);
    }
    
    
    @Override
    public boolean removeReference(P reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ElementList<P, NCLElement> getReferences() {
        return references;
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
