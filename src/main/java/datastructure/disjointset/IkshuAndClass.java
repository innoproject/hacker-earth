package datastructure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class IkshuAndClass {

	private static final long[] fact = new long[100002];
 	private static final long modulo = (long) Math.pow(10, 9) + 7;
 	
 	static {
 		fact[0] = fact[1] = 1;
 		for(int i = 2; i < fact.length; i++) {
 			fact[i] = i * fact[i - 1];
 			fact[i] %= modulo;
 		}
 	}
 	
	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		streamTokenizer.nextToken(); int K = (int) streamTokenizer.nval;
		
		int[] students = new int[N]; int[] ranks = new int[N];
		initialize(students, ranks);
		
		while(K-- > 0) {
			streamTokenizer.nextToken(); int a = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int b = (int) streamTokenizer.nval;
			
			union(students, ranks, a, b);
		}
		Arrays.sort(ranks);
		
		long result = 1l;
		for(int i = ranks.length - 1; i > 0; i--) {
			if(ranks[i] == 1)
				break;
			
			result *= fact[ranks[i]];
			result %= modulo;
		}
		System.out.println(result);
		bufferedReader.close();
	}
	
	private static void union(int[] students, int[] ranks, int a, int b) throws Exception {
		int aSet = findGroup(students, a);
		int bSet = findGroup(students, b);
		
		if(aSet == bSet)
			return;

		if(aSet < bSet) {
			students[bSet] = students[aSet];
			ranks[aSet] += ranks[bSet];
			ranks[bSet] = 1;
		} else {
			students[aSet] = students[bSet];
			ranks[bSet] += ranks[aSet];
			ranks[aSet] = 1;
		}
	}
	
	private static int findGroup(int[] students, int a) throws Exception {
		while(students[a] != a) {
			students[a] = students[students[a]];
			a = students[a];
		}
		return a;
	}
	
	private static void initialize(int[] students, int[] ranks) throws Exception {
		for(int i = 0; i < students.length; i++) {
			students[i] = i;
			ranks[i] = 1;
		}
	}
}