package networking.response;

import utility.GamePacket;

public class ResponseCreateAccount extends GameResponse {
	
	// Must override abstract class' method
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        return packet.getBytes();
    }
}