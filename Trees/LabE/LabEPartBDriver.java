

public class LabEPartBDriver {

	public static void main(String[] args) 	{
		String brent = "Brent";
		String donna = "Donna";
		String jerry = "Jerry";
		String luke  = "Luke";
		String sue   = "Sue";
		String tom   = "Tom";
		String missy = "Missy";
		String abby = "Abby";
		String zeke = "Zeke";
			
		// TEST ADD, IS EMPTY, AND GET LENGTH
		SortedListInterface<String> myList = new BSTSortedList<String>();
		System.out.println("The list starts empty (" + myList.isEmpty() + ")");
		myList.add(luke);
		myList.add(brent);
		myList.add(donna);
		myList.add(tom);
		myList.add(sue);
		myList.add(jerry);
		
		System.out.println("The sorted list should contain\nBrentDonnaJerryLukeSueTom:");
		myList.display();
		System.out.println();
		System.out.println("This is 6 entries: " + myList.getLength());
		System.out.println("The list is empty? " + myList.isEmpty() + " full? " + myList.isFull());
		System.out.println();
		
		// TEST GETENTRY
		System.out.println("Brent is at position 1: " + myList.getEntry(1));
		System.out.println("Donna is at position 2: " + myList.getEntry(2));
		System.out.println("Jerry is at position 3: " + myList.getEntry(3));
		System.out.println("Luke is at position 4: " + myList.getEntry(4));
		System.out.println("Sue is at position 5: " + myList.getEntry(5));
		System.out.println("Tom is at position 6: " + myList.getEntry(6));
		System.out.println("No one (null) is in position 0: " + myList.getEntry(0));
		System.out.println("No one (null) is in position 7: : " + myList.getEntry(7));
		System.out.println();
		
		// TEST GETPOSITION
		System.out.println("Brent is at position 1: " + myList.getPosition(brent));
		System.out.println("Donna is at position 2: " + myList.getPosition(donna));
		System.out.println("Jerry is at position 3: " + myList.getPosition(jerry));
		System.out.println("Luke is at position 4: " + myList.getPosition(luke));
		System.out.println("Sue is at position 5: " + myList.getPosition(sue));
		System.out.println("Tom is at position 6: " + myList.getPosition(tom));
		System.out.println("Missy should be at position -5: " + myList.getPosition(missy));
		System.out.println("Abby should be at position -1: " + myList.getPosition(abby));
		System.out.println("Zeke should be at position -7: " + myList.getPosition(zeke));
		System.out.println();

		// TEST CONTAINS
		System.out.println("Is Brent in list? " + myList.contains(brent));
		System.out.println("Is Luke in list?  " + myList.contains(luke));
		System.out.println("Is Missy in list? " + myList.contains(missy));
		System.out.println("Is Abby in list? " + myList.contains(abby));
		System.out.println("Is Zeke in list? " + myList.contains(zeke));
		System.out.println();

		// TEST REMOVE
		System.out.println("Removing first item (Brent): " + myList.remove(brent));
		System.out.println("After removing Brent, list contains " + myList.getLength() + " items.");
		System.out.println("The sorted list should contain\nDonnaJerryLukeSueTom:");
		myList.display();
		System.out.println();
		
		System.out.println("Removing interior item (Luke): " + myList.remove(luke));
		System.out.println("After removing Luke, list contains " + myList.getLength() + " items.");
		System.out.println("The sorted list should contain\nDonnaJerrySueTom:");
		myList.display();
		System.out.println();
		
		System.out.println("Removing last item (Tom): " + myList.remove(tom));
		System.out.println("After removing last item, list contains " +  myList.getLength() + " items.");
		System.out.println("The sorted list should contain\nDonnaJerrySue:");
		myList.display();
		System.out.println();

		System.out.println("Removing a missing item (Missy): " + myList.remove(missy));
		System.out.println("The list contains " + myList.getLength() +                  " items.");
		System.out.println("The sorted list should contain\nDonnaJerrySue:");
		myList.display();
		System.out.println();
		
		// TEST CLEAR
		myList.clear();
		System.out.println("\nThe list is empty after invoking clear? " + myList.isEmpty());
	} 
} 


