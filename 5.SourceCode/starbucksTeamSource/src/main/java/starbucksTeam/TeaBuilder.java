//tea screen
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

	@Override
	public void buildMilk() {
		starbucks.setMilk("2%Milk");
		System.out.println("2% Milk");
		
	}

	@Override
	public void buildFalvour() {
		starbucks.setFalvour("Chai Tea Latte");
		System.out.println("Chai Tea Latte");
		
	}
}