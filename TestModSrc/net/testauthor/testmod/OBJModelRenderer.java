package net.testauthor.testmod;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.ModelRenderer;
import dangerzone.TextureMapper;
import net.testauthor.testmod.OBJ.Face;
import net.testauthor.testmod.OBJ.FaceType;

public class OBJModelRenderer extends ModelRenderer {
	
	OBJ obj;
	Texture model_texture;
	
	/**
	 * Constructor for an individual OBJ(its actually a object group)
	 * @param obj Object group from .obj
	 * @param material_texture_path File path of the model texture, uses .png
	 */
	public OBJModelRenderer(OBJ obj, String material_texture_path) {
		//Here for compability, must not be used
		super(0,0);
		
		this.obj = obj;
		this.model_texture = TextureMapper.getTexture(material_texture_path);
	}
	
	/**
	 * Offsets the position of the model
	 * @param x Position X
	 * @param y Position Y
	 * @param z Position Z
	 */
	public void setOffset(float x, float y, float z) {
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
	}
	
	/**
	 * Default rendering code
	 * <br>
	 * deathfactorscale is used by the entity death animation
	 * @see dangerzone.ModelRenderer
	 */
	public void render(float deathfactorscale) {
		GL11.glScalef(1f, 1f, 1f);
		DangerZone.wr.loadtexture(model_texture);
		if (!this.compiled) {
			this.compileDisplayList();
		}
		
		float my = -(this.rotationPointY*deathfactorscale-24);
		if(deathfactorscale >= 1.1f && my < 0)my = -my; //So small things don't just explode into the ground on dying!
		GL11.glTranslatef(this.rotationPointX*deathfactorscale, my, -this.rotationPointZ*deathfactorscale);
		
		GL11.glPushMatrix();
		
		//Too small by default, so scale everything up!
		GL11.glScalef(8f, 8f, 8f);
		
		if (this.rotateAngleZ != 0.0f) {
			GL11.glRotatef(-this.rotateAngleZ * (180f / (float)Math.PI), 0.0f, 0.0f, 1.0f);
		}
		if (this.rotateAngleY != 0.0f) {
    		GL11.glRotatef(-this.rotateAngleY * (180f / (float)Math.PI), 0.0f, 1.0f, 0.0f);
    	}
		if (this.rotateAngleX != 0.0f) {
			GL11.glRotatef(this.rotateAngleX * (180f / (float)Math.PI), 1.0f, 0.0f, 0.0f);
		}
		
		my = -this.offsetY*deathfactorscale;
		if(deathfactorscale > 1.25f && my < 0)my = -my;
		GL11.glTranslatef(this.offsetX*deathfactorscale, my, -this.offsetZ*deathfactorscale);
		
		
		
		GL11.glCallList(this.displayList);
		
		GL11.glPopMatrix();
		
		my = -(this.rotationPointY*deathfactorscale-24);
		if(deathfactorscale >= 1.1f && my < 0)my = -my;
		GL11.glTranslatef(-this.rotationPointX*deathfactorscale, -my, this.rotationPointZ*deathfactorscale);  
		
		GL14.glSecondaryColor3f(0.0f, 0.0f, 0.0f);
	}
	
	/**
	 * Compiles current part into the display list
	 */
	private void compileDisplayList() {
		if(compiled)return;
		if(DangerZone.wr == null)return; //server!
		
		this.displayList = DangerZone.wr.getNextRenderID();
		GL11.glNewList(this.displayList, GL11.GL_COMPILE);
		
		//GL11.glBegin(GL11.GL_QUADS);
		
		compile_part();
		
		/*if(connected_parts != null) {
			for(int i=0;i<connected_parts.size();i++) {
				compile_connected_part(connected_parts.get(i));
				}
			connected_parts = null;
		}*/
		
		GL11.glEnd(); // Done Drawing 
		
		GL11.glEndList();
		this.compiled = true;
	}
	
	/**
	 * Cycles through all faces and compiles them into the display list!
	 */
	private void compile_part() {
		for(Face face : obj.getFaces()) {
			//Compile as quad if faces are quads(squares)
			if(obj.getFaceType() == FaceType.F_QUAD) {
				GL11.glBegin(GL11.GL_QUADS);
				
				Vector3f[] normals = { obj.getNormals().get(face.getNormals()[0]-1-obj.count_normals), obj.getNormals().get(face.getNormals()[1]-1-obj.count_normals), obj.getNormals().get(face.getNormals()[2]-1-obj.count_normals), obj.getNormals().get(face.getNormals()[3]-1-obj.count_normals)};
				Vector2f[] texture_coords = { obj.getTextureCoordinates().get(face.getTextureCoordinates()[0]-1-obj.count_txtcoords), obj.getTextureCoordinates().get(face.getTextureCoordinates()[1]-1-obj.count_txtcoords), obj.getTextureCoordinates().get(face.getTextureCoordinates()[2]-1-obj.count_txtcoords), obj.getTextureCoordinates().get(face.getTextureCoordinates()[3]-1-obj.count_txtcoords)};
				Vector3f[] vertices = { obj.getVertices().get(face.getVertices()[0]-1-obj.count_vertices), obj.getVertices().get(face.getVertices()[1]-1-obj.count_vertices), obj.getVertices().get(face.getVertices()[2]-1-obj.count_vertices), obj.getVertices().get(face.getVertices()[3]-1-obj.count_vertices)};
				
				//System.out.println("Vertex of index 0,Txtcoord X:"+texture_coords[0].getX()+" Txtcoord Y:"+texture_coords[0].getY());
				//System.out.println("Vertex of index 1,Txtcoord X:"+texture_coords[1].getX()+" Txtcoord Y:"+texture_coords[1].getY());
				//System.out.println(face.getType());
				
				{
				GL11.glNormal3f(normals[0].getX(), normals[0].getY(), normals[0].getZ());
				GL11.glTexCoord2f(texture_coords[0].getX(), texture_coords[0].getY()-0.000001f);
				GL11.glVertex3f(vertices[0].getX(), vertices[0].getY(), vertices[0].getZ());
				GL11.glNormal3f(normals[1].getX(), normals[1].getY(), normals[1].getZ());
				GL11.glTexCoord2f(texture_coords[1].getX(), texture_coords[1].getY()-0.000001f);
				GL11.glVertex3f(vertices[1].getX(), vertices[1].getY(), vertices[1].getZ());
				GL11.glNormal3f(normals[2].getX(), normals[2].getY(), normals[2].getZ());
				GL11.glTexCoord2f(texture_coords[2].getX(), texture_coords[2].getY()-0.000001f);
				GL11.glVertex3f(vertices[2].getX(), vertices[2].getY(), vertices[2].getZ());
				GL11.glNormal3f(normals[3].getX(), normals[3].getY(), normals[3].getZ());
				GL11.glTexCoord2f(texture_coords[3].getX(), texture_coords[3].getY()-0.000001f);
				GL11.glVertex3f(vertices[3].getX(), vertices[3].getY(), vertices[3].getZ());
				}
			} else {
				GL11.glBegin(GL11.GL_TRIANGLES);
				
				Vector3f[] normals = { obj.getNormals().get(face.getNormals()[0]-1-obj.count_normals), obj.getNormals().get(face.getNormals()[1]-1-obj.count_normals), obj.getNormals().get(face.getNormals()[2]-1-obj.count_normals)};
				Vector2f[] texture_coords = { obj.getTextureCoordinates().get(face.getTextureCoordinates()[0]-1-obj.count_txtcoords), obj.getTextureCoordinates().get(face.getTextureCoordinates()[1]-1-obj.count_txtcoords), obj.getTextureCoordinates().get(face.getTextureCoordinates()[2]-1-obj.count_txtcoords)};
				Vector3f[] vertices = { obj.getVertices().get(face.getVertices()[0]-1-obj.count_vertices), obj.getVertices().get(face.getVertices()[1]-1-obj.count_vertices), obj.getVertices().get(face.getVertices()[2]-1-obj.count_vertices)};
				
				//System.out.println("Vertex of index 0,Txtcoord X:"+texture_coords[0].getX()+" Txtcoord Y:"+texture_coords[0].getY());
				//System.out.println("Vertex of index 1,Txtcoord X:"+texture_coords[1].getX()+" Txtcoord Y:"+texture_coords[1].getY());
				//System.out.println("Vertex of index 2,Txtcoord X:"+texture_coords[2].getX()+" Txtcoord Y:"+texture_coords[2].getY());
				
				{
				GL11.glNormal3f(normals[0].getX(), normals[0].getY(), normals[0].getZ());
				GL11.glTexCoord2f(texture_coords[0].getX(), texture_coords[0].getY()-0.000001f);
				GL11.glVertex3f(vertices[0].getX(), vertices[0].getY(), vertices[0].getZ());
				GL11.glNormal3f(normals[1].getX(), normals[1].getY(), normals[1].getZ());
				GL11.glTexCoord2f(texture_coords[1].getX(), texture_coords[1].getY()-0.000001f);
				GL11.glVertex3f(vertices[1].getX(), vertices[1].getY(), vertices[1].getZ());
				GL11.glNormal3f(normals[2].getX(), normals[2].getY(), normals[2].getZ());
				GL11.glTexCoord2f(texture_coords[2].getX(), texture_coords[2].getY()-0.000001f);
				GL11.glVertex3f(vertices[2].getX(), vertices[2].getY(), vertices[2].getZ());
				}
			}
			
		}
	}
}
