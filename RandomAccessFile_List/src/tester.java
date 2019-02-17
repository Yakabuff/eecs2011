import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class tester {
	
	static String a = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileList list = new FileList("file1.txt");
		
		

//beginning

		
		Integer i = new Integer(2);
		Integer j = new Integer(3);
		
		//list.add(i);
		

		
		list.add(0,1);
		list.add(1,2);
		list.add(2,3);
		list.add(3,4);
		list.add(4,5);
		list.add(5,6);
//		list.add(6,7);
//		list.add(7,8);
//		list.add(8,9);
//		list.add(9,10);
		
		//list.remove(i);
		//list.remove(0);
		//list.remove(9);
		
		
		
		
//		FileList list2 = new FileList("123.list");
//		
//		System.out.println(list2.elements.get(0));
//		System.out.println(list2.elements.get(1));
//		System.out.println(list2.elements.get(2));
//		Short k = new Short("123");
//		list2.add(k);
//		System.out.println(list2.elements.get(3));
		//Files.lines(Paths.get("beg.txt")).limit(10).forEach(System.out::println);
//		try {
//			 Files.lines(Paths.get("beg.txt")).limit(6).forEach(line -> a = "\r\n"+line + a);
//			 
//			 System.out.println(a);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
