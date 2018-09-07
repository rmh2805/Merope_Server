package Commands.Client;

import Commands.Command;

public class Log_Off extends Command {
    private String name;

    public Log_Off(String name) {
        super(LOG_OFF, name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
