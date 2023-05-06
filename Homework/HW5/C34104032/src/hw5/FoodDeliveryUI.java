package hw5;

import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class FoodDeliveryUI {
	Scanner scanner = new Scanner(System.in);
	FoodDelivery foodDelivery;
	int currentRestaurantIndex;
	
	public FoodDeliveryUI(FoodDelivery foodDelivery) {
		this.foodDelivery = foodDelivery;
	}
	
	public void start() {
		boolean exit = false;
		//state: (0)Select restaurant, (1)Restaurant operation menu (2)AddFood, (3) CheckFoods
		int state = 0;
		while (exit != true) {
			switch (state) {
			//Select restaurant
			case 0: {
				int restaurantIndex = selectRestaurant();
				if(restaurantIndex == -1) exit = true;
				else {
					state = 1;
					restaurantIndex -= 1; //the restaurants list in foodDelivery starts from index 0
					foodDelivery.selectRestaurant(restaurantIndex);
					currentRestaurantIndex = restaurantIndex;
				}
				break;
			}
			//Restaurant operation menu
			case 1:{
				String name = foodDelivery.getRestaurants().get(currentRestaurantIndex).getName();
				String[] operations = new String[] {"新增食物","查看所有食物","返回餐廳選擇"};
				for(int i = 0; i < operations.length; i++) {
					System.out.printf("(%s)%d. %s\n", name, i+1, operations[i]);
				}
				
				
				while(state >= 1) {
					state = restaurantOperationMenu(operations.length);
					switch (state) {
					// Add Food
					case 0:{
						break;
					}
					case 2: {
						addFood();
						break;
					}
					// Check Foods
					case 3: {
						checkFoods();
						break;
					}
					default:
						state = 1;
						break;
					}
				}
				break;
			}
			default:
				break;
			}
							
		}
	}
	//Select restaurant
	private int selectRestaurant() {
		int restaurantNum = foodDelivery.getRestaurants().size();
		for(int i = 0; i < restaurantNum; i++) {
			System.out.printf("%d. %s\n", i+1, foodDelivery.getRestaurants().get(i).getName());
		}
		System.out.printf("Select Restaurant: ");
		int restaurantIndex = scanner.nextInt();
		while((restaurantIndex <= 0 && restaurantIndex != -1) || (restaurantIndex > restaurantNum)) {
			System.out.printf("請選擇畫面上的餐廳\n");
			System.out.printf("Select Restaurant: ");
			restaurantIndex = scanner.nextInt();
		}
		return restaurantIndex; 
	}
	//Restaurant operation menu
	private int restaurantOperationMenu(int operationNum) {
		
		System.out.printf("請輸入選擇: ");
		int userChoose = scanner.nextInt();
		
		while((userChoose <= 0 ) || (userChoose > operationNum)) {
			System.out.printf("請輸入正確選項: ");
			userChoose = scanner.nextInt();
		}
		
		//Encode userChoose to state
		switch (userChoose) {
		case 1: {
			userChoose = 2;
			break;
		}
		case 2: {
			userChoose = 3;
			break;
		}
		default:{
			userChoose = 0;
			break;
		}
		}
		return userChoose;
	}
	
	private void addFood() {
		System.out.println("請輸入食物名稱: ");
		scanner.nextLine();
		String name = scanner.nextLine();
		System.out.println("請輸入食物描述: ");
		String description = scanner.nextLine();
		System.out.println("請輸入食物價格: ");
		double price = scanner.nextDouble();
		while(price <= 0) {
			System.out.println("價錢必須大於零，請重新輸入。\n");
			System.out.println("請輸入食物價格: ");
			price = scanner.nextDouble();
		}
		//add food into food list
		foodDelivery.getRestaurants().get(currentRestaurantIndex).addFood(name, description, price);
	}
	
	private void checkFoods() {
		List<Food> foods = foodDelivery.getFoods();
		int foodNum = foods.size();
		System.out.printf("%-8s %-16s %-8s %-8s\n", "No.", "Name", "Price", "Description");
		for(int i = 0; i <foodNum; i++) {
			System.out.printf("%-8d %-16s %-8.2f %-8s\n", foods.get(i).getId(), foods.get(i).getName(), foods.get(i).getPrice(), foods.get(i).getDescription());
		}
	}
}




