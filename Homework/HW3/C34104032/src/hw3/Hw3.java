package hw3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Hw3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> publicCards = new ArrayList<>();
		ArrayList<String> Cards = new ArrayList<>();
		String temp = new String();
		
		int[] number = new int[14]; //how many cards in different numbers from 1~13
		int[] shape = new int[4]; //how many cards in diff. shapes: 0-S, 1-H, 2-D, 3-C
		
		int tempNum = 'A';
		int[] tempNums = new int[14]; //how many cards in different numbers from 1~13
		
		
		//Scan 5 public cards
		for(int i = 0; i < 5; i++) {
			publicCards.add(scanner.next());
		}
		
		//6 players
		for(int i = 0; i < 6; i++) {
			//reset
			Cards.clear();
			Arrays.fill(number, 0);
			Arrays.fill(shape, 0);
			//put publicCards in Cards
			Cards = (ArrayList<String>)publicCards.clone();
			//scan 2 cards form hands
			for(int j = 0 ; j < 2; j++) {
				Cards.add(scanner.next());
			}
			
			//(1)Sort the Cards in numbers
			Cards.sort(null);
			for(int j = 0; j < 7; j++) {
				if(Cards.get(j).length() > 2) {
					temp = Cards.get(j);
					Cards.remove(j);
					Cards.add(temp);
				}
			}
			//(2)conclude the number and shape
			for(int j = 0; j < 7; j++) {
				if(Cards.get(j).length() > 2) { //10~13
					number[Cards.get(j).charAt(1) - '0' + 10] += 1;
					
					switch (Cards.get(j).charAt(2)){
					case 'S': shape[0]++; break;
					case 'H': shape[1]++; break;
					case 'D': shape[2]++; break;
					case 'C': shape[3]++; break;
					}
				}else { //1~9
					number[Cards.get(j).charAt(0) - '0'] += 1;
					
					switch (Cards.get(j).charAt(1)){
					case 'S': shape[0]++; break;
					case 'H': shape[1]++; break;
					case 'D': shape[2]++; break;
					case 'C': shape[3]++; break;
					}
				}
			}
			//(3) Judge
			//8: Straight Flush		
			if(straightFlush(Cards) == 1) {
				System.out.println(8);
				continue; //notice: don't type "break"
			}
			//7: Four of a kind
			if(fourOfAKind(number) == 1) {
				System.out.println(7);
				continue;
			}
			//6: full house
			if(fullHouse(number) == 1) {
				System.out.println(6);
				continue;
			}
			//5: flush
			if(flush(shape) == 1) {
				System.out.println(5);
				continue;
			}
			//4: straight
			if(straight(number) == 1) {
				System.out.println(4);
				continue;
			}
			//3:
			if(threeOfAKind(number) == 1) {
				System.out.println(3);
				continue;
			}
			//2:
			if(twoPairs(number) == 1) {
				System.out.println(2);
				continue;
			}
			//1:
			if(onePair(number) == 1) {
				System.out.println(1);
				continue;
			}
			//0:
			System.out.println(0);
		}
		
		scanner.close();
	}
	//8: Straight Flush
	public static int straightFlush(ArrayList<String> cards) { //must type static
		int[] tempNums = new int[14];		
		char[] shapes = {'S','H','D','C'};
		//judge 4 shapes
		for(int i = 0; i < 4; i++) {
			Arrays.fill(tempNums, 0);
			for(int j = 0; j < 7; j++) {
				if(cards.get(j).length()==2) { //1~9
					if(cards.get(j).charAt(1) == shapes[i]) {
						tempNums[cards.get(j).charAt(0) - '0'] ++;
					}
				}else { //10~13
					if(cards.get(j).charAt(2) == shapes[i]) {
						tempNums[cards.get(j).charAt(1) - '0' +10] ++;
					}
				}
			}
			for(int j = 1; j <=9; j++) {
				if(tempNums[j]==1 && tempNums[j+1]==1 && tempNums[j+2]==1 && tempNums[j+3]==1 && tempNums[j+4]==1) {
					return 1;
				}			
			}
			//royal flush
			if(tempNums[1]==1 && tempNums[13]==1 && tempNums[12]==1 && tempNums[11]==1 && tempNums[10]==1) {
				return 1;
			}
		}
		return 0;
	}
	//7: four of a kind
	public static int fourOfAKind(int[] number) {
		for(int i = 1; i <=13; i++) {
			if(number[i] == 4)
				return 1;
		}
		return 0;
	}
	//6: full house
	public static int fullHouse(int[] number) {
		int hasThree = 0;
		int hasTwo = 0;
		for(int i = 1; i <=13; i++) {
			if(number[i] == 3)
				hasThree = 1;
			else if(number[i] == 2)
				hasTwo = 1;
			
			if(hasThree == 1 && hasTwo == 1)
				return 1;
		}
		return 0;
	}
	//5: flush
	public static int flush(int[] shape) {
		for(int i = 0; i < 4; i++) {
			if(shape[i]>=5)
				return 1;
		}
		return 0;
	}
	//4: Straight
	public static int straight(int[] number) {
		for(int j = 1; j <=9; j++) {
			if(number[j]>=1 && number[j+1]>=1 && number[j+2]>=1 && number[j+3]>=1 && number[j+4]>=1) {
				return 1;
			}			
		}
		//10, J, Q, K, A
		if(number[1]>=1 && number[13]>=1 && number[12]>=1 && number[11]>=1 && number[10]>=1) {
			return 1;
		}
		
		return 0;
	}
	//3: three of a kind
	public static int threeOfAKind(int[] number) {
		for(int i = 1; i <=13; i++) {
			if(number[i] == 3)
				return 1;
		}
		return 0;
	}
	//2: two pairs
	public static int twoPairs(int[] number) {
		int hasTwo = 0;
		for(int i = 1; i <=13; i++) {
			if(number[i] == 2)
				hasTwo++;
			if(hasTwo >= 2) {
				return 1;
			}
		}
		return 0;
	}
	//1: one pair
	public static int onePair(int[] number) {
		for(int i = 1; i <=13; i++) {
			if(number[i] == 2)
				return 1;
		}
		return 0;
	}
}