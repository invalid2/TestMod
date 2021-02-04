package net.testauthor.testmod;

import org.newdawn.slick.opengl.Texture;

import dangerzone.ModelBase;
import dangerzone.entities.Entity;

/**
 * This class is meant to be used as a base for your model classes
 * @author InvalidName
 * @see dangerzone.ModelBase
 */
public class OBJModelBase extends ModelBase {
	
	//Initialize object part vars of your model here
	
	//Constructor
	public OBJModelBase() {
		//Define your models obj part here
	}
	
	//Standard render routine
	public void render( Entity entity, float lifetimeticker, float velocity, float headupdown, float headleftright, float headtilt, float deathfactor) {
		
	}
	
	
	public Texture getTexture(Entity ent){
		  if(ent != null)return ent.getTexture();
		  return null;
	  }
}
