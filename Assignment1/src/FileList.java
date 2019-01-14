import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FileList<E> extends FileContainer implements List<E>{

	public E[] elements;
	private int size = 0;
	BufferedWriter output;
	BufferedReader input;
	File file;
	
	
	@SuppressWarnings("unchecked")
	public FileList(String fileName){
		super.fileName = fileName;
		//Check if file already exists??
		File tmpFile = new File(fileName);
		elements = (E[])new Object [size];
		
		 
		
		if(tmpFile.exists()) {
			System.out.println(true);
			//write contents of file into list??
			
		}else {
			System.out.println(false);
			file = new File(fileName);
			try {
				
				
				output = new BufferedWriter(new FileWriter(file));          

				}catch(IOException e ){
					
				}
			elements = (E[])new Object [size];
			
		
		}
		

		

	}
	
	@SuppressWarnings("unchecked")
	public FileList() {
		elements = (E[])new Object [size];
		file = new File("");
		
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
		return file.getTotalSpace();
	}
///////////////////////////////////////////////
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		this.size = size+ 1;
		E[] tempElements = elements;
		
		elements = (E[])new Object [this.size];
		
		for(int i=0; i<tempElements.length; i++) {
			elements[i] = tempElements[i];

		}
		
		
		elements[this.size-1] = e;
		return true;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		this.size = elements.length;
		
		grow();
		
		System.arraycopy(elements, index, elements, index + 1,size - index);
		elements[index] = element;
		
	}
	
	private void grow() {
		int oldSize = elements.length;
		
		int newSize = elements.length+1;
		
		elements = Arrays.copyOf(elements, newSize);

	}
	
	private void write(E e) {
		
	}
	
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		elements = (E[])new Object[0];
	}
	
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		boolean removed;
		
		if(o == null) {
			for(int i=0; i<elements.length;i++) {
				if(elements[i]==null) {
					System.arraycopy(elements, i+1, elements, i, elements.length-i-1);
					shrink();
					size--;
					return true;
				}
			}
			return true;
		}else {
			for(int i=0; i<elements.length;i++) {
				if(elements[i].equals(o)) {
					
					System.arraycopy(elements, i+1, elements, i, elements.length-i-1);
					//elements[elements.length-1] = null;
					shrink();
					size--;
					
					return true;
				}
			}
		}
		

		return false;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void shrink() {
		int oldSize = elements.length;
		
		int newSize = elements.length-1;
		elements = Arrays.copyOf(elements, newSize);
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elements.length;
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
