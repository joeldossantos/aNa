package br.pensario.interfaces;


/**
 * Esta classe define um indicador temporal da <i>Nested Context Language</i> (NCL).<br>
 * O indicador pode ter um dos seguintes formatos:<br>
 *    - ano:mês:dia:hora:minuto:segundo.fração<br>
 *    - hora:minuto:segundo.fração<br>
 *    - segundo.fração<br>
 *    - segundos<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLTime {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Integer fraction;
    
    
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
     *          inteiro com o valor de segundo.
     * @param fraction
     *          inteiro com o valor das frações de segundo.
     * @throws java.lang.IllegalArgumentException
     *          se algum valor não estiver no formato definido pela norma.
     *
     * @see NCLTime#setYear
     * @see NCLTime#setMonth
     * @see NCLTime#setDay
     * @see NCLTime#setHour
     * @see NCLTime#setMinute
     * @see NCLTime#setSecond
     * @see NCLTime#setFraction
     */
    public NCLTime(int year, int month, int day, int hour, int minute, int second, int fraction) throws IllegalArgumentException {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setFraction(fraction);
    }
    
    
    /**
     * Construtor do indicador temporal da <i>Nested Context Language</i> (NCL).
     */
    public NCLTime(int second) {
        setSecond(second);
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
        if (year != null && year < 0)
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
        if (month != null && !(month >= 1 && month <= 12))
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
        if (day != null && !(day >= 1 && day <= 31))
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
        if (hour != null && !(hour >= 0 && (absoluteHour() || hour <= 23)))
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
        if (minute != null && !(minute >= 0 && minute <= 59))
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
     *          inteiro que determina o valor da parte de segundo.
     *          Deve ser um inteiro no intervalo [0,59], caso hava um valor de minuto,
     *          ou um inteiro positivo, caso contrário.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setSecond(Integer second) throws IllegalArgumentException {
        if (second != null && !(second >= 0 && (absoluteSecond() || second <= 59)))
            throw new IllegalArgumentException("Invalid second");

        this.second = second;
    }
    
    
    /**
     * Retorna o valor de segundo do indicador temporal.
     *
     * @return
     *          Inteiro que determina o valor da parte de segundo.
     */
    public Integer getSecond() {
        return second;
    }
    
    
    /**
     * Atribui um valor de fração de segundo ao indicador temporal.
     *
     * @param fraction
     *          inteiro que determina o valor da parte de fração de segundo. Deve ser um inteiro positivo.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma.
     */
    public void setFraction(Integer fraction) throws IllegalArgumentException {
        if (fraction != null && fraction < 0)
            throw new IllegalArgumentException("Invalid fraction");

        this.fraction = fraction;
    }
    
    
    
    /**
     * Retorna o valor de fração de segundo do indicador temporal.
     *
     * @return
     *          Inteiro que determina o valor da parte de fração de segundo.
     */
    public Integer getFraction() {
        return fraction;
    }
    
    
    
    /**
     * Verifica se o indicador temporal está no formato UTC (ano:mês:dia:hora:minuto:segundo:fração).
     * 
     * @return
     *          verdadeiro se o indicador estiver no formato UTC.
     */
    public boolean isUTC() {
        if (getYear() != null && getMonth() != null && getDay() != null &&
                getHour() != null && getMinute() != null && getSecond() != null && getFraction()!= null)
            return true;
        else
            return false;
    }


    private boolean absoluteHour() {
        if (getYear() == null && getMonth() == null && getDay() == null)
            return true;
        else
            return false;
    }


    private boolean absoluteSecond() {
        if (getYear() == null && getMonth() == null && getDay() == null &&
                getHour() == null && getMinute() == null)
            return true;
        else
            return false;
    }
    
    
    @Override
    public String toString() {
        if (getSecond() != null){
            if (getFraction() != null){
                if (getHour() != null && getMinute() != null){
                    if (getYear() != null && getMonth() != null && getDay() != null){
                        return year + ":" + month + ":" + day + ":" + hour + ":" + minute + ":" + second + "." + fraction;
                    }
                    else
                        return hour + ":" +    minute + ":" + second + "." + fraction;
                }
                else
                    return second + "." + fraction + "s";
            }
            else
                return second + "s";
        }
        return null;
    }


    public int compareTo(NCLTime other) {
        String this_time = toString();
        String other_time = other.toString();

        if (this_time == null) this_time = "";
        if (other_time == null) other_time = "";

        return this_time.compareTo(other_time);
    }

}
