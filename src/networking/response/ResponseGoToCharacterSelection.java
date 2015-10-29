package networking.response;

import java.util.ArrayList;

import metadata.Constants;
import utility.GamePacket;
import core.Character;

public class ResponseGoToCharacterSelection extends GameResponse {
	ArrayList<Character> characterList;
	
	/**
	 * constructor. set response code
	 */
	public ResponseGoToCharacterSelection() {
		// TODO Auto-generated constructor stub
		responseCode = Constants.S_GO_TO_CHARACTER_SELECTION;
	}
	
	/**
	 * generate packet.
	 * info: character counts, list of (char_id, char_type)
	 */
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        // add character count
    	packet.addInt32(characterList.size());
    	// add
    	for (Character c : characterList){
    		packet.addInt32(c.getId());
    		packet.addInt32(c.getTypeId());
    	}
        return packet.getBytes();
    }

	public ArrayList<Character> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(ArrayList<Character> characterList) {
		this.characterList = characterList;
	}

    
}