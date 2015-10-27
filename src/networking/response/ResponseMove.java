package networking.response;

import metadata.Constants;
import utility.GamePacket;

public class ResponseMove extends GameResponse {
	int characterID;
	float xPos, yPos, zPos;
	/**
	 * constructor. set response code
	 */
	public ResponseMove() {
		// TODO Auto-generated constructor stub
		responseCode = Constants.S_MOVE;
	}
	
	/**
	 * generate the packet
	 * info: player_id, x, y, z
	 */
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(characterID);
        packet.addFloat(xPos);
        packet.addFloat(yPos);
        packet.addFloat(zPos);
        return packet.getBytes();
    }
    
    /** 
     * Set the player and the position
     * @param id
     * @param x
     * @param y
     * @param z
     */
    public void setMove(int id, float x, float y, float z){
    	// sets the info
    	characterID = id;
    	xPos = x;
    	yPos = y;
    	zPos = z;
    }
}