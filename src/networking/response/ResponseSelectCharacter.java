package networking.response;

import metadata.Constants;
import utility.GamePacket;

public class ResponseSelectCharacter extends GameResponse {
	
	int flag;
	
	/**
	 * constructor. set response code
	 */
	public ResponseSelectCharacter() {
		// TODO Auto-generated constructor stub
		responseCode = Constants.S_SELECT_CHARACTER;
	}
	
	/**
	 * Generate the packet
	 */
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(flag);
        return packet.getBytes();
    }

	public void setChracterSelectionToSuccess() 
	{
		flag = Constants.CHARACTER_SELECTION_SUCCESS;
    	if (Constants.DEBUG)
    		System.out.println("Selection success. Account-Character selected.");
	}

	public void setChracterSelectionToFail() 
	{
		//there really should be no reason this should fail
		flag = Constants.CHARACTER_SELECTION_FAIL;
	}
}