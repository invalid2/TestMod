package net.testauthor.testmod;

import java.util.List;

import net.testauthor.testmod.OBJ.FaceType;

public class ModelCube extends OBJModelBase {
	
	//Load obj data from file system
	List<OBJMaterial> materials = OBJLoader.getMaterials("testmodres/banshee_eye.mtl");
	
	//Load .obj from file system
	List<OBJ> model = OBJLoader.getModel("testmodres/banshee_eye.obj", materials, FaceType.F_TRI);
	
	//Model parts
	OBJModelRenderer Cube;
	
	public ModelCube() {
		//System.out.println(model.get(0));
		
		Cube = new OBJModelRenderer(OBJLoader.findIn(model, "EyeLeft"), "testmodres/banshee_eye.png");
		Cube.setRotationPoint(1f, 1f, 1f);
		setRotation(Cube, 0, 135, 0);
		
	}
	
	public void render() {
		Cube.render(1);
	}

}
