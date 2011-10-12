package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.xml.XMLElementImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NCLElementImpl<T extends NCLIdentifiableElement, P extends NCLElement>
        extends XMLElementImpl<T, P> {


    @Override
    protected boolean validate(String id) {
        Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }
}
