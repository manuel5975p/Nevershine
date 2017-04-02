package Items;

import Blocks.Block;
import Mainpackage.Mainclass;

public class Eisenspitzhacke extends Items{
	public Eisenspitzhacke(){
		
	}
	public void render(int xpos, int ypos) {
		Mainclass.Eisenspitzhacke.displayRect(xpos, ypos, grösse, grösse);
	}

	public boolean setzbar() {
		return false;
	}
	
	public int starke() {
		return 4;
	}
	@Override
	public void setzaktion() {
	
	}

}
