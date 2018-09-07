package Common.Commands.Errors;

import Common.Commands.Command;

public class Malformed_Command extends Command {
    private String command;
    private String message;

    public Malformed_Command(String command, String message) {
        super(Command.MALFORMED_COMMAND, concat(command, message));
        this.command = command;
        this.message = message;
    }

    public Malformed_Command(String command){
        super(Command.MALFORMED_COMMAND, command);
        this.message = command.split(separator)[command.split(separator).length - 1];
        this.command = command.replaceFirst(message, "");
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }
}
