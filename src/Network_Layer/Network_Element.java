package Network_Layer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Network_Element {
    protected Socket socket;
    protected PrintWriter writer;
    protected Scanner reader;

    static final String k_New_Line = ":n:";

    public Network_Element(Socket socket) throws IOException {
        this.setSocket(socket);
    }

    protected void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream());
        this.reader = new Scanner(socket.getInputStream());
    }

    private String nextLine() {
        String toReturn = null;
        if (reader.hasNext()) {
            toReturn = reader.nextLine().replaceAll(k_New_Line, "\n") + "\n";
        }

        return toReturn;
    }

    private void writeLine(String msg) {
        writer.write(msg.replaceAll("\n", k_New_Line)+"\n");
        writer.flush();
    }
}
