
public class HttpResponse {

    HttpRequest request;
    String response;

    public HttpResponse(HttpRequest request) {
        System.out.println("ProxyServer response message is: ");
        this.request = request;
            response = "HTTP/1.0 414 \r\n"; //version of http and 200 is status code which means all okay
            response += "Server: Our server/1.0 \r\n";
            response += "Connection: close \r\n";
            response += "Content-Length: " + 0 + " \r\n"; //length of response file
            response += "\r\n"; //after blank line we have to append file data

            System.out.println(response);
    }
}
