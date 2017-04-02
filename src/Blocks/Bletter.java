package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Apfel;
import Items.Items;
import Mainpackage.Mainclass;


public class Bletter extends Block implements Serializable{
	static int bletterharte = 2;
	public Bletter (){
		}
	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.Bletter.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
		
	}
	public Items drop() {
		return new Apfel();
		
	}
	public int getharte() {
		return bletterharte;
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