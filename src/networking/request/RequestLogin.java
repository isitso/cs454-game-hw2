package networking.request;

import java.io.IOException;

import utility.DataReader;
import utility.GamePacket;

import java.sql.*;
import java.util.ArrayList;

import metadata.Constants;
import networking.response.ResponseLogin;
import core.Character;

public class RequestLogin extends GameRequest {
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
			String username = DataReader.readString(dataInput);
			String pwd = DataReader.readString(dataInput);
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

		Statement stmt = null;
		ResponseLogin responseLogin = new ResponseLogin();
		try {
			// Query for the account in the database
			makeConnectionToDB(); // Open the connection to DB
			stmt = c.createStatement();
			String sql = ""; // Query to get the accounts with username
			ResultSet rs = stmt.executeQuery(sql);

			// Got result from query. Now check for valid or none
			if (rs.next()) {
				// Account exists. Now check for password
				sql = ""; // Query to get the list of accounts with certain
							// username and password
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					// check if the account is currently in use
					boolean isOnline = rs.getBoolean("is_online");
					if (isOnline) {
						// Should not let this user login again
						responseLogin.setFlag(Constants.LOGIN_FAIL);
						responseLogin
								.setErrorType(Constants.ERROR_ACCOUNT_IS_IN_USE);
					} else {
						// Everything is good. Login success
						responseLogin.setFlag(Constants.LOGIN_SUCCESS);

						// Get Character list from the database
						sql = "";
						rs = stmt.executeQuery(sql);

						// Put them into array list
						ArrayList<Character> list = new ArrayList<Character>();
						while (rs.next()) {
							Character character = new Character();
							character.setId(rs.getInt("id"));
							character.setName(rs.getString("char_name"));
							character.setTypeId(rs.getInt("type_id"));
							list.add(character);
						}
						responseLogin.setCharacterList(list);
					}
				}else {
					// Account exist but wrong password
					responseLogin.setFlag(Constants.LOGIN_FAIL);
					responseLogin.setErrorType(Constants.ERROR_WRONG_PASSWORD);					
				}
			} else {
				// Send back the response for login failure: account doesn't exist
				responseLogin.setFlag(Constants.LOGIN_FAIL);
				responseLogin.setErrorType(Constants.ERROR_ACCOUNT_NOT_FOUND);
			}
			
			// finally add the response to the list
			responses.add(responseLogin);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// the last thing to do is closing the connection to the DB
			closeConnectionToDB();
		}
	}
}
