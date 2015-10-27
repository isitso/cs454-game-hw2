package networking.request;

import java.io.IOException;

public class RequestHeartbeat extends GameRequest {
	/**
	 * Constructor
	 */
	public RequestHeartbeat() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * Get data from socket
	 * Expected: nothing
	 */
    @Override
    public void parse() throws IOException {
    }

    /**
     * 
     */
    @Override
    public void doBusiness() throws Exception {
    }
}
