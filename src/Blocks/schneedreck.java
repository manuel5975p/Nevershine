package Blocks;


import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Dreckitem;
import Items.Items;
import Mainpackage.Mainclass;


public class schneedreck extends Block implements Serializable{
	static int grasdreckharte = 4;

	public schneedreck() {
		
	}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.schneedreck.displayRect(xpos + Mainclass.xver, ypos + Mainclass.yver, grösse ,grösse);
		
	}

	public Items drop() {
		return new Dreckitem();
		
	}
	
	public int getharte() {
		return grasdreckharte;
	}

	public boolean begehbar() {
		return false;
	}

	public boolean verwendbar() {
		return false;
	}

	public void aktion() {
		
	}

}