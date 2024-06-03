import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new 
InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
        String inputLine;
        while ((inputLine = userInput.readLine()) != null) {
            out.println(inputLine);
            System.out.println("Server response: " + in.readLine());
        }
        
        socket.close();
    }
}
