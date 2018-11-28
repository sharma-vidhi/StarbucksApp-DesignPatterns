// Concrete Builder to build tea
class TeaBuilder extends StarbucksBuilder {
	public void buildSize() {
		starbucks.setSize("large");
		System.out.println("large size");
	}
 
	public void buildDrink() {
		starbucks.setDrink("tea");
		System.out.println("tea");
	}
}