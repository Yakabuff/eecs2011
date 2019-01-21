import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class tester {
	
	static String a = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileList list = new FileList("file1.txt");
		
		

//beginning
		list.add(0,1);
		list.add(0,2);
		list.add(0,3);
		list.add(0,4);
		list.add(0,5);
		list.add(0,6);
		
		Integer i = new Integer(2);
		Integer j = new Integer(3);
		list.remove(i);
		list.remove(j);
		list.clear();
		
		list.add(0,1);
		list.add(1,2);
		list.add(2,3);
		list.add(3,4);
		
		list.remove(1);
		list.remove(2);
		
		list.clear();
		
		
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
