package net.testauthor.testmod;

import org.newdawn.slick.opengl.Texture;

import dangerzone.TextureMapper;
import dangerzone.World;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityLiving;

public class Banshee extends EntityLiving {

	public Banshee(World w) {
		super(w);
		setMaxHealth(100f);
		setHealth(100f);
		setDefense(1f);
		this.height = 0.1f;
		this.width = 0.000001f;
		uniquename = "TestMod:Banshee";
		enable_lookaround = false;
		movefrequency = Integer.MAX_VALUE;
		
	}
	
	public boolean isSuitableTarget(Entity e) {
		return false;
	}
	
	//Model calls back out to see what texture to use.
		public Texture getTexture() {
			if(texture == null){
				//ENTITIES MUST USE TEXTUREMAPPER.GETTEXTURE()!!!!
				texture = TextureMapper.getTexture("res/skins/"+ "vampire.png");	//this is not fast, so we keep our own pointer!
			}
			return texture;
		}
}
