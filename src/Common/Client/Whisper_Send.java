package Common.Client;

import Common.Command;

public class Whisper_Send extends Command {
    private String target;
    private String message;

    public Whisper_Send(String target, String message) {
        super(Command.WHISPER_SEND, concat(target, message));
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }
}
