import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTester {
	
	enum Position {beginning, end, random, byValue};

	public static void main(String[] args) {
		List [] lists = {new ArrayList<Number>(), new FileList<Number>()};
		for (int N = 10; N <= 10000; N *= 10){
			 System.out.printf("N = %8d: ms to Ins. start, end, rnd; Rmv. start, end, rnd; Rmv. by value%n", N);
			 for (List <Number> currentList : lists){
				 System.out.printf("%-22s", currentList.getClass().getSimpleName());
				 //System.out.printf("%6d", listAdd(N, currentList, Position.beginning));
				 //System.out.printf("%6d", listAdd(N, currentList, Position.end));
				 //System.out.printf("%6d", listAdd(N, currentList, Position.random));
				 //System.out.printf("%10d", listRemove(N, currentList, Position.beginning));
				 System.out.printf("%6d", listRemove(N, currentList, Position.end));
			     System.out.printf("%6d", listRemove(N, currentList, Position.random));
				 if (N <100_000)
					 System.out.printf("%12d", listRemove(N, currentList, Position.byValue));
				 System.out.println();
			 }
		}
		System.out.println("done");
	}
	
	public static <E> long listAdd (int N, List<E> list, Position pos)
	{
		Random rnd = new Random();
		int insertIndex = 0;
		list.clear();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < N; i++)
		{	
			if (pos == Position.end) list.add((E) new Integer (rnd.nextInt(10*N)));
			else{
				if (pos == Position.random) 
					insertIndex = list.size() > 0 ? rnd.nextInt(list.size() + 1) : 0;
					//default is 0 (beginning)
				list.add(insertIndex, (E) new Integer (rnd.nextInt(10*N)));
			}
		}
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;
		
		//for Debugging student's submissions
		//System.out.println(list + "("+list.size()+")");
		return elapsed;
	}

	public static <E> long listRemove (int N, List<E> list, Position position)
	{
		Random rnd = new Random();
		int removeIndex = 0;
		list.clear();
		listAdd(N, list, Position.end); //create a list of N items, end is the fastest

		long startTime = System.currentTimeMillis();
		for(int i = 0; i < N; i++)
		{	
			if (position != Position.byValue){
				if (position == Position.end) removeIndex = list.size() - 1;
				if (position == Position.random) removeIndex = rnd.nextInt(list.size());
				list.remove(removeIndex);
			}else
				list.remove((E) new Integer (rnd.nextInt(10*N)));
		}
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;
		
		//for Debugging student's submissions
		//System.out.printf("(%4d)", l.size());
		return elapsed;
	}

}
