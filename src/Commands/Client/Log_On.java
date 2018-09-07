package Commands.Client;

import Commands.Command;

public class Log_On extends Command {
    private String username;

    public Log_On(String username) {
        super(Command.LOG_ON, username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
