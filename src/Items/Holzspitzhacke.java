package Items;

import Blocks.Block;
import Blocks.Dreck;
import Mainpackage.Mainclass;

public class Holzspitzhacke extends Items{
	public Holzspitzhacke(){
		
	}
	public void render(int xpos, int ypos) {
		Mainclass.Holzspitzhacke.displayRect(xpos, ypos, grösse, grösse);
	}

	public boolean setzbar() {
		return false;
	}

	public int starke() {
		return 8;
	}

	public void setzaktion() {
		
	}

}