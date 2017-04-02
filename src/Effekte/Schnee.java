package Effekte;

import org.lwjgl.opengl.GL11;

import Mainpackage.Mainclass;

public class Schnee {
	int xp;
	public int yp;
	int grösse;
	public Schnee(int x, int y, int g){
		xp = x;
		yp = y;
		grösse = g;
	}
	public void anzeigen(){
		GL11.glColor4f(1, 1, 1, 1);
		Mainclass.schneeflocke.displayRect(xp + Mainclass.xver, yp, grösse, grösse);
	}
	public void bewegen(){
		xp ++;
		yp += 8;
	}
}
