package Commands.Server;

import Commands.Command;

public class Change_Ack extends Command {
    private String newName;

    public Change_Ack(String newName) {
        super(Command.CHANGE_ACK, newName);
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
