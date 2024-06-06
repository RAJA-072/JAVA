import java.net.*;
import java.io.*;

public class FileTransferServer {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(1234);
        System.out.println("Server Started. Waiting For Clients");

        while (true) {
            Socket clientSocket = socket.accept();
            System.out.println("Client Connected: " + clientSocket);

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientMessage = inFromClient.readLine();

            if (clientMessage.equals("1")) {
                receiveFile(clientSocket);
            } else if (clientMessage.equals("2")) {
                sendFile(clientSocket);
            } else {
                System.out.println("Invalid Option");
            }

            clientSocket.close();
            System.out.println("Connection closed");

        }

    }

    private static void receiveFile(Socket clientSocket) throws Exception {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String fileName = inFromClient.readLine();
        System.out.println("Receiving file: " + fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int byteRead;

        InputStream inputStream = clientSocket.getInputStream();

        while ((byteRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, byteRead);
        }

        fileOutputStream.close();
        System.out.println("File successfully received");

    }

    private static void sendFile(Socket clientSocket) throws Exception {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String fileName = inFromClient.readLine();
        File sendFile = new File(fileName);

        if (!sendFile.exists()) {
            System.out.println("File Doesn't Exist");
            return;
        }

        System.out.println("Sending File: " + fileName);
        FileInputStream fileInputStream = new FileInputStream(sendFile);
        OutputStream outputStream = clientSocket.getOutputStream();

        byte[] buffer = new byte[1024];
        int byteRead;

        while ((byteRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, byteRead);
        }

        fileInputStream.close();
        System.out.println("File sent Successfully");
    }

}