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
	String objectGroup = "main";
	List<OBJMaterial> materials = null;
	List<Vector3f> vertices = null;
	List<Vector2f> textureCoordinates = null;
	List<Vector3f> normals = null;
	List<Face> faces = null;
	FaceType faceType;
	
	/*
	 * Due to the separation the model in parts I need this or
	 * there will be array out of bounds crashes!
	 */
	int countVertices = 0;
	int countTextureCoords = 0;
	int countNormals = 0;
	int countFaces = 0;
	
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
		
		this.objectGroup = object_group;
		this.materials = materials;
		this.vertices = vertices;
		this.textureCoordinates = texture_coordinates;
		this.normals = normals;
		this.faces = faces;
	}
	
	public OBJ() {
		this(new String(), new ArrayList<OBJMaterial>(), new ArrayList<Vector3f>(), new ArrayList<Vector2f>(), new ArrayList<Vector3f>(), new ArrayList<Face>());
	}
	
	public String getObjectGroup() {
		return objectGroup;
	}
	
	public List<Vector3f> getVertices() {
		return vertices;
	}
	
	public List<Vector2f> getTextureCoordinates() {
		return textureCoordinates;
	}
	
	public List<Vector3f> getNormals() {
		return normals;
	}
	
	public List<Face> getFaces() {
		return faces;
	}
	
	public FaceType getFaceType() {
		return faceType;
	}
	
	public void setObjectGroup(String objectGroup) {
		this.objectGroup = objectGroup;
	}
	
	public void setCountVertices(int countVertices) {
		this.countVertices = countVertices;
	}
	
	public void setCountTextureCoords(int countTextureCoords) {
		this.countTextureCoords = countTextureCoords;
	}
	
	public void setCountNormals(int countNormals) {
		this.countNormals = countNormals;
	}
	
	public void setCountFaces(int countFaces) {
		this.countFaces = countFaces;
	}
	
	/**
	 * Idk what to put here honestly
	 * @author InvalidName
	 *
	 */
	public static class Face extends Object {
		private int[] vertexIndices;
		private int[] textureCoordinatesIndices;
		private int[] normalIndices;
		
		public int[] getVertices() {
			return this.vertexIndices;
		}
		
		public int[] getTextureCoordinates() {
			return this.textureCoordinatesIndices;
		}
		
		public int[] getNormals() {
			return this.normalIndices;
		}
		public Face(int[] vertexIndices, int[] textureCoordinatesIndices, int[] normalIndices) {
			super();
			this.vertexIndices = vertexIndices;
			this.textureCoordinatesIndices = textureCoordinatesIndices;
			this.normalIndices = normalIndices;
		}
	}
	
	/**
	 * Enum containing the face type constants
	 * @author InvalidName
	 */
	public enum FaceType {
		F_TRI, F_QUAD
	}
}
