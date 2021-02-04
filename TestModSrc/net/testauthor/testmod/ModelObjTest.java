package net.testauthor.testmod;

import java.util.List;

import net.testauthor.testmod.OBJ.FaceType;

/**
 *  Generic item for test of the OBJModelRenderer functionality,
 * this model also sets up the how to correctly use it
 *
 */
public class ModelObjTest extends OBJModelBase {
	//Load obj data from file system
	List<OBJMaterial> materials = OBJLoader.getMaterials("testmodres/eye.mtl");
	
	//Load .obj from file system
	List<OBJ> model = OBJLoader.getModel("testmodres/eye.obj", materials, FaceType.F_TRI);
	
	//Model parts
	OBJModelRenderer Eye;
	
	public ModelObjTest() {
		//System.out.println(model.get(0));
		
		Eye = new OBJModelRenderer(OBJLoader.findIn(model, "Sphere_Sphere"), "testmodres/eye_texture.png");
		Eye.setRotationPoint(1f, 1f, 1f);
		setRotation(Eye, 0, 135, 0);
		
	}
	
	public void render() {
		Eye.render(1);
	}
}
