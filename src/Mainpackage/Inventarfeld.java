package Mainpackage;

import java.io.Serializable;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Items.Eisenitem;
import Items.Frei;
import Items.Holzitem;
import Items.Items;

public class Inventarfeld implements Serializable{
	int x, y, g;
	Stak stak;
	Inventarfeld(int xp, int yp, int grösse,Stak c){
		x = xp;
		y = yp;
		g = grösse;
		stak = c;
		}
	}

