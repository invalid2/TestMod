package net.testauthor.testmod;

import org.lwjgl.opengl.GL11;

import dangerzone.InventoryContainer;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.items.ItemSword;

public class EpicSword extends ItemSword {
	
	//model of the epic sword
	ModelEpicSword ma;
	
	//Constructor
	public EpicSword(String n, String txt, int a, int b) {
		super(n, txt, a, b);
		
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		//texture is already auto-loaded for us
		if(ma == null){
			ma = new ModelEpicSword();
		}
		//Rotate it so it is positioned correctly on our hand!
		GL11.glRotatef(15, 0, 1, 0);
		GL11.glRotatef(-135, 0, 0, 1);
		GL11.glRotatef(22.5f, 1, 0, 0);
		GL11.glTranslatef(0, -12, 3);
		if(ma != null)ma.render();
	}

}
