package Network_Layer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server_Base implements Runnable {
    private List<Client_Socket> clients;
    private ServerSocket serverSocket;

    public Server_Base(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new LinkedList<>());

    }


    @Override
    public void run() {
        while (true) {
            try {
                Client_Socket client = new Client_Socket(serverSocket.accept());
                clients.add(client);
                System.out.println("Notice: New client connected");
            } catch (IOException e) {
                System.out.println("***ERROR:Client connection failed***");
            }
        }
    }

    public Client_Socket getClient(int idx) {
        return clients.get(idx);
    }

    public Client_Socket removeClient(int idx) {
        return clients.remove(idx);
    }
}
