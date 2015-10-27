package networking.request;

import java.io.IOException;
import java.sql.*;

import networking.response.ResponseSelectCharacter;
import utility.DataReader;

public class RequestSelectCharacter extends GameRequest {

	int char_id;
	// Must override the abstract class' method
    @Override   
    public void parse() throws IOException 
    {
    	try
    	{
    		char_id = DataReader.readInt(dataInput);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    // Must override the abstract class' method
    @Override
    public void doBusiness() throws Exception 
    {
    	ResponseSelectCharacter response = new ResponseSelectCharacter();
    	try
    	{
    		//make connection to DB
    		makeConnectionToDB();
    		
    		//updating database to reflect that the character is now active
    		String sql = "UPDATE character SET char_active = 1 WHERE id = ?";
    		PreparedStatement pstmt =  c.prepareStatement(sql);
    		pstmt.setInt(1, char_id);
    		pstmt.execute();
    		response.setChracterSelectionToSuccess();
    		
    		//updating the Character class 
    		sql = "SELECT * FROM character WHERE id =?";
    		pstmt = c.prepareStatement(sql);
    		pstmt.setInt(1, char_id);
    		ResultSet rs = pstmt.executeQuery();
    		
    		this.client.getPlayer().getCharacter().setId(rs.getInt("id"));
    		this.client.getPlayer().getCharacter().setName(rs.getString("char_name"));
    		this.client.getPlayer().getCharacter().setTypeId(rs.getInt("char_type_id"));
    		this.client.getPlayer().getCharacter().setX(rs.getInt("char_x"));
    		this.client.getPlayer().getCharacter().setY(rs.getInt("char_y"));
    		this.client.getPlayer().getCharacter().setZ(rs.getInt("char_z"));
    		this.client.getPlayer().getCharacter().setH(rs.getInt("char_h"));
    		this.client.getPlayer().getCharacter().setP(rs.getInt("char_p"));
    		this.client.getPlayer().getCharacter().setR(rs.getInt("char_r"));
    	}
    	catch (Exception e)
    	{
    		response.setChracterSelectionToFail();
    	}
    }
}
