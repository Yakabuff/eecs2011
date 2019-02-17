import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class tester {
	
	static String a = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileList list = new FileList("file_1.txt");
		
		

//beginning

		
		Integer i = new Integer(2);
		Integer j = new Integer(3);

		
		list.add(0,20.0F);
		list.add(1,2);
		list.add(2,3);
		list.add(3,20.0F);
		list.add(4,5);
		list.add(5,6);
		list.add(6,7);
		list.add(7,8);
		list.add(8,9);
		list.add(9,10);
		
		list.remove(9);
		//list.remove(0);
		list.remove(8);
		list.remove(7);
		list.remove(6);
		list.remove(5);
		
		list.remove(20.0F);
		
		List arrayList = new ArrayList();
		list.add(0, 6e0);
		
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
