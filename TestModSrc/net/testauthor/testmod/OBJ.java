package net.testauthor.testmod;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Implementation of wavefront file format
 * <p> 
 * DO NOT be deceived by the file and class name,
 * even though its called OBJ the class is actually an
 * object group from a given .obj, I named it like that
 * because I had no idea of a better name!
 * <p>
 * An object group is essentially a group of faces, those
 * faces being a subset of the all of those the model has
 * @author InvalidName
 */
public class OBJ {
	
	//Name of the object group that is part of the obj model
	String object_group = "main";
	List<OBJMaterial> materials = null;
	List<Vector3f> vertices = null;
	List<Vector2f> texture_coordinates = null;
	List<Vector3f> normals = null;
	List<Face> faces = null;
	
	/*
	 * Due to the separation the model in parts I need this or
	 * there will be array out of bounds crashes!
	 */
	int face_type;
	int count_vertices = 0;
	int count_txtcoords = 0;
	int count_normals = 0;
	int count_faces = 0;
	
	/**
	 * 
	 * @param object_group Name of this object group
	 * @param materials Materials this group uses
	 * @param vertices List of vertices of the group
	 * @param texture_coordinates List of vertices of the group
	 * @param normals List of vertices of the group
	 * @param faces List of vertices of the group
	 */
	public OBJ(String object_group, List<OBJMaterial> materials, List<Vector3f> vertices, List<Vector2f> texture_coordinates, List<Vector3f> normals, List<Face> faces) {
		super();
		
		this.object_group = object_group;
		this.materials = materials;
		this.vertices = vertices;
		this.texture_coordinates = texture_coordinates;
		this.normals = normals;
		this.faces = faces;
	}
	
	public OBJ() {
		this(new String(), new ArrayList<OBJMaterial>(), new ArrayList<Vector3f>(), new ArrayList<Vector2f>(), new ArrayList<Vector3f>(), new ArrayList<Face>());
	}
	
	public List<Vector3f> getVertices() {
		return vertices;
	}
	
	public List<Vector2f> getTextureCoordinates() {
		return texture_coordinates;
	}
	
	public List<Vector3f> getNormals() {
		return normals;
	}
	
	public List<Face> getFaces() {
		return faces;
	}
	
	public int getFaceType() {
		return face_type;
	}
	
	/**
	 * Idk what to put here honestly
	 * @author InvalidName
	 *
	 */
	public static class Face extends Object {
		private int[] vertex_indices;
		private int[] texture_coordinates_indices;
		private int[] normal_indices;
		
		public int[] getVertices() {
			return this.vertex_indices;
		}
		
		public int[] getTextureCoordinates() {
			return this.texture_coordinates_indices;
		}
		
		public int[] getNormals() {
			return this.normal_indices;
		}
		public Face(int[] vertex_indices, int[] texture_coordinate_indices, int[] normal_indices) {
			super();
			this.vertex_indices = vertex_indices;
			this.texture_coordinates_indices = texture_coordinate_indices;
			this.normal_indices = normal_indices;
		}
	}
	
	/**
	 * Class containing the face type constants
	 * @author InvalidName
	 */
	public static class FaceType {
		public static final int F_TRI = 0;
		public static final int F_QUAD = 1;
	}
}
