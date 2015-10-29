package metadata;

/**
 * The Constants class stores important variables as constants for later use.
 */
public class Constants {

    // Request (1xx) + Response (2xx)
    public final static short C_AUTH = 101;
    public final static short S_AUTH = 201;
    public final static short C_REGISTER = 105;
    public final static short S_REGISTER = 205;
    public final static short C_CREATE_CHARACTER = 106;
    public final static short S_CREATE_CHARACTER = 206;
    public final static short C_DISCONNECT = 102;
    public final static short S_DISCONNECT = 202;
    public final static short C_GO_TO_CHARACTER_SELECTION = 103;
    public final static short S_GO_TO_CHARACTER_SELECTION = 203;
    public final static short C_SELECT_CHARACTER = 104;
    public final static short S_SELECT_CHARACTER = 204;
    public final static short C_MOVE = 110;
    public final static short S_MOVE = 210;
    public final static short S_PLAYER_LOGOUT = 212;
    public final static short C_CHAT = 111;
    public final static short S_CHAT = 211;
    public final static short C_HEARTBEAT = 120;
    public final static short S_HEARTBEAT = 220;
    public final static short C_SAVE_EXIT_GAME = 119;
    public final static short S_SAVE_EXIT_GAME = 219;
    public final static short S_SPAWN = 213;

    //Test Request + Response
    public final static short RAND_INT = 1;
    public final static short RAND_STRING = 2;
    public final static short RAND_SHORT = 3;
    public final static short RAND_FLOAT = 4;
    // Other
    public static final int SAVE_INTERVAL = 60000;
    public static final String CLIENT_VERSION = "1.00";
    public static final int TIMEOUT_SECONDS = 3;
    
    
    // Login flags
    public static final int LOGIN_FAIL = 0;
    public static final int LOGIN_SUCCESS = 1;
    public static final int ERROR_ACCOUNT_NOT_FOUND = 0;
    public static final int ERROR_WRONG_PASSWORD = 1;
    public static final int ERROR_ACCOUNT_IS_IN_USE = 2;
    
    // Chat flags
    public static final int CHAT_GLOBAL = 0;
    public static final int CHAT_PRIVATE = 1;
    public static final int CHAT_FAIL = 2;
    
    // Character selection flags
    public static final int CHARACTER_SELECTION_FAIL = 0;
    public static final int CHARACTER_SELECTION_SUCCESS = 1;
    
    // Registration flags
    public static final int REGISTRATION_FAIL = 0;
    public static final int REGISTRATION_SUCCESS = 1;
    public static final int REGISTRATION_ACCOUNT_ALREADY_EXIST = 0;
    public static final int REGISTRATION_INCORRECT_INPUT = 1;
    
    // Character creation flags
    public static final int CHARACTER_CREATION_FAIL = 0;
    public static final int CHARACTER_CREATION_SUCESS = 1;
    public static final int CHARACTER_CREATION_SLOTS_FULL = 0;
    public static final int CHARACTER_CREATION_NAME_TAKEN = 1;

    // Spawn flags
    public static final int SPAWN_PLAYER = 0;
    public static final int SPAWN_NPC = 1;
    public static final int SPAWN_MAIN_PLAYER = 2;
    
    // Gamestates
    public static final int GAMESTATE_NOT_LOGGED_IN = 0;
    public static final int GAMESTATE_LOGGED_IN = 1;
    public static final int GAMESTATE_PLAYING = 2;
    // DEBUG
    public static final boolean DEBUG = true;
}
