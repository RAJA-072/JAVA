import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ConcurrentServerClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number : ");
        int count = input.nextInt();

        PrintStream outStream = new PrintStream(socket.getOutputStream(), true);
        outStream.println(count);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String output = reader.readLine();

        System.out.println("Sum of numbers upto " + count + " :" + output);
        socket.close();

    }
}