public class LabDPartADriver {

	public static void main(String[] args) {
		
		System.out.println("Create a queue: ");
		QueueInterface<String> myQueue = new CircularLinkedQueue<String>();
		System.out.println("\tisEmpty() returns " + myQueue.isEmpty());

		System.out.println("Add to queue to get Ann Bill Carol David Edgar Fred");
		myQueue.enqueue("Ann");
		myQueue.enqueue("Bill");
		myQueue.enqueue("Carol");
		myQueue.enqueue("David");
		myQueue.enqueue("Edgar");
		myQueue.enqueue("Fred");

		System.out.println("\tisEmpty() returns " + myQueue.isEmpty());

		System.out.println("Testing getFront and dequeue:");
		while (!myQueue.isEmpty()) {
			String front = myQueue.getFront();
			System.out.println("\t" + front + " is at the front of the queue.");

			front = myQueue.dequeue();
			System.out.println("\t" +front + " is removed from the front of the queue.");
		} 

		System.out.print("The queue should be empty: ");
		System.out.println("\tisEmpty() returns " + myQueue.isEmpty());

		System.out.println("Add to queue to get Greg Hank Ian");
		myQueue.enqueue("Greg");
		myQueue.enqueue("Hank");
		myQueue.enqueue("Ian");

		System.out.println("Testing clear:");
		myQueue.clear();

		System.out.println("\tisEmpty() returns " + myQueue.isEmpty());

		System.out.println("myQueue.getFront() returns " + myQueue.getFront());
		System.out.println("myQueue.dequeue() returns " + myQueue.dequeue()
				+ "\n");
	} 

}