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
package br.uff.midiacom.ana.transition;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionDirection;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionSubtype;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionType;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLTransition<T extends NCLTransition,
                           P extends NCLElement,
                           I extends NCLElementImpl>
        extends NCLTransitionPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P> {

    
    public NCLTransition(String id) throws XMLException {
        super(id);
    }


    public NCLTransition() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }

    
    public String parse(int ident) {
        String space, content;
        Object aux;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<transition";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseType();
        content += parseSubtype();
        content += parseDur();
        content += parseStartProgress();
        content += parseEndProgress();
        content += parseDirection();
        content += parseFadeColor();
        content += parseHorRepeat();
        content += parseVertRepeat();
        content += parseBorderWidth();
        content += parseBorderColor();
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseType() {
        NCLTransitionType aux = getType();
        if(aux != null)
            return " type='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseSubtype() {
        NCLTransitionSubtype aux = getSubtype();
        if(aux != null)
            return " subtype='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseDur() {
        TimeType aux = getDur();
        if(aux != null)
            return " dur='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseStartProgress() {
        Double aux = getStartProgress();
        if(aux != null)
            return " startProgress='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseEndProgress() {
        Double aux = getEndProgress();
        if(aux != null)
            return " endProgress='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseDirection() {
        NCLTransitionDirection aux = getDirection();
        if(aux != null)
            return " direction='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseFadeColor() {
        NCLColor aux = getFadeColor();
        if(aux != null)
            return " fadeColor='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseHorRepeat() {
        Integer aux = getHorRepeat();
        if(aux != null)
            return " horRepeat='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseVertRepeat() {
        Integer aux = getVertRepeat();
        if(aux != null)
            return " vertRepeat='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseBorderWidth() {
        Integer aux = getBorderWidth();
        if(aux != null)
            return " borderWidth='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseBorderColor() {
        NCLColor aux = getBorderColor();
        if(aux != null)
            return " borderColor='" + aux.toString() + "'";
        else
            return "";
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the type (required)
            att_name = NCLElementAttributes.TYPE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setType(NCLTransitionType.getEnumType(att_var));
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the subtype (optional)
            att_name = NCLElementAttributes.SUBTYPE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setSubtype(NCLTransitionSubtype.getEnumType(att_var));

            // set the duration (optional)
            att_name = NCLElementAttributes.DUR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDur(new TimeType(att_var));

            // set the startProgress (optional)
            att_name = NCLElementAttributes.STARTPROGRESS.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setStartProgress(new Double(att_var));
                }
                catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the endProgress (optional)
            att_name = NCLElementAttributes.ENDPROGRESS.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setEndProgress(new Double(att_var));
                }catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the direction (optional)
            att_name = NCLElementAttributes.DIRECTION.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDirection(NCLTransitionDirection.getEnumType(att_var));

            // set the fadeColor (optional)
            att_name = NCLElementAttributes.FADECOLOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setFadeColor(NCLColor.getEnumType(att_var));

            // set the horRepeat (optional)
            att_name = NCLElementAttributes.HORREPEAT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setHorRepeat(new Integer(att_var));
                }catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the vertRepeat (optional)
            att_name = NCLElementAttributes.VERTREPEAT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setVertRepeat(new Integer(att_var));
                }catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the borderWidth (optional)
            att_name = NCLElementAttributes.BORDERWIDTH.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setBorderWidth(new Integer(att_var));
                }catch (Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the borderColor (optional)
            att_name = NCLElementAttributes.BORDERCOLOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setBorderColor(NCLColor.getEnumType(att_var));
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Transition" + aux + ":\n" + ex.getMessage());
        }
    }
}
