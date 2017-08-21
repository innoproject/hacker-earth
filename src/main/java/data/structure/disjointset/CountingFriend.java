package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class CountingFriend {
    
	private static class Friends {
		@SuppressWarnings("unused")
		int data;
		Friends parent;
		int rank;
		
		public Friends(int data) {
			this.data = data;
		}
	}
	
    public static void main(String args[] ) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken();
		int friendCount = (int) streamTokenizer.nval;
		Friends[] friends = new Friends[friendCount + 1];
		
		streamTokenizer.nextToken();
		int connectionCount = (int) streamTokenizer.nval;
		
		Map<Integer, Friends> map = new HashMap<Integer, Friends>();
		while(connectionCount-- > 0) {
			streamTokenizer.nextToken();
			int a = (int) streamTokenizer.nval;
			makeSet(map, friends, a);
			
			streamTokenizer.nextToken();
			int b = (int) streamTokenizer.nval;
			makeSet(map, friends, b);
			
			weightedUnion(map, a, b);
		}
		printResult(friends);
    }
    
    private static void makeSet(Map<Integer, Friends> map, Friends[] friends, int a) throws Exception {
		if(!map.containsKey(a)) {
			Friends friend = new Friends(a);
			friend.parent = friend;
			
			friends[a] = friend;
			map.put(a, friend);
		}
	}
	
	private static void weightedUnion(Map<Integer, Friends> map, int a, int b) throws Exception {
		Friends aFriend = map.get(a);
		Friends bFriend = map.get(b);
		
		Friends aFriendParent = findFriend(aFriend);
		Friends bFriendParent = findFriend(bFriend);
		if(aFriendParent == bFriendParent)
			return;
		
		if(aFriendParent.rank < bFriendParent.rank) {
			bFriendParent.rank += aFriendParent.rank + 1;
			aFriendParent.parent = bFriendParent;
		} else {
			aFriendParent.rank += bFriendParent.rank + 1;
			bFriendParent.parent = aFriendParent;
		}
	}
	
	private static Friends findFriend(Friends friend) throws Exception {
		Friends parent = friend.parent;
		if(parent == friend)
			return parent;
		friend.parent = findFriend(friend.parent);
		return friend.parent;
	}
	
	private static void printResult(Friends[] friends) throws Exception {
		StringBuilder result = new StringBuilder();
		for(int i = 1; i < friends.length; i++) {
			Friends friend = getParent(friends[i]);
			
			if(friend == null) {
				result.append(0);
			} else {
				result.append(friend.rank);
			}
			result.append(" ");
		}
		
		System.out.println(result.toString());
	}
	
	private static Friends getParent(Friends friend) throws Exception {
		if(friend == null) 
			return null;
		
		if(friend.parent == friend)
			return friend.parent;
		return getParent(friend.parent);
	}
}