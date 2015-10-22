package networking.response;

import java.util.ArrayList;



import metadata.Constants;
import utility.GamePacket;
import core.Character;

public class ResponseLogin extends GameResponse {
	private int flag;
	private int errorType;
	private ArrayList<Character> characterList;
	
	public ResponseLogin() {
		// TODO Auto-generated constructor stub
		// Set the response id 
		responseCode = Constants.S_AUTH;
	}
	// Must override abstract class' method
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(flag);
        if (flag == 0){
        	// Login fail: what kind of error?
        	packet.addInt32(errorType);
        }else{
        	// login success: give me the list count and list of character
        	packet.addInt32(characterList.size());
        	for (Character c : characterList){
        		packet.addInt32(c.getId());
        		packet.addInt32(c.getTypeId());
        		packet.addString(c.getName());
        	}
        }
        return packet.getBytes();
    }
    

	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	public ArrayList<Character> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(ArrayList<Character> characterList) {
		this.characterList = characterList;
	}
    
}
