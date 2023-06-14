package demo;

import java.util.Scanner;

public class MyHomework {
	public static void main(String[] args) {
		//'0':48, '9':57
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.next();
		String tempString = scanner.next();//?A?B
		int numA = tempString.charAt(0) - 48;
		int numB = tempString.charAt(2) - 48;
		
		for(int i = 1023; i < 9876;i++) {
			String guessString = "" + i;
			if(guessString.charAt(0) != guessString.charAt(1)
			&& guessString.charAt(0) != guessString.charAt(2) 
			&& guessString.charAt(0) != guessString.charAt(3)
			&& guessString.charAt(1) != guessString.charAt(2)
			&& guessString.charAt(1) != guessString.charAt(3)
			&& guessString.charAt(2) != guessString.charAt(3)) {
				if(judgeAB(answer,numA, numB, guessString)) {
					System.out.println(guessString);
				}
			}	
		}
	}
	
	public static boolean judgeAB(String answer, int numA, int numB, String guess) {
		boolean result = false;
		int guessA = 0;
		int guessB = 0;
		int nums[] = {0,0,0,0,0,0,0,0,0,0};
		int A[] = {0,0,0,0};
		int B[] = {0,0,0,0};
		
		//find A's num
		for(int i = 0; i <4; i++) {
			if(guess.charAt(i) == answer.charAt(i)) {
				guessA++;
				A[i] = 1;
			}
		}
		//find B's num
		for(int i = 0; i <4; i++) {
			if(A[i] == 0) {
				for(int j = 0; j<4;j++) {
					if(answer.charAt(j) == guess.charAt(i)) {
						guessB++;
						break;
					}
				}
			}
		}
		
		//result
		if(guessA == numA && guessB == numB) {
			result = true;
		}
		//System.out.println(guessA +"A" + guessB + "B " + result);
		return result;
	}
}
