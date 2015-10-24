package networking.response;


import metadata.Constants;
import utility.GamePacket;

public class ResponseCreateAccount extends GameResponse {
	private int flag;
	private int errorType;
	
	// Must override abstract class' method
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        return packet.getBytes();
    }
    
    /**
     * Set registration fail. Flag is auto set to fail
     * @param errorType which errror to be send to the client
     */
    public void setRegistrationFail(int error){
    	// set the flag to fail
    	flag = Constants.REGISTRATION_FAIL;
    	
    	// set error type
    	errorType = error;
    }
    
    /**
     * Set registration success. Flag is set to success
     */
    public void setRegistrationSuccess(){
    	// set the flag to success
    	flag = Constants.REGISTRATION_SUCCESS;
    }
}