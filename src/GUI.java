import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	
	public GUI(String frameName) {
		super(frameName);
		makeFrame(frameName);
	}
	
	private AddressBook aBook;
	
	private void makeFrame(String frameName)
	{
		FlowLayout flow = new FlowLayout();
		
		frame = new JFrame(frameName);
		panel = new JPanel();
		frame.add(this.panel);
		frame.setLayout(flow);
		frame.setVisible(true);
		
		JButton createButton = new JButton("Create New AddressBook");
		JButton saveButton = new JButton("Save AddressBook");
		JButton addButton = new JButton("Add Buddy");

		JLabel bN = new JLabel("Enter buddy name:");
		JTextField buddyName = new JTextField();
		buddyName.setColumns(10);
		JLabel bA = new JLabel("Enter buddy address:");
		JTextField buddyAddress = new JTextField();
		buddyAddress.setColumns(10);
		JLabel bP = new JLabel("Enter buddy phone number:");
		JTextField buddyPhoneNumber = new JTextField();
		buddyPhoneNumber.setColumns(10);
		
		frame.add(createButton);
		frame.add(saveButton);
		frame.add(addButton);
		frame.add(bN);
		frame.add(buddyName);
		frame.add(bA);
		frame.add(buddyAddress);
		frame.add(bP);
		frame.add(buddyPhoneNumber);
		frame.pack();
		
		createButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				aBook = new AddressBook();
				System.out.println("Book created.");
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				/*String s = "";
				for (BuddyInfo b : aBook.getBuddies()) {
					s += ("Buddy added: " + b.getName() + " " + b.getAddress() + " " + b.getPhoneNumber());
				}
				BufferedWriter out = new BufferedWriter(new FileWriter("myFile.txt"));
				out.write(s);
				out.close();*/
				System.out.println("Book saved.");
			}
		});
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String name = buddyName.getText();
				String address = buddyAddress.getText();
				int phone_number = Integer.parseInt(buddyPhoneNumber.getText());
				BuddyInfo buddy = new BuddyInfo(name, address, phone_number);
				System.out.println("Buddy added: " + buddy.getName() + " " + buddy.getAddress() + " " + buddy.getPhoneNumber());
				aBook.addBuddy(buddy);
				for (BuddyInfo b : aBook.getBuddies()) {
					System.out.println(b.getName());
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		GUI g = new GUI("BuddyInfo");
	}
}
