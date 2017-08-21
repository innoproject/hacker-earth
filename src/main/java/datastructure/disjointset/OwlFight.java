package datastructure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * The problem statement can be found
 * <a href="https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/practice-problems/algorithm/owl-fight/">here</a>
 * 
 * @author viveksingh
 */
public class OwlFight {

	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
	
	private PrintWriter writer = new PrintWriter(System.out);
	
	public static void main(String...strings) throws Exception {
		OwlFight owlFight = new OwlFight();
		StreamTokenizer streamTokenizer = owlFight.getStreamTokenizer();

		streamTokenizer.nextToken(); int owls = (int) streamTokenizer.nval;
		streamTokenizer.nextToken(); int connections = (int) streamTokenizer.nval;
		
		int[] participants = new int[owls + 1];
		owlFight.initialize(participants, owls + 1);
		
		while(connections-- > 0) {
			streamTokenizer.nextToken(); int u = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int v = (int) streamTokenizer.nval;
			
			owlFight.union(participants, u, v);
		}
		
		streamTokenizer.nextToken(); int q = (int) streamTokenizer.nval;
		PrintWriter writer = owlFight.getPrintWriter();
		while(q-- > 0) {
			streamTokenizer.nextToken(); int u = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int v = (int) streamTokenizer.nval;
			
			int uSet = owlFight.findSet(participants, u);
			int vSet = owlFight.findSet(participants, v);
			
			if(uSet == vSet) {
				writer.append("TIE");
			} else {
				if(uSet < vSet) {
					writer.append(""+v);
				} else {
					writer.append(""+u);
				}
			}
			writer.append("\n");
		}
		
		writer.flush();
		owlFight.closeResource();
	}
	
	private void initialize(int[] participants, int size) {
		for(int i = 1; i < size; i++) {
			participants[i] = i;
		}
	}
	
	private void union(int[] participants, int u, int v) {
		int uSet = findSet(participants, u);
		int vSet = findSet(participants, v);
		
		if(uSet == vSet)
			return;
		
		if(uSet < vSet) {
			participants[uSet] = vSet; 
		} else {
			participants[vSet] = uSet;
		}
	}
	
	private int findSet(int[] participants, int u) {
		while(participants[u] != u) {
			participants[u] = participants[participants[u]];
			u = participants[u];
		}
		return u;
	}
	
	private void closeResource() throws Exception {
		this.bufferedReader.close();
		this.writer.close();
	}
	
	private StreamTokenizer getStreamTokenizer() {
		return this.streamTokenizer;
	}
	
	private PrintWriter getPrintWriter() {
		return this.writer;
	}
}