package Gui;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Mainpackage.Mainclass;

public class Mainmenu {
	public static void Mainmenu(){
	glColor3f(1,1,1);
	Mainclass.sm.loopSound(Mainclass.sm.Menumusic);
	Mainclass.Menubackground.displayRect(0, 0, 1920, 1080);
	Mainclass.Singleplayer.render();
	Mainclass.Singleplayer.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
	Mainclass.exit.render();
	Mainclass.exit.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
	glColor3f(0.2f,1,0.2f);
	Mainclass.superschrift.displayText(650, 100, "Nevershine", 100);
	}
	public static void Menu2(){
		glColor3f(1,1,1);
		Mainclass.sm.loopSound(Mainclass.sm.Menumusic);
		Mainclass.Menubackground.displayRect(0, 0, 1920, 1080);
		Mainclass.load.render();
		Mainclass.load.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
		Mainclass.restart.render();
		Mainclass.restart.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
		glColor3f(0.2f,1,0.2f);
		Mainclass.superschrift.displayText(650, 100, "Nevershine", 100);
	}
	public static void IngameMenu(){
		glColor3f(1,1,1);
		Mainclass.Menubackground.displayRect(0, 0, 1920, 1080);
		Mainclass.resum.render();
		Mainclass.resum.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
		Mainclass.mainmenu.render();
		Mainclass.mainmenu.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
		Mainclass.speichern.render();
		Mainclass.speichern.tick(Mouse.getX(), Display.getHeight() - Mouse.getY());
		glColor3f(0.2f,1,0.2f);
		Mainclass.superschrift.displayText(650, 100, "Nevershine", 100);
	}
}
