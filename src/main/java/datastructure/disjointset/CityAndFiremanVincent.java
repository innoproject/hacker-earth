package datastructure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class CityAndFiremanVincent {
	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		int[] cities = new int[N + 1]; int[] E = new int[N + 1];
		
		for(int i = 1; i < E.length; i++) {
			streamTokenizer.nextToken(); E[i] = (int) streamTokenizer.nval;
			cities[i] = i;
		}
		streamTokenizer.nextToken(); int K = (int) streamTokenizer.nval;
		
		while(K-- > 0) {
			streamTokenizer.nextToken(); int a = (int) streamTokenizer.nval;			
			streamTokenizer.nextToken(); int b = (int) streamTokenizer.nval;
			
			weightedUnion(cities, E, a, b);
		}
		printWays(cities, E);
	}
	
	private static void weightedUnion(int[] cities, int[] E, int a, int b) throws Exception {
		int aSet = findRoot(cities, a);
		int bSet = findRoot(cities, b);
		
		if(aSet == bSet)
			return;
		
		if(aSet < bSet) 
			cities[bSet] = aSet;
 		else 
 			cities[aSet] = bSet;
	}
	
	private static int findRoot(int[] cities, int a) throws Exception {
		while(cities[a] != a) {
			cities[a] = cities[cities[a]];
			a = cities[a];
		}
		return a;
	}
	
	private static void printWays(int[] cities, int[] E) throws Exception {
		long modulo = (long) Math.pow(10, 9) + 7;
		int[] freq = new int[E.length];
		int[] min = new int[E.length];
		
		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(freq, 0);
		for(int i = 1; i < cities.length; i++) {
			int root = findRoot(cities, i);
			if(min[root] > E[i]) {
				min[root] = E[i];
				freq[root] = 1;
			} else if(E[i] == min[root]) {
				freq[root]++;
			}
		}
		
		int result = 1;
		for(int i = 1; i < freq.length; i++) {
			if(freq[i] != 0) {
				result *= freq[i];
				result %= modulo;
			}
		}
		System.out.println(result);
 	}
}