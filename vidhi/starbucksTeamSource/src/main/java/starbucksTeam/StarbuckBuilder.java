package starbucksTeam;

public class StarbuckBuilder {
    Starbucks starbucks = new Starbucks();

    public StarbuckBuilder withFlavour(Flavour flavour) {
        starbucks.setFlavour(flavour);
        starbucks.addtoOrdr(flavour.getName());
        //starbucks.addToPrice(topping.getCost());
        return this;
    }

    public StarbuckBuilder withSize(Size size) {
        starbucks.setSize(size);
        starbucks.addtoOrdr(size.getName());
        //starbucks.addToPrice(size.getCost());
        return this;
    }

    public StarbuckBuilder withMilk(Milk milk) {
        starbucks.setMilk(milk);
        starbucks.addtoOrdr(milk.getName());
        //starbucks.addToPrice(crust.getCost());
        return this;
    }

    public Starbucks build() {
        return starbucks;
    }

    public String finalReceipt(){
        return  starbucks.getFinalOrder();
    }

   /* public double calculatePrice() {
        return starbucks.getTotalPrice();
    }*/
}
