package Common.Commands.Server;

import Common.Commands.Command;

public class Logged_On extends Command {
    private String name;

    public Logged_On(String name) {
        super(Command.LOGGED_ON, name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
