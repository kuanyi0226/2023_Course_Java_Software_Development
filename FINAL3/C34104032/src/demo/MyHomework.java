package demo;

import java.util.Scanner;

public class MyHomework {
	public static void main(String[] args) {
		//input
		int[][] inputs = new int[10][10];
		int[][] outputs = new int[10][10];
		Scanner scanner = new Scanner(System.in);
		for(int i = 0; i <10; i++){
			for(int j = 0; j < 10;j++){
				int tempInt = scanner.nextInt();
				inputs[i][j] = tempInt;
				outputs[i][j] = tempInt;
			}
		}
		//judge
		//row check
		int countOne = 0;
		int zeroAtIndex = 0;
		for(int i = 0; i < 10;i++){
			for(int j = 0; j < 6; j++){ //6 times per row
				countOne = 0;
				for(int k = 0; k <5; k++){ //5 scan per time
					if(inputs[i][j+k] == 1){
						countOne++;
					}else{
						zeroAtIndex = j+k;
					}
				}
				if(countOne == 4){
					outputs[i][zeroAtIndex] = 2;
				}
			}
		}
		//column check
		for(int i = 0; i < 10;i++){
			for(int j = 0; j < 6; j++){ //6 times per column
				countOne = 0;
				for(int k = 0; k <5; k++){ //5 scan per time
					if(inputs[j+k][i] == 1){
						countOne++;
					}else{
						zeroAtIndex = j+k;
					}
				}
				if(countOne == 4){
					outputs[zeroAtIndex][i] = 2;
				}
			}
		}
		//X check
		//case1:up right
		for(int i = 0; i < 6;i++){
			for(int j = 0; j < 6 - i; j++){ //6~1 times per line
				countOne = 0;
				for(int k = 0; k <5; k++){ //5 scan per time
					if(inputs[i+j+k][j+k] == 1){
						countOne++;
					}else{
						zeroAtIndex = k;
					}
				}
				if(countOne == 4){
					outputs[i+j+zeroAtIndex][j+zeroAtIndex] = 2;
				}
			}
		}
		//case2:down right
		for(int i = 1; i < 6;i++){
			for(int j = 0; j < 6 - i; j++){ //5~1 times per line
				countOne = 0;
				for(int k = 0; k <5; k++){ //5 scan per time
					if(inputs[j+k][i+j+k] == 1){
						countOne++;
					}else{
						zeroAtIndex = k;
					}
				}
				if(countOne == 4){
					outputs[j+zeroAtIndex][i+j+zeroAtIndex] = 2;
				}
			}
		}

		//case3:up left
		for(int i = 9; i >3;i--){
			for(int j = 0; j < i-3; j++){ //6~1 times per line
				countOne = 0;
				for(int k = 0; k <5; k++){ //5 scan per time
					if(inputs[i-j-k][j+k] == 1){
						countOne++;
					}else{
						zeroAtIndex = k;
					}
				}
				if(countOne == 4){
					outputs[i-j-zeroAtIndex][j+zeroAtIndex] = 2;
				}
			}
		}
		//case4:down left
		for(int i = 1; i < 6;i++){
			for(int j = 0; j < 6 - i; j++){ //5~1 times per line
				countOne = 0;
				for(int k = 0; k <5; k++){ //5 scan per time
					if(inputs[9-j-k][i+j+k] == 1){
						countOne++;
					}else{
						zeroAtIndex = k;
					}
				}
				if(countOne == 4){
					outputs[9-j-zeroAtIndex][i+j+zeroAtIndex] = 2;
				}
			}
		}

		//output logic
		for(int i = 0; i < 10; i++){
			for(int j = 0; j <10; j++){
				if(outputs[i][j] == 2){
					System.out.print("X");
				}else{
					System.out.print(outputs[i][j] + "");
				}

				if(j < 9){
					System.out.print(" ");
				}
				
			}
			System.out.println("");
		}
	}
}
/*
0 0 0 0 0 0 0 0 0 0
0 1 0 1 0 1 0 0 1 1
0 0 0 1 1 0 0 1 0 1
0 0 1 1 0 0 0 1 0 1
0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 1 1 0 0 0
1 1 1 0 0 1 1 1 1 0
0 0 0 0 1 1 1 0 0 0
1 1 1 0 0 0 0 0 0 0
0 0 0 1 0 1 0 1 0 0
 */

/* 
0 0 0 X 0 0 0 0 0 0
0 1 0 1 0 1 0 0 1 1
0 0 0 1 1 0 0 1 0 1
0 0 1 1 0 0 0 1 0 1
0 0 0 1 0 0 0 0 0 0
0 0 0 X X 1 1 0 0 0
1 1 1 0 X 1 1 1 1 X
0 0 0 0 1 1 1 0 0 0
1 1 1 0 0 X 0 0 0 0
0 0 0 1 0 1 0 1 0 0
*/