package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Blocks.Dreck;
import Blocks.Stone;
import Mainpackage.Mainclass;

public class Stoneitem extends Items{

	public void render(int xpos, int ypos) {
		GL11.glColor3f(1, 1, 1);
		Mainclass.stone.displayRect(xpos, ypos, grösse, grösse);
	}

	public boolean setzbar() {
		return true;
	}

	public int starke() {
		return 0;
	}

	public void setzaktion() {
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new Stone());
		Mainclass.inventarfeld[Mainclass.xauswahl][0].inventarstak.anzahl --;
	}

}