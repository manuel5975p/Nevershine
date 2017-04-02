package Effekte;

import org.lwjgl.opengl.GL11;

import Mainpackage.Mainclass;

public class Feuerpartikel {
	int xp, yp;
	float farbe =1;
	public float grösse = 10;
	public Feuerpartikel(int x, int y){
		xp = x;
		yp = y;
	}
	public void anzeigen(){
		GL11.glColor4f(farbe, 0f, 0.1f, 0.1f);
		Mainclass.ellipse(xp + Mainclass.xver, yp + Mainclass.yver, (int)grösse , (int)grösse, 50);
	}
	public void beweg(){
		yp -= 2;
		grösse -= 0.3;
	}
	public void tick(){
		farbe -= 0.06;
	}
}
