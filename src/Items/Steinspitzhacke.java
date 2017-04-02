package Items;

import Blocks.Block;
import Mainpackage.Mainclass;

public class Steinspitzhacke extends Items{
	public Steinspitzhacke(){
		
	}
	public void render(int xpos, int ypos) {
		Mainclass.Steinspitzhacke.displayRect(xpos, ypos, grösse, grösse);
	}

	public boolean setzbar() {
		return false;
	}

	public int starke() {
		return 2;
	}
	
	public void setzaktion() {
	}

}