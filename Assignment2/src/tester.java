
public class tester {

	public static void main(String[] args) {
		
		SkipList myList = new SkipList();
		
		myList.add(0,1);

		myList.add(1,2);
		myList.add(2,3);
		myList.add(3,4);
		myList.add(1,5);
		myList.add(0,6);
//		myList.add(2);
//		myList.add(3);
//		
//		myList.add(2);
//		
//		myList.add(3);
//		
//		myList.add(4);
//		
//		myList.add(1,5);
//		myList.add(0,6);
		
		System.out.println(myList.toString());
		System.out.println(myList.height);

//		System.out.println(myList.firstHead.next.value);
		
//		myList.add(0,4);
//		System.out.println("hi");
//		System.out.println(myList.height);
//		
//		myList.add(0,4);
//		myList.add(0,5);
//		myList.add(0,5);
//		
//		System.out.println(myList.height);
//		myList.add(1,3);
//		
//		System.out.println(myList.toString());
//		
//		myList.add(1,2);
//		
//		myList.add(2,7);
//		System.out.println(myList.height);
//		System.out.println(myList.get(1));
	}

}
