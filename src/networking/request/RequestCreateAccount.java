package networking.request;

import java.io.IOException;
import java.sql.*;

import metadata.Constants;
import networking.response.ResponseCreateAccount;
import utility.DataReader;

public class RequestCreateAccount extends GameRequest {
	String username, pwd;

	public RequestCreateAccount() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * Parsing the request from the client
	 *  Expect String username and String password
	 */
	@Override
	public void parse() throws IOException {
		try {
			username = DataReader.readString(dataInput);
			pwd = DataReader.readString(dataInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create new account
	 * Check for account already exists
	 */
	@Override
	public void doBusiness() throws Exception {
		ResponseCreateAccount response = new ResponseCreateAccount();
		try {
			// open connection to DB
			makeConnectionToDB();

			// query for account with that username
			String sql = "SELECT * FROM user WHERE username = ?;";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()){
				// account already exists
				response.setRegistrationFail(Constants.REGISTRATION_ACCOUNT_ALREADY_EXIST);
			}else {
				// account does not exist. create new account
				sql = "INSERT INTO user(username, password, is_online) VALUES(?, ?, 0);";
				pstmt = c.prepareStatement(sql);
				pstmt.setString(1, username);
				pstmt.setString(2, pwd);
				pstmt.execute();
				response.setRegistrationSuccess();
				
			}
			// add response to the list
			responses.add(response);
			closeConnectionToDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
