import java.util.ArrayList;
import java.util.List;


public class AddressBook {
	private List<BuddyInfo> buddies;
	
	
	public AddressBook(){
		buddies = new ArrayList<BuddyInfo>();
	}
	
	public void addBuddy(BuddyInfo aBuddyInfo){
		if (aBuddyInfo != null){
			buddies.add(aBuddyInfo);
		}
	}
	
	public BuddyInfo removeBuddy(int i){
		if (i >= 0 && i < buddies.size()){
			return buddies.remove(i);
		}
		return null;
	}
	
	public List<BuddyInfo> getBuddies() {
		return buddies;
	}
	
	public static void main(String args[]){
		/*
		BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", 1234);
		AddressBook addressBook = new AddressBook();
		addressBook.addBuddy(buddy);
		addressBook.removeBuddy(0);
		addressBook.addBuddy(buddy);
		addressBook.removeBuddy(0);
		System.out.println(buddy.getName() + " " + buddy.getAddress() + " " + buddy.getPhoneNumber());
		*/
	}
}