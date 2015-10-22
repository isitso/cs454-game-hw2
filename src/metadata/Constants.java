package metadata;

/**
 * The Constants class stores important variables as constants for later use.
 */
public class Constants {

    // Request (1xx) + Response (2xx)
    public final static short C_AUTH = 101;
    public final static short C_REGISTER = 103;
    public final static short C_CREATE_CHARACTER = 104;
    public final static short S_AUTH = 201;
    public final static short C_CHAT = 112;
    public final static short S_CHAT = 212;
    public final static short C_HEARTBEAT = 113;
    public final static short S_HEARTBEAT = 213;
    public final static short C_SAVE_EXIT_GAME = 119;
    public final static short S_SAVE_EXIT_GAME = 219;

    //Test Request + Response
    public final static short RAND_INT = 1;
    public final static short RAND_STRING = 2;
    public final static short RAND_SHORT = 3;
    public final static short RAND_FLOAT = 4;
    // Other
    public static final int SAVE_INTERVAL = 60000;
    public static final String CLIENT_VERSION = "1.00";
    public static final int TIMEOUT_SECONDS = 90;
    
    
    // FLAGs
    public static final int LOGIN_FAIL = 0;
    public static final int LOGIN_SUCCESS = 1;
    public static final int ERROR_ACCOUNT_NOT_FOUND = 0;
    public static final int ERROR_WRONG_PASSWORD = 1;
    public static final int ERROR_ACCOUNT_IS_IN_USE = 2;
}
