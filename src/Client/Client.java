package Client;

import Common.Commands.Command;
import Common.Commands.Server.Logged_On;
import Common.Merope_Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Merope_Protocol {

    static public void main(String[] args) {
        Client client;
        boolean connected = false;

        while (!connected) {
            try {
                client = new Client("127.0.0.1", 1);
                connected = true;
            } catch (IOException e) {
                System.out.println("Invalid hostname or port, please try again");
            }
        }
    }

    //-----------------------------------------<Objects>-----------------------------------------//
    //---------------------------------<Network Layer>---------------------------------//
    private Socket socket;

    //------------------------------------<Inputs>-------------------------------------//
    // Streams
    private InputStream serverIn;
    private InputStream consoleIn;

    // Readers
    private Scanner serverReader;
    private Scanner consoleReader;

    // Buffers
    private String serverInBuffer;
    private String consoleInBuffer;

    //------------------------------------<Outputs>------------------------------------//
    // Streams
    private OutputStream serverOut;

    // Writers
    private PrintStream serverPrinter;

    //-----------------------------------------<Fields>------------------------------------------//
    //------------------------------------<Static>-------------------------------------//

    //------------------------------------<Private>------------------------------------//
    private boolean connected;


    //-----------------------------------------<Methods>-----------------------------------------//
    //------------------------------------<Public>-------------------------------------//
    public Client(String hostName, int port, InputStream userInput) throws IOException {
        socket = new Socket(hostName, port);

        serverIn = socket.getInputStream();
        consoleIn = userInput;
        serverOut = socket.getOutputStream();

        serverInBuffer = "";
        consoleInBuffer = "";

        serverReader = new Scanner(serverIn);
        consoleReader = new Scanner(consoleIn);
        serverPrinter = new PrintStream(serverOut);

        connected = false;
    }

    /**
     * A default constructor, assumes that the user InputStream is the console (System.in)
     *
     * @param hostName The Host Name of the server
     * @param port     The Port the server is listening on
     * @throws IOException Thrown iff there is no host
     */
    public Client(String hostName, int port) throws IOException {
        this(hostName, port, System.in);
    }

    public String LogOn() {
        boolean loggedOn = false;
        //Do this until you log on
        String username = "";
        while (!loggedOn) {
            //Prompt user for a name
            System.out.println("Please indicate your preferred username:");
            while (username.isEmpty()) {
                username = NextConsole().trim();
            }

            //Wait for the server's response (good/bad name)
            Command response = null;
            while (response == null) {
                response = Command.mkCommand(NextServer());
            }

            // If the server says we'er logged on, save the username it got and set the
            // flag to true. Otherwise, reset the username field to an empty String.
            if (response.getTag().equals(Command.LOGGED_ON)) {
                username = response.getPayload();
                loggedOn = true;
            } else {
                username = "";
            }
        }

        return username;
    }

    //------------------------------------<Private>------------------------------------//
    private Command ReadCommand() {
        return Command.mkCommand(NextServer());
    }

    private String NextConsole() {
        return NextLine(consoleIn, consoleReader);
    }

    private String NextServer() {
        return NextLine(serverIn, serverReader);
    }
}
