package Commands.Server;

import Commands.Command;

public class Broadcast_Get extends Command {
    private String sender;
    private String message;

    public Broadcast_Get(String sender, String message) {
        super(Command.BROADCAST_GET, Command.concat(sender, message));
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
