package test.br.pensario;

import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.region.NCLRegion;
import br.pensario.region.NCLRegionBase;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		NCLRegionBase region_base = new NCLRegionBase();
		NCLDescriptorBase descriptor_base = new NCLDescriptorBase();
		NCLConnectorBase connector_base = new NCLConnectorBase();

		try {
						
			
			NCLRegion region1 = new NCLRegion("regiao01");
			
			region1.setWidth(45, true);
			region1.setHeight(150, false);
			region1.setBottom(56, true);
						
			NCLRegion region2 = new NCLRegion("regiao02");
			
			region2.setTitle("Título de teste");
			
			region1.addRegion(region2);
			
			System.out.print(region1);
			System.out.print(region2);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*NCLRegion region2 = new NCLRegion("regiao02");

		region_base.addRegion(region1);
		region_base.addRegion(region2);*/

		

		/*
		 * NCLHead head = new NCLHead(region_base, descriptor_base,
		 * connector_base);
		 * 
		 * NCLBody body = new NCLBody();
		 * 
		 * NCLDoc document;
		 * 
		 * try { document = new NCLDoc("meudocumento", NCLNamespace.TESTE1,
		 * head, body); System.out.println(document); } catch (Exception e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

}
