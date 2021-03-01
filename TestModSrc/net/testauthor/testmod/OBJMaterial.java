package net.testauthor.testmod;

/**
 * Wavefront file material, currently is essentially unused on the rendering side
 * of things, but the information is still used by OBJLoader.getmodel()
 * @author InvalidName
 * @see OBJLoader
 */
public class OBJMaterial {
	String nameMaterial;
	String nameFile;
	
	public OBJMaterial(String nameMaterial, String nameFile) {
		this.nameMaterial = nameMaterial;
		this.nameFile = nameFile;
	}
	
	public OBJMaterial() {
		this(new String(), new String());
	}
	
	public String getName() {
		return nameMaterial;
	}
	
	public String getFileName() {
		return nameFile;
	}
	
	public void setNameMaterial(String nameMaterial) {
		this.nameMaterial = nameMaterial;
	}
	
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
}
