package Blocks;


import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Frei;
import Items.Items;
import Mainpackage.Mainclass;


public class Luft extends Block  implements Serializable{

	public Luft(){
		
	}
	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(0,0,0,0);
		Mainclass.quader(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse);
		
	}
	public Items drop() {
		return new Frei();

		
	}
	public int getharte() {
		return -1;
	}
	public boolean begehbar() {
		return true;
	}

	public boolean verwendbar() {
		return false;
	}
	public void aktion() {
	}
	
	public void sound() {
		
		
	}
	public void abbau(int Wert) {
		
	}

}