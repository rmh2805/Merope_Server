package Common.Commands;

import Common.Commands.Client.LogonRequest;
import Common.Error.Merope_Exception;

public abstract class Command {
    //------------------------------------------<Spacing Values>------------------------------------------//
    public static final String k_Separator = "::";

    //-------------------------------------------<Client Tags>--------------------------------------------//
    public static final String k_Logon_Request = "LOGONREQUEST";

    //-------------------------------------------<Server Tags>--------------------------------------------//


    public static String CommandCat(String str1, String str2) {
        return str1 + k_Separator + str2;
    }

    public static Command CommandFromString(String in) throws Merope_Exception {
        String[] data = in.split(k_Separator, 2);

        //TODO make a Switch statement for all possible command sub-types, throwing if bad command
        switch (data[0]) {
            case (k_Logon_Request):
                return new LogonRequest(data[1]);

        }

        return null;
    }

    private String tag;
    private String payload;

    public Command(String tag, String payload) {
        this.tag = tag;
        this.payload = payload;
    }

    public String getTag() {
        return tag;
    }

    public String getPayload() {
        return payload;
    }

    public String toString() {
        return CommandCat(tag, payload);
    }
}
