package net.testauthor.testmod;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class ItemCustomPixel extends Item {
	//Texture size of item
	public int ITEM_TEXTURE_SIZE = 16;
	
	//Relative position of item
	float posx;
	float posy;
	float posz;
	
	//Relative rotation of item
	float rotationx;
	float rotationy;
	float rotationz;
		
	//Relative scale of item
	float sx;
	float sy;
	float sz;
	
	//The DisplayList id
	private int compiled_quads_id;
	
	public ItemCustomPixel(String n, String txt, int txtsize) {
		super(n, txt);
		
		//Position info
		posx = 0;
		posy = 0;
		posz = 0;
		
		//Rotation
		rotationx = 0;
		rotationy = 0;
		rotationz = 0;
		
		//Scale
		sx = 1;
		sy = 1;
		sz = 1;
		
		//Texture size ex 32, 64, 8, etc
		ITEM_TEXTURE_SIZE = txtsize;
	}
	
	public ItemCustomPixel(String n, String txt, int txtsize, float offsetx, float offsety, float offsetz) {
		super(n, txt);
		
		//Position info
		posx = offsetx;
		posy = offsety;
		posz = offsetz;
		
		//Rotation
		rotationx = 0;
		rotationy = 0;
		rotationz = 0;
		
		//Scale
		sx = 1;
		sy = 1;
		sz = 1;
		
		//Texture size ex 32, 64, 8, etc
		ITEM_TEXTURE_SIZE = txtsize;
	}
	/**
	 * Big method to allow for more customization and adjusting wihtout having to create another class
	 * @param n
	 * @param txt
	 * @param txtsize
	 * @param offsetx
	 * @param offsety
	 * @param offsetz
	 * @param anglex
	 * @param angley
	 * @param anglez
	 * @param scalex
	 * @param scaley
	 * @param scalez
	 */
	public ItemCustomPixel(String n, String txt, int txtsize, float offsetx, float offsety, float offsetz, float anglex, float angley, float anglez, float scalex, float scaley, float scalez) {
		super(n, txt);
		
		//Position info
		posx = offsetx;
		posy = offsety;
		posz = offsetz;
		
		//Rotation
		rotationx = anglex;
		rotationy = angley;
		rotationz = anglez;
		
		//Scale
		sx = scalex;
		sy = scaley;
		sz = scalez;
		
		//Texture size ex 32, 64, 8, etc
		ITEM_TEXTURE_SIZE = txtsize;
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		//texture is already auto-loaded for us
		
		//if we have no assigne id, compile model
		if(compiled_quads_id == 0) {
			compile_item();
		}
		if(compiled_quads_id != 0) {
			//Change position, rotation and scale based on the constructor info
			GL11.glTranslatef(posx, posy, posz);
			GL11.glRotatef(rotationx, 1f, 0, 0);
			GL11.glRotatef(rotationy, 0, 1f, 0);
			GL11.glRotatef(rotationz, 0, 0, 1f);
			GL11.glScalef(sx, sy, sz);
			GL11.glCallList(compiled_quads_id);
			GL14.glSecondaryColor3f(0.0f, 0.0f, 0.0f);
		}
		
		
	}
	
	private void compile_item(){

		int curval;
		int i, j;
		byte[] data;
		Texture t;

		t = getTexture();
		if(t == null)return;
		data = t.getTextureData();
		compiled_quads_id = DangerZone.wr.getNextRenderID();

		GL11.glNewList(compiled_quads_id, GL11.GL_COMPILE);
		GL11.glBegin(GL11.GL_QUADS);
		
		if(!flopped){

		for(i=0;i<ITEM_TEXTURE_SIZE;i++){
			for(j=0;j<ITEM_TEXTURE_SIZE;j++){
				curval = gimmedataat(data, i, j); //Get a pixel
				if(curval >= 0){					
					/*
					 * The -8, -8 moves the center to the center of the image.
					 */
					
					GL14.glSecondaryColor3f(0.02f, 0.02f, 0.02f);
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+1, 0); // Top Right
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+1, 0); // Top Left
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+0, 0); // Bottom Left
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+0, 0); // Bottom Right

					
					GL14.glSecondaryColor3f(0.02f, 0.02f, 0.02f);
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+1, 1); // Top Right
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+1, 1); // Top Left
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+0, 1); // Bottom Left
					GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
					GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+0, 1); // Bottom Right
					
					
					if(gimmedataat(data, i-1, j) < 0){
						GL14.glSecondaryColor3f(0.04f, 0.04f, 0.04f);
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+0, 1); // Top Right
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+0, 1); // Top Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+0, 0); // Bottom Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+0, 0); // Bottom Right
					}

					
					if(gimmedataat(data, i+1, j) < 0){
						GL14.glSecondaryColor3f(0.04f, 0.04f, 0.04f);
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+1, 1); // Top Right
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+1, 1); // Top Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+1, 0); // Bottom Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+1, 0); // Bottom Right
					}

					
					if(gimmedataat(data, i, j-1) < 0){
						GL14.glSecondaryColor3f(0f, 0f, 0f);
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+1, 0); // Top Right
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+1, 1); // Top Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+0, 1); // Bottom Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+0, -(ITEM_TEXTURE_SIZE/2)+i+0, 0); // Bottom Right
					}

					
					if(gimmedataat(data, i, j+1) < 0){
						GL14.glSecondaryColor3f(0f, 0f, 0f);
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+1, 1); // Top Right
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+1, 0); // Top Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+0, 0); // Bottom Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f(-(ITEM_TEXTURE_SIZE/2)+j+1, -(ITEM_TEXTURE_SIZE/2)+i+0, 1); // Bottom Right
					}
				}
			}
		}
		
		}else{
			
			for(i=0;i<ITEM_TEXTURE_SIZE;i++){
				for(j=0;j<ITEM_TEXTURE_SIZE;j++){
					curval = gimmedataat(data, i, j); //Get a pixel
					if(curval >= 0){					
						/*
						 * The -8, -8 moves the center to the center of the image.
						 */
						
						GL14.glSecondaryColor3f(0.02f, 0.02f, 0.02f);
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+1, 0); // Top Right
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+1, 0); // Top Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, 16-i+0, 0); // Bottom Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+0, 0); // Bottom Right

						
						GL14.glSecondaryColor3f(0.02f, 0.02f, 0.02f);
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+1, 1); // Top Right
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+1, 1); // Top Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+0, 1); // Bottom Left
						GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
						GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+0, 1); // Bottom Right
						
						
						if(gimmedataat(data, i+1, j) < 0){
							GL14.glSecondaryColor3f(0.04f, 0.04f, 0.04f);
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+0, 1); // Top Right
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+0, 1); // Top Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+0, 0); // Bottom Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+0, 0); // Bottom Right
						}

						
						if(gimmedataat(data, i-1, j) < 0){
							GL14.glSecondaryColor3f(0.04f, 0.04f, 0.04f);
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+1, 1); // Top Right
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+1, 1); // Top Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+1, 0); // Bottom Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+1, 0); // Bottom Right
						}

						
						if(gimmedataat(data, i, j+1) < 0){
							GL14.glSecondaryColor3f(0f, 0f, 0f);
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+1, 0); // Top Right
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+1, 1); // Top Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+0, 1); // Bottom Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+0, (ITEM_TEXTURE_SIZE/2)-i+0, 0); // Bottom Right
						}

						
						if(gimmedataat(data, i, j-1) < 0){
							GL14.glSecondaryColor3f(0f, 0f, 0f);
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+1, 1); // Top Right
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+1, 0); // Top Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+0, 0); // Bottom Left
							GL11.glTexCoord2f((float)j/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE), (float)i/(1f*ITEM_TEXTURE_SIZE)+(1f/ITEM_TEXTURE_SIZE));
							GL11.glVertex3f((ITEM_TEXTURE_SIZE/2)-j+1, (ITEM_TEXTURE_SIZE/2)-i+0, 1); // Bottom Right
						}
					}
				}
			}
			
		}
		
		
		GL11.glEnd(); // Done Drawing The Quads
		GL11.glEndList();
	}
	
	private int gimmedataat(byte[] data, int x, int y){
		if(x < 0 || x >= ITEM_TEXTURE_SIZE)return -1;
		if(y < 0 || y >= ITEM_TEXTURE_SIZE)return -1;
		//System.out.printf("data %d at %d, %d is: %d, %d, %d, %d\n", data.length, x, y, data[((x*16)+y)*4], data[((x*16)+y)*4 + 1], data[((x*16)+y)*4 + 2], data[((x*16)+y)*4 + 3] );		
		if(data[((x*ITEM_TEXTURE_SIZE)+y)*4 + 3] >= 0 && data[((x*ITEM_TEXTURE_SIZE)+y)*4 + 3] < 100)return -1; //check alpha first! Arbitrary alpha min. Should really always be 0 or -1 (255) for solid colors.		
		return (data[((x*ITEM_TEXTURE_SIZE)+y)*4] + data[((x*ITEM_TEXTURE_SIZE)+y)*4 + 1] + data[((x*ITEM_TEXTURE_SIZE)+y)*4 + 2])&0xffffff;
	}
}
