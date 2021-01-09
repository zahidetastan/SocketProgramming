import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConnectionHandler extends Thread {
    Socket s;
    PrintWriter pw;
    BufferedReader br;
    int wp;

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

            HttpRequest req = new HttpRequest(reqS);

            if(req.byteCount > 9999){
                HttpResponse res = new HttpResponse(req);

                pw.write(res.response.toCharArray());

                pw.close();
                br.close();
                s.close();

            }
            else {
                Socket webSocket = new Socket("localhost", 8085);

                PrintWriter wtr = new PrintWriter(webSocket.getOutputStream());
                wtr.print(reqS);
                wtr.flush();

                InputStreamReader streamReader = new InputStreamReader(webSocket.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);

                String resS = "";
                while (reader.ready() || resS.length() == 0) {
                    resS += (char) reader.read();
                }



                File[] files = new File("./cacheFiles").listFiles();
                //If this pathname does not denote a directory, then listFiles() returns null.

                for (File file : files) {
                    if (file.isFile()) {
                        String byteStr = file.getName().split("\\.")[0];
                        //System.out.println(byteStr);
                        if(String.valueOf(req.byteCount).equals(byteStr)){
                            String responseFile;
                            System.out.println("Response message was cached. It is cached response.");


                            responseFile = new String(Files.readAllBytes(Paths.get("./cacheFiles/"+byteStr+".txt")));

                            pw.write(responseFile.toCharArray());

                            System.out.println(responseFile);

                            pw.close();
                            br.close();
                            s.close();
                        }
                    }
                }


                Path path = Paths.get("./cacheFiles/"+req.byteCount+".txt");
                byte[] bytes = resS.getBytes();

                try {
                    if(req.byteCount > 0)
                    Files.write(path, bytes);
                } catch (IOException ex) {
                    // Handle exception
                }
                pw.write(resS.toCharArray());
                System.out.println(resS);


                pw.close();
                br.close();
                s.close();
            }}
            catch(java.io.IOException a){
                String response;
                System.out.println("ProxyServer response message is: ");

                response = "HTTP/1.0 404 \r\n";
                response += "Server: Our server/1.0 \r\n";
                response += "Connection: close \r\n";
                response += "Content-Length: " + 0 + " \r\n"; //length of response file
                response += "\r\n"; //after blank line we have to append file data

                pw.write(response);
                System.out.println(response);

                pw.close();
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        catch(Exception e){

        }
    }
}

