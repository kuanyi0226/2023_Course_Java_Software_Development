package hw4;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import hw4.Card;

public class Hw4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Card[][] cards = new Card[4][13]; //important: the array is still null
		int currentPlayer = 0;//0~3
		int[] score = new int[4]; //output
		
		char trumpShape;
		int trumpNumber;
		int trumpPlayer;
		
		String tempString = ""; //must initialize
		int tempNum;
		char tempChar;
		Card tempCard = new Card(2, 'D');
		boolean startGame = false;
		int passTimes = 0;
		
		//1. Splitting cards
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j <13; j++) {
				tempString = scanner.next();
				if(tempString.length() == 2) { //1~9
					cards[i][j] = new Card(tempString.charAt(0) - '0', tempString.charAt(1));
				}else { //10~13
					cards[i][j] = new Card(tempString.charAt(1) - '0' + 10, tempString.charAt(2));
				}
			}
		}
		//2. Binding
		tempString = scanner.next();
		trumpPlayer = 0;
		trumpNumber = tempString.charAt(0) - '0';
		trumpShape = tempString.charAt(1);
		
		while (!startGame) {
			if(trumpNumber > 3) {
				System.out.println("ERROR");
				break;
			}
			if(currentPlayer >= 3) {
				currentPlayer = 0;
			}else
				currentPlayer++;
			
			tempString = scanner.next();
			if(trumpNumber > 3) {
				System.out.println("ERROR");
				break;
			}
			if(tempString.equals("pass")) { //important: can't use tempString == "pass"
				passTimes++;
				if(passTimes == 3) {
					startGame = true;
				}
			}else {
				passTimes = 0;
				tempNum = tempString.charAt(0) - '0';
				tempChar = tempString.charAt(1);
				if((tempChar < trumpShape) || (tempChar >= trumpShape && tempNum > trumpNumber)) {
					trumpPlayer = currentPlayer;
					trumpNumber = tempNum;
					trumpShape = tempChar;
				}else {
					System.out.println("ERROR");
					break;
				}
			}
			
					
		}
		//3. Start
		if(startGame) {
			currentPlayer = trumpPlayer;
			if(currentPlayer == 3) currentPlayer = 0;
			else currentPlayer++;
			
			int winnerPlayer = 0;
			int winnerNumber;
			char winnerShape;
			char currentShape = 'D';
			boolean hasCard = false;
			boolean wrongCard = false;
			//3 turns
			for(int i = 0; i < 3; i++) {
				winnerNumber = 0;
				winnerShape = 'D';
				//4 players
				for(int j = 0; j < 4; j++) {
					//System.out.println(currentPlayer);
					//(1)Scan
					tempString = scanner.next();
					if(tempString.length() == 2) { //1~9
						tempCard = new Card(tempString.charAt(0) - '0', tempString.charAt(1));
					}else { //10~13
						tempCard = new Card(tempString.charAt(1) - '0' + 10, tempString.charAt(2));
					}
					if(j == 0) { //decide the shape of this turn
						currentShape = tempCard.shape;
						winnerShape = tempCard.shape;
					}
					//(2)check whether has card
					hasCard = false;
					for(int k = 0; k < 13; k++) {
						if(cards[currentPlayer][k].number == tempCard.number && 
								cards[currentPlayer][k].shape == tempCard.shape &&
								cards[currentPlayer][k].used == false) {
							hasCard = true;
							cards[currentPlayer][k].used = true;
							break;
						}
					}
					if(!hasCard) {
						System.out.println("ERROR");
						break; //stop the program
					}
					//(3)comparing
					//same shape
					if(tempCard.shape == winnerShape) {
						if((tempCard.number == 1) || 
							(tempCard.number > 1 && tempCard.number > winnerNumber && winnerNumber != 1)) {
							winnerPlayer = currentPlayer;
							winnerNumber = tempCard.number;
							winnerShape = tempCard.shape;
						}
					}
					//trump shape
					if(tempCard.shape == trumpShape) {
						if(winnerShape != trumpShape) {
							winnerPlayer = currentPlayer;
							winnerNumber = tempCard.number;
							winnerShape = tempCard.shape;
						}
					}
					
					//wrong card
					wrongCard = false;
					if(tempCard.shape != trumpShape && tempCard.shape != currentShape) { //use the irrelevant card
						for(int k = 0; k < 13; k++) {
							if((cards[currentPlayer][k].shape == currentShape || 
									cards[currentPlayer][k].shape == trumpShape) &&
									cards[currentPlayer][k].used == false) {
								wrongCard = true; //but he did have card to use
							}
						}
					}
					if(wrongCard) {
						System.out.println("ERROR");
						break; //stop the program
					}
					
					//iteration
					if(j < 3) {
						if(currentPlayer == 3)
							currentPlayer = 0;
						else {
							currentPlayer++;
						}				
					}else { //find winner
						currentPlayer = winnerPlayer;
					}
				}
				
				if(!hasCard || wrongCard) break;
				//Count score
				score[winnerPlayer]++;
				//System.out.println(winnerPlayer);
				
				//output
				if(i == 2) {
					for(int j = 0; j < 4; j++) {
						System.out.println(score[j]);
					}
				}
			}
		}
		
		scanner.close();	
	}

}
/*
// test data 1:
2A 12B 1B 13D 11A 8A 10A 3D 1D 4D 10D 12A 3B
8B 1A 1C 13C 4A 9A 7B 7C 8C 12C 9D 12D 13B
10B 5C 5D 2D 4B 5B 9B 3C 9C 10C 7D 11D 8D
3A 2B 5A 2C 7A 6B 11B 4C 11C 6A 6C 6D 13A
1A
2D
2B
pass
pass
3C
pass
pass
pass
10B 2B 12B 8B
11A 1A 5C 5A
5D 6D 3D 12D

test data 2:
2A 12B 1C 13D 11A 8A 10A 6C 10C 4D 10D 13A 13C
8B 2B 7A 11C 4A 9A 7B 7C 5C 12C 9D 12D 13B
10B 8C 5D 6A 4B 5B 9B 5A 3C 9C 1D 7D 12A
3A 3B 2C 1A 6B 11B 4C 1B 2D 11D 3D 6D 8D
1C
1A
2D
2B
pass
pass
pass
8A 4A 12A 1A
2C 1C 5C 5A
2A 9A 12A 3A

test data 3:
2A 12B 1C 13D 11A 8A 10A 6C 10C 4D 10D 13A 13C
8B 2B 7A 11C 4A 9A 7B 7C 5C 12C 9D 12D 13B
10B 8C 5D 6A 4B 5B 9B 5A 3C 9C 1D 7D 12A
3A 3B 2C 1A 6B 11B 4C 1B 2D 11D 3D 6D 8D
1C
1A
2D
2B
2C
pass
pass
pass
8A 4A 12A 1A
2C 1C 5C 5A
2A 9A 12A 3A
*/
//8 2 1 5
