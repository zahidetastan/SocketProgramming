import java.util.ArrayList;
import java.util.Arrays;

public class HttpRequest {

    int byteCount = -1;
    String[] gt = {"POST", "GET","PUT" ,"PATCH", "DELETE", "COPY","HEAD","OPTIONS","LINK","UNLINK","PURGE","LOCK","UNLOCK","PROPFIND","VIEW"};
    ArrayList<String> generalTypes;
    String type;

    public HttpRequest(String request) {
        generalTypes = new ArrayList<String>(Arrays.asList(gt));
        //First Spliting Level
        String lines[] = request.split("\n");

        try {
                String byteStr = lines[0].split(" ")[1];
                byteCount = Integer.parseInt(byteStr.substring(1));

                String requestType = lines[0].split(" ")[0];
                System.out.println(requestType);
                type = requestType;

            } catch (Exception e) {

            }
        }
}
