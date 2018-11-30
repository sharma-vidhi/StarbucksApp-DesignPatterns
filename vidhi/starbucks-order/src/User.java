//order screen
public class User {
	public static void main(String[] args) {
		Order order = new Order();
		StarbucksBuilder coffeeBuilder = new CoffeeBuilder();
		//StarbucksBuilder teaBuilder = new TeaBuilder();
		
		//edit based on order given
		order.setStarbucksBuilder(coffeeBuilder);
		//order.constructStarbucks();
		//order.setStarbucksBuilder(teaBuilder );
		order.constructStarbucks();
		
		
 
		//get the drink built
		Starbucks drink = order.getstarbucksDrink();
 
	}
}