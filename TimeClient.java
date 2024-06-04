import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3328);
            System.out.println("Connected to Time Server.");
 BufferedReader in = new BufferedReader(new 
InputStreamReader(socket.getInputStream()));
            String serverResponse = in.readLine();
            System.out.println("Current Date and Time from server: " + serverResponse);
socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    }}
