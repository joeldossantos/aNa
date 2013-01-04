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
package br.uff.midiacom.ana.util;

import br.uff.midiacom.ana.util.exception.XMLException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;


/**
 * This class represents an value represented as an array (ex.: 99,99,99).
 */
public class ArrayType implements Serializable {

    private double[] values;
    
    
    /**
     * Create the array from an array of values.
     *
     * @param values
     *          array of values.
     * @throws XMLException
     *          if one value of the array is negative.
     */
    public ArrayType(double[] values) throws XMLException {
        setValues(values);
    }


    /**
     * Create the array from an array of values.
     *
     * @param values
     *          array of values.
     * @throws XMLException
     *          if one value of the array is negative.
     */
    public ArrayType(String values) throws XMLException {
        Vector<Double> array = new Vector<Double>();
        while(values.contains(",")){
            int index = values.indexOf(",");
            array.add(new Double(values.substring(0, index)));
            values = values.substring(index + 1);
        }
        array.add(new Double(values));
        double[] a = new double[array.size()];
        for(int k = 0; k < array.size(); k++)
            a[k] = (double) array.elementAt(k);

        setValues(a);
    }


    private void setValues(double[] values) throws XMLException {
        if(values != null){
            for(double coord : values){
                if(coord < 0)
                    throw new XMLException("Invalid value: " + coord);
            }
        }

        this.values = values;
    }


    /**
     * Returns the array.
     *
     * @return
     *          array of values.
     */
    public double[] getArray() {
        return values;
    }


    /**
     * Returns the array size.
     *
     * @return
     *          integer with the array size.
     */
    public int getSize() {
        return values.length;
    }


    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < values.length; i++){
            result += values[i];
            if(i < values.length - 1)
                result += ",";
        }
        return result;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof ArrayType))
            return false;
        
        return toString().equals(o.toString());
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Arrays.hashCode(this.values);
        return hash;
    }
}
