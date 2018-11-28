//abstract builder
abstract class StarbucksBuilder {
	protected Starbucks starbucks;
	 
	public Starbucks getStarbucks() {
		return starbucks;
	}
 
	public void createStarbucks() {
		starbucks = new Starbucks();
		System.out.println("Your drink is read");
	}
 
	public abstract void buildSize();
	public abstract void buildDrink();

}
