// Concrete builder to build coffee
class CoffeeBuilder extends StarbucksBuilder {
	public void buildSize() {
		starbucks.setSize("medium");
		System.out.println("medium size");
	}
 
	public void buildDrink() {
		starbucks.setDrink("coffee");
		System.out.println("coffee");
	}
}