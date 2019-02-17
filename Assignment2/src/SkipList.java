import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class SkipList<E> implements List<E> {
	
///////////////////    NODE CLASS ///////////////////
	
	public static class Node<E> {
		
		E value;
		int width;

		Node<E> down;
		Node<E> next;
		
		public Node(E value ) {
			this.value = value;	
		}
		
		
	}
	///////////////////////////////////////////////////

	Node<E> firstHead;

	int size = 1;
	int height = 1;
	
	List<Node> levels;
	Random r = new Random();
	final double p = 0.5; //coin
	final int MAX_LEVEL = 20;	
	
	public SkipList() {
		
		levels = new ArrayList();

		firstHead = new Node(null); //first element of the top level
		firstHead.width = 1;
		
		//levels.add(firstHead);
	}
	
	//add node and keep flipping coin to determine how many high u need it
	//E is a node
	@Override
	public boolean add(E e) {
		//add e to end of bottom layer
		
		if(size == 0) {
			add(0,e);
		}else if(size == 1) {
			add(1,e);
		}else {
		add(size()-1, e);
		}
		return true;
	}
	
	
	int randomLevel() {
		int lvl = height;
		while ((r.nextDouble() < p) && (lvl < MAX_LEVEL)) {
			lvl = lvl +1;
		}
		return lvl;
	}
	
	//insert node at n index
	@Override
	public void add(int index, E e) {
		
		
		
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		int lvl = randomLevel();
		if(lvl > this.height) {
			for(int i = lvl; i> this.height ; i--) {
				Node node = new Node(null);
				node.down = firstHead;
				node.next = null;
				node.width = 1;
				firstHead = node;
			}
			this.height = lvl;
		}
	

		
		int pos = 0;
		int currentLevel = this.height;
		
		Node lastInserted = null;
		
		Node levelhead = this.firstHead;
	
		for(Node<E>x = levelhead;currentLevel >0; currentLevel-- ) {
			
			while((pos + x.width)<=index) {  //find x: node behind index u want to insert at
				//update distance travelled
				pos = pos + x.width;
				x = x.next; //navigate horizontally
			}
			
			if(currentLevel > lvl) { // if current level is above top level of column u are adding
				x.width = x.width + 1;//update the width of x
			}else {  //if currentlevel is at level your column starts
				//update nodes
				Node y = new Node(e);
				Node z = x.next;
				
				
				y.next = z;
				x.next = y;	
				
				y.width = pos + x.width - index;
			    x.width = index + 1 - pos;
			    
			    if(lastInserted != null) {  //if lastInserted already exists, 
			    	lastInserted.down = y;
			    }
			    lastInserted = y;
			}
			
			levelhead = levelhead.down;
			x = levelhead;
		}
		size++;
		
	}
	
	//E is a node. width is the index
	@Override
	public E get(int width) {
		Node currNode = this.firstHead; //top head node
		
		Node levelhead = this.firstHead;
		width = width+1; // dont count head
		
		for(int i = this.height;i>= 0; i--) { //iterate through levels (top to bottom)
			
			while(width >= (currNode.width)) {  //(check if width is greater than next node width)
				width = width - currNode.width;  //subtract widths
				currNode = currNode.next; //advance to next node

			}
			
			levelhead = levelhead.down;
			currNode = levelhead;
			
			if(width==0) {
				break;
			}
			
		}
		
		return (E) currNode.value;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		
		return size;
	}
	
	@Override
	public String toString() {
		
		String s = "";
		
		Node currNode = firstHead;
		Node levelhead = this.firstHead;
		for(int i =this.height; i>=1; i--) { //iterate through levels
			
			while(currNode != null) {
				
				s+= "("+currNode.value+ ")" + "-"+currNode.width+"-" ;
				
				
				currNode = currNode.next;

			}
			s+= "\n";
			levelhead = levelhead.down;
			currNode = levelhead;
			
		}
		
		return s;
	}
	
///////////////////////////////////////////////
	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public List subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

}
