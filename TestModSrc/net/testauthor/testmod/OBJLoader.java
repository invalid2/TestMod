package net.testauthor.testmod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import net.testauthor.testmod.OBJ.Face;
import net.testauthor.testmod.OBJ.FaceType;

/**
 * Responsible for loading both models and materials from the filesystem,
 * also has quality-of-life functions like findIn
 * @author InvalidName
 *
 */
public class OBJLoader {
	
	/**
	 * Loads wavefront file from the file system with the use of a scanner
	 * @param path File path of model
	 * @param materials OBJMaterial list to be assigned for every object group
	 * @param faceType Type of face the model has, either quads(F_QUAD) or tris(F_TRI)
	 * @param flipTxtCoordsY Flip vertex texture coords on the y axis, necessary for compatibility with certain blender models
	 * @return Returns a list of OBJ aka object groups of the model
	 */
	public static List<OBJ> getModel(String path, List<OBJMaterial> materials, FaceType faceType, boolean flipTxtCoordsY) {
		
		int countVertices = 0;
		int countTextureCoords = 0;
		int countNormals = 0;
		int countFaces = 0;
		
		try {
			//File scanner
			Scanner scanner = new Scanner(new File(path));
			
			//Model, list of .obj object groups
			List<OBJ> model = new ArrayList<>();
			
			//Default names for certain parts of the .obj
			//String name_polygon_group = "default";
			boolean hasData = false;
			OBJ current = new OBJ();
			current.objectGroup = "main";
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				if(line == null || line.startsWith("#") || line.equals(" ")) {
				} else {
					String[] split = line.split(" ");
					
					switch(split[0]) {
						case "mtllib":
							break;
						case "usemtl":
							for(OBJMaterial material : materials) {
								if(split[1].equals(material.getName()))
									current.materials.add(material);
							}
							break;
						case "o":
							if(hasData)
								model.add(current);
							current = new OBJ();
							current.setObjectGroup(split[1]);
							current.setCountNormals(countNormals);
							current.setCountTextureCoords(countTextureCoords);
							current.setCountVertices(countVertices);
							current.setCountFaces(countFaces);
							hasData = true;
							break;
						case "g":
							break;
						case "vn":
							current.normals.add(new Vector3f( Float.parseFloat(split[1]), Float.parseFloat(split[2]) , Float.parseFloat(split[3])) );
							countNormals++;
							break;
						case "vt":
							if(flipTxtCoordsY) {
								current.textureCoordinates.add(new Vector2f(Float.parseFloat(split[1]), 1-Float.parseFloat(split[2])) );
							} else {
								current.textureCoordinates.add(new Vector2f(Float.parseFloat(split[1]), Float.parseFloat(split[2])) );
							
							}
							countTextureCoords++;
							break;
						case "v":
							current.vertices.add(new Vector3f( Float.parseFloat(split[1]), Float.parseFloat(split[2]) , Float.parseFloat(split[3])) );
							countVertices++;
							break;
						case "f":
							String[] split_1 = split[1].split("/");
							String[] split_2 = split[2].split("/");
							String[] split_3 = split[3].split("/");
							
							if(faceType == FaceType.F_TRI) {
								current.faces.add(new Face(
									new int[]{Integer.parseInt(split_1[0]), Integer.parseInt(split_2[0]), Integer.parseInt(split_3[0])},
									new int[]{Integer.parseInt(split_1[1]), Integer.parseInt(split_2[1]), Integer.parseInt(split_3[1])},
									new int[]{Integer.parseInt(split_1[2]), Integer.parseInt(split_2[2]), Integer.parseInt(split_3[2])}));
							
								
								if(split.length > 4) {
									for(int i = 4; i < split.length; i++) {
										//System.out.println(Arrays.toString(split[i].split("/")));
										String[] split_current = split[i].split("/");
										String[] split_before = split[i-1].split("/");
										current.faces.add(new Face(
											new int[]{ Integer.parseInt(split_1[0]), Integer.parseInt(split_before[0]), Integer.parseInt(split_current[0])},
											new int[]{ Integer.parseInt(split_1[1]), Integer.parseInt(split_before[1]), Integer.parseInt(split_current[1])},
											new int[]{ Integer.parseInt(split_1[2]), Integer.parseInt(split_before[2]), Integer.parseInt(split_current[2])}));
										countFaces++;
									}
								}
							} else {
								String[] split_4 = split[4].split("/");
								current.faces.add(new Face(
									new int[]{Integer.parseInt(split_1[0]), Integer.parseInt(split_2[0]), Integer.parseInt(split_3[0]), Integer.parseInt(split_4[0])},
									new int[]{Integer.parseInt(split_1[1]), Integer.parseInt(split_2[1]), Integer.parseInt(split_3[1]), Integer.parseInt(split_4[1])},
									new int[]{Integer.parseInt(split_1[2]), Integer.parseInt(split_2[2]), Integer.parseInt(split_3[2]), Integer.parseInt(split_4[2])}));
							}
							countFaces++;
							break;
					}
				}
			}
			model.add(current);
			
			scanner.close();
			return model;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Loads wavefront file from the file system with the use of Scanner
	 * @param path File path of model
	 * @param materials OBJMaterial list to be assigned for every object group
	 * @param face_type Type of face the model has, either quads or tris
	 * @return Returns a list of OBJ aka object groups of the model
	 */
	public static List<OBJ> getModel(String path, List<OBJMaterial> materials, FaceType faceType) {
		return getModel(path, materials, faceType, false);
	}
	
	/**
	 * Loads wavefront file from the file system with the use of Scanner,
	 * method version where FaceType defaults to F_TRI
	 * @param path File path of model
	 * @param materials OBJMaterial list to be assigned for every object group
	 * @return Returns a list of OBJ aka object groups of the model
	 */
	public static List<OBJ> getModel(String path, List<OBJMaterial> materials) {
		return getModel(path, materials, FaceType.F_TRI);
				
	}
	
	/**
	 * Loads .mtl from the file system with the use of Scanner
	 * @param mtlfilepath File path of the material
	 * @return Returns a list of all the materials on the file
	 */
	public static List<OBJMaterial> getMaterials( String mtlfilepath) {
		try {
			Scanner scanner = new Scanner(new File(mtlfilepath));
			List<OBJMaterial> materials = new ArrayList<>();
			boolean hasData = false;
			OBJMaterial material = new OBJMaterial();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line == null || line.startsWith("#") || line.equals(" ")) {
					
				} else {
					String[] split = line.split(" ");
					switch(split[0]) {
						case "newmtl":
							if(hasData);
								materials.add(material);
							material = new OBJMaterial();
							material.setNameMaterial(split[1]);
							hasData = true;
							break;
						case "map_Kd":
							material.setNameFile(split[1]);
							break;
					}
				}
			}
			
			materials.add(material);
			scanner.close();
			
			return materials;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Finds an object group of name <tt>name</tt> on the model <tt>model</tt>
	 * @param model Wavefront model
	 * @param name Name of the object group
	 * @return Returns the object group or null if no matching name is found
	 */
	public static OBJ findIn(List<OBJ> model, String name) {
		
		for(int i = 0; i < model.size(); i++) {
			if(model.get(i).objectGroup.equals(name)) {
				return model.get(i);
			}
		}
		return null;
	}
}
