package Commands.Server;

import Commands.Command;

public class List_Get extends Command {

    String[] usernames;

    private static String makeList(String[] list){
        String toReturn = list[0];
        for (int i = 1; i<list.length; i++) {
            Command.concat(toReturn, list[i]);
        }
        return toReturn;
    }

    public List_Get(String[] usernames) {
        super(Command.LIST_GET, makeList(usernames));
        this.usernames = usernames;
    }

    public String[] getUsernames() {
        return usernames;
    }
}
