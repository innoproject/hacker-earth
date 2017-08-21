package datastructure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TeacherDilemma {

	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken();
		int N = (int) streamTokenizer.nval;
		
		streamTokenizer.nextToken();
		int M = (int) streamTokenizer.nval;
		
		int[] vertices = new int[N + 1];
		int[] ranks = new int[N + 1];
		
		intialize(vertices, ranks);
		while(M-- > 0) {
			streamTokenizer.nextToken();
			int a = (int) streamTokenizer.nval;
			
			streamTokenizer.nextToken();
			int b = (int) streamTokenizer.nval;
			
			weightedUnion(vertices, ranks, a, b);
		}
		chooseLeaders(ranks, vertices);
		bufferedReader.close();
	}
	
	private static void intialize(int[] vertices, int[] ranks) {
		for(int i = 1; i < vertices.length; i++) {
			ranks[i] = 1;
			vertices[i] = i;
		}
	}
	
	private static void weightedUnion(int[] vertices, int[] rank, int a, int b) {
		int aSet = findSet(vertices, a);
		int bSet = findSet(vertices, b);
		
		if(aSet == bSet)
			return;
		
		if(aSet < bSet) {
			vertices[bSet] = aSet;
			rank[aSet] += rank[bSet];
		} else {
			vertices[aSet] = bSet;
			rank[bSet] += rank[aSet];
		}
	}
	
	private static int findSet(int[] vertices, int a) {
		if(vertices[a] == a)
			return vertices[a];
		vertices[a] = findSet(vertices, vertices[a]);
		return vertices[a];
	}
	
	private static void chooseLeaders(int[] ranks, int[] vertices) {
		long modulo = (long) Math.pow(10, 9) + 7;
		long result = 1;
		for(int i = 1; i < ranks.length; i++) {
			if(i == vertices[i]) {
				result *= ranks[i];
				result %= modulo;
			}
		}
		System.out.println(result);
	}
}