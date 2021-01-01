import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Main {
    ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the port number");
        int port = sc.nextInt();
        new Main().runServer(port);

    }

    private void runServer(int port) throws IOException {
        System.out.println("Server started");
        serverSocket = new ServerSocket(port);
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
