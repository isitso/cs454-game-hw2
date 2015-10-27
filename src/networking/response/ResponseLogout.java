package networking.response;

import metadata.Constants;
import utility.GamePacket;

public class ResponseLogout extends GameResponse {
	
	/**
	 * constructor. set response code
	 */
	public ResponseLogout() {
		// TODO Auto-generated constructor stub
		responseCode = Constants.S_DISCONNECT;
	}
	
	// Must override abstract class' method
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        return packet.getBytes();
    }
}
