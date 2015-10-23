package metadata;

// Java Imports
import java.util.HashMap;

// Custom Imports
import networking.request.GameRequest;

/**
 * The GameRequestTable class stores a mapping of unique request code numbers
 * with its corresponding request class.
 */
public class GameRequestTable {

    private static HashMap<Short, Class> requestNames; // Stores request classes by request codes

    /**
     * Initialize the hash map by populating it with request codes and classes.
     */
    public static void init() {
        requestNames = new HashMap<Short, Class>();

        // Populate the hash map using request codes and class names
        add(Constants.C_AUTH, "RequestLogin");
        add(Constants.C_REGISTER, "RequestCreateAccount");
        add(Constants.C_CREATE_CHARACTER, "RequestCreateAccount");
        add(Constants.C_DISCONNECT, "RequestLogout");
        add(Constants.C_GO_TO_CHARACTER_SELECTION, "RequestGoToCharacterSelection");
        add(Constants.C_SELECT_CHARACTER, "RequestSelectCharacter");
        add(Constants.C_CREATE_CHARACTER, "RequestCreateCharacter");
        add(Constants.C_MOVE, "RequestMove");
        add(Constants.C_CHAT, "RequestChat");
        add(Constants.C_HEARTBEAT, "RequestHeartbeat");
//        add(Constants.CMSG_SAVE_EXIT_GAME, "RequestExitGame");
        add(Constants.RAND_INT, "RequestInt");
        add(Constants.RAND_STRING, "RequestString");
        add(Constants.RAND_SHORT, "RequestShort");
        add(Constants.RAND_FLOAT, "RequestFloat");
    }

    /**
     * Map the request code number with its corresponding request class,
     * derived from its class name using reflection, by inserting the pair into
     * the hash map.
     * 
     * @param code a value that uniquely identifies the request type
     * @param name a string value that holds the name of the request class
     */
    public static void add(short code, String name) {
        try {
            requestNames.put(code, Class.forName("networking.request." + name));
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Get the instance of the request class by the given request code.
     * 
     * @param requestID a value that uniquely identifies the request type
     * @return the instance of the request class
     */
    public static GameRequest get(short requestID) {
        GameRequest request = null;

        try {
            Class name = requestNames.get(requestID);

            if (name != null) {
                request = (GameRequest) name.newInstance();
                request.setID(requestID);
            } else {
                System.err.println("Invalid Request Code: " + requestID);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return request;
    }
}
