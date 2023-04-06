package hw2;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class hw2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int inputNum = scanner.nextInt();
		int tempTotal;

		if(inputNum <= 0 || inputNum >= Math.pow(10, 9)) { //detect valid input
			System.out.println("E");
		}else { //finding results
			for(int i = 1; i <= inputNum; i++) { //from 1 ~ input
				tempTotal = 0;
				for(int j = 0; ; j++) { //input + (input + 1) + (input +2)......
					tempTotal += (i+j);
					//judge whether found the result
					//found
					if(tempTotal == inputNum) { 
						for(int k = 0; k < j + 1; k++) {
							if(k != 0) System.out.print("+");
							System.out.print(i + k);					
						}
						System.out.println();
					}
					//not found
					else if(tempTotal > inputNum) { 
						break; //go detect next beginning number
					}
				}
			}
		}
	}
}


