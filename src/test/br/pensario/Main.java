package test.br.pensario;

import br.pensario.NCLBody;
import br.pensario.NCLDoc;
import br.pensario.NCLHead;
import br.pensario.NCLValues.NCLColor;
import br.pensario.NCLValues.NCLNamespace;
import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.region.NCLRegion;
import br.pensario.region.NCLRegionBase;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		NCLRegionBase region_base = new NCLRegionBase("rgBase");
		NCLDescriptorBase descriptor_base = new NCLDescriptorBase();
		NCLConnectorBase connector_base = new NCLConnectorBase();

		try {

			NCLDescriptor descriptor1 = new NCLDescriptor("descriptor01");
			
			descriptor1.setExplicitDur("valexpDur");
			descriptor1.setFocusBorderColor(NCLColor.gray);
			descriptor1.setFocusSelSrc("valFocusSelSrc");
			
			
			
			NCLDescriptor descriptor2 = new NCLDescriptor("descriptor02");
			
			descriptor2.setSelBorderColor(NCLColor.green);
			descriptor2.setFocusBorderTransparency(40);			
			descriptor2.setMoveUp(descriptor1);			
					
			descriptor1.setMoveDown(descriptor2);
			
			descriptor_base.addDescriptor(descriptor1);
			descriptor_base.addDescriptor(descriptor2);
			
			NCLRegion region1 = new NCLRegion("regiao01");

			region1.setWidth(45, true);
			region1.setHeight(150, false);
			region1.setBottom(56, true);

			NCLRegion region2 = new NCLRegion("regiao02");

			region2.setTitle("Título de teste");

			region1.addRegion(region2);

			region_base.addRegion(region1);

			NCLHead head = new NCLHead(region_base, descriptor_base,
					connector_base);

			NCLBody body = new NCLBody();

			NCLDoc document;

			document = new NCLDoc("meudocumento", NCLNamespace.EDTV, head, body);
			System.out.println(document);
		
		} catch (Exception e) { //
			e.printStackTrace();
		}

	}

}
