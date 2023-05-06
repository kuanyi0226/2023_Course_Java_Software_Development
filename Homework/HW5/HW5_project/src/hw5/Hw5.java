package hw5;

public class Hw5 {

	public static void main(String[] args) {
		FoodDelivery foodDelivery = new FoodDelivery();
		FoodDeliveryUI ui = new FoodDeliveryUI(foodDelivery);
		ui.start();
	}

}
