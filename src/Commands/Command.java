package Commands;

import Commands.Client.*;
import Commands.Errors.Bad_Name;
import Commands.Errors.Bad_Target;
import Commands.Errors.Malformed_Command;
import Commands.Errors.Not_Logged_On;
import Commands.Server.*;

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
    public final static String BROADCAST_SEND = "BROADCAST_SEND";
    public final static String CHANGE_NAME = "CHANGE_NAME";
    public final static String LIST_REQUEST = "LIST_REQUEST";
    public final static String LOG_OFF = "LOG_OFF";
    public final static String LOG_ON = "LOG_ON";
    public final static String WHISPER_SEND = "WHISPER_SEND";

    //Server Command Tags
    public final static String BROADCAST_GET = "BROADCAST_GET";
    public final static String CHANGE_ACK = "CHANGE_ACK";
    public final static String LIST_GET = "LIST_GET";
    public final static String LOGGED_OFF = "LOGGED_OFF";
    public final static String LOGGED_ON = "LOGGED_ON";
    public final static String WHISPER_GET = "WHISPER_GET";
    public final static String WHISPER_SENT = "WHISPER_SENT";

    //Error Tags
    public final static String BAD_NAME = "BAD_NAME";
    public final static String BAD_TARGET = "BAD_TARGET";
    public final static String NOT_LOGGED_ON = "NOT_LOGGED_ON";
    public final static String MALFORMED_COMMAND = "MALFORMED_COMMAND";

    //--------------------------------------<Statics>---------------------------------------//
    public static Command mkCommand(String received) {
        String[] data = received.trim().split(Command.separator, 2);
        if (data.length == 2) {
            String[] subData = data[1].split(separator, 2);
            switch (data[0]) {
                case BROADCAST_SEND:
                    return new Broadcast_Send(data[1]);
                case CHANGE_NAME:
                    return new Change_Name(data[1]);
                case LOG_OFF:
                    return new Log_Off(data[1]);
                case LOG_ON:
                    return new Log_On(data[1]);
                case WHISPER_SEND:
                    return new Whisper_Send(subData[0], subData[1]);
                case BAD_NAME:
                    return new Bad_Name(data[1]);
                case BAD_TARGET:
                    return new Bad_Target(data[1]);
                case MALFORMED_COMMAND:
                    return new Malformed_Command(data[1]);
                case BROADCAST_GET:
                    return new Broadcast_Get(subData[0], subData[1]);
                case CHANGE_ACK:
                    return new Change_Ack(data[1]);
                case LOGGED_ON:
                    return new Logged_On(data[1]);
                case LOGGED_OFF:
                    return new Logged_Off(data[1]);
                case WHISPER_GET:
                    return new Whisper_Get(subData[0], subData[1]);
                case WHISPER_SENT:
                    return new Whisper_Sent(subData[0], subData[1]);
                case LIST_GET:
                    return new List_Get(data[1].split(separator));
                default:
                    return new Malformed_Command(received);
            }
        } else {
            switch (data[0]) {
                case LIST_REQUEST:
                    return new List_Request();
                case NOT_LOGGED_ON:
                    return new Not_Logged_On();

            }
            return new Malformed_Command(received);
        }
    }
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
