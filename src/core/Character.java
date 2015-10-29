package core;

public class Character {
	/**
	 * Character class hold data for one character in the game
	 * It can be a player character or NPC
	 */
	// Class members
	int id;
	int typeId;	// determine which model to use
	String name;
	float x, y, z, h, p, r;	// xyz is for position. hpr is for hpr
	
	// Getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public float getH() {
		return h;
	}
	public void setH(float h) {
		this.h = h;
	}
	public float getP() {
		return p;
	}
	public void setP(float p) {
		this.p = p;
	}
	public float getR() {
		return r;
	}
	public void setR(float r) {
		this.r = r;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * set position x y z at the same time
	 */
	public void setPos(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * set hpr
	 */
	public void setHpr(float h, float p, float r) {
		this.h = h;
		this.p = p;
		this.r = r;
	}
}
