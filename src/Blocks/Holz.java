package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Holzitem;
import Items.Items;
import Mainpackage.Mainclass;


public class Holz extends Block implements Serializable{
	static int holzharte = 4;
	public Holz (){
		}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.Holz.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
		
	}

	public Items drop() {
		return new Holzitem();
		
	}

	public int getharte() {
		return holzharte;
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
