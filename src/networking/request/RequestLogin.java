package networking.request;

import java.io.IOException;

import utility.DataReader;
import utility.GamePacket;

import java.sql.*;
import java.util.ArrayList;

import metadata.Constants;
import networking.response.ResponseLogin;
import core.Character;
import core.Player;

public class RequestLogin extends GameRequest {
	String username, pwd;
	public RequestLogin() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * Get the data from socket
	 * Expect: username and password
	 */
	@Override
	public void parse() throws IOException {
		/**
		 * Get the data from socket
		 */
		// Expected data: String username, String pwd
		try {
			username = DataReader.readString(dataInput);
			pwd = DataReader.readString(dataInput);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Connect to the database
	 * Check for account exist or not
	 * Check for password is correct or not
	 * Check for account is currently in use or not
	 * Generate the Response
	 */
	@Override
	public void doBusiness() throws Exception {
		PreparedStatement pstmt = null;
		ResponseLogin responseLogin = new ResponseLogin();
		int id = 0;
		try {
			// Query for the account in the database
			makeConnectionToDB(); // Open the connection to DB
			String sql = "SELECT * FROM user WHERE username = ?;"; // Query to get the accounts with username
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			// Got result from query. Now check for valid or not
			if (rs.next()) {
				// Account exists. Now check for password
				String password = rs.getString("password");
				if (password.equals(pwd)) {
					// check if the account is currently in use
					boolean isOnline = rs.getBoolean("is_online");
					if (isOnline) {
						// Should not let this user login again
						responseLogin
								.setLoginFail(Constants.ERROR_ACCOUNT_IS_IN_USE);
					} else {
						// Everything is good. Login success
						// Get Character list from the database
						// get the user id
						id = rs.getInt("id");
						sql = "SELECT * FROM character WHERE user_id = ?";
						pstmt = c.prepareStatement(sql);
						pstmt.setInt(1, id);
						rs = pstmt.executeQuery();

						// Put them into array list
						ArrayList<Character> list = new ArrayList<Character>();
						while (rs.next()) {
							Character character = new Character();
							character.setId(rs.getInt("id"));
							character.setName(rs.getString("char_name"));
							character.setTypeId(rs.getInt("type_id"));
							list.add(character);
						}
						// Set information to the response
						responseLogin.setLoginSuccess(list);
						// Store player to server
						//client.getServer().
					}
				}else {
					// Account exist but wrong password
					responseLogin.setLoginFail(Constants.ERROR_WRONG_PASSWORD);					
				}
			} else {
				// Send back the response for login failure: account doesn't exist
				responseLogin.setLoginFail(Constants.ERROR_ACCOUNT_NOT_FOUND);
			}
			// finally add the response to the list
			responses.add(responseLogin);
			
			// store player into gameclient
			Player p = new Player();
			p.setId(id);
			p.setCharacter(new Character());
			p.setUsername(username);
			client.setPlayer(p);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// the last thing to do is closing the connection to the DB
			closeConnectionToDB();
		}
	}
}
