package Mainpackage;

import java.io.Serializable;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import Items.Eisenitem;
import Items.Frei;
import Items.Holzitem;
import Items.Items;
import Items.Frei;

public class Inventarfeld implements Serializable {
	int x, y, g;
	boolean aktiv;
	public Stak stak;
	public int timer;

	public Inventarfeld(int xp, int yp, int grösse) {
		x = xp;
		y = yp;
		g = grösse;
		stak = new Stak();
		stak.art = new Frei();
	}

	public void Ausgewählt() {
		int wheel = Mouse.getDWheel();
		if (wheel > 0 && Mainclass.ausgewahlt > 0) {
			Mainclass.ausgewahlt--;
		}
		if (wheel < 0 && Mainclass.ausgewahlt < 9) {
			Mainclass.ausgewahlt++;
		}
	}

	public void render() {
		GL11.glColor3f(1, 1, 1);
		Mainclass.Itemframe.displayRect(x, y, g, g);
		Mainclass.ausgewählt.displayRect(Mainclass.inventars.get(Mainclass.ausgewahlt).x,
				Mainclass.inventars.get(Mainclass.ausgewahlt).y, 40, 40);
		
		stak.art.render(x + 11, y +6);
		
		if (!stak.art.getClass().equals(Frei.class) && stak.anzahl < 100 && stak.anzahl > 9) {
			Mainclass.superschrift.displayText(x + 22, y + 25, "" + stak.anzahl, 11);
			Ausgewählt();
		}

		if (!stak.art.getClass().equals(Frei.class) && stak.anzahl < 10) {
			Mainclass.superschrift.displayText(x + 27, y + 25, "" + stak.anzahl, 11);
			Ausgewählt();
		}

		
		
		// anzeigen des Mousestak im Inventar
		if (Mainclass.Mousestak.anzahl < 10) {
			Mainclass.Mousestak.art.render(Mouse.getX() - 25, Display.getHeight() - Mouse.getY() - 25);
			Mainclass.superschrift.displayText(Mouse.getX() - 7, Display.getHeight() - Mouse.getY() - 9,
					"" + Mainclass.Mousestak.anzahl, 11);
		}
		if (Mainclass.Mousestak.anzahl >= 10) {
			Mainclass.Mousestak.art.render(Mouse.getX() - 25, Display.getHeight() - Mouse.getY() - 25);
			Mainclass.superschrift.displayText(Mouse.getX() - 12, Display.getHeight() - Mouse.getY() - 9,
					"" + Mainclass.Mousestak.anzahl, 11);
		}
	}

	public void stakputall() {
		if (Mouse.getX() >= x && Mouse.getX() <= x + 40 && Display.getHeight() - Mouse.getY() >= y
				&& Display.getHeight() - Mouse.getY() <= y + 40 && stak.art.getClass().equals(Frei.class)
				&& Mouse.isButtonDown(0) && timer == 0) {
			stak.art = Mainclass.Mousestak.art;
			stak.anzahl = Mainclass.Mousestak.anzahl;
			Mainclass.Mousestak.art = new Frei();
			Mainclass.Mousestak.anzahl = 0;
			timer = 15;
			System.out.println("put all");
		}
	}

	public void staktakeall() {

		if (Mouse.getX() >= x && Mouse.getX() <= x + 40 && Display.getHeight() - Mouse.getY() >= y
				&& Display.getHeight() - Mouse.getY() <= y + 40 && Mouse.isButtonDown(0)
				&& Mainclass.Mousestak.art.getClass().equals(Frei.class) && timer == 0) {
			Mainclass.Mousestak.art = stak.art;
			Mainclass.Mousestak.anzahl = stak.anzahl;
			stak.art = new Frei();
			stak.anzahl = 0;
			timer = 15;
			System.out.println("take all");
		}
	}
	
	public void staktakeone() {
		if (Mouse.getX() >= x && Mouse.getX() <= x + 40 && Display.getHeight() - Mouse.getY() >= y
				&& Display.getHeight() - Mouse.getY() <= y + 40 && Mouse.isButtonDown(1)
				&& !stak.art.getClass().equals(Frei.class) && timer == 0) {
			Mainclass.Mousestak.art = stak.art;
			stak.anzahl -= 1;
			Mainclass.Mousestak.anzahl += 1;
			timer = 15;
			System.out.println("take one");
		} 
	}
	public void stakswitch() {
		if(Mouse.getX() >= x && Mouse.getX() <= x + 40 && Display.getHeight() - Mouse.getY() >= y && Display.getHeight() - Mouse.getY() <= y + 40 && Mouse.isButtonDown(0)
				&& !stak.art.getClass().equals(Frei.class) && !Mainclass.Mousestak.art.getClass().equals(stak.art.getClass()) && timer == 0) {
			Stak status = stak;
			stak = Mainclass.Mousestak;
			Mainclass.Mousestak = status;
			
			timer = 15;
			System.out.println("Switch");
		}
		else if(Mouse.getX() >= x && Mouse.getX() <= x + 40 && Display.getHeight() - Mouse.getY() >= y && Display.getHeight() - Mouse.getY() <= y + 40 && Mouse.isButtonDown(0)
				&& !stak.art.getClass().equals(Frei.class) && Mainclass.Mousestak.art.getClass().equals(stak.art.getClass()) && timer == 0) {
			stak.anzahl += Mainclass.Mousestak.anzahl;
			Mainclass.Mousestak.anzahl = 0;
			System.out.println("add");
			timer = 15;
		}
	}


	public void update() {
		for (int i = 0; i < Mainclass.inventars.size(); i++) {
			if (Mainclass.inventars.get(i).stak.anzahl <= 0) {
				Mainclass.inventars.get(i).stak.art = new Frei();
			}
		}
		if(Mainclass.Mousestak.anzahl == 0) {
			Mainclass.Mousestak.art = new Frei();
		}
	}
}
