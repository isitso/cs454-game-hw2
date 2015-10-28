package networking.response;

import metadata.Constants;
import utility.GamePacket;
import core.Character;
public class ResponseSpawn extends GameResponse {
	Character character;
	int flag;
	/**
	 * constructor. ser the response code
	 */
	public ResponseSpawn() {
		// TODO Auto-generated constructor stub
		responseCode = Constants.S_SPAWN;
	}

	/**
	 * generate the packet.
	 * info: flag, char_id, char_type_id, char_name, x, y, z, h, p, r
	 */
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(flag);
        packet.addInt32(character.getId());
        packet.addString(character.getName());
        packet.addInt32(character.getTypeId());
        packet.addFloat(character.getX());
        packet.addFloat(character.getY());
        packet.addFloat(character.getZ());
        packet.addFloat(character.getH());
        packet.addFloat(character.getP());
        packet.addFloat(character.getR());
        return packet.getBytes();
    }
    
    /**
     * Set character/npc to spawn
     * @param fl flag to set
     * @param c character to send to the client
     */
    public void setCharacter(int fl, Character c) {
    	// set the flag and character
    	flag = fl;
    	character = c;
    }
}