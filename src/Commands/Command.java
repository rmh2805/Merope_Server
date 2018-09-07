package Commands;

public abstract class Command {
    //-----------------------------------------<Fields>------------------------------------------//
    //--------------------------------------<Statics>---------------------------------------//
    public static String separator = "::";

    //--------------------------------------<Private>---------------------------------------//
    private String tag;
    private String payload;

    //-----------------------------------------<Methods>-----------------------------------------//
    //--------------------------------------<Statics>---------------------------------------//

    /**
     * Concatenates the two strings with the separator between them for parsing
     *
     * @param str1 The first string to concatenate
     * @param str2 The second string to concatenate
     * @return The concatenated message with the separator b/n the two
     */
    protected static String concat(String str1, String str2) {
        return str1 + separator + str2;
    }

    //Client Command Tags
    public static String LOG_ON = "LOG_ON";
    public static String BROADCAST_SEND = "BROADCAST_SEND";
    public static String WHISPER_SEND = "WHISPER_SEND";
    public static String LIST_REQUEST = "LIST_REQUEST";
    public static String CHANGE_NAME = "CHANGE_NAME";
    public static String LOG_OFF = "LOG_OFF";

    //Server Command Tags
    public static String LOGGED_ON = "LOGGED_ON";
    public static String BROADCAST_GET = "BROADCAST_GET";
    public static String WHISPER_SENT = "WHISPER_SENT";
    public static String WHISPER_GET = "WHISPER_GET";
    public static String LIST_GET = "LIST_GET";
    public static String CHANGE_ACK = "CHANGE_ACK";
    public static String LOGGED_OFF = "LOGGED_OFF";

    //Error Tags
    public static String BAD_NAME = "BAD_NAME";
    public static String BAD_TARGET = "BAD_TARGET";
    public static String NOT_LOGGED_ON = "NOT_LOGGED_ON";
    public static String MALFORMED_COMMAND = "MALFORMED_COMMAND";

    //---------------------------------------<Public>---------------------------------------//

    /**
     * Creates a new 'Command'
     *
     * @param tag     Tells us what kind of command it is for decoding on the other side
     * @param payload The data attached to the command, e.g. the actual message to pass along, the recipient, etc.
     */
    public Command(String tag, String payload) {
        this.tag = tag;
        this.payload = payload;
    }

    /**
     * Returns the tag to tell us what kind of command it is
     *
     * @return The command's tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets the command as a string properly formatted for transmission and decoding
     *
     * @return A properly formatted String representation of the command ready for transmission and decoding
     */
    public String toString() {
        if (payload.isEmpty()) {
            return tag;
        } else {
            return concat(tag, payload);
        }
    }

    //--------------------------------------<Private>---------------------------------------//
}
