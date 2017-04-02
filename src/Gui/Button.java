package Gui;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.lwjgl.input.*;

import Mainpackage.Mainclass;
import static org.lwjgl.opengl.GL11.*;
/**
 * Font class
 * 
 * @author Manuel Winkler
 */
public abstract class Button implements Serializable{

	private static final long serialVersionUID = 1L;
	String text;
	public int x,y,xg,yg; 
	float textSize = 1;
	public boolean mouseOver;
	public boolean active, pactive;
	public static void loadFont() throws IOException{
	}
	public Button(int xp,int yp,int xs,int ys,String t) {
		text = t;
		x = xp;
		y = yp;
		xg = xs;
		yg = ys;
	}
	public Button(int xp,int yp,int xs,int ys,String t,float ts) {
		text = t;
		x = xp;
		y = yp;
		xg = xs;
		yg = ys;
		textSize = ts;
	}
	public void tick(int mausx,int mausy){
		mouseOver = false;
		active = false;
		//System.out.println(Mouse.isButtonDown(0));
		//System.out.println(x + xg);
		if(mausx >= x && mausx <= x + xg){
			if(mausy >= y && mausy <= y + yg){
				mouseOver = true;
				//System.out.println(mouseOver);
				if(Mouse.isButtonDown(0)){
					active = true;
					if(!pactive){
						action();
					}
				}
			}
		}
		if(pactive && !active){
			onRelease();
		}
		pactive = active;
		
	}
	public void render(){
		//Font.drawChar('f', 100, 100, 30);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		if(active){
			glBegin(GL_QUADS);
			glColor3f(0.2f,1,0.2f);
			glVertex2d(x,y);
			glVertex2d(x + xg,y);
			glColor3f(0.8f,1,0.2f);
			glVertex2d(x + xg,y + yg);
			glVertex2d(x,y + yg);
			glEnd();
		}
		else if(mouseOver){
			glBegin(GL_QUADS);
			glColor3f(0.8f,1,0.2f);
			glVertex2d(x,y);
			glVertex2d(x + xg,y);
			glColor3f(0.2f,1,0.2f);
			glVertex2d(x + xg,y + yg);
			glVertex2d(x,y + yg);
			glEnd();
		}
		else{
			glBegin(GL_QUADS);
			glColor3f(0.6f,0.8f,0.6f);
			glVertex2d(x,y);
			glVertex2d(x + xg,y);
			glColor3f(0.0f,0.6f,0.0f);
			glVertex2d(x + xg,y + yg);
			glVertex2d(x,y + yg);
			glEnd();
		}
		//glDisable(GL_BLEND);
		//System.out.println("render");
		glColor3f(1,0,0);
		Mainclass.superschrift.displayText(x, y + yg / 3, text, yg / 4f * textSize);
		glColor3f(1,1,1);
	}
	public abstract void action();
	public void displayInfoText(){
		
	}
	public void onRelease(){
		
	}
}
