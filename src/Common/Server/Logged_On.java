package Common.Server;

import Common.Command;

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
