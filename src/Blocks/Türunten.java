package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Items;
import Items.Stoneitem;
import Items.Türitem;
import Mainpackage.Mainclass;


public class Türunten extends Block implements Serializable{
	static int Türharte = 2;
	
	public Türunten (){
		}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		if(Türbegehbar = false){
		Mainclass.Türezu.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
		}
		if(Türbegehbar = true){
			Mainclass.Türeoffenunten.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
		}
	}

	public Items drop() {
		return new Türitem();
		
	}

	public int getharte() {
		return Türharte;
	}

	public boolean begehbar() {
		return Türbegehbar;
	}

	public boolean verwendbar() {
		return true;
	}

	public void aktion() {
		this.Türbegehbar =! this.Türbegehbar;
	}
}