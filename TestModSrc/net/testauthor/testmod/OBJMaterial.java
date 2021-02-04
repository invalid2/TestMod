package net.testauthor.testmod;

/**
 * Wavefront file material, currently is essentially unused on the rendering side
 * of things, but the information is still used by OBJLoader.getmodel()
 * @author InvalidName
 * @see OBJLoader
 */
public class OBJMaterial {
	String name_material;
	String name_file;
	
	public OBJMaterial(String name_mtl, String name_file) {
		this.name_material = name_mtl;
		this.name_file = name_file;
	}
	
	public OBJMaterial() {
		this(new String(), new String());
	}
	public String geName() {
		return name_material;
	}
	
	public String getFileName() {
		return name_file;
	}
}
