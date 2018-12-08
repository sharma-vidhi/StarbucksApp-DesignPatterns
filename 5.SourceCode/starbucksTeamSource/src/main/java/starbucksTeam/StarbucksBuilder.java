//abstract builder
abstract class StarbucksBuilder {
	protected Starbucks starbucks;
	 
	public Starbucks getStarbucks() {
		return starbucks;
	}
 
	public void createStarbucks() {
		starbucks = new Starbucks();
		System.out.println("Your Order:");
	}
 
	
	public abstract void buildDrink();
	public abstract void buildSize();
	public abstract void buildMilk();
	public abstract void buildFalvour();

}
