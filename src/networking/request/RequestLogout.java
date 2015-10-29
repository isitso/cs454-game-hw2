package networking.request;

import java.io.IOException;
import java.sql.PreparedStatement;

import metadata.Constants;
import networking.response.ResponseLogout;
import networking.response.ResponsePlayerLogout;
import utility.DataReader;

public class RequestLogout extends GameRequest {
	
	/**
	 * Constructor
	 */
	public RequestLogout() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * Get data from socket
	 * Expected: none
	 */
    @Override
    public void parse() throws IOException {
    }

    /**
     * Client requested logout
     * if client was playing, save character information into the database
     * then send ResponsePlayerLogout to other playing players
     */
    @Override
    public void doBusiness() throws Exception {
    	if (client.getGamestate() == Constants.GAMESTATE_PLAYING){
        	ResponseLogout response = new ResponseLogout();
    		try {
    			// let other clients know that this one has logged out
//        		ResponsePlayerLogout otherResponse = new ResponsePlayerLogout();
//        		otherResponse.setCharacterID(client.getPlayer().getCharacter().getId());
//        		client.getServer().addResponseForAllOnlinePlayers(client.getId(), otherResponse);
        		// let this client log out
        		responses.add(response);

        		// stop GameClient object
        		client.stopClient();
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    	}
    }
}
