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

	/**
	 * Construct the packet using the flag, errorType,
	 * and Character List
	 */
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
    
    /**
     * Set login fail. Flag is auto set to fail.
     * @param errorType which error to be send to the client
     * @return void
     */
    public void setLoginFail(int error){
    	// set the flag to fail
    	flag = Constants.LOGIN_FAIL;
    	
    	// set the error type
    	errorType = error;
    }
    
    /**
     * Set log in success. Flag is auto set to success.
     * character list must be given
     * @param characterList list of characters to send to the client
     * @return
     */
    public void setLoginSuccess(ArrayList<Character> list){
    	// set the flag to success
    	flag = Constants.LOGIN_SUCCESS;
    	characterList = list;
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
