package networking.request;

import java.io.IOException;
import java.sql.*;

import core.Character;
import core.Player;
import metadata.Constants;
import networking.response.ResponseSelectCharacter;
import networking.response.ResponseSpawn;
import utility.DataReader;

public class RequestSelectCharacter extends GameRequest {

	int type_id;

	// Must override the abstract class' method
	@Override
	public void parse() throws IOException {
		try {
			type_id = DataReader.readInt(dataInput);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Must override the abstract class' method
	@Override
	public void doBusiness() throws Exception {
		if (client.getGamestate() == Constants.GAMESTATE_LOGGED_IN) {
			// let client select character only when he logged in
			ResponseSelectCharacter response = new ResponseSelectCharacter();
			try {
				// make connection to DB
				makeConnectionToDB();

				// updating database to reflect that the character is now active
//				String sql = "UPDATE character SET char_active = 1 WHERE id = ?";
//				PreparedStatement pstmt = c.prepareStatement(sql);
//				pstmt.setInt(1, type_id);
//				pstmt.execute();
//				response.setChracterSelectionToSuccess();

				// print out something
				if (Constants.DEBUG)
					System.out.printf("Client selected character with char_type_id = %d", type_id);
				// updating the Character class
				String sql = "SELECT * FROM character WHERE char_type_id = ? AND user_id = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, type_id);
				pstmt.setInt(2, client.getPlayer().getId());
				ResultSet rs = pstmt.executeQuery();
				if (!rs.next()) {
					// no character. create one for that account
					if (Constants.DEBUG)
						System.out.println("No character in character table. Creating new one.");
					sql = "INSERT INTO character(user_id, char_type_id, char_active, char_x, char_y, char_z, char_h, char_p, char_r)\n"
							+ "VALUES(?, ?, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);";
					pstmt = c.prepareStatement(sql);
					pstmt.setInt(1, client.getPlayer().getId());
					pstmt.setInt(2, type_id);
					pstmt.executeUpdate();
					
					// now get the info for character. need char_id
					sql = "SELECT * FROM character WHERE char_type_id = ? AND user_id = ?";
					pstmt = c.prepareStatement(sql);
					pstmt.setInt(1, type_id);
					pstmt.setInt(2, client.getPlayer().getId());
					rs = pstmt.executeQuery();
					if (Constants.DEBUG)
						System.out.printf("Created new character with id = %d", rs.getInt("id"));
				}
				// success
				Character character = new core.Character();
				character.setId(rs.getInt("id"));
				character.setTypeId(rs.getInt("char_type_id"));
				character.setName(client.getPlayer().getUsername());;
				character.setX(rs.getInt("char_x"));
				character.setY(rs.getInt("char_y"));
				character.setZ(rs.getInt("char_z"));
				character.setH(rs.getInt("char_h"));
				character.setP(rs.getInt("char_p"));
				character.setR(rs.getInt("char_r"));

				// change client's gamestate to playing
				client.getPlayer().setCharacter(character);
				client.setGamestate(Constants.GAMESTATE_PLAYING);
				response.setChracterSelectionToSuccess();
				responses.add(response);

				// spawn main player and other
				ResponseSpawn responseSpawn = new ResponseSpawn();
				responseSpawn.setCharacter(Constants.SPAWN_MAIN_PLAYER,
						character);
				responses.add(responseSpawn);
				for (Player p : client.getServer().getActivePlayers()) {
					ResponseSpawn spawn = new ResponseSpawn();
					spawn.setCharacter(Constants.SPAWN_PLAYER, p.getCharacter());
					responses.add(spawn);
				}

				// response for other online players
				ResponseSpawn otherResponseSpawn = new ResponseSpawn();
				otherResponseSpawn.setCharacter(Constants.SPAWN_PLAYER,
						character);
				client.getServer().addResponseForAllOnlinePlayers(
						client.getId(), otherResponseSpawn);

				// add this character to server's online list
				client.getServer().setActivePlayer(client.getPlayer());

			} catch (Exception e) {
				e.printStackTrace();
				// response.setChracterSelectionToFail();
			}finally{
				// close connection
				closeConnectionToDB();
			}
		}
	}
}
