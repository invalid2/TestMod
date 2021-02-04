package net.testauthor.testmod;

import java.util.List;

import net.testauthor.testmod.OBJ.FaceType;
 /**
  * Also a model for an item, a sword in this case
  * @author InvalidName
  */
public class ModelEpicSword extends OBJModelBase {
	
	//Load the materials for the model
	List<OBJMaterial> materials = OBJLoader.getMaterials("testmodres/sword.mtl");
	
	//Load the model
	List<OBJ> model = OBJLoader.getModel("testmodres/sword.obj", materials, FaceType.F_TRI);
	
	OBJModelRenderer sword;
	
	public ModelEpicSword() {
		sword = new OBJModelRenderer(model.get(0), "testmodres/sword.png");
		sword.setRotationPoint(0f, 24f, 0f);
		//sword.setOffset(-0.5f, 0, -0.5f);
		setRotation(sword, 0, 0, 0);
	}
	
	public void render() {
		sword.render(1);
	}
}
