package br.pensario;

import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.region.NCLRegionBase;


/**
 * Esta classe define o elemento <i>head</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define o cabeçalho de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 */
public class NCLHead extends NCLElement{
    
    private NCLRegionBase rbase;
    private NCLDescriptorBase dbase;
    private NCLConnectorBase cbase;


    /**
     * Atribui uma base de regiões ao cabeçalho do documento NCL.
     *
     * @param rbase
     *          elemento representando a base de regiões NCL a ser utilizada pelo cabeçalho.
     */
    public void setRegionBase(NCLRegionBase rbase) {
        this.rbase = rbase;
    }


    /**
     * Retorna a base de regiões utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de regiões NCL a ser utilizada pelo cabeçalho.
     */
    
    public NCLRegionBase getRegionBase() {
        return rbase;
    }


    /**
     * Atribui uma base de descritores ao cabeçalho do documento NCL.
     *
     * @param dbase
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */    
    public void setDescriptorBase(NCLDescriptorBase dbase) {
        this.dbase = dbase;
    }

    
    /**
     * Retorna a base de descritores utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de descritores NCL a ser utilizada pelo cabeçalho.
     */
    public NCLDescriptorBase getDescriptorBase() {
        return dbase;
    }


    /**
     * Atribui uma base de conectores ao cabeçalho do documento NCL.
     *
     * @param cbase
     *          elemento representando a base de conectores NCL a ser utilizada pelo cabeçalho.
     */
    public void setConnectorBase(NCLConnectorBase cbase) {
        this.cbase = cbase;
    }

    
    /**
     * Retorna a base de conectores utilizada pelo cabeçalho do documento NCL.
     *
     * @return
     *          elemento representando a base de conectores NCL a ser utilizada pelo cabeçalho.
     */
    public NCLConnectorBase getConnectorBase() {
        return cbase;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";
                
        content = space + "<head>\n";    
        
        if(rbase != null)
            content += rbase.parse(ident + 1);        
        
        if(dbase != null)
            content += dbase.parse(ident + 1);
        
        if(cbase != null)
            content += cbase.parse(ident + 1);        
        
        content += space + "</head>\n";
        
        return content;
    }
    
}
