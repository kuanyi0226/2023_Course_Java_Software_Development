package hw5;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private List<Food> foods = new ArrayList<Food>();
	private String name;
	
	public Restaurant(String name) {
		this.name = name;
	}
	
	public void addFood(String name, String description, double price) {
		foods.add(new Food(FoodDelivery.foodindex ,name, description, price));
		FoodDelivery.foodindex ++;
	}
	
	public String getName() {
		return this.name;
	}
	
	public  List<Food> getFoods() {
		return this.foods;
	}
}
