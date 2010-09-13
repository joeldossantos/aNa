package br.pensario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO - "[\i-[:]][\c-[:]]*" \i = [_:A-Za-z] \c = [-._:A-Za-z0-9]

public abstract class NCLIdentifiable {

	protected String id;

	public void setId(String id) throws NCLIdentifiableException {

		Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");

		Matcher matcher = pattern.matcher(id);

		if (!matcher.matches()) {
			throw new NCLIdentifiableException(
					"O formato do identificador é inválido");
		}

		this.id = id;

	}

	public String getId() {
		return id;
	}

}
