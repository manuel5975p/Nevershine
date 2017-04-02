package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Items;
import Items.Stoneitem;
import Mainpackage.Mainclass;


public class Stone extends Block implements Serializable{
	static int stoneharte = 6;
	public Stone (){
		}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		Mainclass.stone.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
		
	}

	public Items drop() {
		return new Stoneitem();
		
	}

	public int getharte() {
		return stoneharte;
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