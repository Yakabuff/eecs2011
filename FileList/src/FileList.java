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
	BufferedWriter bw;
	BufferedReader br;
	File file;
	Scanner reader;
	PrintWriter pw;
	//PrintWriter tmp;

	FileOutputStream fs;

	private static int fileNum;
	
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


	public FileList() {
		//this.fileName = "file"+fileNum+".txt";
		this("file"+fileNum+".txt");
		fileNum++;
		//file = new File(fileName);
		
//		try {
//			fs = new FileOutputStream(file);
//			pw = new PrintWriter(fs,true);
//			
//			
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	//////////////////////////////////////////////	
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return super.fileName;
	}

	@Override
	public long getFileSize() {
		// TODO Auto-generated method stub
		return file.length();
	}
	///////////////////////////////////////////////
	
	void loadIntoArray(File file) {
		

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
	
	void restoreFile(File file) {

		for(Number n : elements) {
			pw.println(n.getClass().getSimpleName()+" "+n.toString() );
		}
					
	}
	
	
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		elements.add(e);
		

		pw.println(e.getClass().getSimpleName() +" "+e.toString());
		
//		pw.close();
//		pw = null;
		return true;
	}


	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub
		elements.add(index,e);
		
		
		
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);  //SLOW
			
			lines.add(index, e.getClass().getSimpleName() +" "+e.toString());
			
			String file = String.join(System.lineSeparator(), lines);
			
			//fs = new FileOutputStream(file);
			PrintWriter addIndex = new PrintWriter(new FileOutputStream(fileName),true);
			
			addIndex.println(file);
			//System.out.println("printing");
			
			this.pw = addIndex;

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
	
	


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

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		
		//File tempFile = new File("tempfile.txt");
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
			//String text = scanner.useDelimiter("\\A").next();
			//text = text.replaceFirst(o.getClass().getSimpleName()+" "+o.toString()+"\r\n", "");
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
		}

	

//		try {
//
//			FileOutputStream fileTemp = new FileOutputStream(tempFile);
//
//			PrintWriter tmp = new PrintWriter(fileTemp, true);
//			br = new BufferedReader(new FileReader(file));
//
//			String currentLine;
//			while((currentLine = br.readLine()) != null) {
//
//				if(!currentLine.equals(o.getClass().getSimpleName()+" "+o.toString())){
//					System.out.println(currentLine);
//					tmp.println(currentLine);
//				}
//
//			}
//			System.gc();
//			fileTemp.close();
//			br.close();
//			pw.close();
//			
//			fs.close();
//			
//			file = new File(file, fileName);
//			
//			if(file.delete()) {
//				System.out.println("deleted");
//			}
//			
//			tempFile.renameTo(file);


//		} catch (IOException e) {

//		}



		return elements.remove(o);


	}


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


	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		E removed = elements.get(index);
		
		
		
		try {
			

		
		//pw.println(file);
		//System.out.println("printing");
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
		

		removeIndex.println(beg.toString());
		
		removeIndex.println(end.toString());
		
		this.pw = removeIndex;
		}catch(IOException e1) {
			
		}
		
		
		return removed;
	}


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
