package networking.request;

import java.io.IOException;
import java.sql.PreparedStatement;

import metadata.Constants;
import networking.response.ResponseGoToCharacterSelection;
import networking.response.ResponseLogout;
import networking.response.ResponsePlayerLogout;

public class RequestGoToCharacterSelection extends GameRequest {
	
	/**
	 * Constructor
	 */
	public RequestGoToCharacterSelection() {
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
        	ResponseGoToCharacterSelection response = new ResponseGoToCharacterSelection();
    		// client is playing the game with a character. save it
    		makeConnectionToDB();	// open connection to database
    		// update position and hpr
    		String sql = "UPDATE user SET char_x = ?, char_y = ?, char_z = ?, char_h = ?, char_p = ?, char_z = ? WHERE id = ?;)";
    		PreparedStatement pstmt = c.prepareStatement(sql);
    		pstmt.setFloat(1, client.getPlayer().getCharacter().getX());
    		pstmt.setFloat(2, client.getPlayer().getCharacter().getY());
    		pstmt.setFloat(3, client.getPlayer().getCharacter().getZ());
    		pstmt.setFloat(4, client.getPlayer().getCharacter().getH());
    		pstmt.setFloat(5, client.getPlayer().getCharacter().getP());
    		pstmt.setFloat(6, client.getPlayer().getCharacter().getR());
    		pstmt.setInt(7, client.getPlayer().getCharacter().getId());
    		try {
    			pstmt.executeUpdate();	// update database
    			closeConnectionToDB();
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    		// let other clients know that this one has logged out
    		ResponsePlayerLogout otherResponse = new ResponsePlayerLogout();
    		otherResponse.setCharacterID(client.getPlayer().getCharacter().getId());
    		client.getServer().addResponseForAllOnlinePlayers(client.getId(), otherResponse);
    		// let this client log out
    		responses.add(response);
    		// change client's gamestate to LOGGED IN
    		client.setGamestate(Constants.GAMESTATE_LOGGED_IN);
    	}
    }
}
