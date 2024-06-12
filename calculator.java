import java.awt.*;
import java.awt.event.*;
public class Demo extends Frame implements ActionListener{

Button add,sub,mul;
TextField t1,t2;
Label la;
Demo(){
setLayout(null);
add=new Button("Addition");
sub=new Button("Multiply");
mul=new Button("Subtract");
la=new Label();
t1=new TextField(20);
t2=new TextField(20);
add(add);
add(sub);
add(mul);
add(la);
add(t1);
add(t2);
add.addActionListener(this);
sub.addActionListener(this);
mul.addActionListener(this);
la.setBounds(0,0,300,70);
t1.setBounds(25,100,100,30);
t2.setBounds(175,100,100,30);
add.setBounds(20,150,60,30);
sub.setBounds(120,150,60,30);
mul.setBounds(220,150,60,30);
setSize(300,300);
setVisible(true);}

 public void ActionPerformed(ActionEvent ae){
int x1=Integer.parseInt(t1.getText());
int x2=Integer.parseInt(t2.getText());
if(ae.getActionCommand()=="Addition"){
la.setText(String.valueOf(x1+x2));}
if(ae.getActionCommand()=="Subtract"){
la.setText(String.valueOf(x1-x2));}
if(ae.getActionCommand()=="Multiply"){
la.setText(String.valueOf(x1*x2));}
}

public static void main(String args[]){
new Demo();}}