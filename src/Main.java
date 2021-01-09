import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        new Main().runServer();
    }

    private void runServer() throws IOException {
        System.out.println("Server started");
        serverSocket = new ServerSocket(8887);
        acceptRequest();
    }

    private void acceptRequest() throws IOException {
        while(true){
            Socket s = serverSocket.accept();
            ConnectionHandler ch = new ConnectionHandler(s);
            ch.start();
        }
    }

}
