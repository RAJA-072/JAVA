import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3328);
            System.out.println("Time Server started, waiting for client...");

          
  while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(getCurrentDateTime());
clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
