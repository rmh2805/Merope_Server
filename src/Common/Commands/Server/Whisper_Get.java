package Common.Commands.Server;

import Common.Commands.Command;

public class Whisper_Get extends Command {
    private String sender;
    private String message;

    public Whisper_Get(String sender, String message) {
        super(Command.WHISPER_GET, concat(sender, message));
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
