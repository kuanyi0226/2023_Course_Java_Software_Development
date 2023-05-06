package hw5;

import java.util.ArrayList;
import java.util.List;

public class FoodDelivery {
	private int restaurantIndex;
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	static public int foodindex = 1;	
	public  FoodDelivery() {
		restaurants.add(new Restaurant("Starbucks"));
		restaurants.add(new Restaurant("Mcdonald's"));
		restaurants.add(new Restaurant("KFC"));
	}
	
	public void createFood(String name, String description, double price) {
		restaurants.get(restaurantIndex).addFood(name, description, price);
	}
	
	public void selectRestaurant(int restaurantIndex) {
		this.restaurantIndex = restaurantIndex;
	}
	
	public List<Restaurant> getRestaurants() {
		return this.restaurants;
	}
	
	public List<Food> getFoods() {
		return this.restaurants.get(restaurantIndex).getFoods();
	}
}
