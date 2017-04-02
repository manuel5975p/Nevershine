package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Blocks.Dreck;
import Mainpackage.Mainclass;

public class Dreckitem extends Items{
	
	public void render(int xpos, int ypos) {
		GL11.glColor3f(1, 1, 1);
		Mainclass.dreck.displayRect(xpos, ypos, grösse, grösse);
	}

	public boolean setzbar() {
		return true;
	}

	public Block setzen() {
		return new Dreck();
	}

	public int starke() {
		return 0;
	}

	public void setzaktion() {
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new Dreck());
	}

}