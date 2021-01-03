
public class HttpResponse {

    HttpRequest request;
    String response;

    public HttpResponse(HttpRequest request) {

        this.request = request;
        String body = "";

        if(!request.generalTypes.contains(request.type)){
            response = "HTTP/1.0 400 \r\n";
            response += "Server: Our server/1.0 \r\n";
            response += "Content-Type: text/html \r\n"; //response is in html format
            response += "Connection: close \r\n";
            response += "Content-Length: 0 \r\n"; //length of response file
            response += "\r\n"; //after blank line we have to append file data
        }
        else if(!request.type.equals("GET")){
            response = "HTTP/1.0 501 \r\n";
            response += "Server: Our server/1.0 \r\n";
            response += "Content-Type: text/html \r\n"; //response is in html format
            response += "Connection: close \r\n";
            response += "Content-Length: 0 \r\n"; //length of response file
            response += "\r\n"; //after blank line we have to append file data
        }
        else if(request.byteCount < 100 || request.byteCount > 20000){
            response = "HTTP/1.0 400 \r\n";
            response += "Server: Our server/1.0 \r\n";
            response += "Content-Type: text/html \r\n"; //response is in html format
            response += "Connection: close \r\n";
            response += "Content-Length: 0 \r\n"; //length of response file
            response += "\r\n"; //after blank line we have to append file data

            System.out.println("**********"+response);
        }
        else if(request.byteCount >= 100 && request.byteCount <= 20000) {
            for (int i = 0; i < request.byteCount - 76; i++) {
                body += "a";
            }
            String html = "<HTML>" +
                    "<HEAD>" +
                    "<TITLE> I am " + request.byteCount + " bytes long</TITLE>" +
                    "<BODY>" + body + "</BODY>" +
                    "</HEAD>" +
                    "</HTML>";

            response = "HTTP/1.0 200 \r\n"; //version of http and 200 is status code which means all okay
            response += "Server: Our server/1.0 \r\n";
            response += "Content-Type: text/html \r\n"; //response is in html format
            response += "Connection: close \r\n";
            response += "Content-Length: " + html.length() + " \r\n"; //length of response file
            response += "\r\n"; //after blank line we have to append file data

            response += html;
            System.out.println(response);
        }
    }
}
