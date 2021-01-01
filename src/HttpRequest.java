
public class HttpRequest {

    int byteCount = -1;

    public HttpRequest(String request) {

        //First Spliting Level
        String lines[] = request.split("\n");

            try {
                String byteStr = lines[0].split(" ")[1];
                byteCount = Integer.parseInt(byteStr.substring(1));

            } catch (Exception e) {

            }
        }
}
