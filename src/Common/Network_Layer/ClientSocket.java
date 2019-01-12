package Common.Network_Layer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static final String k_New_Line = ":n:";

    private Socket socket;
    private PrintWriter writer;
    private Scanner reader;

    /**
     * Attempt to establish a link to a server at the specified address on the specified port
     *
     * @param address The ip address/url of the server to connect to
     * @param port The port the server is listening on
     * @throws IOException Thrown if socket creation fails
     */
    public ClientSocket(String address, int port) throws IOException {
        this(new Socket(address, port));
    }

    /**
     * Constructor for a client socket
     *
     * @param socket The network socket to use
     * @throws IOException If getting the input or output stream of the socket fails
     * @throws NullPointerException If provided with a null socket
     */
    public ClientSocket(Socket socket) throws IOException, NullPointerException {
        if(socket == null){
            throw new NullPointerException("Null socket provided");
        }
        this.setSocket(socket);
    }

    /**
     * A default constructor for child classes (to make switching viable
     */
    protected ClientSocket(){
        socket = null;
        writer = null;
        reader = null;
    }

    /**
     * Set the associated socket and Initialize the writer and reader
     *
     * @param socket The network socket to communicate on
     * @throws IOException Thrown if an error is encountered grabbing the socket's streams
     */
    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream());
        this.reader = new Scanner(socket.getInputStream());
    }

    /**
     * Returns the next line (including newline) from the socket, or null if no line is available
     *
     * @return The next line (including newline) from the socket, or null if no line is available
     * @throws IOException Thrown if no socket is specified
     */
    public String nextLine() throws IOException{
        String toReturn = null;

        if(socket == null)
            throw new IOException("No socket specified");

        if (reader.hasNextLine()) {
            //Undoes the replacement from send-side
            toReturn = reader.nextLine().replaceAll(k_New_Line, "\n") + "\n";
        }

        return toReturn;
    }

    /**
     * Writes a line to the socket Input stream, then flushes
     *
     * @param msg The message to write
     * @throws IOException Thrown if no socket is provided
     */
    public void writeLine(String msg) throws  IOException{
        if(socket == null)
            throw new IOException("No socket specified");

        writer.write(msg.replaceAll("\n", k_New_Line)+"\n");    //Sanitize for sending
        writer.flush();
    }

    /**
     * Checks whether or not the reader has a new command to read in
     *
     * @return Whether or not a command is available
     */
    public boolean hasData(){
        return reader.hasNextLine();
    }
}
