package starbucksTeam;

public class Starbucks {
   // private float totalPrice = 0;

    private Size size;
    private Flavour flavour;
    private Milk milk;
    private String finalOrder ="";
   

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public void setFlavour(Flavour flavour) {
        this.flavour = flavour;
    }

    public Milk getMilk() {
        return milk;
    }

    public void setMilk(Milk milk) {
        this.milk = milk;
    }

    public String getFinalOrder(){
        return finalOrder;
    }

    public void addtoOrdr(String order){
        this.finalOrder= finalOrder +order;
    }
    

   /* public float getTotalPrice() {
        return totalPrice;
    }*/

   /* public void addToPrice(float price) {
        this.totalPrice = totalPrice + price;
    }*/
}

