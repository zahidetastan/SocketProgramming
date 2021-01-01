
public class HttpResponse {

    HttpRequest request;
    String response;

    public HttpResponse(HttpRequest request) {

        this.request = request;
        String body = "";

        if(request.byteCount == -1 || request.byteCount < 100 || request.byteCount > 20000){
            response = "HTTP/1.1 400 BAD REQUEST";
            System.out.println(response);
        }

        else if(request.byteCount > 100 && request.byteCount < 20000) {
            for (int i = 0; i < request.byteCount - 76; i++) {
                body += "a";
            }
            String html = "<HTML>" +
                    "<HEAD>" +
                    "<TITLE> I am " + request.byteCount + " bytes long</TITLE>" +
                    "<BODY>" + body + "</BODY>" +
                    "</HEAD>" +
                    "</HTML>";
            //To read the file for sending response

			/*HTTP/1.0 200 Document follows
			   MIME-Version: 1.0
			   Server: CERN/3.0
			   Date: Sunday, 10-Nov-96 06:54:46 GMT
			   Content-Type: text/html
			   Content-Length: 4531
			   Last-Modified: Wednesday, 16-Oct-96 10:14:01 GMT
			 */

            response = "HTTP/1.1 200 \r\n"; //version of http and 200 is status code which means all okay
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
