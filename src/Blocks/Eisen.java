package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Eisenerzitem;
import Items.Items;
import Mainpackage.Mainclass;
public class Eisen extends Block implements Serializable{
	int eisenharte = 8;

	public Eisen() {
	}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.Eisenerz.displayRect(xpos + Mainclass.xver, ypos + Mainclass.yver, grösse ,grösse);
		
	}
	public Items drop() {
		return new Eisenerzitem();
		
	}

	public int getharte() {
		return eisenharte;
	}

	public boolean begehbar() {
		return false;
	}

	public boolean verwendbar() {
		return false;
	}

	public void aktion() {
	}
	
	public void sound() {
		Mainclass.dig.playSound(Mainclass.dig.stonedig);
		
	}

	public void abbau(int Wert) {
		eisenharte -= Wert;
		
	}

}