import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class test {

	public static <E> void main(String[] args){
		// TODO Auto-generated method stub
		
		FileList myList = new FileList("filename.txt");
		System.out.println(myList.fileName);
		
		


		System.out.println("...................");
		
		myList.add(4);
		myList.add(null);
		myList.add(6e0);
		System.out.println(myList.elements[0]);
		System.out.println(myList.elements[1]);
		System.out.println(myList.elements[2]);
		
		
		myList.remove(null);
		System.out.println("...................");
		System.out.println(myList.elements[0]);
		System.out.println(myList.elements[1]);
		//System.out.println(myList.elements[2]);
		
		
		//File file = new File("123.list");
		
		FileList list2 = new FileList();
		
		
		
//		try {
//		
//		
//		BufferedWriter output = new BufferedWriter(new FileWriter(file));          
//		output.write("abc");
//		output.close();
//		}catch(IOException e ){
//			
//		}
//
//		try {
//		
//		FileWriter fileWriter = new FileWriter(file, true);
//		BufferedWriter output = new BufferedWriter(fileWriter);          
//		output.write(123);
//		output.close();
//		}catch(IOException e ){
//			
//		}
		
		
	}

}
