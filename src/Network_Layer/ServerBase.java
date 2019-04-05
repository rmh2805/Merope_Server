package Network_Layer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ServerBase implements Runnable {
    private List<ClientSocket> clients;
    private ServerSocket serverSocket;

    public ServerBase(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new LinkedList<>());
        new Thread(this).start();
        System.out.println(String.format("Notice: Server started, listening on port %d", port));
    }


    @Override
    public void run() {
        while (true) {
            try {
                ClientSocket client = new ClientSocket(serverSocket.accept());
                clients.add(client);
                System.out.println("Notice: New client connected");
            } catch (IOException e) {
                System.out.println("***ERROR:Client connection failed***");
            }
        }
    }

    public ClientSocket getClient(int idx) {
        return clients.get(idx);
    }

    public ClientSocket removeClient(int idx) {
        return clients.remove(idx);
    }
}
