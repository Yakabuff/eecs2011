import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class FileList<E extends Number> extends FileContainer implements List<E>{

	public List<E> elements;
	private int size = 0;
	private BufferedReader br;
	private File file;
	private Scanner reader;
	private PrintWriter pw;
	

	private FileOutputStream fs;

	private static int fileNum;
	/**
	 * Constructs fileList with the given name checks if file already exists. 
	 * If file does not exist, create a new file. If it does, load contents of file into list
	 * @param fileName Name of file
	 */
	public FileList(String fileName){

		elements = new ArrayList<E>(0);

		file = new File(fileName);
		
		try {

			
			if(file.exists()) {
				loadIntoArray(file);
				fs = new FileOutputStream(file);
				pw = new PrintWriter(fs,true);
				System.out.println("File already exists: Loading contents into list");
				restoreFile(file);
			}else {
			fs = new FileOutputStream(file);
			pw = new PrintWriter(fs,true);
			
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.fileName = fileName;



	}
/**
 * Constructs empty file list with a counter as name. Increment number of files by 1
 */

	public FileList() {
		
		this("file"+fileNum+".txt");
		fileNum++;

		
	}

	//////////////////////////////////////////////	
	/**
	 * returns name of the file
	 */
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return super.fileName;
	}
	/**
	 * returns file size in bits
	 */
	@Override
	public long getFileSize() {
		// TODO Auto-generated method stub
		return file.length();
	}
	///////////////////////////////////////////////
	/**
	 * Loads file into list by checking for types Integer, float, byte 
	 * Double,Short. Splits the line into 2 and compares each part
	 * https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html : delimiter to remove spaces
	 * @param file
	 */
	private void loadIntoArray(File file) {
		

		String currentLine;
		try {
			br = new BufferedReader(new FileReader(file));
			while((currentLine = br.readLine()) != null) {

				String[] words = currentLine.split("\\s+");
				
				if(words[0].equals("Integer")) {
					Integer i = Integer.valueOf(words[1]);
					elements.add((E) i);
				}else if(words[0].equals("Float")) {
					Float f = Float.valueOf(words[1]);
					elements.add((E) f);
				}else if(words[0].equals("Byte")) {
					Byte b = Byte.valueOf(words[1]);
					elements.add((E) b);
				}else if(words[0].equals("Double")) {
					Double d = Double.valueOf(words[1]);
					elements.add((E) d);
				}else if(words[0].equals("Short")) {
					Short s = Short.valueOf(words[1]);
					elements.add((E) s);
				}else if(words[0].equals("Long")) {
					Long l = Long.valueOf(words[1]);
					elements.add((E) l);
				}
				

			}
		} catch (IOException e) {

		}
	}
	/**
	 * Restores file but writing contents of list into file
	 * @param file
	 */
	private void restoreFile(File file) {

		for(Number n : elements) {
			pw.println(n.getClass().getSimpleName()+" "+n.toString() );
		}
					
	}
	/**Add a generic into fileList. Return true
	 * @param e 
	 * @return true if succesfully removed
	 */
	
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		elements.add(e);
		

		pw.println(e.getClass().getSimpleName() +" "+e.toString());
		
		return true;
	}
	
	/**Takes e and insert into the specified index in the list and update the file.
	 * @param index, e
	 * @exception IOexception    
	 * https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html : readAllLines method
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/String.html : String.join method
	 */
	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub
		elements.add(index,e);
		
		
		
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);  
			
			lines.add(index, e.getClass().getSimpleName() +" "+e.toString());
			
			String file = String.join(System.lineSeparator(), lines);
			
			
			PrintWriter addIndex = new PrintWriter(new FileOutputStream(fileName),true);
			
			addIndex.println(file);
			
			
			this.pw = addIndex;

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
	
	

	/** clear the list by removing contents in the file and clearing arraylist
	 * @exception IOexception
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		elements = new ArrayList<E>(0);;

		try {
			FileOutputStream fs = new FileOutputStream(file);
			pw = new PrintWriter(fs,true);
		
		}catch (IOException e1) {

		}
	}

	/** remove first occurrence of specified object from the list
	 * @param Object o
	 * @exception IOException
	 * @return return true if successfully removed
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/String.html : replaceFirst method
	 * https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html : stringBuilder set length method
	 */
	
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		
		
		FileOutputStream fs;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			//Scanner scanner = new Scanner(file);
			o = (Number)o;
			String line = "";
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			
			
			
			String text = sb.toString();

			text = text.replaceFirst(o.getClass().getSimpleName()+" "+o.toString()+"\r\n", "");
			
			StringBuilder cut = new StringBuilder(text);
			
			cut.setLength(cut.length()-2);
			
			fs = new FileOutputStream(file);
			PrintWriter removeObj = new PrintWriter(fs,true);
			
			removeObj.println(cut.toString());
			
			this.pw = removeObj;
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}


		return elements.remove(o);


	}
	/**
	 * Returns list as a string. lists each element between brackets
	 * @return s
	 */

	@Override
	public String toString() {
		
		String s="[";
		if(elements.size()>0) {
		s = s + elements.get(0);
		
		for(int i = 1; i<elements.size();i++) {
			s =s + (", "+ elements.get(i)); 
		}
		}
		s = s + "]";
		return s;
	}
	/**
	 * removes element from list as specified by the index. Once removed, return removed element
	 * @param index
	 * @return E
	 * https://www.eecs.yorku.ca/course_archive/2018-19/W/2011N/assignments.shtml : FAQ examples 
	 */

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		E removed = elements.get(index);
		
		
		
		try {
			
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		
		StringBuilder beg = new StringBuilder("");
		StringBuilder end  = new StringBuilder("");
		
		for(int i = 0; i < elements.size(); i++) {
		    
		    if(i < index) {
		    	
		    	if(i+1 == index) {
		    		beg.append(reader.readLine());
		    	}else {
		    		beg.append(reader.readLine()+"\r\n");
		    	}
		    	
		    }else if (i > index) {

		    	if((i +1) == elements.size()) {
		    		end.append(reader.readLine());
		    	}else {
		    	end.append(reader.readLine()+"\r\n");
		    	}
		    	
		    }else if(i == index) {
		    	reader.readLine();
		    	
		    	
		    }
		}

		elements.remove(index);
		fs = new FileOutputStream(file);
		PrintWriter removeIndex = new PrintWriter(new FileOutputStream(fileName),true);
		
		beg = new StringBuilder(beg.toString().trim());
		end = new StringBuilder(end.toString().trim());
		

		if(index == 0) {
			removeIndex.println(end.toString());
		}else if(index == elements.size()) {
			removeIndex.println(beg.toString());
		}else if(index >0) {
			removeIndex.println(beg.toString());
			
			removeIndex.println(end.toString());
			
		}
		this.pw = removeIndex;
		}catch(IOException e1) {
			
		}
		
		
		return removed;
	}

	/**
	 * returns size of list
	 * @return size
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elements.size();
	}



	//////////////////////////////////////////////

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}



	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}



	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}








}
