package KNet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class BaseClient extends KNetProtocol {
    //-----------------------------------------<Objects>-----------------------------------------//
    //---------------------------------<Network Layer>---------------------------------//
    private Socket socket;
    //------------------------------------<Inputs>-------------------------------------//
    // Streams
    private InputStream serverIn;

    // Readers
    private Scanner serverReader;

    //------------------------------------<Outputs>------------------------------------//
    // Streams
    private OutputStream serverOut;

    // Writers
    private PrintStream serverPrinter;

    //-----------------------------------------<Fields>------------------------------------------//
    //------------------------------------<Static>-------------------------------------//
    //------------------------------------<Private>------------------------------------//


    //-----------------------------------------<Methods>-----------------------------------------//
    //------------------------------------<Public>-------------------------------------//

    /**
     * Constructor for the base client class of the KNet protocol
     *
     * @param hostName The domain name of the server to connect to
     * @param port     The port the target server is listening on
     * @throws IOException Throws an 'IOException' if it fails to connect to the desired server
     */
    public BaseClient(String hostName, int port) throws IOException {
        socket = new Socket(hostName, port);

        serverIn = socket.getInputStream();
        serverOut = socket.getOutputStream();

        serverReader = new Scanner(serverIn);
        serverPrinter = new PrintStream(serverOut);

    }

    /**
     * Sends the specified message, appended by a kNewLine character across to the Server
     *
     * @param message The message to send across
     */
    public void WriteOut(String message) {
        message = message.replaceAll("\n", KNetProtocol.kNewLine);
        serverPrinter.println(message);
        serverPrinter.flush();
    }

    public String NextIn() {
        return NextLine(serverIn, serverReader);
    }

    public String WaitForNext(long timeoutMS) {
        timeoutMS = java.lang.Math.abs(timeoutMS);
        long startTime = System.currentTimeMillis();
        String toReturn = "";
        while (toReturn.trim().isEmpty() && System.currentTimeMillis() <= startTime + timeoutMS) {
            toReturn = NextIn();
        }
        return toReturn;
    }

    public String WaitForNext() {
        return WaitForNext(KNetProtocol.kDefaultWaitTimeoutMS);
    }
}
