import java.net.*;
import java.io.*;

public class ConcurrentServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client: " + clientSocket);
            ServerThread serverThread = new ServerThread(clientSocket);
            serverThread.start();
        }

    }
}

class ServerThread extends Thread {
    Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int count = Integer.parseInt(reader.readLine());
            int sum = 0;
            for (int i = 1; i <= count; i++) {
                sum += i;
                Thread.sleep(200);
            }

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(sum);
            clientSocket.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}