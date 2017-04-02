package Blocks;

import org.lwjgl.opengl.GL11;

import Effekte.Feuerpartikel;
import Items.Feuerstelleitem;
import Items.Items;
import Mainpackage.Mainclass;

public class Feuerstelle extends Block{
	int Feuerharte = 2;
	public Feuerstelle(){
		
	}

	public void anzeigen(int xpos, int ypos) {
		int randome = (int) (Math.random() *5);
		if(randome == 1){
		Mainclass.feuerpartikels.add( new Feuerpartikel(xpos + 9, ypos + 15));
		}
		if(randome == 2){
			Mainclass.feuerpartikels.add( new Feuerpartikel(xpos + 11, ypos + 15));
			}
		if(randome == 3){
			Mainclass.feuerpartikels.add( new Feuerpartikel(xpos + 13, ypos + 15));
			}
		if(randome == 3){
			Mainclass.feuerpartikels.add( new Feuerpartikel(xpos + 15, ypos + 15));
			}
		if(randome == 3){
			Mainclass.feuerpartikels.add( new Feuerpartikel(xpos + 17, ypos + 15));
			}
		GL11.glColor4f(1, 1, 1, 1);
		Mainclass.Feuerstelle.displayRect(xpos + Mainclass.xver, ypos + Mainclass.yver, grösse, grösse);
	}
	public Items drop() {
		return new Feuerstelleitem();
	}

	public int getharte() {
		return Feuerharte;
	}

	public boolean begehbar() {
		return true;
	}

	public boolean verwendbar() {
		return true;
	}

	public void aktion() {
		Mainclass.Feuerinventar = true;
		Mainclass.inventar = 1;
	}
}
