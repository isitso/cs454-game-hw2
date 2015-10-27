package networking.response;

import metadata.Constants;
import utility.GamePacket;

public class ResponseSelectCharacter extends GameResponse {
	
	int flag;
	// Must override abstract class' method
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
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