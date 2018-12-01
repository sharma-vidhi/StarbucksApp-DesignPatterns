//coffee screen
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

	@Override
	public void buildMilk() {
		starbucks.setMilk("2%Milk");
		System.out.println("2% Milk");
		
	}

	@Override
	public void buildFalvour() {
		starbucks.setFalvour("Caffe Mocha");
		System.out.println("Caffe Mocha");
		
	}
}