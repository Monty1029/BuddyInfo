import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	
	public GUI(String frameName) {
		super(frameName);
		makeFrame(frameName);
	}
	
	private AddressBook aBook;
	private BuddyInfo selectedBuddy;
	private JList<String> buddyList;
	
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
		JButton editButton = new JButton("View Buddy List");

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
		frame.add(editButton);
		frame.add(bN);
		frame.add(buddyName);
		frame.add(bA);
		frame.add(buddyAddress);
		frame.add(bP);
		frame.add(buddyPhoneNumber);
		frame.pack();
		
		editButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> editList = new DefaultListModel<String>();
				for (BuddyInfo b : aBook.getBuddies()) {
					editList.addElement(b.getName());
				}
				buddyList = new JList<String>(editList);
				
				JFrame listView = new JFrame("Buddy List");
				listView.add(buddyList);
				listView.setSize(200, 200);
				listView.setLocationRelativeTo(null);
				listView.setVisible(true);
				listView.add(new JScrollPane(buddyList));
				
				buddyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				MouseListener mouseListener = new MouseAdapter() {
				      public void mouseClicked(MouseEvent mouseEvent) {
				        JList theList = (JList) mouseEvent.getSource();
				        if (mouseEvent.getClickCount() == 2) {
				        	selectedBuddy = new BuddyInfo(buddyList.getSelectedValue(), " ", 1);
				        	JFrame editBuddy = new JFrame("Edit Buddy");
				        	FlowLayout flow = new FlowLayout();
				        	editBuddy.setLayout(flow);
				        	editBuddy.setSize(200, 500);
				        	editBuddy.setVisible(true);
				        	
				        	JButton remove = new JButton("Remove Buddy");
				        	JButton edit = new JButton("Edit Buddy");
				        	
				        	editBuddy.add(remove);
				        	editBuddy.add(edit);
				        	
				        	JLabel editName = new JLabel("Edit buddy name:");
				    		JTextField eN = new JTextField();
				    		eN.setColumns(10);
				    		JLabel editAddress = new JLabel("Edit buddy address:");
				    		JTextField eA = new JTextField();
				    		eA.setColumns(10);
				    		JLabel editPhoneNumber = new JLabel("Edit buddy phone number:");
				    		JTextField eP = new JTextField();
				    		eP.setColumns(10);
				    		
				    		editBuddy.add(editName);
				    		editBuddy.add(eN);
				    		editBuddy.add(editAddress);
				    		editBuddy.add(eA);
				    		editBuddy.add(editPhoneNumber);
				    		editBuddy.add(eP);
				    		
				    		remove.addActionListener(new ActionListener(){
				    			public void actionPerformed(ActionEvent e) {
				    				for (BuddyInfo b : aBook.getBuddies()) {
				    					if (b.getName().equals(selectedBuddy.getName())) {
				    						aBook.removeBuddy(b);
				    						System.out.println("Buddy removed.");
				    					}
				    				}
				    				System.out.println("New list of buddies:");
				    				for (BuddyInfo b : aBook.getBuddies()) {
				    					System.out.println(b.getName());
				    				}
				    			}
				    		});
				        }
				      }
				    };
				    buddyList.addMouseListener(mouseListener);
			}
		});
		
		createButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				aBook = new AddressBook();
				System.out.println("Book created.");
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String s = "";
				for (BuddyInfo b : aBook.getBuddies()) {
					s += ("Buddy added: " + b.getName() + " " + b.getAddress() + " " + b.getPhoneNumber());
				}
				BufferedWriter out;
				try {
					out = new BufferedWriter(new FileWriter("myFile.txt"));

					out.write(s);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
