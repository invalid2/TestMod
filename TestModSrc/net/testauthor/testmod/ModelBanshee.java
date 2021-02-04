package net.testauthor.testmod;

import java.util.List;

import dangerzone.entities.Entity;
import net.testauthor.testmod.OBJ.FaceType;

/**
 * Banshee model from Lycanite Mobs, Lycanite generously allows people to use his models,
 * this model is specifically here due to having both quads and tries
 * as such a custom solution needed to be created from my part, also has multiple object groups!
 * @author InvalidName
 * @author Lycanite (creator of the .obj model)
 */
public class ModelBanshee extends OBJModelBase {
	
	//Load the materials for the model
	List<OBJMaterial> materials = OBJLoader.getMaterials("testmodres/banshee.mtl");
	
	//Load the model
	List<OBJ> model = OBJLoader.getModel("testmodres/banshee.obj", materials, FaceType.F_TRI);
	
	OBJModelRenderer HandRight;
	OBJModelRenderer EyeRight;
	OBJModelRenderer FingerRight02;
	OBJModelRenderer FingerRight03;
	OBJModelRenderer FingerRight01;
	OBJModelRenderer FingerRight04;
	OBJModelRenderer FingerRight05;
	OBJModelRenderer HandLeft;
	OBJModelRenderer FingerLeft05;
	OBJModelRenderer FingerLeft04;
	OBJModelRenderer FingerLeft01;
	OBJModelRenderer FingerLeft03;
	OBJModelRenderer FingerLeft02;
	OBJModelRenderer EyeLeft;
	OBJModelRenderer Body;
	OBJModelRenderer Head;
	OBJModelRenderer Hair02;
	OBJModelRenderer Hair01;
	
	public ModelBanshee() {
		HandRight = new OBJModelRenderer(OBJLoader.findIn(model, "HandRight"), "testmodres/banshee.png");
		HandRight.setOffset(0, 0, 0);
		HandRight.setRotationPoint(0, 0, 0);
		setRotation(HandRight, 0, 0, 0);
		EyeRight = new OBJModelRenderer(OBJLoader.findIn(model, "EyeRight"), "testmodres/banshee_eye.png");
		EyeRight.setOffset(0, 0, 0);
		EyeRight.setRotationPoint(0, 0, 0);
		setRotation(EyeRight, 0, 0, 0);
		FingerRight02 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerRight02"), "testmodres/banshee.png");
		FingerRight02.setOffset(0, 0, 0);
		FingerRight02.setRotationPoint(0, 0, 0);
		setRotation(FingerRight02, 0, 0, 0);
		FingerRight03 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerRight03"), "testmodres/banshee.png");
		FingerRight03.setOffset(0, 0, 0);
		FingerRight03.setRotationPoint(0, 0, 0);
		setRotation(FingerRight03, 0, 0, 0);
		FingerRight01 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerRight01"), "testmodres/banshee.png");
		FingerRight01.setOffset(0, 0, 0);
		FingerRight01.setRotationPoint(0, 0, 0);
		setRotation(FingerRight01, 0, 0, 0);
		FingerRight04 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerRight04"), "testmodres/banshee.png");
		FingerRight04.setOffset(0, 0, 0);
		FingerRight04.setRotationPoint(0, 0, 0);
		setRotation(FingerRight04, 0, 0, 0);
		FingerRight05 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerRight05"), "testmodres/banshee.png");
		FingerRight05.setOffset(0, 0, 0);
		FingerRight05.setRotationPoint(0, 0, 0);
		setRotation(FingerRight05, 0, 0, 0);
		HandLeft = new OBJModelRenderer(OBJLoader.findIn(model, "HandLeft"), "testmodres/banshee.png");
		HandLeft.setOffset(0, 0, 0);
		HandLeft.setRotationPoint(0, 0, 0);
		setRotation(HandLeft, 0, 0, 0);
		FingerLeft05 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerLeft05"), "testmodres/banshee.png");
		FingerLeft05.setOffset(0, 0, 0);
		FingerLeft05.setRotationPoint(0, 0, 0);
		setRotation(FingerLeft05, 0, 0, 0);
		FingerLeft04 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerLeft04"), "testmodres/banshee.png");
		FingerLeft04.setOffset(0, 0, 0);
		FingerLeft04.setRotationPoint(0, 0, 0);
		setRotation(FingerLeft04, 0, 0, 0);
		FingerLeft01 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerLeft01"), "testmodres/banshee.png");
		FingerLeft01.setOffset(0, 0, 0);
		FingerLeft01.setRotationPoint(0, 0, 0);
		setRotation(FingerLeft01, 0, 0, 0);
		FingerLeft03 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerLeft03"), "testmodres/banshee.png");
		FingerLeft03.setOffset(0, 0, 0);
		FingerLeft03.setRotationPoint(0, 0, 0);
		setRotation(FingerLeft03, 0, 0, 0);
		FingerLeft02 = new OBJModelRenderer(OBJLoader.findIn(model, "FingerLeft02"), "testmodres/banshee.png");
		FingerLeft02.setOffset(0, 0, 0);
		FingerLeft02.setRotationPoint(0, 0, 0);
		setRotation(FingerLeft02, 0, 0, 0);
		EyeLeft = new OBJModelRenderer(OBJLoader.findIn(model, "EyeLeft"), "testmodres/banshee_eye.png");
		EyeLeft.setOffset(0, 0, 0);
		EyeLeft.setRotationPoint(0, 0, 0);
		setRotation(EyeLeft, 0, 0, 0);
		Body = new OBJModelRenderer(OBJLoader.findIn(model, "Body"), "testmodres/banshee.png");
		Body.setOffset(0, 0, 0);
		Body.setRotationPoint(0, 0, 0);
		setRotation(Body, 0, 0, 0);
		Head = new OBJModelRenderer(OBJLoader.findIn(model, "Head"), "testmodres/banshee.png");
		Head.setOffset(0, 0, 0);
		Head.setRotationPoint(0, 0, 0);
		setRotation(Head, 0, 0, 0);
		Hair02 = new OBJModelRenderer(OBJLoader.findIn(model, "Hair02"), "testmodres/banshee_hair.png");
		Hair02.setOffset(0, 0, 0);
		Hair02.setRotationPoint(0, 0, 0);
		setRotation(Hair02, 0, 0, 0);
		Hair01 = new OBJModelRenderer(OBJLoader.findIn(model, "Hair01"), "testmodres/banshee_hair.png");
		Hair01.setOffset(0, 0, 0);
		Hair01.setRotationPoint(0, 0, 0);
		setRotation(Hair01, 0, 0, 0);
		
	}
	
	/**
	 * Default entity render method
	 * @see dangerzoner.ModelRenderer
	 */
	public void render( Entity entity, float lifetimeticker, float velocity, float headupdown, float headleftright, float headtilt, float deathfactor) {
		
		HandRight.render(deathfactor);
		EyeRight.render(deathfactor);
		FingerRight02.render(deathfactor);
		FingerRight03.render(deathfactor);
		FingerRight01.render(deathfactor);
		FingerRight04.render(deathfactor);
		FingerRight05.render(deathfactor);
		HandLeft.render(deathfactor);
		FingerLeft05.render(deathfactor);
		FingerLeft04.render(deathfactor);
		FingerLeft01.render(deathfactor);
		FingerLeft03.render(deathfactor);
		FingerLeft02.render(deathfactor);
		EyeLeft.render(deathfactor);
		Body.render(deathfactor);
		Head.render(deathfactor);
		Hair02.render(deathfactor);
		Hair01.render(deathfactor);
	}

	/**
	 * This is here for rendering as an item instead of an entity
	 */
	public void render() {
		
		HandRight.render(1);
		EyeRight.render(1);
		FingerRight02.render(1);
		FingerRight03.render(1);
		FingerRight01.render(1);
		FingerRight04.render(1);
		FingerRight05.render(1);
		HandLeft.render(1);
		FingerLeft05.render(1);
		FingerLeft04.render(1);
		FingerLeft01.render(1);
		FingerLeft03.render(1);
		FingerLeft02.render(1);
		EyeLeft.render(1);
		Body.render(1);
		Head.render(1);
		Hair02.render(1);
		Hair01.render(1);
	}
}
