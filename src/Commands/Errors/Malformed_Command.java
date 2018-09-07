package Commands.Errors;

import Commands.Command;

public class Malformed_Command extends Command {
    private String command;

    public Malformed_Command(String command, String message) {
        super(Command.MALFORMED_COMMAND, concat(command, message));
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
