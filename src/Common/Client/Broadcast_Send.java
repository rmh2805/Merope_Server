package Common.Client;

import Common.Command;

public class Broadcast_Send extends Command {
    private String message;

    public Broadcast_Send(String message) {
        super(Command.BROADCAST_SEND, message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
