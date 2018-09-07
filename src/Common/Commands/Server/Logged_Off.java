package Common.Commands.Server;

import Common.Commands.Command;

public class Logged_Off extends Command {
    private String name;

    public Logged_Off(String name) {
        super(Command.LOGGED_OFF, name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
