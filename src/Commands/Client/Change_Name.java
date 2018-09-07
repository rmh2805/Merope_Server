package Commands.Client;

import Commands.Command;

public class Change_Name extends Command {
    private String newName;

    public Change_Name(String newName) {
        super(Command.CHANGE_NAME, newName);
        this.newName = newName;
    }
}