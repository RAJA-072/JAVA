import java.util.*;
class MessageHolder {
    public String message;

    synchronized void setMessage(String message) {
        this.message = message;
        notify(); // Notify waiting threads that a new message is available
        try {
            wait(); // Wait for the other thread to consume the message
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized String getMessage() {
        while (message == null) {
            try {
                wait(); // Wait for a message to be available
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String receivedMessage = message;
        message = null; // Clear the message after receiving
        notify(); // Notify the sender that the message has been consumed
        return receivedMessage;
    }
}

class Person implements Runnable {
    public String name;
    public MessageHolder messageHolder;

    Person(String name, MessageHolder messageHolder) {
        this.name = name;
        this.messageHolder = messageHolder;
        new Thread(this, name).start();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(name + " - Enter message: ");
            String message = scanner.nextLine();
            messageHolder.setMessage(name + ": " + message);
        }
    }
}

 class ChatApplication {
    public static void main(String[] args) {
        MessageHolder messageHolder = new MessageHolder();
        Person person1 = new Person("Person 1", messageHolder);
        Person person2 = new Person("Person 2", messageHolder);
        System.out.println("Chat Application is running...");
        System.out.println("Type messages to send.");

        // Simulate three conversations
        for (int i = 0; i < 3; i++) {
            String conversation1 = person1.messageHolder.getMessage();
            System.out.println(conversation1);
            String conversation2 = person2.messageHolder.getMessage();
            System.out.println(conversation2);
        }
    }
}

