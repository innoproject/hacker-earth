package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CityAndFlood {

	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken();
		int empireCount = (int) streamTokenizer.nval;
		
		streamTokenizer.nextToken();
		int invasionCount = (int) streamTokenizer.nval;
		
		int[] empires = new int[empireCount + 1];
		initalize(empires);
		while(invasionCount-- > 0) {
			streamTokenizer.nextToken();
			int i = (int) streamTokenizer.nval;
			
			streamTokenizer.nextToken();
			int j = (int) streamTokenizer.nval;
			
			union(empires, i, j);
		}
		distinctEmpire(empires);
	}
	
	private static void initalize(int[] empires) {
		for(int i = 1; i < empires.length; i++) {
			empires[i] = i;
		}
	}
	
	private static void union(int[] empires, int a, int b) {
		int aSet = find(empires, a);
		int bSet = find(empires, b);
		
		if(aSet == bSet)
			return;
		
		empires[bSet] = aSet;
	}
	
	private static int find(int[] empires, int a) {
		if(empires[a] == a)
			return empires[a];
		empires[a] = find(empires, empires[a]);
		return empires[a];
	}
	
	private static void distinctEmpire(int[] empires) {
		int result = 0;
		for(int i = 1; i < empires.length; i++) {
			if(i == empires[i]) {
				result++;
			}
		}
		System.out.println(result);
	}
}