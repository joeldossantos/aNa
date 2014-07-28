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

import java.io.Serializable;
import java.util.Vector;


/**
 * Esta classe define um indicador temporal da <i>Nested Context Language</i> (NCL).<br/>
 * O indicador pode ter um dos seguintes formatos:<br/>
 *    - ano:mês:dia:hora:minuto:segundo.fração<br/>
 *    - hora:minuto:segundo.fração<br/>
 *    - segundo.fração<br/>
 *    - segundos<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class TimeType implements Serializable {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Double second;
    
    
    /**
     * Construtor do indicador temporal da <i>Nested Context Language</i> (NCL).
     * 
     * @param year
     *          inteiro com o valor de ano.
     * @param month
     *          inteiro com o valor de mês.
     * @param day
     *          inteiro com o valor de dia.
     * @param hour
     *          inteiro com o valor de hora.
     * @param minute
     *          inteiro com o valor de minuto.
     * @param second
     *          double com o valor de segundo.
     * @throws java.lang.IllegalArgumentException
     *          se algum valor não estiver no formato definido pela norma.
     *
     * @see TimeType#setYear
     * @see TimeType#setMonth
     * @see TimeType#setDay
     * @see TimeType#setHour
     * @see TimeType#setMinute
     * @see TimeType#setSecond
     */
    public TimeType(Integer year, Integer month, Integer day, Integer hour, Integer minute, Double second) throws IllegalArgumentException {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }
    
    
    /**
     * Construtor do indicador temporal da <i>Nested Context Language</i> (NCL).
     *
     * @param second
     *          double com o valor de segundo.
     * @throws java.lang.IllegalArgumentException
     *          se algum valor não estiver no formato definido pela norma.
     *
     * @see TimeType#setSecond
     */
    public TimeType(Double second) {
        setSecond(second);
    }


    /**
     * Construtor do indicador temporal da <i>Nested Context Language</i> (NCL).
     *
     * @param second
     *          inteiro com o valor de segundo.
     * @throws java.lang.IllegalArgumentException
     *          se algum valor não estiver no formato definido pela norma.
     *
     * @see TimeType#setSecond
     */
    public TimeType(Integer second) {
        setSecond(second);
    }


    /**
     * Construtor do indicador temporal da <i>Nested Context Language</i> (NCL).
     *
     * @param time
     *          String contendo o valor de tempo a ser transformado em um objeto TimeType.
     */
    public TimeType(String time) {
        stringToTime(time);
    }
    
    
    /**
     * Atribui um valor de ano ao indicador temporal.
     * 
     * @param year
     *          inteiro que determina o valor da parte de ano. Deve ser um inteiro positivo.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setYear(Integer year) throws IllegalArgumentException {
        if(year != null && year < 0)
            throw new IllegalArgumentException("Invalid year");

        this.year = year;
    }
    
    
    /**
     * Retorna o valor de ano do indicador temporal.
     * 
     * @return
     *          inteiro que determina o valor da parte de ano.
     */
    public Integer getYear() {
        return year;
    }
    
    
    /**
     * Atribui um valor de mês ao indicador temporal.
     * 
     * @param month
     *          Inteiro que determina o valor da parte de mês. Deve ser um inteiro no intervalo [1,12].
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setMonth(Integer month) throws IllegalArgumentException {
        if(month != null && !(month >= 1 && month <= 12))
            throw new IllegalArgumentException("Invalid month");

        this.month = month;
    }
    
    
    /**
     * Retorna o valor de mês do indicador temporal.
     * 
     * @return
     *          Inteiro que determina o valor da parte de mês.
     */
    public Integer getMonth() {
        return month;
    }
    
    
    /**
     * Atribui um valor de dia ao indicador temporal.
     * 
     * @param day
     *          inteiro que determina o valor da parte de dia. Deve ser um inteiro no intervalo [1,31].
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setDay(Integer day) throws IllegalArgumentException {
        if(day != null && !(day >= 1 && day <= 31))
            throw new IllegalArgumentException("Invalid day");

        this.day = day;
    }
    
    
    /**
     * Retorna o valor de dia do indicador temporal.
     * 
     * @return
     *          Inteiro que determina o valor da parte de dia.
     */
    public Integer getDay() {
        return day;
    }
    
    
    /**
     * Atribui um valor de hora ao indicador temporal.
     *
     * @param hour
     *          inteiro que determina o valor da parte de hora.
     *          Deve ser um inteiro no intervalo [0,23], caso hava um valor de dia,
     *          ou um inteiro positivo, caso contrário.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setHour(Integer hour) throws IllegalArgumentException {
        if(hour != null && !(hour >= 0 && (absoluteHour() || hour <= 23)))
            throw new IllegalArgumentException("Invalid hour");

        this.hour = hour;
    }
    
    
    /**
     * Retorna o valor de hora do indicador temporal.
     *
     * @return
     *          Inteiro que determina o valor da parte de hora.
     */
    public Integer getHour() {
        return hour;
    }


    /**
     * Atribui um valor de minuto ao indicador temporal.
     *
     * @param minute
     *          inteiro que determina o valor da parte de minuto. Deve ser um inteiro no intervalo [0,59].
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setMinute(Integer minute) throws IllegalArgumentException {
        if(minute != null && !(minute >= 0 && minute <= 59))
            throw new IllegalArgumentException("Invalid minute");

        this.minute = minute;
    }
    
    
    /**
     * Retorna o valor de minuto do indicador temporal.
     *
     * @return
     *          Inteiro que determina o valor da parte de minuto.
     */
    public Integer getMinute() {
        return minute;
    }
    
    
    /**
     * Atribui um valor de segundo ao indicador temporal.
     *
     * @param second
     *          double que determina o valor da parte de segundo.
     *          Deve ser um inteiro no intervalo [0,59], caso hava um valor de minuto,
     *          ou um inteiro positivo, caso contrário.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setSecond(Double second) throws IllegalArgumentException {
        if(second != null && !(second >= 0 && (absoluteSecond() || second <= 59)))
            throw new IllegalArgumentException("Invalid second");

        this.second = second;
    }


    /**
     * Atribui um valor de segundo ao indicador temporal.
     *
     * @param second
     *          integer que determina o valor da parte de segundo.
     *          Deve ser um inteiro no intervalo [0,59], caso hava um valor de minuto,
     *          ou um inteiro positivo, caso contrário.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setSecond(Integer second) throws IllegalArgumentException {
        if(second == null && !(second >= 0 && (absoluteSecond() || second <= 59)))
            throw new IllegalArgumentException("Invalid second");

        this.second = second.doubleValue();
    }
    
    
    /**
     * Retorna o valor de segundo do indicador temporal.
     *
     * @return
     *          Inteiro que determina o valor da parte de segundo.
     */
    public Double getSecond() {
        return second;
    }


    /**
     * Transforma uma String em um objeto TimeType.
     *
     * @param time
     *          String representando o valor de tempo.
     */
    public void stringToTime(String time) {
        Vector<Integer>  timeParts = new Vector<Integer>();
        String newTime;
        boolean hasDot = false;

        if(time.contains("s")){
            newTime = time.substring(0, time.length() - 1);
            hasDot = newTime.contains(".");
            setSecond(new Double(newTime));
        }
        else{
            newTime = time;
            while(newTime.contains(":")){
                int index = newTime.indexOf(":");
                timeParts.add(new Integer(newTime.substring(0, index)));
                newTime = newTime.substring(index + 1);
            }
            setSecond(new Double(newTime));
        }

        try{
            int index = timeParts.size() - 1;
            setMinute(timeParts.elementAt(index));
            index--;
            setHour(timeParts.elementAt(index));
            index--;
            setDay(timeParts.elementAt(index));
            index--;
            setMonth(timeParts.elementAt(index));
            index--;
            setYear(timeParts.elementAt(index));
        }
        catch(ArrayIndexOutOfBoundsException ex){}
    }
    
    
    
    /**
     * Verifica se o indicador temporal está no formato UTC (ano:mês:dia:hora:minuto:segundo:fração).
     * 
     * @return
     *          verdadeiro se o indicador estiver no formato UTC.
     */
    public boolean isUTC() {
        if(getYear() != null && getMonth() != null && getDay() != null &&
                getHour() != null && getMinute() != null && getSecond() != null)
            return true;
        else
            return false;
    }


    private boolean absoluteHour() {
        if(getYear() == null && getMonth() == null && getDay() == null)
            return true;
        else
            return false;
    }


    private boolean absoluteSecond() {
        if(getYear() == null && getMonth() == null && getDay() == null &&
                getHour() == null && getMinute() == null)
            return true;
        else
            return false;
    }
    
    
    public Double getTimeInSeconds() {
        Double result = 0.0;
        
        if(second != null) result += second;
        if(minute != null) result += minute * 60;
        if(hour != null) result += hour * 3600;
        if(day != null) result += day * 86400;
        if(month != null) result += month * 2592000;
        if(year != null) result += year * 31536000;
        
        return result;
    }
    
    
    @Override
    public String toString() {
        if(getSecond() == null)
            return null;
        
        String result = "";

        if(getYear() != null && getMonth() != null && getDay() != null)
            result += getYear() + ":" + getMonth() + ":" + getDay() + ":";
        if(getHour() != null && getMinute() != null)
            result += getHour() + ":" + getMinute() + ":";

        result += getSecond();

        if(absoluteSecond())
            result += "s";

        return result;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof TimeType))
            return false;
        
        return toString().equals(o.toString());
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.year != null ? this.year.hashCode() : 0);
        hash = 29 * hash + (this.month != null ? this.month.hashCode() : 0);
        hash = 29 * hash + (this.day != null ? this.day.hashCode() : 0);
        hash = 29 * hash + (this.hour != null ? this.hour.hashCode() : 0);
        hash = 29 * hash + (this.minute != null ? this.minute.hashCode() : 0);
        hash = 29 * hash + (this.second != null ? this.second.hashCode() : 0);
        return hash;
    }
}
