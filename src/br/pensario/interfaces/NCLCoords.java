/*
 * Assim, ele permite a definição de âncoras de conteúdo representando
 * porções espaciais, através de atributo coords (como em XHTML);
 * 
 * The position and shape of an image map element on the screen.
 * 
 * Numerical coordinates in length
 * rectangle: left-x,top-y,right-x,bottom-y
 * circle: center-x,center-y,radius
 * polygon: x1,y1,x2,y2,...,xN,yN
 */
package br.pensario.interfaces;

public class NCLCoords {

	private int[] coords;
	
	
	public NCLCoords(int left_x, int top_y, int right_x, int bottom_y) throws Exception {
		if (left_x >= 0 && top_y >= 0 && right_x >= 0 && bottom_y >= 0){
			coords = new int[4];
			coords[0] = left_x;
			coords[1] = top_y;
			coords[2] = right_x;
			coords[3] = bottom_y;
		}
		else{
			Exception ex = new Exception("Invalid coordinates");
			throw ex;
		}
	}
	
	public NCLCoords(int center_x, int center_y, int radius) throws Exception {
		if (center_x >= 0 && center_y >= 0 && radius >= 0){
			coords = new int[3];
			coords[0] = center_x;
			coords[1] = center_y;
			coords[2] = radius;
		}
		else{
			Exception ex = new Exception("Invalid coordinates");
			throw ex;
		}
	}
	
	public NCLCoords(int[] coords) throws Exception {
		for (int i = 0; i < coords.length; i++){
			if (coords[i] < 0){
				Exception ex = new Exception("Invalid coordinates");
				throw ex;
			}
		}
		this.coords = coords;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < coords.length; i++){
			result += coords[i];
			if (i < coords.length - 1)
				result += ",";
		}
		return result;
	}
}
