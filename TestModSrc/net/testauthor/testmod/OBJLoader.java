package net.testauthor.testmod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	 * @param face_type Type of face the model has, either quads or tris
	 * @return Returns a list of OBJ aka object groups of the model
	 */
	public static List<OBJ> getModel(String path, List<OBJMaterial> materials, int face_type) {
		
		int count_vertices = 0;
		int count_txtcoords = 0;
		int count_normals = 0;
		int count_faces = 0;
		
		try {
			//File scanner
			Scanner scanner = new Scanner(new File(path));
			
			//Model, list of .obj object groups
			List<OBJ> model = new ArrayList<>();
			
			//Default names for certain parts of the .obj
			//String name_polygon_group = "default";
			boolean hasData = false;
			OBJ current = new OBJ();
			current.object_group = "main";
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
								if(split[1].equals(material.name_material))
									current.materials.add(material);
							}
							break;
						case "o":
							if(hasData)
								model.add(current);
							current = new OBJ();
							current.object_group = split[1];
							current.count_normals = count_normals;
							current.count_txtcoords = count_txtcoords;
							current.count_vertices = count_vertices;
							current.count_faces = count_faces;
							hasData = true;
							break;
						case "g":
							break;
						case "vn":
							current.normals.add(new Vector3f( Float.parseFloat(split[1]), Float.parseFloat(split[2]) , Float.parseFloat(split[3])) );
							count_normals++;
							break;
						case "vt":
							current.texture_coordinates.add(new Vector2f(Float.parseFloat(split[1]), Float.parseFloat(split[2])) );
							count_txtcoords++;
							break;
						case "v":
							current.vertices.add(new Vector3f( Float.parseFloat(split[1]), Float.parseFloat(split[2]) , Float.parseFloat(split[3])) );
							count_vertices++;
							break;
						case "f":
							String[] split_1 = split[1].split("/");
							String[] split_2 = split[2].split("/");
							String[] split_3 = split[3].split("/");
							
							if(face_type == FaceType.F_TRI) {
								current.faces.add(new Face(
									new int[]{Integer.parseInt(split_1[0]), Integer.parseInt(split_2[0]), Integer.parseInt(split_3[0])},
									new int[]{Integer.parseInt(split_1[1]), Integer.parseInt(split_2[1]), Integer.parseInt(split_3[1])},
									new int[]{Integer.parseInt(split_1[2]), Integer.parseInt(split_2[2]), Integer.parseInt(split_3[2])}));
							
								
								if(split.length > 4) {
									String[] split_4 = split[4].split("/");
									current.faces.add(new Face(
										new int[]{ Integer.parseInt(split_1[0]), Integer.parseInt(split_3[0]), Integer.parseInt(split_4[0])},
										new int[]{ Integer.parseInt(split_1[1]), Integer.parseInt(split_3[1]), Integer.parseInt(split_4[1])},
										new int[]{ Integer.parseInt(split_1[2]), Integer.parseInt(split_3[2]), Integer.parseInt(split_4[2])}));
									count_faces++;
								}
							} else {
								String[] split_4 = split[4].split("/");
								current.faces.add(new Face(
									new int[]{Integer.parseInt(split_1[0]), Integer.parseInt(split_2[0]), Integer.parseInt(split_3[0]), Integer.parseInt(split_4[0])},
									new int[]{Integer.parseInt(split_1[1]), Integer.parseInt(split_2[1]), Integer.parseInt(split_3[1]), Integer.parseInt(split_4[1])},
									new int[]{Integer.parseInt(split_1[2]), Integer.parseInt(split_2[2]), Integer.parseInt(split_3[2]), Integer.parseInt(split_4[2])}));
							}
							count_faces++;
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
	 * Loads .mtl from the file system with the use of a scanner
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
							material.name_material = split[1];
							hasData = true;
							break;
						case "map_Kd":
							material.name_file = split[1];
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
	 * Finds an object group of name {name} on the model {model}
	 * @param model Wavefront model
	 * @param name Name of the object group
	 * @return Returns the object group or null if no matching name is found
	 */
	public static OBJ findIn(List<OBJ> model, String name) {
		
		for(int i = 0; i < model.size(); i++) {
			if(model.get(i).object_group.equals(name)) {
				return model.get(i);
			}
		}
		return null;
	}
}
