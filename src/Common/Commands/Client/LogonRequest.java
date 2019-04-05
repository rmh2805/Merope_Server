package Common.Commands.Client;
import Common.Commands.Command;

public class LogonRequest extends Command {
    public LogonRequest(String username) {
        super(k_Logon_Request, username);
    }

    public String getUsername(){
        return getPayload();
    }
}
