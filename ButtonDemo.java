import java.awt.*;
import java.awt.event.*;
public class ButtonDemo extends Frame implements ActionListener
{
TextField tf;
Button b1,b2,b3;
ButtonDemo(){
tf=new TextField(20);
b1=new Button("Yes");
b2=new Button("No");

b3=new Button("Exit");
add(b1);
add(b2);
add(b3);
add(tf);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b1.setBounds(100,120,80,30);
b2.setBounds(180,120,80,30);
b3.setBounds(270,120,80,30);
tf.setBounds(130,180,80,30);
setSize(400,400);
setLayout(null);
setVisible(true);

}
public void actionPerformed (ActionEvent ae)
{
String str=ae.getActionCommand();
if(str.equals("Yes")){
tf.setText("Yes");}
if(str.equals("No")){
tf.setText("No");}
if(str.equals("Exit")){
System.exit(0);}
}
public static void main(String args[]){
new ButtonDemo();
}
}