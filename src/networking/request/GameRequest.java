package networking.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Custom Imports
import core.GameClient;
import networking.response.GameResponse;
import java.sql.*;

/**
 * The GameRequest class is an abstract class used as a basis for storing
 * request information.
 */
public abstract class GameRequest {

    protected GameClient client;
    protected DataInputStream dataInput;
    protected List<GameResponse> responses;
    protected int request_id;
    protected Connection c;	// Connection to the database

    public GameRequest() {
        responses = new ArrayList<GameResponse>();
        c = null;
    }

    public int getID() {
        return request_id;
    }

    public int setID(int request_id) {
        return this.request_id = request_id;
    }

    public void setDataInputStream(DataInputStream dataInput) {
        this.dataInput = dataInput;
    }

    public void setGameClient(GameClient client) {
        this.client = client;
    }

    /**
     * Parse the request from the input stream.
     * 
     * @throws IOException 
     */
    public abstract void parse() throws IOException;

    /**
     * Interpret the information from the request.
     * 
     * @throws Exception 
     */
    public abstract void doBusiness() throws Exception;

    /**
     * Get the responses generated from the request.
     * 
     * @return the responses
     */
    public List<GameResponse> getResponses() {
        return responses;
    }

    
    /**
     * Make connection to the database
     * http://www.tutorialspoint.com/sqlite/sqlite_java.htm
     */
    public void makeConnectionToDB(){
    	try{
    	Class.forName("org.sqlite.JDBC");
    	// The database need to changed to working one
    	c = DriverManager.getConnection("jdbc:sqlite:hw2.db");
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Close connection to the database
     */
    public void closeConnectionToDB(){
    	try{
    		// Only close the connection if it is still open
    		if ((c != null) && c.isValid(0))
    			c.close();
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}