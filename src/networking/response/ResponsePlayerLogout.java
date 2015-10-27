package networking.response;

import metadata.Constants;
import utility.GamePacket;

public class ResponsePlayerLogout extends GameResponse {
	int characterID;
	
	/**
	 * constructor. set response code
	 */
	public ResponsePlayerLogout() {
		// TODO Auto-generated constructor stub
		responseCode = Constants.S_PLAYER_LOGOUT;
	}
	
	/**
	 * generate packet.
	 * info: character id
	 */
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(characterID);
        return packet.getBytes();
    }

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}
    
}