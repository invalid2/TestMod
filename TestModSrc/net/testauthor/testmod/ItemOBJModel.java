package net.testauthor.testmod;

import org.lwjgl.opengl.GL11;

import dangerzone.InventoryContainer;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class ItemOBJModel extends Item {
	
	//ModelObjTest ma = null;
	//ModelBanshee ma = null;
	ModelCube ma = null;
	public ItemOBJModel(String n, String txt) {
		super(n, txt);
		
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		//texture is already auto-loaded for us
		if(ma == null){
			ma = new ModelCube();
		}
		GL11.glTranslatef(-1, -8, 1);
		GL11.glScalef(0.6f, 0.6f, 0.6f);
		if(ma != null)ma.render();
	}
}
