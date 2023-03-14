package hw1;

import java.util.Scanner;

public class Hw1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[][] bottleBuff = new int[10][3];
		int[] scoreCase = new int[10]; //0:normal, 1: spare, 2: strike
		int totalScore = 0;
		int bottleLeft;
		
		//initialize array
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 3; j++) {
				bottleBuff[i][j] = 0;
			}
		}
		
		//start game
		//(1) 1~9 turn
		for(int i = 0; i < 9; i++) {
			bottleLeft = 10;
			bottleBuff[i][0] = scanner.nextInt(); //first ball
			bottleLeft -= bottleBuff[i][0];
			if(bottleLeft <= 0) {
				//strike
				scoreCase[i] = 2;
			}
			else {
				bottleBuff[i][1] = scanner.nextInt(); //second ball
				bottleLeft -= bottleBuff[i][1];
				if(bottleLeft <= 0) {
					//spare
					scoreCase[i] = 1;
				}
				else {
					scoreCase[i] = 0;				
				}
			}	
		}
		//(2) 10 turn
		bottleLeft = 10;
		bottleBuff[9][0] = scanner.nextInt(); //first ball
		bottleLeft -= bottleBuff[9][0];
		if(bottleLeft <= 0) { //strike: can throw 3 ball in total
			for(int i = 0; i <2; i++) { //second and third ball
				bottleBuff[9][i+1] = scanner.nextInt(); 
			}			
		}
		else {
			bottleBuff[9][1] = scanner.nextInt(); //second ball
			bottleLeft -= bottleBuff[9][1];
			
			if(bottleLeft <= 0) {//spare
				bottleBuff[9][2] = scanner.nextInt(); //third ball
			}
		}	
		//Calculate scores
		for(int i = 0; i < 10; i++) {
			//1~9
			if(i < 9) {
				switch (scoreCase[i]) {
					case 0: //normal
						totalScore += (bottleBuff[i][0] + bottleBuff[i][1]);
						break;
					case 1: //spare
						totalScore += (10 + bottleBuff[i+1][0]);
						break;
					case 2: //strike
						totalScore += (10 + bottleBuff[i+1][0]+ bottleBuff[i+1][1]);
						if(bottleBuff[i+1][0] == 10 && ((i+1) < 9)) {
							totalScore += bottleBuff[i+2][0];
						}
						break;
					
				}
			}else if(i == 9) {
				for(int j = 0; j < 3; j++)
					totalScore += (bottleBuff[9][j]);
			}
			
		}
		
		System.out.println(totalScore); //Q: \n ?
	}
}
