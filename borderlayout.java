import java.awt.*;
import java.awt.event.*;

class Demo extends Frame {
    Button b1, b2, b3, b4;
    Label la;

    // Corrected the method name to match the class name
    Demo() {
        setLayout(new BorderLayout());

        b1 = new Button("NORTH");
        b2 = new Button("SOUTH");
        b3 = new Button("WEST");
        b4 = new Button("EAST");
        la = new Label("CENTER");

        add(b1, BorderLayout.NORTH);
        add(b2, BorderLayout.SOUTH);
        add(b3, BorderLayout.WEST);
        add(b4, BorderLayout.EAST);
        add(la, BorderLayout.CENTER);

        setSize(300, 300);

        // Adding a window listener to handle window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String args[]) {
        new Demo();
    }
}
