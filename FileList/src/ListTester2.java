import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTester2 {


	public static void main(String[] args) {
		//List [] lists = {new ArrayList<Number>(), new FileList<Number>()};
		for (int N = 10; N <= 10000; N *= 10){
			System.out.printf("N = %-6d: ms to Ins. start, end, rnd; Rmv. start, end, rnd; Rmv. by value%n", N);
			 	  
			      List arrayList = new ArrayList();
				
				  System.out.printf("%-22s", arrayList.getClass().getSimpleName());
				  System.out.printf("%6d", listAdd(N, arrayList, "beg"));
				  System.out.printf("%6d", listAdd(N, arrayList, "end"));
				  System.out.printf("%6d", listAdd(N, arrayList, "random"));
				  System.out.printf("%10d", listRemove(N, arrayList, "beg"));
				  System.out.printf("%6d", listRemove(N, arrayList, "end"));
				  System.out.printf("%6d", listRemove(N, arrayList, "random"));
				  
				  System.out.printf("%12d", listRemove(N, arrayList, "byValue"));
				 
				  System.out.println();
				  
				  List add = new FileList("add "+N);
				  
				  System.out.printf("%-22s", add.getClass().getSimpleName());
				  System.out.printf("%6d", listAdd(N, add, "beg"));
				  System.out.printf("%6d", listAdd(N, add, "end"));
				  System.out.printf("%6d", listAdd(N, add, "random"));
				  
				  List removeBeg = new FileList("removeBeg "+N);
				  System.out.printf("%10d", listRemove(N, removeBeg, "beg"));
				  
				  List removeEnd = new FileList("removeEnd "+N);
				  System.out.printf("%6d", listRemove(N, removeEnd, "end"));
				  List removeRandom = new FileList("removeRandom" + N);
				  System.out.printf("%6d", listRemove(N, removeRandom, "random"));
				  List removeValue = new FileList("removeValue "+N);
				  System.out.printf("%12d", listRemove(N, removeValue, "byValue"));
				 
				  System.out.println();
			
		}
		System.out.println("done");
	}

	public static <E> long listAdd (int N, List<E> list, String pos)
	{
		Random rnd = new Random();
		int insertIndex = 0;
		list.clear();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < N; i++)
		{	
			if (pos.equals("end")) {
				list.add((E) new Integer (rnd.nextInt(10*N)));
			}
			else if(pos.equals("beg")){
				list.add(0,(E) new Integer (rnd.nextInt(10*N)));
			}else if (pos.equals("random")) {
				if(list.size()>0) { //if size > 0
					insertIndex =  rnd.nextInt(list.size() + 1);
					list.add(insertIndex, (E)new Integer (rnd.nextInt(10*N)));
				}else { //if size not greater than 0, add to beginning
					list.add(0, (E) new Integer (rnd.nextInt(10*N)));
				}
				
				//default is 0 (beginning)

			}
		}
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;

		//for Debugging student's submissions
		//System.out.println(list + "("+list.size()+")");
		return elapsed;
	}

	public static <E> long listRemove (int N, List<E> list, String position)
	{
		Random rnd = new Random();
		int removeIndex = 0;
		list.clear();
		listAdd(N, list, "end"); //create a list of N items, end is the fastest

		long startTime = System.currentTimeMillis();
		for(int i = 0; i < N; i++)
		{	
			if (position.equals("beg")){
				removeIndex = 0;
				list.remove(removeIndex);
			}else if(position.equals("end")) {
				removeIndex = list.size() - 1;
				list.remove(removeIndex);
			}else if(position.equals("random")){
				removeIndex = rnd.nextInt(list.size());
				list.remove(removeIndex);
			}else if(position.equals("byValue")) {
				list.remove((E) new Integer (rnd.nextInt(10*N)));
			}
		}
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime;

		//for Debugging student's submissions
		//System.out.printf("(%4d)", l.size());
		return elapsed;
	}

}
