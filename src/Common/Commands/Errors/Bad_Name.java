package Common.Commands.Errors;

import Common.Commands.Command;

public class Bad_Name extends Command {
    private String name;

    public Bad_Name(String name) {
        super(Command.BAD_NAME, name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
