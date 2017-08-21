package indiahacks.programming.wildcard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class RoundTableKillers {

	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		streamTokenizer.nextToken(); int K = (int) streamTokenizer.nval;
		streamTokenizer.nextToken(); int X = (int) streamTokenizer.nval;
		
		int[] person = new int[N];
		initialize(person, N);
		
		int totalNonZero = N;
		while(true) {
			int toBeKilled = X % K;
			
			if(totalNonZero <= toBeKilled) {
				toBeKilled = totalNonZero - 1;
			}
			totalNonZero -= toBeKilled;
			int temp = 0;
			int i = 0;
			for(; i < toBeKilled; i++) {
				while(person[(X + temp + i) % N] == 0) {
					temp++;
				}
				person[(X + temp + i) % N] = 0; 
			}
			
			if(totalNonZero < 2) {
				break;
			}
			while(person[(X + temp + i) % N] == 0) {
				temp++;
			}
			X = (X + temp + i) % N + 1;
		}
		System.out.println(X);
	}
	
	private static void initialize(int[] person, int N) throws Exception {
		for(int i = 0; i < N; i++) {
			person[i] = i + 1;
		}
	}
}