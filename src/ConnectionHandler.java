import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    Socket s;
    PrintWriter pw;
    BufferedReader br;

    public ConnectionHandler(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw = new PrintWriter(s.getOutputStream());
    }

    public void run(){
        try{

            String reqS = "";

            while(br.ready() || reqS.length() == 0){
                reqS += (char)br.read();
            }

            System.out.println(reqS);

            //Passing the request String to Request class for processing
            HttpRequest req = new HttpRequest(reqS);

            //Sending Request Object to HttpResponse Class
            HttpResponse res = new HttpResponse(req);

            //write the final output to pw
            pw.write(res.response.toCharArray());

            pw.close();
            br.close();
            s.close();

        }catch(Exception e){

        }

    }


}

