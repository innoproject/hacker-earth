package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * The problem statement can be found
 * <a href="https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/practice-problems/algorithm/friends-and-foes/">here</a>
 * 
 * @author viveksingh
 */
public class FriendsAndFoes {

	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
	
	public static void main(String args[] ) throws Exception {
		FriendsAndFoes friendsAndFoes = new FriendsAndFoes();
		
		StreamTokenizer streamTokenizer = friendsAndFoes.getStreamTokenizer(); 
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		int totalExpectedPairs = (int) Math.min(2 * Math.pow(10, 5), (N * (N - 1)) / 2);
		
		int[] people = new int[N + 1];
		int[] ranks = new int[N + 1];
		
		streamTokenizer.nextToken(); int friends = (int) streamTokenizer.nval;
		friendsAndFoes.initialize(people, ranks, N + 1);
		
		for(int i = 0; i < friends; i++) {
			streamTokenizer.nextToken(); int a = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int b = (int) streamTokenizer.nval;
			
			friendsAndFoes.unionFriends(people, ranks, a, b);
		}
		
		streamTokenizer.nextToken(); int enemies = (int) streamTokenizer.nval;
		assert friends + enemies >= 1 && friends + enemies <= totalExpectedPairs;
		
		for(int i = 0; i < enemies; i++) {
			streamTokenizer.nextToken(); int a = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int b = (int) streamTokenizer.nval;
			
			friendsAndFoes.intersectEnemies(people, ranks, a, b);
		}
		Arrays.sort(ranks);
		System.out.println(ranks[ranks.length - 1]);
		
		friendsAndFoes.closeResource();
	}
	
	private void initialize(int[] people, int[] ranks, int count) throws Exception {
		for(int i = 1; i < count; i++) {
			people[i] = i;
			ranks[i] = 1;
		}
	}
	
	private void unionFriends(int[] people, int[] ranks, int a, int b) throws Exception {
		int aSet = findSet( people, a );
		int bSet = findSet( people, b );
		
		if(aSet == bSet)
			return;
		
		if(people[aSet] < people[bSet]) {
			people[bSet] = people[aSet];
			ranks[aSet] += ranks[bSet];
			ranks[bSet] = 0;
		} else {
			people[aSet] = people[bSet];
			ranks[bSet] += ranks[aSet];
			ranks[aSet] = 0;
		}
	}
	
	private void intersectEnemies(int[] people, int[] ranks, int a, int b) throws Exception {
		int aSet = findSet( people, a );
		int bSet = findSet( people, b );
		
		if(aSet == bSet) {
			ranks[aSet] = 0;
		}
	}
	
	private int findSet(int[] people, int a) throws Exception {
		while(people[a] != a) {
			people[a] = people[people[a]];
			a = people[a];
		}
		return a;
	}
	
	private void closeResource() throws Exception {
		this.bufferedReader.close();
	}
	
	private StreamTokenizer getStreamTokenizer() {
		return this.streamTokenizer;
	}
}