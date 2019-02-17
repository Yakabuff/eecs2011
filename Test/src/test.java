import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("xd.txt");
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(file), true);
			Integer three = new Integer(3);
			pw.println("Integer 1");
			pw.println("Integer 2");
			pw.println(three.getClass().getSimpleName() + " " + three.toString());
			pw.println("Integer 3");
			
			
			
			Scanner scanner = new Scanner(file);
			
			
			String text = scanner.useDelimiter("\\A").next();
			text = text.replaceFirst(three.getClass().getSimpleName() + " " + three.toString() + "\r\n", "");
			
			Scanner skipper = new Scanner(text);
			
			//skipper.skip("Integer 1");
			//Scanner find = new Scanner(text);
			//String found = find.findInLine("Integer 1");
			
			System.out.println(text);
			PrintWriter pd = new PrintWriter(new FileOutputStream(file), true);
			pd.println(text);
			//System.out.println(" " + skipper.nextLine() + skipper.nextLine());
			
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				text = text +line + "\r\n" ;
//			}
			
			
			List<String> lines = Files.readAllLines(Paths.get("xd.txt"), StandardCharsets.UTF_8);
			
			lines.add(2, three.getClass().getSimpleName() +" "+three.toString());
			String theFile = String.join("\r\n", lines);
			
			PrintWriter pc = new PrintWriter(new FileOutputStream(file), true);
			pc.println(theFile);
			
		} catch (IOException e) {
			
		}
		
	}

}
