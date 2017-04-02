package Mainpackage;

import java.io.Serializable;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Items.Eisenitem;
import Items.Frei;
import Items.Holzitem;
import Items.Items;

public class Inventarfelder implements Serializable{
	static int xp;
	static int yp;
	int grösse;
	public Stak inventarstak;
	public Inventarfelder(int x, int y, int g){
		x = xp;
		y = yp;
		g = grösse;
		inventarstak = new Stak();
		inventarstak.art = new Frei();
	}
	public void inhalt(int xpos, int ypos){
		inventarstak.art.render(xpos, ypos);
	}
	public void ersetzen(Items content1){
		inventarstak.art = content1;
	}
	public static void InvMaus(){
		for (int c = 0; c < Mainclass.xinventar; c++) {
			for (int d = 0; d < Mainclass.yinventar; d++) {
				if(Mouse.getX() >= Mainclass.inventarfeld[c][d].xp && Mouse.getX() <= Mainclass.inventarfeld[c][d].xp + 100
					&& Display.getHeight() - Mouse.getY() >= Mainclass.inventarfeld[c][d].yp && Display.getHeight() - Mouse.getY() <= Mainclass.inventarfeld[c][d].yp + 100){
					Mainclass.xmausaus = c;
					Mainclass.ymausaus = d;
					System.out.println(Mainclass.inventarfeld[c][d].xp);
					System.out.println(Mouse.getX());
				}
			}
		}
	}
	public static void staknehmen() {
		if (Mouse.isButtonDown(1) && !Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art.getClass().equals(new Frei().getClass()) && Mainclass.einmal == 0 && Mainclass.verwendung == 0) {
			Mainclass.Mousestak.anzahl++;
			Mainclass.Mousestak.art = Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art;
			Mainclass.inventarfeld[Mainclass.xmausaus][Mainclass.ymausaus].inventarstak.anzahl--;
			Mainclass.einmal = 1;
			Mainclass.verwendung = 10;
			System.out.println("staknehmen");
			System.out.println(Mainclass.xmausaus);
			System.out.println(Mainclass.ymausaus);
		}

		/* stak */if (Mouse.isButtonDown(0) && !Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art.getClass().equals(new Frei().getClass())
				&& Mainclass.Mousestak.art.getClass().equals(new Frei().getClass()) && Mainclass.verwendung == 0) {
			Mainclass.Mousestak.anzahl += Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.anzahl;
			Mainclass.Mousestak.art = Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art;
			Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.anzahl = 0;
			Mainclass.verwendung = 10;
			Mainclass.einmal = 1;
		}
		/* item */if (Mouse.isButtonDown(1) && !Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art.getClass().equals(new Frei().getClass())
				&& Mainclass.Mousestak.art.getClass().equals(Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art.getClass()) && Mainclass.verwendung == 0) {
			Mainclass.Mousestak.anzahl++;
			Mainclass.Mousestak.art = Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art;
			Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.anzahl--;
			Mainclass.einmal = 1;
			Mainclass.verwendung = 10;
		}
	}

	public static void staksetzen() {

		/* stak */if (Mouse.isButtonDown(0) && Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art.getClass().equals(Mainclass.Mousestak.art.getClass()) && Mainclass.verwendung == 0) {
			Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.anzahl += Mainclass.Mousestak.anzahl;
			Mainclass.Mousestak.anzahl = 0;
			Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art = Mainclass.Mousestak.art;
			Mainclass.verwendung = 10;
		}
		/* stak */if (Mouse.isButtonDown(0) && Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art.getClass().equals(new Frei().getClass()) && Mainclass.verwendung == 0) {
			Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.anzahl += Mainclass.Mousestak.anzahl;
			Mainclass.Mousestak.anzahl = 0;
			Mainclass.inventarfeld[Mouse.getX() / 100][(Display.getHeight() - Mouse.getY()) / 100].inventarstak.art = Mainclass.Mousestak.art;
			Mainclass.verwendung = 10;
		}
	}
}