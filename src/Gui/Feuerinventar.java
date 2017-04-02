package Gui;

import org.lwjgl.opengl.GL11;

import Mainpackage.Mainclass;

public class Feuerinventar {

	public static void render(){
		GL11.glColor3f(1, 1, 1);
		Mainclass.Feuerhintergrund.displayRect(865, 600, 100, 100);
		Mainclass.Itemframe.displayRect(9 * 100 + 80, 7 * 100 + 80, 50, 50);
		Mainclass.Itemframe.displayRect(9 * 100 + 80, 6 * 100 + 80, 50, 50);
		Mainclass.Itemframe.displayRect(11 * 100 + 80, 6 * 100 + 80, 50, 50);
	}
	public static void tick(){

	}
}
