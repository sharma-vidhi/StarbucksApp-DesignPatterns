//director to encapsulate the builder
class Order {
	private StarbucksBuilder starbucksBuilder;
 
	public void setStarbucksBuilder(StarbucksBuilder builder) {
		starbucksBuilder = builder;
	}
 
	public Starbucks getstarbucksDrink() {
		return starbucksBuilder.getStarbucks();
	}
 
	public void constructStarbucks() {
		starbucksBuilder.createStarbucks();
		starbucksBuilder.buildDrink();
		starbucksBuilder.buildSize();
		starbucksBuilder.buildMilk();
		starbucksBuilder.buildFalvour();
	}
}