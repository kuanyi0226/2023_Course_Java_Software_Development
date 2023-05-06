package hw5;

public class Food {
	private int id;
	private String name;
	private String description;
	private double price;
	
	public Food(int id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getId() {
		return this.id;
	}
	
	
}
