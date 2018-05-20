package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Dreckitem;
import Items.Frei;
import Items.Items;
import Mainpackage.Mainclass;

public class Schneedecke extends Block implements Serializable{
	int schneeharte = 10;
	public Schneedecke() {
		
	}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.schneedecke.displayRect(xpos + Mainclass.xver, ypos + Mainclass.yver, grösse ,grösse);
	}

	public Items drop() {
		return new Frei();
		
	}

	public int getharte() {
		schneeharte = 10;
		return schneeharte;
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
		schneeharte -= Wert;
		
	}

}