package br.pensario.descriptor;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.region.NCLRegion;

public class NCLDescriptorBase {

	private String id;
	Set<NCLDescriptor> descriptors = new TreeSet<NCLDescriptor>();

	public NCLDescriptorBase(String id) {
		setId(id);
	}
	
	public NCLDescriptorBase(){}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean addDescriptor(NCLDescriptor descriptor) {
		return descriptors.add(descriptor);

	}

	public boolean removeDescriptor(NCLDescriptor descriptor) {
		return descriptors.remove(descriptor);
	}

	public boolean hasDescriptor(NCLDescriptor descriptor) {
		return descriptors.contains(descriptor);
	}

	public boolean hasDescriptor() {
		return descriptors.size() > 0;
	}

	public Iterable<NCLDescriptor> getDescriptors() {
		return descriptors;
	}

	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<descriptorBase ";

		if (getId() != null)
			content += " id='" + getId();

		content += "'>\n";

		if (hasDescriptor())

			for (NCLDescriptor descriptor : getDescriptors())
				content += descriptor.parse(ident + 1);

		content += space + "<descriptorBase/>\n";

		return content;
	}

	public String toString() {
		return parse(0);
	}

}
