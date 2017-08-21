package datastructure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class MonkPalindromes {

	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		streamTokenizer.nextToken(); int Q = (int) streamTokenizer.nval;
		
		int[] arr = new int[N + 1]; int[] ranks = new int[N + 1];
		initialize(arr, ranks);
		
		while(Q-- > 0) {
			streamTokenizer.nextToken(); int a = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int b = (int) streamTokenizer.nval;
			
			union(arr, ranks, a, b);
		}
		long modulo = (long) Math.pow(10, 9) + 7;
		long result = 1l;
		for(int i = 1; i < arr.length; i++) {
			if(findRep(arr, i) == i) {
				result *= 10;
				result %= modulo;
			}
		}
		System.out.println(result);
		bufferedReader.close();
	}
	
	private static void union(int[] arr, int[] ranks, int a, int b) throws Exception {
		int aSet = findRep(arr, a);
		int bSet = findRep(arr, b);
		
		if(aSet == bSet)
			return;
		
		if(arr[aSet] < arr[bSet]) {
			arr[bSet] = arr[aSet];
			ranks[aSet] += ranks[bSet];
			ranks[bSet] = 0;
		} else {
			arr[aSet] = arr[bSet];
			ranks[bSet] += ranks[aSet];
			ranks[aSet] = 0;
		}
	}
	
	private static int findRep(int[] arr, int a) throws Exception {
		while(arr[a] != a) {
			arr[a] = arr[arr[a]];
			a = arr[a];
		}
		return a;
	}
	
	private static void initialize(int[] arr, int[] ranks) throws Exception {
		for(int i = 1; i < arr.length; i++) {
			arr[i] = i;
			ranks[i] = 1;
		}
	}
}