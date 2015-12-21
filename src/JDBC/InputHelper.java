package JDBC;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {

	public static String getStringInput(String prompt) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(prompt);
		System.out.flush();
		
		try {
			return input.readLine();
		} catch (Exception exception) {
			return "Error: " + exception.getMessage();
		}
	}

}
