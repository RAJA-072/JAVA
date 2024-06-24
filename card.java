import java.awt.*;
import java.awt.event.*;

class ListDemo extends Frame implements ActionListener
{
	List li;
	Label la;
ListDemo()
{
	li=new List(5,false);
	la=new Label("");
	li.add("item1");
	li.add("item2");
	li.add("item3");
	li.add("item4");
	li.add("item5");
	add(li);
	add(la);
	li.setBounds(30,80,100,90);
	la.setBounds(30,200,100,30);
	li.addActionListener(this);
	setSize(300,300);
	setLayout(null);
	setVisible(true);
}
public void actionPerformed(ActionEvent ae)
{
	la.setText(li.getSelectedItem());
}
public static void main(String s[])
{
	new ListDemo();
}
}