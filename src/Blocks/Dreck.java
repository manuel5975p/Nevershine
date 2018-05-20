package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Dreckitem;
import Items.Items;
import Mainpackage.Mainclass;

public class Dreck extends Block implements Serializable{
	int dreckharte = 4;
	
	public Dreck() {
		
	}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.dreck.displayRect(xpos + Mainclass.xver, ypos + Mainclass.yver, grösse ,grösse);
	}

	public Items drop() {
		return new Dreckitem();
		
	}

	public int getharte() {
		return dreckharte;
	}
	public boolean begehbar(){
		return false;
	}

	public boolean verwendbar() {
		return false;
	}

	public void aktion() {
		
	}

	public void sound() {
		Mainclass.dig.playSound(Mainclass.dig.dreckdig);
		
	}

	public void abbau(int Wert) {
		dreckharte -= Wert;
	}
}