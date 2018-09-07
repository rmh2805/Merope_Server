package Common.Errors;

import Common.Command;

public class Bad_Target extends Command {
    private String name;

    public Bad_Target(String name) {
        super(Command.BAD_TARGET, name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}