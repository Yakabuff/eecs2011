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
		
		myList.add(1);
		myList.add(2.2);
		myList.add(1,-3);
		myList.add(1,-4);
		
		Integer i = new Integer(1);
		myList.remove(i);
		
		Integer j = new Integer(-3);
		myList.remove(j);
		
		

		
		

		
		
		

		
	}

}
