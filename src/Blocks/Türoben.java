package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Items;
import Items.Stoneitem;
import Items.Türitem;
import Mainpackage.Mainclass;


public class Türoben extends Block implements Serializable{
	int Türharte = 2;
	
	public Türoben(){
		}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		if(Türbegehbar = false){
		Mainclass.Türezu.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
		}
		if(Türbegehbar = true){
			Mainclass.Türeoffenoben.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, grösse, grösse);
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
		System.out.println("verwendet");
		Türbegehbar = false;
	}

	public void sound() {
		Mainclass.dig.playSound(Mainclass.dig.holzdig);
		
	}

	public void abbau(int Wert) {
		Türharte -= Wert;
	}
}