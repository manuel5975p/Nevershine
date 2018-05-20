package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Mainpackage.Mainclass;

public class Apfel extends Items{

	public void render(int xpos, int ypos) {
		GL11.glColor3f(1, 1, 1);
		Mainclass.Apfel.displayRect(xpos -3, ypos +2, grösse, grösse);
	}

	public boolean setzbar() {
		return false;
	}
	public int starke() {
		return 0;
	}

	public void setzaktion() {
	}

}
