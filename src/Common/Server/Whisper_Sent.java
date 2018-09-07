package Common.Server;

import Common.Command;

public class Whisper_Sent extends Command {
    private String target;
    private String message;

    public Whisper_Sent(String target, String message) {
        super(Command.WHISPER_SENT, Command.concat(target, message));
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

