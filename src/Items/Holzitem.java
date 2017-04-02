package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Blocks.Dreck;
import Blocks.Holz;
import Mainpackage.Mainclass;

public class Holzitem extends Items{

	public void render(int xpos, int ypos) {
		GL11.glColor3f(1, 1, 1);
		Mainclass.Holz.displayRect(xpos, ypos, grösse, grösse);
	}

	public boolean setzbar() {
		return true;
	}

	public int starke() {
		return 0;
	}

	@Override
	public void setzaktion() {
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new Holz());
	}

}