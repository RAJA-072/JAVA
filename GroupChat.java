import java.net.*;
import java.io.*;
import java.util.*;

public class GroupChat {
    static String name;
    static boolean finished = false;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the host name or IP address of the group: ");
        String host = input.nextLine();
        InetAddress group = InetAddress.getByName(host);
        System.out.println("Enter the port number: ");
        int port = input.nextInt();
        input.nextLine(); // Consume newline character

        MulticastSocket socket = new MulticastSocket(port);
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        socket.joinGroup(new InetSocketAddress(group, port), networkInterface);

        System.out.println("Enter your name: ");
        name = input.nextLine().trim(); // Trim whitespace from the name

        GroupThread thread = new GroupThread(socket, group, port);
        thread.start();

        System.out.println("Start typing messages");

        while (true) {
            String message;
            message = input.nextLine();
            if (message.equalsIgnoreCase("Exit")) {
                finished = true;
                // Leave the multicast group and close the socket
                socket.leaveGroup(new InetSocketAddress(group, port), networkInterface);
                socket.close();
                break; // Exit the loop
            }

            message = name + ": " + message;
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
            socket.send(packet);
        }
    }
}

class GroupThread extends Thread {
    MulticastSocket socket;
    InetAddress group;
    int port;

    public GroupThread(MulticastSocket socket, InetAddress group, int port) {
        this.group = group;
        this.socket = socket;
        this.port = port;
    }

    public void run() {
        while (!GroupChat.finished) {
            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
            try {
                socket.receive(packet);
                String message = new String(buffer, 0, packet.getLength(), "UTF-8");
                if (!message.startsWith(GroupChat.name)) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}