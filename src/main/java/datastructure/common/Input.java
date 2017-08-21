package datastructure.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Input {
	
	private static BufferedReader bufferedReader;
	private static StreamTokenizer tokenizer;
	
	static {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		tokenizer = new StreamTokenizer(bufferedReader);
	}
	
	public static String string() throws Exception {
		tokenizer.nextToken();
		return tokenizer.sval;
	}
	
	public static int in() throws Exception {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
	
	public static void close() throws Exception {
		bufferedReader.close();
	}
}