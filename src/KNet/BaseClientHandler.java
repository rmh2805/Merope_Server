package KNet;

import java.net.Socket;

public class BaseClientHandler extends KNetProtocol {
    private Socket socket;

    public BaseClientHandler(Socket connection){
        socket = connection;
    }

    @Override
    public void WriteOut(String message) {

    }

    @Override
    public String NextIn() {
        return null;
    }

    @Override
    public String WaitForNext(long timeoutMS) {
        return null;
    }

    @Override
    public String WaitForNext() {
        return null;
    }
}
